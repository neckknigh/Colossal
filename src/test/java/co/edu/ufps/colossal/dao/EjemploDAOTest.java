/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Ejemplo;
import javax.persistence.EntityManager;
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
public class EjemploDAOTest {

    private EntityManager em;
    private EjemploDAO eDao;

    @Before
    public void setUp() {

        this.eDao = mock(EjemploDAO.class);
        em = mock(EntityManager.class);
        this.eDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class EjemploDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(eDao.create((Ejemplo) any())).thenReturn(expected);
        String result = eDao.create((Ejemplo) any());
        assertThat(result, is(expected));
    }

}
