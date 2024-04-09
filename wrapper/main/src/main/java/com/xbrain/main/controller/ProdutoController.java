package com.xbrain.main.controller;

import com.xbrain.main.mapper.ProdutoMapper;
import com.xbrain.main.schema.ProdutoSchema;
import com.xbrain.main.service.ProdutoService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produto")
@RequiredArgsConstructor
public class ProdutoController {
  @Autowired
  private final ProdutoService produtoService;
  @Autowired
  private final ProdutoMapper produtoMapper;
  @GetMapping
  public ResponseEntity<List<ProdutoSchema>> findAllprodutos() {
    List<ProdutoSchema> ProdutoSchemas = produtoMapper.toSchemaList(produtoService.findAll());
    return new ResponseEntity<>(ProdutoSchemas, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ProdutoSchema> createOneproduto(@RequestBody ProdutoSchema ProdutoSchema) {
    ProdutoSchema produtoToReturn = produtoMapper.toSchema(produtoService.create(ProdutoSchema));
    return new ResponseEntity<>(produtoToReturn, HttpStatus.OK);
  }

  @PutMapping(value = "/{produtoId}")
  public ResponseEntity<ProdutoSchema> updateOne(@PathVariable UUID produtoId,
      @RequestBody ProdutoSchema ProdutoSchema) {
    ProdutoSchema produtoToReturn =
        produtoMapper.toSchema(produtoService.updateOne(ProdutoSchema, produtoId));
    return new ResponseEntity<>(produtoToReturn, HttpStatus.OK);
  }

  @DeleteMapping(value = "/{produtoId}")
  public ResponseEntity<ProdutoSchema> deleteOne(@PathVariable UUID produtoId){
    ProdutoSchema ProdutoSchema = produtoMapper.toSchema(produtoService.deleteOne(produtoId));
    return new ResponseEntity<>(ProdutoSchema, HttpStatus.OK);
  }
}
