/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.vagas.historico;

import dados.entidades.Vagas;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servicos.VagasServico;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class HistoricoController implements Initializable {
    
    //Variaveis criadas
    private VagasServico vagas_servicos = new VagasServico();
    private ObservableList<Vagas> dados = FXCollections.observableArrayList();

    @FXML
    private TableView<Vagas> table_historico;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_placa;
    @FXML
    private TableColumn<?, ?> col_nome;
    @FXML
    private TableColumn<?, ?> col_horario_entrada;
    @FXML
    private TableColumn<?, ?> col_horario_saida;
    @FXML
    private TableColumn<?, ?> col_valor_final;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabela();
        //listarDadosTabela();
    }    
    
    private void configurarTabela() {
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_nome.setCellValueFactory(new PropertyValueFactory("nome"));
        col_placa.setCellValueFactory(new PropertyValueFactory("placa"));
        col_horario_entrada.setCellValueFactory(new PropertyValueFactory("entrada"));
        col_horario_saida.setCellValueFactory(new PropertyValueFactory("saida"));
        col_valor_final.setCellValueFactory(new PropertyValueFactory("valor_final"));
    }
    
    private void listarDadosTabela(){
        
        dados.clear();
        
        List<Vagas> vagas = vagas_servicos.listarVagasSaidas();
        
        dados = FXCollections.observableArrayList(vagas);
        
        table_historico.setItems(dados);
        
    }
    
}
