import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(bf.readLine());
		String[][] arr = new String[n][n];
		
		for(int i=0;i<n;i++) {
			String str = bf.readLine();
			for(int j=0;j<n;j++) {
				arr[i] = str.split("");
			}
		}
		int max = 1;
		
		// 각 좌표 기준으로 탐색
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				
				// 바꾸기 전 탐색
				max = Math.max(max, calmax(arr,i,j,n));
//				System.out.println("i: "+i+" j: "+j);
//				System.out.println("바꾸기 전: "+calmax(arr,i,j,n));
				
				// 우로 교체
				if(j+1<n) {
					String temp = "";
					temp = arr[i][j];
					arr[i][j] = arr[i][j+1];
					arr[i][j+1] = temp;
					// 탐색
//					System.out.println("우로 교체: "+calmax(arr,i,j,n));
					max = Math.max(max, calmax(arr,i,j,n));
					// 원복
					temp = arr[i][j];
					arr[i][j] = arr[i][j+1];
					arr[i][j+1] = temp;
				}
				
				// 하로 교체
				if(i+1<n) {
					String temp = "";
					temp = arr[i][j];
					arr[i][j] = arr[i+1][j];
					arr[i+1][j] = temp;
					// 탐색
//					System.out.println("하로 교체: "+calmax(arr,i,j,n));
					max = Math.max(max, calmax(arr,i,j,n));
					// 원복
					temp = arr[i][j];
					arr[i][j] = arr[i+1][j];
					arr[i+1][j] = temp;
				}
				
			}// j
		}// i
		System.out.println(max);
	}
	
	public static int calmax(String[][] arr,int a, int b, int n) {
		int max = 1;
		int cnt = 1;
        
        // 가로 줄 탐색
		cnt = 1;
		for(int k=0;k<n-1;k++) {
			if(arr[a][k].equals(arr[a][k+1])) {
				cnt++;
			}else {
				cnt = 1;
			}
			if(cnt>max) {
				max=cnt;
			}
		}
        // 아래로 바뀐 줄 가로도 탐색
		if(a+1<n) {
			cnt = 1;
			for(int k=0;k<n-1;k++) {
				if(arr[a+1][k].equals(arr[a+1][k+1])) {
					cnt++;
				}else {
					cnt = 1;
				}
				if(cnt>max) {
					max=cnt;
				}
			}
		}
		// 세로 줄 탐색
		cnt = 1;
		for(int k=0;k<n-1;k++) {
			if(arr[k][b].equals(arr[k+1][b])) {
				cnt++;
			}else {
				cnt = 1;
			}
			if(cnt>max) {
				max=cnt;
			}
		}
        // 가로로 바뀐 줄 세로도 탐색
		if(b+1<n) {
			cnt = 1;
			for(int k=0;k<n-1;k++) {
				if(arr[k][b+1].equals(arr[k+1][b+1])) {
					cnt++;
				}else {
					cnt = 1;
				}
				if(cnt>max) {
					max=cnt;
				}
			}
		}
		
		return max;
	}

}
