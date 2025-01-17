
import java.math.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		String[][] arr = new String[n][m];
		
		for(int i=0;i<n;i++) {
			String str = sc.next();
			for(int j=0;j<m;j++) {
				arr[i][j] = Character.toString(str.charAt(j));
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				//(n,m)좌표일 때 계산
				
				// 최소 i보다 한칸은 더 가야함
				for(int a=i+1;a<n;a++) {
					// i와a사이의 거리만큼 j에서 이동했을 때 m을 벗어나면 안됨
					if((j+a-i)<m) {
						// (i,j) 와 (a,j)가 같을 때  => 세로
						if(arr[i][j].equals(arr[a][j])) {
							// 그 사이의 거리만큼 j에서 이동  => 가로
							if(arr[i][j].equals(arr[i][j+(a-i)])) {
								// 그것도 같으면 대각의 좌표도 확인
								if(arr[i][j].equals(arr[a][j+(a-i)])) {
									// 같으면 그 사이의 거리보다 큰 경우가
									// 있는지 확인
									if((a-i+1)>max) {
										//없으면 max에 저장
										max = a-i+1;
									}
								}
							}
						}
					}
				}
				
			}
		}
		// max에 저장된 값이 없으면 자기 자신만 가능하기에 1밖에 없음
		if(max == Integer.MIN_VALUE) {
			System.out.println(1);
		}else {
			//아니면 사이의 거리의 제곱으로 사이즈를 구함
			System.out.println(max*max);
		}
	}


}
