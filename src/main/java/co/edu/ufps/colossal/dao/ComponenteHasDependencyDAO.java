/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ComponenteHasDependency;
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
 * archivo. El término se aplica frecuentemente al Patrón de diseño Object. Dao
 * para la tabla ComponenteHasDependency
 *
 * @author Jhon Vargas
 */
@Stateless
public class ComponenteHasDependencyDAO extends AbstractDAO<ComponenteHasDependency> {

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
     * Constructor vacío de la clase ComponenteHasDependencyDAO
     */
    public ComponenteHasDependencyDAO() {
        super(ComponenteHasDependency.class);
    }

    /**
     * Permite obtener un listado con todas las dependencias que tengan como
     * owner a un componente pasado como parametro
     *
     * @param componente el componente owner de las dependencias
     * @return un listado con las dependencias.
     */
    public List<ComponenteHasDependency> findByComponentOwner(Componente componente) {
        List<ComponenteHasDependency> dependencias = null;
        try {

            dependencias = (List<ComponenteHasDependency>) this.em.createNamedQuery("ComponenteHasDependency.findByComponentOwner")
                    .setParameter("componente", componente)
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getResultList();

        } catch (Exception e) {
//                e.printStackTrace();

        }
        return dependencias;
    }

    /**
     * Permite eliminar todos los registros en el sistema de aquellas
     * dependencias que tengan como Owner a un componente pasado como parámetro.
     *
     * @param componente el componente el componente owner de las dependencias
     * @return true si se pudo efecuar la operacion, false de otra forma.
     */
    public boolean deleteAllByComponentOwner(Componente componente) {
        int i = 0;

        try {
            i = this.em.createNamedQuery("ComponenteHasDependency.deleteAllByComponentOwner")
                    .setParameter("componente", componente)
                    .executeUpdate();

        } catch (Exception e) {
        }

        return i > 0;
    }

}
