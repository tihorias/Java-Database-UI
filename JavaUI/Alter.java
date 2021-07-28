package lib;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Alter extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alter frame = new Alter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn1 = null;
	String dbURL1 = "jdbc:oracle:thin:fabedin/04118552@oracle.scs.ryerson.ca:1521:orcl"; 
	// String dbURL1 = "jdbc:oracle:thin:snidumuk/12302399@oracle.scs.ryerson.ca:1521:orcl"; 
	

	/**
	 * Create the frame.
	 */
	public Alter() {
		try {
			conn1 = DriverManager.getConnection(dbURL1);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the Reserve ID below to delete from the table. (Refresh Table if necessary)");
		lblPleaseEnterThe.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPleaseEnterThe.setBounds(6, 6, 705, 16);
		contentPane.add(lblPleaseEnterThe);
		
		JLabel lblReserveId = new JLabel("Reserve ID:");
		lblReserveId.setBounds(6, 48, 102, 16);
		contentPane.add(lblReserveId);
		
		textField = new JTextField();
		textField.setBounds(101, 43, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "DELETE FROM RESERVE3 WHERE RESERVE_ID=?";
				try(PreparedStatement stmt = conn1.prepareStatement(query)) {
					stmt.setString(1,textField.getText());
					ResultSet rs1 = stmt.executeQuery();	
					JOptionPane.showMessageDialog(null, "Reserve ID deleted");

				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
				
		});
		btnSave.setBounds(261, 43, 117, 29);
		contentPane.add(btnSave);
		
		JButton btnRefreshTable = new JButton("Refresh Table");
		btnRefreshTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT * FROM RESERVE3";

				try(Statement stmt = conn1.createStatement()) {
					ResultSet rs = stmt.executeQuery(query);	
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					}
				catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
							
			}
		});
		btnRefreshTable.setBounds(410, 43, 117, 29);
		contentPane.add(btnRefreshTable);
		
		table = new JTable();
		table.setBounds(25, 108, 770, 388);
		contentPane.add(table);
		
		JLabel lblReserveId_1 = new JLabel("Reserve ID");
		lblReserveId_1.setBounds(25, 89, 83, 16);
		contentPane.add(lblReserveId_1);
		
		JLabel lblReserveAmount = new JLabel("Reserve Amount");
		lblReserveAmount.setBounds(282, 89, 109, 16);
		contentPane.add(lblReserveAmount);
		
		JLabel lblReserveLocation = new JLabel("Reserve Location");
		lblReserveLocation.setBounds(535, 89, 109, 16);
		contentPane.add(lblReserveLocation);
	}
}
