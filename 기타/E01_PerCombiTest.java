/*
 * 1. 순열/조합/부분집합 개념 및 예시를 들어 설명하세요.
 *	(1) 순열 
 *	- 개념: 서로 다른 것들 중 몇 개를 뽑아서 한 줄로 나열하는 것(순서 있는 뽑기)
 *	- 예시: 서로 다른 n개 중 r개를 순서에 따라 택하는 순열 = nPr
 *
 *	(2) 조합
 *	- 개념: 서로 다른 n개의 원소 중 r개를 순서 없이 골라낸 것
 *	- 예시: 서로 다른 n개 중 r개를 택하는 조합 = nCr
 *
 *	(3) 부분집합
 *	- 개념: 집합에 포함된 원소들을 선택
 *	- 예시: N개의 원소들 중 1개를 선택하는 경우부터 N개를 선택하는 경우까지의 조합(부분집합의 개수 = 2^n)
 *
 * 2. 순열/조합/부분집합을 이용하여 아래의 코드를 구현하세요.
 *
 */
public class E01_PerCombiTest {
	static int[] num= {1,2,3};   // num={1,2,3,4}
	static int N=2;              // N=2, N=3
	static int sCount=0;
	
	//--------------------------------------------------------------------------------------
	private static void makePermutation(int toSelect,int[] selected, boolean[] visited) {
		if(toSelect==selected.length) {
			for(int i=0;i<selected.length;i++) {
				System.out.print(selected[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=0;i<num.length;i++) {
			if(!visited[i]) {
				visited[i]=true;
				selected[toSelect] = num[i];
				makePermutation(toSelect+1,selected,visited);
				visited[i] = false;
			}
		}
	}

	//--------------------------------------------------------------------------------------
	private static void makeCombination(int toSelect, int[] selected, int startIdx) {
		if(toSelect==selected.length) {
			for(int i=0;i<selected.length;i++) {
				System.out.print(selected[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=startIdx;i<num.length;i++) {
			selected[toSelect] = num[i];
			makeCombination(toSelect+1,selected,i+1);
		}
	}

	//--------------------------------------------------------------------------------------
	private static void powerSet(int cnt, boolean[] isSelected) {
		if(cnt==num.length) {
			for(int i=0;i<num.length;i++) {
				if(isSelected[i]) {
					System.out.print(num[i]+" ");
				}
			}
			System.out.println();
			sCount++;
			return;
		}
		// 선택 했거나, 안했거나 둘 중 하나! 
		isSelected[cnt]=true;
		powerSet(cnt+1,isSelected);
		
		isSelected[cnt]=false;
		powerSet(cnt+1,isSelected);
	}

	//--------------------------------------------------------------------------------------	
	public static void main(String[] args) {
//		1. num에서 N개를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
		System.out.println("-----순열-----");
		makePermutation(0, new int[N], new boolean[num.length]);
		
//		2. num에서 N개를 뽑아서 만들 수 있는 조합을 모두 출력하시오.
		System.out.println("-----조합-----");
		makeCombination(0, new int[N], 0);
		
//		3. num으로 구성할 수 있는 모든 부분집합을 출력하시오.			
		System.out.println("-----부분집합-----");
		powerSet(0, new boolean[num.length]);
		System.out.println("\n총 경우의 수: " + sCount);
	}
}

/*
-----순열-----
[1, 2]
[1, 3]
[2, 1]
[2, 3]
[3, 1]
[3, 2]
-----조합-----
[1, 2]
[1, 3]
[2, 3]
-----부분집합-----
{1 2 3 },{1 2 },{1 3 },{1 },{2 3 },{2 },{3 },{},
총 경우의 수: 8
*/