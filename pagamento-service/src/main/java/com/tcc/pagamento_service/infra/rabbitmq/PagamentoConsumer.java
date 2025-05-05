package com.tcc.pagamento_service.infra.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConsumer {
    @RabbitListener(queues = RabbitMQConfig.PAGAMENTO_CRIADO_QUEUE)
    public void receberPedidoCriado(PagamentoCriadoEvent event) {
        System.out.println("📦 Pagamento recebido - ID: " + event.getPagamentoId());
        System.out.println("👤 Pedido ID: " + event.getPedidoId());
    }

    @RabbitListener(queues = RabbitMQConfig.PAGAMENTO_DELETADO_QUEUE)
    public void processarPedidoDeletado(Long pagamentoId) {
        System.out.println("🚮 Pagamento deletado - ID: " + pagamentoId);
    }
}
