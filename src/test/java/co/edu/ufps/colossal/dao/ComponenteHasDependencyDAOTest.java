/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ComponenteHasDependency;
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
public class ComponenteHasDependencyDAOTest {

    private EntityManager em;
    private Query query;
    private ComponenteHasDependencyDAO cDao;

    @Before
    public void setUp() {

        this.cDao = mock(ComponenteHasDependencyDAO.class);
        em = mock(EntityManager.class);
        this.cDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class ComponenteHasDependencyDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(cDao.create((ComponenteHasDependency) any())).thenReturn(expected);
        String result = cDao.create((ComponenteHasDependency) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of deleteAllByComponentOwner method, of class
     * ComponenteHasDependencyDAO.
     */
    @Test
    public void testDeleteAllByComponentOwner() {
        System.out.println("deleteAllByComponentOwner");
        query = mock(Query.class);
        String namedQuery = "ComponenteHasDependency.deleteAllByComponentOwner";
        boolean expected = true;
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(cDao.deleteAllByComponentOwner((Componente) any())).thenReturn(expected);
        boolean result = cDao.deleteAllByComponentOwner((Componente) any());
        assertThat(result, is(expected));
    }

}
