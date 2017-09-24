/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.colossal.dao;

import co.edu.ufps.colossal.entities.Revision;
import javax.persistence.EntityManager;
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
public class RevisionDAOTest {

    private EntityManager em;
    private RevisionDAO rDao;

    @Before
    public void setUp() {

        this.rDao = mock(RevisionDAO.class);
        em = mock(EntityManager.class);
        this.rDao.setEntityManager(em);
    }

    /**
     * Test of create method, of class RevisionDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String expected = "create-method worked";
        when(rDao.create((Revision) any())).thenReturn(expected);
        String result = rDao.create((Revision) any());
        assertThat(result, is(expected));
    }

}
