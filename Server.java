package ChatApp;
import javax.swing.border.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class Server implements ActionListener{
		JTextField text;
		JPanel a1;
		static DataOutputStream dout;
		static Box vertical = Box.createVerticalBox();
	    static JFrame f=new JFrame();
		Server(){
			f.setLayout(null);
			JPanel p1=new JPanel();
			p1.setBackground(new Color(7,94,84));
			p1.setBounds(0,0,450,70);
			p1.setLayout(null);
		    f.add(p1);
			ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("profiles/R.png"));
			Image i2=i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			ImageIcon i3=new ImageIcon(i2);
			JLabel back=new JLabel(i3);
			back.setBounds(5,20,30,30);
			p1.add(back);
			
			back.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ae) {
					System.exit(0);
				}
			});
			
			ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("profiles/chinnodu1.png"));
			Image i5=i4.getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT);
			ImageIcon i6=new ImageIcon(i5);
			JLabel profile=new JLabel(i6);
			profile.setBounds(40,15,60,40);
			p1.add(profile);
			
			ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("profiles/vid.png"));
			Image i8=i7.getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT);
			ImageIcon i9=new ImageIcon(i8);
			JLabel video=new JLabel(i9);
			video.setBounds(300,15,40,40);
			p1.add(video);
			
			ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("profiles/tel1.png"));
			Image i11=i10.getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT);
			ImageIcon i12=new ImageIcon(i11);
			JLabel phone=new JLabel(i12);
			phone.setBounds(360,15,40,40);
			p1.add(phone);
			ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("profiles/more3.png"));
			Image i14=i13.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon i15=new ImageIcon(i14);
			JLabel more=new JLabel(i15);
			more.setBounds(420,15,5,40);
			p1.add(more);
			
			JLabel name=new JLabel("Nag");
			JLabel name1=new JLabel("👑");
			name.setBounds(110,18,100,25);
			name1.setBounds(147,18,100,25);
			name.setForeground(Color.WHITE);
			name1.setForeground(Color.YELLOW);
			name.setFont(new Font("ITALIC",Font.BOLD,20));
			name1.setFont(new Font("SAN_SERIF",Font.BOLD,20));
			p1.add(name);
			p1.add(name1);
			
			JLabel status=new JLabel("online");
			status.setBounds(110,40,100,20);
			status.setForeground(Color.WHITE);
			status.setFont(new Font("SAN_SERIF",Font.BOLD,10));
			p1.add(status);
			
//			 a1=new JPanel();
			 
			 
			 a1 = new JPanel();
			 JScrollPane scrollPane = new JScrollPane(a1);
			 scrollPane.setBounds(5, 73, 440, 580);
			 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			 f.add(scrollPane);
			 
			 
			 
			 JViewport viewport = scrollPane.getViewport();
			 Rectangle rect = a1.getVisibleRect();
			 rect.y = a1.getHeight() - rect.height;
     		 viewport.scrollRectToVisible(rect);
			 
//			 JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
//			 verticalScrollBar.setValue(verticalScrollBar.getMaximum());
			 
			 
//			a1.setBounds(5,73,440,580);
//			f.add(a1);
			
			 text=new JTextField();
			text.setBounds(5,655,310,40);
			text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
			f.add(text);
			
			JButton send=new JButton("➡️");
			send.setBounds(320,655,125,40);
			send.setBackground(new Color(7,94,84));
			send.setForeground(Color.WHITE);
			send.addActionListener(this);
			send.setFont(new Font("SAN_SERIF",Font.BOLD,40));
			f.add(send);
			
			
			f.setSize(450,700);
			f.setLocation(200,50);
			f.setUndecorated(true);
			f.getContentPane().setBackground(Color.white);
			f.setVisible(true);
		}
		
		public void actionPerformed(ActionEvent ae) {
			
			try {
			String out = text.getText();
			JLabel output = new JLabel(out);
			
			JPanel p2 = formatLabel(out);
//			System.out.println(out);
			
			a1.setLayout(new BorderLayout());
			
			JPanel right = new JPanel(new BorderLayout());
			right.add(p2, BorderLayout.LINE_END);
			vertical.add(right);
			vertical.add(Box.createVerticalStrut(15));
			a1.add(vertical,BorderLayout.PAGE_START);
			
			dout.writeUTF(out);
			text.setText("");
			f.repaint();
			f.invalidate();
			f.validate();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static JPanel formatLabel(String out) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
			output.setFont(new Font("Tahoma",Font.PLAIN,16));
			output.setBackground(new Color(37,211,102));
			output.setOpaque(true);
			output.setBorder(new EmptyBorder(15,15,15,15));
			panel.add(output);
			
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
			
			JLabel time = new JLabel();
			time.setText(sdf.format(cal.getTime()));
			panel.add(time);
			return panel;
		
		}
	public static void main(String args[]) {
	     new Server();
	     try {
	    	 ServerSocket skt = new ServerSocket(6001);
	    	 while(true) {
	    		 Socket s = skt.accept();
	    		 DataInputStream din = new DataInputStream(s.getInputStream());
	    		  dout = new DataOutputStream(s.getOutputStream());
	    		 while(true) {
	    			 String msg = din.readUTF();
	    			 JPanel panel = formatLabel(msg);
	    			 
	    			 JPanel left = new JPanel(new BorderLayout());
	    			 left.add(panel,BorderLayout.LINE_START);
	    			 vertical.add(left);
	    			 f.validate();
	    			 
	    		 }
	    	 }
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }
//		System.out.println("hi nag");
	}
	}
