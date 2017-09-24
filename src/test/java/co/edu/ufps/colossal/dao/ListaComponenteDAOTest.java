/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.ListaComponente;
import co.edu.ufps.colossal.entities.Usuario;
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
public class ListaComponenteDAOTest {

    private EntityManager em;
    private Query query;
    private ListaComponenteDAO lDao;

    @Before
    public void setUp() {

        this.lDao = mock(ListaComponenteDAO.class);
        em = mock(EntityManager.class);
        this.lDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class ListaComponenteDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(lDao.create((ListaComponente) any())).thenReturn(expected);
        String result = lDao.create((ListaComponente) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of edit method, of class ListaComponenteDAO.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        String expected = "edit-method worked";
        when(lDao.edit((ListaComponente) any())).thenReturn(expected);
        String result = lDao.edit((ListaComponente) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of findByUser method, of class ListaComponenteDAO.
     */
    @Test
    public void testFindByUser() {
        System.out.println("findByUser");
        query = mock(Query.class);
        String namedQuery = "ListaComponente.findByUser";
        List<ListaComponente> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(lDao.findByUser((Usuario) Matchers.any())).thenReturn(expected);
        List<ListaComponente> result = lDao.findByUser((Usuario) Matchers.any());
        assertThat(result, is(expected));
    }

    /**
     * Test of findListaByName method, of class ListaComponenteDAO.
     */
    @Test
    public void testFindListaByName() {
        System.out.println("findListaByName");
        query = mock(Query.class);
        String namedQuery = "ListaComponente.findByNombreLista";
        ListaComponente expected = new ListaComponente();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(lDao.findListaByName(Matchers.anyString())).thenReturn(expected);
        ListaComponente result = lDao.findListaByName(Matchers.anyString());
        assertThat(result, is(expected));
    }

    /**
     * Test of findListById method, of class ListaComponenteDAO.
     */
    @Test
    public void testFindListById() {
        System.out.println("findListById");
        query = mock(Query.class);
        String namedQuery = "ListaComponente.findByIdlistaComponente";
        String expected = "expected";
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(lDao.findListById(Matchers.anyInt())).thenReturn(expected);
        String result = lDao.findListById(Matchers.anyInt());
        assertThat(result, is(expected));
    }

    /**
     * Test of deleteByName method, of class ListaComponenteDAO.
     */
    @Test
    public void testDeleteByName() {
        System.out.println("deleteByName");
        query = mock(Query.class);
        String namedQuery = "ListaComponente.deleteByName";
        int expected = Integer.SIZE;
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(lDao.deleteByName(Matchers.anyString())).thenReturn(expected);
        int result = lDao.deleteByName(Matchers.anyString());
        assertThat(result, is(expected));
    }

}
