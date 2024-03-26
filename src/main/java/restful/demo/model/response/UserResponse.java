package restful.demo.model.response;

public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String userid;


    public UserResponse(String fName,String lName,String email){
        this.firstName=fName;
        this.lastName=lName;
        this.email=email;
    }
    public UserResponse(){
        
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastname() {
        return lastName;
    }
    public void setLastname(String lastname) {
        this.lastName = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
