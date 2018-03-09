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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;
import practica3.pkg2.Practica3E2;
import model.Residencia;


/**
 * FXML Controller class
 *
 * @author Martín Romero
 */
public class FXMLPersonaController implements Initializable {

    private Person persona;
    private String accion;
    private Stage primaryStage;
    private int opcion;
    private ObservableList<String> imagenes;
    @FXML private TextField id;
    @FXML private TextField nombre;
    @FXML private TextField apellidos;
    @FXML private TextField ciudad;
    @FXML private TextField provincia;
    @FXML private ComboBox imagen;
    @FXML private Button baceptar;
    @FXML private Button bcancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) id.setText(newValue.replaceAll("[^\\d]", "")); });
    }

    public void initStage(Stage stage, Person p, String a) {
        primaryStage = stage;
        persona=p;
        accion=a;
        primaryStage.setTitle(accion);
        imagenes=FXCollections.observableArrayList();
        imagenes.addAll("LLoroso.png","Pregunta.png","Sonriente.png");
        imagen.setItems(imagenes);
        if ("Añadir".equals(accion)) {
            this.id.setText("");
            this.nombre.setText("");
            this.apellidos.setText("");
            this.ciudad.setText("");
            this.provincia.setText("");
            
        }
        else {
            this.id.setText(persona.getId()+"");
            this.nombre.setText(persona.getNombre());
            this.apellidos.setText(persona.getApellidos());
            this.ciudad.setText(persona.getResidencia().getCiudad());
            this.provincia.setText(persona.getResidencia().getProvincia());
            this.imagen.getSelectionModel().select(persona.getImagen());
        }
    }

    @FXML private void aceptar(ActionEvent event) {
        switch (accion) {
            case "Borrar":
                Practica3E2.borrarPersona(persona);
                break;
            case "Añadir":
                Practica3E2.añadirPersona(new Person(Integer.parseInt(id.getText()),nombre.getText(), apellidos.getText(),new Residencia(ciudad.getText(),provincia.getText()),imagen.getValue().toString()));
                break;
            case "Modificar":
                persona.setId(Integer.parseInt(id.getText()));
                persona.setNombre(nombre.getText());
                persona.setApellidos(apellidos.getText());
                persona.setResidencia(new Residencia(ciudad.getText(),provincia.getText()));
                persona.setImagen(imagen.getValue().toString());
                break;
        }
        cancelar(event);
    }

    @FXML private void cancelar(ActionEvent event) {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }
}
