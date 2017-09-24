/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author neck
 */
public class ComponenteDAOTest {

    private EntityManager em;
    private Query query;
    private ComponenteDAO cDao;

    @Before
    public void setUp() {

        this.cDao = mock(ComponenteDAO.class);
        em = mock(EntityManager.class);
        this.cDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class ComponenteDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(cDao.create((Componente) any())).thenReturn(expected);
        String result = cDao.create((Componente) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of edit method, of class ComponenteDAO.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        String expected = "edit-method worked";
        when(cDao.edit((Componente) any())).thenReturn(expected);
        String result = cDao.edit((Componente) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of getComponentesRecientes method, of class ComponenteDAO.
     */
    @Test
    public void testGetComponentesRecientes() {
        System.out.println("getComponentesRecientes");
        query = mock(Query.class);
        String nativeQuery = "Componente.findByRecently";
        List<Componente> expected = new ArrayList<>();
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(cDao.getComponentesRecientes()).thenReturn(expected);
        List<Componente> result = cDao.getComponentesRecientes();
        assertThat(result, is(expected));
    }
//

    /**
     * Test of getComponentesByNombreYDescripcion method, of class
     * ComponenteDAO.
     */
    @Test
    public void testGetComponentesByNombreYDescripcion() {
        System.out.println("getComponentesByNombreYDescripcion");
        query = mock(Query.class);
        String nativeQuery = "Componente.findByString";
        List<Componente> expected = new ArrayList<>();
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(cDao.getComponentesByNombreYDescripcion(Matchers.anyString(), anyInt())).thenReturn(expected);
        List<Componente> result = cDao.getComponentesByNombreYDescripcion(Matchers.anyString(), anyInt());
        assertThat(result, is(expected));
    }

    /**
     * Test of getCantComponentesByNombreYDescripcion method, of class
     * ComponenteDAO.
     */
    @Test
    public void testGetCantComponentesByNombreYDescripcion() {
        System.out.println("getCantComponentesByNombreYDescripcion");
        query = mock(Query.class);
        String nativeQuery = "Componente.countByString";
        int expected = Integer.SIZE;
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(cDao.getCantComponentesByNombreYDescripcion(Matchers.anyString())).thenReturn(expected);
        int result = cDao.getCantComponentesByNombreYDescripcion(Matchers.anyString());
        assertThat(result, is(expected));
    }
//

    /**
     * s
     * Test of getComponentesByCategory method, of class ComponenteDAO.
     */
    @Test
    public void testGetComponentesByCategory() {
        System.out.println("getComponentesByCategory");
        query = mock(Query.class);
        String nativeQuery = "Componente.findByCategory";
        List<Componente> expected = new ArrayList<>();
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(cDao.getComponentesByCategory(anyInt(), anyInt(), anyString())).thenReturn(expected);
        List<Componente> result = cDao.getComponentesByCategory(anyInt(), anyInt(), anyString());
        assertThat(result, is(expected));
    }

    /**
     * Test of getCantComponentesByCategory method, of class ComponenteDAO.
     */
    @Test
    public void testGetCantComponentesByCategory() {
        System.out.println("getCantComponentesByCategory");
        query = mock(Query.class);
        String nativeQuery = "Componente.CountComponentsByCategory";
        int expected = Integer.SIZE;
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(cDao.getCantComponentesByCategory(anyInt(), anyString())).thenReturn(expected);
        int result = cDao.getCantComponentesByCategory(anyInt(), anyString());
        assertThat(result, is(expected));
    }
//

    /**
     * Test of getComponentesByTag method, of class ComponenteDAO.
     */
    @Test
    public void testGetComponentesByTag() {
        System.out.println("getComponentesByTag");
        query = mock(Query.class);
        String nativeQuery = "Componente.findByTag";
        List<Componente> expected = new ArrayList<>();
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(cDao.getComponentesByTag(anyString(), anyInt(), anyString())).thenReturn(expected);
        List<Componente> result = cDao.getComponentesByTag(anyString(), anyInt(), anyString());
        assertThat(result, is(expected));
    }

    /**
     * Test of getCantComponentesByTag method, of class ComponenteDAO.
     */
    @Test
    public void testGetCantComponentesByTag() {
        System.out.println("getCantComponentesByTag");
        query = mock(Query.class);
        String nativeQuery = "Componente.CountComponentsByTag";
        int expected = Integer.SIZE;
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(cDao.getCantComponentesByTag(anyString(), anyString())).thenReturn(expected);
        int result = cDao.getCantComponentesByTag(anyString(), anyString());
        assertThat(result, is(expected));
    }

    /**
     * Test of getComponenteById method, of class ComponenteDAO.
     */
    @Test
    public void testGetComponenteById() {
        System.out.println("getComponenteById");
        query = mock(Query.class);
        String nativeQuery = "Componente.findByIdComponente";
        Componente expected = new Componente();
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(cDao.getComponenteById(anyInt())).thenReturn(expected);
        Componente result = cDao.getComponenteById(anyInt());
        assertThat(result, is(expected));
    }

    /**
     * Test of findByNombre method, of class ComponenteDAO.
     */
    @Test
    public void testFindByNombre() {
        System.out.println("findByNombre");
        query = mock(Query.class);
        String namedQuery = "Componente.findByNombre";
        Componente expected = new Componente();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(cDao.findByNombre(anyString())).thenReturn(expected);
        Componente result = cDao.findByNombre(anyString());
        assertThat(result, is(expected));
    }
//

    /**
     * Test of findComponentsLikeNombre method, of class ComponenteDAO.
     */
    @Test
    public void testFindComponentsLikeNombre() {
        System.out.println("findComponentsLikeNombre");
        query = mock(Query.class);
        String namedQuery = "Componente.findComponentsLikeNombre";
        List<Componente> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(cDao.findComponentsLikeNombre(anyString(), (Usuario) any())).thenReturn(expected);
        List<Componente> result = cDao.findComponentsLikeNombre(anyString(), (Usuario) any());
        assertThat(result, is(expected));
    }
//

    /**
     * Test of findComponentsLikeNombreAprobados method, of class ComponenteDAO.
     */
    @Test
    public void testFindComponentsLikeNombreAprobados() {
        System.out.println("findComponentsLikeNombreAprobados");
        query = mock(Query.class);
        String namedQuery = "Componente.findComponentsLikeNombreAprobados";
        List<Componente> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(cDao.findComponentsLikeNombreAprobados(anyString(), (Componente) any())).thenReturn(expected);
        List<Componente> result = cDao.findComponentsLikeNombreAprobados(anyString(), (Componente) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of getCantComponentsAdvancedSearch method, of class ComponenteDAO.
     */
    @Test
    public void testGetCantComponentsAdvancedSearch() {
        System.out.println("getCantComponentsAdvancedSearch");
        String cquery = "Componente.dummyQuery";
        query = mock(Query.class);
        int expected = Integer.SIZE;
        when(this.em.createNamedQuery(cquery)).thenReturn(query);
        when(cDao.getCantComponentsAdvancedSearch(anyString(), (List<String>) any(), (List<String>) any(), (List<String>) any(), (Date) any(), (Date) any())).thenReturn(expected);
        int result = cDao.getCantComponentsAdvancedSearch(anyString(), (List<String>) any(), (List<String>) any(), (List<String>) any(), (Date) any(), (Date) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of getAdvancedSearch method, of class ComponenteDAO.
     */
    @Test
    public void testGetAdvancedSearch() {
        System.out.println("getAdvancedSearch");
        query = mock(Query.class);
        String cQuery = "Componente.dummyQuery";
        List<Componente> expected = new ArrayList<>();
        when(this.em.createNamedQuery(cQuery)).thenReturn(query);
        when(cDao.getAdvancedSearch(anyString(), (List<String>) any(), (List<String>) any(), (List<String>) any(), (Date) any(), (Date) any(), anyInt())).thenReturn(expected);
        List<Componente> result = cDao.getAdvancedSearch(anyString(), (List<String>) any(), (List<String>) any(), (List<String>) any(), (Date) any(), (Date) any(), anyInt());
        assertThat(result, is(expected));
    }

    /**
     * Test of getReporteCantComponentesXTecnologias method, of class
     * ComponenteDAO.
     */
    @Test
    public void testGetReporteCantComponentesXTecnologias() {
        System.out.println("getReporteCantComponentesXTecnologias");
        query = mock(Query.class);
        List<Object[]> expected = new ArrayList<>();
        when(this.em.createNamedQuery("SELECT c.tecnologia,count(c.idComponente) "
                + " FROM componente c,peticionSubida p,estado e "
                + " WHERE p.componente_idComponente = c.idComponente and p.estado_idestado = e.idestado and e.estado = 'Aprobado' GROUP BY c.tecnologia")).thenReturn(query);
        when(cDao.getReporteCantComponentesXTecnologias()).thenReturn(expected);
        List<Object[]> result = cDao.getReporteCantComponentesXTecnologias();
        assertThat(result, is(expected));
    }
//

    /**
     * Test of getReporteCantComponentesXEstado method, of class ComponenteDAO.
     */
    @Test
    public void testGetReporteCantComponentesXEstado() {
        System.out.println("getReporteCantComponentesXEstado");
        query = mock(Query.class);
        List<Object[]> expected = new ArrayList<>();
        when(this.em.createNativeQuery("SELECT e.estado,count(c.idComponente) "
                + " FROM componente c,peticionSubida p,estado e "
                + " WHERE p.componente_idComponente = c.idComponente and p.estado_idestado = e.idestado  "
                + " GROUP BY e.estado")).thenReturn(query);
        when(cDao.getReporteCantComponentesXEstado()).thenReturn(expected);
        List<Object[]> result = cDao.getReporteCantComponentesXEstado();
        assertThat(result, is(expected));
    }
//

    /**
     * Test of actualizarVisualiciones method, of class ComponenteDAO.
     */
    @Test
    public void testActualizarVisualiciones() {
        System.out.println("actualizarVisualiciones");
        query = mock(Query.class);
        boolean expected = true;
        when(this.em.createNamedQuery("UPDATE componente SET visualizaciones = visualizaciones +1 WHERE idComponente=" + anyInt())).thenReturn(query);
        when(cDao.actualizarVisualiciones((Componente) any())).thenReturn(expected);
        boolean result = cDao.actualizarVisualiciones((Componente) any());
        assertThat(result, is(expected));
    }
//

    /**
     * Test of getReporteCantComponentesXVisualizaciones method, of class
     * ComponenteDAO.
     */
    @Test
    public void testGetReporteCantComponentesXVisualizaciones() {
        System.out.println("getReporteCantComponentesXVisualizaciones");
        query = mock(Query.class);
        String namedQuery = "SELECT c.nombre,c.visualizaciones "
                + " FROM componente c,peticionSubida p,estado e "
                + " WHERE p.componente_idComponente = c.idComponente and p.estado_idestado = e.idestado and e.estado='Aprobado' "
                + " ORDER BY c.visualizaciones ASC limit 10";
        List<Object[]> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(cDao.getReporteCantComponentesXVisualizaciones()).thenReturn(expected);
        List<Object[]> result = cDao.getReporteCantComponentesXVisualizaciones();
        assertThat(result, is(expected));
    }

}
