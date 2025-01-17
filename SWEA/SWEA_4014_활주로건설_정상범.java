import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

public class 활주로건설 {
	static Queue<Integer> downQue;
	static Queue<Integer> upQue;
	static int n,x,canBuild;
	static int[][] arr;
	static int[] dy = {-1,1};
	static int[] dx = {-1,1};
	static boolean[][] visit;

	// 배열 내인지 확인
	static boolean isRange(int i) {
		return i>=0&&i<n;
	}
	
    // y축 체크
	static void ySearch(int j) {
		visit = new boolean[n][n];
		downQue = new LinkedList<>();
		upQue = new LinkedList<>();
		
        // 각 자리를 기준으로 양 옆이 더 작은경우만 찾아서 queue에 삽입
		for(int i=0;i<n;i++) {
			if((i-1)>=0) {
                // 이전 인덱스가 현재 자리보다 작을 때
				if(arr[i][j]>arr[i-1][j]) {
					downQue.add(i);
				}
			}
			if((i+1)<n) {
                // 다음 인덱스가 현재 자리보다 작을 때
				if(arr[i][j]>arr[i+1][j]) {
					upQue.add(i);
				}
			}
		}
		
        // 이전 인덱스가 값이 더 작을 때(현재보다 작은 인덱스만 탐색)
		while(!downQue.isEmpty()) {
			int now = downQue.poll();
			
            // 경사로의 길이만큼 확보 되어야 함
			for(int i=1;i<=x;i++) {
                // 경사로의 길이만큼 확보되지 않으면 불가능 => return
				if(isRange(now-i)) {
                    // 값 차이는 1이어야하고, 다른 경사로와 겹치면 안됨
					if(arr[now][j]-arr[now-i][j]==1&&!visit[now-i][j]) {
                        // 겹치지 않았으면 경사로 건설
						visit[now-i][j] = true;
					}else {
						return;
					}
				}else {
					return;
				}
			}
		}
		
        // 다음 인덱스가 값이 더 작을 때(현재보다 큰 인덱스만 탐색)
		while(!upQue.isEmpty()) {
			int now = upQue.poll();
			
            // 경사로의 길이만큼 확보 되어야 함
			for(int i=1;i<=x;i++) {
                // 경사로의 길이만큼 확보되지 않으면 불가능 => return
				if(isRange(now+i)){
                    // 값 차이는 1이어야하고, 다른 경사로와 겹치면 안됨
					if(arr[now][j]-arr[now+i][j]==1&&!visit[now+i][j]) {
                        // 겹치지 않았으면 경사로 건설
						visit[now+i][j] = true;
					}else {
						return;
					}
				}else {
					return;
				}
			}
		}
        // return이 안됬으면 모두 건설완료
		canBuild++;
	}
	
    // x축 체크
    // y축과 같은 원리로 해당 인덱스기준 앞, 뒤를 체크
	static void xSearch(int j) {
		visit = new boolean[n][n];
		downQue = new LinkedList<>();
		upQue = new LinkedList<>();
		
		for(int i=0;i<n;i++) {
			if((i-1)>=0) {
				if(arr[j][i]>arr[j][i-1]) {
					downQue.add(i);
				}
			}
			if((i+1)<n) {
				if(arr[j][i]>arr[j][i+1]) {
					upQue.add(i);
				}
			}
		}
		
		while(!downQue.isEmpty()) {
			int now = downQue.poll();

			for(int i=1;i<=x;i++) {
				if(isRange(now-i)) {
					if(arr[j][now]-arr[j][now-i]==1&&!visit[j][now-i]) {
						visit[j][now-i] = true;
					}else {
						return;
					}
				}else {
					return;
				}
			}
		}
		
		while(!upQue.isEmpty()) {
			int now = upQue.poll();
			
			for(int i=1;i<=x;i++) {
				if(isRange(now+i)) {
					if(arr[j][now]-arr[j][now+i]==1&&!visit[j][now+i]) {
						visit[j][now+i] = true;
					}else {
						return;
					}
				}else {
					return;
				}
			}
		}
        // return이 안됬으면 모두 건설완료
		canBuild++;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tcase = parseInt(br.readLine());
		
		for(int i=1;i<=tcase;i++) {
			
			st = new StringTokenizer(br.readLine());
			
			n = parseInt(st.nextToken());
			x = parseInt(st.nextToken());
			canBuild = 0;
			
			arr = new int[n][n];
			
			for(int j=0;j<n;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<n;k++) {
					arr[j][k] = parseInt(st.nextToken());
				}
			}
			
			for(int j=0;j<n;j++) {
				ySearch(j);
				xSearch(j);
			}
			System.out.println("#"+i+" "+canBuild);
		}
		
	}

}
