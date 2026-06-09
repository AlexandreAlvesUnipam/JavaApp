/*

O padrão do JPA é:

Criamos interfaces para cada modelo em nossa aplicação.
Essas interfaces vão Herdar de JpaRepository e implementar os métodos para o CRUD.

*/

package com.ecommerce.ecommerce.repositorio;

import com.ecommerce.ecommerce.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>{

}