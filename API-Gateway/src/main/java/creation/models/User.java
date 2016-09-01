package creation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by MSBH9799 on 13/04/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore any properties not bound here
public class User implements Serializable {

    private Long userID;
    private String firstname;
    private String lastname;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
