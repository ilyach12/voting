package com.chaikovsky.web;

import com.chaikovsky.dao.DAO;
import com.chaikovsky.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VoteController {

    private final DAO dao;

    @Autowired
    public VoteController(DAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Vote> getVote(@PathVariable("id") long id) {
        return dao.findVote(id);
    }

    @RequestMapping(value = "/agree/{id}", method = RequestMethod.GET)
    public void agree(@PathVariable("id") long id) {
        dao.agree(id);
    }

    @RequestMapping(value = "/disagree/{id}", method = RequestMethod.GET)
    public void disagree(@PathVariable("id") long id) {
        dao.disagree(id);
    }

    @RequestMapping(value = "/create/{title}", method = RequestMethod.POST)
    public void create(@PathVariable("title") String title) {
        dao.create(title);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void delete(@PathVariable("id") long id) {
        dao.delete(id);
    }

}
