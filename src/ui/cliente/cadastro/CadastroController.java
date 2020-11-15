/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cliente.cadastro;

import dados.entidades.Clientes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    private ClientesServico cliente_servico = new ClientesServico();

    private Clientes cliente_editar;
    @FXML
    private Button btn_sair_id;
    @FXML
    private Button btn_salvar_id;
    @FXML
    private Text txt_titulo;

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
        fecharJanela(btn_sair_id);
    }

    @FXML
    private void btn_salvar(ActionEvent event) {

        //Editar
        if (cliente_editar != null) {
            cliente_editar.setNome(txf_nome.getText());
            cliente_editar.setCpf(txf_cpf.getText());
            cliente_editar.setPlaca(txf_placa.getText());
            cliente_editar.setTipo_veiculo(cb_tipoVeiculo.getValue());

            cliente_servico.editar(cliente_editar);

            AlertaUtil.mensagemSucesso("Cliente atualizado com sucesso!");

            Stage stage = (Stage) btn_salvar_id.getScene().getWindow();
            //Quando fechado, atualizada a tabela na tea "pai"
            stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            stage.close();

        } else { //Adicionar
            Clientes c = new Clientes(txf_nome.getText(), txf_placa.getText(), txf_cpf.getText(),
                    cb_tipoVeiculo.getValue(), false);

            cliente_servico.salvar(c);

            AlertaUtil.mensagemSucesso("Cliente salvo com sucesso!");

            limpar_campos();
        }

    }

    private void limpar_campos() {
        txf_cpf.setText("");
        txf_placa.setText("");
        txf_nome.setText("");
        cb_tipoVeiculo.getItems().removeAll(cb_tipoVeiculo.getItems());
        cb_tipoVeiculo.getItems().setAll("Moto", "Carro");
    }

    public void receberCliente(Clientes cliente_editar) {
        this.cliente_editar = cliente_editar;
        txt_titulo.setText("Alteração de cliente");
        txf_nome.setText(String.valueOf(cliente_editar.getNome()));
        txf_cpf.setText(String.valueOf(cliente_editar.getCpf()));
        txf_placa.setText(String.valueOf(cliente_editar.getPlaca()));
        cb_tipoVeiculo.getSelectionModel().select(cliente_editar.getTipo_veiculo());
    }

    private void fecharJanela(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

}
