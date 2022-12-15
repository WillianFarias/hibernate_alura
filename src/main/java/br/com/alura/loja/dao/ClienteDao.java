package br.com.alura.loja.dao;

import br.com.alura.modelo.Cliente;

import javax.persistence.EntityManager;

public class ClienteDao {

    private EntityManager manager;

    public ClienteDao(EntityManager manager) {
        this.manager = manager;
    }

    public void cadastrar(Cliente cliente) {
        this.manager.persist(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return this.manager.find(Cliente.class, id);
    }
}
