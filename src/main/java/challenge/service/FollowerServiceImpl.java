package challenge.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import challenge.model.Following;

@Service
public class FollowerServiceImpl {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Following> getFollowers(int userId) {
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("id", userId);
		return namedParameterJdbcTemplate
				.query("SELECT PEOPLE.ID as id, PEOPLE.HANDLE as handle FROM PEOPLE  "
						+ "INNER JOIN FOLLOWERS ON (PEOPLE.ID = FOLLOWERS.FOLLOWER_PERSON_ID) "
						+ "where FOLLOWERS.PERSON_ID = :id",
						parameters, new RowMapper<Following>() {
							@Override
							public Following mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Following following = new Following();
								following.setPerson_id(rs.getInt("id"));
								following.setHandle(rs.getString("handle"));
								return following;
							}
						});
	}
}
