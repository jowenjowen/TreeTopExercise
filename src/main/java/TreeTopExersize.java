import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

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
                                   @DefaultValue("") @QueryParam("category") String category
    ) throws JsonProcessingException {        // Return some cliched textual content
        Organization org = new Organization(id, name, city, state, postal, category);
        OrganizationList result = new OrganizationList();
        result.organizations.add(org);
        result.organizations.add(org);
        result.organizations.add(org);
        return new ObjectMapper().writeValueAsString(result);

    }
}

