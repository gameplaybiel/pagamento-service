package com.tcc.pagamento_service.infra.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // Nomes consistentes com os usados no Publisher
    public static final String PAGAMENTO_EXCHANGE = "pagamento.exchange";
    public static final String PAGAMENTO_CRIADO_QUEUE = "pagamento.criado.queue";
    public static final String PAGAMENTO_DELETADO_QUEUE = "pagamento.deletado.queue";
    public static final String PAGAMENTO_CRIADO_ROUTING_KEY = "pagamento.criado";
    public static final String PAGAMENTO_DELETADO_ROUTING_KEY = "pagamento.deletado";

    @Bean
    public DirectExchange pagamentoExchange() {
        return new DirectExchange(PAGAMENTO_EXCHANGE);
    }

    @Bean
    public Queue pagamentoCriadoQueue() {
        return new Queue(PAGAMENTO_CRIADO_QUEUE, true);
    }

    @Bean
    public Queue pagamentoDeletadoQueue() {
        return new Queue(PAGAMENTO_DELETADO_QUEUE, true);
    }

    @Bean
    public Binding bindingCriado() {
        return BindingBuilder.bind(pagamentoCriadoQueue())
                .to(pagamentoExchange())
                .with(PAGAMENTO_CRIADO_ROUTING_KEY);
    }

    @Bean
    public Binding bindingDeletado() {
        return BindingBuilder.bind(pagamentoDeletadoQueue())
                .to(pagamentoExchange())
                .with(PAGAMENTO_DELETADO_ROUTING_KEY);
    }
}
