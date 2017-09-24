/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Usuario;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.negocio.Message;
import co.edu.ufps.colossal.security.SessionManager;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 * ManagedBean encargado de las funciones de session.
 *
 * @author Jhon Vargas
 */
@Named(value = "logBean")
@ViewScoped
public class LogBean implements Serializable {

    @EJB
    private Colossal colossal;

    private static final long serialVersionUID = 1L;
    private boolean status;

    private String username;

    private String password;

    private String token;

    /**
     * Creates a new instance of LogBean
     */
    public LogBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            this.token = URLDecoder.decode(request.getParameter("token"), "UTF-8");
        } catch (Exception e) {
            this.token = "";
        }

//        this.colossal.findValidToken(this.token);
//        if (tok != null) {
//            System.out.println(tok.getUsuarioCodigo().getNombre());
//        }
    }

    /**
     * Metodo que permite iniciar sesion a traves de un token proporcionado en
     * la url
     *
     * @return la ruta con la redireccion.
     */
    public String token() {
        List<Object[]> tok = this.colossal.findValidToken(this.token);

        System.out.println(tok.size());
        for (Object[] objects : tok) {

            Usuario u = this.colossal.findUserByCodigo((int) objects[3]);
            if (u != null) {
                SessionManager.saveUserSession(u);
                return "/views/componentView/componente.xhtml?faces-redirect=true";
            }
        }
        return "";
    }

    /**
     * Metodo que lanza un evento de un boton sin necesidad de pulsarlo
     * manualmente
     */
    public void triggerEventButtonToken() {
        System.out.println("Se lanzo");
        RequestContext.getCurrentInstance().execute("triggerBtnToken()");
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    /**
     * Metodo get del atributo status
     *
     * @return
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Metodo set del atributo status
     *
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Metodo get del atributo username
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo set del atributo username
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo get del atributo password
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metodo set del atributo password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo le que permite iniciar sesion en el sistema a un usuario
     *
     * @param uBean un bean con los datos ingresados por el usuario
     */
//    public void login(UsuarioBean uBean,ComponentBean cBean) {
//
//        uBean.getUsuario().setUsername(username);
//        uBean.getUsuario().setPassword(password);
//
//        if (uBean.iniciarSesion().equals("OK")) {
//            SessionManager.saveUserSession(uBean.getUsuario());
//            this.status = true;
//            cBean.openInicio();
//        } else {
//            this.status = false;
//            Message.addErrorMessage("Usuario y Contraseña Inválidos!", null);
//        }
//
//    }
    /**
     * Metodo le que permite iniciar sesion en el sistema a un usuario
     *
     * @param uBean un bean con los datos ingresados por el usuario
     * @return
     */
    public String login(UsuarioBean uBean) {

        uBean.getUsuario().setUsername(username);
        uBean.getUsuario().setPassword(password);

        if (uBean.iniciarSesion().equals("OK")) {
            SessionManager.saveUserSession(uBean.getUsuario());
            this.status = true;
            return "views/componentView/componente.xhtml?faces-redirect=true";
        }
        this.status = false;
//        Message.addMessageFlash("Usuario y Contraseña Inválidos!", null);
//        Message.addMessageScript("growlLogin","Usuario y Contraseña Inválidos!" , "error");
        Message.addErrorMessage("Usuario y Contraseña Inválidos!", null);
        return "";

    }

    /**
     * Metodo que si status es true redirecciona a la pagina principal de la
     * biblioteca
     *
     * @return
     */
    public String salida() {
        if (this.status) {
            return "views/componentView/componente?faces-redirect=true";
        } else {
            return null;
        }
    }

    /**
     * Metodo que le permite cerrar la session en el sistema a un usuario.
     *
     * @param cBean un bean con el comportamiento necesario para cerrar la
     * session
     */
    public void cerrarSesion(ComponentBean cBean) {
        cBean.getFacesContext().getExternalContext().invalidateSession();
        try {
            cBean.getFacesContext().getExternalContext().redirect(((HttpServletRequest) cBean.getFacesContext().getExternalContext().getRequest()).getContextPath());
        } catch (IOException ex) {
        }
    }

}
