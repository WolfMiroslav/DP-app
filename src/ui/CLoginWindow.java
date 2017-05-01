package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.google.gson.JsonObject;

import communication.CRequestBuilder;
import utils.CUtils;

public class CLoginWindow {

	private JFrame jLoginFrame;
	private JTextField loginTextField;
	private JPasswordField passwordTextField;
	
	private static final String LOGIN_URL = "http://localhost:8080/MyEmotionServlet/CLoginServlet";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CLoginWindow window = new CLoginWindow();
					window.jLoginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CLoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jLoginFrame = new JFrame();
		jLoginFrame.setBounds(100, 100, 418, 170);
		jLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginTextField = new JTextField();
		loginTextField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					JsonObject jsonObj = new JsonObject();
					jsonObj.addProperty("username", loginTextField.getText());
					jsonObj.addProperty("userpassword", CUtils.MD5(passwordTextField.getPassword()));
										
					String body = jsonObj.toString();
					
					CRequestBuilder.executePost(LOGIN_URL, body);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		JLabel lblLogin = new JLabel("Login:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		passwordTextField = new JPasswordField();
		
		JButton btnRegister = new JButton("Registration");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				new CRegistrationWindow();
				jLoginFrame.setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(jLoginFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLogin)
						.addComponent(lblPassword))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 87, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLogin))
						.addComponent(loginTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
						.addComponent(passwordTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
					.addGap(56))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogin))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnRegister))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		jLoginFrame.getContentPane().setLayout(groupLayout);
		jLoginFrame.setVisible(true);
	}	
}
