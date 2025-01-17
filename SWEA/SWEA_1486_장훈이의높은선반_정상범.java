import java.util.*;
import java.io.*;

public class 장훈이의높은선반 {

    static int n;
    static int jh;
    static int min;
    static int[] heights;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tcase = Integer.parseInt(br.readLine());
        
        for(int i = 1; i <= tcase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            jh = Integer.parseInt(st.nextToken());
            heights = new int[n];
            
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                heights[j] = Integer.parseInt(st.nextToken());
            }
            // 최소의 차이를 초기화
            min = Integer.MAX_VALUE;
            // 계산
            cal(0, 0);
            
            System.out.println("#" + i + " " + min);
        }
    }

    static void cal(int depth, int cur) {
        // 현재 조합의 합이 장훈이보다 크거나 같은 경우
        if (cur >= jh) {
            // 현재 최솟값과 비교 후 저장
            min = Math.min(min, cur - jh);
            return;
        }
        // 전체 원소의 수보다 오버되면 메서드 종료
        if (depth >= n) return;

        // 현재 사람을 포함하는 경우
        cal(depth + 1, cur + heights[depth]);
        // 현재 사람을 포함하지 않는 경우
        cal(depth + 1, cur);
    }
}
