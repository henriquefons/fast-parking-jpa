/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Henrique
 */
public class main_create_bd {
    
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        
        session.close();
        sf.close();
    }
    
}
