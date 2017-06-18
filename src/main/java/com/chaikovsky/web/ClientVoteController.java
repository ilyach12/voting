package com.chaikovsky.web;

import com.chaikovsky.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ClientVoteController {

    private final VoteService service;

    @Autowired
    public ClientVoteController(VoteService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getVotes(){
        ModelAndView mav = new ModelAndView("voting");
        mav.addObject("votesList", service.getVotes());
        return mav;
    }

    @RequestMapping(value = "/vote/{id}", method = RequestMethod.GET)
    public ModelAndView getVote(@PathVariable("id") String id){
        ModelAndView mav = new ModelAndView("vote");
        mav.addObject("voteList", service.getVote(id));
        return mav;
    }

    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    public void agree(HttpServletResponse response, @RequestParam("agreeId") String id) throws IOException {
        service.agree(id);
        response.sendRedirect("http://localhost:8080/vote/" + id);
    }

    @RequestMapping(value = "/disagree", method = RequestMethod.POST)
    public void disagree(HttpServletResponse response, @RequestParam("disagreeId") String id) throws IOException {
        service.disagree(id);
        response.sendRedirect("http://localhost:8080/vote/" + id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(HttpServletResponse response, @RequestParam("name") String name) throws IOException {
        service.create(name);
        response.sendRedirect("http://localhost:8080");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(HttpServletResponse response, @RequestParam("id") String id) throws IOException {
        service.delete(id);
        response.sendRedirect("http://localhost:8080");
    }

}
