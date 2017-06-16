package com.chaikovsky.dao;

import com.chaikovsky.model.Vote;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DAO {

    List<Vote> findVote(long id);

    void agree(long id);

    void disagree(long id);

    void create(String voteName);

    void delete(long id);
}
