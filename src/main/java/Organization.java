import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jowen on 1/22/17.
 * Organization (element of Organizations)
 */
@XmlRootElement
public class Organization {
    public int id;
    public String name;
    public String city;
    public String state;
    public String postal;
    public String category;

    public Organization() {};

    public Organization(int id, String name, String city, String state, String postal, String category) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.category = category;
    }
}
