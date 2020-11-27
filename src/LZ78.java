import java.util.ArrayList;

public class LZ78 {
	
	private static String data = "";
	private static ArrayList<String> dict = new ArrayList<String>();
	private static int current = 0;
	private int index;
	private char symbol;
	private static Tag tag;
	private static ArrayList<Tag> allTags = new ArrayList<Tag>();
	
	LZ78(String d){
		data = d;
		dict.add(""); // make the first element in the dictionary empty
	}
	
	private void printAllTags() {
		for(int i = 0; i < allTags.size(); i++) {
			allTags.get(i).printTag();
			System.out.print(" ");
		}
		
		System.out.print("\n");
	}
	
	private void printDict() {
		System.out.println("\n==[ Dictionary ]==\n");
		for(int i = 0; i < dict.size(); i++) {
			System.out.println("| " + i + " |\t" + dict.get(i) + "\t |");
			System.out.println("------------------");
		}
	}
	
	private Tag searching(String chunk) {
		
		// if in the dictionary get the index
		if(dict.contains(chunk)) {

			index = dict.indexOf(chunk);
			
			// if there's no next symbol (end of text) then put null ' '
			if(current + dict.get(index).length() >= data.length()) {
				symbol = ' ';
			}
			
			else {
				// get the next symbol after the matched chunk
				symbol = data.charAt(current + chunk.length());
				
				// update the dictionary
				dict.add(dict.get(index) + symbol);
			}
			
			
			return new Tag(index, symbol);
		}
		
		// if not search for a smaller chunk
		else {
			return searching(chunk.substring(0, chunk.length() - 1));
		}
		
	}

	public void compress() {
		
		// searching for the largest possible chunk in dictionary
		while(current < data.length()) {
			
			// start searching from current to the end
			tag = searching(data.substring(current));
			
			allTags.add(tag); // append to all tags array list
			
			// update the current forward by matched chunk's length + the next symbol's length
			current += dict.get(tag.getIndex()).length() + 1;
		}

		// print all the tags
		System.out.print("Compressed Data: ");
		printAllTags();
		
		// print the dictionary
		printDict();
	}

	public void decompress() {
		
	}
}
