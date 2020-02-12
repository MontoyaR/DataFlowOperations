package io.github.MontoyaR;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {
        /**
         * Iteration 1.
         */

        CSVParser csvP = new CSVParser("src/Data/SEOExample.csv");
        csvP.printCsv();

        /**
         * Load the JSON
         *  1. Create instance of GSON
         *  2. Create a JsonReader object using FileReader
         *  3. Array of class instances of AuthorParser, assign data from our JsonReader
         *  4. foreach loop to check data
         */
        Gson gson = new Gson();
        JsonReader jread = new JsonReader(new FileReader("src/Data/authors.json"));
        AuthorParser[] authors = gson.fromJson(jread, AuthorParser[].class);

        for (var element : authors) {
            System.out.println(element.getName());
        }

        /**
         * Converts the json file attributes to the database.
         */

        //Declare Connection
        Connection conn = null;

        //Creating a null JSONObject object.
        JsonParser jsonParser = new JsonParser();

        try {
            //Parsing the contents of the JSON file
            JsonObject jsonObject = (JsonObject) jsonParser.parse(new FileReader("src/Data/authors.json"));
            //Retrieving the array
            JsonArray jsonArray = (JsonArray) jsonObject.get("authors");





            //Establish connection to the database.
            DBUtil.dbConnect();

            //Insert a new row into the SQLite table.
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO author values (?,?,?)");

            for (Object obj : jsonArray) {
                JsonObject record = (JsonObject) obj;
                String authorName = record.get("author_name").toString();
                String authorEmail = record.get("author_email").toString();
                String authorURL = record.get("author_url").toString();
                pstmt.setString(1, authorName);
                pstmt.setString(2, authorEmail);
                pstmt.setString(3, authorURL);
                pstmt.executeUpdate();
            }
            System.out.println("Records inserted successfully.");
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
