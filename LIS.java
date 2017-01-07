import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class Main {
	
	static int [] cache;
	static ArrayList<Integer> seq;

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		int T = sc.nextInt();
		
		while(T-- > 0) {
			int N = sc.nextInt();
			seq = new ArrayList<Integer>();
			cache = new int [N+1];
			Arrays.fill(cache, -1);
			while(N-- > 0) 
				seq.add(sc.nextInt());

			//lis
//			System.out.println(lis(seq));
			
			//lis2
//			int maxLen = 0;
//			for(int begin=0; begin<seq.size(); ++begin)
//				maxLen = Math.max(maxLen, lis2(begin));
//			System.out.println(maxLen);
			
			//lis3
			System.out.println(lis3(-1)-1);
			
		}

	}
	
	private static int lis(final ArrayList<Integer> seq) {
		
		if(seq.isEmpty()) return 0;
		int ret = 0;
		for(int i=0; i<seq.size(); i++) {
			ArrayList<Integer> subSeq = new ArrayList<Integer>();
			
			for(int j=i+1; j<seq.size(); j++) {
				if(seq.get(i) < seq.get(j))
					subSeq.add(seq.get(j));
			}
			
			ret = Math.max(ret, 1+lis(subSeq));
		}
		return ret;
	}
	
	private static int lis2(int start) {
		if(cache[start] != -1) return cache[start];
		
		cache[start] = 1;
		for(int next = start+1; next<seq.size(); ++next)
			if(seq.get(start) < seq.get(next))
				cache[start] = Math.max(cache[start], lis2(next) + 1);
		return cache[start];
	}
	
	private static int lis3(int start) {
		if(cache[start+1] != -1) return cache[start+1];
		cache[start+1] = 1;
		
		for(int next = start+1; next<seq.size(); ++next)
			if(start == -1 || seq.get(start) < seq.get(next))
				cache[start+1] = Math.max(cache[start+1], lis3(next) + 1);
		return cache[start+1];
	}

}
