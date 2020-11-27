
public class Tag {
	private int i;  // index
	private char s; // symbol
	
	Tag(int index, char sym){
		i = index;
		s = sym;
	}
	
	public void printTag() {
		System.out.print("<" + i + ", '" + s + "'>");
	}
	
	public int getIndex() {
		return i;
	}
	
	public char getSym() {
		return s;
	}
}
