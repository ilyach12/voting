package com.chaikovsky.web;

import com.chaikovsky.dao.DAO;
import com.chaikovsky.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server")
public class ServerVoteController {

    private final DAO dao;

    @Autowired
    public ServerVoteController(DAO dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Vote> getVotes() {
        return dao.findVotes();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Vote> getVote(@PathVariable("id") long id) {
        return dao.findVote(id);
    }

    @RequestMapping(value = "/agree/{id}", method = RequestMethod.POST)
    public void agree(@PathVariable("id") long id) {
        dao.agree(id);
    }

    @RequestMapping(value = "/disagree/{id}", method = RequestMethod.POST)
    public void disagree(@PathVariable("id") long id) {
        dao.disagree(id);
    }

    @RequestMapping(value = "/create/{name}", method = RequestMethod.POST)
    public void create(@PathVariable("name") String voteName) {
        dao.create(voteName);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void delete(@PathVariable("id") long id) {
        dao.delete(id);
    }

}
