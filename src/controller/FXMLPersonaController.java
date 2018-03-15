/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Person;
import model.Residencia;

/**
 * FXML Controller class
 *
 * @author Martín Romero
 */
public class FXMLPersonaController implements Initializable {
    private boolean okAccion = false;
    private Person persona;
    private String accion;
    private Stage modalStage;
    private ObservableList<String> imagenes;
    @FXML private GridPane panelGrid;
    @FXML private TextField id;
    @FXML private TextField nombre;
    @FXML private TextField ciudad;
    @FXML private TextField provincia;
    @FXML private ComboBox imagen;
    @FXML private Label idMsgError;
    @FXML private Label nombreMsgError;
    @FXML private Label ciudadMsgError;
    @FXML private Label provinciaMsgError;
    @FXML private Label imagenMsgError;
    @FXML private Button baceptar;
    @FXML private Button bcancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        imagenes=FXCollections.observableArrayList();
        imagenes.addAll("LLoroso.png","Pregunta.png","Sonriente.png");
        imagen.setItems(imagenes);
        id.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) id.setText(newValue.replaceAll("[^\\d]", "")); });
    }
    
    public boolean isOkAccion() {return okAccion;}
    
    public void initStage(Stage stage, Person p, String a) {
        
        modalStage = stage;
        this.persona=p;
        accion=a;
        modalStage.setTitle(accion);
        
        if ("Añadir".equals(accion)) {
            this.id.setText("");
            this.nombre.setText("");
            this.ciudad.setText("");
            this.provincia.setText("");
        }
        else {
            if ("Borrar".equals(accion)) {panelGrid.disableProperty().setValue(true);}
            
            this.id.setText(this.persona.getId()+"");
            this.nombre.setText(this.persona.getNombre());
            this.ciudad.setText(this.persona.getResidencia().getCiudad());
            this.provincia.setText(this.persona.getResidencia().getProvincia());
            this.imagen.getSelectionModel().select(this.persona.getImagen());
        }
    }

    @FXML private void aceptar(ActionEvent event) {
        
        if ("Borrar".equals(accion)) 
            okAccion = true;
        else
            if (isInputValid()) {
                this.persona.setId(Integer.parseInt(id.getText()));
                this.persona.setNombre(nombre.getText());
                this.persona.setResidencia(new Residencia(ciudad.getText(),provincia.getText()));
                this.persona.setImagen(imagen.getValue().toString());
                okAccion = true;
            }
        if(okAccion) modalStage.close();
    }

    @FXML private void cancelar(ActionEvent event) {
        modalStage.close();
    }
    
    private boolean isInputValid() {
        Boolean isValid = true;
        
        if (id.getText() == null || id.getText().length() == 0) {
            idMsgError.setText("Id No valido! ");
            isValid=false;
        }
        else idMsgError.setText("");
        
        if (nombre.getText() == null || nombre.getText().length() == 0) {
            nombreMsgError.setText("Nombre No valido! ");
            isValid=false;
        }
        
        else nombreMsgError.setText("");
        if (ciudad.getText() == null || ciudad.getText().length() == 0) {
            ciudadMsgError.setText("Ciudad No valido! ");
            isValid=false;
        }
        else ciudadMsgError.setText("");
        
        if (provincia.getText() == null || provincia.getText().length() == 0) {
            provinciaMsgError.setText("Provincia No valido! ");
            isValid=false;
        }
        else provinciaMsgError.setText("");
        
        if (imagen.getSelectionModel().getSelectedIndex()==-1) {
            imagenMsgError.setText("Imagen No valido! ");
            isValid=false;
        }
        else imagenMsgError.setText("");
        
        return isValid;
    }
}
