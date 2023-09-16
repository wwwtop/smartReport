package com.proj.proxy;

import java.io.InputStream;
import java.util.Properties;

public class Test {

    public static void test2() {
        ProxyConfig config = ProxyConfig.getGlobalConfig();
        System.out.println(config.getUrl());
    }

    public static void test1(String fn) {
        ProxyConfig config = ProxyConfig.getGlobalConfig();
        //config.getUrl()

        try {
            Properties properties = new Properties();

            //ClassLoader classLoader = ClassLoaderTest

        /*FileInputStream fileInputStream = new FileInputStream("proxyconfig.properties");
        properties.load(fileInputStream);*/

            InputStream inputStream = Test.class
                    .getClassLoader().getResourceAsStream(fn);
            properties.load(inputStream);

            System.out.println(properties.getProperty("url"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        //ClassLoader classLoader = ClassLoaderTest

        /*FileInputStream fileInputStream = new FileInputStream("proxyconfig.properties");
        properties.load(fileInputStream);*/

        InputStream inputStream = Test.class
                .getClassLoader().getResourceAsStream("proxyconfig.properties");
        properties.load(inputStream);

        System.out.println(properties.getProperty("url"));
    }
}
