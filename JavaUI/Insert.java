package lib;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Insert extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Insert frame = new Insert();
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
	
	 private JTextField txtPleaseInsertData;
	 private JTextField textField;
	 private JTextField textField_1;
	 private JTextField textField_2;
	 private JTable table;
	
	/**
	 * Create the frame.
	 */
	public Insert() {
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
		
		JLabel lblNewLabel = new JLabel("Please insert data into the table:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 6, 300, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblReserveId = new JLabel("Reserve ID");
		lblReserveId.setBounds(20, 42, 83, 16);
		contentPane.add(lblReserveId);
		
		JLabel lblReserveAmount = new JLabel("Reserve Amount");
		lblReserveAmount.setBounds(20, 70, 112, 16);
		contentPane.add(lblReserveAmount);
		
		JLabel lblReserveLocation = new JLabel("Reserve Location");
		lblReserveLocation.setBounds(20, 98, 112, 16);
		contentPane.add(lblReserveLocation);
		
		textField = new JTextField();
		textField.setBounds(156, 34, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(156, 65, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(156, 93, 130, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "INSERT INTO reserve3(reserve_id, reserve_amount, reserve_location)VALUES(?,?,?)";

				try(PreparedStatement stmt = conn1.prepareStatement(query)) {
					stmt.setString(1,textField.getText());
					stmt.setString(2,textField_1.getText());
					stmt.setString(3,textField_2.getText());
										
					ResultSet rs1 = stmt.executeQuery();	
					JOptionPane.showMessageDialog(null, "Data Saved successfully");
					
					}
				catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
			
		});
		btnSave.setBounds(15, 138, 117, 29);
		contentPane.add(btnSave);
		
		JButton btnShowTable = new JButton("Refresh Table");
		btnShowTable.addActionListener(new ActionListener() {
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
		btnShowTable.setBounds(156, 138, 117, 29);
		contentPane.add(btnShowTable);
		
		table = new JTable();
		table.setBounds(54, 223, 740, 349);
		contentPane.add(table);
		
		JLabel lblReserveid = new JLabel("Reserve ID");
		lblReserveid.setBounds(54, 195, 93, 16);
		contentPane.add(lblReserveid);
		
		JLabel lblReserveAmount_1 = new JLabel("Reserve Amount");
		lblReserveAmount_1.setBounds(303, 195, 112, 16);
		contentPane.add(lblReserveAmount_1);
		
		JLabel lblReserveLocation_1 = new JLabel("Reserve Location");
		lblReserveLocation_1.setBounds(546, 195, 112, 16);
		contentPane.add(lblReserveLocation_1);
		
		
		
		
		
	}
}
