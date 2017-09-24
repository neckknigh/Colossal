/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author neck
 */
public class UsuarioDAOTest {

    private EntityManager em;
    private Query query;
    private UsuarioDAO uDao;

    @Before
    public void setUp() {

        this.uDao = mock(UsuarioDAO.class);
        em = mock(EntityManager.class);
        this.uDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class UsuarioDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(uDao.create((Usuario) any())).thenReturn(expected);
        String result = uDao.create((Usuario) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of edit method, of class UsuarioDAO.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        String expected = "edit-method worked";
        when(uDao.edit((Usuario) any())).thenReturn(expected);
        String result = uDao.edit((Usuario) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of remove method, of class UsuarioDAO.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        String expected = "remove-method worked";
        when(uDao.remove((Usuario) any())).thenReturn(expected);
        String result = uDao.remove((Usuario) any());
        assertThat(result, is(expected));
    }
//

    /**
     * Test of find method, of class UsuarioDAO.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        Usuario expected = new Usuario();
        when(uDao.find(anyObject())).thenReturn(expected);
        Usuario result = uDao.find(anyObject());
        assertThat(result, is(expected));
    }
//

    /**
     * Test of findAll method, of class UsuarioDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        List<Usuario> expected = new ArrayList<>();
        when(uDao.findAll()).thenReturn(expected);
        List<Usuario> result = uDao.findAll();
        assertThat(result, is(expected));
    }

    /**
     * Test of count method, of class UsuarioDAO.
     */
    @Test
    public void testCount() {
        System.out.println("count");
        int expected = Integer.SIZE;
        when(uDao.count()).thenReturn(expected);
        int result = uDao.count();
        assertThat(result, is(expected));

    }
//

    /**
     * Test of iniciarSesion method, of class UsuarioDAO.
     */
    @Test
    public void testIniciarSesion() {
        System.out.println("iniciarSesion");
        query = mock(Query.class);
        String namedQuery = "Usuario.findByUsernamePass";
        Usuario expected = new Usuario();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(uDao.iniciarSesion((Usuario) any())).thenReturn(expected);
        Usuario result = uDao.iniciarSesion((Usuario) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of findUserByCodigo method, of class UsuarioDAO.
     */
    @Test
    public void testFindUserByCodigo() {
        System.out.println("findUserByCodigo");
        query = mock(Query.class);
        String namedQuery = "Usuario.findByUsernamePass";
        Usuario expected = new Usuario();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(uDao.iniciarSesion((Usuario) any())).thenReturn(expected);
        Usuario result = uDao.iniciarSesion((Usuario) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of getAdmins method, of class UsuarioDAO.
     */
    @Test
    public void testGetAdmins() {
        System.out.println("getAdmins");
        query = mock(Query.class);
        String namedQuery = "Usuario.findByTipoUsuario";
        List<Usuario> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(uDao.getAdmins()).thenReturn(expected);
        List<Usuario> result = uDao.getAdmins();
        assertThat(result, is(expected));

    }

    /**
     * Test of getUsuarioByComponente method, of class UsuarioDAO.
     */
    @Test
    public void testGetUsuarioByComponente() {
        System.out.println("getUsuarioByComponente");
        query = mock(Query.class);
        int componente = Integer.SIZE;
        String nativeQuery = "SELECT u.* FROM usuario u,peticionSubida p,componente c "
                + " WHERE u.codigo = p.usuario_codigo and p.componente_idComponente = c.idComponente and c.idComponente =" + componente;
        Usuario expected = new Usuario();
        when(this.em.createNativeQuery(nativeQuery)).thenReturn(query);
        when(uDao.getUsuarioByComponente(anyInt())).thenReturn(expected);
        Usuario result = uDao.getUsuarioByComponente(anyInt());
        assertThat(result, is(expected));
    }

}
