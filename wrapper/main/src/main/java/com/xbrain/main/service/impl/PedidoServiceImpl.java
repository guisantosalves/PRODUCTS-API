package com.xbrain.main.service.impl;

import com.xbrain.main.dto.PedidoDTO;
import com.xbrain.main.schema.PedidoSchema;
import com.xbrain.main.service.PedidoService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

  @Override
  public PedidoDTO create(PedidoSchema pedidoSchema) {
    return null;
  }

  @Override
  public List<PedidoDTO> findAll() {
    return null;
  }

  @Override
  public PedidoDTO findOne(UUID pedidoId) {
    return null;
  }

  @Override
  public PedidoDTO updateOne(PedidoSchema pedidoSchema, UUID pedidoId) {
    return null;
  }

  @Override
  public PedidoDTO deleteOne(UUID pedidoId) {
    return null;
  }
}
