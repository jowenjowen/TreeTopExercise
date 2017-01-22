import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Result;
import java.sql.*;
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
    ) throws JsonProcessingException, SQLException, ClassNotFoundException {        // Return some cliched textual content
        Class.forName("org.postgresql.Driver");
        String dbURL = "jdbc:postgresql://localhost/TreeTop?user=postgres&password=postgres";
        Connection conn = DriverManager.getConnection(dbURL);
        StringBuilder sql = new StringBuilder("select * from organizations");
        if (id != 0) {
            sql.append(" where id=");
            sql.append(id);
        }
        sql.append(';');
        Statement stmt = conn.createStatement();
        ResultSet resultset = stmt.executeQuery(sql.toString());
        OrganizationList result = getOrganizationList(resultset);
       return new ObjectMapper().writeValueAsString(result);
    }

    private OrganizationList getOrganizationList(ResultSet resultset) throws SQLException {
        OrganizationList result = new OrganizationList();
        while (resultset.next()) {
            Organization org = new Organization(resultset.getInt("id"),
                    resultset.getString("name"),
                    resultset.getString("city"),
                    resultset.getString("state"),
                    resultset.getString("postal"),
                    resultset.getString("category"));
            result.organizations.add(org);
        }
        return result;
    }
}

