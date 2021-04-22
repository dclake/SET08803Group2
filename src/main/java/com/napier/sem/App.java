package com.napier.sem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.ArrayList;
import java.io.IOException;

@SpringBootApplication
@RestController
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
     * Gets country by population.
     *
     * @return the country by population
     */
    @RequestMapping("countries")
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

    /**
     * Gets country by continent.
     *
     * @param continent the continent
     * @return the country by continent
     */
    @RequestMapping("countriesbycontinent")
    public ArrayList<Country> getCountryByContinent(@RequestParam String continent)
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

    /**
     * Gets top n country by continent.
     *
     * @param continent the continent
     * @param N         the n
     * @return the top n country by continent
     */
    @RequestMapping("topncountriesbycontinent")
    public ArrayList<Country> getTopNCountryByContinent(@RequestParam  String continent, int N)
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

    /**
     * Gets country by region.
     *
     * @param region the region
     * @return the country by region
     */
    @RequestMapping("countriesbyregion")
    public ArrayList<Country> getCountryByRegion(@RequestParam String region)
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

    /**
     * Gets top n country by region.
     *
     * @param region the region
     * @param N      the n
     * @return the top n country by region
     */
    @RequestMapping("topncountrybyregion")
    public ArrayList<Country> getTopNCountryByRegion(@RequestParam  String region, int N)
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

    /**
     * Gets top n country by population.
     *
     * @param N the n
     * @return the top n country by population
     */
    @RequestMapping("topncountriesbypopulation")
    public ArrayList<Country> getTopNCountryByPopulation(@RequestParam int N)
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
     * Gets world cities.
     *
     * @return the world cities
     */
    @RequestMapping("worldcities")
    public  static ArrayList<City> getWorldCities()
    {
        try
        {
            // Create an SQL statement
            //Connection con = null;
            //Statement stmt = con.createStatement();
            Statement stmt = App.getCon().createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n"
                            + "FROM world.city , world.country\n"
                            + "where country.Code = city.CountryCode\n"
                            + "ORDER BY Population desc;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
              city.setCity_name(rset.getString("city.Name"));
              city.setCountry_name(rset.getString("country.name"));
              city.setDistrict(rset.getString("city.District"));
              city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets continent cities.
     *
     * @param continent the continent
     * @return the continent cities
     */
    @RequestMapping("continentcities")
    public  static ArrayList<City> getContinentCities(@RequestParam String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where country.Continent like '" + continent + "'\n" +
                            "ORDER BY Population desc";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets region cities.
     *
     * @param Region the region
     * @return the region cities
     */
    @RequestMapping("regioncities")
    public  static ArrayList<City> getRegionCities(@RequestParam String Region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where country.Region like '" + Region + "'\n" +
                            "ORDER BY Population desc";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets district cities.
     *
     * @param District the district
     * @return the district cities
     */
    @RequestMapping("districtcities")
    public  static ArrayList<City> getDistrictCities(@RequestParam String District)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where city.District like '" + District + "'\n" +
                            "ORDER BY Population desc";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets countries cities.
     *
     * @param Country the country
     * @return the countries cities
     */
    @RequestMapping("countrycities")
    public  static ArrayList<City> getCountriesCities(@RequestParam String Country)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where country.Name like '" + Country + "'\n" +
                            "ORDER BY Population desc";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets top n world cities.
     *
     * @param N the n
     * @return the top n world cities
     */
    @RequestMapping("topnworldcities")
    public  static ArrayList<City> getTopNWorldCities(@RequestParam int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "ORDER BY Population desc\n" +
                            "LIMIT 0, "+ N;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets top n cities in continent.
     *
     * @param Continent the continent
     * @param N         the n
     * @return the top n cities in continent
     */
    @RequestMapping("topncitiesincontinent")
    public  static ArrayList<City> getTopNCitiesInContinent(@RequestParam String Continent, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where country.Continent like '" +Continent +"' \n" +
                            "ORDER BY Population desc\n" +
                            "LIMIT 0, "+ N;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets top n cities in region.
     *
     * @param Region the region
     * @param N      the n
     * @return the top n cities in region
     */
    @RequestMapping("topncitiesinregion")
    public  static ArrayList<City> getTopNCitiesInRegion(@RequestParam String Region, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where country.Region like '" +Region +"' \n" +
                            "ORDER BY Population desc\n" +
                            "LIMIT 0, "+ N;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets top n cities in country.
     *
     * @param Country the country
     * @param N       the n
     * @return the top n cities in country
     */
    @RequestMapping("topncitiesincountry")
    public  static ArrayList<City> getTopNCitiesInCountry(@RequestParam String Country, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where country.Name like '" + Country + "'\n" +
                            "ORDER BY Population desc\n" +
                            "LIMIT 0, "+ N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets top n cities in district.
     *
     * @param District the district
     * @param N        the n
     * @return the top n cities in district
     */
    @RequestMapping("topncitiesindistrict")
    public  static ArrayList<City> getTopNCitiesInDistrict(@RequestParam String District, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where city.District like '" + District + "'\n" +
                            "ORDER BY Population desc\n" +
                            "LIMIT 0, "+ N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /**
     * Gets world capital cities.
     *
     * @return the world capital cities
     */
    @RequestMapping("worldcapitalcities")
    public  static ArrayList<CapitalCity> getWorldCapitalCities()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.ID = country.Capital\n" +
                            "ORDER BY Population desc";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract capital city information
            ArrayList<CapitalCity> capitalcities = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rset.getString("city.Name"));
                capitalCity.setCountry(rset.getString("country.name"));
                capitalCity.setPopulation(rset.getLong("city.Population"));
                capitalcities.add(capitalCity);
            }
            return capitalcities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Gets continent capital cities.
     *
     * @param continent the continent
     * @return the continent capital cities
     */
    @RequestMapping("continentcapitalcities")
    public  static ArrayList<CapitalCity> getContinentCapitalCities(@RequestParam String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.ID = country.Capital\n" +
                            "where country.Continent like '" +continent + "'\n" +
                            "ORDER BY Population desc";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract capital city information
            ArrayList<CapitalCity> capitalCities = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rset.getString("city.Name"));
                capitalCity.setCountry(rset.getString("country.name"));
                capitalCity.setPopulation(rset.getLong("city.Population"));
                capitalCities.add(capitalCity);
            }
            return capitalCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Gets region capital cities.
     *
     * @param region the region
     * @return the region capital cities
     */
    @RequestMapping("regioncapitalcities")
    public  static ArrayList<CapitalCity> getRegionCapitalCities(@RequestParam String region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name,city.Name,city.District, city.Population " +
                            "FROM country " +
                            "INNER JOIN city ON country.Code = city.CountryCode AND country.Capital = city.ID " +
                            "WHERE country.Region like '" + region +"' " +
                            "ORDER BY city.Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract capital city information
            ArrayList<CapitalCity> capitalCities = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rset.getString("city.Name"));
                capitalCity.setCountry(rset.getString("country.name"));
                capitalCity.setPopulation(rset.getLong("city.Population"));
                capitalCities.add(capitalCity);
            }
            return capitalCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Gets top n capital cities.
     *
     * @param N the n
     * @return the top n capital cities
     */
    @RequestMapping("topncapitalcities")
    public  static ArrayList<CapitalCity> getTopNCapitalCities(@RequestParam int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name,city.Name,city.District, city.Population " +
                            "FROM country " +
                            "INNER JOIN city ON country.Code = city.CountryCode AND country.Capital = city.ID " +
                            "ORDER BY city.Population DESC "+
                            "LIMIT "+ N;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract capital city information
            ArrayList<CapitalCity> capitalCities = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rset.getString("city.Name"));
                capitalCity.setCountry(rset.getString("country.name"));
                capitalCity.setPopulation(rset.getLong("city.Population"));
                capitalCities.add(capitalCity);
            }
            return capitalCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Gets top n continent capital cities.
     *
     * @param N the n
     * @return the top n continent capital cities
     */
    @RequestMapping("topncontinentcapitalcities")
    public  static ArrayList<CapitalCity> getTopNContinentCapitalCities(@RequestParam String Continent, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name,city.Name,city.District, city.Population " +
                            "FROM country " +
                            "INNER JOIN city ON country.Code = city.CountryCode AND country.Capital = city.ID " +
                            "WHERE country.Continent like '"+Continent +"'"+
                            " ORDER BY city.Population DESC "+
                            "LIMIT "+ N;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract capital city information
            ArrayList<CapitalCity> capitalCities = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rset.getString("city.Name"));
                capitalCity.setCountry(rset.getString("country.name"));
                capitalCity.setPopulation(rset.getLong("city.Population"));
                capitalCities.add(capitalCity);
            }
            return capitalCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }
    /**
     * Gets top n capital cities region.
     *
     * @param Region the region
     * @param N      the n
     * @return the top n capital cities region
     */
    @RequestMapping("topnregioncapital")
    public  static ArrayList<City> getTopNCapitalCitiesRegion(@RequestParam String Region,int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name,city.Name,city.District, city.Population " +
                            "FROM country " +
                            "INNER JOIN city ON country.Code = city.CountryCode AND country.Capital = city.ID " +
                            "WHERE country.Region like '"+ Region +"' "+
                            "ORDER BY city.Population DESC "+
                            "LIMIT "+ N;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract capital city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setCity_name(rset.getString("city.Name"));
                city.setCountry_name(rset.getString("country.name"));
                city.setDistrict(rset.getString("city.District"));
                city.setPopulation(rset.getInt("city.Population"));
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Gets city dwellers continent.
     *
     * @return the city dwellers continent
     */
    @RequestMapping("citydwellersbycontinent")
    public  static ArrayList<CityDwellers> getCityDwellersContinent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "Select qry.Continent, qry.TotalPopulation,qry.CityDwellers, ((qry.CityDwellers/qry.TotalPopulation)*100) as PercentageCityDwellers,(((qry.TotalPopulation-qry.CityDwellers) /qry.TotalPopulation)*100) as PercentageNonCityDwellers, qry.TotalPopulation-qry.CityDwellers as NonCityDwellers  from\n" +
                            "(SELECT cnt.Continent  , sum(cnt.Population) as TotalPopulation,\n" +
                            "(SELECT SUM(city.Population) FROM city INNER JOIN country ON city.CountryCode = country.`Code` where country.Continent= cnt.Continent) as CityDwellers\n" +
                            "FROM country as cnt GROUP BY cnt.Continent) as qry";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract City Dwellers information
            ArrayList<CityDwellers> entries = new ArrayList<CityDwellers>();
            while (rset.next())
            {
                CityDwellers entry = new CityDwellers();
                entry.setName(rset.getString("Continent"));
                entry.setTotalPopulation(rset.getLong("TotalPopulation"));
                entry.setCityDwellers(rset.getInt("CityDwellers"));
                entry.setPercentageCityDwellers(rset.getFloat("PercentageCityDwellers"));
                entry.setNonCityDwellers(rset.getLong("NonCityDwellers"));
                entry.setPercentageNonCityDwellers(rset.getFloat("PercentageNonCityDwellers"));
                entries.add(entry);
            }
            return entries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Dwellers details");
            return null;
        }
    }

    /**
     * Gets city dwellers region.
     *
     * @return the city dwellers region
     */
    @RequestMapping("citydwellersbyregion")
    public  static ArrayList<CityDwellers> getCityDwellersRegion()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "Select qry.Region, qry.TotalPopulation,qry.CityDwellers, ((qry.CityDwellers /qry.TotalPopulation)*100) as PercentageCityDwellers,(((qry.TotalPopulation-qry.CityDwellers) *100)/qry.TotalPopulation) as PercentageNonCityDwellers, qry.TotalPopulation-qry.CityDwellers as NonCityDwellers  from\n" +
                            "(SELECT cnt.Region, sum(cnt.Population) as TotalPopulation,\n" +
                            "(SELECT SUM(city.Population) FROM city INNER JOIN country ON city.CountryCode = country.`Code` where country.Region= cnt.Region) as CityDwellers\n" +
                            "FROM country as cnt GROUP BY cnt.Region) as qry";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city dweller information
            ArrayList<CityDwellers> entries = new ArrayList<CityDwellers>();
            while (rset.next())
            {
                CityDwellers entry = new CityDwellers();
                entry.setName(rset.getString("Region"));
                entry.setTotalPopulation(rset.getLong("TotalPopulation"));
                entry.setCityDwellers(rset.getInt("CityDwellers"));
                entry.setPercentageCityDwellers(rset.getFloat("PercentageCityDwellers"));
                entry.setNonCityDwellers(rset.getLong("NonCityDwellers"));
                entry.setPercentageNonCityDwellers(rset.getFloat("PercentageNonCityDwellers"));
                entries.add(entry);
            }
            return entries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Dwellers details");
            return null;
        }
    }

    /**
     * Gets city dwellers country.
     *
     * @return the city dwellers country
     */
    @RequestMapping("citydwellersbycountry")
    public  static ArrayList<CityDwellers> getCityDwellersCountry()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "Select qry.Name,  qry.TotalPopulation,qry.CityDwellers, ((qry.CityDwellers /qry.TotalPopulation)*100) as PercentageCityDwellers,(((qry.TotalPopulation-qry.CityDwellers) *100)/qry.TotalPopulation) as PercentageNonCityDwellers, qry.TotalPopulation-qry.CityDwellers as NonCityDwellers  from\n" +
                            "(SELECT cnt.Name, sum(cnt.Population) as TotalPopulation,\n" +
                            "(SELECT SUM(city.Population) FROM city INNER JOIN country ON city.CountryCode = country.`Code` where country.Name= cnt.Name) as CityDwellers\n" +
                            "FROM country as cnt GROUP BY cnt.Name) as qry";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city dweller information
            ArrayList<CityDwellers> entries = new ArrayList<CityDwellers>();
            while (rset.next())
            {
                CityDwellers entry = new CityDwellers();
                entry.setName(rset.getString("Name"));
                entry.setTotalPopulation(rset.getLong("TotalPopulation"));
                entry.setCityDwellers(rset.getInt("CityDwellers"));
                entry.setPercentageCityDwellers(rset.getFloat("PercentageCityDwellers"));
                entry.setNonCityDwellers(rset.getLong("NonCityDwellers"));
                entry.setPercentageNonCityDwellers(rset.getFloat("PercentageNonCityDwellers"));
                entries.add(entry);
            }
            return entries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City Dwellers details");
            return null;
        }
    }

    /**
     * Gets languages.
     *
     * @return the languages
     */
    @RequestMapping("languages")
    public  static ArrayList<Language> getLanguages()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "Select q.Language,q.TotalSpeakers,(q.TotalSpeakers/ (Select SUM(country.Population) from country))*100 as WorldPercent from\n" +
                            "(SELECT\n" +
                            "countrylanguage.Language,\n" +
                            "SUM(country.Population) as TotalSpeakers\n" +
                            "FROM\n" +
                            "countrylanguage\n" +
                            "INNER JOIN\n" +
                            "country\n" +
                            "ON\n" +
                            "countrylanguage.CountryCode = country.`Code`\n" +
                            " where countrylanguage.Language in (\"Chinese\",\"English\",\"Hindi\",\"Spanish\",\"Arabic\") GROUP BY countrylanguage.Language) as q\n" +
                            " order by WorldPercent desc";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<Language> languages = new ArrayList<Language>();
            while (rset.next())
            {
                Language language = new Language();
                language.setLanguage(rset.getString("Language"));
                language.setTotalSpeakers(rset.getLong("TotalSpeakers"));
                language.setWorldPercentage(rset.getFloat("WorldPercent"));
                languages.add(language);
            }
            return languages;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Language details");
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

    /**
     * Print cities.
     *
     * @param cities the cities
     */
    public static void printCities(ArrayList<City> cities)
    {
        // Check countries is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }
        // Print header
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        System.out.println(String.format("%-35s %-40s %-35s %-15s", "City", "Country", "District", "Population"));
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        // Loop over all countries in the list
        for (City city : cities)
        {
            if (city == null)
                continue;
            String cities_string =
                         city.toString();
            System.out.println(cities_string);
        }
    }
    public static void printCapitalCities(ArrayList<CapitalCity> capitalcities)
    {
        // Check countries is not null
        if (capitalcities == null) {
            System.out.println("No Capital Cities");
            return;
        }
        // Print header
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        System.out.println(String.format("%-35s %-40s %-15s", "City", "Country", "Population"));
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        // Loop over all countries in the list
        for (CapitalCity capitalCity : capitalcities)
        {
            if (capitalCity == null)
                continue;
            String capitalcities_string =
                    capitalCity.toString();
            System.out.println(capitalcities_string);
        }
    }
    /**
     * Print city dwellers.
     *
     * @param entries the entries
     */
    public static void printCityDwellers(ArrayList<CityDwellers> entries)
    {
        // Check entries is not null
        if (entries == null) {
            System.out.println("No City Dwellers");
            return;
        }
        // Print header
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        System.out.println(String.format("%-45s %-20s %-15s %-25s %-15s %-15s", "Name", "Total Population", "City Dwellers", "PercentageCityDwellers", "NonCityDwellers", "PercentageNonCityDwellers"));
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        // Loop over all countries in the list
        for (CityDwellers entry : entries)
        {
            if (entry == null)
                continue;
            String citydwellers_string =
                    entry.toString();
            System.out.println(citydwellers_string);
        }
    }

    /**
     * Print languages.
     *
     * @param languages the languages
     */
    public static void printLanguages(ArrayList<Language> languages)
    {
        // Check entries is not null
        if (languages == null) {
            System.out.println("No Languages");
            return;
        }
        // Print header
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        System.out.println(String.format("%-20s %-20s %-15s", "Languages", "Total Speakers", "Percentage World Speakers"));
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        // Loop over all countries in the list
        for (Language language : languages)
        {
            if (language == null)
                continue;
            String Language_string =
                    language.toString();
            System.out.println(Language_string);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     * @throws IOException          the io exception
     */
    public static void main(String[] args) throws InterruptedException, IOException  {
        // Connect to database
        if (args.length < 1)
        {
            //connect();
            // Create new Application
           // App a = new App();

            // Connect to database
            //a.connect("localhost:33060");
            connect("localhost:33060");
            

        }
        else
        {
            connect(args[0]);
        }
        //String command = "curl http://app:8080/countriesbyregion?region=Caribbean";
        SpringApplication.run(App.class, args);



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



        //a.disconnect();
    }

}
