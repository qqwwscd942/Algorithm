import java.util.*;
import java.io.*;
public class Main {
	
	public static ArrayList<Integer>[] list;
	public static boolean[] visited;
	
	public static void bfs(int one) {
		Queue<Integer> que = new LinkedList<>();
        // 큐에 1 넣기
		que.add(one);
		while(!que.isEmpty()) {
            // q에서 첫 번째 값 빼기
			int q = que.poll();
            // 뺀 값에 들어있는 만큼 돌리기 ex)1 -> 2,5  총 두번 반복
			for(int i=1;i<=list[q].size();i++) {
                // 1번과 연결된 번호의 방문처리
				if(visited[list[q].get(i-1)]==false) {
					visited[list[q].get(i-1)] = true;
                    // 방문 했으면 큐에넣고 각각 연결된 번호 확인
					que.add(list[q].get(i-1));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int connect = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		for(int i=1;i<=n;i++) {
			list[i] = new ArrayList();
		}
		
		for(int i=0;i<connect;i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			// 양방향 연결 리스트에 저장
			list[from].add(to);
			list[to].add(from);
		}
        // 탐색
		bfs(1);
		
		int cnt=0;
        // 총 연결된 번호의 갯수 확인
        // 1은 시작이라 2번 부터 확인
		for(int i=2;i<=n;i++) {
			if(visited[i]==true) cnt++;
		}
		System.out.println(cnt);
		
	}
	
}
