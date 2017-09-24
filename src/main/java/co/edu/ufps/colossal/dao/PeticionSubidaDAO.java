/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.PeticionSubida;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Un Data Access Object (DAO, Objeto de Acceso a Datos) es un componente de
 * software que suministra una interfaz común entre la aplicación y uno o más
 * dispositivos de almacenamiento de datos, tales como una Base de datos o un
 * archivo. El término se aplica frecuentemente al Patrón de diseño Object. Dao
 * para la tabla PeticionSubida
 *
 * @author Jhon Vargas
 */
@Stateless
public class PeticionSubidaDAO extends AbstractDAO<PeticionSubida> {

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
     * Constructor vacío de la clase PeticionSubidaDAO
     */
    public PeticionSubidaDAO() {
        super(PeticionSubida.class);
    }

    /**
     * MEtodo que devuelve las petificiones de subida realizadas por los usuarios
     * dependiendo de su estado.
     * @param indice
     * @param valor
     * @return lista de peticiones de subida
     */
    public List<PeticionSubida> obtenerPeticionesPorEstado(int indice, String valor) {
        List<PeticionSubida> peticiones = null;
        try {
            if (this.em.isOpen()) {
                String namequery = "PeticionSubida.findByEstado";
                if (valor.equalsIgnoreCase("Aprobado")) {

                    namequery = "PeticionSubida.findByEstadoAprobado";
                }

                peticiones = this.em.createNamedQuery(namequery)
                        .setParameter("valor", valor)
                        .setFirstResult(indice - 1).setMaxResults(5).getResultList();
                for (PeticionSubida p : peticiones) {
                    this.em.refresh(p);
                }
            }

        } catch (Exception e) {
        }

        return peticiones;
    }

    /**
     * Devuelve la cantidad de peticiones de subida realizadas por los usuarios
     * dependiendo de su estado
     * @param indice
     * @param valor
     * @return 
     */
    public int obtenerCantPeticionesPorEstado(int indice, String valor) {
        int cant = 0;
        try {
            if (this.em.isOpen()) {
                cant = ((Number) this.em.createNamedQuery("PeticionSubida.CountByEstado")
                        .setParameter("valor", valor).getSingleResult()).intValue();
            }

        } catch (Exception e) {
        }
        return cant;
    }
}
