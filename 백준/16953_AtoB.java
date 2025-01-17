import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String in = bf.readLine();
		StringTokenizer st = new StringTokenizer(in);
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int cnt = 1;
	
		while(true) {
            // a와 b가 다르면 cal함수 돌리기
			if(a!=b) {
				b = cal(b);
                돌아간 횟수 카운트
				cnt++;
			}else {
                // 같아지면 카운트 출력하고 종료
				System.out.println(cnt);
				break;
			}
			if(b<a) {
                // 같아지지 못하고 cal함수로 인해 a보다 작아지면 -1출력
				System.out.println(-1);
				break;
			}
		}
	}
	
	public static int cal(int n) {
        // 짝수는 나누는 방법밖에 없음
		if(n%2==0) {
			return n/2;
        // 나머지가 1이면 1을 제거하는 방버밖에 없음 ex)21 -> 2
		}else if(n%10==1){
			return (n-1)/10;
        // 나머지 홀수들은 1을 붙이거나 2를 곱하는 연산으로 만들 수 없음
		}else {
        // a보다 작아지게 -1로 반환
			return -1;
		}
		
	}
	
}