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

import challenge.model.MessageResponse;
import challenge.model.Messages;

@Service
public class MessageServiceImpl {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private final String fetchMyMessages = "SELECT PEOPLE.NAME as name, MESSAGES.CONTENT as content FROM MESSAGES "
			+ "INNER JOIN PEOPLE  ON (MESSAGES.PERSON_ID = PEOPLE.ID )"
			+ "where MESSAGES.PERSON_ID = :id";
	private final String fetchMessagesFromUsersIFollow = "SELECT PEOPLE.NAME as name, MESSAGES.CONTENT as content FROM MESSAGES INNER JOIN FOLLOWERS ON"
			+ "(MESSAGES.PERSON_ID  = FOLLOWERS.PERSON_ID )"
			+ "INNER JOIN PEOPLE  ON (MESSAGES.PERSON_ID = PEOPLE.ID )"
			+ "where FOLLOWERS.FOLLOWER_PERSON_ID  = :id";
	
	public List<MessageResponse> getMyMessages (int userId) {
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("id", userId);
		return namedParameterJdbcTemplate.query(fetchMyMessages, parameters, new MessageResponseMapper());
	}
	
	public List<Messages> getMyMessagesForSearch (int userId) {
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("id", userId);
		return namedParameterJdbcTemplate.query(fetchMyMessages, parameters, new MessageMapperForSearch());
	}

	public List<MessageResponse> getAllMessages(int userId) {
		List<MessageResponse> returnList = new ArrayList<MessageResponse>();
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("id", userId);
		List<MessageResponse> myMessages = getMyMessages(userId);
		for(MessageResponse message : myMessages)
			returnList.add(message);
		
		List<MessageResponse> messagesFromUsersIFollow = namedParameterJdbcTemplate.query(fetchMessagesFromUsersIFollow, parameters, new MessageResponseMapper());
		for(MessageResponse message : messagesFromUsersIFollow)
			returnList.add(message);
		
		return returnList;
	}
	
	public List<Messages> getAllMessagesForSearch(int userId) {
		List<Messages> returnList = new ArrayList<Messages>();
		Map<String, Integer> parameters = new HashMap<String, Integer>();
		parameters.put("id", userId);
		List<Messages> myMessages = getMyMessagesForSearch(userId);
		for(Messages message : myMessages)
			returnList.add(message);
		
		List<Messages> messagesFromUsersIFollow = namedParameterJdbcTemplate.query(fetchMessagesFromUsersIFollow, parameters, new MessageMapperForSearch());
		for(Messages message : messagesFromUsersIFollow)
			returnList.add(message);
		
		return returnList;
	}
}

class MessageResponseMapper implements RowMapper<MessageResponse> {

	@Override
	public MessageResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		MessageResponse message = new MessageResponse();
		message.setPersonName(rs.getString("name"));
		message.setContent(rs.getString("content"));
		return message;
	}

}

class MessageMapperForSearch implements RowMapper<Messages> {

	@Override
	public Messages mapRow(ResultSet rs, int rowNum) throws SQLException {
		Messages message = new Messages();
		message.setContent(rs.getString("content"));
		return message;
	}

}
