package com.fadesp.api.repository;

import com.fadesp.api.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByCodigoDoDebito(String codigoDoDebito);

    List<Pagamento> findByCpfCnpj(String cpfCnpj);

    List<Pagamento> findByStatusPagamento(String statusPagamento);

}
