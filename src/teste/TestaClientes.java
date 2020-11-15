/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import dados.entidades.Clientes;
import servicos.ClientesServico;

/**
 *
 * @author Henrique
 */
public class TestaClientes {
    
    public static void main(String[] args) {
        Clientes c1 = new Clientes("Joao", "AKA5865", "147.456.852-40", "Carro", true);
        Clientes c2 = new Clientes("Maria", "LOR7145", "312.322.421-36", "Carro", true);
        Clientes c3 = new Clientes("Jose", "CVQ4563", "752.312.266-88", "Moto", true);
        
        ClientesServico c = new ClientesServico();
        
        c.salvar(c1);
        c.salvar(c2);
        c.salvar(c3);
    }
    
}
