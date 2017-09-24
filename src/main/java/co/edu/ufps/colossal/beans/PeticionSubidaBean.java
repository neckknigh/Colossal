/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.PeticionSubida;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author AvrilFockMe
 */
@Named(value = "peticionSubidaBean")
@ViewScoped
public class PeticionSubidaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private PeticionSubida peticion;

    /**
     * Creates a new instance of PeticionSubidaBean
     */
    public PeticionSubidaBean() {
        this.peticion = new PeticionSubida();
    }

    /**
     * Metodo que devuelve una peticion de subida
     * @return peticion de subida
     */
    public PeticionSubida getPeticion() {
        return peticion;
    }

    /**
     * Metodo que modifica una peticion de subida
     * @param peticion 
     */
    public void setPeticion(PeticionSubida peticion) {
        this.peticion = peticion;
    }

}
