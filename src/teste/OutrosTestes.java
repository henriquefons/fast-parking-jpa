/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import dados.entidades.Clientes;
import dados.entidades.Estacionamento;
import dados.entidades.Vagas;
import servicos.ClientesServico;
import servicos.EstacionamentoServico;
import servicos.VagasServico;

/**
 *
 * @author Henrique
 */
public class OutrosTestes {
    
    public static void main(String[] args) {
        
        EstacionamentoServico e_servico = new EstacionamentoServico();
        
        Estacionamento e = new Estacionamento();
        
        e = e_servico.procurarEstacionamentoPorId(1);
        
        System.out.println(e.getNome());
        
        
        
    }
    
}
