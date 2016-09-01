package Movie.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by MSBH9799 on 04/04/2016.
 */

/**
 * An entity Category composed by three fields (categoryID, name, description).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 */

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryID;

    @NotNull
    private String name;

    //@NotNull
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<Movie> movies;


    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(String name) {
        this.name = name;
    }

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

    @JsonBackReference
    public List<Movie> getMovies() {
        return movies;
    }

    @JsonBackReference
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
