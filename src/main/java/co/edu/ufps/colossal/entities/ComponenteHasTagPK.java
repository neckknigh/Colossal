
package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Clase que representa la llave primaria compuesta de la tabla ComponenteHasTag
 * @author Jhon Vargas
 */
@Embeddable
public class ComponenteHasTagPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "componente_idComponente")
    private int componenteidComponente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tag_idtag")
    private int tagIdtag;

    /**
     * Constructor vacio de la clase ComponenteHasTagPK
     */
    public ComponenteHasTagPK() {
    }

    /**
     * Constructor con parámetros de la clase ComponenteHasTagPK
     * @param componenteidComponente un entero con la id del componente relacionado
     * @param tagIdtag un entero con la id del tag relacionado
     */
    public ComponenteHasTagPK(int componenteidComponente, int tagIdtag) {
        this.componenteidComponente = componenteidComponente;
        this.tagIdtag = tagIdtag;
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
     * @param componenteidComponente un entero con la nueva id del componente relacionado
     */
    public void setComponenteidComponente(int componenteidComponente) {
        this.componenteidComponente = componenteidComponente;
    }

    /**
     * 
     * @return un entero con la id del tag relacionado
     */
    public int getTagIdtag() {
        return tagIdtag;
    }

    /**
     * 
     * @param tagIdtag un entero con la nueva id del tag relacionado
     */
    public void setTagIdtag(int tagIdtag) {
        this.tagIdtag = tagIdtag;
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
        hash += (int) componenteidComponente;
        hash += (int) tagIdtag;
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
        if (!(object instanceof ComponenteHasTagPK)) {
            return false;
        }
        ComponenteHasTagPK other = (ComponenteHasTagPK) object;
        if (this.componenteidComponente != other.componenteidComponente) {
            return false;
        }
        if (this.tagIdtag != other.tagIdtag) {
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
        return "co.edu.ufps.alexandria.entities.ComponenteHasTagPK[ componenteidComponente=" + componenteidComponente + ", tagIdtag=" + tagIdtag + " ]";
    }
    
}
