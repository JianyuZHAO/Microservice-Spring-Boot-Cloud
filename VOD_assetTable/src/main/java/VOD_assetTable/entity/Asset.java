package VOD_assetTable.entity;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by MSBH9799 on 15/06/2016.
 */

@Table
public class Asset {

    @PrimaryKey
    private String assetID;

    private String assetName;

    private String productionYear;

    private String csaCode;

    private String providerID;

    private String duration;

    private String seriesID;

    private String episodeID;

    private String quality;

    private String genre;

    private String country;

    private String CSA;

    private String Provider;

    private String seriesSeason;

    private String seriesName;

    private String seriesDescription;

    private String seriesShortDescription;

    private String terminal;

    private String priceID;

    private String subtitleLanguage;

    private String audioLanguage;

    private String trailerID;

    private String imagePath;

    private String imageID;

    private String attachmentName;

    private String description;

    private String shortDescription;


    public Asset() {}

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getCsaCode() {
        return csaCode;
    }

    public void setCsaCode(String csaCode) {
        this.csaCode = csaCode;
    }

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public String getEpisodeID() {
        return episodeID;
    }

    public void setEpisodeID(String episodeID) {
        this.episodeID = episodeID;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCSA() {
        return CSA;
    }

    public void setCSA(String CSA) {
        this.CSA = CSA;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        Provider = provider;
    }

    public String getSeriesSeason() {
        return seriesSeason;
    }

    public void setSeriesSeason(String seriesSeason) {
        this.seriesSeason = seriesSeason;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesDescription() {
        return seriesDescription;
    }

    public void setSeriesDescription(String seriesDescription) {
        this.seriesDescription = seriesDescription;
    }

    public String getSeriesShortDescription() {
        return seriesShortDescription;
    }

    public void setSeriesShortDescription(String seriesShortDescription) {
        this.seriesShortDescription = seriesShortDescription;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getPriceID() {
        return priceID;
    }

    public void setPriceID(String priceID) {
        this.priceID = priceID;
    }

    public String getSubtitleLanguage() {
        return subtitleLanguage;
    }

    public void setSubtitleLanguage(String subtitleLanguage) {
        this.subtitleLanguage = subtitleLanguage;
    }

    public String getAudioLanguage() {
        return audioLanguage;
    }

    public void setAudioLanguage(String audioLanguage) {
        this.audioLanguage = audioLanguage;
    }

    public String getTrailerID() {
        return trailerID;
    }

    public void setTrailerID(String trailerID) {
        this.trailerID = trailerID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
