package com.xbrain.main.service;

import com.xbrain.db.model.Cliente;
import com.xbrain.db.repository.ClienteRepository;
import com.xbrain.main.dto.ClienteDTO;
import com.xbrain.main.mapper.ClienteMapper;
import com.xbrain.main.schema.ClienteSchema;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private final ClienteRepository clienteRepository;

  @Autowired
  private final ClienteMapper clienteMapper;

  @Override
  public ClienteDTO create(ClienteSchema clienteSchema) {
    ClienteDTO clienteDTO = clienteMapper.toDto(clienteSchema);
    Cliente clienteEntity = clienteMapper.toEntity(clienteDTO);

    // saving
    Cliente savedData = clienteRepository.save(clienteEntity);

    return clienteMapper.toDto(savedData);
  }

  @Override
  public List<ClienteDTO> findAll() {
    List<Cliente> clienteList = clienteRepository.findAll();
    return clienteMapper.toDtoList(clienteList);
  }

  @Override
  public ClienteDTO findOne(UUID clienteId) {
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("It can not find your data"));
    return clienteMapper.toDto(cliente);
  }

  @Override
  public ClienteDTO updateOne(ClienteSchema clienteSchema, UUID id) {
    ClienteDTO clienteToUpdate = clienteMapper.toDto(clienteSchema);

    Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("It can not find your data"));

    // updating data
    cliente.setNome(clienteToUpdate.getNome());

    // saving data updated
    return clienteMapper.toDto(clienteRepository.save(cliente));
  }

  @Override
  public ClienteDTO deleteOne(UUID clienteId) {
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("It can not find your data"));
    clienteRepository.delete(cliente);
    return clienteMapper.toDto(cliente);
  }
}
