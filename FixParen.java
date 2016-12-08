import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


public class Main {
	
	static HashMap<Character, Character> pairs = new HashMap<Character, Character>();

	public static void main(String[] args) throws FileNotFoundException {
		
		pairs.put('(', ')');
		pairs.put(')', '(');
		pairs.put('{', '}');
		pairs.put('}', '{');
		pairs.put('<', '>');
		pairs.put('>', '<');
		pairs.put('[', ']');
		pairs.put(']', '[');
		
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		int T = sc.nextInt();
		
		while(T-- > 0) {
			
			Stack<Parenthes> stack = new Stack<Parenthes>();
			
			char [] parentheses = sc.next().toCharArray();
			char [] result = new char [parentheses.length];
			String priority = sc.next();
			
			// 여는 괄호의 경우 스택에 쌓음 
			for(int i=0; i<parentheses.length; i++) {
				switch(parentheses[i]) {
				case '{':
				case '[':
				case '(':
				case '<':
					stack.push(new Parenthes(parentheses[i], i));
					break;
					
				// 닫는 괄호의 경우 
				default:
					if(stack.peek().p == pairs.get(parentheses[i])) {
						result[stack.peek().index] = stack.pop().p;
						result[i] = parentheses[i];
					} else {
						Parenthes t = stack.pop();
						result[i] = priority.indexOf(t.p) < priority.indexOf(pairs.get(parentheses[i])) ? pairs.get(t.p) : parentheses[i];
						result[t.index] = priority.indexOf(t.p) < priority.indexOf(pairs.get(parentheses[i])) ? t.p : pairs.get(parentheses[i]);
					}
				}
			}
			for(int i=0; i<result.length; i++)
				System.out.print(result[i]);
			System.out.println();
		}
	}
	
	public static class Parenthes {
		
		char p;
		int index;
		
		public Parenthes (char p, int index) {
			this.p = p;
			this.index = index;
		}
		
	}

}
