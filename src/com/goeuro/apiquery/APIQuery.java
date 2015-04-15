/*
 * This project was made as test for GoEuro
 */
package com.goeuro.apiquery;

/**
 *
 * @author Yamen Ali
 */
public class APIQuery {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length > 0) {
            APIQueryController controller = new APIQueryController(args[0]);
            String result = controller.fetchData();
            System.out.println(result);
        } else {
            System.out.println("Please Enter a City");
        }

    }

}
