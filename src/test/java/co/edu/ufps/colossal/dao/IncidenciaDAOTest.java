/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Incidencia;
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
public class IncidenciaDAOTest {

    private EntityManager em;
    private Query query;
    private IncidenciaDAO iDao;

    @Before
    public void setUp() {

        this.iDao = mock(IncidenciaDAO.class);
        em = mock(EntityManager.class);
        this.iDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class IncidenciaDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(iDao.create((Incidencia) any())).thenReturn(expected);
        String result = iDao.create((Incidencia) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of getIncidencias method, of class IncidenciaDAO.
     */
    @Test
    public void testGetIncidencias() {
        System.out.println("getIncidencias");
        query = mock(Query.class);
        String namedQuery = "Incidencia.findByStatus";
        List<Incidencia> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(iDao.getIncidencias(Matchers.anyString(), Matchers.anyInt())).thenReturn(expected);
        List<Incidencia> result = iDao.getIncidencias(Matchers.anyString(), Matchers.anyInt());
        assertThat(result, is(expected));
    }

    /**
     * Test of getIncidenciasXComponente method, of class IncidenciaDAO.
     */
    @Test
    public void testGetIncidenciasXComponente() {
        System.out.println("getIncidenciasXComponente");
        query = mock(Query.class);
        String namedQuery = "Incidencia.findByComponent";
        List<Incidencia> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(iDao.getIncidenciasXComponente(Matchers.anyInt())).thenReturn(expected);
        List<Incidencia> result = iDao.getIncidenciasXComponente(Matchers.anyInt());
        assertThat(result, is(expected));
    }

    /**
     * Test of countIncidencias method, of class IncidenciaDAO.
     */
    @Test
    public void testCountIncidencias() {
        System.out.println("countIncidencias");
        query = mock(Query.class);
        String namedQuery = "Incidencia.findByStatus";
        int expected = Integer.SIZE;
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(iDao.countIncidencias(Matchers.anyString())).thenReturn(expected);
        int result = iDao.countIncidencias(Matchers.anyString());
        assertThat(result, is(expected));
    }

}
