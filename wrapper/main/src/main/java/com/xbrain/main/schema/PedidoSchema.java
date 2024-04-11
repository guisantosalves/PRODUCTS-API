package com.xbrain.main.schema;

import com.xbrain.db.model.Cliente;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoSchema {

  private UUID id;

  private UUID clienteId;

  private List<UUID> codigoProdutos;

  private String enderecoEntrega;

  private double valorTotal;
}
