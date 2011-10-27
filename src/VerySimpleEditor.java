import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VerySimpleEditor extends JFrame {
	private JPanel editorPanel, pnStatusBar;
	private JTextArea editor;
	private JScrollPane scroller;
	private JMenuItem loadButton, saveButton, cutButton, copyButton,
			pasteButton, newButton, exitButton, undoButton;
	private JMenuBar menuBar;
	private JMenu mnFile, mnEdit;
	private JLabel statusBarFile, statusBarLineNum, statusBarAltered;
	private UndoManager undoManager;

	public VerySimpleEditor() {
		createGUI();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void createGUI() {
		setTitle("Very simple editor");
		createEditorPanel();
		lineNumListen();
		alteredListen();
		createMenu();
		createStatusBar();
		createTopPanel();
	}

	private void createStatusBar() {
		pnStatusBar = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		statusBarFile = new JLabel("untitled");
		statusBarLineNum = new JLabel("Line Number: 1");
		statusBarAltered = new JLabel("Not Altered");
		statusBarAltered.setForeground(Color.GREEN);
		pnStatusBar.add(statusBarFile);
		pnStatusBar.add(statusBarLineNum);
		pnStatusBar.add(statusBarAltered);
	}

	private void createTopPanel() {
		setJMenuBar(menuBar);
		add(editorPanel, BorderLayout.CENTER);
		add(pnStatusBar, BorderLayout.SOUTH);
	}

	private void lineNumListen() {
		editor.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				int offset = editor.getCaretPosition();
				try {
					int line = editor.getLineOfOffset(offset) + 1;
					statusBarLineNum.setText("Line Number: " + line);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	}

	private void alteredListen() {
		editor.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				statusBarAltered.setForeground(Color.RED);
				statusBarAltered.setText("Altered");
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				statusBarAltered.setForeground(Color.RED);
				statusBarAltered.setText("Altered");
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void createEditorPanel() {
		editor = new JTextArea();
		editor.setColumns(40);
		editor.setRows(20);
		scroller = new JScrollPane(editor);
		editorPanel = new JPanel();
		editorPanel.setLayout(new BorderLayout());
		editorPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		editorPanel.add(scroller, BorderLayout.CENTER);
	}

	private void createMenu() {
		menuBar = new JMenuBar();
		mnFile = new JMenu("File");
		mnEdit = new JMenu("Edit");
		menuBar.add(mnFile);
		menuBar.add(mnEdit);
		createNewButton();
		createExitButton();
		createLoadButton();
		createSaveButton();
		createCutButton();
		createCopyButton();
		createPasteButton();
		createUndoButton();
		mnFile.add(newButton);
		mnFile.addSeparator();
		mnFile.add(loadButton);
		mnFile.add(saveButton);
		mnFile.addSeparator();
		mnFile.add(exitButton);
		mnEdit.add(undoButton);
		mnEdit.addSeparator();
		mnEdit.add(cutButton);
		mnEdit.add(copyButton);
		mnEdit.add(pasteButton);
	}

	private void createUndoButton() {
		undoButton = new JMenuItem("Undo");
		undoButton.setEnabled(false);
		undoManager = new UndoManager();
		editor.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					public void undoableEditHappened(UndoableEditEvent e) {
						undoManager.addEdit(e.getEdit());
						updateUndo();
					}
				});
		undoButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		          try {
		            undoManager.undo();
		          } catch (CannotRedoException cre) {
		            cre.printStackTrace();
		          }
		          updateUndo();
		        }
		      });
	}

	private void updateUndo() {
		undoButton.setText(undoManager.getUndoPresentationName());
	    undoButton.setEnabled(undoManager.canUndo());
	}

	private void createNewButton() {
		newButton = new JMenuItem("New");
		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VerySimpleEditor();
			}

		});
	}

	private void createExitButton() {
		// TODO Auto-generated method stub
		exitButton = new JMenuItem("Exit");
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});
	}

	private void createLoadButton() {
		loadButton = new JMenuItem("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFile();
			}
		});
	}

	private void createSaveButton() {
		saveButton = new JMenuItem("Save...");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
	}

	private void createCutButton() {
		cutButton = new JMenuItem("Cut");
		cutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cut();
			}
		});
	}

	private void createCopyButton() {
		copyButton = new JMenuItem("Copy");
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy();
			}
		});
	}

	private void createPasteButton() {
		pasteButton = new JMenuItem("Paste");
		pasteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paste();
			}
		});
	}

	// Display a file chooser and load a file.
	private void loadFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				editor.read(new FileReader(file), null);
				statusBarFile.setText("" + file.getName());
				statusBarAltered.setForeground(Color.GREEN);
				statusBarAltered.setText("Not Altered");
			} catch (IOException exp) {
			}
		}
	}

	// Display a file chooser and save a file.
	private void saveFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				editor.write(new FileWriter(file));
				statusBarFile.setText("" + file.getName());
				statusBarAltered.setForeground(Color.GREEN);
				statusBarAltered.setText("Not Altered");
			} catch (IOException exp) {
			}
		}
	}

	// Cut, copy and paste are implemented using methods provided
	// by a superclass of the JTextArea class, and work with
	// the system clipboard.
	// Very useful and makes it very easy to implement cut/copy/paste!!
	//
	// The requestFocus method is used to make the JTextArea the active
	// component after a button is clicked. The component with the focus
	// receives the input events. If focus is not returned to the JTextArea
	// it remains with the button, preventing text being entered into
	// the text area until it is clicked on to regain focus.
	// Try commenting out the calls to requestFocus() to see the
	// difference in behaviour.
	private void cut() {
		editor.cut();
		editor.requestFocus();
	}

	private void copy() {
		editor.copy();
		editor.requestFocus();
	}

	private void paste() {
		editor.paste();
		editor.requestFocus();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VerySimpleEditor();
			}
		});
	}
}