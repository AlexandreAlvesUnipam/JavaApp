package com.ecommerce.ecommerce.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.modelo.Cliente;
import com.ecommerce.ecommerce.modelo.Pagamento;
import com.ecommerce.ecommerce.modelo.PagamentoPix;
import com.ecommerce.ecommerce.modelo.Pedido;
import com.ecommerce.ecommerce.repositorio.ClienteRepository;
import com.ecommerce.ecommerce.repositorio.PedidoRepository;
import com.ecommerce.ecommerce.servico.CheckoutMarketplaceService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final CheckoutMarketplaceService checkoutService;
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoController(CheckoutMarketplaceService checkoutService, 
                            ClienteRepository clienteRepository, 
                            PedidoRepository pedidoRepository) {
        this.checkoutService = checkoutService;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    // Endpoint para simular o cenário de ponta a ponta rapidamente
    @PostMapping("/simular-compra")
    public ResponseEntity<String> simularCompraCompleta() {
        try {
            // 1. Criamos um cliente com 500 reais de limite e salvamos
            Cliente joao = new Cliente("João", new BigDecimal("500.00"));
            clienteRepository.save(joao);

            // 2. Criamos o pedido e usamos a POO para adicionar itens
            Pedido novoPedido = new Pedido(joao);
            novoPedido.adicionarItem("Liquidificador", new BigDecimal("200.00"), 2); // Subtotal: 200
            novoPedido.adicionarItem("Livro: Introdução ao Java", new BigDecimal("50.00"), 1);       // Subtotal: 50
            // Valor total calculado pela classe Pedido = 250.00
            
            pedidoRepository.save(novoPedido);

            // 3. Escolhemos a estratégia de pagamento via POO (Polimorfismo)
            // Pagamento PIX vai dar 5% de desconto. O total final cobrado será 237.50.
            Pagamento formaDePagamento = new PagamentoPix(novoPedido);

            // 4. O Service orquestra a transação e debita do limite do João
            checkoutService.processarCompra(novoPedido, formaDePagamento);

            return ResponseEntity.ok("Compra realizada! ID do Pedido: " + novoPedido.getId());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }
}