package com.tcc.pagamento_service.infra.rabbitmq;

import com.tcc.pagamento_service.application.dto.PedidoDTO;
import com.tcc.pagamento_service.infra.service.PagamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = "pagamento.queue")
public class PagamentoListener {
    private final PagamentoService pagamentoService;

    public PagamentoListener(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @RabbitListener(queues = "${pagamento.queue}")
    public void handlePagamento(PedidoDTO dto) {
        pagamentoService.processar(dto);
        System.out.println("Pagamento processado para pedido: " + dto.getId());
    }
}
