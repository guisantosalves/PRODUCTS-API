package com.xbrain.main.controllers;

import com.xbrain.main.mapper.PedidoMapper;
import com.xbrain.main.schema.PedidoSchema;
import com.xbrain.main.service.PedidoService;
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
@RequestMapping(value = "/pedido")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PedidoController {

  @Autowired
  private final PedidoService pedidoService;
  @Autowired
  private final PedidoMapper pedidoMapper;
  @GetMapping
  public ResponseEntity<List<PedidoSchema>> findAllpedidos() {
    List<PedidoSchema> PedidoSchemas = pedidoMapper.toSchemaList(pedidoService.findAll());
    return new ResponseEntity<>(PedidoSchemas, HttpStatus.OK);
  }

  @GetMapping(value = "/{pedidoId}")
  public ResponseEntity<PedidoSchema> findAllpedidos(@PathVariable UUID pedidoId) {
    PedidoSchema PedidoSchemas = pedidoMapper.toSchema(pedidoService.findOne(pedidoId));
    return new ResponseEntity<>(PedidoSchemas, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PedidoSchema> createOnepedido(@RequestBody PedidoSchema PedidoSchema) {
    PedidoSchema pedidoToReturn = pedidoMapper.toSchema(pedidoService.create(PedidoSchema));
    return new ResponseEntity<>(pedidoToReturn, HttpStatus.OK);
  }

  @PutMapping(value = "/{pedidoId}")
  public ResponseEntity<PedidoSchema> updateOne(@PathVariable UUID pedidoId,
      @RequestBody PedidoSchema PedidoSchema) {
    PedidoSchema pedidoToReturn =
        pedidoMapper.toSchema(pedidoService.updateOne(PedidoSchema, pedidoId));
    return new ResponseEntity<>(pedidoToReturn, HttpStatus.OK);
  }

  @DeleteMapping(value = "/{pedidoId}")
  public ResponseEntity<PedidoSchema> deleteOne(@PathVariable UUID pedidoId){
    PedidoSchema PedidoSchema = pedidoMapper.toSchema(pedidoService.deleteOne(pedidoId));
    return new ResponseEntity<>(PedidoSchema, HttpStatus.OK);
  }
}
