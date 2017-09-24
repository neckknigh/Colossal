/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ComponenteHasTag;
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
public class ComponenteHasTagDAOTest {

    private EntityManager em;
    private Query query;
    private ComponenteHasTagDAO cDao;

    @Before
    public void setUp() {

        this.cDao = mock(ComponenteHasTagDAO.class);
        em = mock(EntityManager.class);
        this.cDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class ComponenteHasTagDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(cDao.create((ComponenteHasTag) any())).thenReturn(expected);
        String result = cDao.create((ComponenteHasTag) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of removeAll method, of class ComponenteHasTagDAO.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        query = mock(Query.class);
        String namedQuery = "ComponenteHasTag.removeAll";
        boolean expected = true;
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(cDao.removeAll((Componente) any())).thenReturn(expected);
        boolean result = cDao.removeAll((Componente) any());
        assertThat(result, is(expected));
    }

}
