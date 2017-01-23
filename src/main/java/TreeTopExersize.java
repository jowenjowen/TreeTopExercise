import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import java.sql.*;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/organizations")
public class TreeTopExersize {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json,application/xml")
    public String getOrganizations(@DefaultValue("0") @QueryParam("id") int id,
                                   @DefaultValue("") @QueryParam("name") String name,
                                   @DefaultValue("") @QueryParam("city") String city,
                                   @DefaultValue("") @QueryParam("state") String state,
                                   @DefaultValue("") @QueryParam("postal") String postal,
                                   @DefaultValue("") @QueryParam("category") String category,
                                   @DefaultValue("") @QueryParam("orderby") String orderby,
                                   @DefaultValue("") @QueryParam("direction") String direction
    ) throws JsonProcessingException, SQLException, ClassNotFoundException {        // Return some cliched textual content
        OrganizationList organizationList = new OrganizationList();
        organizationList.initialize();
        organizationList.selectOrganizations(id, name, city, state, postal, category, orderby, direction);
       return new ObjectMapper().writeValueAsString(organizationList.getSelection());
    }

}

