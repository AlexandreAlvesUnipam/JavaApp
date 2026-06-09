package com.ecommerce.ecommerce.repositorio;

import com.ecommerce.ecommerce.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID>{

}