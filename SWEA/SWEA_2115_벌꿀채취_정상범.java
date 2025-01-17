import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
import java.awt.Point;
public class 벌꿀채취 {
	static int[][] map;
	static int[] select;
	static boolean[][] visit;
	static boolean[] visited;
	static Point[] start;
	static int n,m,c,all,maxsum,sum,anw,max,sumpow;
	
	// 1.먼저 모든 M개의 벌통집합 중 2개를 고르는 과정
	static void choice(int depth, int cur) {
		// 2개를 뽑았을 때
		if(depth==2) {
			anw = 0;
			// 뽑은 벌통집합에 꿀의 양을 배열로 만들어서 계산하는 메서드에 전달
			// => 벌통집합의 시작점으로부터 m개가 벌통집합
			for(int j=0;j<2;j++) {
				int[] arr = new int[m];
				for(int i=0;i<m;i++) {
					Point one = start[select[j]];
					arr[i] = map[one.y][one.x+i];
				}
				max = 0;
				// 그 중에서 부분집합을 통해 최대값을 계산
				inChoice(0,arr);
				// 두 개의 벌통집합의 최대값을 합산
				anw += max;
			}
			// 다른 조합의 경우와 비교해서 최대값 저장
			if(maxsum<anw) {
				maxsum = anw;
			}
			return;
		}
		// 선택한 벌통집합에 방문처리를 하여 다른 집합에서 겹치는 부분이 없게 만듦
		for(int i=cur;i<all;i++) {
			int starty = start[i].y;
			int startx = start[i].x;
			// 선택한 집합의 시작점으로부터 m개가 해당 집합
			if(!visit[starty][startx]) {
				for(int j=0;j<m;j++) {
					visit[starty][startx+j] = true;
				}
				select[depth] = i;
				choice(depth+1,i+1);
				for(int j=0;j<m;j++) {
					visit[starty][startx+j] = false;
				}
			}
			
		}
	}
	
	// 2.선택한 벌통집합에서 부분집합을 통해 C(꿀의 양에 최대값)를 넘지않을 때 수익을 계산
		static void inChoice(int depth,int[] arr) {
			if(depth==m) {
				sum = 0;
				sumpow = 0;
				for(int i=0;i<m;i++) {
					if(visited[i]) {
						// 꿀의 양 최대값을 넘기는지 확인하기위해 sum
						sum += arr[i];
						// 최대값을 넘기면 메서드 종료
						if(sum>c) {
							return;
						}
						// 넘기지 않으면 수익 계산
						sumpow += arr[i] * arr[i];
					}
				}
				// 현재 집합에서 가장 큰 수익이면 갱신
				if(max<sumpow) {
					max = sumpow;
				}
				return;
			}
			
			visited[depth] = true;
			inChoice(depth+1,arr);
			
			visited[depth] = false;
			inChoice(depth+1,arr);
			
			
		}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tcase = parseInt(br.readLine());
		
		for(int i=1;i<=tcase;i++) {
			
			st  = new StringTokenizer(br.readLine());
			n = parseInt(st.nextToken());
			m = parseInt(st.nextToken());
			c = parseInt(st.nextToken());
			//  일꾼들이 벌통을 선택할 수 있는 가지 수
			all = (n-m+1)*n;
			maxsum = Integer.MIN_VALUE;
			// 벌통(꿀의 양) 배치
			map = new int[n][n];
			// 일꾼의 선택
			select = new int[2];
			// M개씩 묶은 벌통의 시작지점을 저장
			start = new Point[all];
			// 선택된 벌통인지 체크를 위한 배열
			visit = new boolean[n][n];
			// M개의 벌통을 선택했을 때 그 중에 부분집합 체크를 위한 배열
			visited = new boolean[m];
			
			// M개의 벌통묶음의 각 시작지점을 객체배열에 저장
			int cnt = 0;
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n-m+1; k++) {
					start[cnt] = new Point(k,j);
					cnt++;
				}
			}
			
			// 꿀의 양 입력
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < n; k++) {
					map[j][k] = parseInt(st.nextToken());
				}
			}
			
			choice(0,0);
			System.out.println("#"+i+" "+maxsum);
			
			
		}
		
		
	}

}
