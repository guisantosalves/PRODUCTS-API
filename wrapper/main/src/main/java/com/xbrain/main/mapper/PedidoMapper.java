package com.xbrain.main.mapper;

import com.xbrain.db.model.Pedido;
import com.xbrain.main.dto.PedidoDTO;
import com.xbrain.main.schema.PedidoSchema;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface PedidoMapper {
  PedidoDTO toDto(PedidoSchema PedidoSchema);
  PedidoDTO toDto(Pedido Pedido);
  List<PedidoDTO> toDtoList(List<Pedido> PedidoList);
  List<PedidoSchema> toSchemaList(List<PedidoDTO> PedidoDTOList);
  PedidoSchema toSchema(PedidoDTO PedidoDTO);
  Pedido toEntity(PedidoDTO PedidoDTO);
}
