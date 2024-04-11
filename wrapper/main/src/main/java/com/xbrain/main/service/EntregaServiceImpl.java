package com.xbrain.main.service;

import com.xbrain.db.model.Entrega;
import com.xbrain.db.model.Pedido;
import com.xbrain.db.repository.EntregaRepository;
import com.xbrain.db.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class EntregaServiceImpl implements EntregaService {
  @Autowired
  private final EntregaRepository entregaRepository;

  @Autowired
  private final PedidoRepository pedidoRepository;

  @Override
  public Entrega create(UUID basedOnThisDelivery) {
    Pedido pedidoEntity = pedidoRepository.findById(basedOnThisDelivery)
        .orElseThrow(() -> new RuntimeException("it can not find your data"));

    Entrega newEntrega = new Entrega();
    newEntrega.setPedido(pedidoEntity);
    newEntrega.setEnderecoEntrega(pedidoEntity.getEnderecoEntrega());

    return entregaRepository.saveAndFlush(newEntrega);
  }

  @Override
  public Entrega updateOne(UUID basedOnThisDelivery) {
    Pedido pedidoEntity = pedidoRepository.findById(basedOnThisDelivery)
        .orElseThrow(() -> new RuntimeException("it can not find your data"));

    // find the delivery by pedido_ID
    Entrega entregaToUpdate = entregaRepository.findByPedidoId(pedidoEntity.getId());
    entregaToUpdate.setEnderecoEntrega(pedidoEntity.getEnderecoEntrega());

    entregaRepository.saveAndFlush(entregaToUpdate);
    return null;
  }

}
