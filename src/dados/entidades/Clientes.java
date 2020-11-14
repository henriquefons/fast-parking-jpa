/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.entidades;

import com.sun.istack.NotNull;
import java.math.BigDecimal;
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
public class Clientes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String placa;
    private String cpf;
    private String tipo_veiculo;
    private boolean ocupacao;
    
    public Clientes(){}

    public Clientes(Integer id, String nome, String placa, String cpf, String tipo_veiculo, boolean ocupacao) {
        this.setId(id);
        this.setNome(nome);
        this.setPlaca(placa);
        this.setCpf(cpf);
        this.setTipo_veiculo(tipo_veiculo);
        this.setOcupacao(ocupacao);
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipo_veiculo() {
        return tipo_veiculo;
    }

    public void setTipo_veiculo(String tipo_veiculo) {
        this.tipo_veiculo = tipo_veiculo;
    }

    public boolean isOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(boolean ocupacao) {
        this.ocupacao = ocupacao;
    }
    
}
