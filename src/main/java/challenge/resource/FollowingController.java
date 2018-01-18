package challenge.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import challenge.model.Following;
import challenge.model.Users;
import challenge.service.FollowingServiceImpl;

@RestController
@RequestMapping("/rest/api/1.0/following")
public class FollowingController {
	@Autowired
	private FollowingServiceImpl followingServiceImpl; 
	
	@RequestMapping(value="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Following>> getFollowings(Principal principal) {
		List<Following> list = followingServiceImpl.getFollowings(Integer.valueOf(principal.getName()));
		if(list.size() == 0) {
			return new ResponseEntity<List<Following>> (list, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Following>> (list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> unfollow(Principal principal, @RequestBody Users user) {
		int myUserId = Integer.valueOf(principal.getName());
		followingServiceImpl.unfollow(myUserId, user);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> follow(Principal principal, @RequestBody Users user) {
		int myUserId = Integer.valueOf(principal.getName());
		followingServiceImpl.follow(myUserId, user);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
}
