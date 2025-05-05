package com.tcc.pagamento_service.infra.rabbitmq;

public class PagamentoCriadoEvent {
    private Long pagamentoId;
    private Long pedidoId;
    private String metodoPagamento;
    private String status;

    public PagamentoCriadoEvent(){}

    public PagamentoCriadoEvent(Long pagamentoId, Long pedidoId, String metodoPagamento, String status) {
        this.pagamentoId = pagamentoId;
        this.pedidoId = pedidoId;
        this.metodoPagamento = metodoPagamento;
        this.status = status;
    }

    public Long getPagamentoId() {
        return pagamentoId;
    }

    public void setPagamentoId(Long pagamentoId) {
        this.pagamentoId = pagamentoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
