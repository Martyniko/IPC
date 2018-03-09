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

/**
 * @author Martin Romero
 */
public class Person {
    private final IntegerProperty id;
    private final StringProperty nombre;
    private final StringProperty apellidos;
    private final ObjectProperty <Residencia> residencia;
    private final StringProperty imagen;
        
    public Person(int id, String nombre, String apellidos, Residencia residencia, String imagen){
        this.nombre = new SimpleStringProperty(nombre);
        this.id = new SimpleIntegerProperty(id);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.residencia = new SimpleObjectProperty(residencia);
        this.imagen = new SimpleStringProperty(imagen);
    }
    
    public int getId(){return this.id.get();}
    public void setId(int id){this.id.set(id);}
    public IntegerProperty idProperty(){return this.id;}
    
    public String getNombre(){return this.nombre.get();}
    public void setNombre(String nombre){this.nombre.set(nombre);}
    public StringProperty nombreProperty(){return this.nombre;}
    
    public String getApellidos(){return this.apellidos.get();}
    public void setApellidos(String apellidos){this.apellidos.set(apellidos);}
    public StringProperty apellidosProperty(){return this.apellidos;}
    
    public String getImagen(){return this.imagen.get();}
    public void setImagen(String imagen){this.imagen.set(imagen);}
    public StringProperty imagenProperty(){return this.imagen;}
        
    
    public Residencia getResidencia() {return residencia.get();}
    public void setResidencia(Residencia value) {residencia.set(value);}
    public ObjectProperty residenciaProperty() {return residencia;}
}
