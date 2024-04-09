package com.xbrain.main.mapper;

import com.xbrain.db.model.Produto;
import com.xbrain.main.dto.ProdutoDTO;
import com.xbrain.main.schema.ProdutoSchema;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ProdutoMapper {
  ProdutoDTO toDto(ProdutoSchema produtoSchema);
  ProdutoDTO toDto(Produto produto);
  List<ProdutoDTO> toDtoList(List<Produto> produtoList);
  List<ProdutoSchema> toSchemaList(List<ProdutoDTO> produtoDTOList);
  ProdutoSchema toSchema(ProdutoDTO produtoDTO);
  Produto toEntity(ProdutoDTO produtoDTO);
}
