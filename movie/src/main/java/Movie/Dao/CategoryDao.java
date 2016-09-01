package Movie.Dao;

import Movie.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by MSBH9799 on 04/04/2016.
 */

/**
 * An interface CategoryDao has two functions (findByName, findByCategoryID).
 * The Repository annotation indicates that this class is a Data Access Object.
 * The Transactional annotation defines the scope of a single database transaction.
 */

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Long> {

    public Category findByName(String name);
    public Category findByCategoryID(Long categoryID);
}
