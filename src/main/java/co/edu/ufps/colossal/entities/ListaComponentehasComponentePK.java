/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Clase que permite representar la llave compuesta de la tabla terciaria
 * resultante de la relacion n a n entre listaComponente y componente
 *
 * @author Jhon Vargas
 */
@Embeddable
public class ListaComponentehasComponentePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "listaComponente_idlistaComponente")
    private int listaComponenteidlistaComponente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Componente_idComponente")
    private int componenteidComponente;

    /**
     * Constructor sin parámetros de la clase ListaComponentehasComponentePK
     */
    public ListaComponentehasComponentePK() {
    }

    /**
     * Constructor con parámetros de la clase ListaComponentehasComponentePK
     *
     * @param listaComponenteidlistaComponente un entero con el id de la lista
     * @param componenteidComponente un entero con el id del componente
     */
    public ListaComponentehasComponentePK(int listaComponenteidlistaComponente, int componenteidComponente) {
        this.listaComponenteidlistaComponente = listaComponenteidlistaComponente;
        this.componenteidComponente = componenteidComponente;
    }

    public int getListaComponenteidlistaComponente() {
        return listaComponenteidlistaComponente;
    }

    /**
     *
     * @param listaComponenteidlistaComponente un entero con el id de la lista
     */
    public void setListaComponenteidlistaComponente(int listaComponenteidlistaComponente) {
        this.listaComponenteidlistaComponente = listaComponenteidlistaComponente;
    }

    /**
     *
     * @return un entero con la id del componente
     */
    public int getComponenteidComponente() {
        return componenteidComponente;
    }

    /**
     *
     * @param componenteidComponente un entero con la id del componente a setear
     */
    public void setComponenteidComponente(int componenteidComponente) {
        this.componenteidComponente = componenteidComponente;
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
        hash += (int) listaComponenteidlistaComponente;
        hash += (int) componenteidComponente;
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
        if (!(object instanceof ListaComponentehasComponentePK)) {
            return false;
        }
        ListaComponentehasComponentePK other = (ListaComponentehasComponentePK) object;
        if (this.listaComponenteidlistaComponente != other.listaComponenteidlistaComponente) {
            return false;
        }
        if (this.componenteidComponente != other.componenteidComponente) {
            return false;
        }
        return true;
    }

    /**
     * The method is used to get a String object representing the value of the
     * Number Object. If the method takes a primitive data type as an argument,
     * then the String object representing the primitive data type value is
     * returned. If the method takes two arguments, then a String representation
     * of the first argument in the radix specified by the second argument will
     * be returned.
     *
     * @return String object representing the value of the Number Object.
     */
    @Override
    public String toString() {
        return "co.edu.ufps.alexandria.entities.ListaComponentehasComponentePK[ listaComponenteidlistaComponente=" + listaComponenteidlistaComponente + ", componenteidComponente=" + componenteidComponente + " ]";
    }

}
