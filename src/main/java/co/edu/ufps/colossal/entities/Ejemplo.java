package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Clase que permite manejar un ejemplo de un método de un componente
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "ejemplo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ejemplo.findAll", query = "SELECT e FROM Ejemplo e")
    , @NamedQuery(name = "Ejemplo.findByIdejemplo", query = "SELECT e FROM Ejemplo e WHERE e.idejemplo = :idejemplo")})

public class Ejemplo implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idejemplo")
    private Integer idejemplo;

    @Lob
    @Size(max = 2147483647)
    @Column(name = "ejUso")
    private String ejUso;

    @JoinColumn(name = "metodo_idmetodo", referencedColumnName = "idmetodo")
    @ManyToOne(optional = false)
    private Metodo metodoIdmetodo;

    /**
     * Constructor sin parámetros de la clase Ejemplo
     */
    public Ejemplo() {
    }

    /**
     *
     * @return un entero con la id del ejemplo
     */
    public Integer getIdejemplo() {
        return idejemplo;
    }

    /**
     *
     * @param idejemplo un entero con la nueva id del ejemplo
     */
    public void setIdejemplo(Integer idejemplo) {
        this.idejemplo = idejemplo;
    }

    /**
     *
     * @return una cadena con las instrucciones de como debe ser usado el método
     */
    public String getEjUso() {
        return ejUso;
    }

    /**
     *
     * @param ejUso una cadena con las nuevas instrucciones de como debe ser
     * usado el método
     */
    public void setEjUso(String ejUso) {
        this.ejUso = ejUso;
    }

    /**
     *
     * @return una referencia al método al cual pertenece el ejemplo
     */
    public Metodo getMetodoIdmetodo() {
        return metodoIdmetodo;
    }

    /**
     *
     * @param metodoIdmetodo una referencia al nuevo método al cual pertenece el
     * ejemplo
     */
    public void setMetodoIdmetodo(Metodo metodoIdmetodo) {
        this.metodoIdmetodo = metodoIdmetodo;
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
        hash += (idejemplo != null ? idejemplo.hashCode() : 0);
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
     * @return true si son iguales, false de otra forma.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ejemplo other = (Ejemplo) obj;

        if (!Objects.equals(this.ejUso, other.ejUso)) {
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
        return "Ejemplo{" + "idejemplo=" + idejemplo + ", ejUso=" + ejUso + '}';
    }

    /**
     * Permite clonar un ejemplo
     *
     * @return una referencia con el ejemplo clonado.
     */
    @Override
    public Ejemplo clone() {
        Ejemplo clon = new Ejemplo();
        clon.setEjUso(ejUso);
        clon.setIdejemplo(idejemplo);

        clon.setMetodoIdmetodo(metodoIdmetodo);

        return clon;
    }

}
