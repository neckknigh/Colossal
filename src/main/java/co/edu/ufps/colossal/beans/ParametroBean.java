/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Parametro;
import co.edu.ufps.colossal.negocio.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author AvrilFockMe
 */
@Named(value = "parametroBean")
@ViewScoped
public class ParametroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Parametro parametro;
    private List<Parametro> parametros;
    private boolean showPanelParam;
    private boolean opcional;

    /**
     * Creates a new instance of ParametroBean
     */
    public ParametroBean() {
        this.parametro = new Parametro();
        this.parametros = new ArrayList<>();
        this.showPanelParam = false;

    }

    /**
     * Metodo get del atributo parametro
     *
     * @return
     */
    public Parametro getParametro() {
        return parametro;
    }

    /**
     * Metodo set del atributo parametro
     *
     * @param parametro
     */
    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    /**
     * Metodo get del atributo parametros
     *
     * @return
     */
    public List<Parametro> getParametros() {
        return parametros;
    }

    /**
     * Metodo set del atributo parametros
     *
     * @param parametros
     */
    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    /**
     * Metodo que permite añadir un parametro a la lista
     */
    public void addParameter() {

        // Validaciones de entrada de informacion
        if (this.parametro.getParametro() == null || this.parametro.getParametro().trim().isEmpty()) {
            Message.addErrorMessage("El nombre del parámetro no puede estar vacio", "");
            return;
        }
//        if (this.parametro.getTipo() == null || this.parametro.getTipo().trim().isEmpty()) {
//            Message.addErrorMessage("El tipo del parámetro no puede estar vacio", "");
//            return;
//        }
        if (this.parametro.getDetalle() == null || this.parametro.getDetalle().trim().isEmpty()) {
            Message.addErrorMessage("Los detalles del parámetro no pueden estar vacios", "");
            return;
        }
        // Validaciones de entrada de informacion

        // Se eliminan los espacios en blanco al inicio y al final
        this.parametro.setParametro(this.parametro.getParametro().trim());
        this.parametro.setDetalle(this.parametro.getDetalle().trim());
        this.parametro.setTipo(this.parametro.getTipo().trim());

        int index = this.parametros.indexOf(this.parametro);

        if (index < 0) {
            this.parametros.add(parametro);
            this.parametro = new Parametro();

        } else {
            this.parametros.set(index, parametro);

        }
        this.opcional = false;
        this.showPanelParam = false;

        Message.addMessage("Parámetro añadido exitosamente!", "");
//        RequestContext.getCurrentInstance().update("form:panelParametros");
//        RequestContext.getCurrentInstance().update("form:fragmentParametro");
//        Message.addMessageScript("msgsEdit", "Parámetro añadido exitosamente!", "info");
//        RequestContext.getCurrentInstance().execute("panelCollapse(1)");
//        RequestContext.getCurrentInstance().update("form:padding");

    }

    /**
     * Metodo get del atributo showPanelParam
     *
     * @return
     */
    public boolean isShowPanelParam() {
        return showPanelParam;
    }

    /**
     * Metodo set del atributo showPanelParam
     *
     * @param showPanelParam showPanelParam
     */
    public void setShowPanelParam(boolean showPanelParam) {
        this.showPanelParam = showPanelParam;
    }

//    private void updateParameterPanel() {
//
//        RequestContext.getCurrentInstance().update("form:panelParametro");
//    }
    /**
     * Metodo que permite cambiar el estado del paranetro
     */
    public void changeStatusPar() {

        this.parametro = new Parametro();
        this.setShowPanelParam(!this.showPanelParam);
        this.opcional = false;

    }

    /**
     * Metodo que permite eliminar un parametro de la lista actual
     *
     * @param p el parametro a eliminar
     */
    public void removeParameter(Parametro p) {

        if (p == this.parametro) {
            this.setShowPanelParam(false);
        }

        this.parametros.remove(p);

//        System.out.println("Entrando a boorar parm");
        Message.addMessage("Parámetro eliminado satisfactoriamente!", "");

//        RequestContext.getCurrentInstance().execute("PF('msgsEdicion').renderMessage({'summary':'"+"Eliminado Satisfactoriamente!"+"','severity':'"+"info"+"'})");
//        Message.addMessageScript("msgsEdicion", "Eliminado Satisfactoriamente!", "info");
//        RequestContext.getCurrentInstance().update("form:panelgridParms");
    }

    /**
     * Metodo que captura el evento que surge cuando el usuario pulsa el
     * checkbox de cada parametro
     */
    public void onCheckEvent() {
        short value = 0;

        // si esta en 0 lo revierte a 1 y viceversa
        switch (this.parametro.getValorOpcional()) {
            case 0:
                value = 1;
                break;
            default:
                value = 0;
                break;
        }

        this.parametro.setValorOpcional(value);

    }

    /**
     * Metodo get del atributo opcional
     *
     * @return
     */
    public boolean isOpcional() {
        return opcional;
    }

    /**
     * Metodo set del atributo opcional
     *
     * @param opcional
     */
    public void setOpcional(boolean opcional) {
        this.opcional = opcional;
    }

    /**
     * metodo que retorna en texto el valor de opcionalidad de un parametro
     *
     * @param i 1 para si y 0 para no
     * @return si --> cuando el parametro es opcional, no--> cuando el parametro
     * es obligatorio
     */
    public String getValorDeOpcionalidad(int i) {
        return (i == 1 ? "Si" : "No");
    }

    /**
     * Metodo que permite editar un parametro
     *
     * @param p
     */
    public void editParameter(Parametro p) {
//        System.out.println(this.showPanelParam);
        this.setShowPanelParam(true);
        this.parametro = p;
        this.opcional = p.getValorOpcional() == 1;

    }
}
