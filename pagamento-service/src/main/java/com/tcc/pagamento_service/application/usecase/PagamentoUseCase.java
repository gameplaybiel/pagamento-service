package com.tcc.pagamento_service.application.usecase;

import com.tcc.pagamento_service.application.dto.PagamentoDTO;
import com.tcc.pagamento_service.domain.model.Pagamento;
import com.tcc.pagamento_service.domain.repository.PagamentoRepository;
import com.tcc.pagamento_service.infra.config.PedidoClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PagamentoUseCase {
    private final PagamentoRepository pagamentoRepository;
    private final PedidoClient pedidoClient;

    public PagamentoUseCase(PagamentoRepository pagamentoRepository, PedidoClient pedidoClient) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoClient = pedidoClient;
    }

    public Pagamento criarPagamento(PagamentoDTO dto) {
        // Valida se o pedido existe
        if (!pedidoClient.existePedido(dto.getPedidoId())) {
            throw new IllegalArgumentException("Pedido não encontrado!");
        }

        // Valida método de pagamento
        if (dto.getMetodoPagamento() == null) {
            throw new IllegalArgumentException("Método de pagamento é obrigatório");
        }

        Pagamento pagamento = new Pagamento(
                dto.getPedidoId(),
                dto.getMetodoPagamento(),
                dto.getStatusPagamento(),
                dto.getDataCriacao(),
                dto.getDataAtualizacao()
        );

        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listarPorPagamento() {
        pagamentoRepository.flush();
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPagamentoPorId(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com id: " + id));
    }

    public void deletarPedido(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
