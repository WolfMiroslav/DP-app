package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.google.gson.JsonObject;

import communication.CRequestBuilder;
import utils.CUtils;

public class CRegistrationWindow {

	private JFrame jRegistrationFrame;
	private JTextField loginTextField;
	private JPasswordField passwordTextField;
	private JTextField ageTextField;
	private final ButtonGroup genderButtonGroup = new ButtonGroup();
	JRadioButton rdbtnMale = new JRadioButton("Male");
	JRadioButton rdbtnFemale = new JRadioButton("Female");
		
	private static final String REGISTRATION_URL = "http://192.168.1.4:8080/MyEmotionServlet/CRegisterServlet";

	/**
	 * Create the application.
	 */
	public CRegistrationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jRegistrationFrame = new JFrame();
		jRegistrationFrame.setBounds(100, 100, 400, 350);
		jRegistrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginTextField = new JTextField();
		loginTextField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				jRegistrationFrame.setVisible(false);
				new CLoginWindow();
			}
		});
		
		JLabel lblLogin = new JLabel("Login:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		passwordTextField = new JPasswordField();
		
		JButton btnRegister = new JButton("Registration");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					if(loginTextField.getText().length() > 0
							&& passwordTextField.getPassword().length > 0
							&& ageTextField.getText().length() > 0
							&& genderButtonGroup.getSelection() != null)
					{
						JsonObject jsonObj = new JsonObject();
						jsonObj.addProperty("username", loginTextField.getText());					
						jsonObj.addProperty("userpassword", CUtils.MD5(passwordTextField.getPassword()));
						jsonObj.addProperty("userage", ageTextField.getText());
						jsonObj.addProperty("usergender", rdbtnMale.isSelected() ? false : true);					
											
						String body = jsonObj.toString();
						
						CRequestBuilder.executePost(REGISTRATION_URL, body);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		ageTextField = new JTextField();
		ageTextField.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
				
		genderButtonGroup.add(rdbtnMale);
		genderButtonGroup.add(rdbtnFemale);
		
		JLabel lblGender = new JLabel("Gender:");
		GroupLayout groupLayout = new GroupLayout(jRegistrationFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLogin))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLogin)
								.addComponent(lblPassword)
								.addComponent(lblAge)
								.addComponent(lblGender))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(loginTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(passwordTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(ageTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(rdbtnMale)
									.addGap(18)
									.addComponent(rdbtnFemale)))))
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
						.addComponent(ageTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAge))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnMale)
						.addComponent(rdbtnFemale)
						.addComponent(lblGender))
					.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnRegister))
					.addContainerGap())
		);
		jRegistrationFrame.getContentPane().setLayout(groupLayout);
		jRegistrationFrame.setVisible(true);
	}	
}
