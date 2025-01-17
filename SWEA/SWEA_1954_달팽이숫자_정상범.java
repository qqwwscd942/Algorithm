
import java.util.*;
public class Main {
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {1,0,-1,0};
	public static boolean[][] visit;
	public static Queue<cur> que;
	public static int[][] arr;
	public static int n;

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tcase = sc.nextInt();
		
		for(int i=1;i<=tcase;i++) {
			n =sc.nextInt();
			arr = new int[n][n];
			visit = new boolean[n][n];
            // bfs활용을 위한 queue 생성
			que = new LinkedList<>();
            // start 배열인 0,0 값 입력
			arr[0][0] = 1;
            // 방문처리
			visit[0][0] = true;
			// queue에 삽입
			que.add(new cur(0,0));
            // dx,dy 배열의 순서는 0번부터 시작
			int mcnt = 0;
            // 값은 2부터
			int cnt = 2;
            // queue가 빌때까지
			while(!que.isEmpty()) {
                // 계산을 위한  queue poll
				cur c = que.poll();
                // 현재위치 계산
				int nx = c.x+dx[mcnt];
				int ny = c.y+dy[mcnt];
                // 벽에 닿거나 이미 방문한 자리가 나올 떄 처리
				if(nx==n||ny==n||nx==-1||ny==-1||visit[nx][ny]) {
                    // 다음 방향으로 이동
					mcnt++;
                    // 4가되면 다시 0번째 dx,dy배열로 
					if(mcnt==4) {
						mcnt=0;
					}
                    // 다시 이동
					nx = c.x+dx[mcnt];
					ny = c.y+dy[mcnt];

				}
                //방문하지 않았을 때
				if(!visit[nx][ny]&&nx>=0&&nx<n&&ny>=0&&ny<n) {
                    // 방문처리
					visit[nx][ny] = true;
                    // 값 입력
					arr[nx][ny] = cnt;
                    // queue에 추가
					que.add(new cur(nx,ny));
					cnt++;
				}
				
			}
			
			for(int a=0;a<n;a++) {
				for(int b=0;b<n;b++) {
					System.out.print(arr[a][b]+" ");
				}
				System.out.println();
			}
			
		}
	}

}
// 현재 위치를 객체화 하기위한 클래스
class cur{
	int x;
	int y;
	public cur(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
