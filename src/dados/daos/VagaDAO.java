/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Vagas;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

        //Substituindo o parametro :id pelo valor da variavel id
        consulta.setParameter("id", id + "%");

        //Retornar os dados
        return consulta.getResultList();
    }
    
    public BigDecimal valorFinal (LocalDateTime entrada, LocalDateTime saida, BigDecimal precoHora){
        // Faz o calculo da diferenca de minutos dos horarios
        long minutos = ChronoUnit.MINUTES.between(entrada, saida);
        
        //Converter os minutos para realizar a multiplicação do precoHora
        BigDecimal minutos_convertidos = new BigDecimal(minutos).divide(BigDecimal.valueOf(60), 2, RoundingMode.UP);
        
        // Retorna o valor final
        return precoHora.multiply(minutos_convertidos);
        
    }
    
    public List<Vagas> listarVagasSaidas(){
       
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery<Vagas> consulta = gerenciador.createQuery("SELECT v.id, c.placa, c.nome, v.entrada, v.saida, v.valor_final "
                + "FROM Vagas AS v INNER JOIN Clientes as c ON v.clientes_id = c.id "
                + "WHERE saida is not null", Vagas.class);

        //Retornar os dados
        return consulta.getResultList();
    }
    
}
