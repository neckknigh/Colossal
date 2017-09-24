/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author neck
 */
public class CategoriaDAOTest {

    private EntityManager em;
    private Query query;
    private CategoriaDAO cDao;

    @Before
    public void setUp() {

        this.cDao = mock(CategoriaDAO.class);
        em = mock(EntityManager.class);
        this.cDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class CategoriaDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");

    }

    /**
     * Test of edit method, of class CategoriaDAO.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        String expected = "edit-method worked";
        when(cDao.edit((Categoria) any())).thenReturn(expected);
        String result = cDao.edit((Categoria) any());
        assertThat(result, is(expected));
    }
//

    /**
     * Test of remove method, of class CategoriaDAO.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        String expected = "remove-method worked";
        when(cDao.remove((Categoria) any())).thenReturn(expected);
        String result = cDao.remove((Categoria) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of findAll method, of class CategoriaDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        query = mock(Query.class);
        String namedQuery = "Categoria.findAll";
        List<Categoria> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(cDao.findAll()).thenReturn(expected);
        List<Categoria> result = cDao.findAll();
        assertThat(result, is(expected));
    }

    /**
     * Test of getCategoriasByNombre method, of class CategoriaDAO.
     */
    @Test
    public void testGetCategoriasByNombre() {
        System.out.println("getCategoriasByNombre");
        query = mock(Query.class);
        String namedQuery = "Categoria.findByNombre";
        List<Categoria> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(cDao.getCategoriasByNombre(Matchers.anyString())).thenReturn(expected);
        List<Categoria> result = cDao.getCategoriasByNombre(Matchers.anyString());
        assertThat(result, is(expected));
    }

    /**
     * Test of getReporteCantComponentesXCategorias method, of class
     * CategoriaDAO.
     */
    @Test
    public void testGetReporteCantComponentesXCategorias() {
        System.out.println("getReporteCantComponentesXCategorias");
        query = mock(Query.class);
        String namedQuery = "SELECT c.nombre, count(ch.categoria_idcategoria) FROM categoria c "
                + " left join "
                + " (SELECT categoria_idcategoria "
                + " from categoria_has_componente,componente c,peticionSubida p, estado e "
                + " where c.idComponente = categoria_has_componente.componente_idComponente and p.componente_idComponente = c.idComponente and p.estado_idestado = e.idestado and e.estado = 'Aprobado') ch on c.idcategoria =ch.categoria_idcategoria "
                + " group by c.idcategoria";
        List<Object[]> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(cDao.getReporteCantComponentesXCategorias()).thenReturn(expected);
        List<Object[]> result = cDao.getReporteCantComponentesXCategorias();
        assertThat(result, is(expected));
    }

}
