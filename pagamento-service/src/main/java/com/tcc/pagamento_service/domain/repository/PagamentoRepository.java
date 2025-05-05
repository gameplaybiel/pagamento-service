package com.tcc.pagamento_service.domain.repository;

import com.tcc.pagamento_service.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
