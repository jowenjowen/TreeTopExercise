import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationList extends OrganizationListDTO {
    private Connection conn;

    private OrganizationListDTO result;
    public OrganizationList() {
    };

    public void initialize() throws SQLException, ClassNotFoundException  {
        Class.forName("org.postgresql.Driver");
        String dbURL = "jdbc:postgresql://localhost/TreeTop?user=postgres&password=postgres";
        conn = DriverManager.getConnection(dbURL);
    }

    public void selectOrganizations(int id, String name, String city, String state, String postal, String category, String orderby, String direction) throws SQLException {
        String sql = getSqlString(id, name, city, state, postal, category, orderby, direction);
        Statement stmt = conn.createStatement();
        ResultSet resultset = stmt.executeQuery(sql.toString());
        result = getOrganizationList(resultset);
    }

    private OrganizationListDTO getOrganizationList(ResultSet resultset) throws SQLException {
        OrganizationListDTO result = new OrganizationListDTO();
        while (resultset.next()) {
            OrganizationDTO org = new Organization(resultset.getInt("id"),
                    resultset.getString("name"),
                    resultset.getString("city"),
                    resultset.getString("state"),
                    resultset.getString("postal"),
                    resultset.getString("category"));
            result.organizations.add(org);
        }
        return result;
    }

    protected String getSqlString(int id, String name, String city, String state, String postal, String category, String orderby, String direction) {
        StringBuilder sql = new StringBuilder("select * from organizations");
        List<String> params = getNonDefaultParams(id, name, city, state, postal, category);
        boolean first = true;
        for(String param: params) {
            if (first) {
                sql.append(" where ");
                first = false;
            } else {
                sql.append(" and ");
            }
            sql.append(param);
        }
        if(!orderby.isEmpty()) {
            sql.append(" order by upper(");
            sql.append(orderby);
            sql.append(")");
            if (!direction.isEmpty()) {
                if (direction.equals("ASC")) {
                    sql.append(" ASC");
                }
                if (direction.equals("DSC")) {
                    sql.append(" DESC");
                }
            }
        }
        sql.append(';');
        return sql.toString();
    }

    private List<String> getNonDefaultParams(int id, String name, String city, String state, String postal, String category) {
        List<String> result = new ArrayList<>();
        if (id != 0) {
            result.add("id="+id);
        }
        if (!name.isEmpty()) {
            result.add("lower(name)=lower('"+name+"')");
        }
        if (!city.isEmpty()) {
            result.add("lower(city)=lower('"+city+"')");
        }
        if (!state.isEmpty()) {
            result.add("lower(state)=lower('"+state+"')");
        }
        if (!postal.isEmpty()) {
            result.add("lower(postal)=lower('"+postal+"')");
        }
        if (!category.isEmpty()) {
            result.add("lower(category)=lower('"+category+"')");
        }
        return result;
    }

    public OrganizationListDTO getSelection() {
        return result;
    }

}
