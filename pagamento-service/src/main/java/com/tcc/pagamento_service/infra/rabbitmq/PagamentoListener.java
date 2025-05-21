package com.tcc.pagamento_service.infra.rabbitmq;

import com.tcc.pagamento_service.infra.service.PagamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PagamentoListener {
    private final PagamentoService pagamentoService;

    public PagamentoListener(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @RabbitListener(queues = "${pagamento.queue}")
    public void handlePagamento(Pedido pedido) {
        pagamentoService.processar(pedido);
        System.out.println("Pagamento processado para pedido: " + pedido.getId());
    }
}
