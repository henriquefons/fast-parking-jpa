/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import dados.daos.VagaDAO;
import dados.entidades.Clientes;
import dados.entidades.Estacionamento;
import dados.entidades.Vagas;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import servicos.ClientesServico;
import servicos.EstacionamentoServico;
import servicos.VagasServico;

/**
 *
 * @author Henrique
 */
public class OutrosTestes {
    
    public static void main(String[] args) {
        
        EstacionamentoServico est = new EstacionamentoServico();
        
        System.out.println(est.procurarEstacionamentoPorId(2));
        
        
        /*
        VagaDAO vagas = new VagaDAO();
        
        
        List<Vagas> vlist = vagas.buscarPeloCliente(1);
        
        Vagas v = vlist.get(0);
        
        LocalDateTime inicio = LocalDateTime.of(2020, 5, 5, 10, 0, 0);
        LocalDateTime fim = LocalDateTime.of(2020, 5, 5, 11, 30, 0);
        
        
        BigDecimal x = est.procurarEstacionamentoPorId(1).getPrice_hours();
        
        System.out.println(x);
        System.out.println(ChronoUnit.HOURS.between(inicio, fim));
        
        
        long minutos = ChronoUnit.MINUTES.between(inicio, fim);
        System.out.println(minutos);
        BigDecimal d1 = new BigDecimal(minutos);
        
        System.out.println(new BigDecimal(minutos).divide(BigDecimal.valueOf(60), 2, RoundingMode.UP));
        
        System.out.println(d1);
*/
        
    }
    
}
