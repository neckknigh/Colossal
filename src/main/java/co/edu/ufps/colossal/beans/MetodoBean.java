/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Ejemplo;
import co.edu.ufps.colossal.entities.Metodo;
import co.edu.ufps.colossal.entities.Parametro;
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
@Named(value = "metodoBean")
@ViewScoped
public class MetodoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Metodo metodo;
    private List<Metodo> metodos;
    private Metodo selectedMetodo;
    private int index;
    private Metodo temp;

    /**
     * Creates a new instance of MetodoBean
     */
    public MetodoBean() {
        this.metodo = new Metodo();
        this.metodos = new ArrayList<>();
        this.index = -1;
    }

    /**
     * Metodo get del atributo metodo
     *
     * @return
     */
    public Metodo getMetodo() {
        return metodo;
    }

    /**
     * Metodo get del atributo selectedMetodo
     *
     * @return
     */
    public Metodo getSelectedMetodo() {
        return selectedMetodo;
    }

    /**
     * Metodo set del atributo selectedMetodo
     *
     * @param selectedMetodo
     */
    public void setSelectedMetodo(Metodo selectedMetodo) {
        this.selectedMetodo = selectedMetodo;
    }

    /**
     * Metodo set del atributo metodo
     *
     * @param metodo
     */
    public void setMetodo(Metodo metodo) {
        this.metodo = metodo;
    }

    /**
     * Metodo get del atributo metodos
     *
     * @return
     */
    public List<Metodo> getMetodos() {
        return metodos;
    }

    /**
     * Metodo set del atributo metodos
     *
     * @param metodos
     */
    public void setMetodos(List<Metodo> metodos) {
        this.metodos = metodos;
    }

    /**
     * Permite añadir un metodo a la lista
     *
     * @param pBean un manged bean con los parametros a ser añadidos al metodo
     * @param eBean un manged bean con los ejemplos a ser añadidos al metodo
     */
    public void addMethod(ParametroBean pBean, EjemploBean eBean) {
//        System.out.println(this.metodo);
        String msg = this.validarCamposMetodo();

        if (!msg.equals("OK")) {
            Message.addErrorMessage(msg, "");
            return;
        }

        if (eBean.getEjemplos().isEmpty()) {
            Message.addErrorMessage("Cada método debe tener al menos 1 ejemplo totalmente funcional!", "");
            return;
        }

        if (this.index < 0) {

//        //referencia del metodo a todos sus ejemplos
            for (Ejemplo e : eBean.getEjemplos()) {
                e.setMetodoIdmetodo(this.metodo);
            }
            //referencia del metodo a todos sus ejemplos

            //referencia del metodo a todos sus ejemplos
            for (Parametro p : pBean.getParametros()) {
                p.setMetodoIdmetodo(this.metodo);
            }
//        //referencia del metodo a todos sus ejemplos

            this.metodo.setEjemploList(eBean.getEjemplos());
            this.metodo.setParametroList(pBean.getParametros());

            // cuidado, un metodo es el mismo si tiene los mismos parametros
            if (!this.metodos.contains(this.metodo)) {
                this.metodos.add(this.metodo);
                Message.addMessage("Método añadido satisfactoriamente!", "");
            } else {
                Message.addErrorMessage("El método " + this.metodo.getCabecera() + " (y sus parámetros) ya se encuentra registrado!", "");
                return;
            }

        }

        RequestContext.getCurrentInstance().update("form:padding");
        this.cancelEditMethod(pBean, eBean, false);

    }

    /**
     * Metodo que permite validar que los campos de un metodo no esten vacios
     *
     * @return
     */
    private String validarCamposMetodo() {
        String msg = "OK";
        if (this.metodo.getCabecera() == null || this.metodo.getCabecera().trim().isEmpty()) {

            msg = ("La cabecera del método no puede estar vacía!");
        } else if (this.metodo.getDescripcion() == null || this.metodo.getDescripcion().trim().isEmpty()) {
            msg = "La descripción del método no puede estar vacía!";
        } else if (this.metodo.getValorRetorno() == null || this.metodo.getValorRetorno().trim().isEmpty()) {
            msg = "Si el método no retorna nada debes indicarlo! (void)";
        }

        return msg;
    }

    /**
     * Metodo que permite guardar los cambios hechos en un metodo de un
     * componente
     *
     * @param pBean un managed bean conla informacion referente a los parametros
     * del metodo
     * @param eBean un managed bean con la informacion referente a los ejemplos
     * del metodo
     */
    public void guardarCambiosMetodo(ParametroBean pBean, EjemploBean eBean) {

        String msg = this.validarCamposMetodo();
        if (!msg.equals("OK")) {
            Message.addErrorMessage(msg, "");
            return;
        }

        if (eBean.getEjemplos().isEmpty()) {
            Message.addErrorMessage("Cada método debe tener al menos 1 ejemplo totalmente funcional!", "");
            return;
        }

        //referencia del metodo a todos sus ejemplos
        for (Ejemplo e : eBean.getEjemplos()) {
            e.setMetodoIdmetodo(this.metodo);
        }
        //referencia del metodo a todos sus ejemplos

        //referencia del metodo a todos sus ejemplos
        for (Parametro p : pBean.getParametros()) {
            p.setMetodoIdmetodo(this.metodo);
        }
//        //referencia del metodo a todos sus ejemplos

        this.metodo.setEjemploList(eBean.getEjemplos());
        this.metodo.setParametroList(pBean.getParametros());

        this.metodos.remove(temp);
        this.metodos.add(this.metodo);

        Message.addMessage("Método " + "modificado satisfactoriamente!", null);
        RequestContext.getCurrentInstance().update("form:padding");
        this.cancelEditMethod(pBean, eBean, false);

    }

    /**
     * Metodo que reinicia los valores de ejemploBean y parametroBean una vez
     * estos han sido añadidos al metodo.
     *
     * @param pBean un manged bean con los valores a reiniciar
     * @param eBean un manged bean con los valores a reiniciar
     */
    private void restarValues(ParametroBean pBean, EjemploBean eBean) {
        pBean.setParametro(new Parametro());
        pBean.setParametros(new ArrayList<Parametro>());
        pBean.setShowPanelParam(false);
        eBean.setShowPanelExample(false);
        eBean.setEjemplo(new Ejemplo());
        eBean.setEjemplos(new ArrayList<Ejemplo>());
    }

    /**
     * Metodo que permite eliminar un metodo de un componente
     *
     * @param pBean un managed bean conla informacion referente a los parametros
     * del metodo
     * @param eBean un managed bean con la informacion referente a los ejemplos
     * del metodo
     */
    public void removeMethod(ParametroBean pBean, EjemploBean eBean) {

        String update;
        if (this.temp == this.selectedMetodo) {

            this.restarValues(pBean, eBean);

            update = "form:padding";
        } else {

            update = "form:outputMethod";
        }
        this.metodo = new Metodo();
        this.metodos.remove(this.selectedMetodo);
        this.temp = null;

        this.index = -1;

        if (this.metodos.isEmpty()) {
            this.restarValues(pBean, eBean);
            RequestContext.getCurrentInstance().update("form:padding");

        } else {
            RequestContext.getCurrentInstance().update(update);

        }
        Message.addMessage("Método eliminado satisfactoriamente!", update);
    }

    /**
     * Metodo que permite editar un metodo
     *
     * @param eBean un managed bean con la informacion referente a los ejemplos
     * del metodo
     * @param pBean un managed bean conla informacion referente a los parametros
     * del metodo
     */
    public void editMethod(EjemploBean eBean, ParametroBean pBean) {

//         se clona el metodo para que los cambios sin guardar sobre el metodo por parte del usuario no afecten al metodo ya registrado
        Metodo clon = this.selectedMetodo.clone();
//        Metodo clon = this.selectedMetodo;
        pBean.setParametros(clon.getParametroList());
        eBean.setEjemplos(clon.getEjemploList());
        this.index = this.metodos.indexOf(this.selectedMetodo);
        this.metodo = clon;
        this.temp = this.selectedMetodo;

        // ocultamos ambos paneles
        eBean.setShowPanelExample(false);
        pBean.setShowPanelParam(false);

    }

    /**
     * Metodo get del atributo index
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * Metodo set del atributo index
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Metodo que permite conocer si un usuario está editando un metodo que
     * previamente ha sido agregado
     *
     * @return
     */
    public boolean estaEditandoMetodo() {
        return this.index != -1;
    }

    /**
     * Metodo que permite cancelar la edicion de un metodo
     *
     * @param pBean un managed bean conla informacion referente a los parametros
     * del metodo
     * @param eBean un managed bean con la informacion referente a los ejemplos
     * del metodo
     * @param sw un booleano usado para controlar si se muestra un mensaje o no
     */
    public void cancelEditMethod(ParametroBean pBean, EjemploBean eBean, boolean sw) {
        this.index = -1;
        this.metodo = new Metodo();
        this.restarValues(pBean, eBean);
        if (sw) { // si esta prendido muestre el mensaje
            Message.addMessage("Edición Cancelada!", "");
        }
    }

}
