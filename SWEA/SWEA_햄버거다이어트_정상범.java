import java.util.Scanner;

public class 햄버거다이어트4 {

    // nextPermutation 함수: 사전 순으로 다음 순열을 구함
    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;

        // 뒤에서부터 탐색해 내림차순이 아닌 곳 찾기
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }

        // 마지막 순열이면 false 리턴
        if (i == 0) {
        	return false;
        }

        int j = arr.length - 1;
        
        // arr[i-1]보다 큰 값을 뒤에서부터 탐색
        while (arr[i - 1] >= arr[j]) {
            j--;
        }

        // 두 값 교환
        swap(arr, i - 1, j);

        // i부터 끝까지 오름차순으로 정렬
        reverse(arr, i, arr.length - 1);

        return true;
    }

    // 배열에서 두 인덱스의 값을 바꾸는 함수
    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // 배열의 일부분을 역순으로 바꾸는 함수
    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }

    // 햄버거 다이어트 계산 함수
    static int cal(int[][] food, int calLimit) {
        int n = food.length;
        int[] index = new int[n];

        // 인덱스 배열 초기화
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }

        int maxScore = 0;

        // 모든 순열에 대해 처리
        do {
            int totalCalories = 0;
            int totalScore = 0;

            // 현재 순열에 대해 점수와 칼로리 계산
            for (int i : index) {
                int score = food[i][0];
                int calories = food[i][1];

                // 칼로리 제한을 넘지 않으면 점수와 칼로리 추가
                // ex) 13245 => 1,3번을 넣었을 때 넘지않으면 ok
                // but 2번까지 넣었을 때 오버하면 break;
                if (totalCalories + calories <= calLimit) {
                    totalScore += score;
                    totalCalories += calories;
                } else {
                    break;
                }
            }

            // 최대 점수 갱신
            maxScore = Math.max(maxScore, totalScore);

        } while (nextPermutation(index)); // 다음 순열로 진행

        return maxScore;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        // 사용자로부터 입력 받기
        int tcase = sc.nextInt();
        for(int i=1;i<=tcase;i++) {
        	int n = sc.nextInt();  // 재료의 개수

            int calLimit = sc.nextInt();  // 칼로리 제한

            int[][] food = new int[n][2];  // 재료 배열

            // 각 재료의 점수와 칼로리 입력 받기
            for (int j = 0; j < n; j++) {
                food[j][0] = sc.nextInt();  // 점수
                food[j][1] = sc.nextInt();  // 칼로리
            }

            // 최대 점수 계산
            int result = cal(food, calLimit);
         // 결과 출력
            System.out.println("#"+i+" " + result);
        }

    }
}
