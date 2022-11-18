package telephonebookmaintaining;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class welcome extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	
	
	JPanel panel_2;
	JList<String> list;
	JTextArea textArea;
	JButton btnNewButton_3 ;
	JButton btnNewButton_4;
	JLabel lblNewLabel_13;
	
	Connection con;
	ResultSet rs = null;
	int ct=1,K=0;
	DefaultListModel<String> listModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome frame = new welcome();
					frame.setVisible(true);
					frame.setTitle("SOUND PHONE BOOK");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public String name,num,mail,loc,nn,work,add,code,dob;
	boolean isnum(String num)
	{
		int p=0;
		for(int i=0;i<num.length();i++)
		{
			if(Character.isDigit(num.charAt(i)))
			{
				p+=1;
			}
		}
		if(p==6)
			return true;
		else
			return false;
	}
	public welcome() {
		setFont(new Font("Cambria", Font.BOLD, 13));
		setTitle("SOUND PHONEBOOK");
		try{  
			Class.forName("org.postgresql.Driver");    
			con=DriverManager.getConnection("jdbc:postgresql://localhost/telephonebook","postgres","Sound@18");  
			 
			if(con!=null)
				System.out.println("connected");
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLUE);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(10, 24, 414, 237);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(null, new CompoundBorder(new LineBorder(new Color(0, 0, 0), 3), null)));
		tabbedPane.addTab("HOME", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "NEW CONTACT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 0, 0)));
		tabbedPane.addTab("CREATECONTACT", null, panel_1, null);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 3), null));
		tabbedPane.addTab("CONTACTLIST", null, panel_2, null);
		panel_2.setLayout(null);
		
		listModel = new DefaultListModel<String>();
		listModel.addElement("Name           "  + "    Number  ");
		
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		list.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), "CONTACT LIST", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		list.setBounds(10, 11, 163, 187);
		panel_2.add(list);
		
		textArea = new JTextArea();
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setEditable(false);
		textArea.setBounds(195, 40, 195, 154);
		panel_2.add(textArea);
		
		btnNewButton_3 = new JButton("SHOW INFO");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton_3.setBounds(183, 7, 104, 22);
		panel_2.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("HIDE INFO");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setVisible(false);
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton_4.setBounds(290, 6, 109, 23);
		panel_2.add(btnNewButton_4);
		//scrollBar.add(list);
		
		
	
		lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setForeground(new Color(255, 0, 0));
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_13.setBounds(10, 175, 217, 23);
		panel_1.add(lblNewLabel_13);
		
		JLabel lblNewLabel_2 = new JLabel("NAME:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(91, 11, 40, 23);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PHONE NUMBER:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_3.setBounds(28, 79, 103, 23);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("AREA CODE:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_4.setBounds(55, 45, 76, 23);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("PHONE NUMBER1:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5.setBounds(21, 113, 110, 23);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("NICK NAME:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_6.setBounds(243, 36, 76, 14);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("LOCATION:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_7.setBounds(248, 70, 71, 14);
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("BIRTHDAY:");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_8.setBounds(248, 104, 71, 14);
		panel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("JOB TITLE:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_9.setBounds(248, 138, 71, 14);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("EMAIL:");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_10.setBounds(85, 151, 46, 14);
		panel_1.add(lblNewLabel_10);
		
		textField = new JTextField();
		textField.setBounds(141, 12, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(141, 46, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(141, 80, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(141, 114, 86, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(141, 148, 86, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(323, 33, 80, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(323, 67, 80, 20);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(323, 101, 80, 20);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(323, 135, 80, 20);
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("SAVE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				name = null;
				name = textField.getText();
				num = null;
				num=textField_2.getText();
				mail =null;
				mail=textField_4.getText();
				loc = null;
				loc=textField_6.getText();
				nn = null;
				nn=textField_5.getText();
				work = null;
				work=textField_8.getText();
				add = null;
				add = textField_3.getText();
				code=null;
				code=textField_1.getText();
				dob = null;
				dob = textField_7.getText();
				if(name.equals("") && isnum(num)==false)
				{
					lblNewLabel_13.setText("Check Name and Number!");
				}
				else if(name.equals(""))
				{
					lblNewLabel_13.setText("Enter the contact name");
					textField_8.setText("");
				}
				else if(isnum(num)==false)
				{
					lblNewLabel_13.setText("Number should only contain 6 digits!!");
				}
				else if(name.equals("") == false && isnum(num))
				{
					lblNewLabel_13.setText("");
					try
					{
						PreparedStatement pt=con.prepareStatement("INSERT INTO public.contactinfo(name, mail_id, location, nick_name, work_info, mob_num, add_mob, area_code, dob)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");  
						pt.setString(1,name);
						pt.setString(2,mail);
						pt.setString(3,loc);
						pt.setString(4,nn);
						pt.setString(5,work);
						pt.setString(6,num);
						pt.setString(7,add);
						pt.setString(8,code);
						pt.setString(9,dob);
						pt.executeUpdate();
						Statement st1=null;
						tabbedPane.setSelectedIndex(2);
						list.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), "CONTACT LIST", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
						list.setBounds(22, 28, 163, 124);
						textArea.setBounds(224, 40, 166, 154);
						panel_2.add(textArea);
						
						btnNewButton_3.setBounds(219, 5, 89, 23);
						panel_2.add(btnNewButton_3);
						
						btnNewButton_4.setBounds(317, 5, 89, 23);
						panel_2.add(btnNewButton_4);
						try {
							st1 = con.createStatement();
							rs = st1.executeQuery("select name,mob_num from contactinfo");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						listModel.add(ct,(name+"       "+code + "-" + num));
						ct+=1;
						tabbedPane.setSelectedIndex(2);
						list.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								tabbedPane.setSelectedIndex(2);
								if (e.getClickCount() == 1) {
									Statement st=null;
									try {
										st = con.createStatement();
										rs = st.executeQuery("select * from contactinfo;");
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									try {
										while(rs.next())
										{           	   
											textArea.setText("Name: " + rs.getString(1) +"\nArea Code: " + rs.getString(8) +"\nPrimary Number: " +rs.getString(6) + "\nAnother Number: " + rs.getString(7)+"\nNickName: " + rs.getString(4) +"\nProfession: " + rs.getString(5)+"\nMail Id: " + rs.getString(2) + "\nLocation: " + rs.getString(3) + "\nDOB:" + rs.getString(9) );
										}
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
							}
							}
						});
						lblNewLabel_13.setText("contact Saved");
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						textField_5.setText("");
						textField_6.setText("");
						textField_7.setText("");
						textField_8.setText("");
						lblNewLabel_13.setText("");
						
					}
					catch(Exception e1)
					{
						lblNewLabel_13.setText("Name already exists");
					}
					}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.setBounds(253, 171, 65, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("EXIT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				lblNewLabel_13.setText("");
				tabbedPane.setSelectedIndex(0);
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_2.setBounds(330, 169, 62, 25);
		panel_1.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO SOUND TELEPHONE BOOK");
		lblNewLabel.setForeground(new Color(165, 42, 42));
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(62, 11, 278, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Documents\\Downloads\\phone1.jpg"));
		lblNewLabel_1.setBounds(21, 56, 180, 131);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("CREATE NEW CONTACT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "NEW CONTACT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 0, 0)));
				panel_1.setLayout(null);
				
				panel_1.add(lblNewLabel_2);
			
				panel_1.add(lblNewLabel_3);
				
				panel_1.add(lblNewLabel_4);
				
				panel_1.add(lblNewLabel_5);
				
				panel_1.add(lblNewLabel_6);
				
				panel_1.add(lblNewLabel_7);
				
				panel_1.add(lblNewLabel_8);
				

				panel_1.add(lblNewLabel_9);
				

				panel_1.add(lblNewLabel_10);
	
				panel_1.add(textField);
				textField.setColumns(10);
				
				panel_1.add(textField_1);
				textField_1.setColumns(10);
				
				panel_1.add(textField_2);
				textField_2.setColumns(10);
				

				panel_1.add(textField_3);
				textField_3.setColumns(10);

				panel_1.add(textField_4);
				textField_4.setColumns(10);
				
				panel_1.add(textField_5);
				textField_5.setColumns(10);
				
				panel_1.add(textField_6);
				textField_6.setColumns(10);
				
				panel_1.add(textField_7);
				textField_7.setColumns(10);
				
				panel_1.add(textField_8);
				textField_8.setColumns(10);
				panel_1.add(btnNewButton_1);
				
				panel_1.add(btnNewButton_2);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(208, 65, 191, 41);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_12 = new JLabel("FIND:");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_12.setBounds(218, 124, 46, 14);
		panel.add(lblNewLabel_12);
		
		textField_9 = new JTextField();
		textField_9.setBounds(263, 122, 111, 20);
		panel.add(textField_9);
		textField_9.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("SEARCH");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
				list.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), "CONTACT LIST", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				list.setBounds(22, 28, 163, 124);
				panel_2.add(list);
				
				textArea.setBounds(224, 40, 166, 154);
				panel_2.add(textArea);
				
				btnNewButton_3.setBounds(219, 5, 89, 23);
				panel_2.add(btnNewButton_3);
				
				btnNewButton_4.setBounds(317, 5, 89, 23);
				
				tabbedPane.setSelectedIndex(2);
				try {
					Statement st = con.createStatement();
					String query = "select * from contactinfo where";
					query += "" + " name= '" + textField_9.getText()+ "'";
					rs = st.executeQuery(query);
					if(rs.getRow()==0)
					{
						textArea.setForeground(new Color(255, 0, 0));
						textArea.setFont(new Font("Times New Roman", Font.BOLD, 11));
						textArea.setText("NO RECORDS FOUND");
					}
					textField_9.setText("");
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					while(rs.next())
					{   textArea.setForeground(new Color(0, 0, 0));
						textArea.setFont(new Font("Times New Roman", Font.PLAIN, 13));        	   
						textArea.setText("Name: " + rs.getString(1) +"\nArea Code: " + rs.getString(8) +"\nPrimary Number: " +rs.getString(6) + "\nAnother Number: " + rs.getString(7)+"\nNickName: " + rs.getString(4) +"\nProfession: " + rs.getString(5)+"\nMail Id: " + rs.getString(2) + "\nLocation: " + rs.getString(3) + "\nDOB:" + rs.getString(9) );
					}
				} catch (SQLException e1) 
				{
						
					e1.printStackTrace();
				}
					
			}

				
		});
		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_5.setBounds(248, 164, 89, 23);
		panel.add(btnNewButton_5);
		
			}
}
