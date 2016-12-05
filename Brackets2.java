import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Main{

     public static void main(String []args) throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
        int T = Integer.parseInt(sc.nextLine());
        
        
        while(T-- > 0) {
            try {
	            if(isValidBrackets(sc.nextLine()))
	                System.out.println("YES");
	            else 
	                System.out.println("NO");
            } catch (EmptyStackException e) {
            	System.out.println("NO");
            }
            
        }
        
        
     }
     
     private static boolean isValidBrackets(String b) throws EmptyStackException{
         char [] brackets = b.toCharArray();
         Stack stack = new Stack();
            
            for(int i=0; i< brackets.length; i++) {
                switch(brackets[i]) {
                    case '{':
                    case '(':
                    case '[':
                        stack.push(brackets[i]);
                        break;
                    case ')':
                        if((char)stack.pop() != '(') return false;
                        break;
                    case '}':
                        if((char)stack.pop() != '{') return false;
                        break;
                    case ']':
                        if((char)stack.pop() != '[') return false;
                        break;
                }
            }
            
            
            if(stack.isEmpty())
            	return true;
            else 
            	return false;
            
     }
}