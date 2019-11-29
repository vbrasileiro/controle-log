package com.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.entity.Campanha;

@Component
public class CampanhaQueue {

	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Value("${javainuse.rabbitmq.queue}")
    private String orderQueue;
	
	public void enviar(Campanha campanha) {
        rabbitTemplate.convertAndSend(orderQueue, campanha);
    }	
	
}
