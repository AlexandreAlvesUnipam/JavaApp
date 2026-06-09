package com.ecommerce.ecommerce.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Relacionamento encapsulado: a lista não deve ser exposta diretamente para modificação
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemVitrine> itens = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    protected Pedido() {}

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    // Encapsulamento: O pedido controla como um item é adicionado e recalcula o total automaticamente
    public void adicionarItem(String descricao, BigDecimal precoBase, int quantidade) {
        if (this.status != StatusPedido.CRIADO) {
            throw new IllegalStateException("Não é possível alterar um pedido já processado.");
        }
        
        ItemVitrine item = new ItemVitrine(descricao, precoBase, quantidade);
        this.itens.add(item);
        
        // O cálculo do total é responsabilidade do pedido
        this.valorTotal = this.valorTotal.add(item.getSubtotal());
    }

    public void confirmarPagamento() {
        this.status = StatusPedido.PAGO;
    }

    public BigDecimal getValorTotal() { return valorTotal; }
    public Cliente getCliente() { return cliente; }

    public UUID getId() { return id; }
}

enum StatusPedido { CRIADO, PAGO, CANCELADO }