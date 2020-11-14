/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.entidades;

import java.math.BigDecimal;
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
    
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date saida;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date entrada;
    
    
    private BigDecimal valor_final;
    
    @ManyToOne(optional=false)
    private Estacionamento estacionamento;
    @ManyToOne(optional=false)
    private Clientes clientes;
    
    public Vagas(){}

    public Vagas(Integer id, Date entrada, Date saida, BigDecimal valor_final, Estacionamento estacionamento, Clientes clientes) {
        this.setId(id);
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

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
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
