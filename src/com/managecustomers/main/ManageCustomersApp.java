package com.managecustomers.main;

/**
 * ManageCustomersApp - a class that provides customer management functionality
 *
 * @author Tim Winwood - x20213638
 * @version 1.0
 */
public class ManageCustomersApp {

	/**
	 * entry point to the  ManageCustomers app, handles user input from the main app menu
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {

		ManageCustomers manageCustomers = new ManageCustomers();

		while (true) {

			int intSelection = manageCustomers.displayAppMenu();

			switch (intSelection) {
			case 0:
				manageCustomers.operationReadFile();
				break;
			case 1:
				manageCustomers.operationWriteJapaneseToFile();
				break;
			case 2:
				manageCustomers.operationWriteCountryMissingToFile();
				break;
			case 3:
				manageCustomers.exitApp();
				break;
			default:
				manageCustomers.invalidOperation();
			}
		}
	}

}
