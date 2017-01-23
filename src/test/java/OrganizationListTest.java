import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by jowen on 1/22/17.
 */
public class OrganizationListTest {

    OrganizationList sut;

    @Before
    public void setUp() throws Exception {
        sut = new OrganizationList();
    }
    @org.junit.Test
    public void getSqlStringTest() throws Exception {
        //simplest statement
        assertEquals("select * from organizations;", sut.getSqlString(0, "", "", "", "", ""));
        // single item statements
        assertEquals("select * from organizations where id=5;", sut.getSqlString(5, "", "", "", "", ""));
        assertEquals("select * from organizations where lower(name)=lower('nAme');", sut.getSqlString(0, "nAme", "", "", "", ""));
        assertEquals("select * from organizations where lower(city)=lower('ciTy');", sut.getSqlString(0, "", "ciTy", "", "", ""));
        assertEquals("select * from organizations where lower(state)=lower('stAte');", sut.getSqlString(0, "", "", "stAte", "", ""));
        assertEquals("select * from organizations where lower(postal)=lower('poStal');", sut.getSqlString(0, "", "", "", "poStal", ""));
        assertEquals("select * from organizations where lower(category)=lower('category');", sut.getSqlString(0, "", "", "", "", "category"));
        // two item statements
        assertEquals("select * from organizations where id=5 and lower(name)=lower('name');", sut.getSqlString(5, "name", "", "", "", ""));
        assertEquals("select * from organizations where id=5 and lower(city)=lower('city');", sut.getSqlString(5, "", "city", "", "", ""));
        assertEquals("select * from organizations where lower(city)=lower('city') and lower(category)=lower('category');", sut.getSqlString(0, "", "city", "", "", "category"));

    }
}