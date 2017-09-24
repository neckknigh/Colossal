/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ComponenteHasDependency;
import co.edu.ufps.colossal.entities.Incidencia;
import co.edu.ufps.colossal.entities.Notificacion;
import co.edu.ufps.colossal.entities.PeticionSubida;
import co.edu.ufps.colossal.entities.Usuario;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.negocio.Message;
import co.edu.ufps.colossal.security.SessionManager;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AvrilFockMe
 */
@Named(value = "administradorBean")
@ViewScoped
public class AdministradorBean implements Serializable {

    @EJB
    private Colossal colossal;
    private Notificacion notificacion;
    private List<PeticionSubida> pSubidaList;
    private List<Incidencia> listaIncidencias;
    private int render;
    private int pagina;
    private int ident;
    private int elemXpagina;
    private int cantElements;

    /**
     * Creates a new instance of AdministradorBean Captura los parametros de la
     * url por peticion GET y llena los respectivos atributos.
     */
    public AdministradorBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String cadenaPagina = request.getParameter("pagina");
        String cadenaIdent = request.getParameter("ident");
        String cadenaRender = request.getParameter("render");
        try {
            int pag = Integer.parseInt(cadenaPagina);
            this.pagina = pag;
        } catch (NumberFormatException e) {
            this.pagina = 1;
        }
        try {
            int ident = Integer.parseInt(cadenaIdent);
            this.ident = ident;
        } catch (NumberFormatException e) {
            this.ident = 1;
        }
        try {
            int render = Integer.parseInt(cadenaRender);
            this.render = render;
        } catch (NumberFormatException e) {
            this.render = 1;
        }
        this.elemXpagina = 5;
        this.notificacion = new Notificacion();
    }

    /**
     * Metodo que inicia los valores de los parametros pSubidaList,
     * cantElements, listaIncidencias dependiendo del valor del parameto render,
     * calcula el indice en el cual se encuentra la busqueda
     */
    @PostConstruct
    public void init() {
        this.cantElements = 1;
        int indice = this.pagina * this.elemXpagina - (this.elemXpagina - 1);
        String valor = this.getStatus();
        if (this.render == 1) {
            this.pSubidaList = this.colossal.obtenerPeticionesPorEstado(indice, valor);
            this.cantElements = this.colossal.obtenerCantPeticionesPorEstado(indice, valor);
        } else if (this.render == 2) {
            this.listaIncidencias = this.colossal.getIncidencias(valor, indice);
            this.cantElements = this.colossal.countIncidencias(valor);
        }
    }

    /**
     * Metodo que devuelve el valor del parametro render
     *
     * @return entero
     */
    public int getRender() {
        return render;
    }

    /**
     * MEtodo que modifica el valor del parametro render
     *
     * @param render
     */
    public void setRender(int render) {
        this.render = render;
    }

    /**
     * Metodo que devuelve la pagina
     *
     * @return entero
     */
    public int getPagina() {
        return pagina;
    }

    /**
     * Metodo que modifica la pagina
     *
     * @param pagina
     */
    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    /**
     * Metodo que retorna una lista de incidencias
     *
     * @return List<Inicdencia>
     */
    public List<Incidencia> getListaIncidencias() {
        return listaIncidencias;
    }

    /**
     * Metodo que modifica la lista de inicidencias
     *
     * @param listaIncidencias
     */
    public void setListaIncidencias(List<Incidencia> listaIncidencias) {
        this.listaIncidencias = listaIncidencias;
    }

    /**
     * Metodo que retorna la lista de peticiones de subida
     *
     * @return List<PeticionSubida>
     */
    public List<PeticionSubida> getpSubidaList() {
        return pSubidaList;
    }

    /**
     * Metodo que modifica la lista de peticiones de subida
     *
     * @param pSubidaList
     */
    public void setpSubidaList(List<PeticionSubida> pSubidaList) {
        this.pSubidaList = pSubidaList;
    }

    /**
     * metodo que retorna la cantidad de elementos de la pagina
     *
     * @return entero
     */
    public int getElemXpagina() {
        return elemXpagina;
    }

    /**
     * Metodo que modifica la cantidad de elementos que hay en la pagina
     *
     * @param elemXpagina
     */
    public void setElemXpagina(int elemXpagina) {
        this.elemXpagina = elemXpagina;
    }

    /**
     * Metodo que retorna la cantidad total de elementos
     *
     * @return
     */
    public int getCantElements() {
        return cantElements;
    }

    /**
     * Metodo que modifica la cantidad total de elementos
     *
     * @param cantElements
     */
    public void setCantElements(int cantElements) {
        this.cantElements = cantElements;
    }

    /**
     * Metodo que devuelve una notificacion
     *
     * @return notificacion
     */
    public Notificacion getNotificacion() {
        return notificacion;
    }

    /**
     * Metodo que cambia la notificacion
     *
     * @param notificacion
     */
    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    /**
     * Metodo que devuelve un string con una clase css de bootstrap segun el
     * identificador (el cual representa el estado o tipo de panel que se va a
     * usar).
     *
     * @return String clase de bootstrap
     */
    public String getClassPanel() {
        String valor = "";
        if (this.ident == 1) {
            valor = "success";
        }
        if (this.ident == 2) {
            valor = "warning";
        }
        if (this.ident == 3 || this.ident == 4) {
            valor = "danger";
        }
        return valor;
    }

    /**
     * Metodo que devuelve el estado de una peticion de subida o de una
     * incidencia dependiendo del atributo "ident", este atributo representa lo
     * que se desea mostrar en la vista
     *
     * @return String con el estado de una peticion de subida o incidencia
     */
    public String getStatus() {
        String valor = "";
        if (this.ident == 1) {
            if (this.render == 1) {
                valor = "Aprobado";
            } else if (this.render == 2) {
                valor = "Revisado";
            }
        }
        if (this.ident == 2) {
            valor = "Pendiente";
        }
        if (this.ident == 3) {
            valor = "Rechazado";
        }
        if (this.ident == 4) {
            valor = "Eliminado";

        }
        return valor;
    }

    /**
     * Metodo que devuelve la cantidad de paginas totales
     *
     * @return entero con la cantidad
     */
    public int calcularPaginasTotales() {
        double operacion = (this.cantElements * 1.0) / this.elemXpagina;
        int paginasTotales = (int) Math.ceil(operacion);
        return paginasTotales;
    }

    /**
     * MEtodo que calcula y devuelve la cantidad de paginas a mostrar
     * dependiendo de cuantos elementos se muestran por pagina.
     *
     * @return List<Integer> con la cantidad de elementos
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
     * Metodo que realiza la redireccion dentro de las paginas recibe una
     * indicacion la cual es correspondiente a ir adelante o atras, y un
     * parametro valor que representa la pagina
     *
     * @param indicacion
     * @param valor
     * @return cadena con la url de la busqueda
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

        switch (this.ident) {
            case 1:
                return this.redirectAdminPanel(valor, paginasTotales, this.render, 1);

            case 2:
                return this.redirectAdminPanel(valor, paginasTotales, this.render, 2);

            case 3:
                return this.redirectAdminPanel(valor, paginasTotales, this.render, 3);

            case 4:
                return this.redirectAdminPanel(valor, paginasTotales, this.render, 4);

        }
        return "";
    }

    /**
     * Metodo que redirecciona al panel de administrador,este metodo crea la url
     * get con los parametros requeridos para mostrar el contenido dentro de la
     * pagina
     *
     * @param pagina pagina a mostrar
     * @param paginasTotales cantidad de paginas para validar que la pagina sea
     * validad
     * @param render panel que se va a mostrar
     * @param ident identificador de la opcion a mostrar
     * @return String con la url con los parametros get
     */
    public String redirectAdminPanel(int pagina, int paginasTotales, int render, int ident) {
        if (paginasTotales == 0) {
            paginasTotales = this.calcularPaginasTotales();
        }
        if (pagina <= 0) {
            pagina = 1;
        } else if (pagina > paginasTotales) {
            pagina = paginasTotales;
        }

        return "/views/usuarioViews/administrador.xhtml?faces-redirect=true&render=" + render + "&pagina=" + pagina + "&ident=" + ident;
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
     * Metodo que valida que una fecha asignada cumpla con el formato requerido
     *
     * @param fecha
     * @return String con el formato valido para una fecha
     */
    public String prepararFechaYHora(Date fecha) {
        String output = "No asignada aun";
        if (fecha != null) {
            SimpleDateFormat outputDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            output = outputDF.format(fecha);
        }
        return output;
    }

    /**
     * Metodo que valida que un usuario en el panel de administrador es un
     * adminis- -trador, si no es un usuario valido este es redireccionado a la
     * pagina inicial
     */
    public void checkAdminRights() {
        if (SessionManager.getUsuarioSession().getTipoUsuario() != 1) {
            try {
                getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/faces/views/componentView/componente.xhtml");
            } catch (Exception e) {
                System.out.println("Error en redireccion");
            }
        }
    }

    /**
     * Metodo que cambia el estado de un componente y envia una notificacion al
     * usuario con los detalles de dicho cambio
     *
     * @param accion
     * @param c
     */
    public void cambiarEstadoComponente(int accion, Componente c) {
        String estado = "";
        String asunto = "";
        switch (accion) {
            case 1:
                estado = "Aprobado";
                asunto = "Tu componente ha sido aprobado!";
                break;
            case 2:
                estado = "Pendiente";
                asunto = "Se ha recuperado un componente";
                break;
            case 3:
                estado = "Rechazado";
                asunto = "Tu componente ha sido rechazado!";
                break;
            case 4:
                estado = "Eliminado";
                asunto = "Un componente ha sido eliminado";
                break;
        }
        String msj = "";
        boolean sw = true;
        this.notificacion.setComponenteidComponente(c);
        this.notificacion.setUsuarioReceptor(c.getPeticionSubidaList().get(0).getUsuarioCodigo());
        this.notificacion.setAsunto(asunto);
        if (this.notificacion.getMensaje() == null || this.notificacion.getMensaje().trim().isEmpty()) {
            msj = "El campo observacion no puede ser vacio";
            RequestContext.getCurrentInstance().execute("enviarAlerta('" + msj + "')");
        } else {
            if (!estado.equalsIgnoreCase("")) {
                Usuario admin = SessionManager.getUsuarioSession();
                PeticionSubida p = c.getPeticionSubidaList().get(0);
                p.setAdmCodigo(admin);

                if (estado.equalsIgnoreCase("Aprobado")) {

                    for (ComponenteHasDependency ch : c.getComponenteHasDependencyList()) {
                        if (!ch.getComponenteDependency().getPeticionSubidaList().get(0).getEstadoIdestado().getEstado().equalsIgnoreCase("Aprobado")) {
                            msj = "El componente " + ch.getComponenteDependency().getNombre() + " de las dependencias no se encuentra disponible";
                            sw = false;
                            break;
                        }
                    }
                }
                if (sw) {
                    String msg = this.colossal.editarEstado(c.getPeticionSubidaList().get(0).getEstadoIdestado(), estado, p, null, notificacion);
                    if (msg.contains("error")) {
                        msj = "Un error inesperado ha ocurrido, intente de nuevo";
                        RequestContext.getCurrentInstance().execute("enviarAlerta('" + msj + "')");
                    } else {
                        RequestContext.getCurrentInstance().execute("$('#modalGestion').modal('hide')");
                        Message.addMessage("El estado del componente se ha actualizado a: " + estado, null);
                    }
                } else {
                    RequestContext.getCurrentInstance().execute("enviarAlerta('" + msj + "')");
                }
            }
        }
        this.notificacion = new Notificacion();
        RequestContext.getCurrentInstance().update("formDetalles:msgsDetalles");
    }

    /**
     * Metodo que da or revisado una incidencia y devuelve un mensaje de
     * validacion
     *
     * @param i
     */
    public void revisarIncidencia(Incidencia i) {
        String estado = "Revisado";
        if (!estado.equalsIgnoreCase("")) {
            Usuario admin = SessionManager.getUsuarioSession();
            i.setAdmCodigo(admin);
            String msg = this.colossal.editarEstado(i.getEstadoIdestado(), estado, null, i, null);
            if (msg.contains("error")) {
                Message.addErrorMessage("Un error ha ocurrido!", null);
            } else {
                Message.addMessage("La incidencia ha sido revisada! ", null);
            }
        }
    }

    /**
     * Metodo que valida si un usuario ha sido asignado a una peticion de subida
     * devuelve un mensaje con el texto corregido a mostrar
     *
     * @param u
     * @param tipoUsuario
     * @return String con mensajes validados
     */
    public String validarUsuario(Usuario u, int tipoUsuario) {
        String texto = "";
        if (u == null) {
            if (tipoUsuario == 1) {
                texto = "Pendiente";
            } else if (tipoUsuario == 2) {
                texto = "Error al cargar el nombre del usuario";
            }
        } else {
            texto = u.getNombre();
        }
        return texto;
    }

}
