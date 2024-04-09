package com.xbrain.main.dto;

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
public class PedidoDTO {
  private UUID id;

  private Cliente cliente;

  private List<UUID> codigoProdutos;

  private double valorTotal;

  private String enderecoEntrega;
}
