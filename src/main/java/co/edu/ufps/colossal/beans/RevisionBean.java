/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Revision;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AvrilFockMe
 */
@Named(value = "revisionBean")
@ViewScoped
public class RevisionBean implements Serializable {

    private Revision revision;

    /**
     * Creates a new instance of RevisionBean
     */
    public RevisionBean() {
        this.revision = new Revision();
    }

    /**
     * Metodo get del atributo revision
     * @return 
     */
    public Revision getRevision() {
        return revision;
    }

    /**
     * Metodo set del atrbuto revision
     * @param revision 
     */
    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    /**
     * Metodo que permite actualizar el panel outputUpdateComponent en la vista
     */
    public void changeListener() {
//        System.out.println("Revision es : " + revision.getDescripcionCambios());

        RequestContext.getCurrentInstance().update("form:outputUpdateComponent");
    }

    /**
     * Metodo que indica si se debe desactivar la descripcion de la revision
     * @return 
     */
    public boolean disable() {

//        if (this.revision.getDescripcionCambios().equals("")) {
//            System.out.println("Desc es: "+this.revision.getDescripcionCambios());
//        }
        return this.revision.getDescripcionCambios().trim().equals("");
    }

}
