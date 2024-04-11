package com.xbrain.consumer.service;

import com.xbrain.main.service.EntregaService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumeMessageService {

  @Autowired
  public final EntregaService entregaService;

  public void consumeMessage(String messageBody) {
    try {
      // 0 -> verb
      // 1 -> id
      String[] spliteredResponse = messageBody.split("/");

      switch (spliteredResponse[0]) {
        case "create":
          // create a delivery with this id
          entregaService.create(UUID.fromString(spliteredResponse[1]));
          break;
        case "update":
          entregaService.updateOne(UUID.fromString(spliteredResponse[1]));
          break;
        default:
          // do nothing
          break;
      }
    } catch (Exception e) {
      throw new RuntimeException("Error when it's publishing");
    }
  }
}
