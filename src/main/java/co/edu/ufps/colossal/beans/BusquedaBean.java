/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ComponenteHasTag;
import co.edu.ufps.colossal.entities.Tag;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.negocio.Message;
import co.edu.ufps.colossal.security.SessionManager;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author fakejhon666
 */
@Named(value = "busquedaBean")
@ViewScoped
public class BusquedaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private Colossal colossal;
    private int id_cat;
    private int id_tag;
    private int render;
    private int pagina;
    private int filtro;
    private int cantElemBusqueda;
    private int ident;
    private int idlist;
    private String status;
    private String busqueda;
    private String nameTag;
    private List<Componente> listaBusqueda;
    private String listaName;
    private int codigo;

    /**
     * Creates a new instance of BusquedaBean Obtiene los parametros por medio
     * de peticion GET de la url y llena los atributos
     */
    public BusquedaBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String cadenaCat = request.getParameter("id_cat");
        String cadenaTag = request.getParameter("idtag");
        String cadenaPagina = request.getParameter("pagina");
        String cadenaIdent = request.getParameter("ident");
        String cadenaFilt = request.getParameter("filt");
        String cadenaList = request.getParameter("list");
        try {
            this.busqueda = URLDecoder.decode(request.getParameter("busqueda"), "UTF-8");
        } catch (Exception e) {
            this.busqueda = "";
        }
        if (busqueda == null) {
            this.busqueda = "";
        }
        try {
            int cat = Integer.parseInt(cadenaCat);
            this.id_cat = cat;
        } catch (NumberFormatException e) {
            this.id_cat = 0;
        }
        try {
            int tag = Integer.parseInt(cadenaTag);
            this.id_tag = tag;
        } catch (NumberFormatException e) {
            this.id_tag = 0;
        }
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
            int filtro = Integer.parseInt(cadenaFilt);
            this.filtro = filtro;
        } catch (NumberFormatException e) {
            this.filtro = 0;
        }
        try {
            int list = Integer.parseInt(cadenaList);
            this.idlist = list;
        } catch (NumberFormatException e) {
            this.idlist = 0;
        }
        try {
            this.codigo = SessionManager.getUsuarioSession().getCodigo();
        } catch (NumberFormatException e) {
            this.codigo = 0;
        }
    }

    /**
     * Metodo que obtiene la lista de componentes dependiendo del identificador
     * usado para determinar el tipo de busqueda a realizar.
     */
    @PostConstruct
    public void init() {
        this.listaBusqueda = new ArrayList<Componente>();
        this.cantElemBusqueda = 1;
        int indice = this.pagina * 5 - 4;
        if (this.ident == 1) {
            this.listaBusqueda = this.colossal.getComponentesByCategory(this.id_cat, indice, this.busqueda);
        }
        if (this.ident == 3) {
            this.listaBusqueda = this.colossal.getComponentesByNombreYDescripcion(this.busqueda, indice);
        }
        if (this.ident == 2) {
            Tag t = this.colossal.getTagsById(this.id_tag);
            if (t != null) {
                this.nameTag = t.getNombre();
                this.listaBusqueda = this.colossal.getComponentesByTag(t.getNombre(), indice, this.busqueda);
            }
        }
        if (this.ident == 4) {
            this.listaBusqueda = this.colossal.getComponentesByNombreYDescripcion("", indice);
        }
        if (this.ident == 5) {
            this.getComponentsByStatusAndUser(1, 1, indice);
        }
        if (this.ident == 6) {
            this.getComponentsByStatusAndUser(2, 1, indice);
        }
        if (this.ident == 7) {
            this.getComponentsByStatusAndUser(3, 1, indice);
        }
        if (this.ident == 8) {
            this.render = 2;
            this.listaName = this.colossal.findListById(this.idlist);
            this.listaBusqueda = this.colossal.getComponentsByIdList(this.idlist, this.codigo, indice);

            if (this.listaName == null) {
                this.openUserPanel(5, 0, 1, 1, 1);
            }
        }
    }

    /**
     * Metodo get del atributo listaName
     *
     * @return
     */
    public String getListaName() {
        return listaName;
    }

    /**
     * Metodo set del atributo listaName
     *
     * @param listaName
     */
    public void setListaName(String listaName) {
        this.listaName = listaName;
    }

    /**
     * Metodo get del atributo pagina
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
     * Meetodo get del atributo status
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * MEtodo set del atributo status
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Metodo get del atributo id_tag
     *
     * @return
     */
    public int getId_tag() {
        return id_tag;
    }

    /**
     * Metodo set del atributo id_tag
     *
     * @param id_tag
     */
    public void setId_tag(int id_tag) {
        this.id_tag = id_tag;
    }

    /**
     * Metodo get del atributo id_tag
     *
     * @return
     */
    public int getId_cat() {
        return id_cat;
    }

    /**
     * metodo get del atributo render
     *
     * @return
     */
    public int getRender() {
        return render;
    }

    /**
     * Metodo set del atributo render
     *
     * @param render
     */
    public void setRender(int render) {
        this.render = render;
    }

    /**
     * Metodo get del atributo ident
     *
     * @return
     */
    public int getIdent() {
        return ident;
    }

    /**
     * metodo set del atributo ident
     *
     * @param ident
     */
    public void setIdent(int ident) {
        this.ident = ident;
    }

    /**
     * metodo get del atributo nameTag
     *
     * @return
     */
    public String getNameTag() {
        return nameTag;
    }

    /**
     * Metodo set del atributo nameTag
     *
     * @param nameTag
     */
    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }

    /**
     * Metodo set del atributo id_cat
     *
     * @param id_cat
     */
    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    /**
     * Metodo get del atributo busqueda
     *
     * @return
     */
    public String getBusqueda() {
        return busqueda;
    }

    /**
     * Metodo set del atributo busqueda
     *
     * @param busqueda
     */
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    /**
     * Metodo get del atributo listaBusqueda
     *
     * @return
     */
    public List<Componente> getListaBusqueda() {
        return listaBusqueda;
    }

    /**
     * Metodo set del atributo listaBusqueda
     *
     * @param listaBusqueda
     */
    public void setListaBusqueda(List<Componente> listaBusqueda) {
        this.listaBusqueda = listaBusqueda;
    }

    /**
     * Metodo que limita la cantidad de caracteres que se devuelven en una
     * cadena de texto
     *
     * @param cadena
     * @return
     */
    public String limitarTexto(String cadena) {
        String resul = cadena;
        if (cadena.length() > 500) {
            resul = cadena.substring(0, 500) + "...";
        }
        return resul;
    }

    /**
     * Metodo que realiza la busqueda de componentes por cadena de texto
     *
     * @param pagina
     * @param paginasTotales
     * @param limpiar
     * @return Strng con la url de la busqueda
     */
    public String buscarTexto(int pagina, int paginasTotales, int limpiar) {
        if (!this.busqueda.equalsIgnoreCase("")) {
            return this.buscar(pagina, paginasTotales, 3, limpiar);
        }
        return "";
    }

    /**
     * Metodo que retorna el valor del panel el cual es una clase de bootstrap
     * donde se muestra la informacion de- -pendiendo del identificador.
     *
     * @return String con la clase de bootstrap
     */
    public String getClassPanel() {
        String valor = "";
        if (this.ident == 5) {
            valor = "success";
        }
        if (this.ident == 6) {
            valor = "warning";
        }
        if (this.ident == 7) {
            valor = "danger";
        }
        return valor;
    }

    /**
     * Metodo que devuelve las paginas para el paginador.
     *
     * @return List<Integer> con la cantidad de paginas
     */
    public List<Integer> calcularPaginas() {
        List<Integer> paginas = new ArrayList<Integer>();
        float div = this.pagina / 5f; // obtengo el segmento dependiendo del intervalo de paginas a mostrar, de 1-5 seria segmento 1 por ejemplo
        int x = (int) (Math.ceil(div));
        int j = (x * 5) - 4;// obtengo el valor de la pagina inicial donde iniciar el segmento 
        int paginasTotales = calcularPaginasTotales();
        for (int i = 1; i <= 5 && i <= paginasTotales && j <= paginasTotales; i++, j++) {
            paginas.add(j);
        }
        return paginas;
    }

    /**
     * MEtodo que devuelve los tags asociados a un componente
     *
     * @param c
     * @return String con los tags asociados al componente
     */
    public String mostrarTags(Componente c) {
        String cadena = "";
        int indice = 0;
        int size = c.getComponenteHasTagList().size() - 1;
        for (ComponenteHasTag cht : c.getComponenteHasTagList()) {
            cadena += cht.getTag().getNombre();
            if (indice == size) {
                cadena += ".";
            } else {
                cadena += ", ";
            }
            indice++;
        }
        return cadena;
    }

    /**
     * Metodo que calcula la cantidad total de paginas de la busqueda
     *
     * @return entero con la cantidad total de paginas
     */
    public int calcularPaginasTotales() {

        switch (this.ident) {
            case 1:
                if (this.filtro != 1) {
                    this.busqueda = "";
                }
                this.cantElemBusqueda = this.colossal.getCantComponentesByCategory(this.id_cat, this.busqueda);
                break;
            case 3:
                if (this.filtro != 1) {
                    this.busqueda = "";
                }
                this.cantElemBusqueda = this.colossal.getCantComponentesByNombreYDescripcion(this.busqueda);
                break;
            case 2:
                this.cantElemBusqueda = this.colossal.getCantComponentesByTag(this.nameTag, this.busqueda);
                break;
            case 4:
                this.cantElemBusqueda = this.colossal.getCantComponentesByNombreYDescripcion(this.busqueda);
                ;
                break;
            case 5:
                this.cantElemBusqueda = this.colossal.getCantComponentsByStatusAndUser("Aprobado", this.codigo);
                break;
            case 6:
                this.cantElemBusqueda = this.colossal.getCantComponentsByStatusAndUser("Pendiente", this.codigo);
                break;
            case 7:
                this.cantElemBusqueda = this.colossal.getCantComponentsByStatusAndUser("Rechazado", this.codigo);
                break;
            case 8:
                this.cantElemBusqueda = this.colossal.getCantComponentsByIdList(this.idlist, this.codigo);
                break;
        }
        double operacion = this.cantElemBusqueda / 5f;
        int paginasTotales = (int) Math.ceil(operacion);
        return paginasTotales;
    }

    /**
     * Metodo que realiza la busqueda de componentes dependiendo de la pagina y
     * la indicacion de busqueda 1 = primera pagina 2 = pagina anterior 3 =
     * pagina siguiente 4 = ultima pagina en caso de ser diferente el parametro
     * valor no cambia (el cual corresponde a la pagina a la que se va ).
     *
     * @param indicacion
     * @param valor
     * @return String con la url de la busqueda
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
        int limpiar = this.filtro;

        switch (this.ident) {
            case 1:
                return this.buscar(valor, paginasTotales, 1, limpiar);
            case 2:
                return this.componentesPorTag(this.id_tag, valor, paginasTotales, limpiar);
            case 3:
                return buscarTexto(valor, paginasTotales, limpiar);

            case 4:
                return this.buscar(valor, paginasTotales, 4, limpiar);
            case 5:
                return this.openUserPanel(5, this.idlist, valor, this.render, paginasTotales);
            case 6:
                return this.openUserPanel(6, this.idlist, valor, this.render, paginasTotales);
            case 7:
                return this.openUserPanel(7, this.idlist, valor, this.render, paginasTotales);
            case 8:
                return this.openUserPanel(8, this.idlist, valor, this.render, paginasTotales);
        }

        return "";
    }

    /**
     * Metodo que permite filtrar la busqueda realizada dependiendo del identi-
     * -ficador.
     *
     * @return String con la url de la busqueda
     */
    public String filtrar() {
        System.out.println("value: "+this.busqueda);
        if (!this.busqueda.isEmpty()) {
            switch (this.ident) {
                case 1:
                    return this.buscar(1, 1, 1, 1);

                case 2:
                    return this.componentesPorTag(this.id_tag, 1, 1, 1);

                case 3:
                    return this.buscarTexto(1, 1, 1);

                default:
                    return this.buscar(1, 1, 4, 1);

            }
        }

        return "";
    }

    /**
     * Metodo que devuelve la url de la busqueda con los parametros respectivos
     * calcula correctamente las paginas.
     *
     * @param pagina
     * @param paginasTotales
     * @param ident
     * @param limpiar
     * @return String con la url y sus parametros correspondientes
     */
    public String buscar(int pagina, int paginasTotales, int ident, int limpiar) {

        if (limpiar != 1) {
            this.busqueda = "";
            this.filtro = 0;
        } else {
            this.filtro = 1;
        }
        if (ident == 3) {
            this.id_cat = 0;
        }
        if (paginasTotales == 0) {
            paginasTotales = this.calcularPaginasTotales();
        }
        if (pagina <= 0) {
            pagina = 1;
        } else if (pagina > paginasTotales) {
            pagina = paginasTotales;
        }

        try {
            return "/views/componentView/componenteBusqueda.xhtml?faces-redirect=true&id_cat=" + this.id_cat + "&idtag=0&busqueda=" + URLEncoder.encode(this.busqueda, "UTF-8") + "&pagina=" + pagina + "&ident=" + ident + "&filt=" + this.filtro;
        } catch (UnsupportedEncodingException ex) {
            System.out.println("error al redireccionar");
            return "";
        }
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
     * Metodo que redirige a la vista correspondiente donde se muestra un com-
     * -ponente.
     *
     * @param idComponente
     * @return url con la id del componente
     */
    public String verComponente(int idComponente) {
        return "/views/componentView/componenteDetalles.xhtml?faces-redirect=true&id_componente=" + idComponente;
    }

    /**
     * Metodo que valida si un componente se puede visualizar o no, en caso de
     * no ser valido su visualizacion este redirecciona al inicio.
     *
     * @param c
     * @return String con la url respectiva
     */
    public String verComponenteNoValido(Componente c) {
        if (SessionManager.getUsuarioSession().getTipoUsuario() == 1 || Objects.equals(SessionManager.getUsuarioSession().getCodigo(), c.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo())) {
            if (SessionManager.getUsuarioSession().getTipoUsuario() == 0 && c.getPeticionSubidaList().get(0).getEstadoIdestado().getEstado().equalsIgnoreCase("Eliminado")) {
                Message.addErrorMessage("El componente seleccionado no se encuentra disponible!", null);
                RequestContext.getCurrentInstance().update("formDetalles:msgsDetalles");
            } else {
                return this.verComponente(c.getIdComponente());
            }
        }
        return "";
    }

    /**
     * Metodo que devuelve la url con la busqueda por componentes relacionados a
     * un tag.
     *
     * @param idtag
     * @param pagina
     * @param paginasTotales
     * @param limpiar
     * @return string con la url de la busqueda por tags
     */
    public String componentesPorTag(int idtag, int pagina, int paginasTotales, int limpiar) {
        this.filtro = limpiar;
        if (limpiar != 1) {
            this.busqueda = "";
        }
        if (paginasTotales == 0) {
            paginasTotales = this.calcularPaginasTotales();
        }
        if (pagina <= 0) {
            pagina = 1;
        } else if (pagina > paginasTotales) {
            pagina = paginasTotales;
        }
        return "/views/componentView/componenteBusqueda.xhtml?faces-redirect=true&idtag=" + idtag + "&id_cat=0&pagina=" + pagina + "&busqueda=" + this.busqueda + "&ident=2&filt=" + this.filtro;
    }

    /**
     * Metodo que retorna una lista de componentes por usuario
     *
     * @param valor el valor correspondiente al estado
     * @param render el valor correspondiente al panel que se renderizara en la
     * vista 1=Aprobado 2=Pendiente 3=Rechazado
     * @param indice que contiene los componentes que el usuario ha registrado
     * segun el estado en el que se encuentran
     */
    public void getComponentsByStatusAndUser(int valor, int render, int indice) {
        this.render = render;
        switch (valor) {
            case 1:
                this.status = "Aprobado";
                break;
            case 2:
                this.status = "Pendiente";
                break;
            case 3:
                this.status = "Rechazado";
                break;
            default:
                this.status = "";
        }
        this.listaBusqueda = this.colossal.getComponentsByStatusAndUser(this.status, this.codigo, indice);
    }

    /**
     * Metodo que abre el panel del usuario con las indicaciones para ver que
     * panel se va a mostrar y en que pagina.
     *
     * @param ident
     * @param idlista
     * @param pagina
     * @param render
     * @param paginasTotales
     * @return url con los parametros correspondientes al panel del usuario
     */
    public String openUserPanel(int ident, int idlista, int pagina, int render, int paginasTotales) {
        if (paginasTotales == 0) {
            paginasTotales = this.calcularPaginasTotales();
        }
        if (pagina <= 0) {
            pagina = 1;
        } else if (pagina > paginasTotales) {
            pagina = paginasTotales;
        }

        return "/views/usuarioViews/usuario.xhtml?faces-redirect=true&ident=" + ident + "&list=" + idlista + "&pagina=" + pagina + "&render=" + render;
    }
}
