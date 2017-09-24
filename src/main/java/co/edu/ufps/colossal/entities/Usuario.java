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
 *
 * Clase que permite manejar usuarios en el sistema
 * @author Jhon Vargas
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCodigo", query = "SELECT u FROM Usuario u WHERE u.codigo = :codigo")
    , @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByUsernamePass", query = "SELECT u FROM Usuario u WHERE u.username=:username AND u.password = :password")
    , @NamedQuery(name = "Usuario.findByTipoUsuario", query = "SELECT u FROM Usuario u WHERE u.tipoUsuario = :tipoUsuario")})

public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioCodigo")
    private List<Token> tokenList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = "El username no puede tener más de 45 caracteres!")
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = "El password no puede tener más de 45 caracteres!")
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = "El nombre no puede tener más de 45 caracteres!")
    @Column(name = "nombre")
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100, message = "El email no puede tener más de 100 caracteres!")
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipoUsuario")
    private short tipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioReceptor")
    private List<Notificacion> notificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioCodigo")
    private List<Incidencia> incidenciaList;
    @OneToMany(mappedBy = "admCodigo")
    private List<Incidencia> incidenciaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariocodigo")
    private List<ListaComponente> listaComponenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioCodigo")
    private List<PeticionSubida> peticionSubidaList;
    @OneToMany(mappedBy = "admCodigo")
    private List<PeticionSubida> peticionSubidaList1;

    /**
     * Constructor sin parametros de la clase Usuario
     */
    public Usuario() {
    }

    /**
     * Constructor que recibe el codigo del usuario
     *
     * @param codigo
     */
    public Usuario(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Contructor con parametros de la clase Usuario
     *
     * @param codigo un entero con el codigo del usuario
     * @param username una cadena con el username del usuario
     * @param password una cadena con la contraseña del usuario
     * @param nombre una cadena con el nombre completo del usuario
     * @param email una cadena con el email del usuario
     * @param tipoUsuario un entero con el tipo del usuario
     */
    public Usuario(Integer codigo, String username, String password, String nombre, String email, short tipoUsuario) {
        this.codigo = codigo;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    /**
     *
     * @return un entero con el codigo del usuario
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo un entero con el codigo a setear
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * 
     * @return una cadena con el username del usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username una cadena con el username a setear
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return una cadena con el password del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password una cadena con el nuevo password del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return una cadena con el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre una cadena con el nuevo nombre a setear
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return una cadena con el email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email una cadena con el nuevo email a setear
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return un entero con el tipo de usuario
     */
    public short getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * 
     * @param tipoUsuario un entero con el nuevo tipo a setear
     */
    public void setTipoUsuario(short tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * 
     * @return un listado con las notificaciones del usuario.
     */
    @XmlTransient
    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    /**
     * 
     * @param notificacionList un listado con las notificaciones a setear
     */
    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    /**
     * 
     * @return un listado con las incidencias creadas por el usuario
     */
    @XmlTransient
    public List<Incidencia> getIncidenciaList() {
        return incidenciaList;
    }

    /**
     * 
     * @param incidenciaList una lista con las nuevas incidencias a setear
     */
    public void setIncidenciaList(List<Incidencia> incidenciaList) {
        this.incidenciaList = incidenciaList;
    }

    /**
     * 
     * @return un listado con las incidencias para las cuales el usuario optó por administrar
     */
    @XmlTransient
    public List<Incidencia> getIncidenciaList1() {
        return incidenciaList1;
    }

    /**
     * 
     * @param incidenciaList1 un listado con las nuevas incidencias a setear
     */
    public void setIncidenciaList1(List<Incidencia> incidenciaList1) {
        this.incidenciaList1 = incidenciaList1;
    }

    /**
     * 
     * @return un listado con las listas creadas por el usuario
     */
    @XmlTransient
    public List<ListaComponente> getListaComponenteList() {
        return listaComponenteList;
    }

    /**
     * 
     * @param listaComponenteList un listado con las nuevas listas a setear
     */
    public void setListaComponenteList(List<ListaComponente> listaComponenteList) {
        this.listaComponenteList = listaComponenteList;
    }

    /**
     * 
     * @return un listado de las peticiones creadas por el usuario
     */
    @XmlTransient
    public List<PeticionSubida> getPeticionSubidaList() {
        return peticionSubidaList;
    }

    /**
     * 
     * @param peticionSubidaList un listado con las nuevas peticiones a setear
     */
    public void setPeticionSubidaList(List<PeticionSubida> peticionSubidaList) {
        this.peticionSubidaList = peticionSubidaList;
    }

    /**
     * 
     * @return un listado con las peticiones para las cuales el presente usuario administró.
     */
    @XmlTransient
    public List<PeticionSubida> getPeticionSubidaList1() {
        return peticionSubidaList1;
    }
 
    /**
     * 
     * @param peticionSubidaList1 un listado con las nuevas notificaciones a setear
     */
    public void setPeticionSubidaList1(List<PeticionSubida> peticionSubidaList1) {
        this.peticionSubidaList1 = peticionSubidaList1;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
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
        return "Usuario{" + "codigo=" + codigo + ", username=" + username + ", password=" + password + ", nombre=" + nombre + ", email=" + email + ", tipoUsuario=" + tipoUsuario + '}';
    }

    
    

    @XmlTransient
    public List<Token> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

}
