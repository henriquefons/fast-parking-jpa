/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.dto;

import dados.entidades.Vagas;
import javax.persistence.EntityManager;
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
    
}
