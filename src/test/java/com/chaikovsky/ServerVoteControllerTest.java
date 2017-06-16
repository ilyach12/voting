package com.chaikovsky;

import com.chaikovsky.dao.DAOImpl;
import com.chaikovsky.model.Vote;
import com.chaikovsky.web.ServerVoteController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ServerVoteController.class)
public class ServerVoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DAOImpl dao;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        Mockito.reset(dao);
    }

    @Test
    public void getVotesTest() throws Exception {
        when(dao.findVotes()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/server"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void getVoteTest() throws Exception {
        when(dao.findVote(1L)).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/server/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void agreeTest() throws Exception {
        this.mockMvc.perform(post("/server/agree/1"))
                .andExpect(status().isOk());
        verify(dao, times(1)).agree(1L);
    }

    @Test
    public void disagreeTest() throws Exception {
        this.mockMvc.perform(post("/server/disagree/1"))
                .andExpect(status().isOk());
        verify(dao, times(1)).disagree(1L);
    }

    @Test
    public void createTest() throws Exception {
        this.mockMvc.perform(post("/server/create/name"))
                .andExpect(status().isOk());
        verify(dao, times(1)).create("name");
    }

    @Test
    public void deleteTest() throws Exception {
        this.mockMvc.perform(post("/server/delete/2"))
                .andExpect(status().isOk());
        verify(dao, times(1)).delete(2L);
    }
}
