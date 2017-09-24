/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
 * Clase que permite representar un componente en el sistema
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "componente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Componente.findAll", query = "SELECT c FROM Componente c")
    , @NamedQuery(name = "Componente.findComponentsLikeNombre", query = "SELECT c FROM Componente c, PeticionSubida p, Estado e"
            + " WHERE (c.nombre LIKE :nombre"
            + " AND p.usuarioCodigo.codigo = :usuarioCodigo"
            + " AND p.componenteidComponente.idComponente=c.idComponente"
            + " AND p.estadoIdestado.idestado=e.idestado"
            + " AND e.estado IN ('Aprobado','Pendiente') )"
            + " OR (c.nombre LIKE :nombre"
            + " AND p.usuarioCodigo.codigo <> :usuarioCodigo"
            + " AND p.componenteidComponente.idComponente=c.idComponente"
            + " AND p.estadoIdestado.idestado=e.idestado"
            + " AND e.estado='Aprobado')")

    , @NamedQuery(name = "Componente.findComponentsLikeNombreAprobados", query = "SELECT c FROM Componente c, PeticionSubida p, Estado e"
            + " WHERE (c.nombre LIKE :nombre"
            + " AND p.componenteidComponente.idComponente=c.idComponente"
            + " AND p.estadoIdestado.idestado=e.idestado"
            + " AND c.idComponente <> :idComponente"
            + " AND e.estado='Aprobado')")

    , @NamedQuery(name = "Componente.findByIdComponente", query = "SELECT c FROM Componente c,PeticionSubida p "
            + " WHERE p.componenteidComponente.idComponente=c.idComponente "
            + " and c.idComponente = :idComponente")
    , @NamedQuery(name = "Componente.findByNombre", query = "SELECT c FROM Componente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Componente.findByVersion", query = "SELECT c FROM Componente c WHERE c.version = :version")
    , @NamedQuery(name = "Componente.findByEstado", query = "SELECT  c FROM Componente c,PeticionSubida p,Estado e"
            + " WHERE c.idComponente=p.componenteidComponente.idComponente  "
            + "and p.estadoIdestado.idestado=e.idestado and e.estado=:valor "
            + "and p.usuarioCodigo.codigo=:codigo ORDER BY c.idComponente DESC")
    , @NamedQuery(name = "Componente.findByEstadoPendiente", query = "SELECT  c FROM Componente c,PeticionSubida p,Estado e"
            + " WHERE c.idComponente=p.componenteidComponente.idComponente  "
            + "and p.estadoIdestado.idestado=e.idestado and e.estado=:valor "
            + "and p.usuarioCodigo.codigo=:codigo ORDER BY c.idComponente DESC")
    , @NamedQuery(name = "Componente.CountByEstado", query = "SELECT  COUNT(c.idComponente) FROM Componente c,PeticionSubida p,Estado e"
            + " WHERE c.idComponente=p.componenteidComponente.idComponente  "
            + "and p.estadoIdestado.idestado=e.idestado and e.estado=:valor "
            + "and p.usuarioCodigo.codigo=:codigo ORDER BY c.idComponente DESC")
    , @NamedQuery(name = "Componente.findByRecently", query = "SELECT  c FROM Componente c,PeticionSubida p,Estado e WHERE c.idComponente=p.componenteidComponente.idComponente  and p.estadoIdestado.idestado=e.idestado and e.estado=:valor ORDER BY e.fechaRevision DESC")
    , @NamedQuery(name = "Componente.findByString", query = "SELECT c FROM Componente c,PeticionSubida p,Estado e "
            + " WHERE (c.nombre like :cadena or c.descripcion like :cadena or c.tecnologia like :cadena) "
            + " and c.idComponente=p.componenteidComponente.idComponente "
            + " and p.estadoIdestado.idestado=e.idestado and e.estado=:valor ORDER BY e.fechaRevision DESC")
    , @NamedQuery(name = "Componente.countByString", query = "SELECT COUNT(c.idComponente) FROM Componente c,PeticionSubida p,Estado e"
            + " WHERE (c.nombre like :cadena or c.descripcion like :cadena) "
            + " and c.idComponente=p.componenteidComponente.idComponente "
            + " and p.estadoIdestado.idestado=e.idestado and e.estado=:valor ")
    , @NamedQuery(name = "Componente.findByCategory", query = "SELECT c FROM PeticionSubida p,Estado e,Componente c,CategoriaHasComponente ca, Categoria cat "
            + " WHERE c.idComponente = ca.componente.idComponente "
            + " and (c.nombre like :cadena or c.descripcion like :cadena) "
            + " and c.idComponente=p.componenteidComponente.idComponente "
            + " and p.estadoIdestado.idestado=e.idestado and e.estado=:valor "
            + " and ca.categoria.idcategoria = cat.idcategoria "
            + " and cat.idcategoria = :categoria ORDER BY e.fechaRevision DESC")
    , @NamedQuery(name = "Componente.findByTag", query = "SELECT c FROM Componente c,PeticionSubida p,Estado e,ComponenteHasTag cht,Tag t "
            + " WHERE c.idComponente=p.componenteidComponente.idComponente "
            + " and (c.nombre like :cadena or c.descripcion like :cadena) "
            + " and p.estadoIdestado.idestado=e.idestado and e.estado=:valor "
            + " and c.idComponente=cht.componente.idComponente and t.nombre like :tag "
            + " and t.idtag = cht.tag.idtag ORDER BY e.fechaRevision DESC")
    , @NamedQuery(name = "Componente.CountComponentsByTag", query = "SELECT COUNT(c.idComponente) FROM Componente c,PeticionSubida p,Estado e,ComponenteHasTag cht,Tag t"
            + " WHERE c.idComponente=p.componenteidComponente.idComponente "
            + " and (c.nombre like :cadena or c.descripcion like :cadena) "
            + " and p.estadoIdestado.idestado=e.idestado and e.estado=:valor "
            + " and c.idComponente=cht.componente.idComponente and t.nombre like :tag "
            + " and t.idtag = cht.tag.idtag")
    , @NamedQuery(name = "Componente.CountComponentsByCategory", query = "SELECT COUNT(c.idComponente) FROM PeticionSubida p,Estado e,Componente c,CategoriaHasComponente ca, Categoria cat "
            + "WHERE c.idComponente = ca.componente.idComponente "
            + " and (c.nombre like :cadena or c.descripcion like :cadena) "
            + " and c.idComponente=p.componenteidComponente.idComponente "
            + " and p.estadoIdestado.idestado=e.idestado and e.estado=:valor "
            + " and ca.categoria.idcategoria = cat.idcategoria "
            + " and cat.idcategoria = :categoria ")

    , @NamedQuery(name = "Componente.findByIdLista", query = "SELECT c FROM Componente c,PeticionSubida p,Estado e,ListaComponentehasComponente lh "
            + " WHERE lh.listaComponente.usuariocodigo.codigo=:codigo "
            + " and lh.listaComponente.idlistaComponente=:idlista and lh.componente.idComponente=c.idComponente "
            + " and c.idComponente=p.componenteidComponente.idComponente "
            + " and p.estadoIdestado.idestado=e.idestado and e.estado=:valor ORDER BY e.fechaRevision DESC")
    , @NamedQuery(name = "Componente.CountByIdLista", query = "SELECT COUNT(c) FROM Componente c,PeticionSubida p,Estado e,ListaComponentehasComponente lh "
            + " WHERE lh.listaComponente.usuariocodigo.codigo=:codigo "
            + " and lh.listaComponente.idlistaComponente=:idlista and lh.componente.idComponente=c.idComponente "
            + " and c.idComponente=p.componenteidComponente.idComponente "
            + " and p.estadoIdestado.idestado=e.idestado and e.estado=:valor ")})

public class Componente implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "visualizaciones")
    private int visualizaciones;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "tecnologia")
    private String tecnologia;
    @Basic(optional = false)
    @NotNull(message = "El grado de reutilización no puede ser nulo")
    @Column(name = "gradoReutilizacion")
    private int gradoReutilizacion;
    @OneToMany(mappedBy = "componenteOwner")
    private List<ComponenteHasDependency> componenteHasDependencyList;
    @OneToMany(mappedBy = "componenteDependency")
    private List<ComponenteHasDependency> componenteHasDependencyList1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idComponente")
    private Integer idComponente;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "instrucciones")
    private String instrucciones;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = "La versión del componente no puede tener más de 45 caracteres")
    @Column(name = "version")
    private String version;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componenteidComponente")
    private List<Recurso> recursoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componenteidComponente")
    private List<Notificacion> notificacionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componenteidComponente")
    private List<Incidencia> incidenciaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componente")
    private List<ListaComponentehasComponente> listaComponentehasComponenteList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componenteidComponente")
    private List<Revision> revisionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componente")
    private List<CategoriaHasComponente> categoriaHasComponenteList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componente")
    private List<ComponenteHasTag> componenteHasTagList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componenteidComponente")
    private List<PeticionSubida> peticionSubidaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componenteidComponente")
    private List<Metodo> metodoList;

    /**
     * Constructor vacio de la clase Componente
     */
    public Componente() {
        this.iniciar();
    }

    /**
     * Constructor con parámetros de la clase Componente
     *
     * @param idComponente un entero con la id del componente
     */
    public Componente(Integer idComponente) {
        this.idComponente = idComponente;
        this.iniciar();
    }

    /**
     * Constructor con parámetros de la clase Componente
     *
     * @param idComponente un entero con la id del componente
     * @param nombre una cadena con el nombre del componente
     * @param descripcion una cadena con la descripcion del componente
     * @param instrucciones una cadena con las instrucciones de como utilizar el
     * componente, incluidas las pre y post condiciones
     * @param version una cadena con la version del componente
     */
    public Componente(Integer idComponente, String nombre, String descripcion, String instrucciones, String version) {
        this.idComponente = idComponente;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.instrucciones = instrucciones;
        this.version = version;

        this.iniciar();
    }

    /**
     * Permite inicializar las propiedades de la clase Componente
     */
    private void iniciar() {
        this.recursoList = new ArrayList<>();
        this.componenteHasTagList = new ArrayList<>();
        this.peticionSubidaList = new ArrayList<>();
        this.incidenciaList = new ArrayList<>();
        this.componenteHasDependencyList = new ArrayList<>();
        this.componenteHasDependencyList1 = new ArrayList<>();
    }

    /**
     *
     * @return un entero con la id del componente
     */
    public Integer getIdComponente() {
        return idComponente;
    }

    /**
     *
     * @param idComponente un entero con la nueva id del componente
     */
    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    /**
     *
     * @return una cadena con el nombre del componente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre una cadena con el nuevo nombre del componente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return una cadena con la descripcion del componente
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion una cadena con la nueva descripcion del componente
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return una cadena con las instrucciones de como utilizar el componente,
     * incluidas las pre y post condiciones
     */
    public String getInstrucciones() {
        return instrucciones;
    }

    /**
     *
     * @param instrucciones una cadena con las nuevas instrucciones de como
     * utilizar el componente, incluidas las pre y post condiciones
     */
    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    /**
     *
     * @return una cadena con la version del componente
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version una cadena con la nueva version del componente
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return un listado con los recursos asociados del componente
     */
    @XmlTransient
    public List<Recurso> getRecursoList() {
        return recursoList;
    }

    /**
     *
     * @param recursoList un listado con los nuevos recursos asociados del
     * componente
     */
    public void setRecursoList(List<Recurso> recursoList) {
        this.recursoList = recursoList;
    }

    /**
     *
     * @return un listado de las notificaciones asociadas al componente
     */
    @XmlTransient
    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    /**
     *
     * @param notificacionList un listado de las nuevas notificaciones asociadas
     * al componente
     */
    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    /**
     *
     * @return un listado con las incidencias reportadas del componente
     */
    @XmlTransient
    public List<Incidencia> getIncidenciaList() {
        return incidenciaList;
    }

    /**
     *
     * @param incidenciaList un listado con las nuevas incidencias reportadas
     * del componente
     */
    public void setIncidenciaList(List<Incidencia> incidenciaList) {
        this.incidenciaList = incidenciaList;
    }

    /**
     *
     * @return un listado con las listaComponentehasComponente del componente
     */
    @XmlTransient
    public List<ListaComponentehasComponente> getListaComponentehasComponenteList() {
        return listaComponentehasComponenteList;
    }

    /**
     *
     * @param listaComponentehasComponenteList un listado con las nuevas
     * listaComponentehasComponente del componente
     */
    public void setListaComponentehasComponenteList(List<ListaComponentehasComponente> listaComponentehasComponenteList) {
        this.listaComponentehasComponenteList = listaComponentehasComponenteList;
    }

    /**
     *
     * @return un listado con las revisiones del componente
     */
    @XmlTransient
    public List<Revision> getRevisionList() {
        return revisionList;
    }

    /**
     *
     * @param revisionList un listado con las revisiones nuevas del componente
     */
    public void setRevisionList(List<Revision> revisionList) {
        this.revisionList = revisionList;
    }

    /**
     *
     * @return un listado con las categoriaHasComponente del componente
     */
    @XmlTransient
    public List<CategoriaHasComponente> getCategoriaHasComponenteList() {
        return categoriaHasComponenteList;
    }

    /**
     *
     * @param categoriaHasComponenteList un listado con las nuevas
     * categoriaHasComponente del componente
     */
    public void setCategoriaHasComponenteList(List<CategoriaHasComponente> categoriaHasComponenteList) {
        this.categoriaHasComponenteList = categoriaHasComponenteList;
    }

    @XmlTransient
    public List<ComponenteHasTag> getComponenteHasTagList() {
        return componenteHasTagList;
    }

    public void setComponenteHasTagList(List<ComponenteHasTag> componenteHasTagList) {
        this.componenteHasTagList = componenteHasTagList;
    }

    /**
     *
     * @return un listado con las peticiones de subida asociadas al componente
     */
    @XmlTransient
    public List<PeticionSubida> getPeticionSubidaList() {
        return peticionSubidaList;
    }

    /**
     *
     * @param peticionSubidaList un listado con las nuevas peticiones de subida
     * asociadas al componente
     */
    public void setPeticionSubidaList(List<PeticionSubida> peticionSubidaList) {
        this.peticionSubidaList = peticionSubidaList;
    }

    /**
     *
     * @return un listado con los métodos del componente
     */
    @XmlTransient
    public List<Metodo> getMetodoList() {
        return metodoList;
    }

    /**
     *
     * @param metodoList un listado con los nuevos métodos del componente
     */
    public void setMetodoList(List<Metodo> metodoList) {
        this.metodoList = metodoList;
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
        hash += (idComponente != null ? idComponente.hashCode() : 0);
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
        final Componente other = (Componente) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
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
        return "Componente{" + "idComponente=" + idComponente + ", nombre=" + nombre + '}';
    }

    /**
     * 
     * @return una cadena con la tecnologia subyacente del componente
     */
    public String getTecnologia() {
        return tecnologia;
    }

    /**
     * 
     * @param tecnologia una cadena con la nueva tecnologia del componente
     */
    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    /**
     * 
     * @return un entero con el grado de reutilizacion del componente
     */
    public int getGradoReutilizacion() {
        return gradoReutilizacion;
    }

    /**
     * 
     * @param gradoReutilizacion un entero con el nuevo grado de reutilizacion del componente
     */
    public void setGradoReutilizacion(int gradoReutilizacion) {
        this.gradoReutilizacion = gradoReutilizacion;
    }

    @XmlTransient
    public List<ComponenteHasDependency> getComponenteHasDependencyList() {
        return componenteHasDependencyList;
    }

    public void setComponenteHasDependencyList(List<ComponenteHasDependency> componenteHasDependencyList) {
        this.componenteHasDependencyList = componenteHasDependencyList;
    }

    @XmlTransient
    public List<ComponenteHasDependency> getComponenteHasDependencyList1() {
        return componenteHasDependencyList1;
    }

    public void setComponenteHasDependencyList1(List<ComponenteHasDependency> componenteHasDependencyList1) {
        this.componenteHasDependencyList1 = componenteHasDependencyList1;
    }

    /**
     * 
     * @return un entero con indicando el numero de visualizaciones del componente
     */
    public int getVisualizaciones() {
        return visualizaciones;
    }

    /**
     * 
     * @param visualizaciones un entero con indicando el nuevo numero de visualizaciones del componente
     */
    public void setVisualizaciones(int visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

}
