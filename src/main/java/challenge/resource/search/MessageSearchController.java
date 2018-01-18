package challenge.resource.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import challenge.builder.MessageSearchQueryBuilder;
import challenge.model.Messages;
import challenge.repository.MessagesRepository;

@RestController
@RequestMapping("/rest/api/1.0/messages/search")
public class MessageSearchController {
	@Autowired
	private MessagesRepository messagesRepo;
	
	@Autowired
    private MessageSearchQueryBuilder messageSearchQueryBuilder;

    @GetMapping(value = "/{text}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Messages> getAllMessages(@PathVariable final String text) {
//        return messageSearchQueryBuilder.getAllMessages(text);
    	return messagesRepo.findByContent(text);
    }
}
