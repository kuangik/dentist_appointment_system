package dentist_reserve;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
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
	private JPanel panel;
	private JTable resultTable;
	private JScrollPane scrollPane;
	private ReadOnlyTableModel tableModel = new ReadOnlyTableModel();
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
    		ArrayList<String> data_list = DataStorage.getPatientInfo(id);
    		
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
        
        query_result = DataStorage.getAppointmentInfoByID_Date(id, date);
        for (int idx = 0; idx < query_result.size(); idx++) {
        	QueryAppointmentData data = query_result.get(idx);
        	String name_str = "---";
        	
        	if (show_by_id) {
        		name_str = name;
        	} else {
        		ArrayList<String> data_list = DataStorage.getPatientInfo(data.id);
        		if (!data_list.isEmpty() ) {
        			name_str = (String)data_list.get(1);
        		}
        	}
        	tableModel.addRow(new Object[]{name_str, data.id, data.reservation_date, data.doctor, data.treatment, data.comment});
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
        
        scrollPane = new JScrollPane(resultTable);
        
        JButton btnCancel = new JButton("取消預約");
        btnCancel.setPreferredSize(new Dimension(200, 50));
        btnCancel.setFont(new Font(btnCancel.getFont().getName(), Font.BOLD, 16));
        
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cancelButtonActionPerformed(e);
            }
        });
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
            	.addGroup(gl_panel.createSequentialGroup()
                    .addGap(400)
                    .addComponent(lblTitle)
                )
            	.addGroup(gl_panel.createSequentialGroup()
                	.addGap(1030)
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
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	int selectedRow = resultTable.getSelectedRow();
    	String selected_id = (String)resultTable.getValueAt(selectedRow, 1);
    	String selected_date = (String)resultTable.getValueAt(selectedRow, 2);
    	String confirmationMessage = "身分證      :  " + selected_id + "\n" + 
									 "預約日期  :  " + selected_date + "\n";

    	int result = JOptionPane.showConfirmDialog(null, confirmationMessage + "\n確定取消?\n\n", "取消預約", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {
			if (DataStorage.delAppointmentInfo(selected_id, selected_date) > 0) {
	    		tableModel.removeRow(selectedRow);
	    		scrollPane.revalidate();
	    		scrollPane.repaint();
	    	} else {
	    		JOptionPane.showMessageDialog(null, "取消預約失敗", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
				return;
	    	}
		}
    }
}
