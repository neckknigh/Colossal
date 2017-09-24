/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.Usuario;
import java.util.ArrayList;
import java.util.Date;
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
 * para la tabla Componente
 *
 * @author Jhon Vargas
 */
@Stateless
public class ComponenteDAO extends AbstractDAO<Componente> {

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
     * @param em el entitymanager a establecer
     */
    public void setEntityManager(EntityManager em){
        this.em = em;
    }

    /**
     * Constructor vacío de la clase ComponenteDAO
     */
    public ComponenteDAO() {
        super(Componente.class);
    }

    /**
     * Obtiene un listado de 6 elementos que contiene los elementos recientemente 
     * publicados en la biblioteca
     * 
     * @return lista de componentes 
     */
    public List<Componente> getComponentesRecientes() {
        String valor = "Aprobado";
        List<Componente> lRecientes = null;

        try {
            lRecientes = this.em.createNamedQuery("Componente.findByRecently")
                    .setParameter("valor", valor)
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setMaxResults(6).getResultList();

            for (Componente c : lRecientes) {
                this.em.refresh(c);
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }

        return lRecientes;
    }

    /**
     * metodo que devuelve una lista con los componentes cuyo nombre, descripcion
     * y tecnologia coincidan con una cadena de texto dada.
     * 
     * @param cadena
     * @param indice desde donde se quiere obtener la busqueda. (limitado por el paginador)
     * @return lista de componentes 
     */
    public List<Componente> getComponentesByNombreYDescripcion(String cadena, int indice) {
        List<Componente> componentes = null;

        try {
            componentes = this.em.createNamedQuery("Componente.findByString")
                    .setParameter("valor", "Aprobado").setParameter("cadena", "%" + cadena + "%")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setFirstResult(indice - 1).setMaxResults(5).getResultList();
            for (Componente c : componentes) {
                this.em.refresh(c);
            }

        } catch (Exception e) {

        }

        return componentes;
    }

    /**
     * metodo que devuelve la cantidad de componentes cuyo nombre, descripcion
     * y tecnologia coincidan con una cadena de texto dada.
     * @param cadena
     * @return entero con la cantidad de componentes correspondientes
     */
    public int getCantComponentesByNombreYDescripcion(String cadena) {
        int cant = 0;

        try {
            cant = ((Number) this.em.createNamedQuery("Componente.countByString")
                    .setParameter("valor", "Aprobado").setParameter("cadena", "%" + cadena + "%")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getSingleResult()).intValue();
        } catch (Exception e) {

        }
        return cant;
    }

    /**
     * Obtiene una lista de componentes por categoria
     * @param categoria
     * @param indice
     * @param cadena
     * @return lista de componentes
     */
    public List<Componente> getComponentesByCategory(int categoria, int indice, String cadena) {
        List<Componente> componentes = null;

        try {
//                int fin = (indice + 5);
            componentes = (List<Componente>) this.em.createNamedQuery("Componente.findByCategory")
                    .setParameter("valor", "Aprobado").setParameter("categoria", categoria)
                    .setParameter("cadena", "%" + cadena + "%")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setFirstResult(indice - 1).setMaxResults(5).getResultList();
            for (Componente c : componentes) {
                this.em.refresh(c);
            }

        } catch (Exception e) {

        }
        return componentes;
    }

    /**
     * Devuelve la cantidad de componentes que tiene una categoria.
     * 
     * @param categoria
     * @param cadena
     * @return entero con la cantidad de componentes por categoria
     */
    public int getCantComponentesByCategory(int categoria, String cadena) {
        int cant = 0;
        try {
            cant = ((Number) this.em.createNamedQuery("Componente.CountComponentsByCategory")
                    .setParameter("valor", "Aprobado").setParameter("categoria", categoria)
                    .setParameter("cadena", "%" + cadena + "%").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getSingleResult()).intValue();

        } catch (Exception e) {

        }
        return cant;
    }

    /**
     * Devuelve una lista con los componentes asociados a un tag
     * @param tag
     * @param indice
     * @param cadena
     * @return lista de componentes
     */
    public List<Componente> getComponentesByTag(String tag, int indice, String cadena) {
        List<Componente> componentes = null;

        try {
            componentes = this.em.createNamedQuery("Componente.findByTag")
                    .setParameter("valor", "Aprobado").setParameter("tag", "%" + tag + "%")
                    .setParameter("cadena", "%" + cadena + "%").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setFirstResult(indice - 1).setMaxResults(5).getResultList();
            for (Componente c : componentes) {
                this.em.refresh(c);
            }

        } catch (Exception e) {
        }

        return componentes;
    }

    /**
     * Devuelve la cantidad de componentes que tiene una busqueda por tag
     * @param tag
     * @param cadena
     * @return entero con cantidad de componentes
     */
    public int getCantComponentesByTag(String tag, String cadena) {
        int cant = 0;
        try {
            cant = ((Number) this.em.createNamedQuery("Componente.CountComponentsByTag")
                    .setParameter("valor", "Aprobado").setParameter("tag", "%" + tag + "%")
                    .setParameter("cadena", "%" + cadena + "%").setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getSingleResult()).intValue();
        } catch (Exception e) {
        }
        return cant;
    }

    /**
     * Metodo que devuelve un componente dada su ID
     * @param id_componente
     * @return componente
     */
    public Componente getComponenteById(int id_componente) {
        Componente componente = null;
        try {
            componente = (Componente) this.em.createNamedQuery("Componente.findByIdComponente")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setParameter("idComponente", id_componente).getSingleResult();
            em.refresh(componente);
            em.refresh(componente.getPeticionSubidaList().get(0).getEstadoIdestado());

        } catch (Exception e) {

        }
        return componente;
    }

    /**
     * Metodo que devuelve los componentes de un usuario dependiendo de su estado
     * @param valor
     * @param codigo
     * @param indice
     * @return lista de compoenntes
     */
    public List<Componente> getComponentsByStatusAndUser(String valor, int codigo, int indice) {
        List<Componente> lRecientes = null;

        try {
            String namequery = "Componente.findByEstado";
            if (valor.equalsIgnoreCase("Pendiente")) {
                namequery = "Componente.findByEstadoPendiente";
            }
            lRecientes = this.em.createNamedQuery("Componente.findByEstado")
                    .setParameter("valor", valor).setParameter("codigo", codigo)
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setFirstResult(indice - 1).setMaxResults(5).getResultList();
            for (Componente c : lRecientes) {
                this.em.refresh(c);
            }

        } catch (Exception e) {
//            e.printStackTrace();
        }

        return lRecientes;
    }

    /**
     * Metodo que devuelve la cantidad de componentes de un usuario dependiendo 
     * de su estado
     * @param valor
     * @param codigo
     * @return entero con la cantidad de componentes
     */
    public int getCantComponentsByStatusAndUser(String valor, int codigo) {
        int cant = 0;
        try {
            cant = ((Number) this.em.createNamedQuery("Componente.CountByEstado")
                    .setParameter("valor", valor).setParameter("codigo", codigo)
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getSingleResult()).intValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cant;
    }

    /**
     * metodo que devuelve una lista de componentes que esta asociada a una lista
     * personaliada de un usuario
     * @param idLista
     * @param codigo
     * @param indice
     * @return lista de componentes
     */
    public List<Componente> getComponentsByIdList(int idLista, int codigo, int indice) {
        List<Componente> compLista = null;
        String valor = "Aprobado";
        try {
            compLista = this.em.createNamedQuery("Componente.findByIdLista")
                    .setParameter("valor", valor).setParameter("codigo", codigo)
                    .setParameter("idlista", idLista).setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setFirstResult(indice - 1).setMaxResults(5).getResultList();
            for (Componente c : compLista) {
                this.em.refresh(c);
            }

        } catch (Exception e) {
//                e.printStackTrace();
        }

        return compLista;
    }

    /**
     * Devuelve la cantidad de componentes que tiene una lista personalizada
     * de un usuario
     * @param idLista
     * @param codigo
     * @return entero con la cantidad de componentes
     */
    public int getCantComponentsByIdList(int idLista, int codigo) {
        int cant = 0;
        String valor = "Aprobado";

        try {
            cant = ((Number) this.em.createNamedQuery("Componente.CountByIdLista")
                    .setParameter("valor", valor).setParameter("codigo", codigo)
                    .setParameter("idlista", idLista).setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getSingleResult()).intValue();

        } catch (Exception e) {
            //e.printStackTrace();
        }

        return cant;
    }

    /**
     * Devuelve un componente dado una cadena de texto que simboliza el nombre 
     * del mismo
     * @param nombre
     * @return componente
     */
    public Componente findByNombre(String nombre) {
        Componente c = null;
        try {
            c = (Componente) this.em.createNamedQuery("Componente.findByNombre")
                    .setParameter("nombre", nombre)
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getSingleResult();

        } catch (Exception e) {
            //e.printStackTrace();
        }

        return c;
    }

    /**
     * Permite obtener un listado de los componentes cuyo nombre sea similiar a
     * una cadena pasada como parametro
     *
     * @param nombre una cadena con el nombre para el cual los componentes deben
     * coincidir
     * @param u una referencia con informacion de un usuario en particular
     * @return un listado de los componentes similares
     */
    public List<Componente> findComponentsLikeNombre(String nombre, Usuario u) {
        List<Componente> componentes = null;
        try {

            componentes = (List<Componente>) this.em.createNamedQuery("Componente.findComponentsLikeNombre")
                    .setParameter("nombre", "%" + nombre + "%")
                    .setParameter("usuarioCodigo", u.getCodigo())
                    .setMaxResults(8)
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getResultList();

        } catch (Exception e) {
//                e.printStackTrace();

        }
        return componentes;
    }

    /**
     * Permite obtener un listado de los componentes cuyo nombre sea similiar a
     * una cadena pasada como parametro
     *
     * @param nombre una cadena con el nombre para el cual los componentes deben
     * coincidir
     * @param componente
     * @return un listado de los componentes similares
     */
    public List<Componente> findComponentsLikeNombreAprobados(String nombre, Componente componente) {
        List<Componente> componentes = null;
        try {

            componentes = (List<Componente>) this.em.createNamedQuery("Componente.findComponentsLikeNombreAprobados")
                    .setParameter("nombre", "%" + nombre + "%")
                    .setParameter("idComponente", componente.getIdComponente())
                    .setMaxResults(8)
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getResultList();

        } catch (Exception e) {
//                e.printStackTrace();

        }
        return componentes;
    }

    /**
     * Metodo que devuelve la cantidad de componentes de una busqueda avanzada
     * @param consulta
     * @param textos
     * @param tags
     * @param servicios
     * @param f1
     * @param f2
     * @return entero con la cantidad de componentes
     */
    public int getCantComponentsAdvancedSearch(String consulta, List<String> textos, List<String> tags, List<String> servicios, Date f1, Date f2) {
        Query query = this.em.createQuery(consulta);
        int cant = 0;
        try {
            this.llenarQueryParameters(textos, "cadena", query);
            this.llenarQueryParameters(tags, "tag", query);
            this.llenarQueryParameters(servicios, "servicio", query);
            query.setParameter("fecha1", f1);
            query.setParameter("fecha2", f2);
            cant = ((Number) query.setParameter("valor", "Aprobado")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .getSingleResult()).intValue();
        } catch (Exception e) {
            System.out.println("error algo paso");
        }
        return cant;
    }

    /**
     * Metodo que devuelve una lista de componentes dado una serie de parametros
     * @param consulta
     * @param textos
     * @param tags
     * @param servicios
     * @param f1
     * @param f2
     * @param indice
     * @return lista de componentes
     */
    public List<Componente> getAdvancedSearch(String consulta, List<String> textos, List<String> tags, List<String> servicios, Date f1, Date f2, int indice) {
        Query query = this.em.createQuery(consulta);
        List<Componente> l = null;
        try {
            this.llenarQueryParameters(textos, "cadena", query);
            this.llenarQueryParameters(tags, "tag", query);
            this.llenarQueryParameters(servicios, "servicio", query);
            query.setParameter("fecha1", f1);
            query.setParameter("fecha2", f2);
            l = query.setParameter("valor", "Aprobado")
                    .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                    .setFirstResult(indice - 1).setMaxResults(5).getResultList();
        } catch (Exception e) {
            System.out.println("error algo paso");
        }
        return l;
    }

    /**
     * metodo que llena los parametros de una consulta
     * @param l
     * @param parameterName
     * @param query 
     */
    private void llenarQueryParameters(List<String> l, String parameterName, Query query) {
        int i = 0;
        for (String cadena : l) {
            query.setParameter(parameterName + i, "%" + cadena + "%");
            i++;
        }
    }

    /**
     * metodo que devuelve el reporte de cantidad de componentes por tecnologia
     * devuelve una lista de vectores de objects, los cuales en la posicion
     * 0 tienen el nombre de la tecnologia y en la posicion 1 la cantidad de 
     * componentes asociados a la misma.
     * @return lista de vectores de objects
     */
    public List<Object[]> getReporteCantComponentesXTecnologias() {
        List<Object[]> reporte = new ArrayList<>();
        try {
            Query q = em.createNativeQuery("SELECT c.tecnologia,count(c.idComponente) "
                    + " FROM componente c,peticionSubida p,estado e "
                    + " WHERE p.componente_idComponente = c.idComponente and p.estado_idestado = e.idestado and e.estado = 'Aprobado' GROUP BY c.tecnologia");
            List<Object[]> results = q.getResultList();
            for (Object[] r : results) {
                reporte.add(r);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return reporte;
    }
    /**
     * metodo que devuelve el reporte de cantidad de componentes por estado
     * devuelve una lista de vectores de objects, los cuales en la posicion
     * 0 tienen el nombre de la tecnologia y en la posicion 1 la cantidad de 
     * componentes asociados a la misma.
     * @return lista de vectores de objects
     */
    public List<Object[]> getReporteCantComponentesXEstado() {
        List<Object[]> reporte = new ArrayList<>();
        try {
            Query q = em.createNativeQuery("SELECT e.estado,count(c.idComponente) "
                    + " FROM componente c,peticionSubida p,estado e "
                    + " WHERE p.componente_idComponente = c.idComponente and p.estado_idestado = e.idestado  "
                    + " GROUP BY e.estado");
            List<Object[]> results = q.getResultList();
            for (Object[] r : results) {
                reporte.add(r);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return reporte;
    }
    
    /**
     * Permite actualizar las visualizaciones de un componente
     * @param componente el componente relacionado 
     * @return true si se efecuto la actualizacion, false de otra forma.
     */
    public boolean actualizarVisualiciones(Componente componente){
        try {
            Query q = this.em.createNativeQuery("UPDATE componente SET visualizaciones = visualizaciones +1 WHERE idComponente="+componente.getIdComponente());
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * metodo que devuelve el reporte de componentes mas visualizados
     * devuelve una lista de vectores de objects, los cuales en la posicion
     * 0 tienen el nombre del componente y en la posicion 1 la cantidad de 
     * componentes asociados a la misma.
     * @return lista de vectores de objects
     */
    public List<Object[]> getReporteCantComponentesXVisualizaciones() {
        List<Object[]> reporte = new ArrayList<>();
        try {
            Query q = em.createNativeQuery("SELECT c.nombre,c.visualizaciones "
                    + " FROM componente c,peticionSubida p,estado e "
                    + " WHERE p.componente_idComponente = c.idComponente and p.estado_idestado = e.idestado and e.estado='Aprobado' "
                    + " ORDER BY c.visualizaciones ASC limit 10");
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
