/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import javax.persistence.EntityManager;
import util.JPAUtil;

/**
 *
 * @author Henrique
 */
public class main_create_bd {
    
    public static void main(String[] args) {
        
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
    }
    
}
