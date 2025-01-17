import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

public class 홈방범서비스 {
	static int[][] map;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static boolean[][] visit;
	static Queue<search> que;
	static int n,m,cost,home,anw;
	static int[] levelsum;
	
	// 방문하지 않았는지, 배열안에 있는지 확인
	static boolean isRange(int ny, int nx) {
		return ny>=0 && ny<n && nx>=0 && nx<n && !visit[ny][nx]; 
	}
	
	// 계산
	static void cal() {
		while(!que.isEmpty()) {
			search now = que.poll();
			// 레벨이 n을 넘어가면 맵에서 짤리는 부분이 생겨 운영비용보다 클 수 없다고 판단
			if(now.level==n+1) {
				// 큐에 들어있는 나머지 위치들은 clear
				que.clear();
				// 탐색 중단
				break;
			}
			// 집을 발견하면 해당 레벨의 배열에 +1
			if(map[now.y][now.x]==1) {
				levelsum[now.level]++;
			}
			
			// 사방 탐색
			for (int i = 0; i < 4; i++) {
				int ny = now.y+dy[i];
				int nx = now.x+dx[i];  
				
				// 미방문이고, 배열내이면 방문처리하고 다음레벨로 큐에삽입
				if(isRange(ny, nx)) {
					que.add(new search(ny,nx,now.level+1));
					visit[ny][nx] = true;
				}
			}
		}
		// 누적합을 저장하기위한 변수
		home = 0;
		// 누적합을 통해 단계별로 집의 개수를 파악
		for(int i=0;i<n+1;i++) {
			home += levelsum[i];
			// 운영비용 계산
			cost = (i+1)*(i+1)+i*i;
			// 이득(본전이상)이고, 현재 집의 최대값보다 더 크면 갱신
			if(home*m>=cost) {
				if(anw<home) {
					anw = home;
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int tcase = parseInt(br.readLine());
        
        for (int i = 1; i <= tcase; i++) {
        	st = new StringTokenizer(br.readLine());
        	n = parseInt(st.nextToken());
        	m = parseInt(st.nextToken());
        	
        	map = new int[n][n];
        	que = new LinkedList<search>();
        	
        	for (int j = 0; j < n; j++) {
        		st = new StringTokenizer(br.readLine());
				for (int k = 0; k < n; k++) {
					map[j][k] = parseInt(st.nextToken());
				}
			}
        	
        	// 최대값을 저장할 변수
        	anw = 0;
        	for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					// 레벨 별 집의 개수를 파악하기위한 배열
					levelsum = new int[n+1];
					// 방문처리를 위한 배열
					visit = new boolean[n][n];
					// 시작점으로 추가(0레벨)
					que.add(new search(j,k,0));
					// 시작점 방문처리
					visit[j][k] = true;
					// 계산
					cal();
				}
			}
        	
        	System.out.println("#"+i+" "+anw);
        	
		}
        
	}

}

class search{
	int y,x,level;

	public search(int y, int x, int level) {
		super();
		this.y = y;
		this.x = x;
		this.level = level;
	}
	
}
