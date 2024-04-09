package com.xbrain.main.service;

import com.xbrain.main.dto.ProdutoDTO;
import com.xbrain.main.schema.ProdutoSchema;
import java.util.List;
import java.util.UUID;

public interface ProdutoService {
  ProdutoDTO create(ProdutoSchema produtoSchema);
  List<ProdutoDTO> findAll();
  ProdutoDTO findOne(UUID produtoId);
  ProdutoDTO updateOne(ProdutoSchema produtoSchema, UUID produtoId);
  ProdutoDTO deleteOne(UUID produtoId);
}
