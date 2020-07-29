package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Movimentacao;

public class TesteJPQLMovimentacaoDeUmaCategoria {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();


        String sql = "select m from Movimentacao m join m.categorias c  where c = :pCategoria";

        //Criamos um objeto Categoria e setamos o seu id como 1L.
        Categoria categoria = new Categoria();
        categoria.setId(1L);

        //TypedQuery puxa do banco a tabela Movimentacao para a memória
        //e coloca o nome de m.
        //TypedQuery puxa do banco o join entre Movimentacao e Categoria
        //TypedQuery espera ser comparado com um objeto Categoria
        TypedQuery<Movimentacao> query = em.createQuery(sql, Movimentacao.class);
        
        //Inserimos ele dentro da TypedQuery no lugar de :pCategoria
        query.setParameter("pCategoria", categoria);

        //Usamos List<Movimentacao> movimentacoes = query.getResultList() para retornar os resultados.
        List<Movimentacao> movimentacoes = query.getResultList();
        for (Movimentacao movimentacao : movimentacoes) {
            System.out.println("Descrição: " + movimentacao.getDescricao());
            System.out.println("Valor: " + movimentacao.getValor());
            System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
        }
    }
}
