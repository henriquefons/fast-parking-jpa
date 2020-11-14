/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cliente.listar;

import dados.entidades.Clientes;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servicos.ClientesServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class ListarController implements Initializable {

    @FXML
    private TableView<Clientes> tbl_clientes;
    @FXML
    private TableColumn col_id;
    @FXML
    private TableColumn col_nome;
    @FXML
    private TableColumn col_placa;
    @FXML
    private TableColumn col_cpf;
    @FXML
    private TableColumn col_veiculo;

    // Variaveis criadas
    private ClientesServico cliente_servico = new ClientesServico();
    private ObservableList<Clientes> dados = FXCollections.observableArrayList();
    private Clientes cliente_selecionado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabela();
        listarClientesTabela();
    }

    private void configurarTabela() {
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_cpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        col_nome.setCellValueFactory(new PropertyValueFactory("nome"));
        col_placa.setCellValueFactory(new PropertyValueFactory("placa"));
        col_veiculo.setCellValueFactory(new PropertyValueFactory("tipo_veiculo"));
    }

    private void listarClientesTabela() {
        dados.clear();

        List<Clientes> clientes = cliente_servico.listar();

        dados = FXCollections.observableArrayList(clientes);

        tbl_clientes.setItems(dados);
    }

    @FXML
    private void btn_editar(ActionEvent event) {
    }

    @FXML
    private void btn_excluir(ActionEvent event) {

        cliente_selecionado = tbl_clientes.getSelectionModel().getSelectedItem();

        if (cliente_selecionado != null) {

            Optional<ButtonType> btn
                    = AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir?",
                            "EXCLUIR");

            //Verificando se apertou o OK
            if (btn.get() == ButtonType.OK) {
                cliente_servico.excluir(cliente_selecionado);
                //mostrar mensagem de sucesso
                AlertaUtil.mensagemSucesso("Ator excluído com sucesso");
                
                //Atualizar a tabela
                listarClientesTabela();
            }

        } else {
            AlertaUtil.mensagemErro("Selecione um Cliente");
        }
    }

    @FXML
    private void btn_sair(ActionEvent event) {
    }

}
