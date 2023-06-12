package dentist_reserve;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Component;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import java.net.URL;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

class MainFrame extends JFrame {
	private JPanel contentPane, rightPanel;
	private JPanel panel, panel_1;
	private JTextField idTextField;
	private JLabel timeLabel;
	private JComboBox<Integer> yearComboBox;
	private JComboBox<Integer> monthComboBox;
	private JComboBox<Integer> dateComboBox;
	
    // Get the current year, month, and date
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH) + 1; // Note: Month value is zero-based
    int currentDate = calendar.get(Calendar.DATE);
	
    MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,1000,550);
		setVisible(true);
		setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST);
        panel.setBackground(Color.WHITE);
        
        panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        //panel_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        
        JLabel nowLabel = new JLabel("現在時間");
        nowLabel.setFont(nowLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        
        // Create a Timer with a delay of 1000 milliseconds (1 second)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        
        timer.start();
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(nowLabel)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(timeLabel)
                )
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(50)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(nowLabel)
                    .addGap(10)
                    .addComponent(timeLabel)
                )
        );
        
        panel.setLayout(gl_panel);
        
        rightPanel = new JPanel();
        contentPane.add(rightPanel, BorderLayout.CENTER);
        rightPanel.setBackground(Color.WHITE);
        
        GroupLayout gl_right_panel = new GroupLayout(rightPanel);
        
        JLabel lblTitle = new JLabel("吉品牙醫預約系統");
        lblTitle.setFont(lblTitle.getFont().deriveFont(40f)); // Increase font size if desired
        lblTitle.setPreferredSize(new Dimension(300, 50)); // Set the desired size
        
        JLabel searchLabel = new JLabel("請選擇搜尋方式:");
        searchLabel.setFont(searchLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        JLabel dateLabel = new JLabel("預約日期");
        dateLabel.setFont(dateLabel.getFont().deriveFont(24f)); // Increase font size if desired
        
        JLabel idLabel = new JLabel("身分證");
        idLabel.setFont(idLabel.getFont().deriveFont(24f)); // Increase font size if desired
        	
        idTextField = new JTextField();
        idTextField.setColumns(10);
        idTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
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
        for (int year = currentYear - 10; year <= currentYear + 10; year++) {
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
        
        JButton btnSearch = new JButton("搜尋");
        btnSearch.setPreferredSize(new Dimension(200, 50));
        btnSearch.setFont(new Font(btnSearch.getFont().getName(), Font.BOLD, 16));
        
        JButton btnAdd = new JButton("新增病例");
        btnAdd.setPreferredSize(new Dimension(200, 50));
        btnAdd.setFont(new Font(btnAdd.getFont().getName(), Font.BOLD, 16));
        
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);
            }
        });
        
        JButton btnReserve = new JButton("預約");
        btnReserve.setPreferredSize(new Dimension(200, 50));
        btnReserve.setFont(new Font(btnReserve.getFont().getName(), Font.BOLD, 16));
        
        btnReserve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reserveButtonActionPerformed(e);
            }
        });
        
		gl_right_panel.setHorizontalGroup(
			gl_right_panel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_right_panel.createSequentialGroup()
		            .addGap(120)
		            .addGroup(gl_right_panel.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_right_panel.createSequentialGroup()
		                	.addGap(20)
		                    .addComponent(lblTitle)
		                )
		                .addGroup(gl_right_panel.createSequentialGroup()
				        	.addGap(20)
				            .addComponent(searchLabel)
				        )
		                .addGroup(gl_right_panel.createSequentialGroup()
					    	.addGap(20)
					        .addComponent(dateLabel)
					        .addGap(75)
					        .addComponent(yearComboBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					        .addGap(20)
					        .addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					        .addGap(20)
					        .addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					        .addGap(20)
					    )
		                .addGroup(gl_right_panel.createSequentialGroup()
							.addGap(20)
						    .addComponent(idLabel)
						    .addGap(100)
					        .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						)
		                .addGroup(gl_right_panel.createSequentialGroup()
							.addGap(20)
						    .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						)
		                .addGroup(gl_right_panel.createSequentialGroup()
					        .addGap(20)
					        .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					        .addGap(40)
						    .addComponent(btnReserve, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
		                )
		            )
		        )
		);
		gl_right_panel.setVerticalGroup(
			gl_right_panel.createSequentialGroup()
	            .addGroup(gl_right_panel.createSequentialGroup()
	            	.addGap(40)
	            	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
	            		.addComponent(lblTitle)
		            )
	            )
	            .addGroup(gl_right_panel.createSequentialGroup()
		        	.addGap(40)
		        	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
		        		.addComponent(searchLabel)
			        )
		        	.addGap(40)
		        	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
		        		.addComponent(dateLabel)
		        		.addComponent(yearComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				        .addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				        .addComponent(dateComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
			        )
		        	.addGap(20)
		        	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
		        		.addComponent(idLabel)
		        		.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
			        )
		        	.addGap(30)
		        	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
		        		.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
			        )
		        	.addGap(30)
		        	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReserve, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
			        )
		        )
        );
        rightPanel.setLayout(gl_right_panel);
        
        loadImage();
	}
	
	private void updateTime() {
        // Get the current time
        Date currentTime = new Date();

        // Format the time as desired
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        String formattedTime = sdf.format(currentTime);

        // Update the timeLabel
        timeLabel.setText(formattedTime);
    }
	
	private void loadImage() {
		try {
			URL ImageUrl = ReserveFrame.class.getResource("/images/dentist.jpg");
			Image image = ImageIO.read(ImageUrl);
			Image resizedImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
			
			JLabel imageLabel = new JLabel(resizedImageIcon);
	        
	        panel_1.removeAll();
	        
	        panel_1.add(imageLabel);
	        
	        panel_1.revalidate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
		AddFrame add_f = new AddFrame();
		add_f.setTitle("Add New Patient");
    }
	
	private void reserveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String id = idTextField.getText();
		String selectedYear = (String)(yearComboBox.getSelectedItem().toString());
    	String selectedMonth = (String)(monthComboBox.getSelectedItem().toString());
    	String selectedDate = (String)(dateComboBox.getSelectedItem().toString());
    	// Pad single-digit month and date values with leading zeros if necessary
    	String paddedMonth = String.format("%02d", Integer.parseInt(selectedMonth));
    	String paddedDate = String.format("%02d", Integer.parseInt(selectedDate));
    	// Create the SQL date string in "YYYY-MM-DD" format
    	String appointmentDate = String.join("-", selectedYear, paddedMonth, paddedDate);
    	
    	if (id.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "身分證不可空白", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
    		idTextField.requestFocus();
    		return;
    	}
		
		DataStorage dstor = new DataStorage();
		ArrayList data_list = dstor.getPatientInfo(id);
		// Check if patient is already registered before
		if (data_list.isEmpty()) {
			JOptionPane.showMessageDialog(null, "請先註冊病患", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		AppointmentFrame ap_f = new AppointmentFrame((String)data_list.get(1), id);
		ap_f.setTitle("Make Appointment");
    }
}

public class ReserveFrame {
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		f.setTitle("Dentist Reservation System");
	}
}

