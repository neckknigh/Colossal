/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.PeticionSubida;
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
public class PeticionSubidaDAOTest {

    private EntityManager em;
    private Query query;
    private PeticionSubidaDAO pDao;

    @Before
    public void setUp() {

        this.pDao = mock(PeticionSubidaDAO.class);
        em = mock(EntityManager.class);
        this.pDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class PeticionSubidaDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(pDao.create((PeticionSubida) any())).thenReturn(expected);
        String result = pDao.create((PeticionSubida) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of obtenerPeticionesPorEstado method, of class PeticionSubidaDAO.
     */
    @Test
    public void testObtenerPeticionesPorEstado() {
        System.out.println("obtenerPeticionesPorEstado");
        query = mock(Query.class);
        String namedQuery = "PeticionSubida.findByEstado";
        List<PeticionSubida> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(pDao.obtenerPeticionesPorEstado(Matchers.anyInt(), Matchers.anyString())).thenReturn(expected);
        List<PeticionSubida> result = pDao.obtenerPeticionesPorEstado(Matchers.anyInt(), Matchers.anyString());
        assertThat(result, is(expected));
    }
//

    /**
     * Test of obtenerCantPeticionesPorEstado method, of class
     * PeticionSubidaDAO.
     */
    @Test
    public void testObtenerCantPeticionesPorEstado() {
        System.out.println("obtenerCantPeticionesPorEstado");
        query = mock(Query.class);
        String namedQuery = "PeticionSubida.CountByEstado";
        int expected = Integer.SIZE;
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(pDao.obtenerCantPeticionesPorEstado(Matchers.anyInt(), Matchers.anyString())).thenReturn(expected);
        int result = pDao.obtenerCantPeticionesPorEstado(Matchers.anyInt(), Matchers.anyString());
        assertThat(result, is(expected));
    }

}
