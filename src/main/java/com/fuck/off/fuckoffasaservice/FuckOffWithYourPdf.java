package com.fuck.off.fuckoffasaservice;

import com.fuck.off.fuckoffasaservice.utilities.FILE;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileNotFoundException;

@Component
@Path(FuckOffWithYourPdf.FUCK_OFF)
@SuppressWarnings({"SpringAutowiredFieldsWarningInspection", "WeakerAccess"})
public class FuckOffWithYourPdf {

    public static final String FUCK_OFF = "/fuck-off-with-your-pdf";
    public static final String APPLICATION_PDF = "application/pdf";

    @SuppressWarnings("ConstantConditions")
    @GET
    @Path("/pdf")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(FuckOffWithYourPdf.APPLICATION_PDF)
    public Response getPdf() throws FileNotFoundException {
        File file = FILE.findFileRecursively(new File("."), "IG-5-Gateway-Guide.pdf");
        System.out.println("File : " + file.getAbsolutePath());
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment; filename=\"" + file.getName() + " \"");
        return response.build();

    }

}