package com.xbrain.main.service;

import com.xbrain.main.dto.ClienteDTO;
import com.xbrain.main.schema.ClienteSchema;
import java.util.List;
import java.util.UUID;

public interface ClienteService {
  ClienteDTO create(ClienteSchema clienteSchema);
  List<ClienteDTO> findAll();
  ClienteDTO findOne(UUID clienteId);
  ClienteDTO updateOne(ClienteSchema clienteSchema, UUID id);
  ClienteDTO deleteOne(UUID clienteId);
}
