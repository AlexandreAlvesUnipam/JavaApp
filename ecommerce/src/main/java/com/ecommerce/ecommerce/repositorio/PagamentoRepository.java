package com.ecommerce.ecommerce.repositorio;

import com.ecommerce.ecommerce.modelo.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID>{

}