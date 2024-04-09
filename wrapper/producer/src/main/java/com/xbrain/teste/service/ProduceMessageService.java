package com.xbrain.teste.service;

import com.xbrain.teste.config.ConfigureRabbitMq;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduceMessageService {

  @Autowired
  private final RabbitTemplate rabbitTemplate;

  public ProduceMessageService(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public String produceMessage(String message) {
    rabbitTemplate.convertAndSend(ConfigureRabbitMq.EXCHANGE_NAME, "myRoutingKey.messages",
        message);
    return "Message(" + message + ")" + " has been produced.";
  }
}