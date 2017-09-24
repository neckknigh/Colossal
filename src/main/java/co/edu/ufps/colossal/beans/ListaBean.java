/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ListaComponente;
import co.edu.ufps.colossal.entities.ListaComponentehasComponente;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.negocio.Message;
import co.edu.ufps.colossal.security.SessionManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AvrilFockMe
 */
@Named(value = "listaBean")
@ViewScoped
public class ListaBean implements Serializable {

    @EJB
    private Colossal colossal;

    private List<ListaComponente> listas;
    private ListaComponente lista;
    private boolean showPanelCrearLista;
    private String nombreListaTemp;

    /**
     * Creates a new instance of ListaBean inicializa el panel de creacion de la
     * busqueda en false instancia una nieva lista de componentes.
     */
    public ListaBean() {
        this.showPanelCrearLista = false;
        this.lista = new ListaComponente();

    }

    /**
     * Metodo get del atributo nombreListaTemp.
     *
     * @return
     */
    public String getNombreListaTemp() {
        return nombreListaTemp;
    }

    /**
     * Metodo set del atributo nombreListaTemp
     *
     * @param nombreListaTemp
     */
    public void setNombreListaTemp(String nombreListaTemp) {
        this.nombreListaTemp = nombreListaTemp;
    }

    /**
     * Metodo get del atributo listas
     *
     * @return
     */
    public List<ListaComponente> getListas() {
        return listas;
    }

    /**
     * Metodo set del atributo listas
     *
     * @param listas
     */
    public void setListas(List<ListaComponente> listas) {
        this.listas = listas;
    }

    /**
     * Metodo get del atributo lista
     *
     * @return
     */
    public ListaComponente getLista() {

        return lista;
    }

    /**
     * metodo que obtiene las listas del usuario dentro del atributo listas
     */
    public void obtenerListasUsuario() {

        this.listas = this.colossal.getListasUser(SessionManager.getUsuarioSession());

    }

    /**
     * metodo set del atributo lista
     *
     * @param lista
     */
    public void setLista(ListaComponente lista) {
        this.lista = lista;
    }

    /**
     * Metodo que permite crear una lista en la base de datos para el usuario en
     * sesion
     *
     * @param componente el componente al cual añadir ala lista
     */
    public void crearLista(Componente componente) {

//        System.out.println("El nombre de la lista antes de enviar " + lista.getNombreLista());
        String msg = this.colossal.createNewList(componente, lista);
//        System.out.println("msg es: " + msg);

        if (msg.toLowerCase().contains("error")) {
            Message.addErrorMessage(msg, "");
        } else {
            Message.addMessage(msg, "");
        }
//
        this.lista = new ListaComponente();
        this.obtenerListasUsuario();
        RequestContext.getCurrentInstance().execute("desbloquearOverlayPanel()");

    }

    /**
     * Metodo que crea una lista, validando que el nombre no sea vacio, que no
     * se encuentre ya registrado o en uso dicho nombre
     */
    public void crearLista() {
        if (this.lista.getNombreLista() == null || this.lista.getNombreLista().isEmpty()) {
            Message.addErrorMessage("El nombre de la lista no puede estar vacío!", "");
            return;
        }
        this.lista.setNombreLista(this.lista.getNombreLista().trim());
        ListaComponente listaExistente = this.colossal.findListaByName(this.lista.getNombreLista());
        String msg;
        //existe la lista, no crear
        if (listaExistente != null) {
            Message.addErrorMessage("La lista ya se encuentra registrada!", "");
            this.lista = new ListaComponente();
            return;

        } //no existe la lista, crear
        else {
            msg = this.colossal.crearLista(lista);
        }

        if (msg.contains("error")) {
            Message.addErrorMessage(msg, "");
        } else {
            RequestContext.getCurrentInstance().execute("setCollapse(3)");
            RequestContext.getCurrentInstance().update("formA:panelGroupLista");
            Message.addMessage(msg, "");
        }
        this.lista = new ListaComponente();
    }

    /**
     * Metodo usado para controlar cuando se muestra o no el panel de crear
     * nueva lista
     */
    public void changeShowPanelCrearLista() {
//        System.out.println("entro");
        this.showPanelCrearLista = !this.showPanelCrearLista;
    }

    /**
     * Metodo boolean que valida si el panel de creacion de lista se esta visua-
     * -lizando.
     *
     * @return boolean true si se esta mostrando, false si no
     */
    public boolean isShowPanelCrearLista() {
        return showPanelCrearLista;
    }

    /**
     * metodo set ddel atributo showPanelCrearLista
     *
     * @param showPanelCrearLista
     */
    public void setShowPanelCrearLista(boolean showPanelCrearLista) {
        this.showPanelCrearLista = showPanelCrearLista;
    }

    /**
     * Metodo que selecciona las listas a las cuales se va a agregar un compo-
     * -nente.
     *
     * @param lista
     * @param componente
     */
    private void checkList(ListaComponente lista, Componente componente) {
        System.out.println("entrando");
        String msg = this.colossal.addComponentToList(lista, componente, true);
        if (msg.contains("error")) {
            Message.addErrorMessage(msg, null);
        } else {
            Message.addMessage(msg, null);
        }

        Message.addMessageScript("msgsDetalles", msg, "info");
//        RequestContext.getCurrentInstance().update("formDetalles:panelGroupLista");
        RequestContext.getCurrentInstance().update(":formDetalles:msgsDetalles");

    }

    /**
     * Metodo agrega un componente a una lista
     *
     * @param componente
     */
    public void checkLista(Componente componente) {

//       
        RequestContext.getCurrentInstance().execute("PF('bui').show()");
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String name = map.get("index"); // name attribute of node

        String msg = this.colossal.saveOnList(this.listas.get(Integer.parseInt(name)), componente);
        if (msg.contains("error")) {
            Message.addErrorMessage(msg, null);
        } else {
            Message.addMessage(msg, null);
        }
        Message.addMessageScript("msgsDetalles", msg, "info");
        RequestContext.getCurrentInstance().update(":formDetalles:msgsDetalles");
        RequestContext.getCurrentInstance().execute("desbloquearOverlayPanel()");

    }

    /**
     * Metodo que permite verificar si un componente se encuentra en una lista
     *
     * @param lista
     * @param componente
     * @return
     */
    public boolean isOnList(ListaComponente lista, Componente componente) {
        ListaComponentehasComponente lh = this.colossal.isOnList(lista, componente);

        return lh != null;
    }

    /**
     * Metodo que se encarga de eliminar una lista personalizada de un usuario
     *
     * @param busBean
     * @return
     */
    public String eliminarLista(BusquedaBean busBean) {

        String msg = this.colossal.eliminarLista(busBean.getListaName());
        SessionManager.saveParameter("msgLista", msg);
        return busBean.openUserPanel(5, 0, 1, 1, 1);

    }

    /**
     * Metodo que permite mostrar un mensaje de estado despues de eliminar una
     * lista y recargar la pagina actual
     */
    public void chechMsgListaEliminada() {
        String msg;

        try {
            msg = (String) SessionManager.getParameter("msgLista");
            SessionManager.removeParameter("msgLista");
            if (msg.contains("Error")) {
                Message.addErrorMessage(msg, null);
            } else {
                Message.addMessage(msg, null);
            }
            RequestContext.getCurrentInstance().update("formB:msgsUsuario");
        } catch (Exception e) {
        }

        try {
            msg = (String) SessionManager.getParameter("edicionLista");
            SessionManager.removeParameter("edicionLista");
            if (msg.contains("Error")) {
                Message.addErrorMessage(msg, null);
            } else {
                Message.addMessage(msg, null);
            }
            RequestContext.getCurrentInstance().update("formB:msgsUsuario");
        } catch (Exception e) {
        }
    }

    /**
     * MEtodo que edita el nombre de una lista, validando que no este vacia y
     * que los nombres introducidos sean validos
     *
     * @param nombreLista
     */
    public void editarLista(String nombreLista) {

        nombreListaTemp = nombreListaTemp.trim();
        if (nombreListaTemp.isEmpty()) {
            this.nombreListaTemp = nombreLista;
            Message.addErrorMessage("El nombre de la lista no puede estar vacío!", null);
            RequestContext.getCurrentInstance().update("formB:txtNewNameList");
            RequestContext.getCurrentInstance().update("formB:msgsUsuario");
            return;
        }

        if (!nombreLista.equals(this.nombreListaTemp)) {
            String msg = this.colossal.editarLista(nombreLista, nombreListaTemp);

            if (msg.contains("Error")) {
                this.nombreListaTemp = nombreLista;
                Message.addErrorMessage(msg, null);
                RequestContext.getCurrentInstance().update("formB:txtNewNameList");
                RequestContext.getCurrentInstance().update("formB:msgsUsuario");

            } else {
                SessionManager.saveParameter("edicionLista", msg);
                RequestContext.getCurrentInstance().execute("location.reload();");
            }
        }

    }

    /**
     * Metodo que checkea el check box correspondiente a la lista seleccionada
     *
     * @param box
     * @param lista
     * @param componente
     */
    public void verificarBox(int box, ListaComponente lista, Componente componente) {
        List<ListaComponente> listasComp = this.colossal.loadListasComponent(componente);
        for (ListaComponente listaComponente : listasComp) {
            if (listaComponente.getIdlistaComponente().equals(lista.getIdlistaComponente())) {
                RequestContext.getCurrentInstance().execute("checkearBox('box" + box + "')");
            }
        }
    }

}
