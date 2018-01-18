package challenge.model;

public class MessageResponse {
	private String personName;
	private String content;
	
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "MessageResponse [personName=" + personName + ", content="
				+ content + "]";
	}
	
}
