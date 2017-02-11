import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		
		int T = sc.nextInt();
		
		while(T-- > 0) {
			int N = sc.nextInt();
			List<Integer> pre = new ArrayList<Integer>();
			List<Integer> in = new ArrayList<Integer>();
			
			for(int i=0; i<N; i++)
				pre.add(sc.nextInt());
			for(int i=0; i<N; i++)
				in.add(sc.nextInt());
			
			
			printPostOrder(pre, in);
		}

	}
	
	
	public static List<Integer> slice(List<Integer> arr, int a, int b) {
		return arr.subList(a, b);
	}
	
	public static void printPostOrder(List<Integer> preorder, List<Integer> inorder) {
		
		final int N = preorder.size();
		
		if(preorder.isEmpty()) return;
		
		final int root = preorder.get(0);
		
		final int L = inorder.indexOf(root);
		final int R = N - 1 - L;
		
		printPostOrder(slice(preorder, 1, L+1), slice(inorder, 0, L));
		printPostOrder(slice(preorder, L+1, N), slice(inorder, L+1, N));
		
		System.out.print(root + " ");
		
	}

}
