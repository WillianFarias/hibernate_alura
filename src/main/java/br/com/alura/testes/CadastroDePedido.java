package br.com.alura.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.modelo.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {
    public static void main(String[] args) {

        popularBD();

        EntityManager manager = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(manager);
        Produto produto = produtoDao.buscarPorId(1L);

        ClienteDao clienteDao = new ClienteDao(manager);
        Cliente cliente = clienteDao.buscarPorId(1L);

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        PedidoDao pedidoDao = new PedidoDao(manager);

        manager.getTransaction().begin();
        pedidoDao.cadastrar(pedido);
        manager.getTransaction().commit();

        BigDecimal valorTotal = pedido.getValorTotal();
        System.out.println("Valor total do pedido: " + valorTotal);

    }

    private static void popularBD() {
        Categoria celulares = new Categoria("Celulares");

        Produto iphone = new Produto("Iphone", "Iphone XR", new BigDecimal("3000"), celulares);
        Produto xiaomi = new Produto("Xiaomi", "Xiaomi", new BigDecimal("500"), celulares);

        Cliente cliente = new Cliente("Willian", "000000");

        EntityManager manager = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(manager);
        CategoriaDao categoriaDao = new CategoriaDao(manager);
        ClienteDao clienteDao = new ClienteDao(manager);

        manager.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(iphone);
        produtoDao.cadastrar(xiaomi);
        clienteDao.cadastrar(cliente);
        manager.getTransaction().commit();
        manager.close();
    }
}
