import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
import java.math.*;
public class 정사각형방 {
	static int[][] arr;
	static boolean[][] visit;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int n;
	static int anwy;
	static int anwx;
	static int cnt;
	static int max;
	static Queue<now> que;
	
	static void cal(int y, int x) {
		// 몇 번 나아가는지 카운트를 위한 변수
		cnt = 0;
		// 현재 위치를 큐에 삽입
		que.add(new now(y,x));
		// 탐색
		while(!que.isEmpty()) {
			// 현재 위치
			now w = que.poll();
			// 자기자신부터 1로 카운트하기 때문에 큐에서 poll 할 때마다 카운트
			cnt++;
			// 현재 위치의 y,x 좌표
			int nowy = w.y;
			int nowx = w.x;
			
			// 사방탐색
			for(int i=0;i<4;i++) {
				int ny = w.y+dy[i];
				int nx = w.x+dx[i];
				// 배열 이내일 경우
				if(ny>=0&&ny<n&&nx>=0&&nx<n) {
					// 탐색 위치가 현재 위치보다 1 더 클 때 큐에 삽입하고 더 이상 탐색X
					if(arr[ny][nx]==arr[nowy][nowx]+1) {
						que.add(new now(ny,nx));
						break;
					}
				}
			}
			
		}
		// 최대값과 그 위치를 저장
		if(max<cnt) {
			max = cnt;
			anwy = y;
			anwx = x;
		// 값이 같을경우 현재 방의 숫자가 더 작은곳을 저장
		}else if(max==cnt) {
			if(arr[anwy][anwx]>arr[y][x]) {
				anwy = y;
				anwx = x;
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		int tcase = parseInt(br.readLine());
		
		for(int i=1;i<=tcase;i++) {
			
			n = parseInt(br.readLine());
			arr = new int[n][n];
			que = new LinkedList<now>();
			visit = new boolean[n][n];
			
			for(int j=0;j<n;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<n;k++) {
					arr[j][k] = parseInt(st.nextToken());
				}
			}
			max = Integer.MIN_VALUE;
			
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					cal(j,k);
				}
			}
			
			System.out.println("#"+i+" "+arr[anwy][anwx]+" "+max);
			
		}
		
	}

}

class now{
	int x;
	int y;
	public now(int y, int x) {
		super();
		this.x = x;
		this.y = y;
	}
	
}

