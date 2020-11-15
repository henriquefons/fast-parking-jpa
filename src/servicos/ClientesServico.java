/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.dto.ClienteDAO;
import dados.entidades.Clientes;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class ClientesServico {
    
    private ClienteDAO dao = new ClienteDAO();
    
    public void salvar(Clientes c){
        dao.salvar(c);
    }
    
    public void excluir(Clientes c){
        dao.excluir(c);
    }
    
    public void editar(Clientes c){
        dao.editar(c);
    }
    
    public List<Clientes> listar(){
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    public List<Clientes> listarPlacas(){
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listarPlacas();
        
    }
    
}
