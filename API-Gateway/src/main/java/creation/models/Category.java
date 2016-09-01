package creation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by MSBH9799 on 14/04/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore any properties not bound here
public class Category implements Serializable {

    private Long categoryID;
    private String name;
    private String description;

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
