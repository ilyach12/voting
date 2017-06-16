package com.chaikovsky.service;

import com.chaikovsky.model.Vote;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VoteService {

    List<Vote> getVotes();

    List<Vote> getVote(String id);

    void agree(String id);

    void disagree(String id);

    void create(String voteName);

    void delete(String id);
}
