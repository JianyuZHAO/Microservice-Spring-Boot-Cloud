package VOD_assetTable.entity;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by MSBH9799 on 13/07/2016.
 */

@Table
public class Series {

    @PrimaryKey
    private String seriesID;

    private String seriesName;

    private String seriesSeason;

    private String seriesDiscription;

    private String seriesShortDescription;

    public Series() {
    }

    public Series(String seriesID, String seriesName, String seriesSeason, String seriesDiscription, String seriesShortDescription) {
        this.seriesID = seriesID;
        this.seriesName = seriesName;
        this.seriesSeason = seriesSeason;
        this.seriesDiscription = seriesDiscription;
        this.seriesShortDescription = seriesShortDescription;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesSeason() {
        return seriesSeason;
    }

    public void setSeriesSeason(String seriesSeason) {
        this.seriesSeason = seriesSeason;
    }

    public String getSeriesDiscription() {
        return seriesDiscription;
    }

    public void setSeriesDiscription(String seriesDiscription) {
        this.seriesDiscription = seriesDiscription;
    }

    public String getSeriesShortDescription() {
        return seriesShortDescription;
    }

    public void setSeriesShortDescription(String seriesShortDescription) {
        this.seriesShortDescription = seriesShortDescription;
    }
}
