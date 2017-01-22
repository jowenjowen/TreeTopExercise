import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class OrganizationList {
    public ArrayList<Organization> organizations = new ArrayList<>();
    public OrganizationList() {};
}
