package com.ecommerce.ecommerce.modelo;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PIX")
public class PagamentoPix extends Pagamento {

    private static final BigDecimal DESCONTO = new BigDecimal("0.05"); // 5% de desconto

    protected PagamentoPix() {}

    public PagamentoPix(Pedido pedido) {
        super(pedido);
    }

    @Override
    public BigDecimal calcularValorFinal() {
        BigDecimal valorOriginal = getPedido().getValorTotal();
        BigDecimal valorDesconto = valorOriginal.multiply(DESCONTO);
        return valorOriginal.subtract(valorDesconto);
    }
}