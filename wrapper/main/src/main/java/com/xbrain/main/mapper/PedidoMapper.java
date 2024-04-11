package com.xbrain.main.mapper;

import com.xbrain.db.model.Pedido;
import com.xbrain.main.dto.PedidoDTO;
import com.xbrain.main.schema.PedidoSchema;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PedidoMapper {
  @Mapping(target = "valorTotal", ignore = true)
  PedidoDTO toDto(PedidoSchema PedidoSchema);
  @Mapping(target = "clienteId", source = "cliente.id")
  PedidoDTO toDto(Pedido Pedido);
  @Mapping(target = "clienteId", source = "cliente.id")
  List<PedidoDTO> toDtoList(List<Pedido> PedidoList);
  List<PedidoSchema> toSchemaList(List<PedidoDTO> PedidoDTOList);
  PedidoSchema toSchema(PedidoDTO PedidoDTO);
  Pedido toEntity(PedidoDTO PedidoDTO);
}
