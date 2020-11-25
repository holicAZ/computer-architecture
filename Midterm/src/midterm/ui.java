package midterm;
import midterm.solve;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

public class ui extends JFrame implements ActionListener, ItemListener{		
		int bit = 0;
		JButton btn;
		TextArea printArea;
		TextField text;
		
		JRadioButton radio[] = new JRadioButton[4];
		String radio_name[] = {"bit_8", "bit_16", "bit_32","bit_64"};
		
		JLabel label = new JLabel("<html>1. 비트를 선택해 주세요.<br>"
				+ "2. 비트에 맞는 음의 정수를 입력해 주세요. <br>"
				+ "3. 오류가 난다면 다시 입력해 주세요. <br>"
				+ "4. 종료를 원한다면 우측 상단의 x키를 눌러주세요. <html>");
		
		public ui() {
			setLayout(new FlowLayout());
			JPanel panel=new JPanel();
			JPanel labelpanel = new JPanel();
			JPanel radiopanel=new JPanel();
			ButtonGroup group = new ButtonGroup();
			setLocationRelativeTo(null);
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //close
			setTitle("컴퓨터 구조 중간과제-1 1723904 안정모");
			
			for(int k=0;k<radio.length;k++) {
				radio[k] = new JRadioButton(radio_name[k]);
				group.add(radio[k]);
				radiopanel.add(radio[k]);
				radio[k].addItemListener(this);
				radio[k].setBackground(Color.DARK_GRAY);
				radio[k].setForeground(Color.WHITE);
			}
			text = new TextField(30);
			btn = new JButton("Run");
			printArea = new TextArea(15,80);
			
			printArea.setFont(new Font("맑은고딕",Font.BOLD,12));
			printArea.setForeground(Color.WHITE);
			printArea.setBackground(Color.GRAY);
			radiopanel.setBackground(Color.DARK_GRAY);
			panel.setBackground(Color.DARK_GRAY);
			btn.setBackground(Color.BLACK);
			btn.setForeground(Color.WHITE);
			label.setFont(new Font("맑은고딕",Font.BOLD,13));
			add(radiopanel);
			add(panel);
			panel.add(text);
			panel.add(btn);
			labelpanel.add(label);
			add(labelpanel);
			text.addActionListener(this);
			
			add(printArea);
			btn.addActionListener(this);
			setSize(700,420);
			setVisible(true);
			
		}
		
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getSource().equals(radio[0])) {
				bit = 8;
			}
			else if(e.getSource().equals(radio[1])) {
				bit = 16;
			}
			else if(e.getSource().equals(radio[2])) {
				bit = 32;
			}
			else if(e.getSource().equals(radio[3])) {
				bit = 64;
			}
		}
		
		public void actionPerformed(ActionEvent e) {
			printArea.setText("");
			
			long target;
				
				try {
					
					target = Long.parseLong(text.getText());

					if(target >= 0 || bit == 0) {
						printArea.setText("*오류 : bit선택과 입력한 음수를 확인해 주세요");
						return;
					}
					
					solve solve = new solve(target, bit);
					long [] result1, result2, result3;
					result1 = solve.func1();
					result2 = solve.func2();
					result3 = solve.func3();
					
					printArea.append("부호 크기 출력 : ");
				    for(long a: result1) printArea.append(a+"");
				    printArea.append("\n 1 의 보수 출력 : ");
				    for(long a: result2) printArea.append(a+"");
				    printArea.append("\n 2 의 보수 출력 : ");
				    for(long a: result3) printArea.append(a+"");
					}
				
				catch(Exception E) {
					printArea.setText("*오류 : bit선택과 입력한 음수를 확인해 주세요");
				}
			}
		
	public static void main(String[] args) {
		ui s = new ui();
		
	}
	
}
