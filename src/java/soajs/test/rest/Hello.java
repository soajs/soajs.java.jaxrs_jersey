package soajs.test.rest;

import java.util.List;
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

import soajs.filters.SoajsRequestUtilities;

/**
 *
 * @author Etienne
 */
@Path("/hello")
public class Hello {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTest(
            @QueryParam("username") String username,
            @QueryParam("lastname") String lastname) {
        JSONObject response = new JSONObject();
        
        response.put("message", "Hello, I am a JAVA service, you are ["+username+"] and your last name is : ["+lastname+"]");
        
        return Response.status(200).entity(response.toString()).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTest(String data,
            @Context HttpHeaders headers,
            @Context HttpServletRequest httpServletRequest,
            @Context HttpServletResponse httpServletResponse
    ) {
        JSONObject soajs = new JSONObject();
        try{
            System.out.println("Post service reached: trying to fetch request header at SOAJS ...");
            System.out.println("Soajs request header length = ["+headers.getRequestHeader("soajs").size()+"]");
            soajs = new JSONObject(headers.getRequestHeader("soajs").get(0));
            
            String host = SoajsRequestUtilities.getHost(soajs);
            soajs.put("controller", host);

            soajs.put("databases", SoajsRegistry.getDatabases());
        }catch(Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(soajs.toString()).build();
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
