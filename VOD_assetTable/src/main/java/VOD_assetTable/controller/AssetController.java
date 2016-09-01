package VOD_assetTable.controller;

import VOD_assetTable.entity.Asset;
import VOD_assetTable.DAO.AssetRepository;
import VOD_assetTable.entity.Category;
import VOD_assetTable.entity.ImageID;
import VOD_assetTable.entity.Series;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by MSBH9799 on 06/07/2016.
 */


@RestController
@RequestMapping(value = "/asset")
@EnableSwagger2

public class AssetController {

    @Autowired
    private AssetRepository assetRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "This is asset";
    }


    @RequestMapping(value = "/getAssetByID/{assetID}", method = RequestMethod.GET)
    @ResponseBody
    public Asset getAssetByID(@PathVariable(value = "assetID") String assetID) {
        Asset asset = null;
        try {
            asset = assetRepository.getAssetByID(" " + assetID);
            asset.setAssetID(asset.getAssetID().trim());
        } catch (Exception ex) {
            return null;
        }
        return asset;
    }


    @RequestMapping(value = "/getImageIDByAssetID/{assetID}", method = RequestMethod.GET)
    @ResponseBody
    public ImageID getImageIDByAssetID(@PathVariable(value = "assetID") String assetID) {
        String imageID_raw = null;
        ImageID imageID;
        try {

            List<Row> imageID_row = assetRepository.getImageIDByAssetID(" " + assetID).all();

            if(imageID_row.size()>0){
                imageID_raw = imageID_row.get(0).toString();
            }

            String[] imageID_array = changeResult(imageID_raw).split(",");

            imageID = new ImageID(imageID_array[0]);

        } catch (Exception ex) {
            return null;
        }
        return imageID;
    }




    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAllCategories() {
        List<Category> Categories = new ArrayList<>();
        Category category;
        try {
            Categories = assetRepository.getAllCategories();

            for(int i=0; i<Categories.size(); i++){



            }


        } catch (Exception ex) {
            return null;
        }
        return Categories;
    }



    @RequestMapping(value = "/getCategoryByID/{itemID}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategoryByID(@PathVariable(value = "itemID") String itemID) {
        List<Category> categoryList = new ArrayList<>();
        ResultSet categoryResult;
        try {
            categoryResult = assetRepository.getCategoryByID(" "+ itemID);

            categoryResult.iterator().forEachRemaining(row->categoryList.add(new Category(row.getString("itemid"),row.getString("itemname"),row.getString("displayorder"),row.getString("assetids"))));


        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
        return categoryList.get(0);
    }




    @RequestMapping(value = "/getAssetByCategoryID/{categoryID}", method = RequestMethod.GET)
    @ResponseBody
    public List<Asset> getAssetByCategoryID(@PathVariable(value = "categoryID") String categoryID) {

        List<Asset> assetList = new ArrayList<>();
        Asset asset;

        try {
            List<Row> assetIDs = assetRepository.getAssetByCategoryID(" " + categoryID).all();
            if(assetIDs.size()>0){

                String assetIDs_str = changeResult(assetIDs.get(0).toString());
                String[] assetIDs_array = assetIDs_str.split(",");

                for(int n=0; n<assetIDs_array.length; n++){

                    asset = assetRepository.getAssetByID(" " + assetIDs_array[n].trim());
                    asset.setAssetID(asset.getAssetID().trim());

                    assetList.add(asset);
                }
            }
        } catch (Exception ex) {
            return null;
        }
        return assetList;

    }



    @RequestMapping(value = "/get5AssetsByCategoryID/{categoryID}", method = RequestMethod.GET)
    @ResponseBody
    public List<Asset> get5AssetsByCategoryID(@PathVariable(value = "categoryID") String categoryID) {

        List<Asset> assetList = new ArrayList<>();
        Asset asset;

        try {
            List<Row> assetIDs = assetRepository.getAssetByCategoryID(" " + categoryID).all();
            if(assetIDs.size()>0){

                String assetIDs_str = changeResult(assetIDs.get(0).toString());
                String[] assetIDs_array = assetIDs_str.split(",");

                for(int n=0; n<5; n++){
                    asset = assetRepository.getAssetByID(" " + assetIDs_array[n].trim());
                    asset.setAssetID(asset.getAssetID().trim());

                    assetList.add(asset);
                }
            }

        } catch (Exception ex) {
            return null;
        }
        return assetList;
    }





    private String changeResult(String result) {     // delete "Row[]" and spaces from a given String
        result = result.replace("Row[", "");
        result = result.replace("]", "");
        result = result.replace(" ", "");
        return result;
    }


    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo("VOD", "This service is the back-end of the VOD web page.", null, null, null, null, null);
        return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/.*")).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
    }


}
