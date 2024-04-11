package com.xbrain.main.service;

import com.xbrain.db.model.Entrega;
import com.xbrain.main.dto.ClienteDTO;
import com.xbrain.main.dto.EntregaDTO;
import com.xbrain.main.schema.ClienteSchema;
import com.xbrain.main.schema.EntregaSchema;
import java.util.List;
import java.util.UUID;

public interface EntregaService {
  Entrega create(UUID basedOnThisDelivery);
  Entrega updateOne(UUID basedOnThisDelivery);
}
