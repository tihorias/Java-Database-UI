package lib;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete frame = new Delete();
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
	public Delete() {
		try {
			conn1 = DriverManager.getConnection(dbURL1);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseEnterThe = new JLabel("Are you sure you want to drop the table");
		lblPleaseEnterThe.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPleaseEnterThe.setBounds(20, 6, 367, 16);
		contentPane.add(lblPleaseEnterThe);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "DROP TABLE RESERVE3";
				try(PreparedStatement stmt = conn1.prepareStatement(query)) {
					ResultSet rs1 = stmt.executeQuery();	
					JOptionPane.showMessageDialog(null, "Table Dropped Succesfully");

				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
			
		});
		btnConfirm.setBounds(6, 34, 117, 29);
		contentPane.add(btnConfirm);
	}

}
