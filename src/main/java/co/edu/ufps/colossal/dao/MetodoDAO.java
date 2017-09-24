/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.Metodo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Un Data Access Object (DAO, Objeto de Acceso a Datos) es un componente de
 * software que suministra una interfaz común entre la aplicación y uno o más
 * dispositivos de almacenamiento de datos, tales como una Base de datos o un
 * archivo. El término se aplica frecuentemente al Patrón de diseño Object. Dao
 * para la tabla Metodo
 *
 * @author Jhon Vargas
 */
@Stateless
public class MetodoDAO extends AbstractDAO<Metodo> {

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
     * Constructor vacío de la clase MetodoDAO
     */
    public MetodoDAO() {
        super(Metodo.class);
    }

    /**
     * Permite eliminar todos los metodos asociados a un componente
     *
     * @param componente el componente asociado con los metodos a eliminar
     * @return true si se pudo efectuar la eliminación, false de otra forma.
     */
    public boolean deleteAllByComponent(Componente componente) {
//        
        int r = 0;
        try {
            r = this.em.createNamedQuery("Metodo.deleteAllByComponent")
                    .setParameter("componente", componente)
                    .executeUpdate();

        } catch (Exception e) {

        }
        return r > 0;
    }

}
