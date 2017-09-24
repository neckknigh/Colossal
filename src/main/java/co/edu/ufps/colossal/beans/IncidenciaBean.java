/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.Incidencia;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.negocio.Message;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author neck
 */
@Named(value = "incidenciaBean")
@ViewScoped
public class IncidenciaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Colossal colossal;

    private Incidencia incidencia;
    private List<Incidencia> listIncidencias;

    /**
     * Creates a new instance of IncidenciaBean crea una instancia del atributo
     * incidencia
     */
    public IncidenciaBean() {
        this.incidencia = new Incidencia();
    }

    /**
     * metodo get del atributo listIncidencias
     *
     * @return
     */
    public List<Incidencia> getListIncidencias() {
        return listIncidencias;
    }

    /**
     * Metodo set del atributo listIncidencias
     *
     * @param listIncidencias
     */
    public void setListIncidencias(List<Incidencia> listIncidencias) {
        this.listIncidencias = listIncidencias;
    }

    /**
     * MEtodo get del atributo incidencia
     *
     * @return
     */
    public Incidencia getIncidencia() {
        return incidencia;
    }

    /**
     * Metodo set del atributo incidencia
     *
     * @param incidencia
     */
    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    /**
     * Metodo que registra una incidencia en la base de datos, genera un mensaje
     * al completar la operacion, valida que todos los datos ingresados esten
     * correctos
     *
     * @param componente
     */
    public void registrarIncidencia(Componente componente) {

        if (this.incidencia.getDescripcion().trim().isEmpty()) {
            Message.addErrorMessage("Los detalles de la incidencia no pueden estar vacíos!", null);
            return;
        }

        String msg = this.colossal.registrarIncidencia(componente, incidencia);
        if (msg.contains("Error")) {
            Message.addErrorMessage(msg, null);
        } else {
            Message.addMessage(msg, null);
        }
        this.incidencia = new Incidencia();
    }

    /**
     * Metodo que devuelve las incidencias asociadas a un componente
     *
     * @param idcomponente
     */
    public void getIncidenciasXComponente(int idcomponente) {
        this.listIncidencias = this.colossal.getIncidenciasXComponente(idcomponente);
    }

}
