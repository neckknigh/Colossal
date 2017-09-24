/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.security.SessionManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author neck
 */
@Named(value = "dependenciaBean")
@ViewScoped
public class DependenciaBean implements Serializable {

    @EJB
    private Colossal colossal;

    private List<Componente> dependencias;
    private List<Componente> dependenciasAlreadySelected;
    private Componente componente;

    /**
     * Creates a new instance of DependenciaBean
     */
    public DependenciaBean() {
        this.dependencias = new ArrayList<>();
        this.dependenciasAlreadySelected = new ArrayList<>();
    }

    /**
     * Metodo get del atributo componente
     * @return 
     */
    public Componente getComponente() {
        return componente;
    }

    /**
     * Metodo set del atributo componente
     * @param componente 
     */
    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    /**
     * Metodo get del atributo dependencias
     * @return 
     */
    public List<Componente> getDependencias() {
        return dependencias;
    }

    /**
     * Metodo set del atributo dependencias
     * @param dependencias 
     */
    public void setDependencias(List<Componente> dependencias) {
        this.dependencias = dependencias;
    }

    /**
     * Metodo get del atributo dependenciasAlreadySelected
     * @return 
     */
    public List<Componente> getDependenciasAlreadySelected() {
        return dependenciasAlreadySelected;
    }

    /**
     * Metodo set del atributo dependenciasAlreadySelected
     * @param dependenciasAlreadySelected 
     */
    public void setDependenciasAlreadySelected(List<Componente> dependenciasAlreadySelected) {
        this.dependenciasAlreadySelected = dependenciasAlreadySelected;
    }

    /**
     * Metodo que devuelve un listado de componentes que coincidan con una cadena 
     * @param query la cadena con el patron de coincidencia
     * @return un listado de componentes que coinciden con la cadena query
     */
    public List<Componente> completeComponent(String query) {

        query = query.trim();
        List<Componente> allComponentes = this.colossal.findComponentsLikeNombre(query, SessionManager.getUsuarioSession());
        List<Componente> componentesFiltrados = new ArrayList<>();

//        System.out.println(this.dependenciasAlreadySelected==null);
        for (Componente allComponent : allComponentes) {
            if (!this.dependenciasAlreadySelected.contains(allComponent)) {
                componentesFiltrados.add(allComponent);
            }
        }

        System.out.println(componentesFiltrados);
        return (componentesFiltrados);

    }

    /**
     * Metodo que devuelve un listado de componentes que coincidan con una cadena 
     * @param query la cadena con el patron de coincidencia
     * @return un listado de componentes que coinciden con la cadena query
     */
    public List<Componente> completeComponent2(String query) {

        query = query.trim();

        List<Componente> allComponentes = this.colossal.findComponentsLikeNombreAprobados(query, componente);
        List<Componente> componentesFiltrados = new ArrayList<>();

        System.out.println(this.dependenciasAlreadySelected == null);
        for (Componente allComponent : allComponentes) {
            if (!this.dependenciasAlreadySelected.contains(allComponent)) {
                componentesFiltrados.add(allComponent);
            }
        }

        System.out.println(componentesFiltrados);
        return (componentesFiltrados);

    }

    /**
     * Metodo que permite eliminar un componente seleccionado que halla sido autocompletado
     * @param event 
     */
    public void onItemUnSelect(UnselectEvent event) {

//        System.out.println("Elimino!");
        Componente componenteSelected = (Componente) event.getObject();
//        System.out.println("va a eliminar: " + componenteSelected);
        this.dependenciasAlreadySelected.remove(componenteSelected);

    }

    /**
     * Metodo que permite agregar un componente que halla sido autocompletado
     * @param event 
     */
    public void onItemSelect(SelectEvent event) {

        Componente componenteSelected = (Componente) event.getObject();

        if (!this.dependenciasAlreadySelected.contains(componenteSelected)) {
//            System.out.println("Agrego a : " + componenteSelected.getNombre());
            this.dependenciasAlreadySelected.add(componenteSelected);
        }

    }

}
