package com.chaikovsky;

import com.chaikovsky.service.VoteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@WebMvcTest(VoteServiceImpl.class)
public class VoteServiceImplTest {

    @MockBean
    private JdbcTemplate jdbcTemplate;

    private RestTemplate restTemplate = new RestTemplate();
    private VoteServiceImpl service = new VoteServiceImpl(restTemplate);
    private MockRestServiceServer restServiceServer;

    @Before
    public void setUp(){
        restServiceServer = MockRestServiceServer.createServer(restTemplate);

        ReflectionTestUtils.setField(service, "serverUri", "http://localhost:8080/server");
        ReflectionTestUtils.setField(service, "votesUri", "/vote");
        ReflectionTestUtils.setField(service, "voteUri", "/vote/{id}");
        ReflectionTestUtils.setField(service, "agreeUri", "/agree/{id}");
        ReflectionTestUtils.setField(service, "disagreeUri", "/disagree/{id}");
        ReflectionTestUtils.setField(service, "createUri", "/vote/{name}");
        ReflectionTestUtils.setField(service, "deleteUri", "/vote/{id}");
    }

    @Test
    public void getVotesTest() throws Exception {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/vote"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        service.getVotes();
        restServiceServer.verify();
    }

    @Test
    public void getVoteTest() throws Exception {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/vote/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        service.getVote("1");
        restServiceServer.verify();
    }

    @Test
    public void agreeTest() throws Exception {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/agree/1"))
                .andExpect(method(HttpMethod.PUT)).andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        service.agree("1");
        restServiceServer.verify();
    }

    @Test
    public void disagreeTest() throws Exception {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/disagree/1"))
                .andExpect(method(HttpMethod.PUT)).andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        service.disagree("1");
        restServiceServer.verify();
    }

    @Test
    public void createTest() throws Exception {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/vote/name"))
                .andExpect(method(HttpMethod.POST)).andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        service.create("name");
        restServiceServer.verify();
    }

    @Test
    public void deleteTest() throws Exception {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/vote/1"))
                .andExpect(method(HttpMethod.DELETE)).andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        service.delete("1");
        restServiceServer.verify();
    }
}
