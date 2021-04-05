package com.napier.sem;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.ArrayList;
import java.io.IOException;

//@SpringBootApplication
//@RestController
public class App {
    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;
    public static java.sql.Connection getCon()
    {
        return con;
    }

    /**
     * Connect to the MySQL database.
     */
    public static void connect(String location) {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database locally for testing
                //con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=false", "root", "example");
                // Connect to database via Docker
                //con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public static void disconnect() {
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
    //@RequestMapping("countries")
    public ArrayList<Country> getCountryByPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, country.Capital, city.ID, city.Name as CapitalCity "
                            + "FROM world.country, world.city "
                            + "where country.Capital = city.ID "
                            + "ORDER BY Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.country_code = rset.getString("country.code");
                country.country_name = rset.getString("country.name");
                country.continent = rset.getString("country.continent");
                country.Region = rset.getString("country.Region");
                country.Population = rset.getInt("country.Population");
                country.Capital = rset.getString("CapitalCity");
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
    //@RequestMapping("countriesbycontinent")
    public ArrayList<Country> getCountryByContinet( String continent)
    {
        try
        {
            //System.out.println("Request for all countries in " + continent +);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, country.Capital, city.ID, city.Name as CapitalCity "
                            + "FROM world.country, world.city "
                            + "where country.Continent Like '" + continent + "' "
                            + "AND country.Capital = city.ID "
                            + "ORDER BY Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.country_code = rset.getString("country.code");
                country.country_name = rset.getString("country.name");
                country.continent = rset.getString("country.continent");
                country.Region = rset.getString("country.Region");
                country.Population = rset.getInt("country.Population");
                country.Capital = rset.getString("CapitalCity");
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
    public ArrayList<Country> getTopNCountryByContinet(  String continent, int N)
    {
        try
        {
            //System.out.println("Request for all countries in " + continent +);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, country.Capital, city.ID, city.Name as CapitalCity "
                            + "FROM world.country, world.city "
                            + "where country.Continent Like '" + continent + "' "
                            + "AND country.Capital = city.ID "
                            + "ORDER BY Population desc "
                            + "LIMIT 0, " + N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.country_code = rset.getString("country.code");
                country.country_name = rset.getString("country.name");
                country.continent = rset.getString("country.continent");
                country.Region = rset.getString("country.Region");
                country.Population = rset.getInt("country.Population");
                country.Capital = rset.getString("CapitalCity");
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
   // @RequestMapping("countriesbyregion")
    public ArrayList<Country> getCountryByRegion(  String region)
    {
        try
        {
            //System.out.println("Request for all countries in " + continent +);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, country.Capital, city.ID, city.Name as CapitalCity "
                            + "FROM world.country, world.city "
                            + "where country.Region Like '" + region + "' "
                            + "AND country.Capital = city.ID "
                            + "ORDER BY Population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.country_code = rset.getString("country.code");
                country.country_name = rset.getString("country.name");
                country.continent = rset.getString("country.continent");
                country.Region = rset.getString("country.Region");
                country.Population = rset.getInt("country.Population");
                country.Capital = rset.getString("CapitalCity");
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
    public ArrayList<Country> getTopNCountryByRegion(  String region, int N)
    {
        try
        {
            //System.out.println("Request for all countries in " + continent +);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, country.Capital, city.ID, city.Name as CapitalCity "
                            + "FROM world.country, world.city "
                            + "where country.Region Like '" + region + "' "
                            + "AND country.Capital = city.ID "
                            + "ORDER BY Population desc "
                            + "LIMIT 0, " + N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.country_code = rset.getString("country.code");
                country.country_name = rset.getString("country.name");
                country.continent = rset.getString("country.continent");
                country.Region = rset.getString("country.Region");
                country.Population = rset.getInt("country.Population");
                country.Capital = rset.getString("CapitalCity");
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
    public ArrayList<Country> getTopNCountryByPopulation(int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, country.Capital, city.ID, city.Name as CapitalCity "
                            + "FROM world.country, world.city "
                            + "where country.Capital = city.ID "
                            + "ORDER BY Population desc "
                            + "LIMIT 0, "+ N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.country_code = rset.getString("country.code");
                country.country_name = rset.getString("country.name");
                country.continent = rset.getString("country.continent");
                country.Region = rset.getString("country.Region");
                country.Population = rset.getInt("country.Population");
                country.Capital = rset.getString("CapitalCity");
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
        // Check countries is not null
        if (countries == null) {
            System.out.println("No countries");
            return;
        }
        // Print header
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        System.out.println(String.format("%4s %-52s %-15s %-26s %-15s %35s", "Code", "Country", "Continent", "Region", "Population", "Capital City"));
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        // Loop over all countries in the list
        for (Country country : countries)
        {
            if (country == null)
                continue;
            String countries_string =
                    String.format("%4s %-52s %-15s %-26s %-15s %35s",
                            country.country_code, country.country_name, country.continent, country.Region, country.Population, country.Capital);
            System.out.println(countries_string);
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException  {
        // Connect to database
        if (args.length < 1)
        {
            //connect();
            // Create new Application
            App a = new App();

            // Connect to database
            a.connect("localhost:33060");

            // Extract country population information
           // ArrayList<Country> countries = a.getTopNCountryByRegion("Caribbean",6);
            ArrayList<City> cities = City.getDistrictCities("New York");
            City.printCities(cities);





            // Test the size of the returned data - should be 239
          //  System.out.println(countries.size());
           //  a.printCountries(countries);
            // City.printCities(cities);
        }
        else
        {
            connect(args[0]);
        }
        //String command = "curl http://app:8080/countriesbyregion?region=Caribbean";
        // SpringApplication.run(App.class, args);



        //ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
        //System.out.println("http://localhost/countries.html");
        //ProcessBuilder processBuilder = new ProcessBuilder(command.split(" ")).inheritIO();
        //processBuilder.start();
        //let process run then close spring app so that travis exits build
        //Thread.sleep(30000);
        //ctx.close();
        //System.out.println("app closed");
        // Create new Application
        //App a = new App();

        // Connect to database
        //a.connect();

        // Extract country population information
        //ArrayList<Country> countries = a.getCountryByPopulation();

        // Test the size of the returned data - should be 239
        //System.out.println(countries.size());
        //a.printCountries(countries);


        // Disconnect from database
        //a.disconnect();
    }

}
