/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.Usuario;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.security.SessionManager;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

/**
 *
 * @author AvrilFockMe
 */
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Colossal colossal;
    private List<Usuario> usuarios;
    private Usuario usuario;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }

    /**
     * Metodo init del controller usuarioBean el cual 
     * instancia el atributo usuario.
     */
    @PostConstruct
    public void init() {
        this.usuario = new Usuario();

    }

    /**
     * Metodo que devuelve el atributo usuario
     * @return usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Metodo que modifica el atributo usuario
     * @param usuario 
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Metodo que retorna la lista de usuarios
     * @return 
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Metodo que devuelve un mensaje de confirmacion al logguear un usuario
     * @return String de confirmacion
     */
    public String iniciarSesion() {

        Usuario user = this.colossal.iniciarSesion(this.usuario);

        if (user == null) {
            return "Datos Incorrectos!";
        }
        this.usuario = user;
        return "OK";

    }

    /**
     * Metodo que valida si el usuario de la session es administrador
     * @return 
     */
    public boolean esAdministrador() {
        return (SessionManager.getUsuarioSession().getTipoUsuario() == 1);
    }

    /**
     * Metodo que retorna el usuario de la session
     * @return 
     */
    public Usuario getUser() {
        return SessionManager.getUsuarioSession();
    }

    /**
     * Metodo que valida si un componente es propiedad del usuario guardado en sesion
     * @param c
     * @return true si es propietario, false si no
     */
    public boolean validarPropiedadComponente(Componente c) {
        return ((this.getUser().getCodigo().equals(c.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo())));
    }

    /**
     * Metodo que valida que un componente no es propiedad de el usuario de la
     * sesion
     * @param c
     * @return true si es propiedad, false si no es propiedad. 
     */
    public boolean validarNoPropiedadComponente(Componente c) {
        return (!(this.getUser().getCodigo().equals(c.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo())) && (this.esAdministrador() == false));
    }
}
