import java.util.*;
import java.io.*;                                                                                                  
import static java.lang.Integer.*;
import java.awt.Point;
public class 디저트카페 {
	static int n,anw,max,sty,stx;
	static int[] dy = {1,1,-1,-1};
	static int[] dx = {-1,1,1,-1};
	static int[][] arr;
	static ArrayList<Integer> list;
	static boolean[][] visit;
	
    // 배열내인지 확인을 위한 메서드
	static boolean isRange(int ny, int nx) {
		if(ny<0||ny>=n||nx<0||nx>=n) {
			return false;
		}
		return true;
	}
	
    // 계산
	static void cal(int j, int k,int cnt,int depth) {
        // 시작지점을 저장
		if(depth==0) {
			sty = j;
			stx = k;
		}
        // dy = -1
        // dx = -1
        // 돌아오는 방향이고, 좌우대칭이어야 하며, 시작지점으로부터 우측하단(+1,+1)이 마지막 지점
		if(cnt==3&&depth%2!=0&&j==sty+1&&k==stx+1) {
            // 그때까지 움직였다면 배열의 사이즈를 저장
			anw = list.size();
			if(max<anw) {
                // 최댓값 갱신
				max = anw;
			}
			return;
		}
        // 좌우 대칭을 벗어나 한 번더 꺾이면 return
		if(cnt==4) {
			return;
		}

		int ny = j+dy[cnt];
		int nx = k+dx[cnt];

		// 배열내이며, 배열에 존재하지 않을 때만 가능
		if(isRange(ny, nx)&&!list.contains(arr[ny][nx])) {
			// 배열에 추가
			list.add(arr[ny][nx]);
            // 방향을 유지하는 경우
			cal(ny,nx,cnt,depth+1);
            // 꺾는 경우
			cal(ny,nx,cnt+1,depth+1);
            // 다음경우를 위해 원복
			list.remove(list.size()-1);
		// 그 외에는 모두 return
		}else{
			return;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tcase = parseInt(br.readLine());
		
		for(int i=1;i<=tcase;i++) {
			
			n = parseInt(br.readLine());
			arr = new int[n][n];
			visit = new boolean[n][n];
			list = new ArrayList<>();
			
			for(int j=0;j<n;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<n;k++) {
					arr[j][k] = parseInt(st.nextToken());
				}
			}
			
			max = Integer.MIN_VALUE;
			
			for(int j=0;j<n-2;j++) {
				for(int k=1;k<=n-2;k++) {
                    // 시작지점을 배열에 추가하고 시작
					list.add(arr[j][k]);
                    // 계산
					cal(j,k,0,0);
                    // 다음경우를 위해 list 초기화
					list.clear();
				}
			}
			// 한 바퀴의 최소값인 4보다 작으면 조건에 맞는 경우가 없으므로 -1
			if(max<4) {
				max = -1;
			}
			System.out.println("#"+i+" "+max);
			
		}

	}

}
