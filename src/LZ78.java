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
	
	private void parsing(String inTags) {

	     String insideTag = inTags.split("[\\\\<\\\\>]")[1];
	     
	     index = Integer.parseInt(insideTag.substring(0, insideTag.indexOf(",")));
	     
	     // check if next symbol is not null ' '
	     if (insideTag.charAt(insideTag.indexOf("'") + 1) != '\'' )
	    	 symbol = insideTag.charAt(insideTag.indexOf("'") + 1);
	     else
	    	 symbol = 0;
	     
	     tag = new Tag(index, symbol);
	     allTags.add(tag);
	     
	     // if the previous wan't the last tag
	     if(!inTags.equals("<" + insideTag + ">"))
	    	 parsing(inTags.substring(insideTag.length() + 3)); // 3 = ' ' + '<' + '>'
	}

	public void decompress() {
		
		String original = "";
		String restore = "";
		
		// load the input to the allTags array list
		parsing(data);
		
		for(int i = 0; i < allTags.size(); i++) {
			tag = allTags.get(i);
			
			// restore the data from the tag
			restore = dict.get(tag.getIndex()) + tag.getSym();
			
			// append the restored chunk to the original
			original += restore;
			
			// if the word restored is not in the dictionary
			// and the last character restored is not null append to dictionary
			if(!dict.contains(restore) && restore.charAt(restore.length() - 1) != ' ') {
				
				// add the new resulting chunk to dictionary for future references
				dict.add(restore);
			}
		}

		System.out.print("Original Data  : " + original);
		
		// print the dictionary
		printDict();
		
	}
}
