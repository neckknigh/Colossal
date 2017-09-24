/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Clase que permite representar un estado en el sistema
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")
    , @NamedQuery(name = "Estado.findByIdestado", query = "SELECT e FROM Estado e WHERE e.idestado = :idestado")
    , @NamedQuery(name = "Estado.findByFechaGeneracion", query = "SELECT e FROM Estado e WHERE e.fechaGeneracion = :fechaGeneracion")
    , @NamedQuery(name = "Estado.findByFechaRevision", query = "SELECT e FROM Estado e WHERE e.fechaRevision = :fechaRevision")
    , @NamedQuery(name = "Estado.findByEstado", query = "SELECT e FROM Estado e WHERE e.estado = :estado")})

public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado")
    private Integer idestado;
    @Column(name = "fechaGeneracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion;
    @Column(name = "fechaRevision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdestado")
    private List<Notificacion> notificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdestado")
    private List<Incidencia> incidenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdestado")
    private List<PeticionSubida> peticionSubidaList;

    /**
     * Constrcutor sin parámetros de la clase Estado
     */
    public Estado() {
    }

    /**
     * Constructor con parámetros de la clase Estado
     *
     * @param idestado un entero con la id del estado
     * @param estado una cadena describiendo el estado
     */
    public Estado(Integer idestado, String estado) {
        this.idestado = idestado;
        this.estado = estado;
    }

    /**
     *
     * @return un entero con la id del estado
     */
    public Integer getIdestado() {
        return idestado;
    }

    /**
     *
     * @param idestado una cadena con la nueva id del estado
     */
    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    /**
     *
     * @return una fecha con el momento exacto de creación del estado
     */
    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    /**
     *
     * @param fechaGeneracion una fecha nueva con el momento exacto de creacion
     * del estado
     */
    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    /**
     *
     * @return una fecha con el momento en que el estado fué revisado por un
     * administrador.
     */
    public Date getFechaRevision() {
        return fechaRevision;
    }

    /**
     *
     * @param fechaRevision una fecha con el nuevo momento en que el estado fué
     * revisado por un administrador.
     */
    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    /**
     *
     * @return una cadena describiendo el estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     * @param estado una nueva cadena descrbiendo el estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     *
     * @return un listado de notificaciones relacionadas con el estado
     */
    @XmlTransient
    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    /**
     *
     * @param notificacionList un listado con las nuevas notificaciones
     * relacionadas con el estado
     */
    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    /**
     *
     * @return un listado con las incidencias relacionadas con el estado
     */
    @XmlTransient
    public List<Incidencia> getIncidenciaList() {
        return incidenciaList;
    }

    /**
     *
     * @param incidenciaList un listado con las nuevas incidencias relacionadas
     * con el estado
     */
    public void setIncidenciaList(List<Incidencia> incidenciaList) {
        this.incidenciaList = incidenciaList;
    }

    /**
     *
     * @return un listado con las incidencias relacionadas con el estado
     */
    @XmlTransient
    public List<PeticionSubida> getPeticionSubidaList() {
        return peticionSubidaList;
    }

    /**
     *
     * @param peticionSubidaList un listado con las nuevas peticiones
     * relacionadas con el estado
     */
    public void setPeticionSubidaList(List<PeticionSubida> peticionSubidaList) {
        this.peticionSubidaList = peticionSubidaList;
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
        hash += (idestado != null ? idestado.hashCode() : 0);
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
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idestado == null && other.idestado != null) || (this.idestado != null && !this.idestado.equals(other.idestado))) {
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
        return "co.edu.ufps.alexandria.entities.Estado[ idestado=" + idestado + " ]";
    }

}
