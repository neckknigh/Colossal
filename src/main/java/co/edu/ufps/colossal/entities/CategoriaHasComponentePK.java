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
 * Clase que permite representar la llave compuesta de la tabla  CategoriaHasComponente
 * @author Jhon Vargas
 */
@Embeddable
public class CategoriaHasComponentePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "categoria_idcategoria")
    private int categoriaIdcategoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "componente_idComponente")
    private int componenteidComponente;

    /**
     * Constructor vacío de la clase CategoriaHasComponentePK
     */
    public CategoriaHasComponentePK() {
    }

    /**
     * Constructor con parámetros de la clase CategoriaHasComponentePK
     * @param categoriaIdcategoria un entero con la id de la categoria relacionada
     * @param componenteidComponente un entero con la id del componente relacionado
     */
    public CategoriaHasComponentePK(int categoriaIdcategoria, int componenteidComponente) {
        this.categoriaIdcategoria = categoriaIdcategoria;
        this.componenteidComponente = componenteidComponente;
    }

    /**
     * 
     * @return un entero con la id de la categoria relacionada
     */
    public int getCategoriaIdcategoria() {
        return categoriaIdcategoria;
    }

    /**
     * 
     * @param categoriaIdcategoria un entero con la id de la nueva categoria relacionada
     */
    public void setCategoriaIdcategoria(int categoriaIdcategoria) {
        this.categoriaIdcategoria = categoriaIdcategoria;
    }

    /**
     * 
     * @return un entero con la id del componente relacionado
     */
    public int getComponenteidComponente() {
        return componenteidComponente;
    }

    /**
     * 
     * @param componenteidComponente un entero con la id del nuevo componente relacionado
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
        hash += (int) categoriaIdcategoria;
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
        if (!(object instanceof CategoriaHasComponentePK)) {
            return false;
        }
        CategoriaHasComponentePK other = (CategoriaHasComponentePK) object;
        if (this.categoriaIdcategoria != other.categoriaIdcategoria) {
            return false;
        }
        if (this.componenteidComponente != other.componenteidComponente) {
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
        return "co.edu.ufps.alexandria.entities.CategoriaHasComponentePK[ categoriaIdcategoria=" + categoriaIdcategoria + ", componenteidComponente=" + componenteidComponente + " ]";
    }
    
}
