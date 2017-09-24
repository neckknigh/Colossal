package co.edu.ufps.colossal.beans;

import co.edu.ufps.colossal.entities.Categoria;
import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ComponenteHasDependency;
import co.edu.ufps.colossal.entities.Recurso;
import co.edu.ufps.colossal.entities.Tag;
import co.edu.ufps.colossal.entities.Usuario;
import co.edu.ufps.colossal.negocio.Colossal;
import co.edu.ufps.colossal.negocio.Message;
import co.edu.ufps.colossal.security.SessionManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
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
@Named(value = "componentBean")
@ViewScoped
public class ComponentBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private Colossal colossal;
    private Componente componente;
    private final String root_folder;
    private int panelActivo;
    private int index;
    private String versionComp;
    private String show;

    /**
     * Constructor que crea una instancia de componentBean inicializa los
     * valores de root_folder, panel activo, index y crea una instancia del
     * atributo componente
     */
    public ComponentBean() {
        this.componente = new Componente();

        this.root_folder = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/");
        this.panelActivo = 1;
        this.index = 1;

    }

    /**
     * Metodo que aumenta las visualizaciones de un componente cuando este se
     * accede al panel correspondiente a su visualizacion
     */
    public void aumentarVisualizaciones() {
        this.colossal.aumentarVisualizacionesComponente(componente);
    }

    /**
     * metodo get del atributo versionComp.
     *
     * @return
     */
    public String getVersionComp() {
        return versionComp;
    }

    /**
     * Metodo set del atributo versionComp.
     *
     * @param versionComp
     */
    public void setVersionComp(String versionComp) {
        this.versionComp = versionComp;
    }

    /**
     * Metodo get del atributo index.
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * Metodo set del atributo index
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Metodo que amuenta el valor del indice.
     */
    public void aumentarIndex() {
        this.index++;

    }

    /**
     * Metodo get del atributo panelActivo
     *
     * @return
     */
    public int getPanelActivo() {
        return panelActivo;
    }

    /**
     * Meetodo set del atributo panelActivo
     *
     * @param panelActivo
     */
    public void setPanelActivo(int panelActivo) {
        this.panelActivo = panelActivo;
        RequestContext.getCurrentInstance().update("form:padding");

    }

    public void showUploadMethod() {
        this.panelActivo = 3;

    }

    /**
     * Metodo get del atributo componente
     *
     * @return
     */
    public Componente getComponente() {
        return componente;
    }

    /**
     * Metodo set del atrbiuto componente
     *
     * @param componente
     */
    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    /**
     * Metodo get del atributo show
     *
     * @return
     */
    public String getShow() {
        return show;
    }

    /**
     * MEtodo set del atributo show
     *
     * @param show
     */
    public void setShow(String show) {
        this.show = show;
    }

    /**
     * Metodo que redirecciona a la vista de Subida del componente
     *
     */
    public void openUploadComponent() {
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/faces/views/componentView/componenteUpload.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en redireccion");
        }
    }

    /**
     * Metodo que devuelve la url para abrir el panel de cargar un componente
     *
     * @return
     */
    public String abrirUploadComponent() {
        return "/views/componentView/componenteUpload.xhtml?faces-redirect=true";
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    /**
     * Metodo que devuelve una instancia del FacesContext al inicio de una
     * request.
     *
     * @return
     */
    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Metodo que devuelve la url para abrir el panel de edicion de un
     * componente
     *
     * @return String con la url del panel de edicion
     */
    public String openEdicionPanel() {
        SessionManager.saveParameter("idComponente", componente.getIdComponente());
        return "/views/componentView/componente-edicion.xhtml?faces-redirect=true";
    }

    /**
     * Metodo devuelve el panel de administracion
     *
     * @return String con la url del panel de administracion
     */
    public String openAdminPanel() {

        return "/faces/views/usuarioViews/administrador.xhtml?faces-redirect=true&render=1&pagina=1&ident=1";

    }

    /**
     * Metodo que devuelve al usuario al inicio.
     */
    public void openInicio() {
        try {
            getFacesContext().getExternalContext().
                    redirect(getRequest().getContextPath() + "/faces/views/componentView/componente.xhtml");
        } catch (Exception e) {
            try {
                getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/faces/views/componentView/componente.xhtml");
            } catch (Exception ee) {

            }
        }
    }

    /**
     * Metodo que devuelve la url para redireccion al inicio
     *
     * @return
     */
    public String abrirInicio() {
        return "/views/componentView/componente.xhtml?faces-redirect=true";
    }

    /**
     * Metodo get del atributo root_folder
     *
     * @return
     */
    public String getRoot_folder() {
        return root_folder;
    }

    /**
     * Metodo que permite registrar un componente en el sistema
     *
     * @param mBean es un managed bean con la informacion referente a los
     * metodos del componente
     * @param tBean es un managed bean con la informacion referente a los tags
     * del componente
     * @param catBean es un managed bean con la informacion referente a las
     * categorias del componente
     * @param rBean es un managed bean con la informacion referente a los
     * recursos de un componente
     * @param pBean es un managed bean con la informacion referente a la
     * peticion del componente
     * @param dBean es un managed bean con la informacion referente a las
     * depedencias del componente
     */
    public void guardarComponente(MetodoBean mBean, TagBean tBean, CategoriaBean catBean, RecursoBean rBean, PeticionSubidaBean pBean, DependenciaBean dBean) {

        if (!rBean.isHayArchivoMain()) {
            Message.addErrorMessage("Debes adjuntar el archivo principal para el componente!", null);
            return;
        }

        // Se guardan todos los datos en una sola coleccion
        Map info = new HashMap();

        // Guardando datos del panel 1
        info.put("usuario", SessionManager.getUsuarioSession());
        this.componente.setVersion(versionComp);
        info.put("raiz", root_folder);
        info.put("componente", this.componente);
        info.put("tags", tBean.getTagsSeleccionados());
        info.put("dependencias", dBean.getDependenciasAlreadySelected());
        info.put("categorias", catBean.getCategoriasTem());
        info.put("logo", rBean.getLogo());
        // Guardando datos del panel 1

        // Guardando datos del panel 2
        info.put("metodos", mBean.getMetodos());
        // Guardando datos del panel 2

        // Guardando datos del panel 3
        info.put("archivoMain", rBean.getArchivoMain());
        info.put("imagenes", rBean.getImagesComponente());
        info.put("archivos", rBean.getArchivosAdicionales());
        info.put("observaciones", pBean.getPeticion().getObservaciones());
        // Guardando datos del panel 3

        String mensaje = this.colossal.registrarComponente2(info);
        if (mensaje.equals("OK")) {
            Message.addMessage("El componente ha sido registrado satisfactoriamente!", null);
            this.panelActivo = 4;
            RequestContext.getCurrentInstance().update("form:padding");
        } else {
            Message.addErrorMessage(mensaje, null);
        }

        RequestContext.getCurrentInstance().execute("PF('bui').hide()");
    }

    /**
     * Metodo que devuelve una lista con todas las categorias existentes
     *
     * @return list<Categoria> lista con las cateogiras
     */
    public List<Categoria> getCategorias() {
        return this.colossal.getAllCategorias();

    }

    /**
     * Metodo que permite mostrar el segundo panel haciendo las validaciones
     * correspondientes
     *
     * @param tagBean un managed bean con informacion de los tags
     * @param catBean una managed bean con informacion de categorias
     * seleccionadas por el user
     */
    public void showSecondPanel(TagBean tagBean, CategoriaBean catBean) {

        if (catBean.getCategoriasTem().isEmpty()) {
            Message.addMessage("Debes Seleccionar al menos 1 categoria!", "");
            return;
        }

        try {

            //Se elimina valores repetidos ingresados forzosamente por el usuario
            Set<Tag> hs = new HashSet<>();
            hs.addAll(tagBean.getTagsSeleccionados());
            ArrayList temp = new ArrayList(hs);
            tagBean.setTagsSeleccionados(temp);

            if (tagBean.getTagsSeleccionados().size() < 3) {
                Message.addErrorMessage("Debes ingresar al menos 3 Tags para el componente!", "");
                return;
            }
        } catch (NullPointerException e) {
            Message.addErrorMessage("Debes ingresar al menos 3 Tags para el componente!", "");
            return;
        }

        this.versionComp = this.versionComp.trim();
        this.componente.setNombre(this.componente.getNombre().trim());
        this.componente.setDescripcion(this.componente.getDescripcion().trim());
        this.componente.setInstrucciones(this.componente.getInstrucciones().trim());

        String msg = this.validarCamposComponente();

        if (!msg.equals("OK")) {
            Message.addErrorMessage(msg, "");
            return;
        }

        if (!Pattern.matches("^(\\d+)((.\\d+)?)+$", this.versionComp)) {

            Message.addErrorMessage("La Versión debe ser un número válido. Ej: 1.0, 1.1.5", "");
            return;

        }
        if (this.contieneCharsProhibidos(versionComp)) {
            Message.addErrorMessage("La Versión debe ser un número válido. Ej: 1.0, 1.1.5", "");
            return;
        }

        if (this.componente.getVersion() != null) {

            Usuario uSession = SessionManager.getUsuarioSession();

            if (uSession.getTipoUsuario() == 1 && uSession.getCodigo().equals(this.componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo())) {
                if (this.isOlderVersion(versionComp)) {
                    Message.addErrorMessage("Al realizar cambios, se debe actualizar la versión del componente a una más reciente!\n\nVersión Actual: " + this.componente.getVersion(), "");
                    return;
                }
            } else if (uSession.getTipoUsuario() == 0) {
                if (this.isOlderVersion(versionComp)) {
                    Message.addErrorMessage("Al realizar cambios, se debe actualizar la versión del componente a una más reciente!\n\nVersión Actual: " + this.componente.getVersion(), "");
                    return;
                }
            }

        }

        this.panelActivo = 2;

        RequestContext.getCurrentInstance().update("form:padding");
    }

    /**
     * Metodo que permite validar que no se ingresen campos vacios
     *
     * @return
     */
    private String validarCamposComponente() {
        String msg = "OK";

        if (this.componente.getNombre().isEmpty()) {
            msg = "Debes indicar un nombre para el componente";
        } else if (this.componente.getDescripcion().isEmpty()) {
            msg = "Debes indicar una descripción para el componente";
        } else if (this.componente.getInstrucciones().isEmpty()) {
            msg = "Debes indicar las instrucciones para el componente";
        }

        return msg;

    }

    /**
     * Metodo que permite validar si la version del componente es vieja o no
     *
     * @param newVersion una cadena con la nueva version del componente
     * @return true de ser mas vieja, false de otra forma
     */
    private boolean isOlderVersion(String newVersion) {
        System.out.println("entro al older" + newVersion);
        boolean ret = false;
        String[] arraynewVersion = newVersion.split("\\.");
        String[] arrayOlderVersion = this.componente.getVersion().split("\\.");

        for (int i = 0; i < arraynewVersion.length; i++) {

            if (i == arrayOlderVersion.length) {
                ret = false;
                break;
            }

            int a = Integer.parseInt(arraynewVersion[i]);
            int b = Integer.parseInt(arrayOlderVersion[i]);

            if (a == b) {
                ret = true;
            } else if (a < b) {
                ret = true;
                break;
            } else {
                ret = false;
                break;
            }

        }

        return ret;
    }

    /**
     * Metodo que permite determinar si la version contiene caracteres no
     * permitidos
     *
     * @param version una cadena con la version del componente
     * @return true si la version contiene caracteres no permitidos, false
     */
    private boolean contieneCharsProhibidos(String version) {
//        System.out.println("Versiin es: "+version);
        char[] letras = version.toCharArray();
        for (char letra : letras) {
            if (!Character.isDigit(letra) && letra != '.') {
                return true;
            }
        }

        return false;
    }

    /**
     * Metodo que permite mostrar el tercer panel
     *
     * @param mBean un managed bean con la informacion de los metodos
     */
    public void showThirdPanel(MetodoBean mBean) {

        if (mBean.getMetodos().isEmpty()) {
            Message.addErrorMessage("Debes registrar al menos 1 método para el componente!", null);
            return;
        }

        this.panelActivo = 3;
        RequestContext.getCurrentInstance().update("form:padding");

    }

    /**
     * Metodo que devuelva una lista con los 6 componentes recientemente
     * publicados dentro de la biblioteca de componentes
     *
     * @return
     */
    public List<Componente> getComponentesRecientes() {
        return this.colossal.getComponentesRecientes();
    }

    /**
     * Metodo que verifica que un componente visualizado no este en null, en
     * caso de estarlo redirecciona al inicio.
     */
    public void checkComponente() {
        if (this.componente == null) {
            this.openInicio();
        }

    }

    /**
     * Metodo que permite buscar un componente por id en el sistema
     */
    public void buscarComponente() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String cadenaComp = request.getParameter("id_componente");
        int id_componente = 0;
        try {
            int comp = Integer.parseInt(cadenaComp);
            id_componente = comp;
        } catch (NumberFormatException e) {
            id_componente = 0;
        }
        this.componente = this.colossal.getComponente(id_componente);
        if (this.componente != null) {
            List<ComponenteHasDependency> ch = this.colossal.findByComponentOwner(componente);
            this.componente.setComponenteHasDependencyList(ch);
            Collections.reverse(this.componente.getRevisionList());

            if (!(this.componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo().intValue() == SessionManager.getUsuarioSession().getCodigo().intValue()) && SessionManager.getUsuarioSession().getTipoUsuario() == 0) {
                if (!this.componente.getPeticionSubidaList().get(0).getEstadoIdestado().getEstado().equalsIgnoreCase("Aprobado")) {
                    this.componente = null;
                }
            }
        }
        if (this.componente != null) {
            if (this.componente.getPeticionSubidaList().get(0).getEstadoIdestado().getEstado().equalsIgnoreCase("Eliminado") && SessionManager.getUsuarioSession().getTipoUsuario() == 0) {
                this.componente = null;
            }
        }

    }

    /**
     * Metodo que permite eliminar un componente de la biblioteca
     *
     * @return una cadena con informacion relativa al proceso de borrado.
     */
    public String eliminarComponente() {

        //Messages get lost during redirect. You can use the flash to keep messages.
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        //Messages get lost during redirect. You can use the flash to keep messages.

        String msg = this.colossal.eliminarComponente(this.componente);
        if (msg.contains("error")) {
            Message.addErrorMessage(msg, null);
        } else {
            Message.addMessage(msg, null);
            return this.abrirInicio();

        }
        return "";

    }

    /**
     * Metodo que permite identificar si debe mostrarse el panel
     *
     * @param panel el panel a evaluar
     * @return true si panel es igual a panel activo
     */
    public boolean isActivePanel(int panel) {
        return panelActivo == panel;
    }

    /**
     * Metodo que permite cargar un componente, para que este pueda ser editado
     *
     * @param tagBean un managed bean que contiene la informacion referente a
     * los tags del componente
     * @param catBean un managed bean que contiene la informacion referente a
     * lass categorias del componente
     * @param mBean un managed bean que contiene la informacion referente a los
     * metodos del componente
     * @param rBean un managed bean que contiene la informacion referente a los
     * recursos del componente
     * @param dBean un managed bean que contiene la informacion referente a lass
     * dependencias del componente
     */
    public void loadComponent(TagBean tagBean, CategoriaBean catBean, MetodoBean mBean, RecursoBean rBean, DependenciaBean dBean) {

        try {
            //se pide el componente a editar
            this.componente = this.colossal.getComponente(((Integer) SessionManager.getParameter("idComponente")));

        } catch (Exception e) {
            this.openInicio();
            return;
        }

        if (SessionManager.getUsuarioSession().getTipoUsuario() == 1) {

            if (!SessionManager.getUsuarioSession().getCodigo().equals(this.componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo())) {
                this.versionComp = this.componente.getVersion();
            }

        }

        //se cargan los tags que tendrá
        List<Tag> tags = this.colossal.loadTagsComponent(this.componente);
        tagBean.setTagsAlreadySelected(tags);
        tagBean.setTagsSeleccionados(tags);

        // se cargan las dependencias
//        dBean.setDependenciasAlreadySelected(componente.getComponenteHasDependencyList1());
        dBean.setComponente(componente);
        List<ComponenteHasDependency> ch = this.colossal.findByComponentOwner(componente);
        for (ComponenteHasDependency componenteHasDependency : ch) {
            dBean.getDependenciasAlreadySelected().add(componenteHasDependency.getComponenteDependency());
//            System.out.println(dBean.getDependenciasAlreadySelected());
            dBean.getDependencias().add(componenteHasDependency.getComponenteDependency());
        }
        // Hasta este punto las categorias se van a cargar automaticamente desde la vista atraves de un llamado a checkCategories(CategoriaBean catBean)
        rBean.setComponente(this.componente);
//        Recurso logoR  = rBean.findLogoComponente();
//        rBean.setHayLogo( logoR !=null  );

        Recurso mainFile = rBean.findArchivoMainComponent();
        rBean.setShowPanelLoadMainFile(mainFile != null);

        mBean.setMetodos(this.componente.getMetodoList());

    }

    /**
     * Permite checkear los checkboxes manualmente de las categorias que tiene
     * un componente
     *
     * @param catBean
     */
    public void checkCategories(CategoriaBean catBean) {

        List<Categoria> categoriasTemp = this.colossal.loadCategoriesComponent(this.componente);
        String checkboxex = "";
        for (Categoria cat : categoriasTemp) {
            int index = catBean.getCategorias().indexOf(cat);
            if (index >= 0) {
                checkboxex += "checkbox" + index + ",";
            }
        }

        String funcion = "checkCategories('" + checkboxex + "')";
        RequestContext.getCurrentInstance().execute(funcion);

    }

    /**
     * Metodo que obtiene el usuario propietario del componente guardado en el
     * atributo componente
     *
     * @return Usuario dueño del componente
     */
    public Usuario getUsuarioComponente() {
//        System.out.println(this.componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo());
        Usuario u = this.colossal.getUsuarioByComponente(this.componente);
        return u;
    }

    /**
     * Metodo que devuelve las imagenes subidas del componente
     *
     * @return Lista de recursos que contiene las imagenes subidas del
     * componetne
     */
    public List<Recurso> getImagenesSubidasComponente() {
        return this.colossal.getImagenesSubidasComponente(componente);
    }

    /**
     * Metodo que obtiene los archivos subidos de un componente
     *
     * @return lista de recursos correspondientes a los archivos opcionales de
     * un componetne
     */
    public List<Recurso> getArchivosSubidosComponente() {
        return this.colossal.getArchivosSubidosComponente(componente);
    }

    /**
     * Metodo que permite editar un componente en el sistema
     *
     * @param tagBean un managed bean que contiene la informacion referente a
     * los tags del componente
     * @param catBean un managed bean que contiene la informacion referente a
     * las categorias del componente
     * @param mBean un managed bean que contiene la informacion referente a los
     * metodos del componente
     * @param rBean un managed bean que contiene la informacion referente a los
     * recursos del componente
     * @param revBean un managed bean que contiene la informacion referente a
     * las revisiones del componente
     * @param dBean un managed bean que contiene la informacion referente a los
     * dependencias del componente
     * @return una cadena con informacion referente al proceso de edicion del
     * componente
     */
    public String modifyComponent(TagBean tagBean, CategoriaBean catBean, MetodoBean mBean, RecursoBean rBean, RevisionBean revBean, DependenciaBean dBean) {

        RequestContext.getCurrentInstance().execute("PF('bui').show()");
        this.componente.setVersion(versionComp);

        // Se guardan todos los datos en una sola coleccion
        Map info = new HashMap();

        info.put("rootFolder", this.root_folder);
        info.put("componente", this.componente);
        info.put("tags", tagBean.getTagsSeleccionados());
        info.put("dependencias", dBean.getDependenciasAlreadySelected());
        info.put("categorias", catBean.getCategoriasTem());

        info.put("recursosEliminados", rBean.getRecursosEliminados());
        info.put("archivoMain", rBean.getArchivoMain());
        info.put("archivos", rBean.getArchivosAdicionales());
        info.put("imagenes", rBean.getImagesComponente());
        info.put("logo", rBean.getLogo());
        info.put("eliminoRecursoLogo", rBean.findLogoComponente() == null);

        info.put("metodos", mBean.getMetodos());

        info.put("eliminoRecursoMain", rBean.findArchivoMainComponent() == null);

        info.put("revision", revBean.getRevision());

        //Messages get lost during redirect. You can use the flash to keep messages.
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        //Messages get lost during redirect. You can use the flash to keep messages.

        String msg = this.colossal.modificarComponente(info);

        if (msg.contains("pendiente")) {
            Message.addMessage(msg, null);
            RequestContext.getCurrentInstance().update("form:msgsEdicion");
            return this.abrirInicio();
//            Message.addMessageScript("msgsComponente", "El componente ha sido actualizado satisfactoriamente!", "info");Message.addMessageScript("msgsComponente", "El componente ha sido actualizado satisfactoriamente!", "info");
        } else if (msg.equals("OK")) {
            Message.addMessage("El componente ha sido actualizado satisfactoriamente!", null);
            RequestContext.getCurrentInstance().update("form:msgsEdicion");
            return this.abrirInicio();
//            Message.addMessageScript("msgsComponente", "El componente ha sido actualizado satisfactoriamente!", "info");Message.addMessageScript("msgsComponente", "El componente ha sido actualizado satisfactoriamente!", "info");
        } else {
            Message.addErrorMessage(msg, null);
            RequestContext.getCurrentInstance().update("form:msgsEdicion");
        }
        RequestContext.getCurrentInstance().execute("PF('bui').hide()");
        return "";

    }

    public void cantModify(RevisionBean rBean) {

        if (rBean.getRevision().getDescripcionCambios().trim().equals("")) {
            Message.addErrorMessage("Por favor, indica los detalles de la versión!", null);
        } else {
            Message.addErrorMessage("Asegurate de adjuntar el archivo principal del componente!", null);
        }

    }

    /**
     * Metodo que devuelve la clase bootstrap que permite que un panel collapse
     * se despliegue
     *
     * @param indice
     * @return String con el nombre de la clase bootstrap que permite que un
     * panel se despliegue
     */
    public String getClassColapse(int indice) {
        String clase = "";
        if (indice == 1) {
            clase = "in";
        }
        return clase;
    }

    /**
     * Metodo que devuelve el estado de un componente.
     *
     * @return String con el estado del componetne
     */
    public String getEstadoComponente() {
        return this.componente.getPeticionSubidaList().get(0).getEstadoIdestado().getEstado();
    }

    /**
     * Metodo que devuelve un booleano validando si el estado de un componente
     * es permitido o no
     *
     * @param c
     * @return true si es aprobado, false si el valor es diferente
     */
    public boolean getEstadoComponente(Componente c) {
        return c.getPeticionSubidaList().get(0).getEstadoIdestado().getEstado().equalsIgnoreCase("Aprobado");
    }

    /**
     * MEtodo que devuelve un booleano si el usuario no es el propietario del
     * componente pero es administrador
     *
     * @return true si es administrador y no el dueño, false en cualquier otro
     * caso.
     */
    public boolean desactivarPropiedadComponente() {
        Usuario uSession = SessionManager.getUsuarioSession();
        return uSession.getTipoUsuario() == 1 && !SessionManager.getUsuarioSession().getCodigo().equals(this.componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo());
    }

}
