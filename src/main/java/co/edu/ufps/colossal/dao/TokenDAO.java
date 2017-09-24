/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Token;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Un Data Access Object (DAO, Objeto de Acceso a Datos) es un componente de
 * software que suministra una interfaz común entre la aplicación y uno o más
 * dispositivos de almacenamiento de datos, tales como una Base de datos o un
 * archivo. El término se aplica frecuentemente al Patrón de diseño Object. Dao
 * para la tabla Token
 *
 * @author Jhon Vargas
 */
@Stateless
public class TokenDAO extends AbstractDAO<Token> {

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

    public TokenDAO() {
        super(Token.class);
    }

    /**
     * Permite validar si el token pasado como parametro en la url existe o no.
     * @param token una cadena con el token 
     * @return 
     */
    public List<Object[]> findValidToken(String token) {

        List<Object[]> tokens = new ArrayList<>();

        try {
            if (this.em.isOpen()) {
                String sql = "SELECT * FROM token WHERE token='" + token + "' AND NOW() < fechaExp";
                Query q = this.em.createNativeQuery(sql);
                List<Object[]> results = q.getResultList();
                for (Object[] r : results) {
                    tokens.add(r);
                }
            }
        } catch (Exception e) {

        }

        return tokens;

    }

}
