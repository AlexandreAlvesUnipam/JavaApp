package com.ecommerce.ecommerce.servico;

import com.ecommerce.ecommerce.modelo.*;
import com.ecommerce.ecommerce.repositorio.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class CheckoutMarketplaceService {

    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ClienteRepository clienteRepository;

    public CheckoutMarketplaceService(PedidoRepository pedidoRepository, 
                                      PagamentoRepository pagamentoRepository,
                                      ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional // Garante que tudo seja salvo junto (ou desfeito em caso de erro)
    public void processarCompra(Pedido pedido, Pagamento formaDePagamento) {
        
        // Polimorfismo
        BigDecimal valorFinal = formaDePagamento.calcularValorFinal();
        Cliente comprador = pedido.getCliente();

        // Encapsulamento: tentamos debitar. 
        // Se não houver limite, a própria classe Cliente lança o erro.
        comprador.debitarLimite(valorFinal);

        // O próprio Pedido altera seu status através de um método com significado (Domain-driven)
        pedido.confirmarPagamento();

        // Persistência
        clienteRepository.save(comprador);
        pedidoRepository.save(pedido);
        pagamentoRepository.save(formaDePagamento);
    }
}