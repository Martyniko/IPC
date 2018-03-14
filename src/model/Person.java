/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Martin Romero
 */
//@XmlRootElement
public class Person {
    private final IntegerProperty id;
    private final StringProperty nombre;
    private final ObjectProperty <Residencia> residencia;
    private final StringProperty imagen;
     
    public Person() {
		this(0, null, null, null);
    }

    public Person(int id, String nombre, Residencia residencia, String imagen){
        this.nombre = new SimpleStringProperty(nombre);
        this.id = new SimpleIntegerProperty(id);
        this.residencia = new SimpleObjectProperty(residencia);
        this.imagen = new SimpleStringProperty(imagen);
    }
        
    public int getId(){return this.id.get();}
    public void setId(int id){this.id.set(id);}
    public IntegerProperty idProperty(){return this.id;}
    
    public String getNombre(){return this.nombre.get();}
    public void setNombre(String nombre){this.nombre.set(nombre);}
    public StringProperty nombreProperty(){return this.nombre;}
    
    public String getImagen(){return this.imagen.get();}
    public void setImagen(String imagen){this.imagen.set(imagen);}
    public StringProperty imagenProperty(){return this.imagen;}
        
    
    public Residencia getResidencia() {return residencia.get();}
    public void setResidencia(Residencia value) {residencia.set(value);}
    public ObjectProperty residenciaProperty() {return residencia;}
}
