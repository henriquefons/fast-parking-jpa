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
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
import servicos.VagasServico;
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
    private ComboBox<Clientes> cb_placaSaida;
    @FXML
    private Text txt_nomeSaida;
    @FXML
    private Text txt_cpfSaida;
    @FXML
    private Text txt_placaSaida;
    @FXML
    private Button btn_cancelar_saida;
    @FXML
    private Button btn_salvar_saida;
    @FXML
    private Text txt_hora_saida;
    @FXML
    private Text txf_hora_entrada;

    // Variaveis criadas
    private ClientesServico cliente_servico = new ClientesServico();
    private EstacionamentoServico estacionamento_servico = new EstacionamentoServico();
    private VagasServico vagas_servico = new VagasServico();
    private Estacionamento estacionamento_aux = null;
    private Clientes cliente_aux = null;
    //private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss");
    private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM);
    private LocalDateTime horario_entrada = null;
    private LocalDateTime horario_saida = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listarPlacas();
        listarPlacasSaida();
    }

    private void listarPlacas() {
        List<Clientes> cliente = cliente_servico.listarPlacasDesocupadas();
        cb_placaDigitada.setItems(FXCollections.observableArrayList(cliente));
    }

    private void listarPlacasSaida() {
        List<Clientes> cliente = cliente_servico.listarPlacasOcupadas();
        cb_placaSaida.setItems(FXCollections.observableArrayList(cliente));
    }

    @FXML
    private void prucurarPlaca(ActionEvent event) throws IOException {
        List<Clientes> cliente = cliente_servico.listar();
        Clientes c = null;
        //Verificar se existe a placa para pegar o objeto cliente.
        for (Clientes clientes : cliente) {
            if (clientes.getPlaca().equals(cb_placaDigitada.getValue())) {
                c = clientes;
            }
        }
        //Verifica se tem ou nao a placa, se não tiver abre msg para cadastrar
        if (c != null) {
            Optional<ButtonType> btn
                    = AlertaUtil.mensagemDeConfirmacao("Seja bem-vindo " + c.getNome(), "MENSAGEM");
            mostrarCampos();
            esconderCamposSaida();
            txt_nome.setText(c.getNome());
            txt_cpf.setText(c.getCpf());
            txt_placa.setText(c.getPlaca());

            //Guardando o horario de entrada
            horario_entrada = LocalDateTime.now();
            txf_hora_entrada.setText(horario_entrada.format(formatter));

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
        txf_hora_entrada.setVisible(true);
        btn_cancelar_id.setDisable(false);
        btn_salvar_id.setDisable(false);
    }

    private void mostrarCamposSaida() {
        txt_nomeSaida.setVisible(true);
        txt_cpfSaida.setVisible(true);
        txt_placaSaida.setVisible(true);
        txt_hora_saida.setVisible(true);
        btn_cancelar_saida.setDisable(false);
        btn_salvar_saida.setDisable(false);
    }

    private void esconderCampos() {
        txt_nome.setVisible(false);
        txt_cpf.setVisible(false);
        txt_placa.setVisible(false);
        txf_hora_entrada.setVisible(false);
        btn_cancelar_id.setDisable(true);
        btn_salvar_id.setDisable(true);
    }

    private void esconderCamposSaida() {
        txt_nomeSaida.setVisible(false);
        txt_cpfSaida.setVisible(false);
        txt_placaSaida.setVisible(false);
        txt_hora_saida.setVisible(false);
        btn_cancelar_saida.setDisable(true);
        btn_salvar_saida.setDisable(true);
    }

    @FXML
    private void cancelarEntrada(ActionEvent event) {
    }

    @FXML
    private void salvarEntrada(ActionEvent event) {

        Optional<ButtonType> btn
                = AlertaUtil.mensagemDeConfirmacao("Confirmar entrada do cliente?", "MENSAGEM");
        estacionamento_aux = estacionamento_servico.procurarEstacionamentoPorId(1);

        if (btn.get() == ButtonType.OK) {
            //Salvando a vaga
            Vagas v = new Vagas(horario_entrada, null, null, estacionamento_aux, cliente_aux);
            vagas_servico.salvar(v);

            //Atualizando a ocupacao do cliente
            cliente_aux.setOcupacao(true);
            cliente_servico.editar(cliente_aux);

            AlertaUtil.mensagemSucesso("Horario de entrada: " + horario_entrada.format(formatter));
            esconderCampos();

            //Setando o cliente
            cliente_aux = new Clientes();

            //atualizando combobox
            listarPlacas();
            listarPlacasSaida();

        } else {
            //Setando os valores globais
            cliente_aux = new Clientes();
            horario_entrada = null;

            AlertaUtil.mensagemErro("Cancelado");
            esconderCampos();
        }
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

    @FXML
    private void prucurarPlacaSaida(ActionEvent event) {

        Clientes c = cb_placaSaida.getValue();

        AlertaUtil.mensagemDeConfirmacao("Até mais, " + c.getNome() + "!", "MENSAGEM");
        txt_nomeSaida.setText(c.getNome());
        txt_cpfSaida.setText(c.getCpf());
        txt_placaSaida.setText(c.getPlaca());
        horario_saida = LocalDateTime.now();
        txt_hora_saida.setText(horario_saida.format(formatter));
        mostrarCamposSaida();
        esconderCampos();
    }

    @FXML
    private void cancelarsaida(ActionEvent event) {
    }

    @FXML
    private void salvarSaida(ActionEvent event) {
        Optional<ButtonType> btn
                = AlertaUtil.mensagemDeConfirmacao("Confirmar saida do cliente?", "MENSAGEM");
        
        if (btn.get() == ButtonType.OK) {
            //retorna uma lista com 1 objetivo vagas atraves da consulta passando id do cliente
            List<Vagas> vlist = vagas_servico.buscarPeloCliente(cb_placaSaida.getValue().getId());
            //Faço uma verificação para ver se tem somente 1 elemento msm
            if (vlist.size() == 1) {
                
                //Vou passar o id para minha vaga e atualizar o horario de saida
                //Como só tenho 1 elemento, posso usar o get na posicao do elemento (0) e pegar o objeto dele 
                Vagas v = vlist.get(0);
                v.setSaida(horario_saida);
                vagas_servico.editar(v);
                
                //Colocar o cliente como nao ocupado. Pego o objetivo-cliente do cambo box
                Clientes c = cb_placaSaida.getValue();
                c.setOcupacao(false);
                cliente_servico.editar(c);
                
                AlertaUtil.mensagemSucesso("Obrigado pela preferencia! Valor final: " + v.getValor_final());
                listarPlacas();
                listarPlacasSaida();
                esconderCamposSaida();
            }else{ 
                AlertaUtil.mensagemErro("Aconteceu um erro inesperado, tente novamento ou chame o suporte!");
                esconderCamposSaida();
                horario_saida = null;
            }
            
        } else{
            esconderCamposSaida();
        }
        //Calcular o preco
        // ....
        
    }

}
