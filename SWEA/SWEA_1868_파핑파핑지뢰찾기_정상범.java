import java.util.*;
import java.io.*;                                                                                                  
import static java.lang.Integer.*;
import java.awt.Point;

public class 파핑파핑지뢰찾기 {
	static char[][] arr;
	static int[][] anw;
	static int[] dy = {-1,-1,-1,0,0,1,1,1};
	static int[] dx = {-1,0,1,-1,1,-1,0,1};
	static boolean[][] visit;
	static Queue<Point> que;
	static int n,zero;
	// 배열을 벗어나지않고 미방문인지 확인을 위한 메서드
	static boolean isRange(int ny, int nx) {
		if(ny>=0&&ny<n&&nx>=0&&nx<n&&!visit[ny][nx]) {
			return true;
		}
		return false;
	}
	// 지뢰가 아닌 자리를 주변 지뢰의 수로 치환
	static void write() {
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			int cnt = 0;
			// 8방 탐색에서 지뢰의 수 카운트
			for(int i=0;i<8;i++) {
				int ny = now.y+dy[i];
				int nx = now.x+dx[i];
				
				if(isRange(ny,nx)&&arr[ny][nx]=='*') {
					cnt++;
				}
			}
            // 현재 자리를 지뢰의 수로 치환
			anw[now.y][now.x] = cnt;
		}
		
	}
	// 주변 지뢰의 수가 0인 곳은 한번에 열리기 때문에 한번에 열리는 0의 구역을 확인
    // 확인한 구역은 -1로 치환해서 다른구역(지뢰의 수가 0인 다른집단)과 구분
	static void cal() {
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			for(int i=0;i<8;i++) {
				int ny = now.y+dy[i];
				int nx = now.x+dx[i];
				
				if(isRange(ny,nx)) {
                    // 0인 구역을 계속 찾아서 -1로 치환하고 방문처리
					if(anw[ny][nx]==0) {
						que.add(new Point(nx,ny));
						anw[ny][nx] = -1;
						visit[ny][nx] = true;
                    // 0의 8방향 안에 있어도 같이 열리기 때문에 방문처리
					}else {
						visit[ny][nx] = true;
					}
					
				}
				
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tcase = parseInt(br.readLine());
		
		for(int i=1;i<=tcase;i++) {
			n = parseInt(br.readLine());
			arr = new char[n][n];
			anw = new int[n][n];
			que = new LinkedList<Point>();
			visit = new boolean[n][n];
            
			int boom = 0;
			
			for(int j=0;j<n;j++) {
				String str = br.readLine();
				for(int k=0;k<n;k++) {
					arr[j][k] = str.charAt(k);
					if(arr[j][k]=='.') {
                        // 폭탄이 아닌 자리는 주변 지뢰의 수로 치환하기위해 큐에 삽입
						que.add(new Point(k,j));
					}else {
                        // 폭탄인 자리는 나올 수 없는 숫자 9로 치환하고 폭탄의 갯수를 카운트
						anw[j][k] = 9;
						boom++;
					}
				}
			}
			// 주변 지뢰의 수로 치환
			write();
			zero = 0;
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
                    // 시작지점 큐에 삽입
					if(anw[j][k]==0) {
						anw[j][k] = -1;
						que.add(new Point(k,j));
						visit[j][k] = true;
                        // 0의 구역이 몇개인지 카운트
						zero++;
					}
                    // 0을 눌렀을 때 열리는 구역을 모두 계산(방문처리)
					cal();
				}
			}
			
			int anwcnt = 0;

			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
                    // 방문하지 않은 곳을 카운트 => 0으로 열리지 않는곳은 직접 눌러서 열어야 하기 때문에
                    // 현재 코드상 폭탄의 구역도 미방문 처리되어 마지막 출력에서 계산
					if(!visit[j][k]) {
						anwcnt++;
					}
				}
			}
            // 방문하지 않은 곳을
			System.out.println("#"+i+" "+(anwcnt+zero-boom));
			
		}
		
		
	}

}