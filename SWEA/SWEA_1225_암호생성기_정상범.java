import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
public class 암호생성기 {
	public static ArrayList<Integer> list;
	
	public static void cal() {
		// list의 마지막이 0이 아니면 반복
		while(list.get(7)!=0) {
			// 1부터5까지 빼는게 1 사이클
			for(int i=1;i<=5;i++) {
				// 0보다 작으면 0으로 처리해서 마지막으로 추가
				if(list.get(0)-i<0) {
					list.add(0);
				}else {
					// 아니면 뺄셈해서 마지막으로 추가
					list.add(list.get(0)-i);
				}
				// 계산 후 첫 번째 list 삭제
				list.remove(0);
				// 마지막이 0이면 메서드 종료
				if(list.get(7)==0) {
					return;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=1;i<=10;i++) {
			// 입력받는 과정
			int num = parseInt(br.readLine());
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<8;j++) {
				list.add(parseInt(st.nextToken()));
			}
			// 계산 메서드
			cal();
			// 출력
			System.out.print("#"+i);
			for(int j=0;j<8;j++) {
				System.out.print(" "+list.get(j));
			}
			System.out.println();
		}

	}

}
