import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class ContactForm extends JFrame {
	private JTextField tfName,tfPhone,tfMobile,tfEmail;
	private JLabel lbName,lbPhone,lbMobile,lbEmail;
	private JButton btnClear,btnAccept;
	private JPanel pnLabel,pnBtn,pnText;
	
	public ContactForm(){
		super("Contact Form");
		tfName = new JTextField(20);
		tfPhone = new JTextField(20);
		tfMobile = new JTextField(20);
		tfEmail = new JTextField(20);
		lbName = new JLabel("Name: ", JLabel.RIGHT);
		lbPhone = new JLabel("Phone: ", JLabel.RIGHT);
		lbMobile = new JLabel("Mobile: ", JLabel.RIGHT);
		lbEmail = new JLabel("Email: ", JLabel.RIGHT);
		btnClear = new JButton("Clear");
		btnAccept = new JButton("Accept");
		pnLabel= new JPanel();
		pnBtn = new JPanel();
		pnText = new JPanel();

		pnLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 5));
		pnText.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 15)); 
		pnBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); 
		pnLabel.setLayout(new GridLayout(4,1,10,10));
		pnText.setLayout(new GridLayout(4,1,10,10));
		pnLabel.add(lbName);
		pnLabel.add(lbPhone);
		pnLabel.add(lbMobile);
		pnLabel.add(lbEmail);
		pnText.add(tfName);
		pnText.add(tfPhone);
		pnText.add(tfMobile);
		pnText.add(tfEmail);
		
		pnBtn.add(btnClear);
		pnBtn.add(btnAccept);
		add(pnText,BorderLayout.EAST);
		add(pnLabel,BorderLayout.WEST);
		add(pnBtn,BorderLayout.SOUTH);
		
		btnClear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfName.setText("");
				tfPhone.setText("");
				tfMobile.setText("");
				tfEmail.setText("");
			}
			
		});
		
		btnAccept.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame newWin = new JFrame("Contact");
				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 150));
				panel.setLayout(new GridLayout(4,0));
				panel.add(new JLabel(""+tfName.getText()));
				panel.add(new JLabel(""+tfPhone.getText()));
				panel.add(new JLabel(""+tfMobile.getText()));
				panel.add(new JLabel(""+tfEmail.getText()));
				newWin.add(panel);
				newWin.pack();
				newWin.setVisible(true);
				
			}
			
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	public static void main(final String[] args)
	  {
	    SwingUtilities.invokeLater(new Runnable()
	    {
	      public void run()
	      {
	        new ContactForm();
	      }
	    });
	  }
}
