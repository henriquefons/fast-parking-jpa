/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Clientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author Henrique
 */
public class ClienteDAO {
    
    /**
     * Salvar o ator no BD
     * @param c
     */
    public void salvar(Clientes c){
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o ator
        gerenciador.persist(c);

        //Commit
        gerenciador.getTransaction().commit();
    }
    
    public void editar(Clientes c){
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(c);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
    }
    
    public void excluir(Clientes c){
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o ator do BD com o ator que foi
        //selecionado na tela
        c = gerenciador.merge(c);

        //Mandar sincronizar as alterações 
        gerenciador.remove(c);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
    }
    
    public List<Clientes> listar(){
        
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery("Select c from Clientes c", Clientes.class);

        //Retornar a lista de atores
        return consulta.getResultList();
        
    }
    
    public List<Clientes> listarPlacasDesocupadas(){
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery("SELECT c FROM Clientes c WHERE ocupacao=0", Clientes.class);

        //Retornar a lista de atores
        return consulta.getResultList();
    }
    
    public List<Clientes> listarPlacasOcupadas(){
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery("SELECT c FROM Clientes c WHERE ocupacao=1", Clientes.class);

        //Retornar a lista de atores
        return consulta.getResultList();
    }
    
}
