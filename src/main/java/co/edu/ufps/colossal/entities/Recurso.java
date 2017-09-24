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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que permite manejar un recurso de un componente en el sistema
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "recurso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r")
    , @NamedQuery(name = "Recurso.findByIdrecurso", query = "SELECT r FROM Recurso r WHERE r.idrecurso = :idrecurso")
    , @NamedQuery(name = "Recurso.findByNombre", query = "SELECT r FROM Recurso r WHERE r.nombre = :nombre AND r.componenteidComponente= :componente")
    , @NamedQuery(name = "Recurso.DeleteByNombre", query = "DELETE FROM Recurso r WHERE r.nombre = :nombre AND r.componenteidComponente= :componente ")
    , @NamedQuery(name = "Recurso.findByUrl", query = "SELECT r FROM Recurso r WHERE r.url = :url")})

public class Recurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrecurso")
    private Integer idrecurso;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100, message = "El nombre del recurso no puede tener más de 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500, message = "La url del recurso no puede tener más de 500 caracteres")
    @Column(name = "url")
    private String url;

    @JoinColumn(name = "componente_idComponente", referencedColumnName = "idComponente")
    @ManyToOne(optional = false)
    private Componente componenteidComponente;

    /**
     * Constructor sin parametros de la clase Recurso
     */
    public Recurso() {
    }

    /**
     * Constructor que recibe la ide del recurso
     * @param idrecurso un entero con la id del recurso a crear
     */
    public Recurso(Integer idrecurso) {
        this.idrecurso = idrecurso;
    }

    /**
     * Constructor que recibe el nombre y la url del recurso
     * @param nombre una cadena con el nombre del recuso a crear
     * @param url una cadena con la url del recurso
     */
    public Recurso(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
    }

    /**
     * Constructor con parametros de la clase Recurso
     *
     * @param idrecurso un entero con la id del recurso a crear
     * @param nombre una cadena con el nombre del recuso a crear
     * @param url una cadena con la url del recurso
     */
    public Recurso(Integer idrecurso, String nombre, String url) {
        this.idrecurso = idrecurso;
        this.nombre = nombre;
        this.url = url;
    }

    /**
     *
     * @return una cadena con la id del recurso
     */
    public Integer getIdrecurso() {
        return idrecurso;
    }

    /**
     *
     * @param idrecurso un entero con la id del recurso a setear
     */
    public void setIdrecurso(Integer idrecurso) {
        this.idrecurso = idrecurso;
    }

    /**
     *
     * @return una cadena con el nombre del recurso
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre una cadena con el nombre del recurso a setear
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return una cadena con la url del recurso
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url una cadena con la url del recurso a setear
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return la referencia al componente al que pertenece el recurso
     */
    public Componente getComponenteidComponente() {
        return componenteidComponente;
    }

    /**
     *
     * @param componenteidComponente una referencia al nuevo componente a setear
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
        hash += (idrecurso != null ? idrecurso.hashCode() : 0);
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
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.idrecurso == null && other.idrecurso != null) || (this.idrecurso != null && !this.idrecurso.equals(other.idrecurso))) {
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
        return "Recurso{" + "nombre=" + nombre + ", url=" + url + '}';
    }

}
