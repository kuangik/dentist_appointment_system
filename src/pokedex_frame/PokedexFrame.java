package pokedex_frame;

import java.io.File;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class MyJFrame extends JFrame {
	private JPanel contentPane, rightPanel;
	private JPanel panel, panel_1;
	private JTextField idxTextField, strengthTextField, attackTextField, defTextField;
	private JTextField attTextField, hpTextField, speedTextField, moveTextField;
	private JTextField defenseTextField, levelTextField, charTextField;
	private static String userHome = System.getProperty("user.home");
	private static String basePath = userHome + File.separator + "Downloads";
	
	MyJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,650,450);
		setVisible(true);
		setResizable(false);
		
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST);
        
        JButton btnBrowse = new JButton("Browse");
        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browseButtonActionPerformed(e);
            }
        });
        
        JButton btnSave = new JButton("儲存");
        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //browseButtonActionPerformed(e);
            }
        });
        
        panel_1 = new JPanel();
        panel_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        
        JLabel lblSelectTargetPicture = new JLabel("Select image...");
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(6)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(lblSelectTargetPicture)
                            .addGap(6)
                            .addComponent(btnBrowse))))
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(130)
                    .addComponent(btnSave)
                )
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(7)
                            .addComponent(lblSelectTargetPicture))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(3)
                            .addComponent(btnBrowse)))
                    .addGap(18)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnSave)
                )
        );
        
        panel.setLayout(gl_panel);
        
        rightPanel = new JPanel();
        contentPane.add(rightPanel, BorderLayout.CENTER);
        
        GroupLayout gl_right_panel = new GroupLayout(rightPanel);
        
        JLabel idxLabel = new JLabel("編號");
        JLabel nameLabel = new JLabel("名稱");
        JLabel attackLabel = new JLabel("特攻");
        JLabel defLabel = new JLabel("特防");
        JLabel attLabel = new JLabel("屬性");
        JLabel hpLabel = new JLabel("HP ");
        JLabel speedLabel = new JLabel("速度");
        JLabel moveLabel = new JLabel("招式");
        JLabel defenseLabel = new JLabel("防禦");
        JLabel levelLabel = new JLabel("等級");
        JLabel charLabel = new JLabel("特性");
        	
        idxTextField = new JTextField();
        idxTextField.setColumns(5);
        
        strengthTextField = new JTextField();
        strengthTextField.setColumns(5);
        
        attackTextField = new JTextField();
        attackTextField.setColumns(5);
        
        defTextField = new JTextField();
        defTextField.setColumns(5);
        
        attTextField = new JTextField();
        attTextField.setColumns(5);
        
        hpTextField = new JTextField();
        hpTextField.setColumns(5);
        
        speedTextField = new JTextField();
        speedTextField.setColumns(5);
        
        moveTextField = new JTextField();
        moveTextField.setColumns(5);
        
        defenseTextField = new JTextField();
        defenseTextField.setColumns(5);
        
        levelTextField = new JTextField();
        levelTextField.setColumns(5);
        
        charTextField = new JTextField();
        charTextField.setColumns(10);
		
		gl_right_panel.setHorizontalGroup(
			gl_right_panel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_right_panel.createSequentialGroup()
		            .addGap(12)
		            .addGroup(gl_right_panel.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_right_panel.createSequentialGroup()
		                	.addGap(20)
		                    .addComponent(idxLabel)
		                    .addGap(20)
		                    .addComponent(idxTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                	.addGap(30)
		                	.addComponent(attackLabel)
		                	.addGap(20)
		                	.addComponent(attackTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                
			         .addGroup(gl_right_panel.createSequentialGroup()
			         	.addGap(20)
			                .addComponent(nameLabel)
			                .addGap(20)
			                .addComponent(strengthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addGap(30)
			         	.addComponent(defLabel)
			         	.addGap(20)
			         	.addComponent(defTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			         
			         .addGroup(gl_right_panel.createSequentialGroup()
			            .addGap(20)
			             .addComponent(attLabel)
			             .addGap(20)
			             .addComponent(attTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			         .addGap(30)
			            .addComponent(hpLabel)
			            .addGap(20)
			            .addComponent(hpTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			         
			         .addGroup(gl_right_panel.createSequentialGroup()
			         .addGap(20)
			            .addComponent(speedLabel)
			            .addGap(20)
			            .addComponent(speedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				       .addGap(30)
			         .addComponent(moveLabel)
			         .addGap(20)
			         .addComponent(moveTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			         
			         .addGroup(gl_right_panel.createSequentialGroup()
				       .addGap(20)
			         .addComponent(defenseLabel)
			         .addGap(20)
			         .addComponent(defenseTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addGap(30)
				       .addComponent(levelLabel)
				       .addGap(20)
				       .addComponent(levelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			         
			         .addGroup(gl_right_panel.createSequentialGroup()
				    .addGap(20)
				       .addComponent(charLabel)
				       .addGap(20)
				       .addComponent(charTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		        )
	     	)
		);
		gl_right_panel.setVerticalGroup(
			gl_right_panel.createSequentialGroup()
	            .addGroup(gl_right_panel.createSequentialGroup()
	            	.addGap(40)
	            	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
	            		.addComponent(idxLabel)
		                .addComponent(idxTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            	.addComponent(attackLabel)
		                .addComponent(attackTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            	.addGap(25)
	            	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
		            	.addComponent(nameLabel)
			            .addComponent(strengthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(defLabel)
			            .addComponent(defTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addGap(25)
	            	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
		            	.addComponent(attLabel)
			            .addComponent(attTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(hpLabel)
			            .addComponent(hpTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addGap(25)
	            	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
		            	.addComponent(speedLabel)
			            .addComponent(speedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(moveLabel)
			            .addComponent(moveTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            	.addGap(25)
	            	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
		            	.addComponent(defenseLabel)
			            .addComponent(defenseTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(levelLabel)
			            .addComponent(levelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            	.addGap(25)
	            	.addGroup(gl_right_panel.createParallelGroup(Alignment.BASELINE)
		            	.addComponent(charLabel)
			            .addComponent(charTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            )
        );
        rightPanel.setLayout(gl_right_panel);
	}
        
        
	public static void main(String[] args) {
		MyJFrame f = new MyJFrame();
		f.setTitle("Pokedex");
		
		//JPanel panel = new JPanel();
		//f.getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fc = new JFileChooser(basePath);
        //fc.setFileFilter(new JPEGImageFileFilter());
        int res = fc.showOpenDialog(null);
        // We have an image!
        try {
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //setTarget(file);
                System.out.println(file.getAbsolutePath());
                
                ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                Image originalImage = imageIcon.getImage();
                
                Image resizedImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
                
                JLabel imageLabel = new JLabel(resizedImageIcon);
                
                panel_1.removeAll();
                
                panel_1.add(imageLabel);
                
                panel_1.revalidate();
                
                basePath = file.getParent().toString();
            } // Oops!
            else {
                JOptionPane.showMessageDialog(null,
                        "You must select one image to be the reference.", "Aborting...",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception iOException) {
        }
    
    }
}

public class PokedexFrame {
	public static void main(String[] args) {
		MyJFrame f = new MyJFrame();
		f.setTitle("Pokedex");
	}
}

