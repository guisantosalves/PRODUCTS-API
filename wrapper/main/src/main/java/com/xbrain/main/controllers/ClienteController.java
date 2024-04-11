package com.xbrain.main.controllers;

import com.xbrain.main.mapper.ClienteMapper;
import com.xbrain.main.schema.ClienteSchema;
import com.xbrain.main.service.ClienteService;
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
@RequestMapping(value = "/cliente")
@RequiredArgsConstructor
public class ClienteController {

  @Autowired
  private final ClienteService clienteService;

  @Autowired
  private final ClienteMapper clienteMapper;

  @GetMapping
  public ResponseEntity<List<ClienteSchema>> findAllClientes() {
    List<ClienteSchema> clienteSchemas = clienteMapper.toSchemaList(clienteService.findAll());
    return new ResponseEntity<>(clienteSchemas, HttpStatus.OK);
  }

  @GetMapping(value = "/{clienteId}")
  public ResponseEntity<ClienteSchema> getOneCliente(@PathVariable UUID clienteId){
    ClienteSchema clienteSchema = clienteMapper.toSchema(clienteService.findOne(clienteId));
    return new ResponseEntity<>(clienteSchema, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ClienteSchema> createOneCliente(@RequestBody ClienteSchema clienteSchema) {
    ClienteSchema clienteToReturn = clienteMapper.toSchema(clienteService.create(clienteSchema));
    return new ResponseEntity<>(clienteToReturn, HttpStatus.OK);
  }

  @PutMapping(value = "/{clienteId}")
  public ResponseEntity<ClienteSchema> updateOne(@PathVariable UUID clienteId,
      @RequestBody ClienteSchema clienteSchema) {
    ClienteSchema clienteToReturn =
        clienteMapper.toSchema(clienteService.updateOne(clienteSchema, clienteId));
    return new ResponseEntity<>(clienteToReturn, HttpStatus.OK);
  }

  @DeleteMapping(value = "/{clienteId}")
  public ResponseEntity<ClienteSchema> deleteOne(@PathVariable UUID clienteId){
    ClienteSchema clienteSchema = clienteMapper.toSchema(clienteService.deleteOne(clienteId));
    return new ResponseEntity<>(clienteSchema, HttpStatus.OK);
  }
}
