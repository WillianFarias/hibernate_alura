package br.com.alura.loja.dao;

import br.com.alura.modelo.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {

    private EntityManager manager;

    public ProdutoDao(EntityManager manager) {
        this.manager = manager;
    }

    public void cadastrar(Produto produto) {
        this.manager.persist(produto);
    }

    public void remover(Produto produto) {
        produto = this.manager.merge(produto);
        this.manager.remove(produto);
    }

    public void atualizar(Produto produto) {
        this.manager.merge(produto);
    }

    public Produto buscarPorId(Long id) {
        return this.manager.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        return this.manager.createQuery("SELECT p FROM Produto p").getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        return this.manager.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome")
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        return this.manager.createQuery("SELECT p FROM Produto p WHERE p.categoria.nome = :nome")
                .setParameter("nome", nome)
                .getResultList();
    }

}
