package com.chaikovsky;

import com.chaikovsky.service.VoteService;
import com.chaikovsky.service.VoteServiceImpl;
import com.chaikovsky.web.ClientVoteController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientVoteControllerTest.class)
public class ClientVoteControllerTest {

    @MockBean
    private JdbcTemplate jdbcTemplate;

    private RestTemplate restTemplate = new RestTemplate();
    private VoteServiceImpl service = new VoteServiceImpl(restTemplate);
    private ClientVoteController controller = new ClientVoteController(service);
    private MockRestServiceServer restServiceServer;
    private HttpServletResponse response = new MockHttpServletResponse();

    @Before
    public void setUp(){
        restServiceServer = MockRestServiceServer.createServer(restTemplate);

        ReflectionTestUtils.setField(service, "serverUri", "http://localhost:8080/server");
        ReflectionTestUtils.setField(service, "voteUri", "/{id}");
        ReflectionTestUtils.setField(service, "agreeUri", "/agree/{id}");
        ReflectionTestUtils.setField(service, "disagreeUri", "/disagree/{id}");
        ReflectionTestUtils.setField(service, "createUri", "/create/{name}");
        ReflectionTestUtils.setField(service, "deleteUri", "/delete/{id}");
    }

    @Test
    public void getVotesTest(){
        restServiceServer
                .expect(requestTo("http://localhost:8080/server"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        ModelAndView mav = controller.getVotes();
        restServiceServer.verify();
        assertViewName(mav, "voting");
        assertModelAttributeAvailable(mav, "votesList");
    }

    @Test
    public void getVoteTest(){
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        ModelAndView mav = controller.getVote("1");
        restServiceServer.verify();
        assertViewName(mav, "vote");
        assertModelAttributeAvailable(mav, "voteList");
    }

    @Test
    public void agreeTest() throws IOException {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/agree/1"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        controller.agree(this.response, "1");
        restServiceServer.verify();
    }

    @Test
    public void disagreeTest() throws IOException {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/disagree/1"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        controller.disagree(this.response, "1");
        restServiceServer.verify();
    }

    @Test
    public void createTest() throws IOException {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/create/name"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        controller.create(this.response, "name");
        restServiceServer.verify();
    }

    @Test
    public void deleteTest() throws IOException {
        restServiceServer
                .expect(requestTo("http://localhost:8080/server/delete/1"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(
                        "[{\"id\":0,\"title\":\"null\",\"agree\":0,\"disagree\":0}]",
                        MediaType.APPLICATION_JSON));

        controller.delete(this.response, "1");
        restServiceServer.verify();
    }
}
