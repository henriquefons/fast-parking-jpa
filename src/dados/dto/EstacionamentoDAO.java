/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.dto;

import dados.entidades.Estacionamento;
import javax.persistence.EntityManager;
import util.JPAUtil;

/**
 *
 * @author Henrique
 */
public class EstacionamentoDAO {
    
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
