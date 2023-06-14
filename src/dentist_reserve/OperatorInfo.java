package dentist_reserve;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class OperatorInfo {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField pwdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OperatorInfo window = new OperatorInfo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OperatorInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("吉品牙醫診所預約系統");
		lblNewLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		pwdField = new JPasswordField();
		pwdField.setColumns(10);
		
		JButton btnNewButton = new JButton("註冊帳號");
		btnNewButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
		
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	registerButtonActionPerformed(e);
            }
        });
		
		JButton btnNewButton_1 = new JButton("登入");
		btnNewButton_1.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
		
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	loginButtonActionPerformed(e);
            }
        });
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(134)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(94)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(96)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(pwdField, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(158)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(80)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addComponent(pwdField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnNewButton))
						.addComponent(btnNewButton_1)))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String secretPassword = "test123";
		JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showOptionDialog(null, passwordField, "請輸入管理員密碼",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        
        if (option == JOptionPane.OK_OPTION) {
            char[] password = passwordField.getPassword();
            String passwordString = new String(password);
            
            if (passwordString.equals(secretPassword)) {
            	AddOperatorFrame f = new AddOperatorFrame();
				f.setVisible(true);
            } else {
            	JOptionPane.showMessageDialog(null, "密碼錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
				return;
            }
        } else {
            System.out.println("Password prompt cancelled.");
        }
    }
	
	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String username = textField.getText();
		char[] password = pwdField.getPassword();
		String pwd = new String(password);
		
		if (username.isEmpty()) {
			JOptionPane.showMessageDialog(null, "請輸入使用者名稱", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
    		return;
		}
		
		if (pwd.isEmpty()) {
			JOptionPane.showMessageDialog(null, "請輸入密碼", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			pwdField.requestFocus();
			return;
		}
		
		ArrayList<String> query = DataStorage.getOperatorInfo(username);
		if (query.isEmpty()) {
			JOptionPane.showMessageDialog(null, "使用者不存在", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
    		return;
		}
		
		String db_pass = (String)query.get(1);
		
		if (!pwd.equals(db_pass)) {
			JOptionPane.showMessageDialog(null, "密碼錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			pwdField.requestFocus();
			return;
		}
		
		MainFrame f = new MainFrame(username);
		f.setTitle("Dentist Reservation System");
		f.setVisible(true);
		frame.dispose();
    }
}

