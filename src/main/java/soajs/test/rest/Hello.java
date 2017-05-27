package soajs.test.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author Etienne
 */
@Path("/hello")
public class Hello {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTest(String data,
            @Context HttpHeaders headers,
            @Context HttpServletRequest httpServletRequest,
            @Context HttpServletResponse httpServletResponse
    ) {
        JSONObject soajs = new JSONObject(headers.getRequestHeader("soajs").get(0));
        return Response.status(201).entity(soajs.toString()).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTest(@QueryParam("queryParam1") String queryParam1) {
        return "get : " + queryParam1;
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteTest(@QueryParam("queryParam1") String queryParam1) {
        return "delete : " + queryParam1;
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String putTest(@QueryParam("queryParam1") String queryParam1) {
        return "put : " + queryParam1;
    }
}
