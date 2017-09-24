/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Tag;
import co.edu.ufps.colossal.negocio.Colossal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author AvrilFockMe
 */
@Named(value = "tagBean")
@ViewScoped
public class TagBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Colossal colossal;

    // Guardará los tags seleccionados por el usuario
    private List<Tag> tagsSeleccionados;

    private List<Tag> tagsAlreadySelected;

    /**
     * Creates a new instance of TagBean
     */
    public TagBean() {
        this.tagsAlreadySelected = new ArrayList<>();
        this.tagsSeleccionados = new ArrayList<>();
    }

    /**
     * Metodo get del atrbuto tagsSeleccionados
     * @return 
     */
    public List<Tag> getTagsSeleccionados() {
        return tagsSeleccionados;
    }

    /**
     * Metodo set del atrbuto tagsSeleccionados
     * @param tagsSeleccionados 
     */
    public void setTagsSeleccionados(List<Tag> tagsSeleccionados) {
        this.tagsSeleccionados = tagsSeleccionados;
    }

//    public List<Tag> getAllTags() {
//        return allTags;
//    }
//
//    public void setAllTags(List<Tag> allTags) {
//        this.allTags = allTags;
//    }
    
    /**
     * Metodo get del atrbuto tagsAlreadySelected 
     * @return 
     */
    public List<Tag> getTagsAlreadySelected() {
        return tagsAlreadySelected;
    }

    /**
     * Metodo set del atrbuto tagsAlreadySelected 
     * @param tagsAlreadySelected 
     */
    public void setTagsAlreadySelected(List<Tag> tagsAlreadySelected) {
        this.tagsAlreadySelected = tagsAlreadySelected;
    }

    /**
     * Metodo que permite enviar sugerencias de tags existentes al usuario
     *
     * @param query una palabra con el indicio del tag del usuario
     * @return una lista de los tags registrados en la base de datos que
     * coinciden con lo que el usuario ingresa
     *
     */
    public List<Tag> completeTag(String query) {

        query = query.trim();
        List<Tag> allTags = this.colossal.findTagsLikeNombre(query);
        List<Tag> tagsFiltrados = new ArrayList<>();

        for (Tag allTag : allTags) {
            if (!this.tagsAlreadySelected.contains(allTag)) {
                tagsFiltrados.add(allTag);
            }
        }

//        if (allTags == null) {
//            allTags = this.colossal.getAllTags();
//        }
//
//        List<Tag> tagsFiltrados = new ArrayList<>();
//
//        for (Tag t : this.allTags) {
//
//            if (t.getNombre().toLowerCase().contains(query.toLowerCase()) && !this.tagsAlreadySelected.contains(t)) {
//                tagsFiltrados.add(t);
//            }
//
//        }
//
////        // Se añade si o si una sugerencia que es exactamente igual a lo ingresado por el usuario
        Tag temp = new Tag();
        temp.setNombre(query);

        if (!this.tagsAlreadySelected.contains(temp) && !tagsFiltrados.contains(temp)) {
            if (tagsFiltrados.size() >= 5) {
                tagsFiltrados.set(0, temp);
            } else {
                tagsFiltrados.add(0, temp);
            }
        }

        return (tagsFiltrados);

    }

    /**
     * Metodo que permite seleccionar un tag y que este no se muestre 2 veces
     *
     * @param event
     */
    public void onItemSelect(SelectEvent event) {

        Tag tagSelected = (Tag) event.getObject();

        if (!this.tagsAlreadySelected.contains(tagSelected)) {
            this.tagsAlreadySelected.add(tagSelected);
        }

    }

    /**
     * Metodo que permite eliminar un tag que el usuario halla seleccionado y
     * luego borre
     *
     * @param event
     */
    public void onItemUnSelect(UnselectEvent event) {

//        System.out.println("Elimino!");
        Tag tagSelected = (Tag) event.getObject();
        this.tagsAlreadySelected.remove(tagSelected);

    }

}
