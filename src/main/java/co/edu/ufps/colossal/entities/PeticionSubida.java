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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que permite manejar las peticiones de subida de componentes al sistema
 * @author Jhon Vargas
 */
@Entity
@Table(name = "peticionSubida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeticionSubida.findAll", query = "SELECT p FROM PeticionSubida p")
    , @NamedQuery(name = "PeticionSubida.findByIdpeticionSubida", query = "SELECT p FROM PeticionSubida p WHERE p.idpeticionSubida = :idpeticionSubida")
    , @NamedQuery(name = "PeticionSubida.findByEstado", query = "SELECT p FROM PeticionSubida p WHERE  p.estadoIdestado.estado =:valor ORDER BY p.estadoIdestado.fechaGeneracion DESC")
    , @NamedQuery(name = "PeticionSubida.findByEstadoAprobado", query = "SELECT p FROM PeticionSubida p WHERE  p.estadoIdestado.estado =:valor ORDER BY p.estadoIdestado.fechaRevision DESC")
    , @NamedQuery(name = "PeticionSubida.CountByEstado", query = "SELECT COUNT(p.idpeticionSubida) FROM PeticionSubida p WHERE p.estadoIdestado.estado =:valor ")})


public class PeticionSubida implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpeticionSubida")
    private Integer idpeticionSubida;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    
    @JoinColumn(name = "componente_idComponente", referencedColumnName = "idComponente")
    @ManyToOne(optional = false)
    private Componente componenteidComponente;
    
    @JoinColumn(name = "estado_idestado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado estadoIdestado;
    
    @JoinColumn(name = "usuario_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuarioCodigo;
    
    @JoinColumn(name = "adm_codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Usuario admCodigo;

    /**
     * Constructor sin parametros de la clase PeticionSubida
     */
    public PeticionSubida() {
    }

    /**
     * Constructor que recibe la id de la peticion
     * @param idpeticionSubida un entero con la id de la peticion
     */
    public PeticionSubida(Integer idpeticionSubida) {
        this.idpeticionSubida = idpeticionSubida;
    }

    /**
     * 
     * @return un entero con la id de la peticion
     */
    public Integer getIdpeticionSubida() {
        return idpeticionSubida;
    }

    /**
     * 
     * @param idpeticionSubida un entero con la nueva id de la peticion a setear
     */
    public void setIdpeticionSubida(Integer idpeticionSubida) {
        this.idpeticionSubida = idpeticionSubida;
    }

    /**
     * 
     * @return una cadena con las observaciones de la peticion
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * 
     * @param observaciones una cadena con las nuevas observaciones de la peticion a setear
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * 
     * @return una referencia al componente involucrado en la peticion
     */
    public Componente getComponenteidComponente() {
        return componenteidComponente;
    }

    /**
     * 
     * @param componenteidComponente una referencia con el nuevo componente a setear
     */
    public void setComponenteidComponente(Componente componenteidComponente) {
        this.componenteidComponente = componenteidComponente;
    }

    /**
     * 
     * @return una referencia con el estado de la peticion
     */
    public Estado getEstadoIdestado() {
        return estadoIdestado;
    }

    /**
     * 
     * @param estadoIdestado una referencia con el nuevo estado de la peticion
     */
    public void setEstadoIdestado(Estado estadoIdestado) {
        this.estadoIdestado = estadoIdestado;
    }

    /**
     * 
     * @return una referencia con el usuario que realizo la peticion de subida
     */
    public Usuario getUsuarioCodigo() {
        return usuarioCodigo;
    }

    /**
     * 
     * @param usuarioCodigo una referencia con el nuevo usuario de la peticion a setear
     */
    public void setUsuarioCodigo(Usuario usuarioCodigo) {
        this.usuarioCodigo = usuarioCodigo;
    }

    /**
     * 
     * @return una referencia del administrador que revisó la petici´pn
     */
    public Usuario getAdmCodigo() {
        return admCodigo;
    }

    /**
     * 
     * @param admCodigo una referencia con eel nuevo administrador a setear
     */
    public void setAdmCodigo(Usuario admCodigo) {
        this.admCodigo = admCodigo;
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
        hash += (idpeticionSubida != null ? idpeticionSubida.hashCode() : 0);
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
        if (!(object instanceof PeticionSubida)) {
            return false;
        }
        PeticionSubida other = (PeticionSubida) object;
        if ((this.idpeticionSubida == null && other.idpeticionSubida != null) || (this.idpeticionSubida != null && !this.idpeticionSubida.equals(other.idpeticionSubida))) {
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
        return "co.edu.ufps.alexandria.entities.PeticionSubida[ idpeticionSubida=" + idpeticionSubida + " ]";
    }
    
}
