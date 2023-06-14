package dentist_reserve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class AppointmentFrame extends JFrame {
	private JPanel contentPane;
	private JPanel panel, centerPanel;
	private JTextField treatmentTextField;
	private JTextArea commentTextArea;
	private JLabel nameValLabel, idValLabel;
	private JComboBox<Integer> yearComboBox;
	private JComboBox<Integer> monthComboBox;
	private JComboBox<Integer> dateComboBox;
	private JComboBox<String> doctorComboBox;
	private String[] doctors = {"黃醫生", "劉醫生", "柯醫生"};
	
    // Get the current year, month, and date
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH) + 1; // Note: Month value is zero-based
    int currentDate = calendar.get(Calendar.DATE);
	
    AppointmentFrame(String name, String id) {
		setBounds(100,100,600,550);
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
        
        JLabel lblTitle = new JLabel("病患預約");
        lblTitle.setFont(lblTitle.getFont().deriveFont(40f)); // Increase font size if desired
        lblTitle.setPreferredSize(new Dimension(300, 50)); // Set the desired size
        
        JLabel nameLabel = new JLabel("姓名");
        nameLabel.setFont(nameLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        nameValLabel = new JLabel(name);
        nameValLabel.setFont(nameValLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        JLabel idLabel = new JLabel("身分證");
        idLabel.setFont(idLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        idValLabel = new JLabel(id);
        idValLabel.setFont(idValLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        JLabel reserveLabel = new JLabel("預約日期");
        reserveLabel.setFont(reserveLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
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
        for (int year = currentYear; year <= currentYear + 10; year++) {
            yearComboBox.addItem(year);
        }

        for (int month = 1; month <= 12; month++) {
            monthComboBox.addItem(month);
        }

        for (int date = 1; date <= 31; date++) {
            dateComboBox.addItem(date);
        }
        
        yearComboBox.setSelectedItem(currentYear);
        monthComboBox.setSelectedItem(currentMonth);
        dateComboBox.setSelectedItem(currentDate);
        
        JLabel doctorLabel = new JLabel("醫生");
        doctorLabel.setFont(doctorLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        doctorComboBox = new JComboBox<String>();
        
        for (int idx = 0; idx < doctors.length; idx++) {
        	doctorComboBox.addItem(doctors[idx]);
        }
        
        JLabel treatmentLabel = new JLabel("治療方式");
        treatmentLabel.setFont(treatmentLabel.getFont().deriveFont(24f)); // Increase font size if desired

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        treatmentTextField = new JTextField();
        treatmentTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        treatmentTextField.setBorder(border);
        
        JLabel commentLabel = new JLabel("註解");
        commentLabel.setFont(commentLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        commentTextArea = new JTextArea(5, 30);
        commentTextArea.setLineWrap(true);
        commentTextArea.setWrapStyleWord(true);
        commentTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        commentTextArea.setBorder(border);
        
        JButton btnAppointment = new JButton("預約");
        btnAppointment.setPreferredSize(new Dimension(200, 50));
        btnAppointment.setFont(new Font(btnAppointment.getFont().getName(), Font.BOLD, 16));
        
        btnAppointment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	appointButtonActionPerformed(e);
            }
        });
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
            	.addGroup(gl_panel.createSequentialGroup()
                    .addGap(190)
                    .addComponent(lblTitle)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(nameLabel)
                    .addGap(100)
                    .addComponent(nameValLabel)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(idLabel)
                    .addGap(75)
                    .addComponent(idValLabel)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(reserveLabel)
                    .addGap(50)
                    .addComponent(yearComboBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
			        .addGap(20)
			        .addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
			        .addGap(20)
			        .addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
			        .addGap(20)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(doctorLabel)
                    .addGap(100)
                    .addComponent(doctorComboBox, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(treatmentLabel)
                    .addGap(50)
                    .addComponent(treatmentTextField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(commentLabel)
                    .addGap(100)
                    .addComponent(commentTextArea, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(250)
                    .addComponent(btnAppointment)
                )
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(Alignment.CENTER)
        		        .addComponent(lblTitle)
        			)
                    .addGap(30)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
    		       	    .addComponent(nameLabel)
    		       	    .addComponent(nameValLabel)
    			    )
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        		        .addComponent(idLabel)
        		        .addComponent(idValLabel)
        			)
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
            		    .addComponent(reserveLabel)
            		    .addComponent(yearComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				        .addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				        .addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
            		    .addComponent(doctorLabel)
            		    .addComponent(doctorComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                    	.addComponent(treatmentLabel)
                    	.addComponent(treatmentTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                   	    .addComponent(commentLabel)
                   	    .addComponent(commentTextArea, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(30)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnAppointment)
                    )
                )
        );
        
        panel.setLayout(gl_panel);
	}
    
    private void appointButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	String selectedYear = (String)(yearComboBox.getSelectedItem().toString());
    	String selectedMonth = (String)(monthComboBox.getSelectedItem().toString());
    	String selectedDate = (String)(dateComboBox.getSelectedItem().toString());
    	// Pad single-digit month and date values with leading zeros if necessary
    	String paddedMonth = String.format("%02d", Integer.parseInt(selectedMonth));
    	String paddedDate = String.format("%02d", Integer.parseInt(selectedDate));
    	// Create the SQL date string in "YYYY-MM-DD" format
    	String appointDate = String.join("-", selectedYear, paddedMonth, paddedDate);
    	String id = idValLabel.getText();
    	String doctor = doctorComboBox.getSelectedItem().toString();
    	String treatment = treatmentTextField.getText();
    	String comment = commentTextArea.getText();
    	
    	// Check if there is existing appointment
    	ArrayList<String> data_list = DataStorage.getAppointmentInfo(id, appointDate);
    	
    	if (data_list.isEmpty()) {
    		String confirmationMessage = "姓名          :  " + nameValLabel.getText() + "\n" + 
									     "身分證      :  " + id + "\n" +
									     "日期          :  " + appointDate + "\n" +
									     "醫生          :  " + doctor + "\n";
    		
    		int result = JOptionPane.showConfirmDialog(null, confirmationMessage + "\n確定預約?\n\n", "預約", JOptionPane.YES_NO_OPTION);
    		
    		if (result == JOptionPane.YES_OPTION) {
	    		if (DataStorage.addAppointment(id, appointDate, doctor, treatment, comment) == true) {
	        		JOptionPane.showMessageDialog(null, "預約完成", "系統訊息", JOptionPane.INFORMATION_MESSAGE);
	        		dispose();
	        	} else {
	        		JOptionPane.showMessageDialog(null, "預約失敗", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
	        		return;
	        	}
    		} else {
    			JOptionPane.showMessageDialog(null, "取消預約", "系統訊息", JOptionPane.INFORMATION_MESSAGE);
    		}
    	} else {
    		JOptionPane.showMessageDialog(null, "重複預約!", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	
    	
    }
    
}
