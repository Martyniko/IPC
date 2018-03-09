/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3.pkg2;

import controller.FXMLDocumentController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Person;
import model.Residencia;

/**
 *
 * @author Martin Romero
 */
public class Practica3E2 extends Application {
    private static final ObservableList<Person> Personas=FXCollections.observableArrayList();
    private static FXMLDocumentController controladorPrincipal;
    @Override
    public void start(Stage stage) throws Exception {
        Personas.add(new Person(1,"John", "Doe",new Residencia("Valencia","Valencia"),"LLoroso.png"));
        Personas.add(new Person(2,"Janes", "Doe",new Residencia("Valencia","Valencia"),"Pregunta.png"));
        Personas.add(new Person(3,"Peter", "Doe",new Residencia("Valencia","Valencia"),"Sonriente.png"));
       
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Lista de personas");
        stage.setScene(scene);
        controladorPrincipal = loader.<FXMLDocumentController>getController();
        controladorPrincipal.initStage(stage);
        controladorPrincipal.setMain(this);
        stage.show();
    }
    
    public ObservableList<Person> getPersonas() {
        return Personas;
    }
    
    public static void añadirPersona(Person p) {
        Personas.add(p);
        controladorPrincipal.selectLastPerson();
    }
       
    public static void borrarPersona(Person p) {
        Personas.remove(p);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
