package com.ecommerce.ecommerce.modelo;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_itens_vitrine")
public class ItemVitrine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String descricao;
    private BigDecimal precoBase;
    private int quantidade;

    protected ItemVitrine() {}

    public ItemVitrine(String descricao, BigDecimal precoBase, int quantidade) {
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {
        return precoBase.multiply(BigDecimal.valueOf(quantidade));
    }
}