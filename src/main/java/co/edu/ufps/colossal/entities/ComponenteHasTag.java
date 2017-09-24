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
 *
 * Clase que resulta de la relacion n a n entre Tag y Componente.
 * @author Jhon Vargas
 */
@Entity
@Table(name = "componente_has_tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponenteHasTag.findAll", query = "SELECT c FROM ComponenteHasTag c")
    , @NamedQuery(name = "ComponenteHasTag.removeAll", query = "DELETE FROM ComponenteHasTag c WHERE c.componente = :componente")
    , @NamedQuery(name = "ComponenteHasTag.findByIdtagcom", query = "SELECT c FROM ComponenteHasTag c WHERE c.idtagcom = :idtagcom")
    , @NamedQuery(name = "ComponenteHasTag.findByComponenteidComponente", query = "SELECT c FROM ComponenteHasTag c WHERE c.componenteHasTagPK.componenteidComponente = :componenteidComponente")})

public class ComponenteHasTag implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComponenteHasTagPK componenteHasTagPK;
    @Basic(optional = false)
    @Column(name = "idtagcom")
    private int idtagcom;
    @JoinColumn(name = "componente_idComponente", referencedColumnName = "idComponente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Componente componente;
    @JoinColumn(name = "tag_idtag", referencedColumnName = "idtag", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tag tag;

    /**
     * Constructor sin parámetros de la clase ComponenteHasTag
     */
    public ComponenteHasTag() {
    }

    /**
     * Constructor que recibe la PK de la clase ComponenteHasTag
     * @param componenteHasTagPK una referencia a la llave compuesta de la clase ComponenteHasTag
     */
    public ComponenteHasTag(ComponenteHasTagPK componenteHasTagPK) {
        this.componenteHasTagPK = componenteHasTagPK;
    }

    /**
     * Constructor con parámetros de la clase ComponenteHasTag
     * @param componenteHasTagPK una referencia a la llave compuesta de la clase ComponenteHasTag
     * @param idtagcom un entero con la id de la clase ComponenteHasTag
     */
    public ComponenteHasTag(ComponenteHasTagPK componenteHasTagPK, int idtagcom) {
        this.componenteHasTagPK = componenteHasTagPK;
        this.idtagcom = idtagcom;
    }

    /**
     * Constructor con parámetros de la clase ComponenteHasTag
     * @param componenteidComponente un entero con la id del componente relacionado
     * @param tagIdtag un entero con la id del tag relacionado
     */
    public ComponenteHasTag(int componenteidComponente, int tagIdtag) {
        this.componenteHasTagPK = new ComponenteHasTagPK(componenteidComponente, tagIdtag);
    }

    /**
     * 
     * @return una referencia a la llave compuesta de la clase ComponenteHasTag
     */
    public ComponenteHasTagPK getComponenteHasTagPK() {
        return componenteHasTagPK;
    }

    /**
     * 
     * @param componenteHasTagPK una referencia a la nueva llave compuesta de la clase ComponenteHasTag
     */
    public void setComponenteHasTagPK(ComponenteHasTagPK componenteHasTagPK) {
        this.componenteHasTagPK = componenteHasTagPK;
    }

    /**
     * 
     * @return un entero con la id de la clase ComponenteHasTag
     */
    public int getIdtagcom() {
        return idtagcom;
    }

    /**
     * 
     * @param idtagcom un entero con la nueva id de la clase ComponenteHasTag
     */
    public void setIdtagcom(int idtagcom) {
        this.idtagcom = idtagcom;
    }

    /**
     * 
     * @return una referencia al componente relacionado
     */
    public Componente getComponente() {
        return componente;
    }

    /**
     * 
     * @param componente una referencia al nuevo componente relacionado
     */
    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    /**
     * 
     * @return una referencia al tag relacionado
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * 
     * @param tag una referencia al nuevo tag relacionado
     */
    public void setTag(Tag tag) {
        this.tag = tag;
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
        hash += (componenteHasTagPK != null ? componenteHasTagPK.hashCode() : 0);
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
        if (!(object instanceof ComponenteHasTag)) {
            return false;
        }
        ComponenteHasTag other = (ComponenteHasTag) object;
        if ((this.componenteHasTagPK == null && other.componenteHasTagPK != null) || (this.componenteHasTagPK != null && !this.componenteHasTagPK.equals(other.componenteHasTagPK))) {
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
        return "ComponenteHasTag{" + "componente=" + componente + ", tag=" + tag + '}';
    }

}
