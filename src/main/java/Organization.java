public class Organization extends OrganizationDTO {
    public Organization(int id, String name, String city, String state, String postal, String category) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.category = category;
    }
}
