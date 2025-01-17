import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

import java.awt.Point;
public class 등산로조성 {
	static int n,k,max;
	static int[][] map;
	static int[][] ori;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static boolean[][] visit;
	
	// 배열내에 위치하는지 확인
	static boolean isRange(int ny, int nx) {
		return ny>=0 && ny<n && nx>=0 && nx<n;
	}
	// 갈 곳의 위치가 현재의 위치보다 낮은지 확인
	static boolean compare(int ny, int nx, int y, int x) {
		return map[ny][nx]<map[y][x];
	}
	
	// 계산
	static void cal(int depth,road now) {
		// 제일 많이 이동했을 때 max값 갱신
		if(max<now.level) {
			max = now.level;
		}
		// 사방 탐색
		for(int i=0;i<4;i++) {
			int ny = now.y+dy[i];
			int nx = now.x+dx[i];
			
			// 배열내에 위치하고, 방문하지 않았으며, 현재보다 값이 작으면 이동가능
			if(isRange(ny, nx) && !visit[ny][nx] && compare(ny,nx,now.y,now.x)) {
				// 방문처리
				visit[ny][nx] = true;
				// 다음 레벨로 이동
				// => now.status => 산을 깎은 적이 있는지 체크하기위해 넣은 값
				cal(depth+1,new road(ny,nx,now.level+1,now.status));
				// 같은 레벨의 다음 노드를 위해 미방문처리
				visit[ny][nx] = false;
				
			// 배열내에 위치하고, 방문하지 않았는데, 현재보다 값이커서 이동이 불가, 산을 깎은적이 없을 때(true=>가능하다는 뜻)
			}else if(isRange(ny, nx) && !visit[ny][nx] && !compare(ny,nx,now.y,now.x) && now.status) {
				// 최대 k까지 깎을 수 있기 때문에 1~k까지 다르게 깎고 다음 레벨로 보내기
				for(int j=1;j<=k;j++) {
					// 깎기
					map[ny][nx] -= j;
					// 깎았을 때 현재보다 작아지면 다음레벨로 보내기
					if(compare(ny,nx,now.y,now.x)) {
						// 방문처리
						visit[ny][nx] = true;
						// 깎았기 때문에 status를 false로 변경 => 앞으로 깎을 수 없음
						cal(depth+1,new road(ny,nx,now.level+1,false));
						// 다른 깊이로 깎기위해 원복과 미방문처리
						map[ny][nx] += j;
						visit[ny][nx] = false;
					}else {
						// 깎았는데도 불구하고 현재위치가 더 작으면 그냥 원상복구하고 다음 깊이로 
						map[ny][nx] += j;
						continue;
					}
					
				}
				
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int tcase = parseInt(br.readLine());
        
        for (int i = 1; i <= tcase; i++) {
			st = new StringTokenizer(br.readLine());
        	n = parseInt(st.nextToken());
        	k = parseInt(st.nextToken());
        	
        	map = new int[n][n];
        	ori = new int[n][n];
        	
        	max = 0;
        	// map을 입력하면서 원래 상태 보존을 위해 ori라는 배열로 똑같이 만들기
        	for (int a = 0; a < n; a++) {
        		st = new StringTokenizer(br.readLine());
				for (int b = 0; b < n; b++) {
					map[a][b] = parseInt(st.nextToken());
					ori[a][b] = map[a][b];
					// 봉우리 높이 찾기
					if(map[a][b]>max) {
						max = map[a][b];
					}
				}
			}
        	
        	max = 0;
        	for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					// 봉우리 마다 탐색하기
					if(map[a][b]==max) {
						// 시작하기 전에 그전 탐색으로 깎인 산을 원상복구
						// 2차원배열 깊은복사
						for (int c = 0; c < n; c++) {
							for (int d = 0; d < n; d++) {
								map[c][d] = ori[c][d];
							}
						}
						// 방문처리도 초기화
						visit = new boolean[n][n];
						// 현재위치 방문처리
						visit[a][b] = true;
						// 현재위치부터 탐색 시작(산 깎을 수 있음)
						cal(0, new road(a,b,1,true));
					}
					
				}
			}
        	
        	System.out.println("#"+i+" "+max);
        	
		}

	}

}
// 현재 위치에 따라 상태를 보기위한 클래스
class road{
	int y,x,level;
	// 산을 깎은적 있는지 판단할 변수
	boolean status;
	
	public road(int y, int x, int level, boolean status) {
		super();
		this.y = y;
		this.x = x;
		this.level = level;
		this.status = status;
	}
}