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
 * Clase que permite manejar las notificaciones en el sistema
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "Notificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n")
    , @NamedQuery(name = "Notificacion.findByIdNotificacion", query = "SELECT n FROM Notificacion n WHERE n.idNotificacion = :idNotificacion")
    , @NamedQuery(name = "Notificacion.findNotifications", query = "SELECT n FROM Notificacion n WHERE n.usuarioReceptor.codigo = :codigo and n.estadoIdestado.estado like :valor ORDER BY  n.estadoIdestado.estado ASC,n.estadoIdestado.fechaGeneracion DESC")
    , @NamedQuery(name = "Notificacion.CountNotifications", query = "SELECT COUNT(n.idNotificacion) FROM Notificacion n WHERE n.usuarioReceptor.codigo = :codigo and n.estadoIdestado.estado like :valor ")})
public class Notificacion implements Serializable {

    @Lob
    @Size(max = 65535)
    @Column(name = "asunto")
    private String asunto;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNotificacion")
    private Integer idNotificacion;
    @Lob
    @Size(max = 65535)
    @Column(name = "mensaje")
    private String mensaje;
    @JoinColumn(name = "estado_idestado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado estadoIdestado;
    @JoinColumn(name = "usuario_receptor", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuarioReceptor;
    @JoinColumn(name = "componente_idComponente", referencedColumnName = "idComponente")
    @ManyToOne(optional = false)
    private Componente componenteidComponente;

    /**
     * Constructor vacio de la clase Notificacion
     */
    public Notificacion() {
    }

    /**
     * Constructor que recibe la id de la notificacion
     *
     * @param idNotificacion un entero con la id de la notificacion
     */
    public Notificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    /**
     *
     * @return un entero con la id de la notificacion
     */
    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    /**
     *
     * @param idNotificacion un entero con la nueva id de la notificacion a
     * setear
     */
    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    /**
     *
     * @return una cadena con el mensajde de la notificacion
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     *
     * @param mensaje una cadena con el nuevo mensaje de la notificacion a
     * setear
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     *
     * @return una referencia con el estado de la notificacion
     */
    public Estado getEstadoIdestado() {
        return estadoIdestado;
    }

    /**
     *
     * @param estadoIdestado una referencia con el nuevo estado de la
     * notificacion a setear
     */
    public void setEstadoIdestado(Estado estadoIdestado) {
        this.estadoIdestado = estadoIdestado;
    }

    /**
     *
     * @return una referencia al usuario al que va dirijida la notificacion
     */
    public Usuario getUsuarioReceptor() {
        return usuarioReceptor;
    }

    /**
     *
     * @param usuarioReceptor una referencia con el nuevo usuario de la
     * notificacion
     */
    public void setUsuarioReceptor(Usuario usuarioReceptor) {
        this.usuarioReceptor = usuarioReceptor;
    }

    /**
     *
     * @return una referencia del componente involucrado en la notificacion
     */
    public Componente getComponenteidComponente() {
        return componenteidComponente;
    }

    /**
     *
     * @param componenteidComponente una referencia con el nuevo componente de
     * la notificacion.
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
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
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
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
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
        return "co.edu.ufps.alexandria.entities.Notificacion[ idNotificacion=" + idNotificacion + " ]";
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

}
