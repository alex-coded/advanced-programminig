package utils;

import daoresponses.CityDAO;
import services.Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * utility class for reading city information from a csv file
 */
public class Reader {

    /**
     * reads the data from a csv file with the buffered reader
     * @param path the path of the csv file
     */
    public void readData(String path) {
        String line = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if(values[0].equals("CountryName") || values[1].equals("N/A")) {
                    continue;
                }
                insertData(values[0], values[1], true, Double.parseDouble(values[2]), Double.parseDouble(values[3]));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * inserts the city in the database
     * @param countryName the name of the country
     * @param capitalName the capital name
     * @param isCapital is capital or not
     * @param capitalLatitude capital latitude
     * @param capitalLongitude capital longitude
     */
    private void insertData(String countryName, String capitalName, boolean isCapital, double capitalLatitude, double capitalLongitude) {
        CityDAO cities = new CityDAO();
        Database database = Database.getInstance();
        try {
            cities.create(database.getConnection(), countryName, capitalName, isCapital, capitalLatitude, capitalLongitude);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
