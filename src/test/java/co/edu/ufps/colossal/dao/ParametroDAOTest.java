/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Parametro;
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
public class ParametroDAOTest {

    private EntityManager em;
    private ParametroDAO pDao;

    @Before
    public void setUp() {

        this.pDao = mock(ParametroDAO.class);
        em = mock(EntityManager.class);
        this.pDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class ParametroDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(pDao.create((Parametro) any())).thenReturn(expected);
        String result = pDao.create((Parametro) any());
        assertThat(result, is(expected));
    }

}
