/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.Recurso;
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
public class RecursoDAOTest {

    private EntityManager em;
    private Query query;
    private RecursoDAO rDao;

    @Before
    public void setUp() {

        this.rDao = mock(RecursoDAO.class);
        em = mock(EntityManager.class);
        this.rDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class RecursoDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(rDao.create((Recurso) any())).thenReturn(expected);
        String result = rDao.create((Recurso) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of remove method, of class RecursoDAO.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        String expected = "remove-method worked";
        when(rDao.remove((Recurso) any())).thenReturn(expected);
        String result = rDao.remove((Recurso) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of findByNombre method, of class RecursoDAO.
     */
    @Test
    public void testFindByNombre() {
        System.out.println("findByNombre");
        query = mock(Query.class);
        String namedQuery = "Recurso.findByNombre";
        Recurso expected = new Recurso();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(rDao.findByNombre(Matchers.anyString(), (Componente) any())).thenReturn(expected);
        Recurso result = rDao.findByNombre(Matchers.anyString(), (Componente) any());
        assertThat(result, is(expected));
    }

}
