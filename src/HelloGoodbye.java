import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Swing based application to show buttons and actions.  The application displays a string and a
 * button and when the button is pressed the string changes.
 *
 * @author Graham Roberts
 * @author Russel Winder
 * @version 2005-06-28
 */
public class HelloGoodbye extends JFrame
{
  //  The two possible strings to display.
  private static final String[] messages = new String[]{"First Item","Second Item","Third Item"};
  /**
   * The current value of string displayed
   */
  private int i = 0;
  private String message = messages[i];
  //  References to components used in interface.
  private final JPanel mesPanel;
  private final JPanel btnPanel;
  private final JButton backButton;
  private final JButton forButton;
  private final JLabel aLabel;

  public HelloGoodbye()
  {
    super("Messages");
    //  Create the main components first and then configure them.
    mesPanel = new JPanel();
    btnPanel = new JPanel();
    backButton = new JButton("Backward"); 
    forButton = new JButton("Forward");
    aLabel = new JLabel(message, SwingConstants.CENTER);
    //  Set the border around the widgets and
    //  set the layout manager -- the default is FlowLayout but we want to use
    //  BorderLayout. Then add the components in at the right section of the
    //  BorderLayout.
    mesPanel.setLayout(new BorderLayout());
    mesPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60)); 
    mesPanel.add(aLabel, BorderLayout.CENTER);
    btnPanel.setLayout(new FlowLayout());
    btnPanel.add(backButton);
    btnPanel.add(forButton);
    //  Use an anonymous inner class to create an action listener for the
    //  button.  Because the anonymous inner class has a connection to the
    //  HelloGoodbye object it can use references and methods in this
    //  method / object, i.e. it has access to the message and aLabel
    //  instance variables.
    backButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent e)
      {
    	  if(i == 0){
    		  i = messages.length-1;
    	  }
    	  else{
    		  i--;
    	  }
        message = messages[i];
        aLabel.setText(message);
      }
    });
    
    forButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent e)
      {
    	  if(i == messages.length -1){
    		  i = 0;
    	  }
    	  else{
    		  i++;
    	  }
        message = messages[i];
        aLabel.setText(message);
      }
    });
    //  Add the panel to the frame (window).
    add(mesPanel);
    add(btnPanel,BorderLayout.SOUTH);
    //  Set the ability to close the window and hence the
    //  application using the window managers close button, pack the components
    //  in the frame and finally display the frame.
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
        new HelloGoodbye();
      }
    });
  }
}