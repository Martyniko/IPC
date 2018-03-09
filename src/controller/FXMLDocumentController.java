/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import model.Residencia;
import practica3.pkg2.Practica3E2;

/**
 * FXML Controller class
 *
 * @author  Martin Romero
 */
public class FXMLDocumentController implements Initializable {
    private Stage primaryStage;
    private Practica3E2 mainApp;
    private Person persona;
    @FXML private Button Añadir;
    @FXML private Button Modificar;
    @FXML private Button Borrar;
    @FXML private TableView<Person> lpersonas;
    @FXML private TableColumn<Person, Integer> idColumn;
    @FXML private TableColumn<Person, String> nombreColumn;
    @FXML private TableColumn<Person, String> apellidosColumn;
    @FXML private TableColumn<Person, Residencia> ciudadColumn;
    @FXML private TableColumn<Person, String> imagenColumn;
    /**
     * Initializes the controller class.
     */
    @FXML public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidosColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
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
    
    @FXML private void añadir(ActionEvent event) {irAVentana1(event);}

    @FXML private void modificar(ActionEvent event) {irAVentana1(event);}

    @FXML private void borrar(ActionEvent event) {irAVentana1(event);}
        
    @FXML private void irAVentana1(ActionEvent event) {
    try {
        Stage estageActual = new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/FXMLPersona.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        estageActual.setScene(scene);
        estageActual.initModality(Modality.APPLICATION_MODAL);
        FXMLPersonaController ventanaPersona = loader.<FXMLPersonaController>getController();
        Button button = (Button) event.getSource();
        ventanaPersona.initStage(estageActual,persona,button.getId());
        estageActual.show();
    } catch (IOException e)  {e.printStackTrace();} 
    }
    
    public void selectLastPerson() {
        lpersonas.getSelectionModel().selectLast();
    }
    
    public void setMain(Practica3E2 mainApp) {
        this.mainApp = mainApp;
        lpersonas.setItems(mainApp.getPersonas());
    }
    
    public void initStage(Stage stage) { primaryStage = stage;}
}
