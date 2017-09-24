/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;

/**
 * Un Data Access Object (DAO, Objeto de Acceso a Datos) es un componente de
 * software que suministra una interfaz común entre la aplicación y uno o más
 * dispositivos de almacenamiento de datos, tales como una Base de datos o un
 * archivo. El término se aplica frecuentemente al Patrón de diseño Object. Dao
 * para la tabla Usuario
 *
 * @author Jhon Vargas
 */
@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario> {

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
     * Constructor vacío de la clase UsuarioDAO
     */
    public UsuarioDAO() {
        super(Usuario.class);
    }

    /**
     * Metodo que permite verficiar la existencia de un usuario en la base de
     * datos
     *
     * @param u un objeto de tipo Usuario con los datos a verificar
     * @return un objeto con los datos del usuario traidos de la base de datos
     * en caso de existir, null de otra forma
     */
    public Usuario iniciarSesion(Usuario u) {
        Usuario user = null;

        try {
            if (this.em.isOpen()) {
                user = (Usuario) this.em.createNamedQuery("Usuario.findByUsernamePass")
                        .setParameter("username", u.getUsername())
                        .setParameter("password", u.getPassword()).getSingleResult();
                em.refresh(user);
            }

        } catch (Exception e) {
            //e.printStackTrace();

        }

        return (user);
    }

    /**
     * Metodo que devuelve un usuario dado su codigo
     * @param codigo
     * @return usuario
     */
    public Usuario findUserByCodigo(Integer codigo) {
        Usuario user = null;
        try {
//            user = (Usuario) this.em.createNamedQuery("Usuario.findByCodigo")
//                    .setParameter("codigo", codigo)
//                    .getSingleResult();

            if (this.em.isOpen()) {
                Query query = this.em.createNamedQuery("Usuario.findByCodigo")
                        .setParameter("codigo", codigo);

                user = (Usuario) query.getSingleResult();
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return user;
    }

    /**
     * Permite obtener un listado de los administradores actuales de la
     * biblioteca
     *
     * @return un listado con las referencias de los administradores
     */
    public List<Usuario> getAdmins() {
        List<Usuario> lUsers = new ArrayList<>();
        try {

            if (em.isOpen()) {
//                lUsers = this.em.createNamedQuery("Usuario.findByTipoUsuario")
//                        .setParameter("tipoUsuario", 1)
//                        .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
//                        .getResultList();

                Query query = this.em.createNamedQuery("Usuario.findByTipoUsuario")
                        .setParameter("tipoUsuario", 1)
                        .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);

                lUsers = (List<Usuario>) query.getResultList();
                for (Usuario u : lUsers) {
                    this.em.refresh(u);
                }
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }

        return lUsers;
    }

    /**
     * Metodo que devuelve un usuario dada la id de un componente
     * @param componente
     * @return usuario
     */
    public Usuario getUsuarioByComponente(int componente){
        Usuario u=null;
        try {
            Query q = em.createNativeQuery("SELECT u.* FROM usuario u,peticionSubida p,componente c "
                    + " WHERE u.codigo = p.usuario_codigo and p.componente_idComponente = c.idComponente and c.idComponente ="+componente);
            Object[] rst = (Object[])q.getSingleResult();
            u=new Usuario();
            u.setCodigo(Integer.parseInt(rst[0].toString()));
            u.setUsername(rst[1].toString());
            u.setPassword(rst[2].toString());
            u.setNombre(rst[3].toString());
            u.setEmail(rst[4].toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}
