package challenge.resource;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import challenge.service.PersonServiceImpl;


@RestController
@RequestMapping("/rest/api/1.0/person")
public class PersonController {
	@Autowired
	private PersonServiceImpl personServiceImpl;
	
	@GetMapping(path="/FollowingAndFollowers", produces=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Map<String, List<String>>> getFollowingAndFollowers(Principal principal) {
		Map<String, List<String>> result = personServiceImpl.getFollowingAndFollowers(Integer.valueOf(principal.getName()));
		
		return new ResponseEntity<Map<String, List<String>>> (result, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(path="/popularFollowers", produces=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Map<Integer, Integer>>> getPopularFollowers() {
		List<Map<Integer, Integer>> result = personServiceImpl.getPopularFollowers();
		
		return new ResponseEntity<List<Map<Integer, Integer>>> (result, HttpStatus.OK);
	}
	
}