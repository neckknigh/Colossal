/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Clase que permite manejar las dependencias de un componente
 * @author Jhon Vargas
 */
@Entity
@Table(name = "componente_has_dependency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponenteHasDependency.findAll", query = "SELECT c FROM ComponenteHasDependency c")
        , @NamedQuery(name = "ComponenteHasDependency.findByComponentOwner", query = "SELECT c FROM ComponenteHasDependency c WHERE c.componenteOwner = :componente")
        , @NamedQuery(name = "ComponenteHasDependency.deleteAllByComponentOwner", query = "DELETE FROM ComponenteHasDependency c WHERE c.componenteOwner = :componente")
    , @NamedQuery(name = "ComponenteHasDependency.findByIdComponenteHasdependency", query = "SELECT c FROM ComponenteHasDependency c WHERE c.idComponenteHasdependency = :idComponenteHasdependency")})
public class ComponenteHasDependency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idComponenteHasdependency")
    private Integer idComponenteHasdependency;
    @JoinColumn(name = "componente_owner", referencedColumnName = "idComponente")
    @ManyToOne
    private Componente componenteOwner;
    @JoinColumn(name = "componente_dependency", referencedColumnName = "idComponente")
    @ManyToOne
    private Componente componenteDependency;

    /**
     * Constructor vacio de la clase ComponenteHasDependency
     */
    public ComponenteHasDependency() {
    }

    /**
     * Constructor con parámetros de la clase ComponenteHasDependency
     * @param idComponenteHasdependency un entero con la id de la clase ComponenteHasDependency
     */
    public ComponenteHasDependency(Integer idComponenteHasdependency) {
        this.idComponenteHasdependency = idComponenteHasdependency;
    }

    /**
     * 
     * @return un entero con la id de la clase ComponenteHasDependency
     */
    public Integer getIdComponenteHasdependency() {
        return idComponenteHasdependency;
    }

    /**
     * 
     * @param idComponenteHasdependency un entero con la nueva id de la clase ComponenteHasDependency
     */
    public void setIdComponenteHasdependency(Integer idComponenteHasdependency) {
        this.idComponenteHasdependency = idComponenteHasdependency;
    }

    /**
     * 
     * @return una referencia al componente dueño de la dependencia
     */
    public Componente getComponenteOwner() {
        return componenteOwner;
    }

    /**
     * 
     * @param componenteOwner una referencia al nuevo componente dueño de la dependencia
     */
    public void setComponenteOwner(Componente componenteOwner) {
        this.componenteOwner = componenteOwner;
    }

    /**
     * 
     * @return una referencia al componente dependiente de Owner
     */
    public Componente getComponenteDependency() {
        return componenteDependency;
    }

    /**
     * 
     * @param componenteDependency una referencia al nuevo componente dependiente
     */
    public void setComponenteDependency(Componente componenteDependency) {
        this.componenteDependency = componenteDependency;
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
        hash += (idComponenteHasdependency != null ? idComponenteHasdependency.hashCode() : 0);
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
        if (!(object instanceof ComponenteHasDependency)) {
            return false;
        }
        ComponenteHasDependency other = (ComponenteHasDependency) object;
        if ((this.idComponenteHasdependency == null && other.idComponenteHasdependency != null) || (this.idComponenteHasdependency != null && !this.idComponenteHasdependency.equals(other.idComponenteHasdependency))) {
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
        return "co.edu.ufps.alexandria.entities.ComponenteHasDependency[ idComponenteHasdependency=" + idComponenteHasdependency + " ]";
    }
    
}
