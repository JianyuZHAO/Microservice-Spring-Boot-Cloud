package VOD_assetTable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class VodAssetTableApplication{


	public static void main(String[] args) {
		SpringApplication.run(VodAssetTableApplication.class, args);
	}
}
