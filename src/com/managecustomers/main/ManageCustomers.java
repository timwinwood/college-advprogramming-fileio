package com.managecustomers.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ManageCustomers - a class that performs the ManageCustomers app operations
 *
 * @author Tim Winwood - x20213638
 * @version 1.0
 */
public class ManageCustomers {

	// constants
	private static final String APP_NAME = "ManageCustomers";
	private static final String[] APP_OPS = { "Read Customers File Line by Line", "Write Japanese Customers to File", "Write Customers with Country Missing to File", "Exit App" };

	private static final String STR_CUSTOMERS_FILE_PATH = "./data/customers.txt";
	private static final String STR_CUSTOMERS_JAPANESE_FILE_PATH = "./data/Japanese_customers.txt";
	private static final String STR_CUSTOMERS_MISSING_COUNTRY_FILE_PATH = "./data/MissingCountry.txt";

	// instance variables
	private Scanner input;
	private ArrayList<Customer> customers;

	/**
	 * ManageCustomers constructor
	 */
	public ManageCustomers() {

		// user input from scanner
		this.input = new Scanner(System.in);

		// initialise the customers ArrayList
		this.customers = new ArrayList<Customer>();

	}

	/**
	 * ManageCustomers Operation - Write Customers with Country Missing to File
	 */
	public void operationWriteCountryMissingToFile() {

		// -- Q3.C - Write Customers with Missing Country to File --
		
		// check if customers data has been read
		if (this.customers.size() == 0) {

			// no customers found, warn user
			System.out.println("No Customer Data found. Please run \"Read Customer File Line by Line\", or contact your app admin.");

		} else {

			// ask the user if they would like to display the Customers with Country missing as they are added to the list
			String yesNoQuestion = "Would you like to display the Customers with Country missing as they are added to the list?";
			boolean shouldDisplay = displayYesNoSelection(yesNoQuestion);
			
			// ArrayList of Customers with Country missing
			ArrayList<Customer> customersCountryMissing = new ArrayList<Customer>();

			// loop through customers, if country is empty, add to customersCountryMissing
			for (int i = 0; i < customers.size(); i++) {

				String country = customers.get(i).getCountry();
				if (country.equals("")) {
					customersCountryMissing.add(customers.get(i));
					if(shouldDisplay) {
						System.out.println(customers.get(i).toString());
					}	
				}

			}
			
			// write the customersCountryMissing to file
			writeCustomersToFile(customersCountryMissing, STR_CUSTOMERS_MISSING_COUNTRY_FILE_PATH);

		}

		// return user to app menu
		pressEnterReturnToMenu();

	}

	/**
	 * ManageCustomers Operation - Write Japanese Customers to File
	 */
	public void operationWriteJapaneseToFile() {

		// -- Q3.B - Write Japanese Customers to File --
		// Last digit of Student Number is 8, therefore Scenario 4 has been implemented:
		// "All customers whose country is Japan to a file called Japanese_customers.txt."
		
		// check if customers data has been read
		if (this.customers.size() == 0) {

			// no customers found, warn user
			System.out.println("No Customer Data found. Please run \"Read Customer File Line by Line\", or contact your app admin.");

		} else {

			// ask the user if they would like to display the Japanese customers as they are added to the list
			String yesNoQuestion = "Would you like to display the Japenese customers as they are added to the list?";
			boolean shouldDisplay = displayYesNoSelection(yesNoQuestion);
			
			// ArrayList of Japanese Customers
			ArrayList<Customer> customersJapanese = new ArrayList<Customer>();

			// loop through customers, if country is Japan, add to customersJapanese
			for (int i = 0; i < customers.size(); i++) {

				String country = customers.get(i).getCountry();
				if (country.equals("Japan")) {
					customersJapanese.add(customers.get(i));
					if(shouldDisplay) {
						System.out.println(customers.get(i).toString());
					}
				}

			}
			
			// write the customersJapanese to file
			writeCustomersToFile(customersJapanese, STR_CUSTOMERS_JAPANESE_FILE_PATH);

		}

		// return user to app menu
		pressEnterReturnToMenu();

	}

	/**
	 * write customers to file
	 * 
	 * @param customers the customers
	 * @param strFile path to file
	 */
	public static void writeCustomersToFile(ArrayList<Customer> customers, String strFile) {

		// Helper method used by:
		// -- Q3.B - Write Japanese Customers to File --
		// -- Q3.C - Write Customers with Missing Country to File --
		
		// init the PrintWriter as null
		PrintWriter out = null;

		try {

			// init the PrintWriter, passing a FileWriter via the Constructor
			out = new PrintWriter(new FileWriter(strFile));

			// loop through the customers and print to the file
			for (int i = 0; i < customers.size(); i++) {
				out.println(customers.get(i).toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			// close the PrintWriter
			if (out != null) {
				out.close();
			}

		}

	}

	/**
	 * ManageCustomers Operation - Read Customers File Line by Line
	 */
	public void operationReadFile() {

		// -- Q3.A - Read File Line by Line --
		
		// read the file to ArrayList<String[]> data 
		ArrayList<String[]> data = ManageCustomers.readCSVFile(STR_CUSTOMERS_FILE_PATH, 1);

		// convert ArrayList<String[]> data to ArrayList<Customer>()
		this.customers = new ArrayList<Customer>();
		Customer c = null;
		for (int i = 0; i < data.size(); i++) {
			c = new Customer(Integer.parseInt(data.get(i)[0]), data.get(i)[1], data.get(i)[2], data.get(i)[3], data.get(i)[4]);
			this.customers.add(c);
		}

		// return user to app menu
		pressEnterReturnToMenu();

	}

	/**
	 * reads a csv file to ArrayList<String[]>
	 * 
	 * @param strCSVPath path to csv file
	 * @param headerCount headers to skip. To include headers, set to 0
	 * 
	 * @return the csv data
	 */
	public static ArrayList<String[]> readCSVFile(String strFile, int headers) {

		// -- Q3.A - Read File Line by Line --
		
		// init the ArrayList<String[]> to store the data
		ArrayList<String[]> data = new ArrayList<>();

		// init the BufferedReader as null
		BufferedReader br = null;
		
		try {
			
			// init the BufferedReader passing a FileReader via the Constructor
			br = new BufferedReader(new FileReader(strFile));
			
			// read the lines in the file
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				
				// print the line to the console
				System.out.println(line);
				
				// skip headers
				if (i > headers) {
					data.add(line.split(","));
				}
				
				i++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// close the BufferedReader
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;

	}

	/**
	 * press enter key to return to app menu
	 */
	public void pressEnterReturnToMenu() {

		System.out.println("Press Enter key to return to " + APP_NAME + " App Menu...");

		try {

			// read the next key
			System.in.read();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	/**
	 * @return the menu selection
	 */
	public int displayAppMenu() {

		// show the menu
		System.out.println("-- " + APP_NAME + " App Menu --");
		for (int i = 0; i < APP_OPS.length; i++) {
			System.out.println((i) + " - " + APP_OPS[i]);
		}
		System.out.println("Enter Your Selection:");

		// read the users selection, if out of bounds, call this method again
		// recursively
		int intSelection = input.nextInt();
		if (!(intSelection >= 0 && intSelection <= APP_OPS.length - 1)) {
			System.out.println("Invalid Selection. Selection must be between 0 and " + (APP_OPS.length - 1));
			return displayAppMenu();
		}

		// return the menu selection
		return intSelection;

	}

	/**
	 * exit the app
	 */
	public void exitApp() {

		System.exit(0);

	}

	/**
	 * shows an invalid operation message, returns the user to the app menu
	 */
	public void invalidOperation() {

		System.out.println("Invalid Selection. Please enter a selection between 0 and " + APP_OPS.length + ".");

		// return user to app menu
		pressEnterReturnToMenu();

	}

	/**
	 * @param the question
	 * @return the user selection (yes/no)
	 */
	public boolean displayYesNoSelection(String question) {

		// show the question
		System.out.println(question + "(y/n)");

		// user selection, if "no" or "n" return false, if "yes" or "y" return true, 
		// else invalid user input, call this method again recursively
		String strSelection = input.next();
		if (strSelection.equalsIgnoreCase("n") || strSelection.equalsIgnoreCase("no")) {
			return false;
		} else if (strSelection.equalsIgnoreCase("y") || strSelection.equalsIgnoreCase("yes")) {
			return true;
		} else {
			System.out.println("Invalid Selection. Selection must be (y/n).");
			return displayYesNoSelection(question);
		}

	}

}
