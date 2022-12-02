package br.com.alura.testes;

import br.com.alura.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {
    public static void main(String[] args) {

        Produto celular = new Produto();
        celular.setNome("Iphone");
        celular.setDescricao("Iphone XR");
        celular.setPreco(new BigDecimal("3000"));

        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("loja");
        EntityManager manager = managerFactory.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(celular);
        manager.getTransaction().commit();
        manager.close();
    }
}
