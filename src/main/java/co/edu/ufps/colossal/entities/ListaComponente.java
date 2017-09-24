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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que permite manejar las listas de componentes
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "listaComponente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaComponente.findAll", query = "SELECT l FROM ListaComponente l")
    , @NamedQuery(name = "ListaComponente.deleteByName", query = "DELETE FROM ListaComponente l WHERE l.nombreLista = :nombreLista AND l.usuariocodigo = :usuario")
    , @NamedQuery(name = "ListaComponente.findByIdlistaComponente", query = "SELECT l.nombreLista FROM ListaComponente l WHERE l.idlistaComponente = :idlistaComponente")
    , @NamedQuery(name = "ListaComponente.findByUser", query = "SELECT l FROM ListaComponente l WHERE l.usuariocodigo = :idUser")
    , @NamedQuery(name = "ListaComponente.findByNombreLista", query = "SELECT l FROM ListaComponente l WHERE l.nombreLista = :nombreLista AND l.usuariocodigo =:usuario")})

public class ListaComponente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlistaComponente")
    private Integer idlistaComponente;

    @Basic(optional = false)
//    @NotNull
    @Size(min = 0, max = 100, message = "El nombre de la lista no puede tener más de 100 caracteres!")
    @Column(name = "nombreLista")
    private String nombreLista;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaComponente")
    private List<ListaComponentehasComponente> listaComponentehasComponenteList;

    @JoinColumn(name = "Usuario_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuariocodigo;

    /**
     * Constructor vacio de la clase ListaComponente
     */
    public ListaComponente() {
    }

    /**
     * Constructor con parámetros de la clase ListaComponente
     * @param idlistaComponente un entero con el id de la lista
     * @param nombreLista una cadena con el nombre de la lista
     */
    public ListaComponente(Integer idlistaComponente, String nombreLista) {
        this.idlistaComponente = idlistaComponente;
        this.nombreLista = nombreLista;
    }

    /**
     * 
     * @return un entero con el id de la lista 
     */
    public Integer getIdlistaComponente() {
        return idlistaComponente;
    }

    /**
     * 
     * @param idlistaComponente un entero con el nuevo id de la lista a setear
     */
    public void setIdlistaComponente(Integer idlistaComponente) {
        this.idlistaComponente = idlistaComponente;
    }

    /**
     * 
     * @return una cadena con el nombre de la lista
     */
    public String getNombreLista() {
        return nombreLista;
    }

    /**
     * 
     * @param nombreLista una cadena con el nombre de la lista
     */
    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    /**
     * Generado automaticamente por EclipseLink.
     * @return 
     */
    @XmlTransient
    public List<ListaComponentehasComponente> getListaComponentehasComponenteList() {
        return listaComponentehasComponenteList;
    }

    /**
     * Generado automaticamente por EclipseLink
     * @param listaComponentehasComponenteList 
     */
    public void setListaComponentehasComponenteList(List<ListaComponentehasComponente> listaComponentehasComponenteList) {
        this.listaComponentehasComponenteList = listaComponentehasComponenteList;
    }

    /**
     * 
     * @return una referencia con el usuario que creó la lista
     */
    public Usuario getUsuariocodigo() {
        return usuariocodigo;
    }

    /**
     * 
     * @param usuariocodigo una referencia con el nuevo usuario dueño de la lista
     */
    public void setUsuariocodigo(Usuario usuariocodigo) {
        this.usuariocodigo = usuariocodigo;
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
        hash += (idlistaComponente != null ? idlistaComponente.hashCode() : 0);
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
        if (!(object instanceof ListaComponente)) {
            return false;
        }
        ListaComponente other = (ListaComponente) object;
        if ((this.idlistaComponente == null && other.idlistaComponente != null) || (this.idlistaComponente != null && !this.idlistaComponente.equals(other.idlistaComponente))) {
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
        return "ListaComponente{" + "nombreLista=" + nombreLista + '}';
    }


}
