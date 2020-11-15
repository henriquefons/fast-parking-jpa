/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.dto.VagaDAO;
import dados.entidades.Vagas;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class VagasServico {
    
    private VagaDAO dao = new VagaDAO();
    
    public void salvar(Vagas v){
        dao.salvar(v);
    }
    
}
