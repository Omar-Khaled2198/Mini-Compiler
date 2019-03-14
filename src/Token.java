public class Token implements Comparable<Token> {

	private String Type;
	private String Value;
	private int Start;
	private int End;

	public Token(String type, String value, int start, int end) {
		this.Type = type;
		this.Value = value;
		this.Start = start;
		this.End = end;
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

	public int getStart() {
		return Start;
	}

	public void setStart(int start) {
		Start = start;
	}

	public int getEnd() {
		return End;
	}

	public void setEnd(int end) {
		End = end;
	}

	@Override
	public int compareTo(Token token) {
		// TODO Auto-generated method stub
		return this.Start - token.getStart();
	}

	public String toString() {
		return "<" + this.Type + ">: " + this.Value;
	}

}