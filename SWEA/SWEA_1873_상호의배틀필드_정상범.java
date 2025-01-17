import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
public class 상호의배틀필드 {
	static int n,m,orderN;
	static int nowy,nowx;
	static String nowt;
	static String[][] map;
	static String[] order;
	
	// 조작에 따라 현재 위치를 이동하고 명령을 수행하는 메서드
	static void cal(int j, int k, String tank) {
		// 현재 위치와 상태
		nowy = j;
		nowx = k;
		nowt = tank;
		// 각 명령에 따라 수행
		for(int i=0;i<orderN;i++) {
			
			switch(order[i]) {
			case "U":
				moveUp(j,k,tank);
				break;
			case "D":
				moveDown(j,k,tank);
				break;
			case "L":
				moveLeft(j,k,tank);
				break;
			case "R":
				moveRight(j,k,tank);
				break;
			case "S":
				shooting(j,k,tank);
				break;
			}
			// 명령 수행 후 위치 업데이트
			j = nowy;
			k = nowx;
			tank = nowt;
		}
	}
	
	// 위로 이동
	static void moveUp(int j, int k, String tank) {
		// 현재 바라보는 방향을 상으로
		map[j][k] = "^";
		// 이동할 좌표
		int ny = j-1;
		// 이동 가능한 범위인지 확인
		if(check(ny,k)) { 
			//  위치 이동
			map[ny][k] = "^";
			// 지나간 자리는 비우기
			map[j][k] = ".";
			// 위치 갱신
			nowy = ny;
			nowx = k;
			nowt = map[ny][k];
		}else {
			nowy = j;
			nowx = k;
			nowt = map[j][k];
		}
		
	}
	
	// 아래로 이동
	static void moveDown(int j, int k, String tank) {
		// 현재 바라보는 방향을 하로
		map[j][k] = "v";
		// 이동할 좌표
		int ny = j+1;
		// 이동 가능한 범위인지 확인
		if(check(ny,k)) {
		//  위치 이동
			map[ny][k] = "v";
			// 지나간 자리는 비우기
			map[j][k] = ".";
			// 위치 갱신
			nowy = ny;
			nowx = k;
			nowt = map[ny][k];
		}else {
			nowy = j;
			nowx = k;
			nowt = map[j][k];
		}
		
	}
	
	// 왼쪽으로 이동
	static void moveLeft(int j, int k, String tank) {
		// 현재 바라보는 방향을 좌로
		map[j][k] = "<";
		// 이동할 좌표
		int nx = k-1;
		// 이동 가능한 범위인지 확인
		if(check(j,nx)) {
		// 위치이동
			map[j][nx] = "<";
			// 지나간 자리는 비우기
			map[j][k] = ".";
			// 위치 갱신
			nowy = j;
			nowx = nx;
			nowt = map[j][nx];
		}else {
			nowy = j;
			nowx = k;
			nowt = map[j][k];
		}
		
	}
	
	// 오른쪽으로 이동
	static void moveRight(int j, int k, String tank) {
		// 현재 바라보는 방향을 우로
		map[j][k] = ">";
		// 이동할 좌표
		int nx = k+1;
		// 이동 가능한 범위인지 확인
		if(check(j,nx)) {
		// 위치 이동
			map[j][nx] = ">";
			// 지나간 자리는 비우기
			map[j][k] = ".";
			// 위치 갱신
			nowy = j;
			nowx = nx;
			nowt = map[j][nx];
		}else {
			nowy = j;
			nowx = k;
			nowt = map[j][k];
		}
		
	}
	
	// 바라보는 방향에 따라 슈팅 조정
	static void shooting(int j, int k, String tank) {
		switch(tank) {
		case "v":
			shoot(j,k,1,0);
			break;
		case "^":
			shoot(j,k,-1,0);
			break;
		case "<":
			shoot(j,k,0,-1);
			break;
		case ">":
			shoot(j,k,0,1);
			break; 
		}
	}
	
	// 슈팅(벽에 따라)
	static void shoot(int y, int x, int dy, int dx) {
		int ny = y+dy;
		int nx = x+dx;
		//  범위 이내에 존재하면
		while(ny>=0&&ny<n&&nx>=0&&nx<m) {
			// 벽이 벽돌이면 부수기
			if(map[ny][nx].equals("*")) {
				map[ny][nx] = ".";
				break;
			// 강철이면 소멸
			}else if(map[ny][nx].equals("#")){
				break;
			}
			// 벽이 없으면 한 칸 더 전진
			ny += dy;
			nx += dx;
		}
	}
	
	// 범위 체크
	static boolean check(int ny, int nx) {
		if(ny>=0&&ny<n&&nx>=0&&nx<m) {
			if(map[ny][nx].equals(".")) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tcase = parseInt(br.readLine());
		
		for(int i=1;i<=tcase;i++) {
			st = new StringTokenizer(br.readLine());
			
			n = parseInt(st.nextToken());
			m = parseInt(st.nextToken());
			
			map = new String[n][m];
			
			for(int j=0;j<n;j++) {
				String str = br.readLine();
				map[j] = str.split("");
			}
			
			orderN = parseInt(br.readLine());
			String str = br.readLine();
			
			order = new String[orderN];
			order = str.split("");
			
			int startj = 0;
			int startk = 0;
			
			// 탱크 위치 찾기
			for(int j=0;j<n;j++) {
				for(int k=0;k<m;k++) {
					switch(map[j][k]) {
					case "v":
						startj = j;
						startk = k;
						break;
					case "^":
						startj = j;
						startk = k;
						break;
					case "<":
						startj = j;
						startk = k;
						break;
					case ">":
						startj = j;
						startk = k;
						break;
					default :
						break;
					}
				}
			}
			// 탱크 위치와 현재 상태에 맞춰 계산
			cal(startj,startk,map[startj][startk]);
			
			System.out.print("#"+i+" ");
			
			for(int j=0;j<n;j++) {
				for(int k=0;k<m;k++) {
					System.out.print(map[j][k]);
				}
				System.out.println();
			}
		}
	}

}
