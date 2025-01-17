import java.util.*;
import java.math.*;

public class snakebird {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int len = sc.nextInt();
		int[] fru = new int[n];
		
		for(int i=0;i<n;i++) {
			fru[i] = sc.nextInt();
		}
		
		for(int i=0;i<n;i++) {
			if(fru[i]<=len&&fru[i]>0) {
				fru[i] = -1;
				i=-1;
				len++;
			}
		}

		System.out.println(len);
		
	}

}
