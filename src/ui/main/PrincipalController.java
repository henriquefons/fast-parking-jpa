/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class PrincipalController implements Initializable {

    @FXML
    private TextField txf_placaSaida;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        
        //Mostrando a nova janela
        stage.show();  
    }

    @FXML
    private void prucurarPlaca(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void menu_listarClientes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/cliente/listar/listar.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
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
    
}
