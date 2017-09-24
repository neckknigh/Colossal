/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que permite representar un tag en el sistema
 * @author Jhon Vargas
 */
@Entity
@Table(name = "tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t")
    , @NamedQuery(name = "Tag.findByIdtag", query = "SELECT t FROM Tag t WHERE t.idtag = :idtag")
    , @NamedQuery(name = "Tag.findTagsLikeNombre", query = "SELECT t FROM Tag t WHERE t.nombre LIKE :nombre")
    , @NamedQuery(name = "Tag.findByNombre", query = "SELECT t FROM Tag t WHERE t.nombre = :nombre")})

public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtag")
    private Integer idtag;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = "El nombre del tag no puede tener más de 45 caracteres!")
    @Column(name = "nombre")
    private String nombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tag")
    private List<ComponenteHasTag> componenteHasTagList;

    /**
     * Constructor sin parametros de la clase Tag
     */
   public Tag() {
        this.iniciar();
    }

   /**
    * Constructor que recibe el id del tag a crear
    * @param idtag un entero con la id del tag a crear
    */
    public Tag(Integer idtag) {
        this.idtag = idtag;
        this.iniciar();
    }

    /**
     * Constructor con parametros de la clase Tag
     * @param idtag un entero con la id del tag
     * @param nombre una cadena con el nombre del tag
     */
    public Tag(Integer idtag, String nombre) {
        this.idtag = idtag;
        this.nombre = nombre;
        this.iniciar();
    }

    /**
     * Permite inicializar los listados de la clase Tag
     */
    private void iniciar() {
        this.componenteHasTagList = new ArrayList<>();
    }

    /**
     * 
     * @return un entero con el id del tag
     */
    public Integer getIdtag() {
        return idtag;
    }

    /**
     * 
     * @param idtag un entero con el nuevo id a setear
     */
    public void setIdtag(Integer idtag) {
        this.idtag = idtag;
    }

    /**
     * 
     * @return una cadena con el nombre del tag
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre una cadena con el nuevo nombre del tag a setear
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return un listado con los conponentes donde el tag esta presente
     */
    @XmlTransient
    public List<ComponenteHasTag> getComponenteHasTagList() {
        return componenteHasTagList;
    }

    /**
     * 
     * @param componenteHasTagList un listado con los nuevos componentes a setear
     */
    public void setComponenteHasTagList(List<ComponenteHasTag> componenteHasTagList) {
        this.componenteHasTagList = componenteHasTagList;
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
        hash += (idtag != null ? idtag.hashCode() : 0);
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
        final Tag other = (Tag) obj;
//        if (!Objects.equals(this.nombre, other.nombre)) {
//            return false;
//        }
        return this.nombre.trim().equalsIgnoreCase(other.nombre.trim());
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
        return "Tag{" + "idtag=" + idtag + ", nombre=" + nombre +'}';
    }
    
}
