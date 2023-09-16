package com.proj.proxy;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 代理的配置文件配置类
 *
 * @author dong.ning
 */
public class ProxyConfig {

    /**
     * 配置文件参数url
     */
    private String url;

    public String getUrl() {
        return this.url;
    }


    private static ProxyConfig config = null;

    public static ProxyConfig getGlobalConfig() {
        return config;
    }

    static {
        final String FN = "proxyconfig.properties";

        System.out.println("正在加载proxy的代理配置文件");

        try {
            Properties properties = new Properties();

            InputStream inputStream = ProxyConfig.class.getClassLoader().getResourceAsStream(FN);
            properties.load(inputStream);

            config = new ProxyConfig();
            String url = properties.getProperty("url");
            if (StringUtils.isEmpty(url)) {
                System.out.println(FN + "，URL参数，值为空");
            }
            config.url = url;
        } catch (Exception ex) {
            ex.printStackTrace();
            config = null;
        }
    }

}
