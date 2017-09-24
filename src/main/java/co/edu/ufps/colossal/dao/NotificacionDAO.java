/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Notificacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;

/**
 * Un Data Access Object (DAO, Objeto de Acceso a Datos) es un componente de
 * software que suministra una interfaz común entre la aplicación y uno o más
 * dispositivos de almacenamiento de datos, tales como una Base de datos o un
 * archivo. El término se aplica frecuentemente al Patrón de diseño Object.
 * Dao para la tabla Notificacion
 * @author Jhon Vargas
 */
@Stateless
public class NotificacionDAO extends AbstractDAO<Notificacion> {

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
     * Constructor vacío de la clase NotificacionDAO
     */
    public NotificacionDAO() {
        super(Notificacion.class);
    }

    /**
     * Devuelve las notificaciones de un usuario dependiendo de su estado
     * @param codigo
     * @param indice
     * @param ident
     * @return lista de notificaciones
     */
    public List<Notificacion> getNotificaciones(int codigo, int indice, int ident) {
        List<Notificacion> notificaciones = null;
        String valor;
        try {
            if (ident == 0) {
                valor = "Pendiente";
            } else {
                valor = "";
            }
            notificaciones = this.em.createNamedQuery("Notificacion.findNotifications")
                    .setParameter("codigo", codigo)
                    .setParameter("valor", "%" + valor + "%")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setFirstResult(indice - 1).setMaxResults(5).getResultList();
            for (Notificacion n : notificaciones) {
                this.em.refresh(n);
            }

        } catch (Exception e) {
        }

        return notificaciones;
    }

    /**
     * Cuenta la cantidad de notificacionees de un usuario dependiendo de su estado
     * @param codigo
     * @param ident
     * @return entero con la cantidad de notificaciones
     */
    public int countNotificaciones(int codigo, int ident) {
        int cant = 0;
        String valor;
        try {
            if (ident == 0) {
                valor = "Pendiente";
            } else {
                valor = "";
            }
            cant = ((Number) this.em.createNamedQuery("Notificacion.CountNotifications")
                    .setParameter("codigo", codigo)
                    .setParameter("valor", "%" + valor + "%")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getSingleResult()).intValue();

        } catch (Exception e) {
        }
        return cant;
    }

    /**
     * Devuelve una notificacion
     * @param id
     * @return notificacion
     */
    public Notificacion getNotificacion(int id) {
        Notificacion notificacion = null;
         try {
                notificacion = (Notificacion) this.em.createNamedQuery("Notificacion.findByIdNotificacion")
                        .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                        .setParameter("idNotificacion", id)
                        .getSingleResult();
               

            } catch (Exception e) {
            }
        return notificacion;
    }
}
