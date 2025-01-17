import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
public class 보호필름 {
	static int d,w,k,anw,min;
	static int[][] arr;
	static int[][] ori;
	static int[] A;
	static int[] B;
	static boolean[] visit;
	
	static boolean check() {
		int total = 0;
		for(int i=0;i<w;i++) {
			int max = 1;
			int cnt = 1;
			for(int j=0;j<d-1;j++) {
				if(arr[j][i]==arr[j+1][i]) {
					cnt++;
					if(max<cnt) {
						max = cnt;
					}
				}else {
					cnt = 1;
				}
			}
			if(max<k) {
				return false;
			}
		}
		return true;
	}
	
	static void change(boolean[] visit, int depth, int cnt) {
		if(cnt >= min) return;
		if(depth==d) {
			if(check()) {
				min = cnt;
			}
			return;
		}
		
		if(visit[depth]) {
			arr[depth] = A.clone();
			change(visit,depth+1,cnt);
			
			arr[depth] = B.clone();
			change(visit,depth+1,cnt);
		}else {
			change(visit,depth+1,cnt);
		}
		
	}
	
	static void cal(int depth) {
		if(depth==d) {
			
			int cnt = 0;
			for(int i=0;i<d;i++) {
				if(visit[i]) {
					cnt++;
				}
			}
			if(cnt!=0) {
				change(visit,0,cnt);
			}
			
			for(int i=0;i<d;i++) {
				if(visit[i]) {
					arr[i] = ori[i].clone();
				}
			}
			
			return;
		}
		
		visit[depth] = true;
		cal(depth+1);
		
		visit[depth] = false;
		cal(depth+1);
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tcase = parseInt(br.readLine());
		
		for(int i=1;i<=tcase;i++) {
			
			st = new StringTokenizer(br.readLine());
			d = parseInt(st.nextToken());
			w = parseInt(st.nextToken());
			k = parseInt(st.nextToken());
			
			arr = new int[d][w];
			ori = new int[d][w];
			visit = new boolean[d];
			A = new int[w];
			B = new int[w];
			Arrays.fill(A, 0);
			Arrays.fill(B, 1);
			
			for(int j=0;j<d;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<w;k++) {
					arr[j][k] = parseInt(st.nextToken());
					ori[j][k] = arr[j][k];
				}
			}
			
			min = Integer.MAX_VALUE;
			if(check()) {
				System.out.println("#"+i+" 0");
			}else {
				cal(0);
				System.out.println("#"+i+" "+min);
			}
			
			check();
			
		}
	}

}
