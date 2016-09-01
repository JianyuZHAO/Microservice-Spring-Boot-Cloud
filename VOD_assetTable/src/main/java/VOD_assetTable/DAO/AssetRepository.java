package VOD_assetTable.DAO;

import VOD_assetTable.entity.Asset;
import VOD_assetTable.entity.Category;
import com.datastax.driver.core.ResultSet;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jca.cci.CannotGetCciConnectionException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends CrudRepository<Asset, String> {


    @Query("select * from asset where assetID=?0;")
    public Asset getAssetByID(String assetID);

    @Query("select imageID from asset where assetID=?0;")
    public ResultSet getImageIDByAssetID(String assetID);

    @Query("select assetIDs from category where itemID=?0;")
    public ResultSet getAssetByCategoryID(String itemID);

    @Query("select * from category;")
    public List<Category> getAllCategories();

    @Query("select * from category where itemID=?0;")
    public ResultSet getCategoryByID(String itemID);

    @Query("select assetID from asset where seriesID=?0;")
    public ResultSet getSeriesAssetByAssetID(String seriesID);

    @Query("select seriesIDs from category where itemID=?0;")
    public ResultSet getSeriesIDsByCategoryID(String itemID);

    @Query("select * from asset where seriesID=?0;")
    public List<Asset> getAssetBySeriesID(String seriesID);




/*    @Query("alter table asset add duration varchar;")
    public ResultSet addColumnDuration();


    @Query("Select assetID from asset;")
    public ResultSet findAssetIDs();


    @Query("update asset.asset set country=?0 where assetID=?1;")
    public ResultSet updateCountry(String country, String assetID);


    @Query("insert into assetTerminal2(ID, assetID, terminalID) values (?0,?1,?2);")
    public ResultSet insertAssetTerminal2(String ID, String assetID, String terminalID);

    @Query("CREATE INDEX assetID_index_price ON asset.moviePrice (assetID);")
    public ResultSet createIndexAssetID_price();*/

}
