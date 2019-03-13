public class Token {

	private String Type;
	private String Value;

	public Token(String type, String value) {
		this.Type = type;
		this.Value = value;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

}