package VOD_assetTable.entity;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by MSBH9799 on 01/08/2016.
 */

@Table
public class ImageID {


    @PrimaryKey
    private String imageID;


    public ImageID() {
    }

    public ImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
}
