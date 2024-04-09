package com.xbrain.main.service.impl;

import com.xbrain.main.dto.ProdutoDTO;
import com.xbrain.main.schema.ProdutoSchema;
import com.xbrain.main.service.ProdutoService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

  @Override
  public ProdutoDTO create(ProdutoSchema produtoSchema) {
    return null;
  }

  @Override
  public List<ProdutoDTO> findAll() {
    return null;
  }

  @Override
  public ProdutoDTO findOne(UUID produtoId) {
    return null;
  }

  @Override
  public ProdutoDTO updateOne(ProdutoSchema produtoSchema, UUID produtoId) {
    return null;
  }

  @Override
  public ProdutoDTO deleteOne(UUID produtoId) {
    return null;
  }
}
