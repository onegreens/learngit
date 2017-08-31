package com.cl.catchUrl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
public class Test{
    public static void main(String[] args) {
        String str = "<script>\n" +
                "    var serverdata = {\n" +
                "        tongji: 'http://www.360kan.com/dianshi/cover.html',\n" +
                "        id: 'PrdocX7kTzbuMH',\n" +
                "        cat: '2',\n" +
                "        mId: '208990',\n" +
                "        title: '我们的爱',\n" +
                "        playsite:[{\"ensite\":\"qq\",\"cnsite\":\"\\u817e\\u8baf\",\"vip\":0},{\"ensite\":\"sohu\",\"cnsite\":\"\\u641c\\u72d0\",\"vip\":0},{\"ensite\":\"imgo\",\"cnsite\":\"\\u8292\\u679cTV\",\"vip\":0},{\"ensite\":\"pptv\",\"cnsite\":\"PPTV\",\"vip\":0}],\n" +
                "        playtype: 'tv'\n" +
                "        \n" +
                "    };\n" +
                "</script>";
        String[] strs = str.split("\\[");
        String[] strs2= strs[1].split("]");
        System.out.println(str);

    }
}