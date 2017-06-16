package com.chaikovsky;

import com.chaikovsky.dao.DAOImpl;
import com.chaikovsky.model.Vote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DAOImplTest {

    @Autowired
    private DAOImpl dao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void findVotesTest() {
        List<Vote> vote = dao.findVotes();
        assertNotNull(vote);
        assertEquals(4, vote.size());
    }

    @Test
    public void findVoteTest() {
        List<Vote> vote = dao.findVote(1L);
        assertNotNull(vote);
        assertEquals(1, vote.size());
    }

    @Test
    public void agreeTest() {
        dao.agree(1L);
        String SQL = "SELECT AGREE_COUNT FROM VOTING WHERE ID = 1";
        jdbcTemplate.query(SQL, (rs) -> {
            Vote vote = new Vote();
            vote.setAgreeCount(rs.getInt("agree_count"));
            assertEquals(1, vote.getAgreeCount());
        });
    }

    @Test
    public void disagreeTest() {
        dao.disagree(1L);
        String SQL = "SELECT DISAGREE_COUNT FROM VOTING WHERE ID = 1";
        jdbcTemplate.query(SQL, (rs) -> {
            Vote vote = new Vote();
            vote.setAgreeCount(rs.getInt("disagree_count"));
            assertEquals(1, vote.getAgreeCount());
        });
    }

    @Test
    public void createTest() {
        dao.create("VOTE");
        List<Vote> vote = dao.findVote(5L);
        assertEquals(1, vote.size());
    }

    @Test
    public void deleteTest() {
        dao.delete(2L);
        List<Vote> vote = dao.findVote(2L);
        assertEquals(0, vote.size());
    }
}
