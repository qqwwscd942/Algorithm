import java.util.*;
import java.io.*;
import java.math.*;

public class 치킨배달 {

	static ArrayList<Chicken> store;
	static ArrayList<House> house;
	static int[][] city;
	static boolean[] visit;
	static int min;
	static int dismin;
	static int summin = Integer.MAX_VALUE;
	static int n;
	
	// 치킨집의 갯수에 맞게 전체 치킨집의 수에서 조합으로 뽑고
	// 각 치킨집에서 가장 가까운 집과의 거리를 구해서 합한다
	public static void inputstore(int N, int M, int depth, int current) {
		// 치킨집을 M개 뽑았을 때
		if (depth == M) {
			int sum = 0;
			min = Integer.MAX_VALUE;
			// 각 집과의 거리를 비교해서 가장 가까운 집과의 거리를 sum
			for(int i=0;i<house.size();i++) {
				calmin(i,n);
				sum += dismin;
			}
			// 현재 뽑은 가게들과 집들의 거리가 가장 작으면 최솟값 갱신
			if(sum<summin) {
				summin = sum;
			}
			return;
		}
		
		// visit은 뽑은 치킨집들을 체크하는 배열
		// 현재 치킨집의 위치들은 객체로 배열에 담겨있기 때문
		for (int i = current; i < N; i++) {
			// 안뽑은 치킨집이면 뽑기
			if (!visit[i]) {
				// 방문체크 
				visit[i] = true;
				// 다음 치킨집 뽑기
				inputstore(N, M, depth + 1, i+1);
				// 원복
				visit[i] = false;
			}
		}
	}
	
	// 뽑은 치킨집들과 집들의 거리를 계산
	public static void calmin(int i, int n) {
		dismin = Integer.MAX_VALUE;
		for(int a=0;a<visit.length;a++) {
			// 뽑은 치킨집이면
			if(visit[a]==true) {
				int storex = store.get(a).x;
				int storey = store.get(a).y;
				int housex = house.get(i).x;
				int housey = house.get(i).y;
				
				// 해당 번호의 치킨집과 집의 거리를 계산
				int dis = (Math.abs(storex-housex)+Math.abs(storey-housey));
				if(dis<dismin) {
					dismin = dis;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 도시 크기
		n = Integer.parseInt(st.nextToken());
		// 남길 치킨집 수
		int m = Integer.parseInt(st.nextToken());
		city = new int[n][n];
		store = new ArrayList<>();
		house = new ArrayList<>();

		// 도시 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				// 우선 치킨집 자리에 0 대입
				// 하지만 치킨집 위치는 객체에 담아 ArrayList에 저장
				if (num == 2) {
					city[i][j] = 0;
					store.add(new Chicken(i, j));
				} else if(num == 1) {
					city[i][j] = 1;
					house.add(new House(i,j));
				}
				
				else {
					// 나머지는 그대로
					city[i][j] = num;
				}
			}
		}
		visit = new boolean[store.size()];
		// 치킨집을 대입하고 계산 시작
		inputstore(store.size(), m, 0, 0);
		
		System.out.println(summin);
		
	}
}

class Chicken {
	int x;
	int y;

	public Chicken(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

class House {
	int x;
	int y;

	public House(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}