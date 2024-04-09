package com.xbrain.main.service.impl;

import com.xbrain.main.dto.ClienteDTO;
import com.xbrain.main.schema.ClienteSchema;
import com.xbrain.main.service.ClienteService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Override
  public ClienteDTO create(ClienteSchema clienteSchema) {
    return null;
  }

  @Override
  public List<ClienteDTO> findAll() {
    return null;
  }

  @Override
  public ClienteDTO findOne(UUID clienteId) {
    return null;
  }

  @Override
  public ClienteDTO updateOne(ClienteSchema clienteSchema, UUID id) {
    return null;
  }

  @Override
  public ClienteDTO deleteOne(UUID clienteId) {
    return null;
  }
}
