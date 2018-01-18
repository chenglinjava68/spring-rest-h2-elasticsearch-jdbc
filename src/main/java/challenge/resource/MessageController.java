package challenge.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import challenge.model.MessageResponse;
import challenge.service.MessageServiceImpl;

@RestController
@RequestMapping("/rest/api/1.0/messages")
public class MessageController {
	@Autowired
	private MessageServiceImpl messageServiceImpl;
	
	@GetMapping(path="/", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<MessageResponse> getMyMessages(Principal principal) {
		int userId = Integer.valueOf(principal.getName());
        return messageServiceImpl.getMyMessages(userId);
    }
	
	@GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<MessageResponse> getAllMessages(Principal principal) {
		int userId = Integer.valueOf(principal.getName());
        return messageServiceImpl.getAllMessages(userId);
    }
}
