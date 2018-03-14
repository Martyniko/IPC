/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3.pkg2;

import controller.FXMLDocumentController;
import controller.FXMLPersonaController;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import model.Residencia;

/**
 *
 * @author Martin Romero
 */
public class Practica3E2 extends Application {
    private static FXMLDocumentController controladorPrincipal;
    private static FXMLPersonaController controladorVentanaPersona;
    private static final ObservableList<Person> PersonsList=FXCollections.observableArrayList();
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
	this.primaryStage = primaryStage;
        PersonsList.add(new Person(1,"John Doe",new Residencia("Valencia","Valencia"),"LLoroso.png"));
        PersonsList.add(new Person(2,"Janes Doe",new Residencia("Valencia","Valencia"),"Pregunta.png"));
        PersonsList.add(new Person(3,"Peter Doe",new Residencia("Valencia","Valencia"),"Sonriente.png"));
        showVentanaListPersons(primaryStage);
    }
    
    public void showVentanaListPersons(Stage stage) throws Exception{
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Lista de personas");
        stage.setScene(scene);
        controladorPrincipal = loader.<FXMLDocumentController>getController();
        controladorPrincipal.setMain(this);
        controladorPrincipal.initStage(stage,PersonsList);
        stage.show();
    }
        
    public Boolean showVentanaPersona(Person persona, String accion) {
        try {
            Stage estageActual = new Stage();
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/FXMLPersona.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            estageActual.setScene(scene);
            estageActual.initModality(Modality.APPLICATION_MODAL);
            controladorVentanaPersona = loader.<FXMLPersonaController>getController();
            controladorVentanaPersona.initStage(estageActual,persona,accion);
            estageActual.showAndWait();
            return controladorVentanaPersona.isOkAccion();
        } catch (IOException e)  {return false;} 
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
