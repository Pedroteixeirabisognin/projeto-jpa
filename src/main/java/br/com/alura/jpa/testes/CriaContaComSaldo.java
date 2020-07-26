package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriaContaComSaldo {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        Conta conta = new Conta();
        conta.setTitular("M�rcia");
        conta.setNumero(12345);
        conta.setAgencia(54321);
        conta.setSaldo(100.0);

        em.getTransaction().begin();

        em.persist(conta);

        em.getTransaction().commit();
        //Conta est� no estado detached
        em.close();

        //� necess�rio criar novamente uma instancia de EntityManager
        EntityManager em2 = emf.createEntityManager();
        System.out.println("ID da Conta da M�rcia:" + conta.getId());
        conta.setSaldo(500.0);

        em2.getTransaction().begin();
        //Tira conta do estado detached e a colocar no estado managed novamente
        em2.merge(conta);

        em2.getTransaction().commit();
    }
}
