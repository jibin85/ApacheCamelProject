package com.example.fileComponent.transformation.dataFormat;

import lombok.NoArgsConstructor;
import org.apache.camel.model.dataformat.BeanioDataFormat;

@NoArgsConstructor
public class BeanIODataFormatFactory {
    public static BeanioDataFormat beanio(String streamName, String mapping) {
        BeanioDataFormat beanioDataFormat = new BeanioDataFormat();
        beanioDataFormat.setStreamName(streamName);
        beanioDataFormat.setMapping(mapping);
        return beanioDataFormat;
    }
}
