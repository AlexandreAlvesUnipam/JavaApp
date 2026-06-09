/*

Algumas observações:

Perceba que esses "modelos" serão nossas tabelas no banco de dados.
Fazemos uma "mapeamento": Os nossos objetos no Java serão as tabelas no banco.

*/

package com.ecommerce.ecommerce.modelo;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal limiteDeCredito;

    protected Cliente() {}

    public Cliente(String nome, BigDecimal limiteDeCredito) {
        this.nome = nome;
        this.limiteDeCredito = limiteDeCredito;
    }

    public boolean temLimiteDisponivel(BigDecimal valorDaCompra) {
        return this.limiteDeCredito.compareTo(valorDaCompra) >= 0;
    }

    public void debitarLimite(BigDecimal valor) {
        if (!temLimiteDisponivel(valor)) {
            throw new IllegalStateException("Limite de crédito insuficiente para o parceiro.");
        }
        this.limiteDeCredito = this.limiteDeCredito.subtract(valor);
    }
    
    public String getNome() { return nome; }
}