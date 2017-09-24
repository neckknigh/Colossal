/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que permite manejar Revisiones para componentes en el sistema
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "revision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revision.findAll", query = "SELECT r FROM Revision r")
    , @NamedQuery(name = "Revision.findByIdrevision", query = "SELECT r FROM Revision r WHERE r.idrevision = :idrevision")
    , @NamedQuery(name = "Revision.findByVersion", query = "SELECT r FROM Revision r WHERE r.version = :version")
    , @NamedQuery(name = "Revision.findByFecha", query = "SELECT r FROM Revision r WHERE r.fecha = :fecha")})

public class Revision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrevision")
    private Integer idrevision;

    @Size(max = 45, message = "La versión no puede tener más de 45 caracteres!")
    @Column(name = "version")
    private String version;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcionCambios")
    private String descripcionCambios;

    @JoinColumn(name = "componente_idComponente", referencedColumnName = "idComponente")
    @ManyToOne(optional = false)
    private Componente componenteidComponente;

    /**
     * Constructor con parametros de la clase Revision
     */
    public Revision() {
        this.iniciar();
    }

    /**
     * Constructor que recibe la id de la revision a crear
     *
     * @param idrevision un entero con la id de la revision
     */
    public Revision(Integer idrevision) {
        this.idrevision = idrevision;
        this.iniciar();
    }

    /**
     * Permite inicializar las propiedades de la Revision
     */
    private void iniciar() {
        this.descripcionCambios = "";
    }

    /**
     *
     * @return un entero con la id de la revision
     */
    public Integer getIdrevision() {
        return idrevision;
    }

    /**
     *
     * @param idrevision un entero con la id de la revision a setear
     */
    public void setIdrevision(Integer idrevision) {
        this.idrevision = idrevision;
    }

    /**
     *
     * @return una cadena con la version de la revision
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version una cadena con la nueva version a setear
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return una referencia con la fecha de la revision
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha una referencia con la nueva fecha a setear
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     *
     * @return una cadena con la descripcion de los cambios hechos en la
     * revision
     */
    public String getDescripcionCambios() {
        return descripcionCambios;
    }

    /**
     *
     * @param descripcionCambios una cadena con la nueva descricion de cambios a
     * setear
     */
    public void setDescripcionCambios(String descripcionCambios) {
        this.descripcionCambios = descripcionCambios;
    }

    /**
     *
     * @return una referencia al componente involucrado en la revision
     */
    public Componente getComponenteidComponente() {
        return componenteidComponente;
    }

    /**
     *
     * @param componenteidComponente un componente a setear
     */
    public void setComponenteidComponente(Componente componenteidComponente) {
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
        hash += (idrevision != null ? idrevision.hashCode() : 0);
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
        if (!(object instanceof Revision)) {
            return false;
        }
        Revision other = (Revision) object;
        if ((this.idrevision == null && other.idrevision != null) || (this.idrevision != null && !this.idrevision.equals(other.idrevision))) {
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
        return "Revision{" + "version=" + version + ", fecha=" + fecha + ", descripcionCambios=" + descripcionCambios + '}';
    }

}
