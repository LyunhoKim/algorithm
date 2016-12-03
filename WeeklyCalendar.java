import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	
	public enum WEEK { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }
	
	// 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1
	public static final int [] LASTDAY_OF_MONTH  = {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 31 };

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(System.in);
//		Scanner scanner = new Scanner(new FileInputStream("src/testcase.txt"));
		int T = Integer.parseInt(scanner.nextLine());
		
		while(T-- > 0) {
			String [] inputTokens = scanner.nextLine().split(" ");
			int [] result = new int [7];
			
			// 해당 월의 마지막 날짜 
			int lastDayOfMonth = LASTDAY_OF_MONTH[Integer.parseInt(inputTokens[0])];

			// 입력 요일 index 
			int indexOfInputDate = WEEK.valueOf(inputTokens[2]).ordinal();
			
			for(int i=0; i<7; i++) {
				
				// 일요일, 월요일...
				int date = Integer.parseInt(inputTokens[1]) - indexOfInputDate + i; 
				
				if(date > lastDayOfMonth)
					result[i] = date % lastDayOfMonth;
				else if(date < 1)
					result[i] = date + LASTDAY_OF_MONTH[ Integer.parseInt(inputTokens[0]) - 1];
				else
					result[i] = Integer.parseInt(inputTokens[1]) - indexOfInputDate + i;
			}
			
			for(int i : result)
				System.out.print(i + " ");
			System.out.println();
			
		}
		

	}

}
