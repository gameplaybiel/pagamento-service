package com.tcc.pagamento_service.application.dto;

import com.tcc.pagamento_service.domain.model.MetodoPagamento;
import com.tcc.pagamento_service.domain.model.StatusPagamento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PagamentoDTO {
    private Long pedidoId;
    private MetodoPagamento metodoPagamento;
    private StatusPagamento statusPagamento;
    private LocalDate dataCriacao;
    private LocalDateTime dataAtualizacao;

    public PagamentoDTO(){}

    public PagamentoDTO(Long pedidoId, MetodoPagamento metodoPagamento, StatusPagamento statusPagamento, LocalDate dataCriacao, LocalDateTime dataAtualizacao) {
        this.pedidoId = pedidoId;
        this.metodoPagamento = metodoPagamento;
        this.statusPagamento = statusPagamento;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
