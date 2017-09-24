/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.negocio.Message;
import co.edu.ufps.colossal.security.SessionManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author neck
 */
@Named(value = "busquedaAvanzadaBean")
@ViewScoped
public class BusquedaAvanzadaBean implements Serializable {

    @EJB
    private Colossal colossal;
    private String cadenaTextos;
    private String cadenaTags;
    private String cadenaServicios;
    private String fecha1;
    private String auxFecha1;
    private String fecha2;
    private String auxFecha2;
    private String identifier1;
    private String identifier2;
    private List<Componente> componentesBusqueda;
    private int pagina;
    private int render;
    private int cantElemBusqueda;
    private int cantElemXpag;

    /**
     * Creates a new instance of BusquedaAvanzadaBean obtiene los parametros GET
     * de la url y llena los atributos render y pagina los cuales determinan el
     * panel a mostrar y la pagina correspondiente al paginador en la vista.
     */
    public BusquedaAvanzadaBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String cadenaPagina = request.getParameter("pagina");
        String cadenaRender = request.getParameter("render");
        try {
            int pag = Integer.parseInt(cadenaPagina);
            this.pagina = pag;
        } catch (NumberFormatException e) {
            this.pagina = 1;
        }
        try {
            int rend = Integer.parseInt(cadenaRender);
            this.render = rend;
        } catch (NumberFormatException e) {
            this.render = 1;
        }
    }

    /**
     * Metodo que se encarga de llenar los atributos correspondientes
     * dependiendo del panel a mostrar, determina la cantidad de elementos por
     * pagina, la cantidad de elementos de la busqueda y el indice en el cual se
     * encuentra la busqueda en el paginador.
     */
    @PostConstruct
    public void init() {
        this.cantElemBusqueda = 1;
        this.cantElemXpag = 5;
        int indice = this.pagina * this.cantElemXpag - (this.cantElemXpag - 1);
        if (this.render == 1) {
            this.fecha1 = "01/01/2000";
            this.auxFecha1 = this.fecha1;
            this.fecha2 = this.colossal.getFechaActual();
            this.auxFecha2 = this.fecha2;
            SessionManager.removeSessionParameters();
        }
        if (this.render == 2) {
            this.cadenaServicios = this.getParameterSession("cadenaServicios");
            this.cadenaTags = this.getParameterSession("cadenaTags");
            this.cadenaTextos = this.getParameterSession("cadenaTextos");
            this.fecha1 = this.getParameterSession("fecha1");
            this.fecha2 = this.getParameterSession("fecha2");
            this.identifier1 = this.getParameterSession("identifier1");
            this.identifier2 = this.getParameterSession("identifier2");
            this.componentesBusqueda = this.colossal.getBusquedaAvanzada(this.cadenaTextos, this.cadenaTags, this.cadenaServicios, this.fecha1, this.fecha2, this.identifier1, this.identifier2, indice);
            this.cantElemBusqueda = this.colossal.getCantComponentsBusquedaAvanzada(this.cadenaTextos, this.cadenaTags, this.cadenaServicios, this.fecha1, this.fecha2, this.identifier1, this.identifier2);
        }
    }

    /**
     * Metodo que devuelve los parametros almacenados en la sesion
     *
     * @param cadena
     * @return
     */
    private String getParameterSession(String cadena) {
        Object o = SessionManager.getParameter(cadena);

        if (o != null) {
            return o.toString();
        }
        return null;
    }

    /**
     * Metodo que devuelve la url con los parametros get para la busqueda
     * avanzada.
     *
     * @return
     */
    public String busquedaAvanzada() {
        return "/views/componentView/busquedaAvanzada.xhtml?faces-redirect=true&pagina=" + this.pagina + "&render=" + this.render;
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
     * Metodo get del atributo cadenaTextos
     *
     * @return String
     */
    public String getCadenaTextos() {
        return cadenaTextos;
    }

    /**
     * Metodo set del atributo cadenaTextos
     *
     * @param cadenaTextos
     */
    public void setCadenaTextos(String cadenaTextos) {
        this.cadenaTextos = cadenaTextos;
    }

    /**
     * Metodo get del atributo cadenaTags
     *
     * @return String
     */
    public String getCadenaTags() {
        return cadenaTags;
    }

    /**
     * Metodo set del atributo cadenaTags
     *
     * @param cadenaTags
     */
    public void setCadenaTags(String cadenaTags) {
        this.cadenaTags = cadenaTags;
    }

    /**
     * Metodo get del atributo cadenaServicios
     *
     * @return Sting
     */
    public String getCadenaServicios() {
        return cadenaServicios;
    }

    /**
     * Metodo set del atributo cadenaServicios
     *
     * @param cadenaServicios
     */
    public void setCadenaServicios(String cadenaServicios) {
        this.cadenaServicios = cadenaServicios;
    }

    /**
     * Metodo get del atributo render
     *
     * @return render
     */
    public int getRender() {
        return render;
    }

    /**
     * MEtodo set del atributo render
     *
     * @param render
     */
    public void setRender(int render) {
        this.render = render;
    }

    /**
     * Metodo get del atributo cantElemBusqueda
     *
     * @return entero cantElemBusqueda
     */
    public int getCantElemBusqueda() {
        return cantElemBusqueda;
    }

    /**
     * Metodo set del parametro cantElemBusqueda
     *
     * @param cantElemBusqueda
     */
    public void setCantElemBusqueda(int cantElemBusqueda) {
        this.cantElemBusqueda = cantElemBusqueda;
    }

    /**
     * MEtodo get del metodo cantElemXpag
     *
     * @return enter cantElemXpag
     */
    public int getCantElemXpag() {
        return cantElemXpag;
    }

    /**
     * Metodo set del atributo cantElemXpag
     *
     * @param cantElemXpag
     */
    public void setCantElemXpag(int cantElemXpag) {
        this.cantElemXpag = cantElemXpag;
    }

    /**
     * Metodo get del atributo fecha1
     *
     * @return String fecha1
     */
    public String getFecha1() {
        return fecha1;
    }

    /**
     * metodo set del atributo fecha 1
     *
     * @param fecha1
     */
    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    /**
     * metodo get del atributo fecha2
     *
     * @return string fecha2
     */
    public String getFecha2() {
        return fecha2;
    }

    /**
     * metodo set del atributo fecha2
     *
     * @param fecha2
     */
    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    /**
     * Metodo get del atributo componentesBusqueda
     *
     * @return List<componente> componentesBusqueda.
     */
    public List<Componente> getComponentesBusqueda() {
        return componentesBusqueda;
    }

    /**
     * Metodo set del atributo componentesBusqueda
     *
     * @param componentesBusqueda
     */
    public void setComponentesBusqueda(List<Componente> componentesBusqueda) {
        this.componentesBusqueda = componentesBusqueda;
    }

    /**
     * Metodo get del atributo identifier1
     *
     * @return string identifier1
     */
    public String getIdentifier1() {
        return identifier1;
    }

    /**
     * Metodo set del atributo identifier1
     *
     * @param identifier1
     */
    public void setIdentifier1(String identifier1) {
        this.identifier1 = identifier1;
    }

    /**
     * Metodo get del atributo identifier2
     *
     * @return String identifier2
     */
    public String getIdentifier2() {
        return identifier2;
    }

    /**
     * Metodo set del atributo identifier2
     *
     * @param identifier2
     */
    public void setIdentifier2(String identifier2) {
        this.identifier2 = identifier2;
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
     * Metodo que devuelve un string con la url con los parametros get para
     * realizar la busqueda avanzada, recibe un valor indicacion que corresponde
     * a las flechas adelante, atras, primero y ultimo del paginador y un entero
     * valor que corresponde a la pagina
     *
     * @param indicacion
     * @param valor
     * @return
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
        return this.buscar(valor, paginasTotales);
    }

    /**
     * Metodo que devuelve un string con la url con los parametros get para que
     * la pagina realize la busqueda avanazda, guarda los parametros necesarios
     * en sesion y calcula la pagina correcta a la cual se va a redireccionar
     *
     * @param pagina
     * @param paginasTotales
     * @return
     */
    public String buscar(int pagina, int paginasTotales) {
        if (paginasTotales == 0) {
            paginasTotales = this.calcularPaginasTotales();
        }
        if (pagina <= 0) {
            pagina = 1;
        } else if (pagina > paginasTotales) {
            pagina = paginasTotales;
        }
        if (this.colossal.compararFechas(this.fecha1, this.fecha2)) {
            if (!(this.cadenaServicios.isEmpty() && this.cadenaTags.isEmpty() && this.cadenaTextos.isEmpty())) {

                if (this.validarCadenasSinVacios(this.cadenaServicios) && this.validarCadenasSinVacios(this.cadenaTags) && this.validarCadenasSinVacios(this.cadenaTextos)) {
                    SessionManager.saveParameter("cadenaServicios", this.cadenaServicios);
                    SessionManager.saveParameter("cadenaTags", this.cadenaTags);
                    SessionManager.saveParameter("cadenaTextos", this.cadenaTextos);
                    SessionManager.saveParameter("fecha1", this.fecha1);
                    SessionManager.saveParameter("fecha2", this.fecha2);
                    SessionManager.saveParameter("identifier1", this.identifier1);
                    SessionManager.saveParameter("identifier2", this.identifier2);
                    this.pagina = pagina;
                    this.render = 2;
                    return this.busquedaAvanzada();
                } else {
                    Message.addErrorMessage("No puede tener datos de bússqueda vacíos después o antes de un ;", null);
                }
            } else {
                Message.addErrorMessage("Parámetros vacíos", null);
            }
        } else {
            Message.addErrorMessage("Fechas no validas", null);
        }
        RequestContext.getCurrentInstance().update("formBusquedaAvanzada:message");
        return "";
    }

    /**
     * Metodo que calcula la cantidad de paginas totales que hay
     *
     * @return
     */
    public int calcularPaginasTotales() {
        double operacion = this.cantElemBusqueda / this.cantElemXpag;
        int paginasTotales = (int) Math.ceil(operacion);
        return paginasTotales;
    }

    /**
     * Metodo que devuelve las paginas que se van a desplegar en el paginador.
     *
     * @return
     */
    public List<Integer> calcularPaginas() {
        List<Integer> paginas = new ArrayList<Integer>();
        float div = this.pagina / this.cantElemXpag; // obtengo el segmento dependiendo del intervalo de paginas a mostrar, de 1-5 seria segmento 1 por ejemplo
        int x = (int) (Math.ceil(div));
        int j = (x * this.cantElemXpag) - (this.cantElemXpag - 1);// obtengo el valor de la pagina inicial donde iniciar el segmento 
        int paginasTotales = calcularPaginasTotales();
        for (int i = 1; i <= 5 && i <= paginasTotales && j <= paginasTotales; i++, j++) {
            paginas.add(j);
        }
        return paginas;
    }

    /**
     * Metodo que evalua la validez de una fecha introducida, validando que el
     * formato sea el correcto, asi como los años bisciestos y demas
     *
     * @param campo
     */
    public void evaluarCadenaFechas(int campo) {
        String value = "";
        String pattern = "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((1[6-9]|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$";
        if (campo == 1) {
            value = this.fecha1;
        } else {
            value = this.fecha2;
        }

        if (Pattern.matches(pattern, value)) {
            if (campo == 1) {
                this.auxFecha1 = this.fecha1;
            } else {
                this.auxFecha2 = this.fecha2;
            }

        } else {
            if (campo == 1) {
                this.fecha1 = this.auxFecha1;
            } else {
                this.fecha2 = this.auxFecha2;
            }
        }
    }

    /**
     * Metodo que devuelve el tamaño de una cadena
     *
     * @param cadena
     * @return
     */
    public int getListString(String cadena) {
        int size = cadena.length();
        return size;
    }

    /**
     * Metodo que devuelve un string con los criterios de la busqueda a realizar
     *
     * @param cadena
     * @return
     */
    public String getLabelCriterioBusqueda(String cadena) {
        String msg = "";
        int i = 0;
        for (String s : this.colossal.convertStringToStringList(cadena)) {
            if (i > 0) {
                msg += ", " + s;
            } else {
                msg += " " + s;
            }
            i++;
        }
        return msg;
    }

    /**
     * Metodo que valida que las cadenas de texto introducidos en los campos
     * esten correctamente ingresados.
     *
     * @param cadena
     * @return
     */
    private boolean validarCadenasSinVacios(String cadena) {
        List<String> l = this.colossal.convertStringToStringList(cadena);
        for (String s : this.colossal.convertStringToStringList(cadena)) {
            if (s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que inicializa los datepickers.
     */
    public void iniciarDatepickers() {
        RequestContext.getCurrentInstance().execute("inicializarDatepickers()");
    }

}
