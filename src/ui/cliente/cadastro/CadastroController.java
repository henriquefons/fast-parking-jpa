/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cliente.cadastro;

import dados.entidades.Clientes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import servicos.ClientesServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class CadastroController implements Initializable {

    @FXML
    private TextField txf_nome;
    @FXML
    private TextField txf_cpf;
    @FXML
    private TextField txf_placa;
    @FXML
    private ComboBox<String> cb_tipoVeiculo;
    
    
   // Variaveis criadas
    ClientesServico cliente_servico = new ClientesServico();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb_tipoVeiculo.getItems().removeAll(cb_tipoVeiculo.getItems());
        cb_tipoVeiculo.getItems().setAll("Moto", "Carro");
    }    

    @FXML
    private void btn_cancelar(ActionEvent event) {
    }

    @FXML
    private void btn_salvar(ActionEvent event) {
        
        Clientes c = new Clientes(txf_nome.getText(), txf_placa.getText(), txf_cpf.getText(), 
                cb_tipoVeiculo.getValue(), false);
        
        cliente_servico.salvar(c);
        
        AlertaUtil.mensagemSucesso("Cliente salvo com sucesso!");
        
        limpar_campos();
        
    }
    
    private void limpar_campos(){
        txf_cpf.setText("");
        txf_placa.setText("");
        txf_nome.setText("");
        cb_tipoVeiculo.getItems().removeAll(cb_tipoVeiculo.getItems());
        cb_tipoVeiculo.getItems().setAll("Moto", "Carro");
    }
    
}
