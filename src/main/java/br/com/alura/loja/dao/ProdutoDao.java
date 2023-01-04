package br.com.alura.loja.dao;

import br.com.alura.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {

    private final EntityManager manager;

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
        return this.manager.createQuery("SELECT p FROM Produto p WHERE p.categoria.id.nome = :nome")
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoPorNome(String nome) {
        return this.manager.createQuery("SELECT p.preco FROM Produto p WHERE p.nome = :nome", BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
        String jpql =  "SELECT p FROM Produto WHERE 1=1";

        if(nome != null && !nome.trim().isEmpty()) {
            jpql += "AND p.nome = :nome";
        }

        if(preco != null) {
            jpql += "AND p.preco = :preco";
        }

        if(dataCadastro != null) {
            jpql += "AND p.dataCadastro = :dataCadastro";
        }

        TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);

        if(nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }

        if(preco != null) {
            query.setParameter("preco", preco);
        }

        if(dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);
        }

        return query.getResultList();
    }

    public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = builder.and();

        if(nome != null && !nome.trim().isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }

        if(preco != null) {
            filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
        }

        if(dataCadastro != null) {
            filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
        }

        query.where(filtros);

        return manager.createQuery(query).getResultList();
    }

}
