/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase resultante de la relación n a n entre listaComponente y componente
 * @author Jhon Vargas
 */
@Entity
@Table(name = "listaComponente_has_Componente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaComponentehasComponente.findAll", query = "SELECT l FROM ListaComponentehasComponente l")
    , @NamedQuery(name = "ListaComponentehasComponente.findListaByIdListIdComp", query = "SELECT l FROM ListaComponentehasComponente l WHERE l.listaComponente = :lista AND l.componente = :componente")
    , @NamedQuery(name = "ListaComponentehasComponente.findByIdlistacom", query = "SELECT l FROM ListaComponentehasComponente l WHERE l.idlistacom = :idlistacom")
    , @NamedQuery(name = "ListaComponentehasComponente.findByListaComponenteidlistaComponente", query = "SELECT l FROM ListaComponentehasComponente l WHERE l.listaComponentehasComponentePK.listaComponenteidlistaComponente = :listaComponenteidlistaComponente")
    , @NamedQuery(name = "ListaComponentehasComponente.findByComponenteidComponente", query = "SELECT l FROM ListaComponentehasComponente l WHERE l.listaComponentehasComponentePK.componenteidComponente = :componenteidComponente")})

public class ListaComponentehasComponente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ListaComponentehasComponentePK listaComponentehasComponentePK;
    @Basic(optional = false)
    @Column(name = "idlistacom")
    private int idlistacom;
    @JoinColumn(name = "listaComponente_idlistaComponente", referencedColumnName = "idlistaComponente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ListaComponente listaComponente;
    @JoinColumn(name = "Componente_idComponente", referencedColumnName = "idComponente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Componente componente;

    /**
     * Constructor sin parámetros de la clase ListaComponentehasComponente
     */
    public ListaComponentehasComponente() {
    }

    public ListaComponentehasComponente(ListaComponentehasComponentePK listaComponentehasComponentePK) {
        this.listaComponentehasComponentePK = listaComponentehasComponentePK;
    }

    public ListaComponentehasComponente(ListaComponentehasComponentePK listaComponentehasComponentePK, int idlistacom) {
        this.listaComponentehasComponentePK = listaComponentehasComponentePK;
        this.idlistacom = idlistacom;
    }

    public ListaComponentehasComponente(int listaComponenteidlistaComponente, int componenteidComponente) {
        this.listaComponentehasComponentePK = new ListaComponentehasComponentePK(listaComponenteidlistaComponente, componenteidComponente);
    }

    public ListaComponentehasComponentePK getListaComponentehasComponentePK() {
        return listaComponentehasComponentePK;
    }

    public void setListaComponentehasComponentePK(ListaComponentehasComponentePK listaComponentehasComponentePK) {
        this.listaComponentehasComponentePK = listaComponentehasComponentePK;
    }

    /**
     * 
     * @return un entero con la id de la clase ListaComponentehasComponente
     */
    public int getIdlistacom() {
        return idlistacom;
    }

    /**
     * 
     * @param idlistacom una id para la clase ListaComponentehasComponente a setear
     */
    public void setIdlistacom(int idlistacom) {
        this.idlistacom = idlistacom;
    }

    /**
     * 
     * @return una referencia a la lista relacionada con el componente
     */
    public ListaComponente getListaComponente() {
        return listaComponente;
    }

    /**
     * 
     * @param listaComponente la lista nueva a setear
     */
    public void setListaComponente(ListaComponente listaComponente) {
        this.listaComponente = listaComponente;
    }

    /**
     * 
     * @return el componente relacionado con la lista
     */
    public Componente getComponente() {
        return componente;
    }

    /**
     * 
     * @param componente el componente nuevo relacionado con la lista
     */
    public void setComponente(Componente componente) {
        this.componente = componente;
    }

     /**
     * The hashCode() method of objects is used when you insert them into a
     * HashTable, HashMap or HashSet. When inserting an object into a hastable
     * you use a key. The hash code of this key is calculated, and used to
     * determine where to store the object internally. When you need to lookup
     * an object in a hashtable you also use a key. The hash code of this key is
     * calculated and used to determine where to search for the object.
     *
     * @return un entero con el hashcode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listaComponentehasComponentePK != null ? listaComponentehasComponentePK.hashCode() : 0);
        return hash;
    }

    /**
     * Compares values for equality. Because this method is defined in the
     * Object class, from which all other classes are derived, it's
     * automatically defined for every class. However, it doesn't perform an
     * intelligent comparison for most classes unless the class overrides it. It
     * has been defined in a meaningful way for most Java core classes. If it's
     * not defined for a (user) class, it behaves the same as ==. It turns out
     * that defining equals() isn't trivial; in fact it's moderately hard to get
     * it right, especially in the case of subclasses.
     *
     * @param object el objeto a comparar
     * @return true si son iguales, false de otra forma.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaComponentehasComponente)) {
            return false;
        }
        ListaComponentehasComponente other = (ListaComponentehasComponente) object;
        if ((this.listaComponentehasComponentePK == null && other.listaComponentehasComponentePK != null) || (this.listaComponentehasComponentePK != null && !this.listaComponentehasComponentePK.equals(other.listaComponentehasComponentePK))) {
            return false;
        }
        return true;
    }

    /**
     * The method is used to get a String object representing the value of the
     * Number Object.
     * If the method takes a primitive data type as an argument, then the String
     * object representing the primitive data type value is returned.
     * If the method takes two arguments, then a String representation of the
     * first argument in the radix specified by the second argument will be
     * returned.
     *
     * @return String object representing the value of the Number Object.
     */
    @Override
    public String toString() {
        return "co.edu.ufps.alexandria.entities.ListaComponentehasComponente[ listaComponentehasComponentePK=" + listaComponentehasComponentePK + " ]";
    }
    
}
