package bni.regression.libraries.ui;

import bni.regression.libraries.common.LaunchBrowser;

import java.util.concurrent.TimeUnit;

public class SelectCountryRegionChapter {
    private bni.regression.pageFactory.BNIConnect bniConnect;
    public void selectCountryRegChap(String country, String region, String chapter) throws Exception{
        TimeUnit.SECONDS.sleep(2);
        bniConnect = new bni.regression.pageFactory.BNIConnect(LaunchBrowser.driver);
        bniConnect.selectCountry(country);
        TimeUnit.SECONDS.sleep(2);
        bniConnect.selectRegion(region);
        TimeUnit.SECONDS.sleep(2);
        bniConnect.selectChapter(chapter);
        TimeUnit.SECONDS.sleep(2);
    }
}
