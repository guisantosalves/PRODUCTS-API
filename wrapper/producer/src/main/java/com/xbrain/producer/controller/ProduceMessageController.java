package com.xbrain.producer.controller;

import com.xbrain.producer.service.ProduceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceMessageController {

  @Autowired
  ProduceMessageService produceMessageService;

  @PostMapping("/produce")
  public ResponseEntity<String> produceMessage(@RequestParam String message) {
    return produceMessageService.produceMessage(message);
  }
}