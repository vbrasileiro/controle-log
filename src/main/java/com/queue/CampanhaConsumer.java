package com.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.Campanha;
import com.service.impl.CampanhaServiceImpl;

@Component
public class CampanhaConsumer<R> {
	
	@Autowired
	private CampanhaServiceImpl<R> campanhaServiceImpl;
	
	@RabbitListener(queues = "${javainuse.rabbitmq.queue}")
	public void recievedMessage(Campanha campanha) {
		campanhaServiceImpl.processaFilaSalvar(campanha);
	}

}
