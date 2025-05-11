package br.com.fiap.on_data_thymeleaf.service;

import br.com.fiap.on_data_thymeleaf.controller.dto.OcorrenciaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OcorrenciaMessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OcorrenciaMessageProducer.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public OcorrenciaMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOcorrenciaMessage(OcorrenciaMessage message) {
        LOGGER.info(String.format("Sending occurrence message -> %s", message));
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
