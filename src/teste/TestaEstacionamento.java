/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import dados.entidades.Estacionamento;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import util.JPAUtil;


/**
 *
 * @author Henrique
 */
public class TestaEstacionamento {
    
    public static void main(String[] args) {
        
        Estacionamento e = new Estacionamento();
      
        
        e.setNome("Fast Parking");
        e.setPrice_hours(BigDecimal.valueOf(25));
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        gerenciador.persist(e);
        
        //Finalizo a transação
        gerenciador.getTransaction().commit();
        
        //Fechar o gerenciador
        gerenciador.close();
          
    }
    
}
