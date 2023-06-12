package dentist_reserve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;


public class AddFrame extends JFrame {
	private JPanel contentPane;
	private JPanel panel, centerPanel;
	private JTextField idTextField, nameTextField, addrTextField, mobileTextField, bdayTextField;
	private JLabel nameLabel, idLabel, bdayLabel, addrLabel;
	private JComboBox<Integer> yearComboBox;
	private JComboBox<Integer> monthComboBox;
	private JComboBox<Integer> dateComboBox;
	
    // Get the current year, month, and date
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH) + 1; // Note: Month value is zero-based
    int currentDate = calendar.get(Calendar.DATE);
	
    AddFrame() {
		setBounds(100,100,650,400);
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
        
        JLabel lblTitle = new JLabel("新建病例");
        lblTitle.setFont(lblTitle.getFont().deriveFont(40f)); // Increase font size if desired
        lblTitle.setPreferredSize(new Dimension(300, 50)); // Set the desired size
        
        JLabel nameLabel = new JLabel("姓名");
        nameLabel.setFont(nameLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        nameTextField = new JTextField();
        nameTextField.setColumns(10);
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel idLabel = new JLabel("身分證");
        idLabel.setFont(idLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        idTextField = new JTextField();
        idTextField.setColumns(10);
        idTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel bdayLabel = new JLabel("生日");
        bdayLabel.setFont(bdayLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        yearComboBox = new JComboBox<>();
        // Put the text in the center
        yearComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Set the alignment to center
                renderer.setHorizontalAlignment(JLabel.CENTER);

                return renderer;
            }
        });
        
        monthComboBox = new JComboBox<>();
        monthComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Set the alignment to center
                renderer.setHorizontalAlignment(JLabel.CENTER);

                return renderer;
            }
        });
        
        dateComboBox = new JComboBox<>();
        dateComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Set the alignment to center
                renderer.setHorizontalAlignment(JLabel.CENTER);

                return renderer;
            }
        });
        
     // Load the year, month, and date JComboBox with the current values
        for (int year = 1950; year <= currentYear; year++) {
            yearComboBox.addItem(year);
        }

        for (int month = 1; month <= 12; month++) {
            monthComboBox.addItem(month);
        }

        for (int date = 1; date <= 31; date++) {
            dateComboBox.addItem(date);
        }
        
        JLabel addrLabel = new JLabel("住址");
        addrLabel.setFont(addrLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        addrTextField = new JTextField();
        addrTextField.setColumns(10);
        addrTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel mobileLabel = new JLabel("行動電話");
        mobileLabel.setFont(mobileLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        mobileTextField = new JTextField();
        mobileTextField.setColumns(10);
        mobileTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
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
                    .addGap(50)
                    .addComponent(nameLabel)
                    .addGap(100)
                    .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(idLabel)
                    .addGap(75)
                    .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(bdayLabel)
                    .addGap(100)
                    .addComponent(yearComboBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
			        .addGap(20)
			        .addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
			        .addGap(20)
			        .addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
			        .addGap(20)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(addrLabel)
                    .addGap(100)
                    .addComponent(addrTextField, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(mobileLabel)
                    .addGap(50)
                    .addComponent(mobileTextField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(300)
                    .addComponent(btnSave)
                )
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(30)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
    		       	    .addComponent(nameLabel)
    		       	    .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
    			    )
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        		        .addComponent(idLabel)
        		        .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			)
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
            		    .addComponent(bdayLabel)
            		    .addComponent(yearComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				        .addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				        .addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
            		    .addComponent(addrLabel)
            		    .addComponent(addrTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mobileLabel)
                        .addComponent(mobileTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
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
    	DataStorage dstor = new DataStorage();
    	String id = idTextField.getText();
    	String name = nameTextField.getText();
    	String addr = addrTextField.getText();
    	String mobile = mobileTextField.getText();
    	String selectedYear = (String)(yearComboBox.getSelectedItem().toString());
    	String selectedMonth = (String)(monthComboBox.getSelectedItem().toString());
    	String selectedDate = (String)(dateComboBox.getSelectedItem().toString());
    	// Pad single-digit month and date values with leading zeros if necessary
    	String paddedMonth = String.format("%02d", Integer.parseInt(selectedMonth));
    	String paddedDate = String.format("%02d", Integer.parseInt(selectedDate));
    	// Create the SQL date string in "YYYY-MM-DD" format
    	String birthday = String.join("-", selectedYear, paddedMonth, paddedDate);
    	
    	if (id.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "身分證不可空白", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if (name.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "姓名不可空白", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if (addr.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "請輸入住址", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if (mobile.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "請輸入行動電話", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	ArrayList data_list = dstor.getPatientInfo(id);
    	
    	if (data_list.isEmpty()) {
    		String confirmationMessage = "姓名          :  " + name + "\n" + 
    									 "生日          :  " + birthday + "\n" +
    									 "身分證      :  " + id + "\n" +
    									 "住址          :  " + addr + "\n" +
    									 "行動電話  :  " + mobile + "\n";
    		
    		int result = JOptionPane.showConfirmDialog(null, confirmationMessage + "\n確定新增?", "新增病患", JOptionPane.YES_NO_OPTION);
    		
    		if (result == JOptionPane.YES_OPTION) {
	    		if (dstor.addPatient(name, id, addr, mobile, birthday) == true) {
	        		JOptionPane.showMessageDialog(null, "新增到資料庫完成", "系統訊息", JOptionPane.INFORMATION_MESSAGE );
	        	} else {
	        		JOptionPane.showMessageDialog(null, "新增到資料庫失敗", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
	        		return;
	        	}
    		} else {
    			JOptionPane.showMessageDialog(null, "取消新增病患", "系統訊息", JOptionPane.INFORMATION_MESSAGE );
    		}
    	} else {
    		JOptionPane.showMessageDialog(null, "身分證已存在", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	
    }
}
