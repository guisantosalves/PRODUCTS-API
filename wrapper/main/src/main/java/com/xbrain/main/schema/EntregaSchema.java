package com.xbrain.main.schema;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntregaSchema {
  private UUID id;

  private UUID pedido;

  private String enderecoEntrega;
}
