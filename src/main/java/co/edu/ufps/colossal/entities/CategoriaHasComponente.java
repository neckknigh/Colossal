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
 * Clase que permite representar la asoiacion entre Categoria y Componente
 * @author Jhon Vargas
 */
@Entity
@Table(name = "categoria_has_componente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaHasComponente.findAll", query = "SELECT c FROM CategoriaHasComponente c")
    , @NamedQuery(name = "CategoriaHasComponente.removeAll", query = "DELETE FROM CategoriaHasComponente c WHERE c.componente = :componente")
    , @NamedQuery(name = "CategoriaHasComponente.findByIdcatcom", query = "SELECT c FROM CategoriaHasComponente c WHERE c.idcatcom = :idcatcom")
    , @NamedQuery(name = "CategoriaHasComponente.findByCategoriaIdcategoria", query = "SELECT c FROM CategoriaHasComponente c WHERE c.categoriaHasComponentePK.categoriaIdcategoria = :categoriaIdcategoria")
    , @NamedQuery(name = "CategoriaHasComponente.findByComponenteidComponente", query = "SELECT c FROM CategoriaHasComponente c WHERE c.categoriaHasComponentePK.componenteidComponente = :componenteidComponente")})

public class CategoriaHasComponente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CategoriaHasComponentePK categoriaHasComponentePK;
    @Basic(optional = false)
    @Column(name = "idcatcom")
    private int idcatcom;
    @JoinColumn(name = "categoria_idcategoria", referencedColumnName = "idcategoria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "componente_idComponente", referencedColumnName = "idComponente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Componente componente;

    /**
     * Constructor vacío de la clase CategoriaHasComponente
     */
    public CategoriaHasComponente() {
    }

    /**
     * Constructor con parámetros de la clase CategoriaHasComponente
     * @param categoriaHasComponentePK una referencia con la llave primaria de la clase CategoriaHasComponente
     */
    public CategoriaHasComponente(CategoriaHasComponentePK categoriaHasComponentePK) {
        this.categoriaHasComponentePK = categoriaHasComponentePK;
    }

    /**
     * Constructor con parámetros de la clase CategoriaHasComponente
     * @param categoriaHasComponentePK una referencia con la llave primaria de la clase CategoriaHasComponente
     * @param idcatcom un entero con la id de la clase CategoriaHasComponente
     */
    public CategoriaHasComponente(CategoriaHasComponentePK categoriaHasComponentePK, int idcatcom) {
        this.categoriaHasComponentePK = categoriaHasComponentePK;
        this.idcatcom = idcatcom;
    }

    /**
     * Constructor con parámetros de la clase CategoriaHasComponente
     * @param categoriaIdcategoria un entero con la id de la categoria asociada
     * @param componenteidComponente un entero con la id del componente asociado
     */
    public CategoriaHasComponente(int categoriaIdcategoria, int componenteidComponente) {
        this.categoriaHasComponentePK = new CategoriaHasComponentePK(categoriaIdcategoria, componenteidComponente);
    }

    /**
     * 
     * @return una referencia con la llave primaria de la clase CategoriaHasComponente
     */
    public CategoriaHasComponentePK getCategoriaHasComponentePK() {
        return categoriaHasComponentePK;
    }

    /**
     * 
     * @param categoriaHasComponentePK una referencia con la nueva llave primaria de la clase CategoriaHasComponente
     */
    public void setCategoriaHasComponentePK(CategoriaHasComponentePK categoriaHasComponentePK) {
        this.categoriaHasComponentePK = categoriaHasComponentePK;
    }

    /**
     * 
     * @return un entero con la id de la clase CategoriaHasComponente
     */
    public int getIdcatcom() {
        return idcatcom;
    }

    /**
     * 
     * @param idcatcom un entero con la nueva id de la clase CategoriaHasComponente
     */
    public void setIdcatcom(int idcatcom) {
        this.idcatcom = idcatcom;
    }

    /**
     * 
     * @return una referencia con la categoria relacionada
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * 
     * @param categoria una referencia con la nueva categoria relacionada
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * 
     * @return una referencia del componente relacionado
     */
    public Componente getComponente() {
        return componente;
    }

    /**
     * 
     * @param componente una referencia del nuevo componente relacionado
     */
    public void setComponente(Componente componente) {
        this.componente = componente;
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
        hash += (categoriaHasComponentePK != null ? categoriaHasComponentePK.hashCode() : 0);
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
        if (!(object instanceof CategoriaHasComponente)) {
            return false;
        }
        CategoriaHasComponente other = (CategoriaHasComponente) object;
        if ((this.categoriaHasComponentePK == null && other.categoriaHasComponentePK != null) || (this.categoriaHasComponentePK != null && !this.categoriaHasComponentePK.equals(other.categoriaHasComponentePK))) {
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
        return "co.edu.ufps.alexandria.entities.CategoriaHasComponente[ categoriaHasComponentePK=" + categoriaHasComponentePK + " ]";
    }

}
