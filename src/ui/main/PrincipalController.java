/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.main;

import dados.entidades.Clientes;
import dados.entidades.Estacionamento;
import dados.entidades.Vagas;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import servicos.ClientesServico;
import servicos.EstacionamentoServico;
import ui.cliente.cadastro.CadastroController;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class PrincipalController implements Initializable {

    @FXML
    private Text txt_nome;
    @FXML
    private Text txt_cpf;
    @FXML
    private Text txt_placa;
    @FXML
    private ComboBox<Clientes> cb_placaDigitada;
    @FXML
    private Button btn_cancelar_id;
    @FXML
    private Button btn_salvar_id;
    @FXML
    private TextField txf_hora_entrada;

    // Variaveis criadas
    private ClientesServico cliente_servico = new ClientesServico();
    private EstacionamentoServico estacionamento_servico = new EstacionamentoServico();
    private Estacionamento estacionamento_aux = null;
    private Clientes cliente_aux = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listarPlacas();
    }

    private void listarPlacas() {
        List<Clientes> cliente = cliente_servico.listar();
        cb_placaDigitada.setItems(FXCollections.observableArrayList(cliente));
    }
    
    @FXML
    private void prucurarPlaca(ActionEvent event) throws IOException {
        List<Clientes> cliente = cliente_servico.listar();
        Clientes c = new Clientes();
        //Verificar se existe a placa
        int cont = 0;
        for (Clientes clientes : cliente) {
            if (clientes.getPlaca().equals(cb_placaDigitada.getValue())) {
                cont++;
                c = clientes;
            }
        }
        //Verifica se tem ou nao a placa, se não tiver abre msg para cadastrar
        if (cont > 0) {
            Optional<ButtonType> btn
                    = AlertaUtil.mensagemDeConfirmacao("Cliente cadastrado", "MENSAGEM");
            mostrarCampos();
            txt_nome.setText(c.getNome());
            txt_cpf.setText(c.getCpf());
            txt_placa.setText(c.getPlaca());
            txf_hora_entrada.setText(LocalDateTime.now().toString());
            
            cliente_aux = c;
        } else {
            Optional<ButtonType> btn
                    = AlertaUtil.mensagemDeConfirmacao("Cliente não cadastrado, deseja cadastrar?", "MENSAGEM");
            
            // Se clicar em OK, chama minha tela de cadastro e passa o valor da placa digitada
            if (btn.get() == ButtonType.OK) {

                esconderCampos();

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/ui/cliente/cadastro/cadastro.fxml"));
                Parent root = fxmlloader.load();

                CadastroController cadastroController = fxmlloader.getController();

                cadastroController.recebePlaca(cb_placaDigitada.getEditor().textProperty().getValue());

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Cadastrar Cliente");

                //Evento para atualizar a tabela quando sair do cadastro e atualizar a lista do combobox
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        listarPlacas();
                    }
                });

                //Mostrando a nova janela
                stage.show();

            } else { // se não, limpa os dados e bloquea as ações
                esconderCampos();
            }
        }
    }

    private void mostrarCampos() {
        txt_nome.setVisible(true);
        txt_cpf.setVisible(true);
        txt_placa.setVisible(true);
        btn_cancelar_id.setDisable(false);
        btn_salvar_id.setDisable(false);
    }

    private void esconderCampos() {
        txt_nome.setVisible(false);
        txt_cpf.setVisible(false);
        txt_placa.setVisible(false);
        btn_cancelar_id.setDisable(true);
        btn_salvar_id.setDisable(true);
    }
    
    @FXML
    private void cancelarEntrada(ActionEvent event) {
    }

    @FXML
    private void salvarEntrada(ActionEvent event) {
        Vagas v = new Vagas(LocalDateTime.now(), null, null, estacionamento_aux, cliente_aux);
    }

    @FXML
    private void menu_listarClientes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/cliente/listar/listar.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage();
        //Titulo na janela
        stage.setTitle("Listar clientes");
        //Adicionando a cena na janela
        stage.setScene(scene);

        //Para impedir que a janela seja redimensionada
        //isso faz a janela ficar igual como está no 
        //Secene Builder
        stage.setResizable(false);

        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);

        //Mostrando a nova janela
        stage.show();
    }
    
    @FXML
    private void menu_cadastrarCliente(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/cliente/cadastro/cadastro.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage();
        //Titulo na janela
        stage.setTitle("Cadastro de Clientes");
        //Adicionando a cena na janela
        stage.setScene(scene);

        //Para impedir que a janela seja redimensionada
        //isso faz a janela ficar igual como está no 
        //Secene Builder
        stage.setResizable(false);

        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);

        //Evento para atualizar a tabela quando sair do edit // identifica quando a janela "filho" for fechada
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                listarPlacas();
            }
        });

        //Mostrando a nova janela
        stage.show();
    }
    
}
