/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import controller.FXMLDocumentController;
import controller.FXMLPersonaController;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Person;
import model.PersonListWrapper;
import model.Residencia;

/**
 *
 * @author Martin Romero
 */
public class Practica3E2V2M extends Application {
    private static FXMLDocumentController controladorPrincipal;
    private static FXMLPersonaController controladorVentanaPersona;
    private static final ObservableList<Person> listPersons=FXCollections.observableArrayList();
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
	this.primaryStage = primaryStage;
        listPersons.add(new Person(1,"John Doe",new Residencia("Valencia","Valencia"),"LLoroso.png"));
        listPersons.add(new Person(2,"Janes Doe",new Residencia("Valencia","Valencia"),"Pregunta.png"));
        listPersons.add(new Person(3,"Peter Doe",new Residencia("Valencia","Valencia"),"Sonriente.png"));
        
        showAVentanalistPersons(primaryStage);
    }
    
    public void showAVentanalistPersons(Stage stage) throws Exception{
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Lista de personas");
        stage.setScene(scene);
        controladorPrincipal = loader.<FXMLDocumentController>getController();
        controladorPrincipal.setMain(this);
        controladorPrincipal.initStage(stage,listPersons);
        stage.show();
        //File file = new File("/xml/persons.xml");
        File file = getPersonFilePath();
	if (file != null) loadPersonFromXML(file);
    }
        
    public Boolean showAVentanaPersona(Person persona, String accion) {
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
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Practica3E2V2M.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null)  return new File(filePath);
        else return null;
    }    
    
    public void loadPersonFromXML(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
            listPersons.clear();
            listPersons.addAll(wrapper.getPersons());
            setPersonFilePath(file);
        } catch (JAXBException e) {}
    }
    
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Practica3E2V2M.class);
        if (file != null) prefs.put("filePath", file.getPath());
        else prefs.remove("filePath");
    }
    
    public void saveAsPersonToXML() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
                if (!file.getPath().endsWith(".xml")) file = new File(file.getPath() + ".xml");
                savePersonToXML(file);
        }
    }
    
    public void savePersonToXML(File file) {
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(listPersons);
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(wrapper, file);
            setPersonFilePath(file);
            m.marshal(wrapper, System.out);
        } catch (JAXBException e) {e.printStackTrace();}
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
