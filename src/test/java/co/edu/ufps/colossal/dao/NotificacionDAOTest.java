/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Notificacion;
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
public class NotificacionDAOTest {

    private EntityManager em;
    private Query query;
    private NotificacionDAO nDao;

    @Before
    public void setUp() {

        this.nDao = mock(NotificacionDAO.class);
        em = mock(EntityManager.class);
        this.nDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class NotificacionDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(nDao.create((Notificacion) any())).thenReturn(expected);
        String result = nDao.create((Notificacion) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of getNotificaciones method, of class NotificacionDAO.
     */
    @Test
    public void testGetNotificaciones() {
        System.out.println("getNotificaciones");
        query = mock(Query.class);
        String namedQuery = "Notificacion.findNotifications";
        List<Notificacion> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(nDao.getNotificaciones(Matchers.anyInt(), Matchers.anyInt(), Matchers.anyInt())).thenReturn(expected);
        List<Notificacion> result = nDao.getNotificaciones(Matchers.anyInt(), Matchers.anyInt(), Matchers.anyInt());
        assertThat(result, is(expected));
    }

    /**
     * Test of countNotificaciones method, of class NotificacionDAO.
     */
    @Test
    public void testCountNotificaciones() {
        System.out.println("countNotificaciones");
        query = mock(Query.class);
        String namedQuery = "Notificacion.CountNotifications";
        int expected = Integer.SIZE;
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(nDao.countNotificaciones(Matchers.anyInt(), Matchers.anyInt())).thenReturn(expected);
        int result = nDao.countNotificaciones(Matchers.anyInt(), Matchers.anyInt());
        assertThat(result, is(expected));
    }

    /**
     * Test of getNotificacion method, of class NotificacionDAO.
     */
    @Test
    public void testGetNotificacion() {
        System.out.println("getNotificacion");
        query = mock(Query.class);
        String namedQuery = "Notificacion.findByIdNotificacion";
        Notificacion expected = new Notificacion();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(nDao.getNotificacion(Matchers.anyInt())).thenReturn(expected);
        Notificacion result = nDao.getNotificacion(Matchers.anyInt());
        assertThat(result, is(expected));
    }

}
