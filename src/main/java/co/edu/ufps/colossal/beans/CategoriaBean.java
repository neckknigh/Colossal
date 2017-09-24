/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Categoria;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.negocio.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author fakejhon666
 */
@Named(value = "categoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private Colossal colossal;
    private List<Categoria> categorias;
    private String categoria;
    private Categoria cat;
    private Categoria auxCat;
    // Guardará las categorias que el usuario seleccione
    private List<Categoria> categoriasTem;

    /**
     * Metodo que obtiene todas las categorias y crea una array de categorias
     */
    @PostConstruct
    public void init() {
        this.categoria = "";
        this.categorias = this.colossal.getAllCategorias();
        this.categoriasTem = new ArrayList<>();
    }

    /**
     * constructor del controlador, instancia la clase y crea una instancia de
     * una categoria.
     */
    public CategoriaBean() {
        this.cat = new Categoria();
    }

    /**
     * Metodo get del atributo categoria
     *
     * @return
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * MEtodo get del atributo categoriasTem
     *
     * @return
     */
    public List<Categoria> getCategoriasTem() {
        return categoriasTem;
    }

    /**
     * Metodo set del atributo categoriasTem
     *
     * @param categoriasTem
     */
    public void setCategoriasTem(List<Categoria> categoriasTem) {
        this.categoriasTem = categoriasTem;
    }

    /**
     * Metodo get del atributo cat
     *
     * @return
     */
    public Categoria getCat() {
        return cat;
    }

    /**
     * Metodo set del atributo cat
     *
     * @param cat
     */
    public void setCat(Categoria cat) {
        this.cat = cat;
    }

    /**
     * Metodo get del atributo auxCat
     *
     * @return
     */
    public Categoria getAuxCat() {
        return auxCat;
    }

    /**
     * Metodo set del atributo auxCat
     *
     * @param auxCat
     */
    public void setAuxCat(Categoria auxCat) {
        this.auxCat = auxCat;
    }

    /**
     * MEtodo set del atributo categoria
     *
     * @param categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * MEtodo get del atirbuto categorias
     *
     * @return
     */
    public List<Categoria> getCategorias() {
        return this.categorias;
    }

    /**
     * Metodo que devuelve las categorias que tengan coincidencia con el texto
     * del buscador de categorias.
     */
    public void findCategoriesByName() {
        if (this.categoria.equalsIgnoreCase("")) {
            this.categorias = this.colossal.getAllCategorias();
        } else {

            this.categorias = colossal.getCategoriasByNombre(this.categoria);
        }
    }

    /**
     * Metodo que añadirá una categoria seleccionada por el usuario, o en caso
     * de estar, la elimina
     *
     * @param c la categoria a agregar/eliminar
     */
    public void checkCategoria(Categoria c) {
//        System.out.println("entro");
        if (categoriasTem.contains(c)) {
            categoriasTem.remove(c);
        } else {
            categoriasTem.add(c);
//            System.out.println("Añadiro");
        }

//        System.out.println("cats temp: " + categoriasTem);
    }

    /**
     * Metodo que permite indicar si una categoria ya ha sido agregada a un
     * componente
     *
     * @param c
     * @return
     */
    public boolean isCatSelected(Categoria c) {
        return categoriasTem.contains(c);
    }

    /**
     * Metodo que agrega una categoria nueva, validando que el nombre no se
     * encuentre en uso y tenga el formato correcto.
     */
    public void addCategoria() {
        if (this.cat != null) {
            String msg = this.colossal.agregarCategoria(this.cat, this.categorias);
            if (msg.contains("Error")) {
                Message.addErrorMessage(msg, null);
                RequestContext.getCurrentInstance().update("formAdmin:msg");
            } else {
                Message.addMessage(msg, null);
                this.categorias = this.colossal.getAllCategorias();
                RequestContext.getCurrentInstance().update("formAdmin:listadoCategorias");
                RequestContext.getCurrentInstance().update("formAdmin:msg");
            }
            this.cat = new Categoria();
        }
    }

    /**
     * Metodo que edita una categoria, validando que el nombre sea valido y no
     * este vacio
     *
     * @param c
     */
    public void editarCategoria(Categoria c) {
        this.categorias.remove(c);
        String msg = this.colossal.editarCategoria(c, this.categorias);
        this.categorias = this.colossal.getAllCategorias();
        if (msg.contains("Error")) {
            Message.addErrorMessage(msg, null);
            RequestContext.getCurrentInstance().update("formAdmin:msg");
        } else {
            Message.addMessage(msg, null);
            RequestContext.getCurrentInstance().update("formAdmin:listadoCategorias");
            RequestContext.getCurrentInstance().update("formAdmin:msg");
        }
    }

    /**
     * Metodo que verifica si una categoria tiene o no componentes
     *
     * @param c
     * @return
     */
    public boolean contieneComponentes(Categoria c) {
        int id = c.getIdcategoria();
        int cant = this.colossal.getCantComponentesByCategory(id, "");
        if (cant > 0) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que elimina una categoria de la base de datos, validando primero
     * que esta no tenga ningun elemento asociado para poder realizar el
     * respectivo borrado.
     *
     * @param c
     */
    public void eliminarCategoria(Categoria c) {
        String msg = "Se ha eliminado la categoria " + c.getNombre();
        boolean val = false;
        if (!this.contieneComponentes(c)) {
            this.colossal.eliminarCategoria(c);
            try {
                this.categorias = this.colossal.getAllCategorias();
            } catch (Exception e) {
                val = true;
            }
        } else {
            msg = "La categoria " + c.getNombre() + " tiene componentes asociados por lo tanto no puede ser eliminada";
            val = false;
        }

        if (val) {
            msg = "Error, no se pudo eliminar la categoria " + c.getNombre();
            Message.addErrorMessage(msg, null);
        } else {
            Message.addMessage(msg, null);
            RequestContext.getCurrentInstance().update("formAdmin:listadoCategorias");
            RequestContext.getCurrentInstance().update("formAdmin:msg");
        }

    }

}
