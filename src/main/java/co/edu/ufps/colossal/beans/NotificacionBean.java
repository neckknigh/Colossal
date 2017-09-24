/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Notificacion;
import co.edu.ufps.colossal.entities.Usuario;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.security.SessionManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fakejhon666
 */
@Named(value = "notificacionBean")
@ViewScoped
public class NotificacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private Colossal colossal;
    private List<Notificacion> lNotificaciones;
    private Notificacion notificacion;
    private int cantidadNotificaciones;
    private int pagina;
    private int ident_notif;
    private int elemXpagina;
    private int id;

    /**
     * Creates a new instance of NotificacionBean Obtiene los parametros de la
     * url y llena los atributos pagina, ident_notif y el atributo id.
     */
    public NotificacionBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String cadenaPagina = request.getParameter("pagina");
        String cadenaIdent = request.getParameter("ident_notif");
        String cadenaId = request.getParameter("id");
        try {
            int pag = Integer.parseInt(cadenaPagina);
            this.pagina = pag;
        } catch (NumberFormatException e) {
            this.pagina = 5;
        }
        try {
            int ident_notif = Integer.parseInt(cadenaIdent);
            this.ident_notif = ident_notif;
        } catch (NumberFormatException e) {
            this.ident_notif = 0;
        }
        try {
            int id = Integer.parseInt(cadenaId);
            this.id = id;
        } catch (NumberFormatException e) {
            this.id = 0;
        }
        this.elemXpagina = 5;
    }

    @PostConstruct
    public void init() {

    }

    /**
     * metodo que inicializa los valores erqueridos para el bean, obtiene el
     * indice del paginador (dependiendo de si se esta con un valor de
     * ident_notif = 2) se encarga de revisar las notificaciones al abrir una o
     * de listar todas las notificaciones correspondientes al icono de notif.
     *
     */
    public void inicializarBean() {

        int indice = this.pagina * this.elemXpagina - (this.elemXpagina - 1);
        Usuario u = SessionManager.getUsuarioSession();
        if (this.ident_notif == 0) {
            this.cantidadNotificaciones = this.colossal.countNotificaciones(u.getCodigo(), 0);
            this.lNotificaciones = this.colossal.getNotificaciones(u.getCodigo(), 1, 0);
        } else if (this.ident_notif == 1) {
            this.notificacion = this.colossal.getNotificacion(this.id);
            if (this.notificacion != null) {
                if (!Objects.equals(this.notificacion.getUsuarioReceptor().getCodigo(), u.getCodigo())) {
                    this.notificacion = null;
                    this.openInicio();
                } else {
                    if (this.notificacion.getEstadoIdestado().getFechaRevision() == null) {
                        this.revisarNotificacion(this.notificacion);
                    }
                }
            } else {
                this.openInicio();
            }
        } else if (this.ident_notif == 2) {
            this.cantidadNotificaciones = this.colossal.countNotificaciones(u.getCodigo(), 1);
            this.lNotificaciones = this.colossal.getNotificaciones(u.getCodigo(), indice, 1);

        }
    }

    /**
     * MEtodo get del atributo lNotificaciones
     *
     * @return
     */
    public List<Notificacion> getlNotificaciones() {
        return lNotificaciones;
    }

    /**
     * Metodo set del atributo lnotificaciones
     *
     * @param lNotificaciones
     */
    public void setlNotificaciones(List<Notificacion> lNotificaciones) {
        this.lNotificaciones = lNotificaciones;
    }

    /**
     * Metodo get del atributo cantidadNotificaciones
     *
     * @return
     */
    public int getCantidadNotificaciones() {
        return cantidadNotificaciones;
    }

    /**
     * Metodo set del atributo cantidadNotificaciones
     *
     * @param cantidadNotificaciones
     */
    public void setCantidadNotificaciones(int cantidadNotificaciones) {
        this.cantidadNotificaciones = cantidadNotificaciones;
    }

    /**
     * Metodo get del atributo notificacion
     *
     * @return
     */
    public Notificacion getNotificacion() {
        return notificacion;
    }

    /**
     * Metodo set del atributo notificacion
     *
     * @param notificacion
     */
    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    /**
     * Metodo get del atributo ident_notif
     *
     * @return
     */
    public int getIdent() {
        return ident_notif;
    }

    /**
     * metodo get del atributo pagina
     *
     * @return
     */
    public int getPagina() {
        return pagina;
    }

    /**
     * Metodo set del atributo pagina
     *
     * @param pagina
     */
    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    /**
     * metodo set del atributo ident_notif
     *
     * @param ident_notif
     */
    public void setIdent(int ident_notif) {
        this.ident_notif = ident_notif;
    }

    /**
     * Metodo que limita la cantidad de texto que se muestra del asunto de una
     * noti ficacion.
     *
     * @param n
     * @return
     */
    public String limitarTexto(Notificacion n) {
        String msj = n.getAsunto();
        if (n.getAsunto().length() > 200) {
            msj = n.getAsunto().substring(0, 200) + "...";
        }
        return msj;
    }

    /**
     * Metodo que se encarga de cambiarle el estado de una notificacion a
     * revisado cuando esta es visualizada.
     *
     * @param n
     */
    private void revisarNotificacion(Notificacion n) {
        String estado = "Revisado";
        if (!estado.equalsIgnoreCase("")) {
            String msg = this.colossal.editarEstado(n.getEstadoIdestado(), estado, null, null, null);
            System.out.println(msg);
        }
    }

    /**
     * Metodo que redirecciona al panel donde se visualiza una notificacion
     *
     * @param notificacion
     * @return string con la url que contiene la id de la notificacion y el
     * ident=1 para mostrar solo la notificacion correspondiente a la id
     */
    public String verNotificacion(Notificacion notificacion) {
        return "/views/usuarioViews/notificaciones.xhtml?faces-redirect=true&ident_notif=1&pagina=1&id=" + notificacion.getIdNotificacion();
    }

    /**
     * Metodo que devuelve la url de la vista donde se visualizaran todas las
     * no- -tificaciones en la pagina correspondiente a la busqueda.
     *
     * @param pagina
     * @param paginasTotales
     * @return String con la url de la busqueda
     */
    public String verNotificaciones(int pagina, int paginasTotales) {
        if (paginasTotales == 0) {
            paginasTotales = this.calcularPaginasTotales();
        }
        if (pagina <= 0) {
            pagina = 1;
        } else if (pagina > paginasTotales) {
            pagina = paginasTotales;
        }

        return "/views/usuarioViews/notificaciones.xhtml?faces-redirect=true&ident_notif=2&pagina=" + pagina + "&id=0";
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    /**
     *
     * @return
     */
    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Metodo que calcula las paginas totales de la busqueda
     *
     * @return
     */
    public int calcularPaginasTotales() {
        double operacion = (this.cantidadNotificaciones * 1.0f) / this.elemXpagina;
        int paginasTotales = (int) Math.ceil(operacion);
        return paginasTotales;
    }

    /**
     * Metodo que calcula y devuelve una lista de entero con las paginas
     * correspondientes del paginador
     *
     * @return lista de enteros correspondiente a las paginas del paginador
     */
    public List<Integer> calcularPaginas() {
        List<Integer> paginas = new ArrayList<Integer>();
        double div = (this.pagina * 1.0) / this.elemXpagina; // obtengo el segmento dependiendo del intervalo de paginas a mostrar, de 1-5 seria segmento 1 por ejemplo
        int x = (int) (Math.ceil(div));
        int j = (x * this.elemXpagina) - (this.elemXpagina - 1);// obtengo el valor de la pagina inicial donde iniciar el segmento 
        int paginasTotales = calcularPaginasTotales();
        for (int i = 1; i <= this.elemXpagina && i <= paginasTotales && j <= paginasTotales; i++, j++) {
            paginas.add(j);
        }
        return paginas;
    }

    /**
     * Metodo que devuelve la url de la busqueda dentro de las notificaciones
     * recibe un entero indicacion el cual corresponde a las flechas del
     * paginador 1 = primera pagina 2 = para anterior 3 = para siguiente 4 =
     * pagina final si el identificacion es diferente de los numeros
     * anteriormente listados valor no cambia, el cual corresponde a la pagina a
     * la que se va a despla zar la busqueda.
     *
     * @param indicacion
     * @param valor
     * @return String con la url de la busqueda.
     */
    public String busqueda(int indicacion, int valor) {
        int paginasTotales = this.calcularPaginasTotales();
        switch (indicacion) {
            case 1:
                valor = 1;
                break;
            case 2:
                valor = this.pagina - 1;
                break;
            case 3:
                valor = this.pagina + 1;
                break;
            case 4:
                valor = paginasTotales;
                break;
        }
        return this.verNotificaciones(valor, paginasTotales);

    }

    /**
     * Metodo que devuelve la clase bootstrap correspondiente al panel donde se
     * muestra una notificacion dependiendo de su estado.
     *
     * @param n
     * @return Sting con la clase bootstrap
     */
    public String getClass(Notificacion n) {
        String clase = "default";
        if (n.getEstadoIdestado().getEstado().equalsIgnoreCase("Pendiente")) {
            clase = "success";
        }
        return clase;
    }

    /**
     * MEtodo que cuenta la cantidad de notificaciones nuevas que tiene un usua-
     * -io.
     *
     * @return entero con la cantidad de notificaciones
     */
    public int contarNotificacionesNuevas() {
        Usuario u = SessionManager.getUsuarioSession();
        return this.colossal.countNotificaciones(u.getCodigo(), 0);
    }

    /**
     * Metodo que devuelve una lista de notificaciones las cuales corresponden a
     * las mas recientes, solo se muestran un maximo de 8 notificaciones.
     *
     * @return lista<notificacion> notificaciones recientes
     */
    public List<Notificacion> encontrarNotificacionesNuevas() {
        Usuario u = SessionManager.getUsuarioSession();
        return this.colossal.getNotificaciones(u.getCodigo(), 1, 0);
    }

    /**
     * Metodo boolean que valida que el estado de un componente se encuentra en
     * eliminado o no
     *
     * @param n
     * @return
     */
    public boolean validarEstadoComponente(Notificacion n) {
        boolean sw = false;
        String estado = n.getComponenteidComponente().getPeticionSubidaList().get(0).getEstadoIdestado().getEstado();
        if (estado.equalsIgnoreCase("Eliminado")) {
            sw = true;
        }
        return sw;
    }

    /**
     * Metodo que devuelve al inicio
     */
    public void openInicio() {
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/faces/views/componentView/componente.xhtml");
        } catch (Exception e) {
            try {
                getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/faces/views/componentView/componente.xhtml");
            } catch (Exception ee) {

            }
        }
    }
}
