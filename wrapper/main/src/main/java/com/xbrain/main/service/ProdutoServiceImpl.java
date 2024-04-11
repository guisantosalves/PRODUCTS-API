package com.xbrain.main.service;

import com.xbrain.db.model.Cliente;
import com.xbrain.db.model.Produto;
import com.xbrain.db.repository.ProdutoRepository;
import com.xbrain.main.dto.ClienteDTO;
import com.xbrain.main.dto.ProdutoDTO;
import com.xbrain.main.mapper.ProdutoMapper;
import com.xbrain.main.schema.ProdutoSchema;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

  @Autowired
  private final ProdutoRepository produtoRepository;

  @Autowired
  private final ProdutoMapper produtoMapper;

  @Override
  public ProdutoDTO create(ProdutoSchema produtoSchema) {
    ProdutoDTO produtoDTO = produtoMapper.toDto(produtoSchema);
    Produto produtoEntity = produtoMapper.toEntity(produtoDTO);

    // saving
    Produto savedData = produtoRepository.save(produtoEntity);

    return produtoMapper.toDto(savedData);
  }

  @Override
  public List<ProdutoDTO> findAll() {
    List<Produto> produtos = produtoRepository.findAll();
    return produtoMapper.toDtoList(produtos);
  }

  @Override
  public ProdutoDTO findOne(UUID produtoId) {
    Produto produto = produtoRepository.findById(produtoId)
        .orElseThrow(() -> new RuntimeException("It can not find your data"));
    return produtoMapper.toDto(produto);
  }

  @Override
  public ProdutoDTO updateOne(ProdutoSchema produtoSchema, UUID produtoId) {
    ProdutoDTO produtoToUpdate = produtoMapper.toDto(produtoSchema);

    Produto produto = produtoRepository.findById(produtoId)
        .orElseThrow(() -> new RuntimeException("It can not find your data"));

    // updating data
    produto.setValor(produtoToUpdate.getValor());

    // saving data updated
    return produtoMapper.toDto(produtoRepository.save(produto));
  }

  @Override
  public ProdutoDTO deleteOne(UUID produtoId) {
    Produto produto = produtoRepository.findById(produtoId)
        .orElseThrow(() -> new RuntimeException("It can not find your data"));
    produtoRepository.delete(produto);
    return produtoMapper.toDto(produto);
  }
}
