package challenge.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import challenge.model.Following;

@Service
public class PersonServiceImpl {
	@Autowired
	private FollowingServiceImpl followingServiceImpl;
	
	@Autowired
	private FollowerServiceImpl followerServiceImpl;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private final String fetchPopularFollowers = "SELECT person_id, "
			+ "( SELECT _f.follower_person_id "
			+ "FROM followers _f "
			+ "WHERE _f.person_id = f.person_id "
			+ "GROUP BY _f.follower_person_id "
			+ "ORDER BY COUNT( _f.follower_person_id ) "
			+ "DESC LIMIT 1) "
			+ "as popular_follower FROM followers f GROUP BY person_id";
	
	public Map<String, List<String>> getFollowingAndFollowers(int userId) {
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		List<Following> followings = followingServiceImpl.getFollowings(userId);
		List<Following> followers = followerServiceImpl.getFollowers(userId);
		
		List<String> followingHandles = new ArrayList<String>();
		for(Following f : followings)
			followingHandles.add(f.getHandle());
		result.put("following", followingHandles);
		
		List<String> followerHandles = new ArrayList<String>();
		for(Following f : followers)
			followerHandles.add(f.getHandle());
		result.put("followers", followerHandles);
		
		return result;
	}

	public List<Map<Integer, Integer>> getPopularFollowers() {
		return namedParameterJdbcTemplate
				.query(fetchPopularFollowers,
						new RowMapper<Map<Integer, Integer>>() {
							@Override
							public Map<Integer, Integer> mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Map<Integer, Integer> singleRecord = new HashMap<Integer, Integer>();
								int personID = rs.getInt("person_id");
								int popularFollower = rs.getInt("popular_follower");
								singleRecord.put(personID, popularFollower);
								return singleRecord;
							}
						});
	}
}
