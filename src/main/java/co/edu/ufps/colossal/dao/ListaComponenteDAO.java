/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.ListaComponente;
import co.edu.ufps.colossal.entities.Usuario;
import co.edu.ufps.colossal.security.SessionManager;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Un Data Access Object (DAO, Objeto de Acceso a Datos) es un componente de
 * software que suministra una interfaz común entre la aplicación y uno o más
 * dispositivos de almacenamiento de datos, tales como una Base de datos o un
 * archivo. El término se aplica frecuentemente al Patrón de diseño Object. Dao
 * para la tabla ListaComponente
 *
 * @author Jhon Vargas
 */
@Stateless
public class ListaComponenteDAO extends AbstractDAO<ListaComponente> {

    @PersistenceContext(unitName = "co.edu.ufps.alexandria_AlexandriaBetaUno_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    /**
     * An EntityManager instance is associated with a persistence context. A
     * persistence context is a set of entity instances in which for any
     * persistent entity identity there is a unique entity instance. Within the
     * persistence context, the entity instances and their lifecycle are
     * managed. The EntityManager API is used to create and remove persistent
     * entity instances, to find entities by their primary key, and to query
     * over entities.
     *
     * @return una referencia con el entitymanager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Permite establecer el entitymanager con uno pasado como parametro
     *
     * @param em el entitymanager a establecer
     */
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Constructor vacío de la clase ListaComponenteDAO
     */
    public ListaComponenteDAO() {
        super(ListaComponente.class);
    }

    /**
     * Metodo que permite obtener las listas creadas por el usuario en sesion
     *
     * @param usuario el usuario en sesion
     * @return una lista con las listas del usuario
     */
    public List<ListaComponente> findByUser(Usuario usuario) {
        List<ListaComponente> listas = null;
        try {
            listas = (List<ListaComponente>) this.em.createNamedQuery("ListaComponente.findByUser")
                    .setParameter("idUser", usuario).getResultList();
            System.out.println(listas);
        } catch (Exception e) {

        }
//        System.out.println(lista);
        return (listas);

    }

    /**
     * Permite obtener una referencia a una lista
     *
     * @param listName el nombre de la lista a obtener
     * @return una referencia con la lista, null de otra forma.
     */
    public ListaComponente findListaByName(String listName) {
        ListaComponente lista = null;

        try {
            lista = (ListaComponente) this.em.createNamedQuery("ListaComponente.findByNombreLista")
                    .setParameter("nombreLista", listName).
                    setParameter("usuario", SessionManager.getUsuarioSession())
                    .getSingleResult();
            em.refresh(lista);

        } catch (Exception e) {
//            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Permite obtener una referencia a una lista
     *
     * @param idLista un entero con el id de la lista a obtener
     * @return una referencia con la lista, null de otra forma.
     */
    public String findListById(int idLista) {
        String lista = null;
        try {
            lista = this.em.createNamedQuery("ListaComponente.findByIdlistaComponente")
                    .setParameter("idlistaComponente", idLista).getSingleResult().toString();

        } catch (Exception e) {

        }
        return (lista);
    }

    /**
     * Permite eliminar una lista del sistema
     *
     * @param nombreLista el nombre de la lista a eliminar
     * @return un entero indicando el resultado de la eliminación
     */
    public int deleteByName(String nombreLista) {
        int m = 0;
        try {
            m = this.em.createNamedQuery("ListaComponente.deleteByName")
                    .setParameter("nombreLista", nombreLista)
                    .setParameter("usuario", SessionManager.getUsuarioSession())
                    .executeUpdate();

        } catch (Exception e) {

        }
        return m;
    }
}
