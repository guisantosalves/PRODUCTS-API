package com.xbrain.producer.service;

import com.xbrain.producer.config.ConfigureRabbitMq;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class ProduceMessageService {

  @Autowired
  private final RabbitTemplate rabbitTemplate;

  public ProduceMessageService(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public ResponseEntity<String> produceMessage(String message) {
    rabbitTemplate.convertAndSend(ConfigureRabbitMq.EXCHANGE_NAME, "myRoutingKey.messages",
        message);
    return new ResponseEntity<>("enviada", HttpStatus.CREATED);
  }
}