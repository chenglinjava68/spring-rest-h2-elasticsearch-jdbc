package challenge.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import challenge.model.Following;
import challenge.service.FollowerServiceImpl;

@RestController
@RequestMapping("/rest/api/1.0/followers")
public class FollowerController {
	@Autowired
	private FollowerServiceImpl followerServiceImpl;
	
	@RequestMapping(value="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Following>> getFollowers(Principal principal) {
		List<Following> list = followerServiceImpl.getFollowers(Integer.valueOf(principal.getName()));
		if(list.size() == 0) {
			return new ResponseEntity<List<Following>> (list, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Following>> (list, HttpStatus.OK);
	}
}
