/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Martin Romero
 */
public class Residencia {
    private String ciudad;
    private String provincia;
    
    public Residencia(){
        this( null, null);
    }
    
    public Residencia(String ciudad, String provincia){
        this.ciudad = ciudad;
        this.provincia = provincia;
    }
    
    public String getCiudad(){return this.ciudad;}
    public void setCiudad(String ciudad){this.ciudad=ciudad;}
    
    public String getProvincia(){return this.provincia;}
    public void setProvincia(String provincia){this.provincia=provincia;}
}