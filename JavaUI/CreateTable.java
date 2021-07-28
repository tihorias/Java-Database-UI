package lib;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class CreateTable extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTable frame = new CreateTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn1 = null;
	String dbURL1 = "jdbc:oracle:thin:fabedin/04118552@oracle.scs.ryerson.ca:1521:orcl"; 
	private JLabel lblAreYouSure;
	private JButton btnConfirm;
	 //String dbURL1 = "jdbc:oracle:thin:snidumuk/12302399@oracle.scs.ryerson.ca:1521:orcl"; 
	

	/**
	 * Create the frame.
	 */
	public CreateTable() {
		try {
			conn1 = DriverManager.getConnection(dbURL1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnShowTable = new JButton("Show Table");
		btnShowTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				String query = "SELECT * FROM RESERVE3";

				try(Statement stmt = conn1.createStatement()) {
					ResultSet rs = stmt.executeQuery(query);	
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					}
				catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
		btnShowTable.setBounds(41, 84, 117, 29);
		contentPane.add(btnShowTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 125, 569, 298);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblAreYouSure = new JLabel("Are you sure you want to create a table:");
		lblAreYouSure.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblAreYouSure.setBounds(48, 34, 306, 16);
		contentPane.add(lblAreYouSure);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query0 = "CREATE TABLE reserve3(\n" + 
						"  reserve_id NUMBER PRIMARY KEY,\n" + 
						"  reserve_amount NUMBER NOT NULL,\n" + 
						"  reserve_location VARCHAR2(50 CHAR)\n" + 
						")";
				try (Statement stmt = conn1.createStatement()) {

					ResultSet rs = stmt.executeQuery(query0);
					JOptionPane.showMessageDialog(null, "Table created successfully");

					
				} catch (SQLException e2) {
					System.out.println(e2.getErrorCode());
				}
											
			}
		});
		btnConfirm.setBounds(343, 30, 117, 29);
		contentPane.add(btnConfirm);
		
		
		
	
	}
}
