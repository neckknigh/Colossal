/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author neck
 */
public class TokenDAOTest {

    private EntityManager em;
    private Query query;
    private TokenDAO tDao;

    @Before
    public void setUp() {

        this.tDao = mock(TokenDAO.class);
        em = mock(EntityManager.class);
        this.tDao.setEntityManager(em);
    }

    /**
     * Test of findValidToken method, of class TokenDAO.
     */
    @Test
    public void testFindValidToken() {
        System.out.println("findValidToken");
        query = mock(Query.class);
        List<Object[]> expected = new ArrayList<>();
        when(this.em.createNativeQuery("SELECT * FROM token WHERE token='" + Matchers.anyString() + "' AND NOW() < fechaExp")).thenReturn(query);
        when(tDao.findValidToken(Matchers.anyString())).thenReturn(expected);
        List<Object[]> result = tDao.findValidToken(Matchers.anyString());
        assertThat(result, is(expected));
    }

}
