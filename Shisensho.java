import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
	
	static final int PATH_TYPE_SAME_ROW = 1;
	static final int PATH_TYPE_SAME_COL = 2;
	static final int PATH_TYPE_OTHER = 3;

	public static void main(String[] args) throws FileNotFoundException {
		
//		Scanner scanner = new Scanner(System.in);
		Scanner scanner = new Scanner(new FileInputStream("src/testcase.txt"));
		int T = Integer.parseInt(scanner.nextLine());
		
		
		while(T-- > 0) {
			String [] boardSize = scanner.nextLine().split(" ");
			int nRow = Integer.parseInt(boardSize[0]);
			int nCol = Integer.parseInt(boardSize[1]);
			int cntVaildPath = 0;
			
			char [][] board = new char [nRow][nCol];
			ArrayList<Point> pointList = new ArrayList<Point>();
			
			// 2차원 배열 입력  
			for(int i=0; i<nRow; i++) 
				board[i] = scanner.nextLine().toCharArray();
			
			// 전체 Tile 검색
			for(int i=0; i<nRow; i++) {
				for(int j=0; j<nCol; j++) {
					if(board[i][j] != '.')
						pointList.add(new Point(board[i][j], i, j));
				}
			}

			for(int i=0; i<pointList.size(); i++) {
				
				Point p1 = pointList.get(i);
 				for(Point p2 : getSameTileList(pointList, i)) {
					//직진 경로 or 2번 꺾이는 경로
					if(Point.getPathType(p1, p2) == PATH_TYPE_SAME_ROW) {
						
						// 직진 경로 확인 (가로 방향) 
						if(isValidLandscapeDirectoly(p1, p2, board) || isPossiblePathWith2Turn(p1, p2, board))
							cntVaildPath++;
						
						
						
					//직진 경로 or 2번 꺾이는 경로 
					} else if(Point.getPathType(p1, p2) == PATH_TYPE_SAME_COL) {
						
						
						// 직진 경로 확인 (세로 방향) 
						if(isValidPortraitDirectly(p1, p2, board) || isPossiblePathWith2Turn(p1, p2, board))
							cntVaildPath++;
						
						
					//1번 꺾이는 경로 or 2번 꺾이는 경로 
					} else {
						
						if(isPossiblePathWith1Turn(p1, p2, board) || isPossiblePathWith2Turn(p1, p2, board))
							cntVaildPath++;
						
					}
 				}
			}
			
			
			
			System.out.println(cntVaildPath);
		}
	}
	
	private static boolean isValidLandscapeDirectoly(Point p1, Point p2, char [][] board) {
		int x = p1.x;
		if(p1.y > p2.y) {
			Point t = p1;
			p1 = p2;
			p2 = t;
		}
			
		for(int y=p1.y+1; y<p2.y; y++)
			if(board[x][y] != '.') 
				return false;
		
		return true;
		
	}
	
	private static boolean isValidPortraitDirectly(Point p1, Point p2, char [][] board) {
		int y = p1.y;
		if(p1.x > p2.x) {
			Point t = p1;
			p1 = p2;
			p2 = t;
		}
		
		for(int x=p1.x+1; x<p2.x; x++) 
			if(board[x][y] != '.') 
				return false;
		return true;
	}
	
	private static boolean isPossiblePathWith1Turn(Point p1, Point p2, char [][]  board) {
		//1번 꺾임 경로
		Point t1 = new Point(p1.tile, p1.x, p2.y);
		Point t2 = new Point(p1.tile, p2.x, p1.x);
		
		if( (isValidLandscapeDirectoly(p1, t1, board) && isValidPortraitDirectly(t1, p2, board) && board[t1.x][t1.y] == '.') ||
			(isValidPortraitDirectly(p1, t2, board) && isValidLandscapeDirectoly(t2, p2, board) && board[t2.x][t2.y] == '.') ) 
			return true;
		return false;
		
	}
	
	private static boolean isPossiblePathWith2Turn(Point p1, Point p2, char [][] board) {
		Point t1;
		Point t2;
		
		int nCol = board[0].length;
		int nRow = board.length;
		
		//우
		for(int c=nCol-1; c>p1.y && c>p2.y; c-- ) {
			t1 = new Point(p1.tile, p1.x, c);
			t2 = new Point(p1.tile, p2.x, c);
			
			if(isValidLandscapeDirectoly(p1, t1, board) && isValidLandscapeDirectoly(p2, t2, board) && isValidPortraitDirectly(t1, t2, board) && board[t1.x][t1.y] == '.' && board[t2.x][t2.y] == '.')  
				return true;
		}
		
		//좌
		for(int c=0; c<p1.y&& c<p2.y; c++) {
			t1 = new Point(p1.x, c);
			t2 = new Point(p2.x, c);
			if(isValidLandscapeDirectoly(p1, t1, board) && isValidLandscapeDirectoly(p2, t2, board) && isValidPortraitDirectly(t1, t2, board) && board[t1.x][t1.y] == '.' && board[t2.x][t2.y] == '.') 
				return true;
		}
			
		
		//상
		for(int r=0; r<p1.x && r<p2.x; r++) {
			t1 = new Point(0, p1.y);
			t2 = new Point(0, p2.y);
			if(isValidPortraitDirectly(p1, t1, board) && isValidPortraitDirectly(p2, t2, board) && isValidLandscapeDirectoly(t1, t2, board) && board[t1.x][t1.y] == '.' && board[t2.x][t2.y] == '.')
				return true;
		}
		
		//하
		for(int r=nRow-1; r>p1.x && r>p2.x; r--) {
			t1 = new Point(nRow-1, p1.y);
			t2 = new Point(nRow-1, p2.y);
			if(isValidPortraitDirectly(p1, t1, board) && isValidPortraitDirectly(p2, t2, board) && isValidLandscapeDirectoly(t1, t2, board) && board[t1.x][t1.y] == '.' && board[t2.x][t2.y] == '.')
				return true;
		}
		
		// 내부 - 가로
		if(p1.y > p2.y) {
			Point t = p1;
			p1 = p2;
			p2 = t;
		}
		
		for(int c=p1.y+1; c<p2.y; c++) {
			t1 = new Point(p1.x, c);
			t2 = new Point(p2.x, c);
			if(isValidLandscapeDirectoly(p1, t1, board) && isValidPortraitDirectly(t1, t2, board) && isValidLandscapeDirectoly(t2, p2, board) && board[t1.x][t1.y] == '.' && board[t2.x][t2.y] == '.' )
				return true;
		}
		
		// 내부 - 세로
		if(p1.x > p2.x) {
			Point t = p1;
			p1 = p2;
			p2 = t;
		}
		for(int r=p1.x+1; r<p2.x; r++) {
			t1 = new Point(r, p1.y);
			t2 = new Point(r, p2.y);
			if(isValidPortraitDirectly(p1, t1, board) && isValidLandscapeDirectoly(t1, t2, board) && isValidPortraitDirectly(t2, p2, board) && board[t1.x][t1.y] == '.' && board[t2.x][t2.y] == '.' )
				return true;
		}
		return false;
	}
	
	
	// Tile 리스트 중 같은 Tile을 가진 Point 검
	private static ArrayList<Point> getSameTileList(ArrayList<Point> pointList, int idx) {
		ArrayList<Point> tiles = new ArrayList<Point>();
		for(int i=idx+1; i<pointList.size(); i++) {
			if( Point.isSameTile(pointList.get(idx), pointList.get(i)))
				tiles.add(pointList.get(i));
		}
		return tiles;
	}

	public static class Point {
		int x, y;
		char tile;
		
		public Point(int x, int y) {
			this.tile = ' ';
			this.x = x;
			this.y = y;
		}
		
		public Point(char tile, int x, int y) {
			this.tile = tile;
			this.x = x;
			this.y = y;
		}
		
		
		// 같은 행 or 같은 열 or 이외인지 확인 
		public static int getPathType(Point p1, Point p2) {
			if(p1.x == p2.x)
				return PATH_TYPE_SAME_ROW;
			else if(p1.y == p2.y)
				return PATH_TYPE_SAME_COL;
			return PATH_TYPE_OTHER;
		}

		public void print() {
			System.out.println(this.tile + ": x=" + this.x + ", y=" + this.y);
		}
		
		// 2개의 점이 같은 모양의 Tile인지 확인  
		public static boolean isSameTile(Point p1, Point p2) {
			if(p1.tile == p2.tile)
				return true;
			return false;
		}
		
	}
	

}
