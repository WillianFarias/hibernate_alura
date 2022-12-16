package br.com.alura.loja.dao;

import br.com.alura.modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PedidoDao {

    private EntityManager manager;

    public PedidoDao(EntityManager manager) {
        this.manager = manager;
    }

    public void cadastrar(Pedido pedido) {
        this.manager.persist(pedido);
    }

    public BigDecimal valorTotalVendido() {
        return manager.createQuery("SELECT SUM(p.valorTotal) FROM Produto p", BigDecimal.class)
                .getSingleResult();
    }
}
