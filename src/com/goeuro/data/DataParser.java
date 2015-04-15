/*
 * This project was made as test for GoEuro
 */
package com.goeuro.data;

import java.io.StringReader;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Yamen Ali
 */
public class DataParser {

    private JSONArray parsedData;

    //============ Constructors ===========//
    public DataParser() {}

    public DataParser(String jsonString) {
        parse(jsonString);
    }
    //============ Constructors ===========//

    /**
     * This function will parse JSON string
     *
     * @param jsonString the JSON string we need to parse
     */
    public void parse(String jsonString) {
        try {
            StringReader reader = new StringReader(jsonString);
            parsedData = (JSONArray) new JSONParser().parse(reader);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function will traverse through nested nodes and try to find our key
     * @param node the parent node we are looking into
     * @param key the key we are looking for
     * @return the node value or not found message
    */
    private String traverseParsedData(JSONObject node,String key){
        if (node.keySet().contains(key))
            return node.get(key).toString();
        else{
            for (Object nodeKey : node.keySet()) {
                Object childNode = node.get(nodeKey.toString());
               if( childNode instanceof JSONObject ){
                   String result = traverseParsedData((JSONObject)childNode,key);
                   if(!result.contains("Not Found")) return result;
               }
            }
        }
        return "Not Found";
    }
    
    /**
     * This function will return specific node from specific position in parsed
     * data array
     *
     * @param key the node key to return its value
     * @param position
     * @return the node value
     */
    public String getNodeValue(int position, String key) {
        JSONObject node = (JSONObject) parsedData.get(position);
        return traverseParsedData(node,key);
    }
    
    /**
     * This function will return the parsed data size
     * @return size
     */
    public int getDataSize() {
        return parsedData.size();
    }

}
