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
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

class FontTableCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Font font = cell.getFont();
        cell.setFont(font.deriveFont(font.getSize() + 1f)); // Increase font size by 1 points
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        return cell;
    }
}

class ReadOnlyTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Make all cells read-only
    }
}

public class ShowAppointmentFrame extends JFrame {
	private JPanel contentPane;
	private JPanel panel, centerPanel;
	private JComboBox<Integer> yearComboBox;
	private JComboBox<Integer> monthComboBox;
	private JComboBox<Integer> dateComboBox;
	private JComboBox<String> doctorComboBox;
	private JTable resultTable;
	private ReadOnlyTableModel tableModel = new ReadOnlyTableModel();
	private DataStorage dstor = new DataStorage();
	private ArrayList<QueryAppointmentData> query_result;
	private Boolean show_by_id = false;
	
    // Get the current year, month, and date
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH) + 1; // Note: Month value is zero-based
    int currentDate = calendar.get(Calendar.DATE);
	
    ShowAppointmentFrame(String id, String date) {
    	String name = "";
    	if (id.isEmpty()) {
    		// Query by date
    		show_by_id = false;
    	} else {
    		// Query by ID
    		show_by_id = true;
    	}
    	
    	if (show_by_id) {
    		// Get name
    		ArrayList data_list = dstor.getPatientInfo(id);
    		
    		// Check if patient is already registered before
    		if (data_list.isEmpty()) {
    			// Shouldn't happen
    			name = "---";
    		} else {
    			name = (String)data_list.get(1);
    		}
    	}
    	
		setBounds(100,100,1200,800);
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
        
        JLabel lblTitle = new JLabel("病患預約狀況");
        lblTitle.setFont(lblTitle.getFont().deriveFont(40f)); // Increase font size if desired
        lblTitle.setPreferredSize(new Dimension(300, 50)); // Set the desired size
        
        JLabel nameLabel = new JLabel("姓名");
        nameLabel.setFont(nameLabel.getFont().deriveFont(24f));
        
        JLabel nameValLabel = new JLabel(name);
        nameValLabel.setFont(nameValLabel.getFont().deriveFont(24f));
        
        JLabel idLabel = new JLabel("身分證");
        idLabel.setFont(idLabel.getFont().deriveFont(24f));
        
        JLabel idValLabel = new JLabel(id);
        idValLabel.setFont(idValLabel.getFont().deriveFont(24f));
        
        tableModel.addColumn("姓名");
        tableModel.addColumn("身分證");
        tableModel.addColumn("預約日期");
        tableModel.addColumn("醫生");
        tableModel.addColumn("治療方式");
        tableModel.addColumn("備註");
        
        query_result = dstor.getAppointmentInfoByID_Date(id, date);
        for (int idx = 0; idx < query_result.size(); idx++) {
        	QueryAppointmentData data = query_result.get(idx);
        	
        	tableModel.addRow(new Object[]{"", data.id, data.reservation_date, data.doctor, data.treatment, data.comment});
        }
        
        resultTable = new JTable(tableModel);
        resultTable.setRowHeight(30);
        
        // Change the font inside table
        FontTableCellRenderer fontCellRenderer = new FontTableCellRenderer();
        resultTable.getColumnModel().getColumn(0).setCellRenderer(fontCellRenderer);
        resultTable.getColumnModel().getColumn(1).setCellRenderer(fontCellRenderer);
        resultTable.getColumnModel().getColumn(2).setCellRenderer(fontCellRenderer);
        resultTable.getColumnModel().getColumn(3).setCellRenderer(fontCellRenderer);
        resultTable.getColumnModel().getColumn(4).setCellRenderer(fontCellRenderer);
        resultTable.getColumnModel().getColumn(5).setCellRenderer(fontCellRenderer);
        
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(130);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(130);
        resultTable.getColumnModel().getColumn(2).setPreferredWidth(130);
        resultTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        resultTable.getColumnModel().getColumn(4).setPreferredWidth(200);
        resultTable.getColumnModel().getColumn(5).setPreferredWidth(500);
        
        JScrollPane scrollPane = new JScrollPane(resultTable);
        
        JButton btnCancel = new JButton("取消預約");
        btnCancel.setPreferredSize(new Dimension(200, 50));
        btnCancel.setFont(new Font(btnCancel.getFont().getName(), Font.BOLD, 16));
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
            	.addGroup(gl_panel.createSequentialGroup()
                    .addGap(400)
                    .addComponent(lblTitle)
                )
            	.addGroup(gl_panel.createSequentialGroup()
                	.addGap(50)
                	.addComponent(nameLabel)
                	.addGap(20)
                	.addComponent(nameValLabel)
                	.addGap(50)
                	.addComponent(idLabel)
                	.addGap(20)
                	.addComponent(idValLabel)
                	.addGap(575)
                    .addComponent(btnCancel)
                )
                .addGroup(gl_panel.createSequentialGroup()
                	.addGap(50)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
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
                    	.addComponent(idLabel)
                    	.addComponent(idValLabel)
                    	.addComponent(btnCancel)
    			    )
                    .addGap(25)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
    		       	    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
    			    )
                    
                )
        );
        
        panel.setLayout(gl_panel);
	}
    
    private void appointButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    }
    
}
