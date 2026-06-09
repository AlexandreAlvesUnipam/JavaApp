package com.ecommerce.ecommerce.modelo;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PRAZO")
public class PagamentoPrazo extends Pagamento {

    private int mesesParaPagamento;
    private static final BigDecimal JUROS_MENSAL = new BigDecimal("0.02"); // 2% ao mês

    protected PagamentoPrazo() {}

    public PagamentoPrazo(Pedido pedido, int mesesParaPagamento) {
        super(pedido);
        this.mesesParaPagamento = mesesParaPagamento;
    }

    @Override
    public BigDecimal calcularValorFinal() {
        BigDecimal valorOriginal = getPedido().getValorTotal();
        // Cálculo simples de juros: Valor + (Valor * Juros * Meses)
        BigDecimal valorJuros = valorOriginal.multiply(JUROS_MENSAL).multiply(BigDecimal.valueOf(mesesParaPagamento));
        return valorOriginal.add(valorJuros);
    }
}