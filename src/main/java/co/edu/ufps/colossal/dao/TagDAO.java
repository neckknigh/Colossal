/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Tag;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Un Data Access Object (DAO, Objeto de Acceso a Datos) es un componente de
 * software que suministra una interfaz común entre la aplicación y uno o más
 * dispositivos de almacenamiento de datos, tales como una Base de datos o un
 * archivo. El término se aplica frecuentemente al Patrón de diseño Object. Dao
 * para la tabla Tag
 *
 * @author Jhon Vargas
 */
@Stateless
public class TagDAO extends AbstractDAO<Tag> {

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
     * Constructor vacío de la clase TagDAO
     */
    public TagDAO() {
        super(Tag.class);
    }

    public Tag getTagsById(int idtag) {
        Tag t = null;
        try {
            if (this.em.isOpen()) {
                t = (Tag) this.em.createNamedQuery("Tag.findByIdtag").setParameter("idtag", idtag).getSingleResult();
                em.refresh(t);
            }

        } catch (Exception e) {
        }
        return t;
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
        List<Tag> tags = null;
        try {

            if (this.em.isOpen()) {
                tags = (List<Tag>) this.em.createNamedQuery("Tag.findTagsLikeNombre")
                        .setParameter("nombre", "%" + nombre + "%")
                        .setMaxResults(8)
                        .getResultList();
            }

        } catch (Exception e) {
        }
        return tags;
    }

}
