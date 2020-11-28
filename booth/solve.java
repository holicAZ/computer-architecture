package booth;
import java.util.*;

public class solve {
 /* 1. 4 8 16 32 64 bit
 * 2. 입력되는 숫자는 다를 수 있다.
 * 3. 각 단계별 승수, 피승수, 부분곱의 합 상태가 출력되게 할 것*/
	Vector<Long> v = new Vector<Long>(); 
	
	Queue<Long> nque = new LinkedList();
	Queue<Long> mque = new LinkedList();
	ArrayList<long[]> aa = new ArrayList<long[]>();
	int bit;
	long n,m; // n 피승수 m 승수
	
	public solve(int bit, long n, long m) {
		this.n = n;
		this.m = m;
		this.bit = bit;
	}
	
	public Queue compto2(long z) {
		   Queue<Long> que = new LinkedList();  
		   if(z<0) {
	            z = (long)z*(-1) ;
	         }
	         
	         while(z != 1) { // n의 값이 1이 되면 종료
	            que.add(z%2);
	            z = z / 2;
	         }
	         que.add(z);
	         
	         return que;
	         // 부호와 크기 구현 부분 end
	      }
	
	public Vector func() {
	long [] n_0 = new long[bit*2];
	long [] n_22 = new long[bit*2];
	long [] comp2 = new long[bit*2];
	long [] n_2 = new long[bit];
	long [] m_2 = new long[bit];
	long [] result = new long[bit*2];
	
	Arrays.fill(result,0);
	n_2[0] =  m_2[0] = 0; // 부호 비트 0으로 초기화
	

	/* change to binary*/
	// n--------------------------------------------------------------
	nque = compto2(n);
	int i=bit-1;
	while(!nque.isEmpty()) {
		n_2[i] = nque.poll();
		i--;	
	}
	if(n<0) { // 음수면 2의 보수로 변환
		n_2[0] = 1;
		
		for(int k=1; k<bit; k++) {
			if(n_2[k] == 1) { // sign을 표현하는 MSB를 제외한 나머지를 0->1 1->0 해준다
				n_2[k] = 0;
			}
			else
				n_2[k] = 1;
			
		}
		
	for(int k=bit-1;k>1;k--) {
		if(n_2[k] == 0) { // 1의 보수에 +1을 해준다 캐리가 발생하지 않으면 STOP
			n_2[k] = 1;
			break;
		}
		else
			n_2[k] = 0;
	}
	}
	// n--------------------------------------------------------------
	// m--------------------------------------------------------------
	mque = compto2(m);
	i=bit-1;
	while(!mque.isEmpty()) {
		m_2[i] = mque.poll();
		i--;	
	}
	if(m<0) { // 음수면 2의 보수로 변환
		m_2[0] = 1;
		
		for(int k=1; k<bit; k++) {
			if(m_2[k] == 1) { // sign을 표현하는 MSB를 제외한 나머지를 0->1 1->0 해준다
				m_2[k] = 0;
			}
			else
				m_2[k] = 1;
			
		}
	
	for(int k=bit-1;k>1;k--) {
		if(m_2[k] == 0) { // 1의 보수에 +1을 해준다 캐리가 발생하지 않으면 STOP
			m_2[k] = 1;
			break;
		}
		else
			m_2[k] = 0;
	}
	}
	// m--------------------------------------------------------------
	
	// 연산 결과 출력 -----------------------------------------------------
	for(int k=0;k<=bit;k++) 
		System.out.print(" ");
	
	save(n_2);
	save(m_2);
	save(result);
	for(long a:n_2) {
		System.out.print(a);
	}
	System.out.println();
	
	for(int k=0;k<=bit;k++) System.out.print(" ");
	for(long a:m_2) System.out.print(a);
	System.out.println();
	
	for(int k=0;k<bit*2+1;k++) System.out.print("-");
	System.out.println();
	for(int k=0;k<bit;k++) System.out.print(result[k]);
	System.out.print(" ");
	
	for(int k=bit;k<bit*2;k++) System.out.print(result[k]);
	System.out.println();
	
	/* change to binary*/
	for(int k=0;k<bit;k++) {
		if(n_2[k] == 1) n_22[k+bit] = 1;
	}
	// m:승수 맨뒤에 추가비트 0 추가
	int check = 0;
	
	for(int k=bit-1;k>=0;k--) { // 승수에 추가비트를 계산 LSB자리가 0이면 00 1이면 10으로 시작
		if(k==bit-1) {
			if(m_2[k]==0) {
				print(n_0);
				for(int j=1;j<bit*2;j++) { // 00 이면 shift 
					n_22[j-1]=n_22[j];
				}
				for(int j=bit*2-1;j>=bit*2-1-check;j--) {
					n_22[j] = 0;	
				}
				
			}
			else {
				print(n_22);
				comp2 = n_22.clone();
				for(int j=0;j<bit-check;j++) {
					comp2[j] = comp2[bit-check]; // MSB 부호 확장	
				}
				comp2 = change(comp2);
				calc(result,comp2);
				for(int j=1;j<bit*2;j++) { // 10 이면 빼고 shift
					n_22[j-1]=n_22[j];
				}
				
				for(int j=bit*2-1;j>=bit*2-1-check;j--) {
					n_22[j] = 0;	
				}
			}
			
		}
		else { 
			if(m_2[k]==m_2[k+1]) { // 11 or 00
				print(n_0);
				for(int j=1;j<bit*2;j++) { // 같으면 shift 
					n_22[j-1]=n_22[j];
				}
				
				for(int j=bit*2-1;j>=bit*2-1-check;j--) {
					n_22[j] = 0;	
				}
				
			}
			else if(m_2[k]==1 && m_2[k+1]==0) { // 10 이면 빼고 shift
				print(n_22);
				comp2 = n_22.clone();
				for(int j=0;j<bit-check;j++) {
					comp2[j] = comp2[bit-check]; // MSB 부호 확장	
				}
				comp2 = change(comp2);
				calc(result,comp2);
				for(int j=1;j<bit*2;j++) { // 10 이면 빼고 shift
					n_22[j-1]=n_22[j];
				}
				
				for(int j=bit*2-1;j>=bit*2-1-check;j--) {
					n_22[j] = 0;	
				}
			}
			else { // 01 이면 더하고 shift
				print(n_22);
				comp2 = n_22.clone();
				for(int j=0;j<bit-check;j++) {
					comp2[j] = comp2[bit-check]; // MSB 부호 확장	
				}
				calc(result,comp2);
				for(int j=1;j<bit*2;j++) { 
					n_22[j-1]=n_22[j];
				}
				
				for(int j=bit*2-1;j>=bit*2-1-check;j--) {
					n_22[j] = 0;	
				}
			}
		}
		
		for(int j=0;j<bit*2+1;j++) System.out.print("-");
		System.out.println();
		for(int j=0;j<bit;j++) System.out.print(result[j]);
		System.out.print(" ");
		
		for(int j=bit;j<bit*2;j++) System.out.print(result[j]);
		System.out.println();
		check++;
		save(result);
	}
	
	
	return v;
	}
	// 연산 결과 출력 -----------------------------------------------------	
	
	public long[] calc(long result[],long n_22[]) { // 더하기 연산
		for(int j=bit*2-1,carry = 0;j>=0;j--) {
			if(carry==1) {
				if(result[j]==1&&n_22[j]==1) {
					carry=1;
					result[j] = 1;
				}
				else if (result[j]==0&&n_22[j]==0) {
					carry=0;
					result[j] = 1;
				}
				else {
					carry=1;
					result[j]=0;
				}
			}
			else { // carry ==0
				if(result[j]==1&&n_22[j]==1) {
					carry=1;
					result[j] = 0;
				}
				else if(result[j]==0&&n_22[j]==0) {
					carry=0;
					result[j] = 0;
				}
				else {
					carry=0;
					result[j]=1;
				}
			}
		}
		return result;
	}
	
	public long[] change(long[] a) {
		for(int i=0;i<a.length;i++) {
			if(a[i]==1) a[i] = 0;
			else a[i] = 1;
		}
		for(int i=a.length-1;i>=0;i--) {
			if(a[i]==0) {
				a[i]=1;
				break;
			}
			else
				a[i]=0;
		}
		return a;
	}
	
	public void print(long[] a) {
		save(a);
		for(int i=0;i<a.length/2;i++) {
			
			System.out.print(a[i]);
		}
		System.out.print(" ");
		for(int i=a.length/2;i<a.length;i++) {
			System.out.print(a[i]);
		}
		System.out.println();
	}
	public void save(long[] a) {
		for(long x:a)
			v.add(x);
	}
}