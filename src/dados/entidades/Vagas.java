/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Henrique
 */
@Entity
public class Vagas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime saida;
    
    @Column(nullable = false)
    private LocalDateTime entrada;
    
    
    private BigDecimal valor_final;
    
    @ManyToOne(optional=false)
    private Estacionamento estacionamento;
    @ManyToOne(optional=false)
    private Clientes clientes;
    
    public Vagas(){}

    public Vagas(LocalDateTime entrada, LocalDateTime saida, BigDecimal valor_final, Estacionamento estacionamento, Clientes clientes) {
        this.setEntrada(entrada);
        this.setSaida(saida);
        this.setValor_final(valor_final);
        this.setEstacionamento(estacionamento);
        this.setClientes(clientes);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public BigDecimal getValor_final() {
        return valor_final;
    }

    public void setValor_final(BigDecimal valor_final) {
        this.valor_final = valor_final;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    
    
     
}
