/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que permite manejar Categorias en el sistema
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByIdcategoria", query = "SELECT c FROM Categoria c WHERE c.idcategoria = :idcategoria")
    , @NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre like :nombre")
})

public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategoria")
    private Integer idcategoria;

    @Basic(optional = false)
//    @NotNull
    @Size(min = 0, max = 100, message = "El nombre de la categoría no puede tener más de 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private List<CategoriaHasComponente> categoriaHasComponenteList;

    /**
     * Constructor vacío de la clase Categoria
     */
    public Categoria() {
    }

    /**
     * Constructor con parámetros de la clase Categoria
     *
     * @param idcategoria un entero con la id de la categoria
     */
    public Categoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    /**
     * Constructor con parámetros de la clase Categoria
     *
     * @param idcategoria un entero con la id de la categoria
     * @param nombre una cadena con el nombre de la categoria
     */
    public Categoria(Integer idcategoria, String nombre) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
    }

    /**
     *
     * @return un entero con la id de la categoria
     */
    public Integer getIdcategoria() {
        return idcategoria;
    }

    /**
     *
     * @param idcategoria un entero con la nueva id de la categoria
     */
    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    /**
     *
     * @return una cadena con el nombre de la categoria
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre una cadena con el nuevo nombre de la categoria
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return un listado con las referencias a la tabla terciaria
     * CategoriaHasComponente
     */
    @XmlTransient
    public List<CategoriaHasComponente> getCategoriaHasComponenteList() {
        return categoriaHasComponenteList;
    }

    /**
     *
     * @param categoriaHasComponenteList un listado con las nuevas referencias a
     * la tabla terciaria CategoriaHasComponente
     */
    public void setCategoriaHasComponenteList(List<CategoriaHasComponente> categoriaHasComponenteList) {
        this.categoriaHasComponenteList = categoriaHasComponenteList;
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
        hash += (idcategoria != null ? idcategoria.hashCode() : 0);
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.idcategoria == null && other.idcategoria != null) || (this.idcategoria != null && !this.idcategoria.equals(other.idcategoria))) {
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
        return "Categoria{" + "idcategoria=" + idcategoria + ", nombre=" + nombre + '}';
    }

}
