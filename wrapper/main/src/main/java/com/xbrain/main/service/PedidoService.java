package com.xbrain.main.service;

import com.xbrain.main.dto.PedidoDTO;
import com.xbrain.main.schema.PedidoSchema;
import java.util.List;
import java.util.UUID;

public interface PedidoService {
  PedidoDTO create(PedidoSchema pedidoSchema);
  List<PedidoDTO> findAll();
  PedidoDTO findOne(UUID pedidoId);
  PedidoDTO updateOne(PedidoSchema pedidoSchema, UUID pedidoId);
  PedidoDTO deleteOne(UUID pedidoId);
}
