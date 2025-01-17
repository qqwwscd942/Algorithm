import java.util.*;
import java.math.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String num1 = sc.next();
		String num2 = sc.next();
		String num3 = sc.next();
		String num4 = sc.next();
		
		int low = lownum(num1,num2,num3,num4);
		
		int count = countMin(low);
		System.out.println(count);
		
	}
	
	public static int lownum(String num1,String num2,String num3,String num4) {
		int min = Integer.MAX_VALUE;
		String[] arr = {num1,num2,num3,num4};
        // 회전의 시작지점
		int startcnt=0;
        // 시작지점부터 0,1,2,3 회전
		int cnt=0;
		String number = "";
		
		for(int i=0;i<4;i++) {
			cnt = startcnt;
			number = "";
			for(int j=0;j<4;j++) {
                // 0,1,2,3 => 1,2,3,0 => 2,3,0,1 => 3다음 다시 0으로 가기위함
				if(cnt==4) {
					cnt=0;
				}
                // 문자배열을 숫자로 만들기위해 붙이기
                // 1+2+3+4 => 1234
				number += arr[cnt];
				cnt++;
			}
            // 4번 회전한 숫자중 가장 작은 수 저장
			if(Integer.parseInt(number)<min) {
				min = Integer.parseInt(number);
			}
            // 시작위치 1증가
			startcnt++;
		}
		
		return min;
	}
	
	public static int countMin(int min){
			
		int cnt = 0;
        // 1111부터 시계수까지 확인
		for(int i=1111;i<min;i++) {
			String num = Integer.toString(i);
			String[] arr = num.split("");
			
			int minVal = Integer.MAX_VALUE;
			int startcnt=0;
			int cnt2=0;
			String number = "";
			// 같은방식으로 1111부터 min까지 시계수인지 체크하면서 증가
			for(int j=0;j<4;j++) {
				cnt2 = startcnt;
				number = "";
				for(int k=0;k<4;k++) {
					if(cnt2==4) {
						cnt2=0;
					}
					number += arr[cnt2];
					cnt2++;
				}
				if(Integer.parseInt(number)<minVal) {
					minVal = Integer.parseInt(number);
				}
				startcnt++;
			}
            // 본인과 회전 시 가장 작은 수가 같으면 시계수
			if(minVal==i) {
                // 시계수이면 카운트
				cnt++;
			}
		}
        //자기 자신까지 세야해서 +1
		return cnt+1;
	}

}
