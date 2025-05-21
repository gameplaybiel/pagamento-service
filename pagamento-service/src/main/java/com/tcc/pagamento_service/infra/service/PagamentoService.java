package com.tcc.pagamento_service.infra.service;

import com.tcc.pagamento_service.application.dto.PedidoDTO;
import com.tcc.pagamento_service.domain.model.Pagamento;
import com.tcc.pagamento_service.domain.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public void salvarPagamento(Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listarPagamento() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> buscarPorId(Long id) {
        return pagamentoRepository.findById(id);
    }

    public void deletarPagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public void processar(PedidoDTO pedidoDTO) {
        System.out.println("Processando pagamento para o pedido: " + pedidoDTO.getId());

    }
}
