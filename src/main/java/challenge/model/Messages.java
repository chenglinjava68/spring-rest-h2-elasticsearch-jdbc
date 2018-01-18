package challenge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "messages", type = "messages", shards = 1)
public class Messages {
	@Id
	private int id;
    private int personId;
    private String content;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Messages [id=" + id + ", personId=" + personId + ", content="
				+ content + "]";
	}
}
