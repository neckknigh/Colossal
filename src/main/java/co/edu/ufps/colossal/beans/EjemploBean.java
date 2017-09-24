/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Ejemplo;
import co.edu.ufps.colossal.negocio.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AvrilFockMe
 */
@Named(value = "ejemploBean")
@ViewScoped
public class EjemploBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Ejemplo ejemplo;
    private List<Ejemplo> ejemplos;
    private boolean showPanelExample;
    private Ejemplo selectedExample;

    /**
     * Creates a new instance of EjemploBean
     */
    public EjemploBean() {
        this.ejemplo = new Ejemplo();
        this.ejemplos = new ArrayList<>();

    }

    /**
     * Metodo get del atributo selectedExample
     *
     * @return
     */
    public Ejemplo getSelectedExample() {
        return selectedExample;
    }

    /**
     * Metodo set del atributo selectedExample
     *
     * @param selectedExample
     */
    public void setSelectedExample(Ejemplo selectedExample) {

        this.selectedExample = selectedExample;

    }

    /**
     * Metodo set del atributo ejemplos
     *
     * @param ejemplos
     */
    public void setEjemplos(List<Ejemplo> ejemplos) {
        this.ejemplos = ejemplos;
    }

    /**
     * Metodo get del atributo ejemplo
     * @return 
     */
    public Ejemplo getEjemplo() {
        return ejemplo;
    }

    /**
     * Metodo set del atributo ejemplo
     * @param ejemplo 
     */
    public void setEjemplo(Ejemplo ejemplo) {
        this.ejemplo = ejemplo;
    }

    /**
     * Metodo get del atributo ejemplos
     * @return 
     */
    public List<Ejemplo> getEjemplos() {
        return ejemplos;
    }

    /**
     * Metodo get del atributo showPanelExample
     * @return 
     */
    public boolean isShowPanelExample() {
        return showPanelExample;
    }

    /**
     * Metodo set del atributo showPanelExample
     * @param showPanelExample 
     */
    public void setShowPanelExample(boolean showPanelExample) {
        this.showPanelExample = showPanelExample;
    }

    /**
     * Metodo que permite actualizar el panel donde se muestra el ejemplo
     */
    public void updatePanelExample() {
        this.showPanelExample = !this.showPanelExample;
        this.ejemplo = new Ejemplo();
    }

    /**
     * Metodo que permite añadir un ejemplo a un listado temporal, luego este será 
     * añadido a un metodo
     */
    public void addExample() {

//        System.out.println("entro!");
//        if(this.ejemplo.getEjConfiguracion()==null || this.ejemplo.getEjConfiguracion().trim().isEmpty() ){
//            Message.addErrorMessage("EDITAR ESTE MENSAJE", "");
//            return;
//        }
//        if(this.ejemplo.getEjParametros()==null || this.ejemplo.getEjParametros().trim().isEmpty()){
//            Message.addErrorMessage("EDITAR ESTE MENSAJE", "");
//            return;
//        }
        if (this.ejemplo.getEjUso() == null || this.ejemplo.getEjUso().trim().isEmpty()) {
            Message.addErrorMessage("No se permiten campos vacíos! ", "");
            return;
        }

        this.ejemplo.setEjUso(this.ejemplo.getEjUso().trim());

        if (!this.ejemplos.contains(this.ejemplo)) {
            this.ejemplos.add(ejemplo);
            this.ejemplo = new Ejemplo();

            RequestContext.getCurrentInstance().execute("panelCollapse(2)");

        } else {

        }

        this.setShowPanelExample(false);
        Message.addMessage("Ejemplo Añadido Satisfactoriamente!", "");

    }

    /**
     * Metodo que permite eliminar un ejemplo
     */
    public void removeEjemplo() {

        if (this.selectedExample == this.ejemplo) {
            this.showPanelExample = false;
        }

        if (this.ejemplos.size() == 1) {
            Message.addErrorMessage("Cada Método debe tener al menos 1 ejemplo totalmente funcional!", "");
        } else {
            this.ejemplos.remove(this.selectedExample);
            Message.addMessage("Ejemplo eliminado satisfactoriamente!", "");
        }

    }

    /**
     * Metodo que permite editar un ejemplo
     */
    public void editarEjemplo() {
        this.ejemplo = selectedExample;
        this.showPanelExample = true;
    }

}
