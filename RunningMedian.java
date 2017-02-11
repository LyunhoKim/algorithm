import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		while(T-- > 0) 
			System.out.println(runningMedian(sc.nextInt(), new RNG(sc.nextInt(), sc.nextInt())));
	}
	
	public static int runningMedian(int n, RNG rng) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		
		int ret = 0;
		
		for(int i=0; i<n; i++) {
			if(maxHeap.size() == minHeap.size())
				maxHeap.add(rng.next());
			else
				minHeap.add(rng.next());
			
			if(!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
				int a = minHeap.poll();
				int b = maxHeap.poll();
				
				maxHeap.add(a);
				minHeap.add(b);
			}
			
			ret = (ret + maxHeap.peek()) % 20090711;
		}
		
		
		return ret;
	}
	
	
	public static class RNG {
		int a, b;
		long seed;
		public RNG(int _a, int _b) {
			a = _a;
			b = _b;
			seed = 1983;
		}
		public int next() {
			int ret = (int)seed;
			seed = ((seed * a) + b) % 20090711;
			return ret;
		}
		
	}

}
