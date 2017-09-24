package co.edu.ufps.colossal.entities;

import java.io.Serializable;
import java.util.Objects;
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
 * Clase que permite manejar un parametro de un metodo en el sistema
 *
 * @author Jhon Vargas
 */
@Entity
@Table(name = "parametro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p")
    , @NamedQuery(name = "Parametro.findByIdparametro", query = "SELECT p FROM Parametro p WHERE p.idparametro = :idparametro")
    , @NamedQuery(name = "Parametro.findByParametro", query = "SELECT p FROM Parametro p WHERE p.parametro = :parametro")
    , @NamedQuery(name = "Parametro.findByTipo", query = "SELECT p FROM Parametro p WHERE p.tipo = :tipo")
    , @NamedQuery(name = "Parametro.findByValorOpcional", query = "SELECT p FROM Parametro p WHERE p.valorOpcional = :valorOpcional")})
public class Parametro implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idparametro")
    private Integer idparametro;

    @Size(max = 45, message = "El nombre del parámetro no puede tener más de 45 caracteres")
    @Column(name = "parametro")
    private String parametro;

    @Size(max = 45, message = "El tipo del parámetro no puede tener más de 45 caracteres")
    @Column(name = "tipo")
    private String tipo;

    @Lob
    @Size(max = 2147483647)
    @Column(name = "detalle")
    private String detalle;

    @Column(name = "valorOpcional")
    private Short valorOpcional;

    @JoinColumn(name = "metodo_idmetodo", referencedColumnName = "idmetodo")
    @ManyToOne(optional = false)
    private Metodo metodoIdmetodo;

    /**
     * Constructor sin parametros de la clase Parametro
     */
    public Parametro() {
        this.iniciar();
    }

    /**
     * Contructor que recibe la id del parametro
     *
     * @param idparametro la id del parametro a crear
     */
    public Parametro(Integer idparametro) {
        this.idparametro = idparametro;
        this.iniciar();
    }

    /**
     * Permite inicializar las propiedades de la clase Parametro
     */
    private void iniciar() {
        this.valorOpcional = 0;
    }

    /**
     *
     * @return un entero con la ide del parametro
     */
    public Integer getIdparametro() {
        return idparametro;
    }

    /**
     *
     * @param idparametro un entero con la id del parametro a setear
     */
    public void setIdparametro(Integer idparametro) {
        this.idparametro = idparametro;
    }

    /**
     *
     * @return una cadena con el nombre del parametro
     */
    public String getParametro() {
        return parametro;
    }

    /**
     *
     * @param parametro una cadena con el nuevo nombre del parametro a setear
     */
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    /**
     *
     * @return una cadena con el tipo del parametro
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo una cadena con el nuevo tipo del parametro a setear
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return una cadena con los detalles del parametro
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     *
     * @param detalle una cadena con los nuevos detalles del parametro
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     *
     * @return un entero indicando si el parametro es opcional o no.
     */
    public Short getValorOpcional() {
        return valorOpcional;
    }

    /**
     *
     * @param valorOpcional un entero con el nuevo valorOpcional para el
     * parametro
     */
    public void setValorOpcional(Short valorOpcional) {
        this.valorOpcional = valorOpcional;
    }

    /**
     *
     * @return una referencia al metodo al cual pertenece el parametro
     */
    public Metodo getMetodoIdmetodo() {
        return metodoIdmetodo;
    }

    /**
     *
     * @param metodoIdmetodo una referencia con el nuevo metodo al cual
     * pertenece el parametro a setear
     */
    public void setMetodoIdmetodo(Metodo metodoIdmetodo) {
        this.metodoIdmetodo = metodoIdmetodo;
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
        hash += (idparametro != null ? idparametro.hashCode() : 0);
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
        final Parametro other = (Parametro) obj;
        if (!Objects.equals(this.parametro, other.parametro)) {
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
        return "{parametro=" + parametro + '}';
    }

    /**
     * Permite clonar el objeto en otro exactamente igual.
     * @return una referencia del parametro clonado.
     */
    @Override
    public Parametro clone() {
        Parametro clon = new Parametro();
        clon.setIdparametro(idparametro);
        clon.setParametro(parametro);
        clon.setDetalle(detalle);
        clon.setTipo(tipo);
        clon.setValorOpcional(valorOpcional);
        clon.setMetodoIdmetodo(metodoIdmetodo);
        return clon;
    }

}
