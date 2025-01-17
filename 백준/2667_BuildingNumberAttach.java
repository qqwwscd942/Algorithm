
import java.util.*;
import java.io.*;

public class Main {
    private static int n;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static List<Integer> list;

    public static void search(int i, int j) {
        // 탐색을 위한 큐 생성
        Queue<Apt> que = new LinkedList<>();
        // 각 단지 수 카운트를 위해 cnt변수 선언
        int cnt = 0;
        // 탐색하는 좌표의 정보를 Apt class로 큐에 추가
        que.add(new Apt(i, j));
        // 큐에 들어가면 탐색을 하기 때문에 자기자신 방문처리
        visited[i][j] = true;

        while (!que.isEmpty()) {
            // 큐의 맨 앞의 집 정보가 poll
            Apt apt = que.poll();
            // 집 카운트
            cnt++;
            //집의 사방탐색
            for (int a = 0; a < 4; a++) {
                int ny = apt.getI() + dy[a];
                int nx = apt.getJ() + dx[a];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (map[ny][nx] == 1 && visited[ny][nx]!=true) {
                        // 집이 있고, 방문처리가 되지 않았으면 큐에 추가
                        que.add(new Apt(ny, nx));
                        // 사방탐색에 대한 방문처리
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        // 단지 수 저장
        list.add(cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 지도 사이즈
        n = Integer.parseInt(br.readLine());
        // 지도 생성
        map = new int[n][n];
        // 방문처리를 위한 배열 생성
        visited = new boolean[n][n];
        // 단지 수 저장을 위한 리스트 생성
        list = new ArrayList<>();
        // 지도를 정수로 입력받기
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        // 탐색 시작
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    search(i, j);
                }
            }
        }
        // 저장된 리스트(각 단지 수)를 정렬
        Collections.sort(list);
        // 크기 출력
        System.out.println(list.size());
        for (int cnt : list) {
            // 정렬된 각 단지 수 출력
            System.out.println(cnt);
        }
    }
}
// 각 집의 위치정보 저장을 위한 class
class Apt {
    private int i;
    private int j;

    public Apt(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
