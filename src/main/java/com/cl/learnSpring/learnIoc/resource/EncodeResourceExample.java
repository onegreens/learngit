package com.cl.learnSpring.learnIoc.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * Created by cl on 2017/8/17.
 * 资源加载时默认采用系统编码读取资源内容。
 * 如果资源文件采用特殊的编码格式，那么可以通过EncodedResource对资源进行编码，以保证资源内容操作的正确性
 *
 */
public class EncodeResourceExample {
    public static void main(String[] args) throws IOException {
        Resource resource = new ClassPathResource("conf/file1.txt");
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        String content = FileCopyUtils.copyToString(encodedResource.getReader());
        System.out.println(content);
    }
}
