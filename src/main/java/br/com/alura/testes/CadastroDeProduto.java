package br.com.alura.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(manager);

        Produto produto = produtoDao.buscarPorId(1L);
        System.out.println(produto.getNome());

        List<Produto> produtos = produtoDao.buscarPorNomeDaCategoria("Celulares");
        produtos.forEach(p -> System.out.println(p.getNome()));
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("Celulares");

        Produto iphone = new Produto("Iphone", "Iphone XR", new BigDecimal("3000"), celulares);
        Produto xiaomi = new Produto("Xiaomi", "Xiaomi", new BigDecimal("500"), celulares);

        EntityManager manager = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(manager);
        CategoriaDao categoriaDao = new CategoriaDao(manager);

        manager.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(iphone);
        produtoDao.cadastrar(xiaomi);

        manager.getTransaction().commit();
        manager.close();
    }
}
