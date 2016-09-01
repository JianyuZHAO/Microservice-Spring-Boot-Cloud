package VOD_assetTable.entity;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by MSBH9799 on 11/07/2016.
 */

@Table
public class Category {

    @PrimaryKey
    private String itemID;

    private String itemName;

    private String displayOrder;

    private String assetIDs;


    public Category() {
    }

    public Category(String itemID, String itemName, String displayOrder, String assetids) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.displayOrder = displayOrder;
        this.assetIDs = assetids;
    }

    public String getAssetids() {
        return assetIDs;
    }

    public void setAssetids(String assetids) {
        this.assetIDs = assetids;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }
}
