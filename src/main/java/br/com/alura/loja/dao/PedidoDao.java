package br.com.alura.loja.dao;

import br.com.alura.loja.vo.RelatorioDeVendasVo;
import br.com.alura.modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

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

    public List<RelatorioDeVendasVo> relatorioDeVendas() {
        return manager.createQuery("SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo(produto.nome, SUM(item.quantidade), MAX(pedido.data)) " +
                        "FROM Pedido pedido" +
                        "JOIN pedido.itens item" +
                        "JOIN item.produto produto" +
                        "GROUP BY produto.nome" +
                        "ORDER BY item.quantidade DESC", RelatorioDeVendasVo.class)
                .getResultList();
    }
}
