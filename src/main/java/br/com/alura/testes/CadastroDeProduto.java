package br.com.alura.testes;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProduto {
    public static void main(String[] args) {

        Produto celular = new Produto("Iphone", "Iphone XR", new BigDecimal("3000"), Categoria.CELULARES);

        EntityManager manager = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(manager);

        manager.getTransaction().begin();
        produtoDao.cadastrar(celular);
        manager.getTransaction().commit();
        manager.close();
    }
}
