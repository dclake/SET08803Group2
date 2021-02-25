package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database locally for testing
                //con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=false", "root", "example");
                // Connect to database via Docker
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Gets all Countries and Polulations from largest to smallest.
     * @return A list of all countries and populations, or null if there is an error.
     */
    public ArrayList<Country> getCountryByPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population "
                            + "FROM world.country "
                            + "ORDER BY Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.country_name = rset.getString("country.name");
                country.Population = rset.getInt("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    /**
     * Prints a list of countries and population from largest to smallest.
     * @param countries The list of countries to print.
     */
    public void printCountries(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("______________________________________________________________");
        System.out.println(String.format("%-50s %-15s ", "Country", "Polulation"));
        System.out.println("______________________________________________________________");
        // Loop over all countries in the list
        for (Country country : countries)
        {
            String countries_string =
                    String.format("%-50s %-15s ",
                            country.country_name, country.Population);
            System.out.println(countries_string);
        }
    }
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Extract country population information
        ArrayList<Country> countries = a.getCountryByPopulation();

        // Test the size of the returned data - should be 239
        //System.out.println(countries.size());
        a.printCountries(countries);


        // Disconnect from database
        a.disconnect();
    }

}
