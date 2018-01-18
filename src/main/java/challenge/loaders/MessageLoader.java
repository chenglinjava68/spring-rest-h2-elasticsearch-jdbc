package challenge.loaders;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import challenge.model.Messages;
import challenge.repository.MessagesRepository;
import challenge.service.MessageServiceImpl;

@Component
public class MessageLoader {
	
//    @Autowired
//    ElasticsearchOperations operations;
//
//    @Autowired
//    MessagesRepository messagesRepository;
//    
//    @Autowired
//    MessageServiceImpl messageServiceImpl;
//
//    @PostConstruct
//    @Transactional
//    public void loadAll(){
//
//        operations.putMapping(Messages.class);
//        System.out.println("Loading Data");
//        messagesRepository.save(getData());
//        System.out.println(getData());
//        System.out.printf("Loading Completed");
//
//    }
//
//    private List<Messages> getData() {
//    	Principal principal = SecurityContextHolder.getContext().getAuthentication();
//        return messageServiceImpl.getMyMessagesForSearch(Integer.valueOf(principal.getName()));
//    	return new ArrayList<Messages>();
//    }
}