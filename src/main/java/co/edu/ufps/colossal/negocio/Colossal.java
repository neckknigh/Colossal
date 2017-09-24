/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.negocio;

import co.edu.ufps.colossal.dao.CategoriaDAO;
import co.edu.ufps.colossal.dao.CategoriaHasComponenteDAO;
import co.edu.ufps.colossal.dao.ComponenteDAO;
import co.edu.ufps.colossal.dao.ComponenteHasDependencyDAO;
import co.edu.ufps.colossal.dao.ComponenteHasTagDAO;
import co.edu.ufps.colossal.dao.EstadoDAO;
import co.edu.ufps.colossal.dao.IncidenciaDAO;
import co.edu.ufps.colossal.dao.ListaComponenteDAO;
import co.edu.ufps.colossal.dao.ListaComponentehasComponenteDAO;
import co.edu.ufps.colossal.dao.MetodoDAO;
import co.edu.ufps.colossal.dao.NotificacionDAO;
import co.edu.ufps.colossal.dao.PeticionSubidaDAO;
import co.edu.ufps.colossal.dao.RecursoDAO;
import co.edu.ufps.colossal.dao.RevisionDAO;
import co.edu.ufps.colossal.dao.TagDAO;
import co.edu.ufps.colossal.dao.TokenDAO;
import co.edu.ufps.colossal.dao.UsuarioDAO;
import co.edu.ufps.colossal.entities.Categoria;
import co.edu.ufps.colossal.entities.CategoriaHasComponente;
import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ComponenteHasDependency;
import co.edu.ufps.colossal.entities.ComponenteHasTag;
import co.edu.ufps.colossal.entities.Estado;
import co.edu.ufps.colossal.entities.Incidencia;
import co.edu.ufps.colossal.entities.ListaComponente;
import co.edu.ufps.colossal.entities.ListaComponentehasComponente;
import co.edu.ufps.colossal.entities.Metodo;
import co.edu.ufps.colossal.entities.Notificacion;
import co.edu.ufps.colossal.entities.PeticionSubida;
import co.edu.ufps.colossal.entities.Recurso;
import co.edu.ufps.colossal.entities.Revision;
import co.edu.ufps.colossal.entities.Tag;
import co.edu.ufps.colossal.entities.Usuario;
import co.edu.ufps.colossal.security.SessionManager;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author AvrilFockMe
 */
@TransactionManagement(value = TransactionManagementType.BEAN)
@Stateless
public class Colossal implements Serializable {

    @EJB
    private TokenDAO tokenDAO;

    @EJB
    private ComponenteHasDependencyDAO componenteHasDependencyDAO;

    @EJB
    private NotificacionDAO notificacionDAO;

    @EJB
    private IncidenciaDAO incidenciaDAO;

    @EJB
    private RevisionDAO revisionDAO;

    @EJB
    private RecursoDAO recursoDAO;

    @EJB
    private MetodoDAO metodoDAO;

    @EJB
    private ListaComponentehasComponenteDAO listaComponentehasComponenteDAO;

    @EJB
    private ListaComponenteDAO listaComponenteDAO;

    @EJB
    private PeticionSubidaDAO peticionSubidaDAO;

    private static final long serialVersionUID = 1L;
    @EJB
    private EstadoDAO estadoDAO;

    @EJB
    private CategoriaHasComponenteDAO categoriaHasComponenteDAO;

    @EJB
    private ComponenteHasTagDAO componenteHasTagDAO;

    @EJB
    private CategoriaDAO categoriaDao;

    @EJB
    private TagDAO tagDAO;

    @EJB
    private ComponenteDAO componenteDAO;

    @EJB
    private UsuarioDAO usuarioDAO;

    @Resource
    private UserTransaction userTransaction;

    /**
     * Metodo que permite verficiar la existencia de un usuario en la base de
     * datos
     *
     * @param usuario un objeto de tipo Usuario con los datos a verificar
     * @return un objeto con los datos del usuario traidos de la base de datos
     * en caso de existir, null de otra forma
     */
    public Usuario iniciarSesion(Usuario usuario) {
        return this.usuarioDAO.iniciarSesion(usuario);
    }

    /**
     * Metodo que devuelve todos los tags registrados en la base de datos
     *
     * @return una lista con todos los tags.
     */
    public List<Tag> getAllTags() {
        return this.tagDAO.findAll();
    }

    /**
     * Metodo que permite registrar un componente en la base de datos
     *
     * @param componente el componente a registrar
     * @param observaciones las observaciones del componente
     * @param estado el estado delcomponente
     * @param u el usuario en sesion
     * @param logo el logo del componente
     * @param imagenes las iamgenes del componente
     * @param archivoMain el archivo principal del componente
     * @param archivos el listado de los archivos adicionales del componente
     */
    public void subirComponente(Componente componente, String observaciones, Estado estado, Usuario u, UploadedFile logo, List<UploadedFile> imagenes, UploadedFile archivoMain, List<UploadedFile> archivos) {

        PeticionSubida peticion = new PeticionSubida();
        peticion.setObservaciones(observaciones);
        peticion.setEstadoIdestado(estado);
        peticion.setComponenteidComponente(componente);
        peticion.setUsuarioCodigo(u);
        componente.getPeticionSubidaList().add(peticion);

//        // se guarda la referencia al logo del componente
        if (logo == null) {

        } else {
            Recurso r = new Recurso("logo", logo.getFileName());
            r.setComponenteidComponente(componente);
            componente.getRecursoList().add(r);
        }
        // se guarda la referencia al  logo del componente

        // se guarda la referencia a las imagenes del componente
        for (UploadedFile imagen : imagenes) {
            Recurso r = new Recurso("imagen", imagen.getFileName());
            r.setComponenteidComponente(componente);
            componente.getRecursoList().add(r);
        }
        // se guarda la referencia a las imagenes del componente

        // se guarda la referencia a los archivos del componente
        for (UploadedFile archivo : archivos) {
            Recurso r = new Recurso("archivo", archivo.getFileName());
            r.setComponenteidComponente(componente);
            componente.getRecursoList().add(r);
        }
        // se guarda la referencia a los archivos del componente

        Recurso r = new Recurso("mainFile", archivoMain.getFileName());
        r.setComponenteidComponente(componente);
        componente.getRecursoList().add(r);

        this.componenteDAO.create(componente);

    }

    /**
     * Permite enviar notificaciones a los administradores del sistema
     *
     * @param users el listado de usuarios que recibiran la notificacion
     * @param c el componente relacionado
     * @param asunto el asunto de la notificacion
     * @param mensaje el mensaje y cuerpo de la notificacion
     */
    private void sendNotifications(List<Usuario> users, Componente c, String asunto, String mensaje) {
        Notificacion n;
        for (Usuario user : users) {
            n = new Notificacion();
            n.setUsuarioReceptor(user);
            n.setComponenteidComponente(c);
            n.setAsunto(asunto);
            n.setMensaje(mensaje);
            Estado e = this.saveEstado();
            n.setEstadoIdestado(e);
            this.notificacionDAO.create(n);
        }
    }

    /**
     * Permite registrar un componente en el sistema
     *
     * @param info una estructura de datos con informacion acerca del componente
     * a registrar.
     * @return una cadena con informacion relativa al proceso de registro del
     * componente.
     */
    public String registrarComponente2(Map info) {

        // Obteniendo datos del panel 1
        Usuario u = (Usuario) info.get("usuario");
        String raiz = (String) info.get("raiz");
        Componente componente = (Componente) info.get("componente");
        List<Tag> tags = (List<Tag>) info.get("tags");
        List<Componente> dependencias = (List<Componente>) info.get("dependencias");
        List<Categoria> categorias = (List<Categoria>) info.get("categorias");
        UploadedFile logo = (UploadedFile) info.get("logo");
        // Obteniendo datos del panel 1

        // Obteniendo datos del panel 2
        List<Metodo> metodos = (List<Metodo>) info.get("metodos");
        this.prepararComponente(metodos, componente);
        // Obteniendo datos del panel 2

        // Obteniendo datos del panel 3
        UploadedFile file = (UploadedFile) info.get("archivoMain");
        List<UploadedFile> imagenes = (List<UploadedFile>) info.get("imagenes");
        List<UploadedFile> archivos = (List<UploadedFile>) info.get("archivos");
        String observaciones = (String) info.get("observaciones");
        // Obteniendo datos del panel 3

//        
        try {
            userTransaction.begin(); // se inicia la transaccion

            this.subirComponente(componente, observaciones, this.saveEstado(), u, logo, imagenes, file, archivos);

            this.saveTags(tags, componente);
            this.saveDependencies(dependencias, componente);
            this.saveCategories(categorias, componente);

            String msg = FileManager.subirLogo(logo, raiz, u, componente);
            if (msg.equals("OK")) {

                if ((msg = FileManager.subirImagenes(imagenes, raiz, u, componente)).equals("OK")) {

                    if ((msg = FileManager.subirArchivoMain(file, raiz, u, componente)).equals("OK")) {

                        if ((msg = FileManager.subirArchivosAdicionales(archivos, raiz, u, componente)).equals("OK")) {
                            msg = FileManager.createPdf(componente, raiz, u, dependencias, tags);
                            if (msg.equals("OK")) {
                                userTransaction.commit();
                                List<Usuario> users = this.usuarioDAO.getAdmins();
                                this.sendNotifications(users, componente, "Se ha registrado un nuevo componente!", "El componente " + componente.getNombre() + " está pendiente de aprobacíon.");
                                return "OK";
                            } //                            if ((msg = FileManager.generarPdfEspecificaciones(raiz, FileManager.getCadenaPDF(componente), componente.getIdComponente(), u)).equals("OK")) {
                            //
                            //                                userTransaction.commit();
                            //                                List<Usuario> users = this.usuarioDAO.getAdmins();
                            //                                this.sendNotifications(users, componente, "Se ha registrado un nuevo componente!", "El componente " + componente.getNombre() + " está pendiente de aprobacíon.");
                            //                                return "OK";
                            //
                            //                            }
                            else {

                                userTransaction.rollback();
                                return msg;
                            }

                        } else {
                            userTransaction.rollback();
                            return msg;
                        }

                    } else {
                        userTransaction.rollback();
                        return msg;
                    }

                } else {
                    userTransaction.rollback();
                    return msg;
                }

            } else {
                userTransaction.rollback();
                return msg;
            }

        } catch (Exception e) {

            try {
                userTransaction.rollback();     // se eliminan las insersiones hechas

            } catch (Exception ex) {
                FileManager.eliminarLogo(logo.getFileName(), raiz, u, componente);
                FileManager.eliminarArchivoPrincipal(file.getFileName(), raiz, u, componente);
                FileManager.eliminarImagenes(imagenes, raiz, u, componente);
                FileManager.eliminarArchivos(archivos, raiz, u, componente);
//                  FileManager.removeFolder(raiz+"componentes/"+u.getCodigo()+"/"+componente.getIdComponente()+"/");
            }
//            e.printStackTrace();
            return "Ha ocurrido un error. Por favor inténtalo de nuevo!";
        }
    }

    /**
     * Permite guardar las dependencias de un componente en el sistema
     *
     * @param dependencias las dependencias del componente a registrar
     * @param componente el componente relacionado
     */
    private void saveDependencies(List<Componente> dependencias, Componente componente) {
        ComponenteHasDependency cd;
//        System.out.println("Dependencies antes de insertarlas es: " + dependencias);
        for (Componente dependencia : dependencias) {
            cd = new ComponenteHasDependency();
            dependencia = this.componenteDAO.findByNombre(dependencia.getNombre());
            cd.setComponenteOwner(componente);
            cd.setComponenteDependency(dependencia);

            this.componenteHasDependencyDAO.create(cd);
        }
    }

    /**
     * Permite preparar un componente antes de ser registrado en el sistema
     *
     * @param metodos un listado con los metodos del componente
     * @param componente el componente a registrar
     */
    private void prepararComponente(List<Metodo> metodos, Componente componente) {

        // se relacionan los metodos con el componente
        for (Metodo m : metodos) {
            m.setComponenteidComponente(componente);
        }
        componente.setMetodoList(metodos);
        // se relacionan los metodos con el componente

    }

    /**
     * Metodo que devuelve todas las categorias
     *
     * @return
     */
    public List<Categoria> getAllCategorias() {

        return this.categoriaDao.findAll();

    }

    /**
     * Devuelve una lista de categorias cuyos nombres coincidan con una cadena
     * de texto
     *
     * @param cadena
     * @return lista de categorias
     */
    public List<Categoria> getCategoriasByNombre(String cadena) {
        return this.categoriaDao.getCategoriasByNombre(cadena);
    }

    /**
     * Metodo que permite persistir los tags que el usuario selecciona para el
     * componente
     *
     * @param tags el listado con los tags
     */
    private void saveTags(List<Tag> tags, Componente componente) {

        List<Tag> allTags = this.tagDAO.findAll(); // se obtienen todos los tags registradps

        // para cada tag
        for (Tag t : tags) {

            Tag temp = this.contienTag(allTags, t); // si no está regitrado en la BD creelo, 
            if (temp == null) {
                this.tagDAO.create(t);
            } // si no actualizelo
            else {
                t.setIdtag(temp.getIdtag());
            }

            ComponenteHasTag tempH = new ComponenteHasTag(componente.getIdComponente(), t.getIdtag());
            tempH.setComponente(componente);
            tempH.setTag(t);
            this.componenteHasTagDAO.create(tempH);
            componente.getComponenteHasTagList().add(tempH);
        }

    }

    /**
     * Metodo que permite actualizar los tags que el usuario selecciona para el
     * componente
     *
     * @param tags el listado con los tags
     */
    private void updateTags(List<Tag> tags, Componente componente) {

        List<Tag> allTags = this.tagDAO.findAll(); // se obtienen todos los tags registradps
        this.componenteHasTagDAO.removeAll(componente);
        // para cada tag
        for (Tag t : tags) {

            Tag temp = this.contienTag(allTags, t); // si no está regitrado en la BD creelo, 
            if (temp == null) {
                this.tagDAO.create(t);
            } // si no actualizelo
            else {
                t.setIdtag(temp.getIdtag());
            }
            ComponenteHasTag tempH = new ComponenteHasTag(componente.getIdComponente(), t.getIdtag());
            tempH.setComponente(componente);
            tempH.setTag(t);
            this.componenteHasTagDAO.create(tempH);

        }

    }

    /**
     * Metodo que actualiza las dependencias de un componente
     *
     * @param dependencias
     * @param componente
     */
    private void updateDependencies(List<Componente> dependencias, Componente componente) {

//        System.out.println("Elimino en alexandria");
        this.deleteAllByComponentOwner(componente);
        this.saveDependencies(dependencias, componente);

    }

    /**
     * Metodo que busca si un tag se encuentra ya registrado en la base de datos
     * y previene registrarlo de nuevo
     *
     * @param allTags la lista de todos los tags registrados en la DB
     * @param tag el tag a buscar
     * @return una referencia con el tag, null de otra forma
     */
    private Tag contienTag(List<Tag> allTags, Tag tag) {

        for (Tag t : allTags) {
            if (t.equals(tag)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Metodo que permite registrar las categorias que selecciono el usuario
     * para el componente
     *
     * @param categories una lista con las categorias seleccionadas
     * @param componente el componente a registrar
     */
    private void saveCategories(List<Categoria> categories, Componente componente) {

        for (Categoria c : categories) {
            CategoriaHasComponente cH = new CategoriaHasComponente(c.getIdcategoria(), componente.getIdComponente());
            cH.setCategoria(c);
            cH.setComponente(componente);
            this.categoriaHasComponenteDAO.create(cH);
        }

    }

    /**
     * Metodo que permite actualizar las categorias que selecciono el usuario
     * para el componente
     *
     * @param categories una lista con las categorias seleccionadas
     * @param componente el componente a actualizar
     */
    private void updateCategories(List<Categoria> categories, Componente componente) {

        this.categoriaHasComponenteDAO.removeAll(componente);

        for (Categoria c : categories) {
            CategoriaHasComponente cH = new CategoriaHasComponente(c.getIdcategoria(), componente.getIdComponente());
            cH.setCategoria(c);
            cH.setComponente(componente);
            this.categoriaHasComponenteDAO.create(cH);
        }

    }

    /**
     * Metodo que permite crear un estado en el sistema Los estados son creados
     * en Pendiente.
     *
     * @return la referencia del estado que se creó
     */
    private Estado saveEstado() {
        Estado estado = new Estado();
        estado.setEstado("Pendiente");
        estado.setFechaGeneracion(new Date());
        this.estadoDAO.create(estado);
        return estado;

    }

    /**
     * Metodo que devuelve los 6 componentes recientemente publicados.
     *
     * @return lista de componentes
     */
    public List<Componente> getComponentesRecientes() {
        return this.componenteDAO.getComponentesRecientes();
    }

    /**
     * Metodo que devuelve los componentes cuyos nombre, decscripcion y
     * tecnologias coincidan con una cadena de texto
     *
     * @param cadena
     * @param indice
     * @return lista de componentes
     */
    public List<Componente> getComponentesByNombreYDescripcion(String cadena, int indice) {
        return this.componenteDAO.getComponentesByNombreYDescripcion(cadena, indice);
    }

    /**
     * Metodo que retorna la cantidad de componentes de la busqueda de
     * componentes por nombre y descripcion
     *
     * @param cadena
     * @return entero con la cantidad de componetnes de la busqueda
     */
    public int getCantComponentesByNombreYDescripcion(String cadena) {
        return this.componenteDAO.getCantComponentesByNombreYDescripcion(cadena);
    }

    /**
     * Metodo que retorna los componentes correspondientes a una categoria
     *
     * @param categoria
     * @param indice
     * @param cadena
     * @return lista de componentes
     */
    public List<Componente> getComponentesByCategory(int categoria, int indice, String cadena) {
        return this.componenteDAO.getComponentesByCategory(categoria, indice, cadena);
    }

    /**
     * Metodo que retorna la cantidad de componentes correspondientes a una
     * categoria recibe un parametro que sirve a manera de filtro para
     * especificar aun mas la busqueda.
     *
     * @param categoria
     * @param cadena
     * @return entero con la cantidad de componentes por categoria
     */
    public int getCantComponentesByCategory(int categoria, String cadena) {
        return this.componenteDAO.getCantComponentesByCategory(categoria, cadena);
    }

    /**
     * Metodo que devuelve una lista de componentes por un tag
     *
     * @param tag
     * @param indice
     * @param cadena
     * @return lista de componentes
     */
    public List<Componente> getComponentesByTag(String tag, int indice, String cadena) {
        return this.componenteDAO.getComponentesByTag(tag, indice, cadena);
    }

    /**
     * Metodo que devuelve la cantidad de componentes de que estan asociados a
     * un tag
     *
     * @param tag
     * @param cadena
     * @return entero con la cantidad de componentes asociados a un tag
     */
    public int getCantComponentesByTag(String tag, String cadena) {
        return this.componenteDAO.getCantComponentesByTag(tag, cadena);
    }

    /**
     * Devuelve un componente dado la id del mismo
     *
     * @param id_componente
     * @return componente
     */
    public Componente getComponente(int id_componente) {

        Componente c = this.componenteDAO.getComponenteById(id_componente);
        return c;
    }

    /**
     * Metodo que permite agregar un componente a una lista a creada
     *
     * @param listaC la lista donde agregar el componente
     * @param componente el componente a agregar en la lista
     * @param delete controla cuando desasociar un componente de una lista
     * @return un mensaje con informacion si se pudo o no agregar el componente
     * a la lista
     */
    public String addComponentToList(ListaComponente listaC, Componente componente, boolean delete) {

        ListaComponentehasComponente lh = this.listaComponentehasComponenteDAO.findListaByIdListIdComp(listaC, componente);
        String msg = "";
        try {

            if (lh == null) {
                lh = new ListaComponentehasComponente(listaC.getIdlistaComponente(), componente.getIdComponente());
                lh.setComponente(componente);
                lh.setListaComponente(listaC);
                listaC.getListaComponentehasComponenteList().add(lh);
                this.listaComponentehasComponenteDAO.create(lh);
                msg = "Componente agregado a la lista satisfactoriamente!";
            } else {

                if (delete) {
//                    System.out.println("La elimino ");
                    this.listaComponentehasComponenteDAO.remove(lh);
                    msg = "Componente removido de la lista satisfactoriamente!";
                } else {
                    msg = "Componente agregado a la lista satisfactoriamente!";
                }

            }

        } catch (Exception e) {
            msg = "Hubo un error, Por favor inténtalo más tarde";

        }

        return msg;

    }

    /**
     * Metodo que permite agregar un componente a una lista
     *
     * @param listaC la lista donde agregar el componente
     * @param componente el componente a agregar
     * @return un mensaje con informacion si se pudo o no agregar el componente
     * a la lista
     */
    public String addListComponent(ListaComponente listaC, Componente componente) {

        listaC.setUsuariocodigo(SessionManager.getUsuarioSession()); //se le establece al usuario de sesion como dueño de la lista

        System.out.println("Lista antes de guardarla: " + listaC);
        String msg;
        try {
            this.listaComponenteDAO.create(listaC);
            ListaComponentehasComponente lh = new ListaComponentehasComponente(listaC.getIdlistaComponente(), componente.getIdComponente());
            lh.setComponente(componente);
            lh.setListaComponente(listaC);
            listaC.getListaComponentehasComponenteList().add(lh);
            this.listaComponentehasComponenteDAO.create(lh);
            componente.getListaComponentehasComponenteList().add(lh);
            msg = "Componente agregado a la lista satisfactoriamente!";

        } catch (Exception e) {
            msg = "Hubo un error, Por favor inténtalo más tarde";

        }

        return msg;
    }

    /**
     * metodo que registra una nueva lista de usuario vacia
     *
     * @param lista una referencia con informacion de la lista a crear
     * @return mensaje con un mensaje de aprobacion en caso de registrarse o un
     * aviso en caso de no poder registrarse la lista
     */
    public String crearLista(ListaComponente lista) {
        lista.setUsuariocodigo(SessionManager.getUsuarioSession()); //se le establece al usuario de sesion como dueño de la lista
        String msg = "";
        try {
            this.listaComponenteDAO.create(lista);
            msg = "Lista creada satisfactoriamente";
        } catch (Exception e) {
            msg = "error, La lista no pudo ser creada";
        }
        return msg;
    }

    /**
     * Metodo que permite buscar una lista en la base de datos por nombre
     *
     * @param listName el nombre de la lista a buscar
     * @return una referencia con la lista, null si no existe
     */
    public ListaComponente findListaByName(String listName) {
        return this.listaComponenteDAO.findListaByName(listName);
    }

    /**
     * Metodo que permite obtener las listas creadas por el usuario en sesion
     *
     * @param usuario el usuario en sesion
     * @return una lista con las listas del usuario
     */
    public List<ListaComponente> getListasUser(Usuario usuario) {
        return this.listaComponenteDAO.findByUser(usuario);
    }

    /**
     * Permite verificar si un componente pertenece a una lista en particular
     *
     * @param lista la lista a verificar
     * @param componente el componente a verificar
     * @return una referencia a la clase ListaComponentehasComponente en caso de
     * existir el componente en la lista, null de otra forma
     */
    public ListaComponentehasComponente isOnList(ListaComponente lista, Componente componente) {
//        return this.listaComponentehasComponenteDAO.findListaByIdListIdComp(lista, componente);
        ListaComponentehasComponente lh = null;
        for (ListaComponentehasComponente listaComponentehasComponente : componente.getListaComponentehasComponenteList()) {

            if (listaComponentehasComponente.getListaComponente() != null) {
                if (listaComponentehasComponente.getListaComponente().getIdlistaComponente().equals(lista.getIdlistaComponente())) {
                    lh = listaComponentehasComponente;
                    break;
                }
            }

        }
        return lh;
    }

    /**
     * Metodo que devuelve una lista de componentes del usuario dependiendo del
     * estado del mismo
     *
     * @param estado
     * @param codigo
     * @param index
     * @return lista de componentes
     */
    public List<Componente> getComponentsByStatusAndUser(String estado, int codigo, int index) {
        return this.componenteDAO.getComponentsByStatusAndUser(estado, codigo, index);
    }

    /**
     * Devuelve la cantidad de componentes que tiene un usuario dependendiento
     * del estado
     *
     * @param valor
     * @param codigo
     * @return entero con la cantidad de componentes
     */
    public int getCantComponentsByStatusAndUser(String valor, int codigo) {
        return this.componenteDAO.getCantComponentsByStatusAndUser(valor, codigo);
    }

    /**
     * Devuelve el nombre de una lista dado su id
     *
     * @param idLista
     * @return String
     */
    public String findListById(int idLista) {
        try {
            return this.listaComponenteDAO.findListById(idLista);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo que permite crear una lista en la base de datos
     *
     * @param componente un componente de la biblioteca
     * @param listaTemp la informacion de la lista a crear
     * @return una cadena con información referente al proceso de creación de la
     * lista.
     */
    public String createNewList(Componente componente, ListaComponente listaTemp) {
        String msg = "";

        try {
            userTransaction.begin(); // se inicia la transaccion
            System.out.println("El nombre en alexandria de la lista antes de enviar " + listaTemp.getNombreLista());
            listaTemp.setNombreLista(listaTemp.getNombreLista().trim());

            if (listaTemp.getNombreLista() == null || listaTemp.getNombreLista().isEmpty()) {
                userTransaction.rollback();

                return "Error, el nombre de la lista no puede estar vacío!";
            }

            ListaComponente listaExistente = this.findListaByName(listaTemp.getNombreLista());
            System.out.println("La lista no fue creada anteriomente: " + listaExistente);

            if (listaExistente != null) {
                userTransaction.rollback();
                return "Error, la lista ya ha sido creada anteriormente";
            }

            msg = this.addListComponent(listaTemp, componente);

//            if (listaExistente != null) {
////                System.out.println("La lista existe");
//                msg = this.addComponentToList(listaExistente, componente, false);
//
//            } //no existe la lista, crear
//            else {
//
//                msg = this.addListComponent(listaTemp, componente);
//                System.out.println("No existe");
//
//            }
            if (msg.contains("error")) {
                userTransaction.rollback();

            } else {
                userTransaction.commit();
            }
            return msg;

        } catch (Exception e) {

            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                msg = "Hubo un error, Por favor inténtalo más tarde";
            }

            return msg;
        }

    }

    /**
     * Metodo que permite eliminar un componente de la biblioteca
     *
     * @param componente el componente a eliminar
     * @return una cadena con informacion acerca del proceso de eliminación.
     */
    public String eliminarComponente(Componente componente) {
        String msg = null;

        try {
            userTransaction.begin();
            PeticionSubida pet = componente.getPeticionSubidaList().get(0);
            Estado e = (Estado) pet.getEstadoIdestado();
            e.setEstado("Eliminado");
            this.estadoDAO.edit(e);
            List<Usuario> users = this.usuarioDAO.getAdmins();
            this.sendNotifications(users, componente, "Un componente ha sido eliminado!", "El componente " + componente.getNombre() + " ha sido eliminado de la bilioteca!");

            userTransaction.commit();
            msg = "El componente ha sido eliminado de la Biblioteca satisfactoriamente!";

        } catch (Exception e) {

            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                msg = "Hubo un error, Por favor inténtalo más tarde";
            }
        }

        return msg;
    }

    /**
     * Devuelve un tag dado su id
     *
     * @param idtag
     * @return tag
     */
    public Tag getTagsById(int idtag) {
        return this.tagDAO.getTagsById(idtag);
    }

    /**
     * Metodo que permite cargar los tags relacionados al componente
     *
     * @param componente el componente relacionado
     * @return un listado con las referencias a los tags del componente
     */
    public List<Tag> loadTagsComponent(Componente componente) {
        ArrayList<Tag> tags = new ArrayList<>();
        for (ComponenteHasTag ch : componente.getComponenteHasTagList()) {
            tags.add(ch.getTag());
        }

        return tags;
    }

    /**
     * Metodo que permite cargar las categorias relacionadas con el componente
     *
     * @param componente el componente involucrado
     * @return un listado con las referencias a las categorias del componente
     */
    public List<Categoria> loadCategoriesComponent(Componente componente) {
        ArrayList<Categoria> categorias = new ArrayList<>();
        for (CategoriaHasComponente ch : componente.getCategoriaHasComponenteList()) {
            categorias.add(ch.getCategoria());
        }

        return categorias;
    }

    /**
     * Metodo que permite cargar las listas relacionadas con el componente
     *
     * @param componente el componente involucrado
     * @return un listado con las referencias a las listas del componente
     */
    public List<ListaComponente> loadListasComponent(Componente componente) {
        List<ListaComponente> listas = new ArrayList<>();
        for (ListaComponentehasComponente listaComponentehasComponente : componente.getListaComponentehasComponenteList()) {
            listas.add(listaComponentehasComponente.getListaComponente());
        }

        return listas;
    }

    /**
     * Devuelve los componentes asociados a una lista personalizada de un
     * usuario
     *
     * @param idLista
     * @param codigo
     * @param indice
     * @return lista de componentes
     */
    public List<Componente> getComponentsByIdList(int idLista, int codigo, int indice) {
        return this.componenteDAO.getComponentsByIdList(idLista, codigo, indice);
    }

    /**
     * Devuelve la cantidad de componentes que tiene una lista personalizada de
     * un usuario
     *
     * @param idLista
     * @param codigo
     * @return entero con la cantidad de componetnes
     */
    public int getCantComponentsByIdList(int idLista, int codigo) {
        return this.componenteDAO.getCantComponentsByIdList(idLista, codigo);
    }

    /**
     * Permite obtener un listado con las imegenes de un componente
     *
     * @param componente el componente involucrado
     * @return un listado con las imagenes del componente
     */
    public List<Recurso> getImagenesSubidasComponente(Componente componente) {
        List<Recurso> ret = new ArrayList<>();
        for (Recurso recurso : componente.getRecursoList()) {
            if (recurso.getNombre().contains("image")) {
                ret.add(recurso);
            }
        }

        return ret;
    }

    /**
     * Permite obtener los archivos adicionales de un componente
     *
     * @param componente el componente involucrado
     * @return un listado con los archivos adicionales del componente
     */
    public List<Recurso> getArchivosSubidosComponente(Componente componente) {
        List<Recurso> ret = new ArrayList<>();

        for (Recurso recurso : componente.getRecursoList()) {
            if (recurso.getNombre().contains("archivo")) {
                ret.add(recurso);
            }
        }

        return ret;
    }

    /**
     * Permite crear una referencia a un recurso en el sistema
     *
     * @param nombre el nombre del recurso
     * @param tipo el tipo del recurso, zip, rar, etc.
     * @return la referencia con el recurso.
     */
    public Recurso addRecurso(String nombre, String tipo) {
        Recurso r = new Recurso();
        r.setNombre(tipo);
        r.setUrl(nombre);
        return r;
    }

    /**
     * Metodo que permite retornar la referencia al logo de un componente pasado
     * como parametro
     *
     * @param componente el componente del cual extraer el logo
     * @return una referencia de tipo Recurso si se registro un logo, null de
     * otra forma
     */
    public Recurso findLogoComponente(Componente componente) {

        for (Recurso recurso : componente.getRecursoList()) {
            if (recurso.getNombre().equals("logo")) {
                return recurso;
            }
        }

        return null;
    }

    /**
     * Metodo que permite retornar la referencia al archivoMain de un componente
     * pasado como parametro
     *
     * @param componente el componente del cual extraer el logo
     * @return una referencia de tipo Recurso si se registro un archivoMain,
     * null de otra forma
     */
    public Recurso findArchivoMainComponent(Componente componente) {
        for (Recurso recurso : componente.getRecursoList()) {
            if (recurso.getNombre().equals("mainFile")) {
                return recurso;
            }
        }

        return null;
    }

    /**
     * Metodo que permite retornar un listado de todas las imagenes de un
     * componente pasado como parametro
     *
     * @param componente el componente del cual extraer el logo
     * @return una lista de tipo Recurso con las referencias a las imagenes
     */
    public List<Recurso> findImagesComponent(Componente componente) {
        List<Recurso> imagenes = new ArrayList<>();
        for (Recurso recurso : componente.getRecursoList()) {
            if (recurso.getNombre().contains("imagen")) {
                imagenes.add(recurso);
            }
        }
        return imagenes;
    }

    /**
     * Metodo que permite retornar un listado de todos los archivos de un
     * componente pasado como parametro
     *
     * @param componente el componente del cual extraer el logo
     * @return una lista de tipo Recurso con las referencias a los archivos
     */
    public List<Recurso> findArchivosAdicComponent(Componente componente) {
        List<Recurso> archivos = new ArrayList<>();
        for (Recurso recurso : componente.getRecursoList()) {
            if (recurso.getNombre().contains("archivo")) {
                archivos.add(recurso);
            }
        }
        return archivos;
    }

    /**
     * Permite modiificar un componente en el sistema
     *
     * @param info una estructura de datos con la informacion a actualizar del
     * componente
     * @return una cadena informando acerca del proceso de modificacion del
     * componente
     */
    public String modificarComponente(Map info) {

        try {
            userTransaction.begin(); // se inicia la transaccion

            String rootFolder = (String) info.get("rootFolder");
            Componente componente = (Componente) info.get("componente");
            List<Tag> tags = (List<Tag>) info.get("tags");
            List<Componente> dependencias = (List<Componente>) info.get("dependencias");
            List<Categoria> categorias = (List<Categoria>) info.get("categorias");

            List<Recurso> recursosEliminados = (List<Recurso>) info.get("recursosEliminados");
            List<UploadedFile> archivos = (List<UploadedFile>) info.get("archivos");
            List<UploadedFile> imagenes = (List<UploadedFile>) info.get("imagenes");
            UploadedFile archivoMain = (UploadedFile) info.get("archivoMain");
            UploadedFile logo = (UploadedFile) info.get("logo");
            boolean eliminoRecursoLogo = (boolean) info.get("eliminoRecursoLogo");
            List<Metodo> metodos = (List<Metodo>) info.get("metodos");
            boolean eliminoRecursoMain = (boolean) info.get("eliminoRecursoMain");
            Revision revision = (Revision) info.get("revision");
            if (revision.getDescripcionCambios() == null || revision.getDescripcionCambios().trim().isEmpty()) {
                revision.setDescripcionCambios("No se especificaron cambios para esta versión.");
            }

            this.updateMetodos(metodos, componente);

            Usuario u = SessionManager.getUsuarioSession();

            if (u.getTipoUsuario() == 0) {
                this.createRevision(revision, componente);
            } else {
                if (u.getCodigo().equals(componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo())) {
                    this.createRevision(revision, componente);
                }
            }
            String msg = null;
            if (componente.getPeticionSubidaList().get(0).getEstadoIdestado().getEstado().equals("Rechazado")) {
                msg = "El componente ha sido actualizado satisfactoriamente, y se encuentra pendiente de revisión.";
            }
            if (componente.getPeticionSubidaList().get(0).getEstadoIdestado().getEstado().equals("Pendiente")) {
                msg = "El componente ha sido actualizado satisfactoriamente, y aún se encuentra pendiente de revisión.";
            }

            this.updatePDF(rootFolder, componente, dependencias, tags);
            this.reinicializarComponente(componente);
            this.componenteDAO.edit(componente);
            this.updateTags(tags, componente);
            this.updateDependencies(dependencias, componente);
            this.updateCategories(categorias, componente);

            this.updateLogo(logo, eliminoRecursoLogo, componente, rootFolder);
            this.updateArchivoMain(archivoMain, eliminoRecursoMain, componente, rootFolder);
            this.updateRecursosComponente(recursosEliminados, imagenes, archivos, componente, rootFolder);

            userTransaction.commit();

            if (msg != null) {
                return msg;
            }

            return "OK";

        } catch (Exception e) {

            try {
                userTransaction.rollback();     // se eliminan las insersiones hechas

            } catch (Exception ex) {
//                FileManager.eliminarLogo(logo, raiz, u, componente);
//                FileManager.eliminarArchivoPrincipal(file, raiz, u, componente);
//                FileManager.eliminarImagenes(imagenes, raiz, u, componente);
//                FileManager.eliminarArchivos(archivos, raiz, u, componente);
            }
            e.printStackTrace();
            return "Ha ocurrido un error. Por favor inténtalo de nuevo!";
        }

    }

    /**
     * Metodo que actualiza el contenido de un archivo PDF
     *
     * @param raiz
     * @param componente
     * @param dependencias
     * @param tags
     */
    private void updatePDF(String raiz, Componente componente, List<Componente> dependencias, List<Tag> tags) {
        FileManager.removeFolder(raiz + "componentes/" + componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + componente.getIdComponente() + "/pdf/");
        FileManager.createPdf(componente, raiz, componente.getPeticionSubidaList().get(0).getUsuarioCodigo(), dependencias, tags);
    }

    /**
     * Permite refistrar una nueva revision para un componente
     *
     * @param revision la revision a regitrar
     * @param componente el componente involucrado
     */
    private void createRevision(Revision revision, Componente componente) {
        revision.setComponenteidComponente(componente);
        revision.setVersion(componente.getVersion());
        revision.setFecha(new Date());
        this.revisionDAO.create(revision);
    }

    /**
     * Permite actualizar el logo de un componente
     *
     * @param logo la referencia con el logo a actualizar
     * @param eliminoRecursoLogo un booleano indicando si se tiene que eliminar
     * el recurso Logo de la base de datos
     * @param componente el componente al que pertenece el logo
     * @param rootFolder la ruta principal del sistema, donde está ubicado.
     */
    private void updateLogo(UploadedFile logo, boolean eliminoRecursoLogo, Componente componente, String rootFolder) {
        if (eliminoRecursoLogo) {

            Recurso oldLogo = this.recursoDAO.findByNombre("logo", componente);

            if (oldLogo != null) {
                this.recursoDAO.remove(oldLogo);
                FileManager.eliminarLogo(oldLogo.getUrl(), rootFolder, componente.getPeticionSubidaList().get(0).getUsuarioCodigo(), componente);
            }

        }

        if (logo != null) {
            FileManager.subirLogo(logo, rootFolder, componente.getPeticionSubidaList().get(0).getUsuarioCodigo(), componente);
            Recurso rLogo = this.addRecurso(logo.getFileName(), "logo");
            rLogo.setComponenteidComponente(componente);
            this.recursoDAO.create(rLogo);

        }
    }

    /**
     * Permite actualizar los recursos de un componente
     *
     * @param recursosEliminados un listado con los recursos que deben ser
     * eliminados de la base de datos
     * @param imagenes un listado con las imagenes que deben crearse como
     * recurso en el sistema
     * @param archivos un listado con los archivos que deben crearse como
     * recurso en el sistema
     * @param componente el componente relacionado
     * @param rootFolder la ruta principal del sistema, donde está ubicado.
     */
    private void updateRecursosComponente(List<Recurso> recursosEliminados, List<UploadedFile> imagenes, List<UploadedFile> archivos, Componente componente, String rootFolder) {

        for (Recurso recursosEliminado : recursosEliminados) {
            this.recursoDAO.remove(recursosEliminado);
            FileManager.eliminarArchivo(recursosEliminado.getUrl(), rootFolder, componente.getPeticionSubidaList().get(0).getUsuarioCodigo(), componente);
        }

        Recurso temp;
        for (UploadedFile archivo : archivos) {
            temp = this.addRecurso(archivo.getFileName(), "archivo");
            temp.setComponenteidComponente(componente);
            this.recursoDAO.create(temp);

        }
        FileManager.subirArchivosAdicionales(archivos, rootFolder, componente.getPeticionSubidaList().get(0).getUsuarioCodigo(), componente);

        for (UploadedFile imagen : imagenes) {
            temp = this.addRecurso(imagen.getFileName(), "imagen");
            temp.setComponenteidComponente(componente);
            this.recursoDAO.create(temp);
        }

        FileManager.subirImagenes(imagenes, rootFolder, componente.getPeticionSubidaList().get(0).getUsuarioCodigo(), componente);

    }

    /**
     * Metodo que permite reinicializar las listas de un componente pasado como
     * parametro
     *
     * @param componente el componente a reinicializar
     */
    private void reinicializarComponente(Componente componente) {

        Estado e = componente.getPeticionSubidaList().get(0).getEstadoIdestado();
        Usuario u = SessionManager.getUsuarioSession();

        if (u.getTipoUsuario() == 0) {

            if (e.getEstado().equals("Rechazado")) {
                e.setFechaGeneracion(new Date());
                e.setFechaRevision(null);
                e.setEstado("Pendiente");
                this.estadoDAO.edit(e);
                List<Usuario> users = this.usuarioDAO.getAdmins();
                this.sendNotifications(users, componente, "Se ha registrado un nuevo componente!", "El componente " + componente.getNombre() + " está pendiente de aprobacíón.");

            }

        } else {

            if (u.getCodigo().equals(componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo())) {

                if (e.getEstado().equals("Rechazado")) {
                    e.setFechaGeneracion(new Date());
                    e.setFechaRevision(null);
                    e.setEstado("Pendiente");
                    this.estadoDAO.edit(e);
                    List<Usuario> users = this.usuarioDAO.getAdmins();
                    this.sendNotifications(users, componente, "Se ha registrado un nuevo componente!", "El componente " + componente.getNombre() + " está pendiente de aprobacíon.");

                }

            }

        }

        componente.setMetodoList(new ArrayList<Metodo>());
        componente.setCategoriaHasComponenteList(new ArrayList<CategoriaHasComponente>());
        componente.setComponenteHasTagList(new ArrayList<ComponenteHasTag>());
        componente.setRecursoList(new ArrayList<Recurso>());
    }

    /**
     * Permite actualizar los metodos de un componente
     *
     * @param metodos el listado con los metodos a actualizar
     * @param componenente el componente relacionado
     */
    private void updateMetodos(List<Metodo> metodos, Componente componenente) {
        this.metodoDAO.deleteAllByComponent(componenente);
        for (Metodo metodo : metodos) {

            metodo.setComponenteidComponente(componenente);
            this.metodoDAO.create(metodo);
        }

    }

    /**
     * Permite actualizar el archivo principal de un componente en el sistema
     *
     * @param archivoMain la referencia con el nuevo archivo a actualizar
     * @param eliminoRecursoMain un boolean indicando si debe eliminarse el
     * recurso Main de la base de datos
     * @param componente el componente relacionado con el archivo
     * @param rootFolder la ruta principal del sistema, donde está ubicado.
     */
    private void updateArchivoMain(UploadedFile archivoMain, boolean eliminoRecursoMain, Componente componente, String rootFolder) {

        if (eliminoRecursoMain) {
            Recurso oldFileMain = this.recursoDAO.findByNombre("mainFile", componente);

            if (oldFileMain != null) {
                this.recursoDAO.remove(oldFileMain);
                FileManager.eliminarArchivoPrincipal(oldFileMain.getUrl(), rootFolder, componente.getPeticionSubidaList().get(0).getUsuarioCodigo(), componente);
            }
        }

        if (archivoMain != null) {
            FileManager.subirArchivoMain(archivoMain, rootFolder, componente.getPeticionSubidaList().get(0).getUsuarioCodigo(), componente);
            Recurso rMain = this.addRecurso(archivoMain.getFileName(), "mainFile");
            rMain.setComponenteidComponente(componente);
            this.recursoDAO.create(rMain);
        }

    }

    /**
     * Metodo que retorna las peticiones de subda de componentes por su estado
     *
     * @param indice
     * @param valor
     * @return
     */
    public List<PeticionSubida> obtenerPeticionesPorEstado(int indice, String valor) {
        return this.peticionSubidaDAO.obtenerPeticionesPorEstado(indice, valor);
    }

    /**
     * Obtiene la cantidad de peticiones de subida por estado
     *
     * @param indice
     * @param valor
     * @return entero con la cantidad de peticiones de subida por estado
     */
    public int obtenerCantPeticionesPorEstado(int indice, String valor) {
        return this.peticionSubidaDAO.obtenerCantPeticionesPorEstado(indice, valor);
    }

    /**
     * Permite registrar una incidencia en el sistema
     *
     * @param componente el componente para el cual registrar la incidencia
     * @param incidencia la indicencia a registrar
     * @return una cadena con informacion acerca de la operacion de registro de
     * la incidencia
     */
    public String registrarIncidencia(Componente componente, Incidencia incidencia) {
        try {
            userTransaction.begin();

            incidencia.setUsuarioCodigo(SessionManager.getUsuarioSession());
            incidencia.setEstadoIdestado(this.saveEstado());
            incidencia.setComponenteidComponente(componente);
            this.incidenciaDAO.create(incidencia);
            List<Usuario> users = this.usuarioDAO.getAdmins();

            this.sendNotifications(users, componente, "Se ha registrado una nueva incidencia!", incidencia.getDescripcion());

//            this.enviarNotificaciones(users, componente, "Se ha registrado una nueva incidencia");
            userTransaction.commit();
            return "Enviada Correctamente!";
        } catch (Exception e) {

            try {
                userTransaction.rollback();
            } catch (Exception ex) {

            }

            return "Error! Inténtalo de nuevo más tarde!";
        }
    }

    /**
     * Permite obtener un listado de los tags que cuyo nombre coincida con el
     * nombre pasado como parametro
     *
     * @param nombre una cadena con el nombre a coincidir
     * @return una listado con los tags cuyo nombre es similar a la cadena
     * pasada
     */
    public List<Tag> findTagsLikeNombre(String nombre) {
        return this.tagDAO.findTagsLikeNombre(nombre);
    }

    /**
     * Metodo que edita el estado de una peticion de subida, una incidencia o
     * una notificacion
     *
     * @param e
     * @param estado
     * @param p
     * @param i
     * @param n
     * @return String con mensaje de confirmacion
     */
    public String editarEstado(Estado e, String estado, PeticionSubida p, Incidencia i, Notificacion n) {
        String msg = "";
        try {
            e.setEstado(estado);
            e.setFechaRevision(new Date());

            userTransaction.begin();
            if (n != null) {
                n.setEstadoIdestado(this.saveEstado());
                this.notificacionDAO.create(n);
            }
            if (p != null) {
                this.peticionSubidaDAO.edit(p);
            }
            if (i != null) {
                this.incidenciaDAO.edit(i);
            }
            this.estadoDAO.edit(e);
            userTransaction.commit();
        } catch (Exception error) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
            }
            msg = "error";
        }
        return msg;
    }

    /**
     * Devuelve la lista de incidencias de los componentes depeendiendo de su
     * estado
     *
     * @param valor
     * @param indice
     * @return lista de incidencias
     */
    public List<Incidencia> getIncidencias(String valor, int indice) {
        return this.incidenciaDAO.getIncidencias(valor, indice);
    }

    /**
     * Devuelve la cantidad de incidencias dependiendo de su estado
     *
     * @param valor
     * @return
     */
    public int countIncidencias(String valor) {
        return this.incidenciaDAO.countIncidencias(valor);
    }

    public List<Incidencia> getIncidenciasXComponente(int idcomponente) {
        return this.incidenciaDAO.getIncidenciasXComponente(idcomponente);
    }

    /**
     * Crea que agrega una nueva categoria, validando que el nombre no se
     * encuentre en uso y que el nombre sea valido.
     *
     * @param c
     * @param l
     * @return String que responde a la peticion del usuario
     */
    public String agregarCategoria(Categoria c, List<Categoria> l) {
        String msg = "";
        if (c.getNombre() == null || c.getNombre().equalsIgnoreCase("")) {
            msg = "Error, no se permiten valores vacíos";
            return msg;
        }
        for (Categoria cat : l) {
            if (cat.getNombre().equalsIgnoreCase(c.getNombre())) {
                msg = "Error, el nombre de la categoría ya se encuentra en uso!";
                return msg;
            }
        }
        if (this.createCategory(c)) {
            msg = "Se ha agregado la categoría: " + c.getNombre();
        } else {
            msg = "Error, No se pudo agregar la categoría, intente de nuevo!";
        }
        return msg;
    }

    /**
     * Metodo que inserta una categoria en la base de datos
     *
     * @param c
     * @return boolean true si inserto, false si no
     */
    private boolean createCategory(Categoria c) {
        boolean rsp = true;
        try {
            this.categoriaDao.create(c);
        } catch (Exception e) {
            rsp = false;
        }
        return rsp;
    }

    /**
     * Metodo que valida que la edicion de una categoria sea posible.
     *
     * @param c
     * @param l
     * @return String mensaje de confirmacion de la operacion
     */
    public String editarCategoria(Categoria c, List<Categoria> l) {
        String msg = "";
        if (c.getNombre() == null || c.getNombre().equalsIgnoreCase("")) {
            msg = "Error, no se permiten valores vacíos!";
            return msg;
        }
        for (Categoria cat : l) {
            if (cat.getNombre().equalsIgnoreCase(c.getNombre())) {
                msg = "Error, el nombre de la categoría ya se encuentra en uso!";
                return msg;
            }
        }
        if (this.editCategory(c)) {
            msg = "Se ha editado la categoría: " + c.getNombre();
        } else {
            msg = "Error, No se pudo editar la categoría, intente de nuevo!";
        }
        return msg;
    }

    /**
     * Metodo que edita una categoria
     *
     * @param c
     * @return boolean true si lo hace, false si no
     */
    private boolean editCategory(Categoria c) {
        boolean rsp = true;
        try {
            this.categoriaDao.edit(c);
        } catch (Exception e) {
            rsp = false;
        }
        return rsp;
    }

    /**
     * Metodo que se encarga de eliminar una categoria de la base de datos
     *
     * @param c
     */
    public void eliminarCategoria(Categoria c) {
        this.categoriaDao.remove(c);
    }

    /**
     * Permite eliminar una lista del sistema
     *
     * @param nombreLista el nombre de la lista a eliminar
     * @return una cadena con informacion acerca de la operacion de eliminado.
     */
    public String eliminarLista(String nombreLista) {
        int m = this.listaComponenteDAO.deleteByName(nombreLista);
        if (m > 0) {
            return "Lista eliminada satisfactoriamente!";
        } else {
            return "Error, Inténtalo más tarde";
        }
    }

    /**
     * Permite editar el nombre de una lista creada por un usuario
     *
     * @param listaBuscar el nombre de la lista a editar
     * @param listaNombreNuevo el nuevo nombre con el que actualizar la lista
     * @return una cadena indicando el estado de la operación.
     */
    public String editarLista(String listaBuscar, String listaNombreNuevo) {
        try {
//            System.out.println("Lista a Buscar: "+listaBuscar);
//            System.out.println("Lista nombre nuevo: "+listaNombreNuevo);

            ListaComponente lista = this.listaComponenteDAO.findListaByName(listaNombreNuevo);
            if (lista != null) {
                return "Error, ya existe una lista con ese nombre";
            }

            lista = this.listaComponenteDAO.findListaByName(listaBuscar);
            lista.setNombreLista(listaNombreNuevo);
            this.listaComponenteDAO.edit(lista);
            return "La lista se actualizó satisfactoriamente!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error, Inténtalo más tarde";
        }
    }

    /**
     * Metodo que cuenta la cantidad de notificaciones que tiene un usuario
     *
     * @param codigo
     * @param ident
     * @return entero con la cantidad de notificaciones del usuario
     */
    public int countNotificaciones(int codigo, int ident) {
        try {
            return this.notificacionDAO.countNotificaciones(codigo, ident);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Metodo que devuelve las notificaciones de un usuario dependiendo de su
     * estado
     *
     * @param codigo
     * @param indice
     * @param ident
     * @return lista de notificaciones
     */
    public List<Notificacion> getNotificaciones(int codigo, int indice, int ident) {
        try {
            return this.notificacionDAO.getNotificaciones(codigo, indice, ident);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Metodo que devuelve una notificacion dada su id
     *
     * @param id
     * @return ntificacion
     */
    public Notificacion getNotificacion(int id) {
        return this.notificacionDAO.getNotificacion(id);
    }

    /**
     * Permite obtener un listado de los componentes cuyo nombre sea similiar a
     * una cadena pasada como parametro
     *
     * @param nombre una cadena con el nombre para el cual los componentes deben
     * coincidir
     * @param u una referencia con informacion de un usuario en particular
     * @return un listado de los componentes similares
     */
    public List<Componente> findComponentsLikeNombre(String nombre, Usuario u) {

        return this.componenteDAO.findComponentsLikeNombre(nombre, u);
    }

    /**
     * Permite obtener un listado de los componentes cuyo nombre sea similiar a
     * una cadena pasada como parametro
     *
     * @param nombre una cadena con el nombre para el cual los componentes deben
     * coincidir
     * @param componente
     * @return un listado de los componentes similares
     */
    public List<Componente> findComponentsLikeNombreAprobados(String nombre, Componente componente) {

        return this.componenteDAO.findComponentsLikeNombreAprobados(nombre, componente);
    }

    /**
     * Permite obtener un listado con todas las dependencias que tengan como
     * owner a un componente pasado como parametro
     *
     * @param componente el componente owner de las dependencias
     * @return un listado con las dependencias.
     */
    public List<ComponenteHasDependency> findByComponentOwner(Componente componente) {
        return this.componenteHasDependencyDAO.findByComponentOwner(componente);
    }

    /**
     * Permite eliminar todos los registros en el sistema de aquellas
     * dependencias que tengan como Owner a un componente pasado como parámetro.
     *
     * @param componente el componente el componente owner de las dependencias
     * @return true si se pudo efecuar la operacion, false de otra forma.
     */
    public boolean deleteAllByComponentOwner(Componente componente) {
        return this.componenteHasDependencyDAO.deleteAllByComponentOwner(componente);
    }

    /**
     * Permite guardar un componente en una lista determinada
     *
     * @param lista la lista donde guardar el componente
     * @param componente el componente a guardar en la lista
     * @return una cadena con informacion del proceso
     */
    public String saveOnList(ListaComponente lista, Componente componente) {
        ListaComponentehasComponente lh = null;
        for (ListaComponentehasComponente listaComponentehasComponente : componente.getListaComponentehasComponenteList()) {

            if (listaComponentehasComponente.getListaComponente().getIdlistaComponente().equals(lista.getIdlistaComponente())) {
                lh = listaComponentehasComponente;
                break;
            }

        }

        String msg = null;

        if (lh == null) {
            lh = new ListaComponentehasComponente(lista.getIdlistaComponente(), componente.getIdComponente());
            lh.setComponente(componente);
            lh.setListaComponente(lista);
            try {
                this.listaComponentehasComponenteDAO.create(lh);
                msg = "Agregado satisfactoriamente a la lista!";
                componente.getListaComponentehasComponenteList().add(lh);
            } catch (Exception e) {
                msg = "Error " + e.getMessage();
            }

        } else {

            try {
                this.listaComponentehasComponenteDAO.remove(lh);
                msg = "Removido satisfactoriamente de la lista!";
                componente.getListaComponentehasComponenteList().remove(lh);
            } catch (Exception e) {
                msg = "Error " + e.getMessage();
            }

        }
        return msg;
    }

    /**
     * Metodo que retorna la fecha actual
     *
     * @return fecha actual del sistema
     */
    public String getFechaActual() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Se comparan las fechas para ver si son validas retorna true si fecha 1 es
     * menor e igual a fecha 2
     *
     * @param fecha1
     * @param fecha2
     * @return
     */
    public boolean compararFechas(String fecha1, String fecha2) {
        boolean resultado = true;
        try {
            /**
             * Obtenemos las fechas enviadas en el formato a comparar
             */
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate1 = formateador.parse(fecha1);
            Date fechaDate2 = formateador.parse(fecha2);;

            if (fechaDate1.before(fechaDate2)) {
                resultado = true;
            } else {
                if (fechaDate2.before(fechaDate1)) {
                    resultado = false;
                } else {
                    resultado = true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return resultado;
    }

    /**
     * Metodo que se encarga de crear los parametros de la busqueda avanzada
     *
     * @param cadenaTexto
     * @param cadenaTags
     * @param cadenaServicios
     * @param fecha1
     * @param fecha2
     * @param identifier1
     * @param identifier2
     * @param indice
     * @return lista de componentes
     */
    public List<Componente> getBusquedaAvanzada(String cadenaTexto, String cadenaTags, String cadenaServicios, String fecha1, String fecha2, String identifier1, String identifier2, int indice) {
        List<Componente> l = new ArrayList<Componente>();
        try {
            userTransaction.begin();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate1 = formateador.parse(fecha1);
            Date fechaDate2 = formateador.parse(fecha2);
            List<String> listaTexto = this.convertStringToStringList(cadenaTexto);
            List<String> listaTag = this.convertStringToStringList(cadenaTags);
            List<String> listaServicios = this.convertStringToStringList(cadenaServicios);
            String query = "SELECT c ";
            String from = "FROM Componente c,PeticionSubida p,Estado e ";
            String where = " WHERE (p.estadoIdestado.idestado=e.idestado and p.componenteidComponente.idComponente=c.idComponente and e.estado like :valor) and (";
            boolean sw_lTexto = false;
            boolean sw_ltag = false;
            if (listaTexto.size() > 0) {
                where += "(";
                sw_lTexto = true;
                where += this.iterateCreationQuery(listaTexto, 1) + ")";
            }
            if (listaTag.size() > 0) {
                sw_ltag = true;
                if (sw_lTexto) {
                    where += " " + identifier1 + " ";
                }
                where += "c.idComponente in (SELECT  cht.componente.idComponente  FROM Tag t,ComponenteHasTag cht ";
                where += " WHERE t.idtag = cht.tag.idtag and cht.componente.idComponente = c.idComponente and (";
                where += this.iterateCreationQuery(listaTag, 2);
                where += ")) ";
            }
            if (listaServicios.size() > 0) {
                if (sw_lTexto || sw_ltag) {
                    where += " " + identifier2 + " ";
                }
                where += " c.idComponente in (  SELECT m.componenteidComponente.idComponente FROM Metodo m ";
                where += " WHERE m.componenteidComponente.idComponente = c.idComponente and (";
                where += this.iterateCreationQuery(listaServicios, 3);
                where += "))";
            }
            where += " ) and e.fechaRevision BETWEEN :fecha1 AND :fecha2  ORDER BY e.fechaRevision DESC";
            query += from + where;
            l = this.componenteDAO.getAdvancedSearch(query, listaTexto, listaTag, listaServicios, fechaDate1, fechaDate2, indice);
            userTransaction.commit();
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                System.out.println("error en rollback");
            }
        }
        return l;
    }

    /**
     * Metodo que devuelve la cantidad de componentes encontrados en la busqueda
     * avanzada
     *
     * @param cadenaTexto
     * @param cadenaTags
     * @param cadenaServicios
     * @param fecha1
     * @param fecha2
     * @param identifier1
     * @param identifier2
     * @return entero con la cantidad de componentes de la busqueda avnzada
     */
    public int getCantComponentsBusquedaAvanzada(String cadenaTexto, String cadenaTags, String cadenaServicios, String fecha1, String fecha2, String identifier1, String identifier2) {
        int cant = 0;
        try {
            userTransaction.begin();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate1 = formateador.parse(fecha1);
            Date fechaDate2 = formateador.parse(fecha2);
            List<String> listaTexto = this.convertStringToStringList(cadenaTexto);
            List<String> listaTag = this.convertStringToStringList(cadenaTags);
            List<String> listaServicios = this.convertStringToStringList(cadenaServicios);
            String query = "SELECT COUNT(c.idComponente) ";
            String from = "FROM Componente c,PeticionSubida p,Estado e ";
            String where = " WHERE (p.estadoIdestado.idestado=e.idestado and p.componenteidComponente.idComponente=c.idComponente and e.estado like :valor) and (";
            boolean sw_lTexto = false;
            boolean sw_ltag = false;
            if (listaTexto.size() > 0) {
                where += "(";
                sw_lTexto = true;
                where += this.iterateCreationQuery(listaTexto, 1) + ")";
            }
            if (listaTag.size() > 0) {
                sw_ltag = true;
                if (sw_lTexto) {
                    where += " " + identifier1 + " ";
                }
                where += "c.idComponente in (SELECT  cht.componente.idComponente  FROM Tag t,ComponenteHasTag cht ";
                where += " WHERE t.idtag = cht.tag.idtag and cht.componente.idComponente = c.idComponente and (";
                where += this.iterateCreationQuery(listaTag, 2);
                where += ")) ";
            }
            if (listaServicios.size() > 0) {
                if (sw_lTexto || sw_ltag) {
                    where += " " + identifier2 + " ";
                }
                where += " c.idComponente in (  SELECT m.componenteidComponente.idComponente FROM Metodo m ";
                where += " WHERE m.componenteidComponente.idComponente = c.idComponente and (";
                where += this.iterateCreationQuery(listaServicios, 3);
                where += "))";
            }
            where += " ) and e.fechaRevision BETWEEN :fecha1 AND :fecha2  ORDER BY e.fechaRevision DESC";
            query += from + where;
            cant = this.componenteDAO.getCantComponentsAdvancedSearch(query, listaTexto, listaTag, listaServicios, fechaDate1, fechaDate2);
            userTransaction.commit();
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                System.out.println("error en rollback");
            }
        }
        return cant;
    }

    /**
     * Metodo que convierte un String a una lista de Strings
     *
     * @param cadena
     * @return lista de strings
     */
    public List<String> convertStringToStringList(String cadena) {
        List<String> l = null;
        if (!cadena.isEmpty()) {
            l = Arrays.asList(cadena.split(";"));
        } else {
            l = new ArrayList<String>();
        }
        return l;
    }

    /**
     * Metodo que itera una lista de strings y va creando la consulta para la
     * busqueda avanzada
     *
     * @param l
     * @param ident
     * @return
     */
    private String iterateCreationQuery(List<String> l, int ident) {
        String where = "(";
        int i = 0;
        for (String cadena : l) {
            switch (ident) {
                case 1:
                    if (i == 0) {
                        where += " c.nombre like :cadena" + i + " or c.descripcion like :cadena" + i + " or c.tecnologia like :cadena" + i;
                    } else {
                        where += " or c.nombre like :cadena" + i + " or c.descripcion like :cadena" + i + " or c.tecnologia like :cadena" + i;
                    }
                    break;
                case 2:
                    if (i == 0) {
                        where += " t.nombre like :tag" + i;
                    } else {
                        where += " or t.nombre like :tag" + i;
                    }
                    break;
                case 3:
                    if (i == 0) {
                        where += " m.cabecera like :servicio" + i + " or m.descripcion like :servicio" + i;
                    } else {
                        where += " or m.cabecera like :servicio" + i + " or m.descripcion like :servicio" + i;
                    }
                    break;
                default:
                    break;
            }
            i++;
        }
        where += ")";
        return where;
    }

    /**
     * Metodo que devuelve los reportes dependiendo de un identificador, el cual
     * corresponde al tipo de reporte requerido
     *
     * @param identificador
     * @return lista de vectores de objetos
     */
    public List<Object[]> getReporte(int identificador) {
        List<Object[]> reporte = new ArrayList<>(0);
        switch (identificador) {
            case 1:
                reporte = this.categoriaDao.getReporteCantComponentesXCategorias();
                break;
            case 2:
                reporte = this.componenteDAO.getReporteCantComponentesXTecnologias();
                break;
            case 3:
                reporte = this.componenteDAO.getReporteCantComponentesXVisualizaciones();
                break;
            case 4:
                reporte = this.componenteDAO.getReporteCantComponentesXEstado();
                break;
            default:
                break;
        }
        return reporte;
    }

    /**
     * Metodo que permite encontrar un token valido
     *
     * @param token
     * @return lista de vector de objects
     */
    public List<Object[]> findValidToken(String token) {
        return this.tokenDAO.findValidToken(token);
    }

    /**
     * Metodo que devuelve un usuario dado su codigo
     *
     * @param codigo
     * @return usuario
     */
    public Usuario findUserByCodigo(Integer codigo) {
        return this.usuarioDAO.findUserByCodigo(codigo);
    }

    /**
     * Metodo que aumenta la cantidad de visualizaciones de un componente
     *
     * @param componente
     */
    public void aumentarVisualizacionesComponente(Componente componente) {
        if (componente.getPeticionSubidaList().get(0).getEstadoIdestado().getEstado().equals("Aprobado")) {
            this.componenteDAO.actualizarVisualiciones(componente);

        }
    }

    /**
     * Metodo que retorna un usuario dado un componente
     *
     * @param c
     * @return usuario
     */
    public Usuario getUsuarioByComponente(Componente c) {
        return this.usuarioDAO.getUsuarioByComponente(c.getIdComponente());
    }
}
