/*
 * This project was made as test for GoEuro
 */
package com.goeuro.apiquery;

import com.goeuro.data.CSVManager;
import com.goeuro.data.DataParser;
import com.goeuro.rest.RestClient;
import com.goeuro.rest.RestResponse;
import java.util.ArrayList;

/**
 *
 * @author Yamen Ali
 */
public class APIQueryController {

    private String baseUri = "http://api.goeuro.com/api/v2/position/suggest/en/";
    private String country = "";

    //============ Constructors ============//

    public APIQueryController() {}

    public APIQueryController(String country) {
        this.country = country;
    }
    //============ Constructors ============//

    /**
     * This function will control the whole process of fetching data and writing
     * it to .csv file
     *
     * @return message indicating the process status
     */
    public String fetchData() {

        String message = "Done";
        
        RestClient client = new RestClient(baseUri + country);
        RestResponse response = client.get("application/json");

        if (response.getCode() != 200) {
            message = "Faild REST Error: " + response.getCode();
        } else if (response.getBody().equals("[]") ) {
            message = "No Data Recieved";
        } else {

            DataParser parser = new DataParser(response.getBody());
            CSVManager manager = new CSVManager("Data.csv");
            String[] header = {"ID", "Name" , "Type" , "Latitude","longitude"};
            manager.writeRow(header);

            for (int i = 0; i < parser.getDataSize(); i++) {
                ArrayList<String> row = new ArrayList<String>();

                row.add(parser.getNodeValue(i, "_id"));
                row.add(parser.getNodeValue(i, "name"));
                row.add(parser.getNodeValue(i, "type"));
                row.add(parser.getNodeValue(i, "latitude"));
                row.add(parser.getNodeValue(i, "longitude"));
                manager.writeRow(row.toArray(new String[row.size()]));
            }
            manager.close();
        }
        return message;
    }

}
