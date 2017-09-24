/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.ListaComponente;
import co.edu.ufps.colossal.entities.ListaComponentehasComponente;
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
public class ListaComponentehasComponenteDAOTest {

    private EntityManager em;
    private Query query;
    private ListaComponentehasComponenteDAO lDao;

    @Before
    public void setUp() {

        this.lDao = mock(ListaComponentehasComponenteDAO.class);
        em = mock(EntityManager.class);
        this.lDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class ListaComponentehasComponenteDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(lDao.create((ListaComponentehasComponente) any())).thenReturn(expected);
        String result = lDao.create((ListaComponentehasComponente) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of findListaByIdListIdComp method, of class
     * ListaComponentehasComponenteDAO.
     */
    @Test
    public void testFindListaByIdListIdComp() {
        System.out.println("findListaByIdListIdComp");
        query = mock(Query.class);
        String namedQuery = "ListaComponentehasComponente.findListaByIdListIdComp";
        ListaComponentehasComponente expected = new ListaComponentehasComponente();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(lDao.findListaByIdListIdComp((ListaComponente) any(), (Componente) any())).thenReturn(expected);
        ListaComponentehasComponente result = lDao.findListaByIdListIdComp((ListaComponente) any(), (Componente) any());
        assertThat(result, is(expected));
    }

}
