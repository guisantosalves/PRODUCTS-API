package com.xbrain.main.mapper;

import com.xbrain.db.model.Entrega;
import com.xbrain.main.dto.EntregaDTO;
import com.xbrain.main.schema.EntregaSchema;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EntregaMapper {
  EntregaDTO toDto(EntregaSchema EntregaSchema);
  @Mapping(target = "pedido", source = "pedido.id")
  EntregaDTO toDto(Entrega Entrega);
  List<EntregaDTO> toDtoList(List<Entrega> EntregaList);
  List<EntregaSchema> toSchemaList(List<EntregaDTO> EntregaDTOList);
  EntregaSchema toSchema(EntregaDTO EntregaDTO);
  @Mapping(target = "pedido", ignore = true)
  Entrega toEntity(EntregaDTO EntregaDTO);
}
