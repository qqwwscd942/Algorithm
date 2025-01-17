import java.util.*;
import java.io.*;
import static java.lang.Integer.*;
 
public class Solution {
 
    static int n, dir, num;
    static ArrayList<Integer>[] arr;
    static Queue<Integer> que;
    static boolean[] visit;
    static int[] di = {1,-1};
    static ArrayList<Integer> change;
    
    // 앞, 뒤로 연결된 부분의 극이 서로 다른지 확인해서
    // 회전이 일어나야하면 회전 리스트에 추가
    static void cal(int num) {
         
        while(!que.isEmpty()) {
            int cur = que.poll();
            change.add(cur);
             
            for(int i=0;i<2;i++) {
                int now = cur+di[i];
                // 오른쪽 자석
                if(i==0) {
                    // 오른쪽에 자석이있고, 체크하지 않았으면
                    if(now>=0&&now<4&&!visit[now]) {
                        // 현재 2번날과 오른쪽자석의 6번날을 비교
                        if(arr[cur].get(2)!=arr[now].get(6)) {
                            que.add(now);
                            visit[now] = true;
                        }
                    }
                // 왼쪽 자석
                }else {
                    // 왼쪽에 자석이있고, 체크하지 않았으면
                    if(now>=0&&now<4&&!visit[now]) {
                        // 현재 6번날과 왼쪽자석의 2번날을 비교
                        if (arr[cur].get(6)!=arr[now].get(2)) {
                            que.add(now);
                            visit[now] = true;
                        }
                    }
                }
                 
            }
        }

    }
    // 회전하는 메서드
    // 기준이 되는 자석이 짝수번이면 짝수번호의 자석이 방향이 정방향이고
    // 홀수번호는 역방향으로 회전
    // 반대의 경우도 같음
    static void turn() {
        for(int k=0;k<change.size();k++) {
            // 기준이 되는 자석이 짝수번이면(0번부터 시작=> 0,1,2,3 번 자석)
            if(num%2==0) {
                // 회전해야하는 자석이 짝수번이면
                if(change.get(k)%2==0) {
                    // 오른쪽으로 회전
                    if(dir==1) {
                        int last = arr[change.get(k)].get(7);
                        arr[change.get(k)].add(0,last);
                        arr[change.get(k)].remove(8);
                    // 왼쪽으로 회전
                    }else {
                        int first = arr[change.get(k)].get(0);
                        arr[change.get(k)].add(first);
                        arr[change.get(k)].remove(0);
                    }
                // 회전해야하는 자석이 홀수번이면
                }else {
                    // 맞물려있는 자석은 반대로 회전
                    // 왼쪽으로 회전
                    if(dir==1) {
                        int first = arr[change.get(k)].get(0);
                        arr[change.get(k)].add(first);
                        arr[change.get(k)].remove(0);
                    // 오른쪽으로 회전
                    }else {
                        int last = arr[change.get(k)].get(7);
                        arr[change.get(k)].add(0,last);
                        arr[change.get(k)].remove(8);
                    }
                }
            // 기준이 되는 자석이 홀수번이면
            }else {
                // 회전해야하는 자석이 짝수번이면
                if(change.get(k)%2==0) {
                    // 맞물려있는 자석은 반대로 회전
                    // 왼쪽으로 회전
                    if(dir==1) {
                        int first = arr[change.get(k)].get(0);
                        arr[change.get(k)].add(first);
                        arr[change.get(k)].remove(0);
                    // 오른쪽으로 회전
                    }else {
                        int last = arr[change.get(k)].get(7);
                        arr[change.get(k)].add(0,last);
                        arr[change.get(k)].remove(8);
                    }
                // 회전해야하는 자석이 홀수번이면
                }else {
                    // 오른쪽으로 회전
                    if(dir==1) {
                        int last = arr[change.get(k)].get(7);
                        arr[change.get(k)].add(0,last);
                        arr[change.get(k)].remove(8);
                    // 왼쪽으로 회전
                    }else {
                        int first = arr[change.get(k)].get(0);
                        arr[change.get(k)].add(first);
                        arr[change.get(k)].remove(0);
                    }
                }
            }
             
             
        }
         
    }
     
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tcase = parseInt(br.readLine());
         
        for(int i=1;i<=tcase;i++) {
            n = parseInt(br.readLine());
            arr = new ArrayList[4];
            que = new LinkedList<Integer>();
             
             
            for(int j=0;j<4;j++) {
                arr[j] = new ArrayList<>();
                st = new StringTokenizer(br.readLine());
                 
                for(int k=0;k<8;k++) {
                    arr[j].add(parseInt(st.nextToken()));
                }
            }
 
            for(int j=0;j<n;j++) {
                st = new StringTokenizer(br.readLine());
                num = parseInt(st.nextToken())-1;
                dir = parseInt(st.nextToken());
                visit = new boolean[4];
                change = new ArrayList<>();
                que.add(num);
                visit[num] = true;
                 
                cal(num);
                 
                turn();
                 
            }
            int sum = 0;
             
            for(int j=0;j<4;j++) {
                sum += (arr[j].get(0)*Math.pow(2, j));
            }
             
            System.out.println("#"+i+" "+sum);
             
        }
         
    }
 
}