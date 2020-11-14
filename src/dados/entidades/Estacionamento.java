/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.entidades;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Henrique
 */
@Entity
public class Estacionamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal price_hours;
    
    //Construtor vazio da JPA (OBRIGATÓRIO)
    public Estacionamento(){}
    
    public Estacionamento(Integer id, String nome, BigDecimal price_hours) {
        this.setId(id);
        this.setNome(nome);
        this.setPrice_hours(price_hours);
    }

    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrice_hours() {
        return price_hours;
    }

    public void setPrice_hours(BigDecimal price_hours) {
        this.price_hours = price_hours;
    }
    
}
