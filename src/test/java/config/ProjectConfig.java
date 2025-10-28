package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.openqa.selenium.MutableCapabilities;

import java.util.Map;

public class ProjectConfig {
    private final WebConfig webConfig;

    public ProjectConfig(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void apiConfig() {
        RestAssured.baseURI = webConfig.baseUrl();
    }

    public void webConfig() {
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.pageLoadStrategy = webConfig.pageLoadStrategy();
        Configuration.browserSize = webConfig.browserSize();

        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();
            Configuration.browserCapabilities = new MutableCapabilities();
            Configuration.browserCapabilities.setCapability("selenoid:options",
                    Map.<String, Object>of(
                            "enableVNC", true,
                            "enableVideo", true
                    )
            );
        }
    }
}
