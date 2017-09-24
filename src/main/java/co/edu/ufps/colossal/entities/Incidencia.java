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
 * Clase que permite manejar las incidencias que puedan tener los componentes.
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "incidencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidencia.findAll", query = "SELECT i FROM Incidencia i")
    , @NamedQuery(name = "Incidencia.findByComponent", query = "SELECT i FROM Incidencia i WHERE i.componenteidComponente.idComponente=:idcomponente and i.estadoIdestado.estado=:valor ORDER BY i.estadoIdestado.fechaGeneracion ASC ")
    , @NamedQuery(name = "Incidencia.findByStatus", query = "SELECT i FROM Incidencia i WHERE i.estadoIdestado.estado = :valor ORDER BY i.estadoIdestado.fechaGeneracion DESC")
    , @NamedQuery(name = "Incidencia.findByStatusRevisado", query = "SELECT i FROM Incidencia i WHERE i.estadoIdestado.estado = :valor ORDER BY i.estadoIdestado.fechaRevision DESC")
    , @NamedQuery(name = "Incidencia.CountByStatus", query = "SELECT COUNT(i.usuarioCodigo.codigo) FROM Incidencia i WHERE i.estadoIdestado.estado = :valor ")
    , @NamedQuery(name = "Incidencia.findByIdincidencia", query = "SELECT i FROM Incidencia i WHERE i.idincidencia = :idincidencia")})

public class Incidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idincidencia")
    private Integer idincidencia;

    @Basic(optional = false)
//    @NotNull
    @Lob
    @Size(min = 0, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;

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
     * Constructor vacio de la clase Incidencia
     */
    public Incidencia() {
    }

    /**
     * Constructor con parámetros de la clase incidencia
     *
     * @param idincidencia un entero con la id de la incidencia
     * @param descripcion una cadena con la descripcion de la incidencia
     */
    public Incidencia(Integer idincidencia, String descripcion) {
        this.idincidencia = idincidencia;
        this.descripcion = descripcion;
    }

    /**
     *
     * @return un entero con la id de la incidencia
     */
    public Integer getIdincidencia() {
        return idincidencia;
    }

    /**
     *
     * @param idincidencia un entero con la nueva id de la incidencia
     */
    public void setIdincidencia(Integer idincidencia) {
        this.idincidencia = idincidencia;
    }

    /**
     *
     * @return una cadena con la descripcion de la incidencia
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion una cadena con la nueva descripcion de la incidencia
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return una referencia con el componente relacionado
     */
    public Componente getComponenteidComponente() {
        return componenteidComponente;
    }

    /**
     *
     * @param componenteidComponente una referencia con el nuevo componente
     * relacionado a la incidencia
     */
    public void setComponenteidComponente(Componente componenteidComponente) {
        this.componenteidComponente = componenteidComponente;
    }

    /**
     *
     * @return una referencia con el estado del componente
     */
    public Estado getEstadoIdestado() {
        return estadoIdestado;
    }

    /**
     *
     * @param estadoIdestado una referencia con el nuevo estado del componente
     */
    public void setEstadoIdestado(Estado estadoIdestado) {
        this.estadoIdestado = estadoIdestado;
    }

    /**
     *
     * @return una referencia de tipo Usuario con el usuario que creo la
     * incidencia
     */
    public Usuario getUsuarioCodigo() {
        return usuarioCodigo;
    }

    /**
     *
     * @param usuarioCodigo una referencia de tipo Usuario con el nuevo usuario
     * que creo la incidencia
     */
    public void setUsuarioCodigo(Usuario usuarioCodigo) {
        this.usuarioCodigo = usuarioCodigo;
    }

    /**
     *
     * @return una referencia de tipo Usuario al administrador que revisó la
     * incidencia
     */
    public Usuario getAdmCodigo() {
        return admCodigo;
    }

    /**
     *
     * @param admCodigo una referencia de tipo Usuario al administrador que
     * revisó la incidencia a setear
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
        hash += (idincidencia != null ? idincidencia.hashCode() : 0);
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
        if (!(object instanceof Incidencia)) {
            return false;
        }
        Incidencia other = (Incidencia) object;
        if ((this.idincidencia == null && other.idincidencia != null) || (this.idincidencia != null && !this.idincidencia.equals(other.idincidencia))) {
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
        return "co.edu.ufps.alexandria.entities.Incidencia[ idincidencia=" + idincidencia + " ]";
    }

}
