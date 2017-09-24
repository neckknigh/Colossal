/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ListaComponente;
import co.edu.ufps.colossal.entities.ListaComponentehasComponente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Un Data Access Object (DAO, Objeto de Acceso a Datos) es un componente de
 * software que suministra una interfaz común entre la aplicación y uno o más
 * dispositivos de almacenamiento de datos, tales como una Base de datos o un
 * archivo. El término se aplica frecuentemente al Patrón de diseño Object.
 * Dao para la tabla ListaComponetehasComponente
 * @author Jhon Vargas
 */
@Stateless
public class ListaComponentehasComponenteDAO extends AbstractDAO<ListaComponentehasComponente> {

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
     * Constructor vacío de la clase ListaComponentehasComponenteDAO
     */
    public ListaComponentehasComponenteDAO() {
        super(ListaComponentehasComponente.class);
    }

    /**
     * Metodo que permite devuelte una referencia de la clase
     * ListaComponentehasComponente en caso que exista en la base de datos
     *
     * @param lista
     * @param componente
     * @return ListaComponentehasComponente
     */
    public ListaComponentehasComponente findListaByIdListIdComp(ListaComponente lista, Componente componente) {
        ListaComponentehasComponente lh = null;
        while (true) {
            try {
                lh = (ListaComponentehasComponente) this.em.createNamedQuery("ListaComponentehasComponente.findListaByIdListIdComp")
                        .setParameter("lista", lista)
                        .setParameter("componente", componente).getSingleResult();
                em.refresh(lh);
                break;
            } catch (Exception e) {
//            e.printStackTrace();
            }
        }

        return lh;
    }

}
