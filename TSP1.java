import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	
	static int nCity;
	static double [][] cityMetrix;
	static int [] visit;
	static double shortestPaht;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		int T = sc.nextInt();
		while(T-- > 0) {
			nCity = sc.nextInt();
			cityMetrix = new double[nCity][nCity];
			visit = new int[nCity];
			
			for(int i=0; i<nCity; i++) 
				for(int j=0; j<nCity; j++) 
					cityMetrix[i][j] = sc.nextDouble();
			
			shortestPaht = Double.MAX_VALUE;
			
			// 각 시작 도시 
			for(int i=0; i<nCity; i++)
				findShortestPath(i, 0, 1);
			System.out.println(shortestPaht);
		}
	}
	
	static void findShortestPath(int v, double length, int depth) {
		// 모든 도시를 방문한 경우 
		if(depth == nCity) {
			// 현재까지 찾은 최단 경로와 현재 경로 비교 
			if(shortestPaht > length)
				shortestPaht = length;
			visit[v] = 0;
			return;
		}
		
		visit[v] = 1;
		
		for(int i=0; i<nCity; i++)
			// 방문가능한 도시에 대해서만 방문 
			if(cityMetrix[v][i] != 0.0f && visit[i] == 0) 
				findShortestPath(i, length + cityMetrix[v][i], depth+1);
		
		visit[v] = 0;
	}

}
