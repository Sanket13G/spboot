package com.cwms.helper;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.PropertyDefiner;

public class CurrentDatePropertyDefiner extends ContextAwareBase implements PropertyDefiner {
    @Override
    public String getPropertyValue() {
        // Return the current date in the required format
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        
        return sdf.format(new java.util.Date());
    }
}
