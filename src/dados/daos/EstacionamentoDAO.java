/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Estacionamento;
import javax.persistence.EntityManager;
import util.JPAUtil;

/**
 *
 * @author Henrique
 */
public class EstacionamentoDAO {
    
    public void salvarOuAtualizar(Estacionamento est){
        
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        try {
         //Iniciar a transação
        gerenciador.getTransaction().begin();
        
            if (procurarEstacionamentoPorId(1) != null) {
                //criar o estacionamento
                gerenciador.persist(est);
            } else {
                //atualizar o estacionamento
                gerenciador.merge(est);
            }
        } catch (Exception e) {
            gerenciador.getTransaction().rollback();
        } finally {
            gerenciador.getTransaction().commit();
            gerenciador.close();
        }
    }
    
    public Estacionamento procurarEstacionamentoPorId(Integer id){
        
        Estacionamento estacionamento = null;
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        try {
            estacionamento = gerenciador.find(Estacionamento.class, id);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            gerenciador.close();
        }
        
        return estacionamento;
        
    }
    
}
