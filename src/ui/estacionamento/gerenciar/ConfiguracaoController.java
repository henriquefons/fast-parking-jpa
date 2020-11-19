/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.estacionamento.gerenciar;

import dados.entidades.Estacionamento;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import servicos.EstacionamentoServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class ConfiguracaoController implements Initializable {
    
    //Variaveis criadas
    EstacionamentoServico  estacionamento_servico = new EstacionamentoServico();

    @FXML
    private TextField txf_nome_estacionamento;
    @FXML
    private TextField txt_preco_hora;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txf_nome_estacionamento.setText(estacionamento_servico.procurarEstacionamentoPorId(1).getNome());
        txt_preco_hora.setText(estacionamento_servico.procurarEstacionamentoPorId(1).getPrice_hours().toString());
    }    
    
    //Alterar ou criar valores do estacionamento
    
    private void bloquearCampos(Boolean b){
        txf_nome_estacionamento.setDisable(b);
        txt_preco_hora.setDisable(b);
    }

    @FXML
    private void sair(ActionEvent event) {
    }

    @FXML
    private void editarValores(ActionEvent event) {
        bloquearCampos(false);
    }

    @FXML
    private void salvarValores(ActionEvent event) {
        
        Estacionamento e = new Estacionamento(txf_nome_estacionamento.getText(), 
                BigDecimal.valueOf(Double.parseDouble(txt_preco_hora.getText())));
        e.setId(1);
        
        estacionamento_servico.salvarOuAtualizar(e);
        
        AlertaUtil.mensagemSucesso("Valores salvo com sucesso!");
        bloquearCampos(false);
    }
    
}
