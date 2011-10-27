import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class SimpleInput extends JFrame {
	private JTextField tfInput;
	private JButton btnCopy;
	private JLabel lbMessage;
	private JPanel pnTop;
	private JPanel pnBot;
	
	public SimpleInput(){
		super("Simple Input");
		
		pnTop = new JPanel();
		pnBot = new JPanel();
		tfInput = new JTextField("Type Here", 20);
		btnCopy = new JButton("Copy Text");
		lbMessage = new JLabel("Your Input: "+ tfInput.getText());
		
		pnTop.add(tfInput);
		pnTop.add(btnCopy);
		pnBot.setLayout(new BorderLayout());
		pnBot.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
		pnBot.add(lbMessage);
		
		add(pnTop, BorderLayout.NORTH);
		add(pnBot);
		
		btnCopy.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String input = tfInput.getText();
				lbMessage.setText("Your Input: "+input);
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
	        new SimpleInput();
	      }
	    });
	  }
}
