package com.tcc.pagamento_service.infra.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "pedido-service",
        url = "${pedido.service.url:http://localhost:8084}", // Valor padrão após ":"
        configuration = FeignConfig.class
)
public interface PedidoClient {
    @GetMapping("/pedido/{id}/existe")
    Boolean existePedido(@PathVariable Long id);
}
