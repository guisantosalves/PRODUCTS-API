package com.xbrain.consumer.config;

import com.xbrain.consumer.service.ConsumeMessageService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureRabbitMq {
  public static final String QUEUE_NAME = "myQueue";

  @Bean
  public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory,
      MessageListenerAdapter messageListenerAdapter) {
    SimpleMessageListenerContainer simpleMessageListenerContainer =
        new SimpleMessageListenerContainer();
    simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
    simpleMessageListenerContainer.setQueueNames(QUEUE_NAME);
    simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
    return simpleMessageListenerContainer;
  }

  @Bean
  public MessageListenerAdapter messageListenerAdapter(ConsumeMessageService consumeMessageService) {
    return new MessageListenerAdapter(consumeMessageService, "consumeMessage");
  }
}
