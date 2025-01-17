import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
public class 괄호짝짓기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=1;i<=10;i++) {
			int num = parseInt(br.readLine());
			
			String str = br.readLine();
			// 각 괄호의 추가와 삭제를 저장할 스택 생성
			Stack<Character> s = new Stack<>();
			Stack<Character> m = new Stack<>();
			Stack<Character> l = new Stack<>();
			Stack<Character> q = new Stack<>();
			
            // 스택이 없는데 닫히는 괄호로 인해 pop되는 상황을 처리하기위한 변수
			int exception = 0;
			
            // 각 괄호에 맞게 add, pop
			for(int k=0;k<num;k++) {
				char c = str.charAt(k);
				if(c=='(') {
					s.add('(');
				}else if(c==')'&&!s.isEmpty()) {
					s.pop();
				}else if(c=='{') {
					m.add('{');
				}else if(c=='}'&&!m.isEmpty()) {
					m.pop();
				}else if(c=='[') {
					l.add('[');
				}else if(c==']'&&!l.isEmpty()) {
					l.pop();
				}else if(c=='<') {
					q.add('<');
				}else if(c=='>'&&!q.isEmpty()) {
					q.pop();
				}else {
                    // 열리는 괄호는 없는데 닫히는 괄호로인해 pop될 때 발생하는 Exception 처리
					exception++;
				}
			}
			// Exception도 없고 각 스택도 다 비어있을 땐 유효함
			if(s.isEmpty()&&m.isEmpty()&&l.isEmpty()&&q.isEmpty()&&exception==0) {
				System.out.println("#"+i+" 1");
			}else {
				System.out.println("#"+i+" 0");
			}
			
		}
	}

}
