package com.xbrain.main.mapper;

import com.xbrain.db.model.Cliente;
import com.xbrain.main.dto.ClienteDTO;
import com.xbrain.main.schema.ClienteSchema;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteMapper {
  ClienteDTO toDto(ClienteSchema ClienteSchema);
  ClienteDTO toDto(Cliente Cliente);
  List<ClienteDTO> toDtoList(List<Cliente> ClienteList);
  List<ClienteSchema> toSchemaList(List<ClienteDTO> ClienteDTOList);
  ClienteSchema toSchema(ClienteDTO ClienteDTO);
  Cliente toEntity(ClienteDTO ClienteDTO);
}
