/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Tag;
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
public class TagDAOTest {

    private EntityManager em;
    private Query query;
    private TagDAO tDao;

    @Before
    public void setUp() {

        this.tDao = mock(TagDAO.class);
        em = mock(EntityManager.class);
        this.tDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class TagDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(tDao.create((Tag) any())).thenReturn(expected);
        String result = tDao.create((Tag) any());
        assertThat(result, is(expected));
    }

    /**
     * Test of findAll method, of class TagDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        List<Tag> expected = new ArrayList<>();
        when(tDao.findAll()).thenReturn(expected);
        List<Tag> result = tDao.findAll();
        assertThat(result, is(expected));
    }

    /**
     * Test of getTagsById method, of class TagDAO.
     */
    @Test
    public void testGetTagsById() {
        System.out.println("getTagsById");
        query = mock(Query.class);
        String namedQuery = "Tag.findByIdtag";
        Tag expected = new Tag();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(tDao.getTagsById(Matchers.anyInt())).thenReturn(expected);
        Tag result = tDao.getTagsById(Matchers.anyInt());
        assertThat(result, is(expected));
    }

    /**
     * Test of findTagsLikeNombre method, of class TagDAO.
     */
    @Test
    public void testFindTagsLikeNombre() {
        System.out.println("findTagsLikeNombre");
        query = mock(Query.class);
        String namedQuery = "Tag.findTagsLikeNombre";
        List<Tag> expected = new ArrayList<>();
        when(this.em.createNamedQuery(namedQuery)).thenReturn(query);
        when(tDao.findTagsLikeNombre(Matchers.anyString())).thenReturn(expected);
        List<Tag> result = tDao.findTagsLikeNombre(Matchers.anyString());
        assertThat(result, is(expected));
    }

}
