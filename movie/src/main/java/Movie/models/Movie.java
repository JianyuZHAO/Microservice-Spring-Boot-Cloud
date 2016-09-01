package Movie.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created by MSBH9799 on 04/04/2016.
 */

/**
 * An entity Movie composed by two fields (videoID, title).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 */

@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long videoID;

    @NotNull
    private String title;

//    //@NotNull
//    private Date releaseDate;
//
//       //@NotNull
//       private String coverURL;
//
//       //@NotNull
//       private String overview;
//
//       //@NotNull
//       private String actors;
//
//       //@NotNull
//       private String directors;
//
//       //@NotNull
//       private int CSAlevel;
//
//       //@NotNull
//       private int duration;

    @ManyToMany
    @JoinTable(name = "movies2categories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public Movie(String title, List<Category> categories) {
        this.title = title;
        this.categories = categories;
    }

    public Movie(Long videoID) {
        this.videoID = videoID;
    }

    public Movie(String title, Long videoID) {
        this.title = title;
        this.videoID = videoID;
    }

    public Long getVideoID() {
        return videoID;
    }

    public void setVideoID(Long videoID) {
        this.videoID = videoID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonBackReference
    public List<Category> getCategories() {
        return categories;
    }

    @JsonBackReference
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
