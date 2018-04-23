package com.fuck.off.fuckoffasaservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

@Path(FuckOffAsAService.FUCK_OFF)
@Component
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@SuppressWarnings({"SpringAutowiredFieldsWarningInspection", "WeakerAccess"})
public class FuckOffAsAService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuckOffAsAService.class);

    public static final String FUCK_OFF = "/fuck-off";

    private static final List<double[]> doubles = new ArrayList<>();

    static {
        new Thread(() -> {
            //noinspection InfiniteLoopStatement
            while (true) {
                if (doubles.size() > 0) {
                    LOGGER.info("Removing one array : ");
                    doubles.remove(0);
                }
                try {
                    Thread.sleep(500);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @GET
    @Path("{name}")
    public Response getService(@PathParam("name") final String name) {
        if (System.currentTimeMillis() % 10000 == 0) {
            MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
            long used = memoryUsage.getUsed();
            long max = memoryUsage.getMax();
            LOGGER.info("Used : " + used + ", max : " + max + ", sed percentage : " + ((used / max) * 100));
        }
        double[] memoryLeak = new double[Short.MAX_VALUE * 10];
        for (int i = 0; i < memoryLeak.length; i++) {
            //noinspection ShiftOutOfRange
            memoryLeak[i] = 31 << 42;
        }
        doubles.add(memoryLeak);
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