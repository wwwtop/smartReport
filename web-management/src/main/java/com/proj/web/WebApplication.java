package com.proj.web;

import com.proj.model.annotation.DefaultValueAnno;
import com.proj.model.vo.second.SecondCircularVO;
import com.proj.proxy.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * web能力中心启动类
 *
 * @author dong.ning
 */
@SpringBootApplication
@MapperScan("com.proj.web.mapper")
public class WebApplication {

    /**
     * 程序主入口
     *
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebApplication.class);

        outputRunInfo(context);
        Test.test2();
//
//        System.out.println(String.format("%03d", 3));
//        System.out.println(String.format("%03d", 53));
//        System.out.println(String.format("%03d", 103));
//        System.out.println(String.format("%03d", 1103));
//
//        System.out.println("----------------------");
//
//        SecondCircularVO vo = new SecondCircularVO();
//        //vo.setFieldset1("sdkfjsdf");
//        vo.setFieldset2("sdlkjflskdjfl");
//
//
//        Map<String, List<String>> map = new LinkedHashMap<>();
//        List<String> fLists = new LinkedList<>();
//        fLists.add("fieldset1");
//        map.put("BP28", fLists);
//
//        Field[] fields = vo.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            boolean isHas = field.isAnnotationPresent(DefaultValueAnno.class);
//            if (isHas) {
//                String defaultValue = field.getAnnotation(DefaultValueAnno.class).defaultValue();
//                String werkName = field.getAnnotation(DefaultValueAnno.class).werkName();
//
//                String value = null;
//                try {
//                    field.setAccessible(true);
//                    value = (null == field.get(vo) ? "" : field.get(vo).toString());
//                    if (StringUtils.isEmpty(value)) {
//                        if (!StringUtils.isEmpty(defaultValue)) {
//
//                            if (!StringUtils.isEmpty(werkName)) {
//                                if (map.containsKey(werkName)) {
//                                    List<String> fdLists = map.get(werkName);
//                                    if (fdLists.contains(field.getName())) {
//                                        value = defaultValue;
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//
//                    field.set(vo, value);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//        System.out.println(vo.getFieldset1());

    }

    /**
     * 输出启动信息
     *
     * @param context
     */
    private static void outputRunInfo(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();

        String port = environment.getProperty("server.port");
        String active = environment.getProperty("spring.profiles.active", "");

        StringBuilder stringBuilder = new StringBuilder()
                .append("-----------------------\n")
                .append(String.format("----环境：%s\n", active))
                .append(String.format("----端口：%s\n", port))
                .append(String.format("----swagger，接口调试地址：http://localhost:%s/swagger-ui.html\n", port))
                .append("--启动成功----\n");
        System.out.println(stringBuilder.toString());
    }

}
