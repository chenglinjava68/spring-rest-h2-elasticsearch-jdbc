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
import challenge.model.Users;

@Service
public class FollowingServiceImpl {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Following> getFollowings(int userId) {
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("id", userId);
		return namedParameterJdbcTemplate
				.query("SELECT PEOPLE.ID as id, PEOPLE.HANDLE as handle FROM PEOPLE  INNER JOIN FOLLOWERS ON (PEOPLE.ID = FOLLOWERS.PERSON_ID) "
						+ "where FOLLOWERS.FOLLOWER_PERSON_ID = :id",
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
	
	
	public void unfollow(int myUserId, Users user) {
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("userId", myUserId);
		parameters.put("followerId", user.getId());
		namedParameterJdbcTemplate.update("DELETE FROM FOLLOWERS where person_id = :followerId AND follower_person_id = :userId", parameters);
	}
	
	public void follow(int myUserId, Users user) {
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("userId", myUserId);
		parameters.put("followerId", user.getId());
		namedParameterJdbcTemplate.update("INSERT INTO FOLLOWERS(person_id, follower_person_id) VALUES (:followerId, :userId)", parameters);
	}
}
