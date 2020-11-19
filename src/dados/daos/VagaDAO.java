/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Vagas;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author Henrique
 */
public class VagaDAO {
    
    public void salvar(Vagas v){
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o ator
        gerenciador.persist(v);

        //Commit
        gerenciador.getTransaction().commit();
        
        gerenciador.close();
    }
    
    public void editar(Vagas v){
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(v);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
    }
    
    public List<Vagas> buscarPeloCliente(Integer id){
       
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery<Vagas> consulta = gerenciador.createQuery(
                "Select v from Vagas v WHERE saida is null and clientes_id = :id", Vagas.class);

        //Substituindo o parametro :nome pelo valor da variavel n
        consulta.setParameter("id", id + "%");

        //Retornar os dados
        return consulta.getResultList();
    }
    
    public BigDecimal valorFinal (LocalDateTime entrada, LocalDateTime saida, BigDecimal precoHora){
        long minutos = ChronoUnit.MINUTES.between(entrada, saida);
        return null; //BigDecimal.valueOf(BigDecimal(minutos).divide().*precoHora);
    }
    
}
