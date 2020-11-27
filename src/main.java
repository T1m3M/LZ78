
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		int input;
		Scanner _sc;
		String data;
		LZ78 alg;
		
		System.out.println("Welcome to LZ78 World!");
		
		System.out.println("\nWhat to you want to do?\n1- Compress\n2- Decompress");
		System.out.print("> ");
		
		_sc = new Scanner(System.in);
		input = _sc.nextInt();
		
		if (input == 1) {
			System.out.print("Original Data  : ");
			_sc = new Scanner(System.in);
			data = _sc.nextLine();
			
			// Example: ABAABABAABABBBBBBBBBBA
			alg = new LZ78(data);
			alg.compress();
		}
		
		else if (input == 2) {
			System.out.print("Compressed Data: ");
			_sc = new Scanner(System.in);
			data = _sc.nextLine();
			
			// Example: <0, 'A'> <0, 'B'> <1, 'A'> <2, 'A'> <4, 'A'> <4, 'B'> <2, 'B'> <7, 'B'> <8, 'B'> <1, ''>
			alg = new LZ78(data);
			alg.decompress();
		}
	}
}
