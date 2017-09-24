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
 *
 * Clase que permite el manejo de Metodos para un componente en el sistema
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "metodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metodo.findAll", query = "SELECT m FROM Metodo m")
    , @NamedQuery(name = "Metodo.findByIdmetodo", query = "SELECT m FROM Metodo m WHERE m.idmetodo = :idmetodo")
    , @NamedQuery(name = "Metodo.findByCabecera", query = "SELECT m FROM Metodo m WHERE m.cabecera = :cabecera")
    , @NamedQuery(name = "Metodo.deleteAllByComponent", query = "DELETE FROM Metodo m WHERE m.componenteidComponente = :componente")
    , @NamedQuery(name = "Metodo.findByDescripcion", query = "SELECT m FROM Metodo m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Metodo.findByValorRetorno", query = "SELECT m FROM Metodo m WHERE m.valorRetorno = :valorRetorno")})

public class Metodo implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmetodo")
    private Integer idmetodo;

    @Basic(optional = false)
//    @NotNull
    @Size(min = 0, max = 100, message = "La cabecera no puede tener más de 100 caracteres!")
    @Column(name = "cabecera")
    private String cabecera;

    @Basic(optional = false)
//    @NotNull
//    @Lob
//    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;

    @Size(max = 100, message = "El valor de retorno no puede tener más de 100 caracteres!")
    @Column(name = "valorRetorno")
    private String valorRetorno;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metodoIdmetodo")
    private List<Parametro> parametroList;

    @JoinColumn(name = "componente_idComponente", referencedColumnName = "idComponente")
    @ManyToOne(optional = false)
    private Componente componenteidComponente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metodoIdmetodo")
    private List<Ejemplo> ejemploList;

    /**
     * Constructor sin parametros de la clase Metodo
     */
    public Metodo() {
        this.iniciar();
    }

    /**
     * Constructor que recibe la id del metodo
     *
     * @param idmetodo un entero con la id del metodo
     */
    public Metodo(Integer idmetodo) {
        this.idmetodo = idmetodo;
        this.iniciar();
    }

    /**
     * Constructor con parámetros de la clase Metodo
     *
     * @param idmetodo un entero con la id del metodo
     * @param cabecera una cadena con el nombre del método
     * @param descripcion una cadena con la descripción de que función cumple el
     * método en el componente
     */
    public Metodo(Integer idmetodo, String cabecera, String descripcion) {
        this.idmetodo = idmetodo;
        this.cabecera = cabecera;
        this.descripcion = descripcion;
        this.iniciar();
    }
    
    /**
     * Permite inicializar las propiedades de la clase
     */
    private void iniciar(){
        this.parametroList = new ArrayList<>();
        this.ejemploList = new ArrayList<>();
    }

    /**
     *
     * @return un entero con la id del método
     */
    public Integer getIdmetodo() {
        return idmetodo;
    }

    /**
     *
     * @param idmetodo un entero con la nueva id del método a setear
     */
    public void setIdmetodo(Integer idmetodo) {
        this.idmetodo = idmetodo;
    }

    /**
     *
     * @return una cadena con la cabecera del método
     */
    public String getCabecera() {
        return cabecera;
    }

    /**
     *
     * @param cabecera una con la nueva cabecera del método a setear
     */
    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    /**
     *
     * @return una cadena con la descripcion del metodo
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion una cadena con la nueva descripcion del método
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return una cadena con el valor de retorno para el método
     */
    public String getValorRetorno() {
        return valorRetorno;
    }

    /**
     *
     * @param valorRetorno una cadena con el nuevo valor de retorno para el
     * método
     */
    public void setValorRetorno(String valorRetorno) {
        this.valorRetorno = valorRetorno;
    }

    /**
     *
     * @return un listado con los parámetros del método
     */
    @XmlTransient
    public List<Parametro> getParametroList() {
        return parametroList;
    }

    /**
     *
     * @param parametroList un listado con los nuevos parámetros del método
     */
    public void setParametroList(List<Parametro> parametroList) {
        this.parametroList = parametroList;
    }

    /**
     *
     * @return una referencia al componente relacionado
     */
    public Componente getComponenteidComponente() {
        return componenteidComponente;
    }

    /**
     *
     * @param componenteidComponente una referencia al nuevo componente
     * relacionado
     */
    public void setComponenteidComponente(Componente componenteidComponente) {
        this.componenteidComponente = componenteidComponente;
    }

    /**
     *
     * @return un listado con los ejemplos del método
     */
    @XmlTransient
    public List<Ejemplo> getEjemploList() {
        return ejemploList;
    }

    /**
     *
     * @param ejemploList un listado con los nuevos ejemplos para el método
     */
    public void setEjemploList(List<Ejemplo> ejemploList) {
        this.ejemploList = ejemploList;
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
        hash += (idmetodo != null ? idmetodo.hashCode() : 0);
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
        final Metodo other = (Metodo) obj;

        if (this.cabecera.equals(other.cabecera) && Objects.equals(this.parametroList, other.parametroList)) {
            return true;
        }

        return false;
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
        return "Metodo{" + "idmetodo=" + idmetodo + ", cabecera=" + cabecera + ", descripcion=" + descripcion + ", valorRetorno=" + valorRetorno + '}';
    }

    /**
     * Permite clonar un método
     *
     * @return una referencia al método clonado.
     */
    @Override
    public Metodo clone() {
        Metodo clon = new Metodo();

        clon.setComponenteidComponente(componenteidComponente);
        clon.setCabecera(cabecera);
        clon.setDescripcion(descripcion);
        clon.setValorRetorno(valorRetorno);
        clon.setIdmetodo(idmetodo);

        ArrayList<Ejemplo> ejemplosClon = new ArrayList<>();
        for (Ejemplo e : ejemploList) {
            ejemplosClon.add(e.clone());
        }
        clon.setEjemploList(ejemplosClon);
//        
        ArrayList<Parametro> parametrosClon = new ArrayList<>();
        for (Parametro parametro : parametroList) {
            parametrosClon.add(parametro.clone());
        }
//        
////        System.out.println("Parametros clonados: "+parametrosClon);
        clon.setParametroList(parametrosClon);

        return clon;
    }

}
