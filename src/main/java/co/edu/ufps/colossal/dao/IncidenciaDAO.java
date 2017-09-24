/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Incidencia;
import java.util.Collections;
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
 * Dao para la tabla Incidencia
 * @author Jhon Vargas
 */
@Stateless
public class IncidenciaDAO extends AbstractDAO<Incidencia> {

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
     * Constructor vacío de la clase IncidenciaDAO
     */
    public IncidenciaDAO() {
        super(Incidencia.class);
    }

    /**
     * MEtodo que devuelve las incidencias dependiendo de su estado
     * @param valor
     * @param indice
     * @return lista de incidencias
     */
    public List<Incidencia> getIncidencias(String valor, int indice) {
        List<Incidencia> incidencias = null;
        try {
            incidencias = this.em.createNamedQuery("Incidencia.findByStatus")
                    .setParameter("valor", valor)
                    .setFirstResult(indice - 1).setMaxResults(5).getResultList();
            for (Incidencia i : incidencias) {
                this.em.refresh(i);
            }

        } catch (Exception e) {
        }

        return incidencias;
    }

    /**
     * Devuelve la lista de incidencias de un componente
     * @param idcomponente
     * @return lista de inicidencias
     */
    public List<Incidencia> getIncidenciasXComponente(int idcomponente) {
        List<Incidencia> incidencias = null;
        try {
            incidencias = this.em.createNamedQuery("Incidencia.findByComponent")
                    .setParameter("valor", "Pendiente")
                    .setParameter("idcomponente", idcomponente)
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getResultList();

            Collections.reverse(incidencias);

        } catch (Exception e) {
        }
        return incidencias;
    }

    /**
     * Devuelve la cantidad de incidencias dependiendo de su estado
     * @param valor
     * @return 
     */
    public int countIncidencias(String valor) {
        int cant = 0;
        try {
            String namequery = "Incidencia.findByStatus";
            if (valor.equalsIgnoreCase("Aprobado")) {
//                    System.out.println("aqui");
                namequery = "Incidencia.findByStatusRevisado";
            }
            cant = ((Number) this.em.createNamedQuery(namequery)
                    .setParameter("valor", valor)
                    .getSingleResult()).intValue();

        } catch (Exception e) {
        }
        return cant;
    }

}
