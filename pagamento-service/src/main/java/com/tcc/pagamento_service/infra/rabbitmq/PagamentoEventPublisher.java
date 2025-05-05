package com.tcc.pagamento_service.infra.rabbitmq;

import com.tcc.pagamento_service.domain.model.Pagamento;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PagamentoEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    // Nomes das filas/exchanges (devem bater com a configuração no RabbitMQConfig)
    private static final String PAGAMENTO_EXCHANGE_NAME = "pagamento.exchange";
    private static final String PAGAMENTO_CRIADO_ROUTING_KEY = "pagamento.criado";
    private static final String PAGAMENTO_DELETADO_ROUTING_KEY = "pagamento.deletado";

    public PagamentoEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarPagamentoCriado(Pagamento pagamento) {
        rabbitTemplate.convertAndSend(
                PAGAMENTO_EXCHANGE_NAME,
                PAGAMENTO_CRIADO_ROUTING_KEY,
                pagamento
        );
    }

    // Método novo para publicar eventos de deleção
    public void publicarPagamentoDeletado(Long pagamentoId) {
        rabbitTemplate.convertAndSend(
                PAGAMENTO_EXCHANGE_NAME,
                PAGAMENTO_DELETADO_ROUTING_KEY,
                pagamentoId  // Pode enviar só o ID ou um DTO com mais informações
        );
    }
}
