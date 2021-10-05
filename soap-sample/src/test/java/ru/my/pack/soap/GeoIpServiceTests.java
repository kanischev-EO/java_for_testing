package ru.my.pack.soap;

import net.webservicex.GeoIPService;
import net.webservicex.GetIpLocation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("185.13.112.96");
        Assert.assertTrue(ipLocation.contains("RU"));
    }
    @Test
    public void testInvalidIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("185.13.112.xxx");
        Assert.assertTrue(ipLocation.contains("RU"));
    }

}
