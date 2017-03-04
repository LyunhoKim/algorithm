import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	
	static HashMap<ArrayList<Integer>, Integer> toSort = new HashMap<ArrayList<Integer>, Integer>();

	public static void main(String[] args) throws FileNotFoundException {
		
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
 
		int T = sc.nextInt();
 
		while(T-- > 0) {
			int N = sc.nextInt();
			ArrayList<Integer> perm = new ArrayList<Integer>();
			
			for(int i=0; i<N; i++)
				perm.add(sc.nextInt());
			
			precalc(perm.size());
			System.out.println(solve(perm));
		
		}

		

	}
	
	public static void precalc(int n) {
		ArrayList<Integer> perm = new ArrayList<Integer>();
		for(int i=0; i<n; i++)
			perm.add(i);
		
		Queue<ArrayList<Integer>> q = new LinkedList<ArrayList<Integer>>();
		q.add(perm);
		toSort.put(perm, 0);
		while(!q.isEmpty()) {
			ArrayList<Integer> here = new ArrayList<Integer>();
			here = q.poll();
			int cost = toSort.get(here);
			for(int i=0; i<n; i++) {
				for(int j=i+2; j<=n; j++) {
					here = reverse(here, i, j);
					if(!toSort.containsKey(here)) {
						toSort.replace(here, cost + 1);
						q.add(here);
					}
					here = reverse(here, i, j); 
				}
			}
		}
	}
	
	public static int solve(ArrayList<Integer> perm) {
		int n = perm.size();
		ArrayList<Integer> fixed = new ArrayList<Integer>();
		for(int i=0; i<n; i++) {
			int smaller = 0;
			for(int j=0; j<n; j++)
				if(perm.get(j) < perm.get(i))
					smaller++;
			fixed.add(smaller);
		}
		return toSort.get(fixed);
		
	}
	
	public static ArrayList<Integer> reverse(ArrayList<Integer> a, int begin, int end) {
		while(begin < end) {
			int t1 = a.get(begin);
			a.set(begin, a.get(end));
			a.set(end, t1);
			
			begin++;
			end--;
		}
		return a;
		
	}

}
