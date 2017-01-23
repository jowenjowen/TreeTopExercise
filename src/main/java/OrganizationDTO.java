import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jowen on 1/22/17.
 * OrganizationDTO (element of Organizations)
 */
@XmlRootElement
public class OrganizationDTO {
    public int id;
    public String name;
    public String city;
    public String state;
    public String postal;
    public String category;

    public OrganizationDTO() {};

}

