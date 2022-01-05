package com.gupaoedu.vip.spring.v2.framework.webmvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewResolver {
    private String name;
    private File templatte;

    public ViewResolver(String name, File templatte) {
        this.name = name;
        this.templatte = templatte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getTemplatte() {
        return templatte;
    }

    public void setTemplatte(File templatte) {
        this.templatte = templatte;
    }

    public String resolver(ModelAndView modelAndView) throws Exception{
        StringBuilder sb = new StringBuilder();
        RandomAccessFile rd = new RandomAccessFile(this.templatte, "r");
        String line;
        while (null !=(line= rd.readLine())) {
            line = new String(line.getBytes("ISO-8859-1"), "utf-8");
            Matcher matcher = matcher(line);
            while (matcher.find()) {
                String dataName = matcher.group(1);
                Object value = modelAndView.getModel().get(dataName);
                line = line.replaceAll("\\$\\{" + dataName + "\\}", value.toString());
                //？这里Tom为何还要多做一次转换
            }
            sb.append(line);
        }

        return sb.toString();
    }

    private Matcher matcher(String line) {
        //${data}格式
        Pattern pattern = Pattern.compile("\\$\\{(.+)\\}");
        return pattern.matcher(line);
    }
}
