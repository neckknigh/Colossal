/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.Metodo;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author neck
 */
public class MetodoDAOTest {

    private EntityManager em;
    private Query query;
    private MetodoDAO mDao;

    @Before
    public void setUp() {

        this.mDao = mock(MetodoDAO.class);
        em = mock(EntityManager.class);
        this.mDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class MetodoDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(mDao.create((Metodo) any())).thenReturn(expected);
        String result = mDao.create((Metodo) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of deleteAllByComponent method, of class MetodoDAO.
     */
    @Test
    public void testDeleteAllByComponent() {
        System.out.println("deleteAllByComponent");
        query = mock(Query.class);
        String nativeQuery = "Metodo.deleteAllByComponent";
        boolean expected = true;
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(mDao.deleteAllByComponent((Componente) any())).thenReturn(expected);
        boolean result = mDao.deleteAllByComponent((Componente) any());
        assertThat(result, is(expected));
    }

}
