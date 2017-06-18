package com.chaikovsky.dao;

import com.chaikovsky.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@PropertySource("classpath:properties/query.properties")
public class DAOImpl implements DAO {

    @Value("${getAll}")
    private String getAll;
    @Value("${getById}")
    private String getByIdQuery;
    @Value("${create}")
    private String createQuery;
    @Value("${delete}")
    private String deleteQuery;
    @Value("${agree}")
    private String agreeQuery;
    @Value("${disagree}")
    private String disagreeQuery;

    private NamedParameterJdbcTemplate template;

    @Autowired
    public DAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private Vote voteSetter(ResultSet rs, int rowNum) throws SQLException {
        Vote vote = new Vote();
        vote.setId(rs.getLong("id"));
        vote.setTitle(rs.getString("title"));
        vote.setAgreeCount(rs.getInt("agree_count"));
        vote.setDisagreeCount(rs.getInt("disagree_count"));
        return vote;
    }

    @Override
    public List<Vote> findVotes() {
        return template.query(getAll, this::voteSetter);
    }

    @Override
    public List<Vote> findVote(long id) {
        List<Vote> votes = template.query(getByIdQuery,
                new MapSqlParameterSource("id", id), this::voteSetter);
        if (votes.isEmpty())
            return null;
        return votes;
    }

    @Override
    public void agree(long id) {
        template.update(agreeQuery,
                new MapSqlParameterSource("id", id));
    }

    @Override
    public void disagree(long id) {
        template.update(disagreeQuery,
                new MapSqlParameterSource("id", id));
    }

    @Override
    public void create(String voteName) {
        template.update(createQuery, new MapSqlParameterSource("voteName", voteName));
    }

    @Override
    public void delete(long id) {
        template.update(deleteQuery,
                new MapSqlParameterSource("id", id));
    }
}
