package com.tcc.pagamento_service.application.dto;

import java.math.BigDecimal;

public class PedidoDTO {
    private Long id;
    private BigDecimal valorTotal;
    private String status;

    public PedidoDTO(){}

    public PedidoDTO(Long id, BigDecimal valorTotal, String status) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
