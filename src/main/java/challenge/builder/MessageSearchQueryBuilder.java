package challenge.builder;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import challenge.model.Messages;


@Component
public class MessageSearchQueryBuilder {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    public List<Messages> getAllMessages(String text) {

        QueryBuilder query = QueryBuilders.boolQuery()
                .should(
                        QueryBuilders.queryStringQuery(text)
                                .lenient(true)
                                .field("content")
                ).should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true)
                        .field("content"));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<Messages> messages = elasticsearchTemplate.queryForList(build, Messages.class);

        return messages;
    }

}
