package lib;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn1 = null;
	   String dbURL1 = "jdbc:oracle:thin:fabedin/04118552@oracle.scs.ryerson.ca:1521:orcl"; 
	 //String dbURL1 = "jdbc:oracle:thin:snidumuk/12302399@oracle.scs.ryerson.ca:1521:orcl"; 
	
	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
		try {
			conn1 = DriverManager.getConnection(dbURL1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Bank Database");
		lblWelcomeToThe.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblWelcomeToThe.setBounds(16, 23, 274, 28);
		frame.getContentPane().add(lblWelcomeToThe);
		
		JLabel lblPleaseSelectAn = new JLabel("Please select an operation:");
		lblPleaseSelectAn.setBounds(16, 52, 187, 16);
		frame.getContentPane().add(lblPleaseSelectAn);
		
		JButton btnNewButton = new JButton("Create Table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					frame.dispose();
					CreateTable ntable = new CreateTable();
					ntable.setVisible(true);
					}
				
				finally {
		            try {
		                if (conn1 != null && !conn1.isClosed()) {
		                    conn1.close();
		                }

		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
				
			}
			}
		});
		
		btnNewButton.setBounds(6, 89, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Populate Table");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					frame.dispose();
					Insert ntable = new Insert();
					ntable.setVisible(true);
					}
				
				finally {
		            try {
		                if (conn1 != null && !conn1.isClosed()) {
		                    conn1.close();
		                }

		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
				
			}
			}
		});
				
		btnNewButton_1.setBounds(135, 89, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		
		JButton btnPopulateTable = new JButton("Alter Table");
		btnPopulateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					frame.dispose();
					Alter ntable = new Alter();
					ntable.setVisible(true);
					}
				
				finally {
		            try {
		                if (conn1 != null && !conn1.isClosed()) {
		                    conn1.close();
		                }

		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
				
			}
			}
		});
			
		btnPopulateTable.setBounds(6, 143, 117, 29);
		frame.getContentPane().add(btnPopulateTable);
		
		JButton btnDeleteTable = new JButton("Delete Table");
		btnDeleteTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					frame.dispose();
					Delete ntable = new Delete();
					ntable.setVisible(true);
					}
			
				finally {
					try {
						if (conn1 != null && !conn1.isClosed()) {
							conn1.close();
						}

					} catch (SQLException ex) {
						ex.printStackTrace();
					}
			
		}
		}
	});
		
		btnDeleteTable.setBounds(135, 143, 117, 29);
		frame.getContentPane().add(btnDeleteTable);
		
		
	}
}
