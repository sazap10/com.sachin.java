import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

public class NumberBase extends JFrame {
	private JRadioButton rbDec, rbOct, rbBin;
	private ButtonGroup rbgNumber;
	private JTextField tfInput;
	private JPanel pnRadio, pnInput;
	private int number;

	// private String number;

	public NumberBase() {
		super("Number Base");
		rbDec = new JRadioButton("Decimal", true);
		rbOct = new JRadioButton("Octal");
		rbBin = new JRadioButton("Binary");
		rbgNumber = new ButtonGroup();
		tfInput = new JTextField(20);
		ItemListener handler;

		pnRadio = new JPanel();
		pnInput = new JPanel();

		rbgNumber.add(rbDec);
		rbgNumber.add(rbOct);
		rbgNumber.add(rbBin);
		pnRadio.setLayout(new GridLayout(0, 1));
		pnRadio.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		pnRadio.add(rbDec);
		pnRadio.add(rbOct);
		pnRadio.add(rbBin);
		pnInput.add(tfInput);
		tfInput.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (rbDec.isSelected()) {
					if(c== '\b'){
						try {
							number = Integer.parseInt(tfInput.getText());
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							number = 0;
						}
					}
					else if (Character.isDigit(c)) {
						// number = tfInput.getText()+String.valueOf(c);
						try {
							number = Integer.parseInt(tfInput.getText()
									+ String.valueOf(c));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e.consume();
						}
					} else {
						e.consume();
					}

				} else if (rbOct.isSelected()) {
					if(c== '\b'){
						try {
							number = Integer.parseInt(tfInput.getText(),8);
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							number = 0;
						}
					}
					else if (Character.isDigit(c) && c != '9' && c != '8') {
						// number = tfInput.getText()+String.valueOf(c);
						try {
							number = Integer.parseInt(
									tfInput.getText() + String.valueOf(c), 8);
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e.consume();
						}
					} else {
						e.consume();
					}
				} else if (rbBin.isSelected()) {
					if(c== '\b'){
						try {
							number = Integer.parseInt(tfInput.getText(),2);
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							number = 0;
						}
					}
					else if (c == '0' || c == '1') {
						// number = tfInput.getText()+String.valueOf(c);
						try {
							number = Integer.parseInt(
									tfInput.getText() + String.valueOf(c), 2);
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e.consume();
						}
					} else {
						e.consume();
					}
				} else {
					e.consume();
				}

			}

		});
		handler = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				ItemSelectable Source;
				Source = e.getItemSelectable();
				if (Source == rbDec) {
					tfInput.setText("" + Integer.toString(number));
				} else if (Source == rbOct) {
					tfInput.setText("" + Integer.toOctalString(number));
				} else if (Source == rbBin) {
					tfInput.setText("" + Integer.toBinaryString(number));
				}
			}

		};
		rbDec.addItemListener(handler);
		rbOct.addItemListener(handler);
		rbBin.addItemListener(handler);

		add(pnRadio, BorderLayout.WEST);
		add(pnInput, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new NumberBase();
			}
		});
	}
}
