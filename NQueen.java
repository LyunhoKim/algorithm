import java.util.Scanner;


public class Main {

	static int [][] table;
	static int tableSize;
	static int cntPossibleQueen;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		while(T-- > 0) {
			tableSize = sc.nextInt();
			cntPossibleQueen = 0;
			table = new int [tableSize][tableSize];
			init();
			
			// 첫번째 행부터 시작 
			findNQueen(0);
			
			System.out.println(cntPossibleQueen);
			
			
		}
		
	}
	
	private static void init() {
		for(int i=0; i<tableSize; i++)
			for(int j=0; j<tableSize; j++)
				table[i][j] = 0;
	}

	private static void findNQueen(int depth) {
		if(depth == tableSize) {
			cntPossibleQueen++;
			return;
		}
			
		
		for(int i=0; i<tableSize; i++) {
			// 해당 열의 이전 Queen 위치 삭제 
			clearBoardRow(depth);
			if(isPossiblePosition(depth, i)) {
				// Queen 위치 표시 
				table[depth][i] = 1; 
				findNQueen(depth+1);
			}
		}
		
		
	}
	
	private static void clearBoardRow(int depth) {
		for(int i=0; i<tableSize; i++)
			table[depth][i] = 0;
	}

	private static boolean isPossiblePosition(int depth, int col) {
		for(int i=0; i<depth; i++) 
			if(table[i][col] != 0)
				return false;
		
		for(int i=depth, j=col; i>=0 && j>=0; i--, j--)
			if(table[i][j] != 0)
				return false;
		
		for(int i=depth, j=col; i>=0 && j<tableSize; i--, j++)
			if(table[i][j] != 0)
				return false;
		
		return true;
	}

}
