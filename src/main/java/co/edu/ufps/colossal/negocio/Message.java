package co.edu.ufps.colossal.negocio;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * Clase que permite enviar mensajes informativos al usuario, estos mensajes se
 * visualizaran en los elementos growl (de Primefaces) de cada pagina.
 *
 * @author Jhon Vargas
 */
public class Message {

    /**
     * Metodo que permite añadir un mensaje informativo en pantalla
     *
     * @param sumary el titulo del mensaje
     * @param detail el cuerpo del mensaje
     */
    public static void addMessage(String sumary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, sumary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Metodo que permite añadir informacion en pantalla sin importar si la
     * pagina esta cargada o no
     *
     * @param sumary el titulo del mensaje
     * @param detail el cuerpo del mensaje
     */
    public static void addMessageFlash(String sumary, String detail) {
        //Messages get lost during redirect. You can use the flash to keep messages.
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        //Messages get lost during redirect. You can use the flash to keep messages.

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, sumary, detail);
        context.addMessage(null, message);
    }

    /**
     * Metodo que permite añadir un mensaje de error en pantalla
     *
     * @param sumary el titulo del mensaje
     * @param detail el cuerpo del mensaje
     */
    public static void addErrorMessage(String sumary, String detail) {

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, sumary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Metodo que permite mostrar un mensaje informativo en pantalla usando
     * JavaScript
     *
     * @param growl el elemento growl donde se mostrará el mensaje
     * @param msg una cadena con el mensaje a mostrar
     * @param severity una cadena indicando la severidad del mensaje (info,
     * error, warning)
     */
    public static void addMessageScript(String growl, String msg, String severity) {

        RequestContext.getCurrentInstance().execute("PF('" + growl + "').renderMessage({'summary':'" + msg + "','severity':'" + severity + "'})");
    }
}
