package booth;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import booth.solve;
import booth.solve2;

import java.util.LinkedList;
import java.util.Queue;

public class gui extends JFrame implements ActionListener, ItemListener{
   int bit=4;
   int type= 100; // 100 이면 booth , 200 이면 2-shift
  
   GridBagLayout GBL;
   JButton btn;
   JPanel panelsc;
   TextArea printArea;
   TextArea signArea;
   TextField text1;
   TextField text2;
   JLabel nlabel = new JLabel("n : ",JLabel.RIGHT);
   JLabel mlabel = new JLabel("m : ",JLabel.RIGHT);
   JRadioButton selectradio[] = new JRadioButton[2];
   String select_name[] = {"booth","2-shift"};
   JRadioButton radio[] = new JRadioButton[5];
   String radio_name[] = {"bit_4","bit_8","bit_16","bit_32","bit_64"};
   
   
   JLabel label = new JLabel("<html>1. 비트와 계산방식을 선택해 주세요.<br>"
         + "2. 비트에 맞는 정수를 두 개 입력해주세요. <br>"
         + "3. 오류가 난다면 다시 입력해 주세요. <br>"
         + "4. 종료를 원한다면 우측 상단의 x키를 눌러주세요. <html>",JLabel.CENTER);

   public gui() {
      GBL = new GridBagLayout();
      panelsc = new JPanel();
      JPanel select = new JPanel();
      ButtonGroup selectgroup = new ButtonGroup();
      for(int i=0;i<selectradio.length;i++) {
         selectradio[i] = new JRadioButton(select_name[i]);
         selectradio[i].setFont(new Font("맑은고딕",Font.BOLD,16));
         selectgroup.add(selectradio[i]);
         select.add(selectradio[i]);
         selectradio[i].addItemListener(typeaction);
      }
      setLayout(GBL);
      Label blank = new Label("                                                ");
      JPanel panel[] = new JPanel[5];
      for(int i=0;i<5;i++) {
         panel[i] = new JPanel();
         
      }
      
      ButtonGroup group = new ButtonGroup();
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("booth-algorithm 1723904 안정모");
      
      for(int k=0;k<radio.length;k++) {
         radio[k] = new JRadioButton(radio_name[k]);
         group.add(radio[k]);
         radio[k].setFont(new Font("맑은고딕",Font.BOLD,15));
         panel[k].add(radio[k]);
         //radiopanel.add(radio[k]);
         radio[k].addItemListener(this);
      }
      text1 = new TextField(10);
      text2 = new TextField(10);
      btn = new JButton("Run");
      printArea=new TextArea(15,90);
      signArea=new TextArea(15,10);
      
      
      selectradio[0].setSelected(true);
      radio[0].setSelected(true);
      
      text1.setFont(new Font("맑은고딕",Font.BOLD,20));
      text2.setFont(new Font("맑은고딕",Font.BOLD,20));
      nlabel.setFont(new Font("맑은고딕",Font.BOLD,30));
      mlabel.setFont(new Font("맑은고딕",Font.BOLD,30));
      printArea.setFont(new Font("맑은고딕",Font.BOLD,12));
      printArea.setForeground(Color.WHITE);
      printArea.setBackground(Color.GRAY);
      signArea.setFont(new Font("맑은고딕",Font.BOLD,12));
      signArea.setForeground(Color.WHITE);
      signArea.setBackground(Color.GRAY);
      btn.setBackground(Color.BLACK);
      btn.setForeground(Color.WHITE);
      label.setFont(new Font("맑은고딕",Font.BOLD,13));
      printArea.setEditable(false);
      for(int k=0;k<radio.length;k++) {
         
         setGlayout(panel[k],true,0,k,1,1);
      }
      
      setGlayout(select,true,1,0,19,1);
      //blank.setBackground(Color.BLACK);
      setGlayout(blank,true,1,1,5,2);
      setGlayout(nlabel,true,6,1,2,1);
      setGlayout(text1,false,8,1,1,1);
      setGlayout(mlabel,true,6,2,2,1);
      setGlayout(text2,false,8,2,1,1);
      setGlayout(btn,true,9,1,1,2);
      //setGlayout(panel,3,0,2,1);
      setGlayout(label,true,1,3,19,3);
      //setGlayout(signArea,false,1,6,1,5);
      setGlayout(printArea,true,0,6,20,5);
      
      this.pack();
      setSize(1000,500);
      setVisible(true);
      
      
      
      btn.addActionListener(this);
      text1.addActionListener(this);
      text2.addActionListener(this);
   }
   
   
   
   @Override
   public void itemStateChanged(ItemEvent e) {
      if(e.getSource().equals(radio[0])) {
         bit = 4;
      }
      else if(e.getSource().equals(radio[1])) {
         bit = 8;
      }
      else if(e.getSource().equals(radio[2])) {
         bit = 16;
      }
      else if(e.getSource().equals(radio[3])) {
         bit = 32;
      }
      else if(e.getSource().equals(radio[4])) {
         bit = 64;
      }
      
   }  
   

   @Override
   public void actionPerformed(ActionEvent e) {
      printArea.setText("");
      Vector<Long> v = new Vector<Long>();
      Vector<Long> m = new Vector<Long>();
      long target1, target2;
      solve solve;
      solve2 solve2;
         try {
            
            target1 = Long.parseLong(text1.getText());
            target2 = Long.parseLong(text2.getText());
            
            if(type==100) {
               solve = new solve(bit, target1, target2);
               v = solve.func();
               
               for(int k=-1;k<bit/7;k++)
                  printArea.append("          ");
                 for(int k=0;k<bit*2;k++) { // n, m 출력
                    if(k==bit) {
                       printArea.append("\n");
                       for(int j=-1;j<bit/7;j++)
                          printArea.append("          "); // 14
                    }
                    printArea.append(v.get(k)+"");
                    if(k>=bit)
                       m.add(v.get(k)); // 부호 파악을 위해 m 값 저장   
                 }
                 m.add((long)0);
                 
                 printArea.append("\n");
                 for(int c=0;c<bit*2-bit+4;c++)
                    printArea.append("ㅡ");
                 printArea.append("\n");
                 printArea.append("=  ");
                 
                 int cnt =0; int sign_cnt =2; int checkshift=0; int breakpoint = 0; int check=0; int q=bit;
                 for(int k=bit*2;k<v.size();k++) {
                    cnt++;
                    if(sign_cnt%2==1) { // =을 찍고 나면 bit*2 자리 모두 출력
                       if(check<bit-checkshift||check>=bit*2-checkshift) {
                          printArea.append("-");
                       }
                       else {
                          if(cnt==bit+1) {
                              printArea.append("  ");
                           }
                          printArea.append(v.get(k)+"");
                       }
                       check++;
                       if(cnt==bit*2) {
                    	  
                          printArea.append("   ("+m.get(q-1)+m.get(q)+")");
                          q--;
                       }
                    }
                    else {
                       if(cnt==bit+1) {
                          printArea.append("  ");
                       }
                       printArea.append(v.get(k)+"");
                    }
                    
                    if(cnt==bit*2) {
                       breakpoint++;
                       if(breakpoint==bit*2+1) break;
                       
                       cnt=0;
                       check=0;
                       if(sign_cnt%2==1) {
                          printArea.append("\n");
                          for(int c=0;c<bit*2-bit+4;c++)
                                printArea.append("ㅡ");
                            printArea.append("\n");
                           printArea.append("=  ");
                           checkshift++;
                           sign_cnt++;
                        }
                        else {
                           printArea.append("\n");
                           if(m.get(bit-checkshift)==0 && m.get(bit-checkshift-1)==1) {

                              printArea.append("-  ");
                           }
                           else {
                              
                              
                              printArea.append("+  ");
                           }
                           
                           sign_cnt++;
                        }
                    }
                 }
            }
            else if(type==200) {
               solve2 = new solve2(bit,target1,target2);
               v = solve2.func();
               
               for(int k=-1;k<bit/7;k++)
                     printArea.append("          ");
               
                    for(int k=0;k<bit*2;k++) { // n, m 출력
                       if(k==bit) {
                          printArea.append("\n");
                          for(int j=-1;j<bit/7;j++)
                             printArea.append("          "); // 14
                       }
                       printArea.append(v.get(k)+"");
                    }
                    
                    printArea.append("\n");
                    for(int c=0;c<bit*2-bit+4;c++)
                       printArea.append("ㅡ");
                    printArea.append("\n");
                    printArea.append("=   ");
            
                    int cnt =0; int sign_cnt =2; int breakpoint = 0; boolean check=false;
                    for(int k=bit*2;k<v.size()-2*bit;k++) {
                       cnt++;
                       
                       printArea.append(v.get(k)+"");
                       if(cnt==bit) 
                           printArea.append("  ");
                       
                       if(cnt==bit*2) { // 8개 출력하면 줄이 바뀐다. 
                    	   breakpoint++;
                    	   if(breakpoint==2*bit+bit+1) break;
                    	   
                    	   printArea.append("\n");
                    	   
                    	   if(check) {
                    		   for(int c=0;c<bit*2-bit+2;c++)
                    			   printArea.append("ㅡ"); 
                    		   printArea.append("\n");
                    		   check=false;
                    	   }
                    	   
                    	   if(sign_cnt%3==2) { // 나머지가 1이면 더하기 출력, 0이면 결과값 출력
                        	   printArea.append("+   ");  
                        	   check=true;
                           }
                           
                           else if(sign_cnt%3==0){
                        	  printArea.append("=   ");
                           }
                           
                           else if(sign_cnt%3==1) {
                        	   printArea.append(">> "); 
                              
                           }
                    	   sign_cnt++;
                    	   cnt=0;
                       }    
                  }
                 
                 cnt=0;
                 printArea.append("\n 최종 결과 :  ");
                 for(int k=v.size()-2*bit;k<v.size();k++) {
                	 cnt++;
                	 printArea.append(v.get(k)+"");
                	 if(cnt==bit) 
                         printArea.append("  ");
                 }
            }
         }
         catch(Exception E) {
            printArea.setText("*오류 : bit선택과 입력한 정수를 확인해 주세요");
         }

      
   }
   public void setGlayout(Component obj,boolean b, int x, int y, int w, int h) {
      GridBagConstraints constraint = new GridBagConstraints();
      if(b)constraint.fill = GridBagConstraints.BOTH;
      constraint.gridx = x;
      constraint.gridy = y;
      constraint.gridwidth = w;
      constraint.gridheight = h;
      constraint.ipadx = 2;
      constraint.ipady = 2;
      add(obj,constraint);
      
   }
   public static void main(String[] args) {
      gui s = new gui();
      
   }
   
   ItemListener typeaction = new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
         if(e.getSource().equals(selectradio[0])) {
            type = 100;
         }
         else if(e.getSource().equals(selectradio[1])) {
            type = 200;
         }
         
      }  
   };
}