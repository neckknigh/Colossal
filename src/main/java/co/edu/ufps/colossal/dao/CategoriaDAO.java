/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Categoria;
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
 * para la tabla Categoria
 *
 * @author Jhon Vargas
 */
@Stateless
public class CategoriaDAO extends AbstractDAO<Categoria> {

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
     * Constructor vacío de la clase CategoriaDAO
     */
    public CategoriaDAO() {
        super(Categoria.class);
    }
    /**
     * Metodo que obtiene un listado de categorias dado una cadena de texto
     * @param cadena
     * @return lista de categorias
     */
    public List<Categoria> getCategoriasByNombre(String cadena) {
        List<Categoria> cat = new ArrayList<>();
        try {
            cat = this.em.createNamedQuery("Categoria.findByNombre").setParameter("nombre", "%" + cadena + "%").getResultList();

        } catch (Exception e) {
            //e.printStackTrace();
        }
        return cat;
    }

    /**
     * Obtiene el reporte de cantidad de componentes por categoria, se devuelve
     * una lista que contiene un vector de Objects en cada posicion, cada uno de estos
     * vectores tiene en la posicion 0 el nombre de la categoria y en la posicion 1
     * la cantidad.
     * @return lista de vector de objects
     */
    public List<Object[]> getReporteCantComponentesXCategorias() {
        List<Object[]> reporte = new ArrayList<>();
        try {
            Query q = em.createNativeQuery("SELECT c.nombre, count(ch.categoria_idcategoria) FROM categoria c "
                    + " left join "
                    + " (SELECT categoria_idcategoria "
                    + " from categoria_has_componente,componente c,peticionSubida p, estado e "
                    + " where c.idComponente = categoria_has_componente.componente_idComponente and p.componente_idComponente = c.idComponente and p.estado_idestado = e.idestado and e.estado = 'Aprobado') ch on c.idcategoria =ch.categoria_idcategoria "
                    + " group by c.idcategoria;");
            List<Object[]> results = q.getResultList();
            for (Object[] r : results) {
                reporte.add(r);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return reporte;
    }
}
