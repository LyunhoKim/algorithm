import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		int T = sc.nextInt();
		
		while(T-- > 0) {
			
			int n = sc.nextInt();
			
			List<LunchBox> boxes = new ArrayList<LunchBox>();
			
			for(int i=0; i<n; i++) 
				boxes.add(new LunchBox(sc.nextInt()));
			for(int i=0; i<n; i++) 
				boxes.get(i).eatTime = sc.nextInt();
			
			boxes.sort(new Comp());
			
			int heatingTime = 0;
			int totalTime = 0;
			for(int i=0; i<n; i++) {
				totalTime = Math.max(boxes.get(i).heatTime + boxes.get(i).eatTime + heatingTime, totalTime);
				heatingTime += boxes.get(i).heatTime;
			}
			System.out.println(totalTime);
		}

	}
	
	static public class LunchBox {
		public int heatTime;
		public int eatTime;
		
		public LunchBox(int h) {
			heatTime = h;
		}
	}
	
	static class Comp implements Comparator<LunchBox> {

		@Override
		public int compare(LunchBox o1, LunchBox o2) {
			
			return o2.eatTime - o1.eatTime;
		}
		
	}

}
