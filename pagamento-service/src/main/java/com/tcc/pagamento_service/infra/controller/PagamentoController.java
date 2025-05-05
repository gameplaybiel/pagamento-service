package com.tcc.pagamento_service.infra.controller;

import com.tcc.pagamento_service.application.dto.PagamentoDTO;
import com.tcc.pagamento_service.application.usecase.PagamentoUseCase;
import com.tcc.pagamento_service.domain.model.Pagamento;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    private final PagamentoUseCase pagamentoUseCase;

    public PagamentoController(PagamentoUseCase pagamentoUseCase) {
        this.pagamentoUseCase = pagamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoUseCase.criarPagamento(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos() {
        return ResponseEntity.ok(pagamentoUseCase.listarPorPagamento());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoUseCase.buscarPagamentoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pagamentoUseCase.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
