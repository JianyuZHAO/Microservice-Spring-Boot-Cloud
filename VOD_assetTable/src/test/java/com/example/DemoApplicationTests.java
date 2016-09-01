package com.example;

import VOD_assetTable.DAO.AssetRepository;
import VOD_assetTable.VodAssetTableApplication;
import VOD_assetTable.entity.Asset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VodAssetTableApplication.class)
@WebIntegrationTest("server.port:8080")

public class DemoApplicationTests {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private AssetRepository assetRepository;


    private MockMvc mockMvc;
    private RestTemplate restTemplate = new TestRestTemplate();


    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

	@Test
	public void getAssetByIDAPI() {

        Asset asset = restTemplate.getForObject("http://localhost:8080/asset/getAssetByID/720508903", Asset.class);
        assertNotNull(asset);
        assertEquals("Les p'tits diables", asset.getAssetName());

	}





}
