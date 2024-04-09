package com.xbrain.teste.controller;

import com.xbrain.teste.service.ProduceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProduceMessageController {

  @Autowired
  ProduceMessageService produceMessageService;

  @PostMapping("/produce")
  public String produceMessage(@RequestParam String message) {
    return produceMessageService.produceMessage(message);
  }
}