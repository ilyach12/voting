package com.chaikovsky.service;

import com.chaikovsky.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:properties/uri.properties")
public class VoteServiceImpl implements VoteService {

    @Value("${serverUri}")
    private String serverUri;
    @Value("${voteUri}")
    private String voteUri;
    @Value("${votesUri}")
    private String votesUri;
    @Value("${agreeUri}")
    private String agreeUri;
    @Value("${disagreeUri}")
    private String disagreeUri;
    @Value("${createUri}")
    private String createUri;
    @Value("${deleteUri}")
    private String deleteUri;

    private final RestTemplate template;

    @Autowired
    public VoteServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Vote> getVotes() {
        Vote[] vote = template.getForObject(serverUri + votesUri, Vote[].class);
        return Arrays.asList(vote);
    }

    @Override
    public List<Vote> getVote(String id) {
        Map<String, String> map = new HashMap<>(1);
        map.put("id", id);
        Vote[] vote = template.getForObject(serverUri + voteUri, Vote[].class, map);
        return Arrays.asList(vote);
    }

    @Override
    public void agree(String id) {
        Map<String, String> map = new HashMap<>(1);
        map.put("id", id);
        template.put(serverUri + agreeUri, Vote.class, map);
    }

    @Override
    public void disagree(String id) {
        Map<String, String> map = new HashMap<>(1);
        map.put("id", id);
        template.put(serverUri + disagreeUri, Vote.class, map);
    }

    @Override
    public void create(String voteName) {
        Map<String, String> map = new HashMap<>(1);
        map.put("name", voteName);
        template.postForLocation(serverUri + createUri, Vote.class, map);
    }

    @Override
    public void delete(String id) {
        Map<String, String> map = new HashMap<>(1);
        map.put("id", id);
        template.delete(serverUri + deleteUri, map);
    }
}
