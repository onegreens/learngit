package com.cl.learnSpring.learnIoc.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by cl on 2017/8/18.
 * 自定义转换器
 *
 * 日期转换器
 *
 */
public class DateConverter implements Converter {
    private Locale locale;

    public DateConverter(Locale locale) {
        super();
        this.locale = locale;
    }

    //编写java对象到xml的转换逻辑
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DATE_FIELD,this.locale);
        hierarchicalStreamWriter.setValue(dateFormat.format(o));
    }

    //编写xml到java对象的转换逻辑
    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        GregorianCalendar calendar = new GregorianCalendar();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DATE_FIELD,this.locale);
        try {
            calendar.setTime(dateFormat.parse(hierarchicalStreamReader.getValue()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getGregorianChange();
    }

    //实现该方法，判断需要转换的类型
    @Override
    public boolean canConvert(Class aClass) {
        return Date.class.isAssignableFrom(aClass);
    }
}
