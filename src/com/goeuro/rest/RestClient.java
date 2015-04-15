/*
 * This project was made as test for GoEuro
 */
package com.goeuro.rest;

import com.sun.jersey.api.client.*;

/**
 *
 * @author Yamen Ali
 */
public class RestClient {

    private Client client;
    private WebResource webResource;

    //=========== Constructors ============//
    public RestClient() {
        client = Client.create();
    }

    public RestClient(String uri) {
        this();
        setResource(uri);
    }
    //=========== Constructors ============//

    /**
     * This function is used to set the REST resource we want to query
     *
     * @param uri the resource of our REST
     */
    public void setResource(String uri) {
        webResource = client.resource(uri);
    }

    /**
     * This function performs GET on our REST resource
     *
     * @param contentType to define the request content type
     * @return String representing get result ( JSON String in this case)
     */
    public RestResponse get(String contentType) {
        try {
            ClientResponse response = webResource.accept(contentType)
                    .get(ClientResponse.class);
            return new RestResponse(response.getStatus(), response.getEntity(String.class));
        } catch (Exception e) {
            return new RestResponse(0, e.getMessage());
        }
    }

    /* Here We can implement post , delete ,put , ... when needed */
}
