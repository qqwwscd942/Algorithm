import java.util.*;
import java.io.*;                                                                                                  
import static java.lang.Integer.*;
import java.awt.Point;
public class contact {
	static int anw,max;
	static ArrayList<Integer>[] list;
	static Queue<node> que;
	static int[] nodelevel;
	static boolean[] visit;

	// 배열에 리스트 채우기
	static ArrayList<Integer>[] MakeSet(ArrayList<Integer>[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new ArrayList<>();
		}
		return arr;
	}
	
	static void cal() {
		// queue가 빌 때까지
		while(!que.isEmpty()) {
			// 현재 노드 꺼내기
			node now = que.poll();
			// 현재 노드가 갈 수있는 노드 확인
			for(int i : list[now.num]) {
				// 미방문 시 큐에 삽입하고 방문처리
				if(!visit[i]) {
					// 다음 노드의 번호와 순서를 담는다
					// 항상 현재 노드의 순서 다음이기 때문에
					// 현재 노드의 순서 + 1
					que.add(new node(i,now.level+1));
					visit[i] = true;
					// 해당 노드번호에 해당하는 배열에 순서 저장
					nodelevel[i] = now.level+1;
				}
			}
		}
		// 가장 나중에 방문하는 배열을 찾는다
		// => 배열의 인덱스번호가 노드의 번호
		anw = 0;
		for(int i : nodelevel) {
			if(anw<i) {
				anw = i;
			}
		}
		max = 0;
		// 가장 나중에 방문하는 노드들 중 가장 큰 수를 저장
		for(int i=0;i<nodelevel.length;i++) {
			if(nodelevel[i]==anw) {
				if(max<i) {
					max = i;
				}
			}
		}
		System.out.println(anw+" "+max);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=1;i<=10;i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int size = parseInt(st.nextToken());
			int start = parseInt(st.nextToken());
			
			list = new ArrayList[101];
			que = new LinkedList<>();
			visit = new boolean[101];
			nodelevel = new int[101];
			MakeSet(list);
			
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<size/2;j++) {
				int from = parseInt(st.nextToken());
				int to = parseInt(st.nextToken());
				// 노드의 간선 연결
				// 중복되지 않은 간선만 저장
				if(list[from].isEmpty()) {
					list[from].add(to);
				}else {
					if(!list[from].contains(to)) {
						list[from].add(to);
					}
				}
				
			}
			// 시작지점과 순서를 객체에 담아 queue에 삽입
			que.add(new node(start,0));
			// 해당 노드 방문처리
			visit[start] = true;
			// 계산
			cal();
			
			System.out.println("#"+i+" "+max);
			
		}
	}
}
// 노드의 번호와 순서를 저장하기 위한 class
class node{
	int num;
	int level;
	
	public node(int num, int level) {
		super();
		this.num = num;
		this.level = level;
	}
	
}
