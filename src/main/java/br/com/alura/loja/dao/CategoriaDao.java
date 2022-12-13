package br.com.alura.loja.dao;

import br.com.alura.modelo.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {

    private EntityManager manager;

    public CategoriaDao(EntityManager manager) {
        this.manager = manager;
    }

    public void cadastrar(Categoria categoria) {
        this.manager.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.manager.merge(categoria);
    }

    public void remover(Categoria categoria) {
        categoria = this.manager.merge(categoria);
        this.manager.remove(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return this.manager.find(Categoria.class, id);
    }

    public List<Categoria> buscarTodos() {
        return this.manager.createQuery("SELECT c FROM Categoria c").getResultList();
    }

    public List<Categoria> buscarPorNome(String nome) {
        return this.manager.createQuery("SELECT c FROM Categoria c WHERE c.nome = ?1")
                .setParameter(1, nome)
                .getResultList();
    }
}
