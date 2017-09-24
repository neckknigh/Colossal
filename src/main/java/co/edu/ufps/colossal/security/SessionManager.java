/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.security;

import co.edu.ufps.colossal.entities.Usuario;
import javax.faces.context.FacesContext;

/**
 * Clase que permite manejar la session del usuario.
 * @author Jhon Vargas
 */
public class SessionManager {

    /**
     * Metodo que permite obtener el usuario guardado en sesion
     *
     * @return una referencia con el usuario en sesion
     */
    public static Usuario getUsuarioSession() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    /**
     * Metodo que permite guardar un usuario en sesion
     *
     * @param usuario el usuario a guardar
     */
    public static void saveUserSession(Usuario usuario) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
    }

    /**
     * Metodo que permite guardar un parametro simple en la sesion del usuario
     *
     * @param name una cadena con el nombre del parametro a gaurdar
     * @param parameter el parametro a guardar en la session.
     */
    public static void saveParameter(String name, Object parameter) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(name, parameter);
    }

    /**
     * metodo que permite obtener un parametro simple de la sesion
     *
     * @param name el nombre del parametro a obtener de la session.
     * @return una referencia con el parametro de la session.
     */
    public static Object getParameter(String name) {
        Object parameter = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(name);
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(name);
        return parameter;
    }

    /**
     * Metodo que permite remover un parametro almacenado en la sesion del usuario
     * @param name el nombre del parametro a eliminar de la session.
     */
    public static void removeParameter(String name) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(name);
    }

    /**
     * Metodo que elimina parametros guardados en la sesion
     */
    public static void removeSessionParameters(){
        SessionManager.removeParameter("cadenaServicios");
        SessionManager.removeParameter("cadenaTags");
        SessionManager.removeParameter("cadenaTextos");
        SessionManager.removeParameter("fecha1");
        SessionManager.removeParameter("fecha2");
        SessionManager.removeParameter("identifier1");
        SessionManager.removeParameter("identifier2");
    }
}
