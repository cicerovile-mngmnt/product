package com.fuck.off.fuckoffasaservice;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(FuckOffAsAService.FUCK_OFF)
@Component
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@SuppressWarnings({"SpringAutowiredFieldsWarningInspection", "WeakerAccess"})
public class FuckOffAsAService {

    public static final String FUCK_OFF = "/fuck-off";

    @GET
    @Path("{name}")
    public Response getService(@PathParam("name") final String name) {
        return buildResponse("Fuck off " + name);
    }

    /**
     * This method will build the response object, setting the headers for cross site JavaScript
     * operations, and for all the method types of the resource. The underlying Json converter will be
     * either Jackson or Gson, depending on the configuration.
     *
     * @param object the entity response object, the object that will be converted into Json for the client
     * @return the response object that will be used as the mechanism for transferring the entity to the client
     */
    private Response buildResponse(final Object object) {
        Response.ResponseBuilder responseBuilder = getResponseBuilder();
        return responseBuilder.entity(object).build();
    }

    private Response.ResponseBuilder getResponseBuilder() {
        return Response
                .status(Response.Status.OK)//
                .header("Access-Control-Allow-Origin", "*") //
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

}