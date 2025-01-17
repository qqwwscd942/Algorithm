import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
public class Solution {

	static int[][] arr;
	static int n;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tcase = parseInt(br.readLine());

		for (int i = 1; i <= tcase; i++) {
			st = new StringTokenizer(br.readLine());
			n = parseInt(st.nextToken());
			String dir = st.nextToken();

			arr = new int[n][n];
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < n; k++) {
					arr[i][j] = parseInt(st.nextToken());
				}
			}
			
			// 오랜만에 if대신 switch 써봤습니다..
			switch (dir) {
			case "up":
				moveUp();
				break;
			case "down":
				moveDown();
				break;
			case "left":
				moveLeft();
				break;
			case "right":
				moveRight();
				break;
			}

			System.out.println("#" + i);
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					System.out.print(arr[j][k] + " ");
				}
				System.out.println();
			}
		}
	}

	public static void moveUp() {
		for (int j = 0; j < n; j++) {
			// 각각 합친적 있는지 체크할 배열
			visit = new boolean[n];
			// 현재 0이면 스킵
			for (int i = 1; i < n; i++) {
				if (arr[i][j] == 0) {
					continue;
				}
				// 아니면 값 저장
				int value = arr[i][j];
				// 현재 열
				int now = i;
				// 현재의 위가 0이면 0이 아니거나 벽일때까지 이동해서 저장
				while (now > 0 && arr[now - 1][j] == 0) {
					arr[now - 1][j] = value;
					arr[now][j] = 0;
					now--;
				}
				// 위와 같으면 값 더하기
				if (now > 0 && arr[now - 1][j] == value && !visit[now - 1]) {
					arr[now - 1][j] *= 2;
					arr[now][j] = 0;
					// 먼저처리하고 그 뒤에 또 합할 수 있기때문에 체크
					visit[now - 1] = true;
				}
			}
		}
	}

	public static void moveDown() {
		for (int j = 0; j < n; j++) {
			visit = new boolean[n];
			for (int i = n - 2; i >= 0; i--) {
				if (arr[i][j] == 0) {
					continue;
				}
				int value = arr[i][j];
				int now = i;
				// 아래가 0일경우 벽을만나거나 0이 아닌곳까지 가서 저장
				while (now < n - 1 && arr[now + 1][j] == 0) {
					arr[now + 1][j] = value;
					arr[now][j] = 0;
					now++;
				}
				// 아래와 같으면 값 더하기
				if (now < n - 1 && arr[now + 1][j] == value && !visit[now + 1]) {
					arr[now + 1][j] *= 2;
					arr[now][j] = 0;
					visit[now + 1] = true;
				}
			}
		}
	}

	public static void moveLeft() {
		for (int i = 0; i < n; i++) {
			visit = new boolean[n];
			for (int j = 1; j < n; j++) {
				if (arr[i][j] == 0) {
					continue;
				}
				int value = arr[i][j];
				int now = j;
				// 이번엔 좌측으로 0이 아니거나 벽가지 이동
				while (now > 0 && arr[i][now - 1] == 0) {
					arr[i][now - 1] = value;
					arr[i][now] = 0;
					now--;
				}
				// 같으면 값 저장
				if (now > 0 && arr[i][now - 1] == value && !visit[now - 1]) {
					arr[i][now - 1] *= 2;
					arr[i][now] = 0;
					visit[now - 1] = true;
				}
			}
		}
	}
	
	public static void moveRight() {
		for (int i = 0; i < n; i++) {
			visit = new boolean[n];
			for (int j = n - 2; j >= 0; j--) {
				if (arr[i][j] == 0) {
					continue;
				}
				int value = arr[i][j];
				int now = j;
				// 벽이나 0이 아닌곳까지 가서 저장
				while (now < n - 1 && arr[i][now + 1] == 0) {
					arr[i][now + 1] = value;
					arr[i][now] = 0;
					now++;
				}
				// 값이 같으면 더하기
				if (now < n - 1 && arr[i][now + 1] == value && !visit[now + 1]) {
					arr[i][now + 1] *= 2;
					arr[i][now] = 0;
					visit[now + 1] = true;
				}
			}
		}
	}

}
