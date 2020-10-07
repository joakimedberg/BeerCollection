package nackademin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * The Graphic User Interface for a beer collection program. 
 * @author Joakim Edberg
 *
 */
public class GraphicUserInterface {
	
	private Database db;
	
	public static void main(String[] args) {
		GraphicUserInterface gui = new GraphicUserInterface();
	}

	/**
	 * A constructor that initializes the GUI and its associated database.
	 */
	public GraphicUserInterface() {
		db = new Database();
		
		initGUI();

	}

	/**
	 * Initializes the graphic user interface; creates its frame, components and sets its attributes.
	 */
	private void initGUI() {
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		JLabel label = new JLabel("BEER COLLECTION");
		JLabel label2 = new JLabel("NAME");
		JLabel label3 = new JLabel("TYPE");
		JLabel label4 = new JLabel("%");

		JTextField name = new JTextField();
		JTextField strength = new JTextField();
		
		JComboBox<String> type = new JComboBox<String>(new String[] { "Lager", "IPA", "Ale", "Stout", "Sour" });
		
		JButton add = new JButton("ADD");
		JButton delete = new JButton("DELETE");

		DefaultTableModel dtm = new DefaultTableModel(getItemsForTable(), new String[] { "name", "type", "%" });
		JTable table = new JTable(dtm);

		// x, y, width, height
		label.setBounds(100, 1, 200, 25);
		label2.setBounds(50, 25, 200, 25);
		label3.setBounds(155, 25, 200, 25);
		label4.setBounds(230, 25, 200, 25);
		name.setBounds(5, 50, 125, 25);
		type.setBounds(135, 50, 75, 25);
		strength.setBounds(215, 50, 40, 25);
		add.setBounds(260, 50, 70, 25);
		table.setBounds(5, 100, 335, 200);
		delete.setBounds(240, 300, 100, 25);

		panel.add(label);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(name);
		panel.add(type);
		panel.add(strength);
		panel.add(add);
		panel.add(table);
		panel.add(delete);

		panel.setLayout(null);

		frame.add(panel);

		frame.setSize(350, 400);
		frame.setVisible(true);

		
		// Adds a new Beer to database and table.
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (!name.getText().isEmpty() && !strength.getText().isEmpty()) {
						db.addBeer(new Beer(name.getText(), type.getSelectedItem().toString(), strength.getText()));
						dtm.addRow(
								new Object[] { name.getText(), type.getSelectedItem().toString(), strength.getText() });
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});

		// Removes the selected row from table and its correlate in database.
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						db.deleteBeer(table.getSelectedRow());
						dtm.removeRow(table.getSelectedRow());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
	}

	/**
	 * Converts beers into a multidimensional array and returns it.
	 * @return beers as a multidimensional array
	 */
	private String[][] getItemsForTable() {
		List<Beer> beers = db.getBeers();
		String[][] tableList = new String[beers.size()][3];
		int i = 0;

		for (Beer b : beers) {
			tableList[i][0] = b.getName();
			tableList[i][1] = b.getType();
			tableList[i][2] = b.getStrength();
			++i;
		}

		return tableList;
	}
}