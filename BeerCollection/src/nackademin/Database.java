package nackademin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the database for the beer collection program.
 * 
 * @author Joakim Edberg
 *
 */
public class Database {
	private List<Beer> beers;
	
	/**
	 * Constructs Database and retrieves the beers from the database.
	 * <p>
	 * Constructs Database and instantiate and populates the beers List with the information stored in the database.
	 */
	public Database() {
		beers = new ArrayList<Beer>();
		beers = getBeersFromDb();

	}

	/**
	 * Retrieves the beers from the database.
	 * <p>
	 * Retrieves the information from the database, converts it to suitable Strings for Beer objects and stores it in a List. 
	 * Also calls on a method that verifies that the database exists. 
	 * @return the beers
	 */
	private List<Beer> getBeersFromDb() {
		
		String name = "", type = "", strength = "", row = "";
		int count = 0, pos = 0;

		// if database doesn't exist
		createDb();

		try {
			BufferedReader reader = new BufferedReader(new FileReader("beerCollection.txt"));

			// Converts the information from database into its Beer representative and stores it in List
			while ((row = reader.readLine()) != null) {
				for (int i = 0; i < row.length(); i++) {
					if (count == 0 && row.charAt(i) == '/') {
						name = row.substring(0, i);
						++count;
						pos = i;
					} else if (count == 1 && row.charAt(i) == '/') {
						type = row.substring(++pos, i);
						strength = row.substring(++i, row.length());
						break;
					}
				}
				beers.add(new Beer(name, type, strength));
				count = 0;
				pos = 0;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return beers;
	}

	/**
	 * Creates a database if none exist already.
	 */
	private void createDb() {
		File f = new File("beerCollection.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Adds a Beer to the database.
	 * 
	 * @param beer Object representing a beer
	 * @throws IOException
	 */
	public void addBeer(Beer beer) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter("beerCollection.txt", true));
		if (!beers.isEmpty()) {
			writer.newLine();
		}
		writer.write(beer.getName() + "/" + beer.getType() + "/" + beer.getStrength());
		writer.close();

		beers.add(beer);

	}

	/**
	 * Deletes a Beer from the database.
	 * <p>
	 * Uses the row id from the table passed as argument, which corresponds to the index of the beer in the list,
	 * removes it from the list and rewrites the database based on the list.
	 * 
	 * @param id the row id of the table
	 * @throws IOException
	 */
	public void deleteBeer(int id) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("beerCollection.txt", false));

		if (id == 0) {
			beers.clear();
		} else {
			beers.remove(id);
		}

		for (Beer b : beers) {
			writer.write(b.getName() + "/" + b.getType() + "/" + b.getStrength());
			writer.newLine();
		}

		writer.close();
	}

	/**
	 * Returns the beers.
	 * 
	 * @return the beers
	 */
	public List<Beer> getBeers() {
		return beers;
	}
}
