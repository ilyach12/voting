package com.chaikovsky.web;

import com.chaikovsky.dao.DAO;
import com.chaikovsky.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public ResponseEntity<List<Vote>> getVotes() {
        List<Vote> vote =  dao.findVotes();
        return ResponseEntity.ok(vote);
    }

    @RequestMapping(value = "/vote/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Vote>> getVote(@PathVariable("id") long id) {
        List<Vote> vote = dao.findVote(id);
        if (null == vote)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(vote);
    }

    @RequestMapping(value = "/agree/{id}", method = RequestMethod.PUT)
    public ResponseEntity agree(@PathVariable("id") long id) {
        dao.agree(id);
        return ResponseEntity.ok("OK");
    }

    @RequestMapping(value = "/disagree/{id}", method = RequestMethod.PUT)
    public ResponseEntity disagree(@PathVariable("id") long id) {
        dao.disagree(id);
        return ResponseEntity.ok("OK");
    }

    @RequestMapping(value = "/vote/{name}", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable("name") String voteName) {
        dao.create(voteName);
        return ResponseEntity.ok("OK");
    }

    @RequestMapping(value = "/vote/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") long id) {
        dao.delete(id);
        return ResponseEntity.ok("OK");
    }

}
