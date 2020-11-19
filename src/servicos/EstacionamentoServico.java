/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.EstacionamentoDAO;
import dados.entidades.Estacionamento;

/**
 *
 * @author Henrique
 */
public class EstacionamentoServico {
    
    EstacionamentoDAO dao = new EstacionamentoDAO();
    
    public Estacionamento procurarEstacionamentoPorId(Integer id){
        return dao.procurarEstacionamentoPorId(id);
    }
    
    public void salvarOuAtualizar(Estacionamento est){
        dao.salvarOuAtualizar(est);
    }
    
}
