package dentist_reserve;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class AddOperatorFrame extends JFrame {
	private JPanel contentPane;
	private JPanel panel, centerPanel;
	private JTextField nameTextField, usernameTextField, employeeidTextField;
	private JPasswordField passwdField, passwd2Field;
	
    AddOperatorFrame() {
		setBounds(100,100,420,450);
		setVisible(true);
		setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST);
        
        centerPanel = new JPanel();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        
        JLabel lblTitle = new JLabel("建立帳號");
        lblTitle.setFont(lblTitle.getFont().deriveFont(40f)); // Increase font size if desired
        lblTitle.setPreferredSize(new Dimension(300, 50)); // Set the desired size
        
        JLabel usernameLabel = new JLabel("登入名稱");
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        usernameTextField = new JTextField();
        usernameTextField.setColumns(10);
        usernameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel nameLabel = new JLabel("姓名");
        nameLabel.setFont(nameLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        nameTextField = new JTextField();
        nameTextField.setColumns(10);
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel employeeidLabel = new JLabel("工號");
        employeeidLabel.setFont(employeeidLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        employeeidTextField = new JTextField();
        employeeidTextField.setColumns(10);
        employeeidTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel passwdLabel = new JLabel("密碼");
        passwdLabel.setFont(passwdLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        passwdField = new JPasswordField();
        passwdField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel passwd2Label = new JLabel("確認密碼");
        passwd2Label.setFont(passwd2Label.getFont().deriveFont(24f)); // Increase font size if desired
        
        passwd2Field = new JPasswordField();
        passwd2Field.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JButton btnSave = new JButton("儲存");
        btnSave.setPreferredSize(new Dimension(200, 50));
        btnSave.setFont(new Font(btnSave.getFont().getName(), Font.BOLD, 16));
        
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	saveButtonActionPerformed(e);
            }
        });
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
            	.addGroup(gl_panel.createSequentialGroup()
                    .addGap(120)
                    .addComponent(lblTitle)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(usernameLabel)
                    .addGap(50)
                    .addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                        .addGap(50)
                        .addComponent(nameLabel)
                        .addGap(100)
                        .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                    )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(employeeidLabel)
                    .addGap(100)
                    .addComponent(employeeidTextField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(passwdLabel)
                    .addGap(100)
                    .addComponent(passwdField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(passwd2Label)
                    .addGap(50)
                    .addComponent(passwd2Field, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(150)
                    .addComponent(btnSave)
                )
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(10)
                	.addComponent(lblTitle)
                	.addGap(30)
                	.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        		        .addComponent(usernameLabel)
        		        .addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			)
                	.addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
    		       	    .addComponent(nameLabel)
    		       	    .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
    			    )
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        		        .addComponent(employeeidLabel)
        		        .addComponent(employeeidTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			)
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
            		    .addComponent(passwdLabel)
            		    .addComponent(passwdField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(passwd2Label)
                        .addComponent(passwd2Field, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(30)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnSave)
                    )
                )
        );
        
        panel.setLayout(gl_panel);
	}
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	char[] password = passwdField.getPassword();
    	String pwd 	= new String(password);
    	char[] password2 = passwd2Field.getPassword();
    	String pwd2 = new String(password2);
    	String username = usernameTextField.getText().trim();
    	String name = nameTextField.getText();
    	String emp_id = employeeidTextField.getText();
    	
    	if (username.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "名稱不可空白", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if (username.contains(" ")) {
    		JOptionPane.showMessageDialog(null, "名稱不可含有空白", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if (name.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "名字不可空白", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if (emp_id.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "工號不可空白", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if (pwd.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "密碼不可空白", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}

    	if (!pwd.equals(pwd2)) {
    		JOptionPane.showMessageDialog(null, "密碼不一致", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		passwdField.requestFocus();
    		return;
    	}
    	
    	ArrayList<String> result = DataStorage.getOperatorInfo(username);
    	
    	if (!result.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "登入名稱已存在", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		usernameTextField.requestFocus();
    		return;
    	}
    	
    	if (DataStorage.addOperatorInfo(username, name, emp_id, pwd)) {
    		JOptionPane.showMessageDialog(null, "增加使用者完成", "系統訊息", JOptionPane.INFORMATION_MESSAGE );
    		dispose();
    	} else {
    		JOptionPane.showMessageDialog(null, "增加使用者失敗", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    }
}
