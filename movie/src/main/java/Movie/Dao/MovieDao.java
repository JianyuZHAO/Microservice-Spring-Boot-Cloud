package Movie.Dao;

import Movie.models.Category;
import Movie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by MSBH9799 on 04/04/2016.
 */

/**
 * An interface MovieDao has three functions (findByTitle, findByVideoID, updateCategory).
 * The Repository annotation indicates that this class is a Data Access Object.
 * The Transactional annotation defines the scope of a single database transaction.
 */

@Repository
@Transactional
public interface MovieDao extends JpaRepository<Movie, Long> {

    public Movie findByTitle(String title);

    public Movie findByVideoID(Long videoID);

    @Query(value = "update movies m set m.categories=?2 where m.videoID=?1")
    default void updateCategory(Long videoID, List<Category> categories) {

    }

}
