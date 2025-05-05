package com.tcc.pagamento_service;

import com.tcc.pagamento_service.application.dto.PagamentoDTO;
import com.tcc.pagamento_service.application.usecase.PagamentoUseCase;
import com.tcc.pagamento_service.domain.model.MetodoPagamento;
import com.tcc.pagamento_service.domain.model.Pagamento;
import com.tcc.pagamento_service.domain.model.StatusPagamento;
import com.tcc.pagamento_service.domain.repository.PagamentoRepository;
import com.tcc.pagamento_service.infra.config.PedidoClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.tcc.pagamento_service.domain.model.MetodoPagamento.PIX;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoUseCaseTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private PedidoClient pedidoClient;

    @InjectMocks
    private PagamentoUseCase pagamentoUseCase;

    @Test
    void criarPagamento_ComDadosValidos_DeveRetornarPagamentoSalvo() {
        // Arrange
        PagamentoDTO dto = new PagamentoDTO(
                1L,
                PIX,
                StatusPagamento.CRIADO,
                LocalDate.now(),
                LocalDateTime.now()
        );

        when(pedidoClient.existePedido(anyLong())).thenReturn(true);
        when(pagamentoRepository.save(any(Pagamento.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Pagamento resultado = pagamentoUseCase.criarPagamento(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(dto.getPedidoId(), resultado.getPedidoId());
        assertEquals(dto.getMetodoPagamento(), resultado.getMetodoPagamento());
        verify(pedidoClient).existePedido(dto.getPedidoId());
        verify(pagamentoRepository).save(any(Pagamento.class));
    }

    @Test
    void criarPagamento_ComPedidoInexistente_DeveLancarExcecao() {
        // Arrange
        PagamentoDTO dto = new PagamentoDTO(
                99L,
                PIX,
                StatusPagamento.CRIADO,
                LocalDate.now(),
                LocalDateTime.now()
        );

        when(pedidoClient.existePedido(anyLong())).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pagamentoUseCase.criarPagamento(dto)
        );

        assertEquals("Pedido não encontrado!", exception.getMessage());
        verify(pedidoClient).existePedido(dto.getPedidoId());
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }

    @Test
    void criarPagamento_ComMetodoPagamentoNulo_DeveLancarExcecao() {
        // Arrange
        PagamentoDTO dto = new PagamentoDTO(
                1L,
                null, // Método nulo
                StatusPagamento.CRIADO,
                LocalDate.now(),
                LocalDateTime.now()
        );

        when(pedidoClient.existePedido(anyLong())).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pagamentoUseCase.criarPagamento(dto)
        );

        assertEquals("Método de pagamento é obrigatório", exception.getMessage());
        verify(pagamentoRepository, never()).save(any(Pagamento.class));
    }

    @Test
    void listarPorPagamento_QuandoExistemPagamentos_DeveRetornarLista() {
        // Arrange
        Pagamento pagamento = new Pagamento(
                1L,
                PIX,
                StatusPagamento.CRIADO,
                LocalDate.now(),
                LocalDateTime.now()
        );

        when(pagamentoRepository.findAll()).thenReturn(List.of(pagamento));

        // Act
        List<Pagamento> resultado = pagamentoUseCase.listarPorPagamento();

        // Assert
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(pagamentoRepository).findAll();
        verify(pagamentoRepository).flush();
    }

    @Test
    void buscarPagamentoPorId_QuandoPagamentoExiste_DeveRetornarPagamento() {
        // Arrange
        Long id = null;
        Pagamento pagamento = new Pagamento(
                1L,
                PIX,
                StatusPagamento.CRIADO,
                LocalDate.now(),
                LocalDateTime.now()
        );

        when(pagamentoRepository.findById(id)).thenReturn(Optional.of(pagamento));

        // Act
        Pagamento resultado = pagamentoUseCase.buscarPagamentoPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(pagamentoRepository).findById(id);
    }

    @Test
    void buscarPagamentoPorId_QuandoPagamentoNaoExiste_DeveLancarExcecao() {
        // Arrange
        Long id = 99L;
        when(pagamentoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> pagamentoUseCase.buscarPagamentoPorId(id)
        );

        assertEquals("Pagamento não encontrado com id: " + id, exception.getMessage());
        verify(pagamentoRepository).findById(id);
    }

    @Test
    void deletarPedido_DeveChamarRepositorio() {
        // Arrange
        Long id = 1L;
        doNothing().when(pagamentoRepository).deleteById(id);

        // Act
        pagamentoUseCase.deletarPedido(id);

        // Assert
        verify(pagamentoRepository).deleteById(id);
    }
}
