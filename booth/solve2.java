package booth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class solve2 {
   Vector<Long> v = new Vector<Long>(); 
   Queue<Long> nque = new LinkedList();
   Queue<Long> mque = new LinkedList();
    ArrayList<long[]> aa = new ArrayList<long[]>();
   int bit;
   long n,m;
   solve2(int bit, long n, long m){
      this.bit = bit;
      this.n=n;
      this.m=m;
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
      
      // n--------------------------------------------------------------
      // m--------------------------------------------------------------
      mque = compto2(m);
      i=bit-1;
      while(!mque.isEmpty()) {
         m_2[i] = mque.poll();
         i--;   
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

      
      int check = 0;
      
      for(int k=bit-1;k>=0;k--) { 
         if(check==bit) break;
         Arrays.fill(comp2, 0);   
         
         if(m_2[k]==1) {
           // 0이면 all 0, 1이면 n_2 그대로 출력
            for(int x=0;x<bit;x++) {
               comp2[x] = n_2[x];
            }
            
            print(comp2);
            calc(result,comp2);
            
            for(int j=0;j<bit*2+1;j++) System.out.print("-");
                 System.out.println();
            
            
            print(result);
            
         }
         else { // 0
            print(n_0);
            
            for(int j=0;j<bit*2+1;j++) System.out.print("-");
                  System.out.println();
            
            print(result);
         }
         
         for(int j=bit*2-1;j>0;j--) {  // 오른쪽 쉬프트 
               result[j]=result[j-1];
            }
           result[0] = 0;   
            
           print(result); // 오른쪽 쉬프트 후 출력;
           check++;
           
           if(k==0) {
          	 // 마지막 결과는 입력 정수의 부호에 따라 음수면 2의보수를 취해준다.
          	 if((n<0 && m<0) || (n>0 && m>0)) {
          		print(result); // 마지막 결과값 입력
          	 }
          	 else if(n<0||m<0) { // 둘중에 하나가 음수면
  
               for(int q=0; q<bit*2; q++) {
                  if(result[q] == 1) { // sign을 표현하는 MSB를 제외한 나머지를 0->1 1->0 해준다
                     result[q] = 0;
                  }
                  else
                     result[q] = 1;
                  
               }
               
               for(int q=bit*2-1;q>=0;q--) {
              	 if(result[q] == 0) { // 1의 보수에 +1을 해준다 캐리가 발생하지 않으면 STOP
              		 result[q] = 1;
              		 break;
              	 }
              	 else
              		 result[q] = 0;
               }
               print(result); // 마지막 결과값 입력
            }
          	 
           }
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