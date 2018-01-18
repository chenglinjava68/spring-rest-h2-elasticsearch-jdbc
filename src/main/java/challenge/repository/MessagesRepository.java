package challenge.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import challenge.model.Messages;

public interface MessagesRepository extends ElasticsearchRepository<Messages, Long>  {
	List<Messages> findByContent(String text);
}
