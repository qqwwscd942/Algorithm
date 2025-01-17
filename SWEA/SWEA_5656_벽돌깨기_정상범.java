import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
public class 벽돌깨기 {
	static int n,w,h,remain,min;
	static int[][] arr;
	static int[][] ori;
	static int[] order;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static Queue<kan> que;
	static boolean[][] visit;
	
    // 해당 칸의 값만큼 사방으로 벽돌 깨기
	static void boom(int x) {
		visit = new boolean[h][w];
		
        // 가장 위에 있는 칸을 큐에 삽입
		for(int i=0;i<h;i++) {
			if(arr[i][x]!=0) {
				que.add(new kan(i,x));
				break;
			}
		}
		
		while(!que.isEmpty()) {
			
			kan now = que.poll();
			// 칸의 값을 확인해서 몇 칸을 부술지 체크
			int power = arr[now.y][now.x]-1;
			arr[now.y][now.x] = 0;
			
            // 사방탐색
			for(int i=0;i<4;i++) {
				int ny = now.y;
				int nx = now.x;
				// 한 방향을 해당 칸의 값-1 만큼 확인
				for(int j=0;j<power;j++) {
					ny += dy[i];
					nx += dx[i];
                    // 배열 내에 존재하고
					if(ny>=0&&ny<h&&nx>=0&&nx<w) {
                        // 0이 아니며, 방문하지 않았을 때 큐에 추가
						if(arr[ny][nx]!=0&&!visit[ny][nx]) {
							que.add(new kan(ny,nx));
							visit[ny][nx] = true;
						}
					}
				}
			}
		}
		
	}
	
    // 중복 순열로 벽돌깨기 순서를 구함
	static void cal(int depth) {
		if(depth==n) {
            // 배열 초기화
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					arr[i][j] = ori[i][j];
				}
			}
            // 순서에따라 부수고, 재배치(아래로 내려오게)하기
			for(int i=0;i<n;i++) {
				boom(order[i]);
				relocation();
			}
			// 몇 칸 남았는지 계산
			remain = count();
			// 최소값 저장
			if(min>remain) {
				min = remain;
			}
			return;
		}
		// 중복순열로 순서 저장
		for(int i=0;i<w;i++) {
			order[depth] = i;
			cal(depth+1);
		}
	}
	// 재배치
	static void relocation() {
        // 아래부터 0이 아닌 숫자들을 문자열로 담아서
        // 아래부터 다시 배치해준다
        // ex) 10111001 => 문자열 : 11111 => 11111000 으로 배치
		for(int i=0;i<w;i++) {
            // 문자열 초기화
			String str = "";
            // 아래부터 0이 아닌지 확인하면서 문자열에 추가
			for(int j=h-1;j>=0;j--) {
				if(arr[j][i]!=0) {
					str += Integer.toString(arr[j][i]);
				}
			}
            // 배치하기전에 먼저 0으로 전부 초기화
			for(int j=0;j<h;j++) {
				arr[j][i] = 0;
			}
            // 다시 아래부터 채워주기
			for(int j=0;j<str.length();j++) {
				arr[(h-1)-j][i] = str.charAt(j)-'0';
			}
		}
	}
	// 남은 벽돌 카운트
	static int count() {
		int cnt = 0;
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(arr[i][j]!=0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tcase = parseInt(br.readLine());
		
		for(int i=1;i<=tcase;i++) {
			st = new StringTokenizer(br.readLine());
			n = parseInt(st.nextToken());
			w = parseInt(st.nextToken());
			h = parseInt(st.nextToken());
			
			arr = new int[h][w];
			ori = new int[h][w];
			que = new LinkedList<>();
			order = new int[n];
			
			for(int j=0;j<h;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<w;k++) {
					arr[j][k] = parseInt(st.nextToken());
					ori[j][k] = arr[j][k];
				}
			}
			
			min = Integer.MAX_VALUE;
			
			cal(0);
			
			System.out.println("#"+i+" "+min);
			
		}
		
	}
	static class kan{
		int y,x;

		public kan(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
