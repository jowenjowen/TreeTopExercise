import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class OrganizationListDTO {
    public ArrayList<OrganizationDTO> organizations = new ArrayList<>();
    public OrganizationListDTO() {};
}

