/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.Recurso;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.negocio.FileManager;
import co.edu.ufps.colossal.negocio.Message;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Jhon Vargas
 */
@Named(value = "recursoBean")
@ViewScoped
public class RecursoBean implements Serializable {

    @EJB
    private Colossal colossal;

    private static final long serialVersionUID = 1L;

    private List<UploadedFile> imagesComponente;
    private List<UploadedFile> archivosAdicionales;
    private UploadedFile archivoMain;
    private UploadedFile logo;

    private List<Recurso> recursos;
    private Componente componente;

    private boolean hayLogo;
    private boolean hayArchivoMain;
    private boolean showPanelLoadMainFile;

    private int index;

    private List<Recurso> recursosEliminados;

    /**
     * Creates a new instance of RecursoBean
     */
    public RecursoBean() {
        this.index = 0;
        this.recursos = new ArrayList<>();
        this.imagesComponente = new ArrayList<>();
        this.archivosAdicionales = new ArrayList<>();
        this.recursosEliminados = new ArrayList<>();
    }

    /**
     * Metodo get del atributo recursosEliminados
     * @return 
     */
    public List<Recurso> getRecursosEliminados() {
        return recursosEliminados;
    }

    /**
     *  Metodo set del atributo recursosEliminados
     * @param recursosEliminados 
     */
    public void setRecursosEliminados(List<Recurso> recursosEliminados) {
        this.recursosEliminados = recursosEliminados;
    }

    /**
     *  Metodo get del atributo showPanelLoadMainFile
     * @return 
     */
    public boolean isShowPanelLoadMainFile() {
        return showPanelLoadMainFile;
    }

    /**
     * Metodo set del atributo showPanelLoadMainFile
     * @param showPanelLoadMainFile 
     */
    public void setShowPanelLoadMainFile(boolean showPanelLoadMainFile) {
        this.showPanelLoadMainFile = showPanelLoadMainFile;
    }

    /**
     * Metodo get del atributo hayArchivoMain
     * @return 
     */
    public boolean isHayArchivoMain() {
        return hayArchivoMain;
    }

    /**
     * Metodo set del atributo hayArchivoMain
     * @param hayArchivoMain 
     */
    public void setHayArchivoMain(boolean hayArchivoMain) {
        this.hayArchivoMain = hayArchivoMain;
    }

    /**
     * Metodo get del atributo hayLogo
     * @return 
     */
    public boolean isHayLogo() {
        return hayLogo;
    }

    /**
     * Metodo set del atributo hayLogo
     * @param hayLogo 
     */
    public void setHayLogo(boolean hayLogo) {
        this.hayLogo = hayLogo;
    }

    /**
     * Metodo get del atributo imagesComponente
     * @return 
     */
    public List<UploadedFile> getImagesComponente() {
        return imagesComponente;
    }

    /**
     * Metodo set del atributo imagesComponente
     * @param imagesComponente 
     */
    public void setImagesComponente(List<UploadedFile> imagesComponente) {
        this.imagesComponente = imagesComponente;
    }

    /**
     * Metodo get del atributo archivosAdicionales
     * @return 
     */
    public List<UploadedFile> getArchivosAdicionales() {
        return archivosAdicionales;
    }

    /**
     * Metodo set del atributo archivosAdicionales
     * @param archivosAdicionales 
     */
    public void setArchivosAdicionales(List<UploadedFile> archivosAdicionales) {
        this.archivosAdicionales = archivosAdicionales;
    }

    /**
     * Metodo get del atributo archivoMain
     * @return 
     */
    public UploadedFile getArchivoMain() {
        return archivoMain;
    }

    /**
     * Metodo set del atributo archivoMain
     * @param archivoMain 
     */
    public void setArchivoMain(UploadedFile archivoMain) {
        this.archivoMain = archivoMain;
    }

    /**
     * Metodo get del atributo logo
     * @return 
     */
    public UploadedFile getLogo() {
        return logo;
    }

    /**
     * Metodo set del atributo logo
     * @param logo 
     */
    public void setLogo(UploadedFile logo) {
        this.logo = logo;
    }

    /**
     * Metodo get del atributo recursos
     * @return 
     */
    public List<Recurso> getRecursos() {
        return recursos;
    }

    /**
     * Metodo set del atributo recursos
     * @param recursos 
     */
    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    /**
     * Metodo get del atributo componente
     * @return 
     */
    public Componente getComponente() {
        return componente;
    }

    /**
     * Metodo get del atributo componente
     * @param componente 
     */
    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    /**
     * Metodo que permite guardar el archivo que el usuario selecciona como logo
     *
     * @param event
     */
    public void handleUploadLogo(FileUploadEvent event) {
        this.logo = event.getFile();
        this.hayLogo = true;
//        System.out.println("Hay logo: "+this.hayLogo);
    }

    /**
     * Metodo que permite guardar el archivo que el usuario selecciona como logo
     *
     * @param event
     */
    public void handleUploadLogo2(FileUploadEvent event) {
        this.logo = event.getFile();
        this.eliminarRecursoLogo();
        this.hayLogo = true;
    }

    /**
     * Metodo que permite eliminar el logo seleccionado por el usuario para el
     * componente
     */
    public void eliminarLogo() {
        this.logo = null;
        this.hayLogo = false;
        RequestContext.getCurrentInstance().execute("eliminarLogo()");
    }

    /**
     * Metodo que permite eliminar el logo seleccionado por el usuario para el
     * componente
     */
    public void eliminarArchivoMain() {
        this.archivoMain = null;
        this.hayArchivoMain = false;
    }

    /**
     * Permite eliminar el recurso principal de un componente
     */
    public void eliminarRecursoArchivoMain() {

        this.hayArchivoMain = false;
        this.componente.getRecursoList().remove(this.findArchivoMainComponent());
        this.showPanelLoadMainFile = false;

    }

    /**
     * Permite manejar la subida de una imagen
     * @param event 
     */
    public void handleImageUpload(FileUploadEvent event) {

        this.imagesComponente.add(event.getFile());
        RequestContext.getCurrentInstance().update("form:panelImagesUploaded");

    }

    /**
     * Permite manejar la subida del archivo principal de un componente
     * @param event 
     */
    public void handleArchivoMainUpload(FileUploadEvent event) {

        this.eliminarRecursoArchivoMain();
        this.archivoMain = event.getFile();
        this.hayArchivoMain = true;

    }

    /**
     * Permite obtener la url de un recurso de un componente
     * @param r la referencia al recurso
     * @return una cadena con la ruta del archivo
     */
    public String getURLArchivos(Recurso r) {
        String recurso = "componentes/" + componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + componente.getIdComponente() + "/archivosAdicionales/" + r.getUrl();
        return recurso;
    }

    /**
     * Permite obtener la url del pdf con las especificaciones del componente
     *
     * @return la url del pdf con las especificaciones del componente
     */
    public String getURLPDF() {
        try {
            return FileManager.getURLPDF(componente);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Permite obtener la url del archivo principal
     * @return 
     */
    public String getURLArchivoMain() {
        String recurso = "componentes/" + componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + componente.getIdComponente() + "/archivoMain/" + this.findArchivoMainComponent().getUrl();
        return recurso;
    }

    /**
     * Permite obtener una url de un recurso imagen de un componente
     * @param image el recurso imagen relacionado
     * @return 
     */
    public String getURLImage(Recurso image) {
        String recurso = "componentes/" + componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + componente.getIdComponente() + "/imagenes/" + image.getUrl();
        return recurso;
    }

    /**
     * Permite eliminar una imagen via javascript en la vista
     * @param imagen la referencia con la imagen
     * @param index 
     */
    public void removeImagen(UploadedFile imagen, int index) {
        this.imagesComponente.remove(imagen);
        String funcion = "removeImage(" + index + ")";
        RequestContext.getCurrentInstance().execute(funcion);
    }

    /**
     * Metodo que permite eliminar un recurso de un componente que ha sido
     * subido con anterioridad a la biblioteca
     *
     * @param recurso la referencia del recurso a eliminar
     */
    public void removeRecurso(Recurso recurso) {
        this.componente.getRecursoList().remove(recurso);
        this.recursosEliminados.add(recurso);
    }

    /**
     * Permite cargar una imagen via javascript en la vista
     * @param index 
     */
    public void cargarImagenSeleccionada(int index) {

        String funcion = "loadImage('" + "imagen" + index + "'," + index + ")";
        RequestContext.getCurrentInstance().execute(funcion);
    }

    /**
     * Permite manejar la subida de un archivo en el sistema
     * @param event 
     */
    public void handleFileUpload(FileUploadEvent event) {

        if (!this.isArchivoRepetido(event.getFile())) {
            this.archivosAdicionales.add(event.getFile());

            RequestContext.getCurrentInstance().update("form:filesUploaded");
        } else {
            Message.addErrorMessage("El archivo " + event.getFile().getFileName() + " ya ha sido añadido!", null);

        }

    }

    /**
     * Metodo que permite conocer si un archivo ya fué añadido a la lista de
     * archivos a subir
     *
     * @param ar el archivo a verificar
     * @return true si ya está, false de otra forma
     */
    private boolean isArchivoRepetido(UploadedFile ar) {
        for (UploadedFile archivo : this.archivosAdicionales) {
            if (archivo.getFileName().equals(ar.getFileName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Metodo que permite eliminar un archivo que el usuario agregó Lo elimina
     * tanto de la lista de recursos del componente, como de la lista de
     * archivos que se cargaran,en caso que el archivo sea nuevo.
     *
     * @param archivo
     */
    public void removeArchivo(UploadedFile archivo) {
        this.archivosAdicionales.remove(archivo);

    }

    /**
     * Metodo que permite retornar la referencia al logo de un componente pasado
     * como parametro
     *
     * @return una referencia de tipo Recurso si se registro un logo, null de
     * otra forma
     */
    public Recurso findLogoComponente() {

        return this.colossal.findLogoComponente(componente);
    }

    /**
     * Metodo que permite retornar la referencia al archivoMain de un componente
     * pasado como parametro
     *
     * @return una referencia de tipo Recurso si se registro un logo, null de
     * otra forma
     */
    public Recurso findArchivoMainComponent() {
        return this.colossal.findArchivoMainComponent(componente);
    }

    /**
     * Permite obtener la url de un recurso que oficia de logo
     * @param logo el recurso logo
     * @return 
     */
    public String getLogoUrl(Recurso logo) {
        return componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + componente.getIdComponente() + "/logo/" + logo.getUrl();
    }

    /**
     * Permite obtener la url del logo del componente
     * @param c una referencia del componente asociado
     * @return una cadena con la url del logo del componennte
     */
    public String getUrlLogo(Componente c) {
        String recurso = "https://www.iconexperience.com/_img/v_collection_png/512x512/shadow/component_blue_add.png";
        Recurso r = this.colossal.findLogoComponente(c);
        if (r != null) {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/componentes/" + c.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + c.getIdComponente() + "/logo/" + r.getUrl());
            if (stream == null) {
                recurso = "https://www.iconexperience.com/_img/v_collection_png/512x512/shadow/component_blue_add.png";
            } else {
                recurso = "../../componentes/" + c.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + c.getIdComponente() + "/logo/" + r.getUrl();
                System.out.println(r.getUrl());
            }
        }
        return recurso;
    }

    /**
     * Permite eliminar el recurso del logo de un componente
     */
    public void eliminarRecursoLogo() {
        componente.getRecursoList().remove(this.findLogoComponente());
        this.hayLogo = false;
    }

    /**
     * Permite cargar la imagen del logo del componente
     */
    public void cargarImagenLogo() {
        RequestContext.getCurrentInstance().execute("cargarLogo()");
    }

    /**
     * Metodo que permite retornar un listado de todas las imagenes de un
     * componente pasado como parametro
     *
     * @return una lista de tipo Recurso con las referencias a las imagenes
     */
    public List<Recurso> findImagesComponent() {
        return this.colossal.findImagesComponent(componente);
    }

    /**
     * Metodo que permite retornar un listado de todas las imagenes de un
     * componente pasado como parametro
     *
     * @param c
     * @return una lista de tipo Recurso con las referencias a las imagenes
     */
    public List<Recurso> findImagesComponent(Componente c) {
        return this.colossal.findImagesComponent(c);
    }

    /**
     * Metodo que permite retornar un listado de todos los archivos de un
     * componente pasado como parametro
     *
     * @return una lista de tipo Recurso con las referencias a los archivos
     */
    public List<Recurso> findArchivosAdicComponent() {
        return this.colossal.findArchivosAdicComponent(componente);
    }

    /**
     * Permite saber si un componente necesita un archivo principal o no
     * @return 
     */
    public boolean needFileMain() {

        return this.findArchivoMainComponent() == null && this.archivoMain == null;
    }

    /**
     * Permite obtener la clase css para aplicar en el carousel
     * @param indice
     * @return 
     */
    public String getClaseCarousel(int indice) {
        String clase = "";
        if (indice == 0) {
            clase = "Active";
        }
        return clase;
    }

    /**
     * Permite obtener la siguiente imagen de un componente
     * @param indicador 
     */
    public void getNextImage(int indicador) {
        int limite = this.findImagesComponent().size() - 1;
        Recurso r = this.findImagesComponent().get(this.index);
        String recurso = "../../" + this.getURLImage(r);
        if (indicador == 1) {
            this.index++;
            if (index > limite) {
                this.index = 0;
            }
        } else if (indicador == 0) {
            this.index--;
            if (index < 0) {
                this.index = limite;
            }
        }
        recurso = "../../" + this.getURLImage(this.findImagesComponent().get(this.index));
        InputStream stream = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getResourceAsStream("/componentes/" + this.componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + this.componente.getIdComponente() + "/imagenes/" + r.getUrl());
        if (stream == null) {
            recurso = "https://upload.wikimedia.org/wikipedia/commons/d/da/Imagen_no_disponible.svg";
        }
        RequestContext.getCurrentInstance().execute("changeSrcImage('" + recurso + "')");
    }

    /**
     * Permite obtener la url de la imagen del recurso
     * @param r una referencia con el recurso
     * @return una cadena con la url de la imagen del recurso
     */
    public String getUrlImagenInicial(Recurso r) {
        String recurso = "../../" + this.getURLImage(r);
        InputStream stream = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getResourceAsStream("/componentes/" + this.componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + this.componente.getIdComponente() + "/imagenes/" + r.getUrl());
        if (stream == null) {
            recurso = "https://upload.wikimedia.org/wikipedia/commons/d/da/Imagen_no_disponible.svg";
        }
        return recurso;
    }
}
