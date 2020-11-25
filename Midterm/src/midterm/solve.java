package midterm;

import java.util.LinkedList;
import java.util.Queue;

public class solve {
	private
		long n;
		int bit;
		long change_n; // 입력받은 음의 정수 -> 양의 정수
		long  [] ss;	// 부호와 크기
		long [] Comp1; // 1의 보수
		long [] Comp2; // 2의 보수
		Queue<Long> que = new LinkedList();
		
	public solve(long n, int bit) {
		this.n = n;
		this.bit = bit;
		switch (bit) {
		case 8 :
			init_bit_8();
			break;
			
		case 16 :
			init_bit_16();
			break;
			
		case 32 :
			init_bit_32();
			break;
			
		case 64 :
			init_bit_64();
			break;
		}
	}
	
	public void init_bit_8() {
		ss = new long[8];
		Comp1 = new long[8]; 
		Comp2 = new long[8];
		ss[0] = Comp1[0] = Comp2[0] = 1;
		change_n = (long)n*(-1);
	}
	
	public void init_bit_16() {
		ss = new long[16];
		Comp1 = new long[16]; 
		Comp2 = new long[16];
		ss[0] = Comp1[0] = Comp2[0] = 1;
		change_n = (long)n*(-1);
	}
	
	public void init_bit_32() {
		ss = new long[32];
		Comp1 = new long[32]; 
		Comp2 = new long[32];
		ss[0] = Comp1[0] = Comp2[0] = 1;
		change_n = (long)n*(-1);
	}
	
	public void init_bit_64() {
		ss = new long[64];
		Comp1 = new long[64]; 
		Comp2 = new long[64];
		ss[0] = Comp1[0] = Comp2[0] = 1;
		change_n = (long)n*(-1);
	}
	
	public long[] func1() {		
		// 부화와 크기 구현 부분 start
		while(change_n != 1) { // n의 값이 1이 되면 종료
			que.add(change_n%2);
			change_n = change_n / 2;
		}
		que.add(change_n);
		
		
		int i=bit-1;
		while(!que.isEmpty()) {
			ss[i] = que.poll();
			i--;
		}
		
		 System.out.print("부호 크기 출력 : ");
		
		for(long a: ss) System.out.print(a);
		System.out.print("\n");
		return ss;
		// 부호와 크기 구현 부분 end
	
	}
	
	public long[] func2() {
		// 1의 보수 구현 부분 start
		
		System.out.print("1의 보수 출력 : ");
		for(int k=1; k<bit; k++) {
			if(ss[k] == 1) { // sign을 표현하는 MSB를 제외한 나머지를 0->1 1->0 해준다
				Comp1[k] = 0;
			}
			else
				Comp1[k] = 1;
			
		}
		for(long a:Comp1) System.out.print(a);
		System.out.print("\n");
		return Comp1;
		
		// 1의 보수 구현 부분 end
	}
	
	public long[] func3() {
		// 2의 보수 구현 부분 start
		
		int stop=0;
		System.out.print("2의 보수 출력 : ");
		for(int k=bit-1;k>1;k--) {
			if(Comp1[k] == 0) { // 1의 보수에 +1을 해준다 캐리가 발생하지 않으면 STOP
				Comp2[k] = 1;
				stop=k;
				break;
			}
			else
				Comp2[k] = 0;
		}
		
		
		for(int k=1;k<stop;k++) Comp2[k] = Comp1[k];
		for(long a:Comp2) System.out.print(a);
		
		System.out.print("\n");
		return Comp2;
		
		// 2의 보수 구현 부분 end
	}
}


