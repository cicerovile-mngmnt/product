package com.fuck.off.fuckoffasaservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path(FuckOffAsAService.FUCK_OFF)
@Component
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class FuckOffAsAService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuckOffAsAService.class);

    static final String FUCK_OFF = "/fuck-off";

    private static final List<double[]> DOUBLES = new ArrayList<>();

    static {
        /* This thread removes the arrays that leak the memory */
        new Thread(() -> {
            //noinspection InfiniteLoopStatement
            while (true) {
                if (DOUBLES.size() > 0) {
                    // LOGGER.info("Removing one array : ");
                    DOUBLES.remove(0);
                    printMemory();
                }
                sleepy(500);
            }
        }).start();
        /* This thread went to market */
        new Thread(() -> {
            //noinspection InfiniteLoopStatement
            while (true) {
                // LOGGER.info("Suggesting GC");
                System.gc();
                sleepy(10000);
            }
        }).start();
        /* And this little thready went wee wee wee all the way home */
        new Thread(() -> {
            //noinspection InfiniteLoopStatement
            while (true) {
                sleepy(10000);
                /*new Thread(() -> {
                    throw new RuntimeException("Un-caught exception");
                }).start();*/
            }
        }).start();
    }

    @GET
    @Path("{name}")
    public Response getService(@PathParam("name") final String name) {
        printMemory();
        double[] memoryLeak = new double[Short.MAX_VALUE * 10];
        for (int i = 0; i < memoryLeak.length; i++) {
            //noinspection ShiftOutOfRange
            memoryLeak[i] = 31 << 42;
        }
        DOUBLES.add(memoryLeak);
        return buildResponse("Fuck off " + name + " => " + printMemory());
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

    private static String printMemory() {
        double megabyte = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();

        double freeMemory = runtime.freeMemory();
        double maxMemory = runtime.maxMemory();
        double usedMemory = maxMemory - freeMemory;

        double freeMemoryPercentage = ((freeMemory / maxMemory) * 100D);
        String message = "Free : %d, max : %d, used : %d, percentage free : %d";
        message = String.format(message,
                (int) (freeMemory / megabyte),
                (int) (maxMemory / megabyte),
                (int) (usedMemory / megabyte),
                (int) (freeMemoryPercentage));

        LOGGER.info(message);

        return message;
    }

    private static void sleepy(final long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (final InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        printMemory();
    }

}