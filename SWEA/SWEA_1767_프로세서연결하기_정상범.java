import java.util.*;
import java.io.*;
import java.awt.Point;

public class 프로세서연결하기 {
    static int n, maxCores, minLength;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static ArrayList<Point> core;

    // 주어진 좌표가 맵의 범위 내에 있는지 체크
    static boolean isRange(int ny, int nx) {
        return ny >= 0 && ny < n && nx >= 0 && nx < n;
    }

    // 특정 방향으로 전선을 설치하고, 설치된 전선의 길이를 반환
    static int installWire(int y, int x, int dir) {
        int length = 0;
        int ny = y + dy[dir];
        int nx = x + dx[dir];
        
        // 범위 내에 0이 아닌 값이 한 번이라도 존재하면 -1을 반환 
        while (isRange(ny, nx)) {
            if (map[ny][nx] != 0) return -1; // 설치할 수 없음
            ny += dy[dir];
            nx += dx[dir];
        }

        // 0만 존재하면 전선 설치
        ny = y + dy[dir];
        nx = x + dx[dir];
        while (isRange(ny, nx)) {
            map[ny][nx] = 2;
            length++;
            ny += dy[dir];
            nx += dx[dir];
        }
        // 설치 완료 후 전선의 길이를 반환
        return length;
    }

    // 설치된 전선을 제거
    static void removeWire(int y, int x, int dir) {
        int ny = y + dy[dir];
        int nx = x + dx[dir];
        // 범위 내에 설치된 전선은 모두 제거
        while (isRange(ny, nx) && map[ny][nx] == 2) {
            map[ny][nx] = 0;
            ny += dy[dir];
            nx += dx[dir];
        }
    }

    // 전선 설치가 가능한 코어들은 순차적으로 수행
    static void dfs(int depth, int connectedCores, int wireLength) {
    	// 가능한 코어들이 모두 수행됬을 때
        if (depth == core.size()) {
        	// 더 많은 코어가 연결된 경우 코어의 개수와 전선의 길이를 갱신
            if (connectedCores > maxCores) {
                maxCores = connectedCores;
                minLength = wireLength;
            // 코어의 개수가 같으면 최소값 갱신
            } else if (connectedCores == maxCores) {
                minLength = Math.min(minLength, wireLength);
            }
            // 메서드 종료
            return;
        }
        // 현재 탐색하는 코어
        Point p = core.get(depth);

        // 4 방향으로 전선 설치 시도
        for (int d = 0; d < 4; d++) {
        	// 전선 설치 후 전선의 길이 or 설치 못했을 때 값
            int length = installWire(p.y, p.x, d);
            // 전선을 설치했을 때
            if (length != -1) {
            	// 다음 코어로, 코어개수 더하기, 전선길이 더하기
                dfs(depth + 1, connectedCores + 1, wireLength + length);
                // 다음 방향을 위해 원복
                removeWire(p.y, p.x, d);
            }
        }
        // length == -1 일 때
        // 이 코어를 연결하지 않고 다음으로 진행
        dfs(depth + 1, connectedCores, wireLength);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tcase = Integer.parseInt(br.readLine());

        for (int i = 1; i <= tcase; i++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            core = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                    if (map[j][k] == 1) {
                        if (j == 0 || k == 0 || j == n - 1 || k == n - 1) continue; // 경계에 있는 코어 제외
                        core.add(new Point(k, j));
                    }
                }
            }

            maxCores = 0;
            minLength = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + i + " " + minLength);
        }
    }
}
