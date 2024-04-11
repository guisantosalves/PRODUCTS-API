package com.xbrain.main.service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import com.xbrain.db.model.Cliente;
import com.xbrain.db.model.Entrega;
import com.xbrain.db.model.Pedido;
import com.xbrain.db.model.Produto;
import com.xbrain.db.repository.ClienteRepository;
import com.xbrain.db.repository.EntregaRepository;
import com.xbrain.db.repository.PedidoRepository;
import com.xbrain.db.repository.ProdutoRepository;
import com.xbrain.main.dto.ClienteDTO;
import com.xbrain.main.dto.PedidoDTO;
import com.xbrain.main.enums.Verbs;
import com.xbrain.main.mapper.PedidoMapper;
import com.xbrain.main.schema.PedidoSchema;
import com.xbrain.main.service.PedidoService;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

  @Autowired
  private final PedidoMapper pedidoMapper;

  @Autowired
  private final PedidoRepository pedidoRepository;

  @Autowired
  private final ProdutoRepository produtoRepository;

  @Autowired
  private final ClienteRepository clienteRepository;

  @Autowired
  private final EntregaRepository entregaRepository;

  @Value("${rabbitmq.server.producer}")
  private String rabbitMqServiceURL;

  public void sendingRequestToQueue(Pedido pedido, Verbs verb) {
    RestTemplate restTemplate = new RestTemplate();

    // mounting the request
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    StringBuilder building = new StringBuilder();

    switch (verb) {
      case CREATE ->
          building.append(rabbitMqServiceURL).append("/produce?message=").append("create/")
              .append(pedido.getId().toString());
      case UPDATE ->
          building.append(rabbitMqServiceURL).append("/produce?message=").append("update/")
              .append(pedido.getId().toString());
    }

    // mouting post
    ResponseEntity<String> response =
        restTemplate.postForEntity(building.toString(), null, String.class);

    if (!response.getStatusCode().equals(HttpStatus.CREATED)) {
      throw new RuntimeException("Impossible to send the data to the queue");
    }
  }

  @Override
  public PedidoDTO create(PedidoSchema pedidoSchema) {
    PedidoDTO pedidoDTO = pedidoMapper.toDto(pedidoSchema);


    // getting the client for this Pedido
    Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
        .orElseThrow(() -> new RuntimeException("it can not find your data"));

    // verifying if all the UUIDs for the produto exist
    double sum = 0;
    for (UUID cod : pedidoDTO.getCodigoProdutos()) {
      Produto prod = produtoRepository.findById(cod)
          .orElseThrow(() -> new RuntimeException("it can not find your data"));
      sum = sum + prod.getValor();
    }

    Pedido pedidoEntity = pedidoMapper.toEntity(pedidoDTO);
    pedidoEntity.setCliente(cliente);
    pedidoEntity.setValorTotal(sum);

    // saving
    Pedido savedData = pedidoRepository.saveAndFlush(pedidoEntity);

    PedidoDTO pedidoDTOToSend = pedidoMapper.toDto(savedData);

    // sending request to queue
    sendingRequestToQueue(savedData, Verbs.CREATE);

    return pedidoDTOToSend;
  }

  @Override
  public List<PedidoDTO> findAll() {
    List<PedidoDTO> pedidosDTO = pedidoMapper.toDtoList(pedidoRepository.findAll());
    return pedidosDTO;
  }

  @Override
  public PedidoDTO findOne(UUID pedidoId) {
    Pedido pedidoEntity = pedidoRepository.findById(pedidoId)
        .orElseThrow(() -> new RuntimeException("It can not find your data"));
    PedidoDTO pedidosDTO = pedidoMapper.toDto(pedidoEntity);
    return pedidosDTO;
  }

  @Override
  public PedidoDTO updateOne(PedidoSchema pedidoSchema, UUID pedidoId) {
    PedidoDTO pedidoToUpdate = pedidoMapper.toDto(pedidoSchema);

    Pedido pedidoEntity = pedidoRepository.findById(pedidoId)
        .orElseThrow(() -> new RuntimeException("It can not find your data"));

    // updating data
    pedidoEntity.setEnderecoEntrega(pedidoToUpdate.getEnderecoEntrega());

    // updating cliente
    Cliente clienteEntity = clienteRepository.findById(pedidoToUpdate.getClienteId())
        .orElseThrow(() -> new RuntimeException("It can not find your data"));
    pedidoEntity.setCliente(clienteEntity);

    // updating produtos
    pedidoEntity.setCodigoProdutos(pedidoToUpdate.getCodigoProdutos());

    double sum = 0;
    // updating valorTotal
    for (UUID cod : pedidoToUpdate.getCodigoProdutos()) {
      Produto currProd = produtoRepository.findById(cod)
          .orElseThrow(() -> new RuntimeException("It can not find your data"));
      sum = sum + currProd.getValor();
    }

    pedidoEntity.setValorTotal(sum);

    // saving data updated
    PedidoDTO pedidoDTOToSend = pedidoMapper.toDto(pedidoRepository.saveAndFlush(pedidoEntity));

    // sending request to queue
    sendingRequestToQueue(pedidoEntity, Verbs.UPDATE);

    return pedidoDTOToSend;
  }

  @Override
  public PedidoDTO deleteOne(UUID pedidoId) {
    Pedido pedido = pedidoRepository.findById(pedidoId)
        .orElseThrow(() -> new RuntimeException("It can not find your data"));

    // deleting delivery linked with that
    Entrega entrega = entregaRepository.findByPedidoId(pedido.getId());
    entregaRepository.delete(entrega);

    // deleting pedido
    pedidoRepository.delete(pedido);
    return pedidoMapper.toDto(pedido);
  }
}
