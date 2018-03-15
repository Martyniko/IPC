/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Person;
import model.Residencia;
import Application.Practica3E2V2M;

/**
 * FXML Controller class
 *
 * @author  Martin Romero
 */
public class FXMLDocumentController implements Initializable {
    private Stage primaryStage;
    private Practica3E2V2M mainApp;
    private Person persona;
    @FXML private Button Añadir;
    @FXML private Button Modificar;
    @FXML private Button Borrar;
    @FXML private TableView<Person> lpersonas;
    @FXML private TableColumn<Person, Integer> idColumn;
    @FXML private TableColumn<Person, String> nombreColumn;
    @FXML private TableColumn<Person, Residencia> ciudadColumn;
    @FXML private TableColumn<Person, String> imagenColumn;
    /**
     * Initializes the controller class.
     */
    @FXML public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        ciudadColumn.setCellValueFactory(c->c.getValue().residenciaProperty());
        ciudadColumn.setCellFactory(v->{
            return new TableCell<Person, Residencia>(){
                @Override
                protected void updateItem(Residencia item, boolean empty){
                    super.updateItem(item, empty);
                    if (item==null||empty){
                    setText(null);
                     }
                    else{
                       setText(item.getCiudad());
                     }
                }
            };
        });
        imagenColumn.setCellValueFactory(cellData -> cellData.getValue().imagenProperty());
        imagenColumn.setCellFactory(columna -> {
            return new TableCell<Person,String> () {
                private final ImageView view = new ImageView();
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) setGraphic(null);
                    else {
                        Image image;
                        image = new Image("/imagenes/"+item, 40, 40, true, true);
                        view.setImage(image);
                        setGraphic(view);
                    }
                }
            };
        }); 
        
        BooleanBinding noPersonSelected = Bindings.isEmpty(lpersonas.getSelectionModel().getSelectedItems());
        this.Borrar.disableProperty().bind(noPersonSelected);
        this.Modificar.disableProperty().bind(noPersonSelected);
        lpersonas.getSelectionModel().selectedItemProperty().addListener((o, oldval, newval) -> {persona = newval;});
    }    
    
    @FXML private void añadir(ActionEvent event) {
        Person newItem = new Person();
        boolean okAccion = mainApp.showAVentanaPersona(newItem,"Añadir");
        if (okAccion) {
                lpersonas.getItems().add(newItem);
                lpersonas.getSelectionModel().selectLast();
        }
    }
     
    @FXML private void  guardar(ActionEvent event) {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonToXML(personFile);
        } else {
            mainApp.saveAsPersonToXML();
        }
    }

    @FXML private void modificar(ActionEvent event) {
        boolean okAccion = mainApp.showAVentanaPersona(persona,"Modificar");
    }

    @FXML private void borrar(ActionEvent event) {
        boolean okAccion = mainApp.showAVentanaPersona(persona,"Borrar");
        if (okAccion) lpersonas.getItems().remove(persona);
    }
        
    public void setMain(Practica3E2V2M mainApp) {
        this.mainApp = mainApp;
    }
    
    public void initStage(Stage stage, ObservableList<Person> lp) {
        primaryStage = stage;
        lpersonas.setItems(lp);
    }
}
