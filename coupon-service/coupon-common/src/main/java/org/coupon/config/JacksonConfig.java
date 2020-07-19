package org.coupon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.coupon.constant.CommonConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class JacksonConfig  {
    @Bean
    public ObjectMapper getObjectMapper() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(
                CommonConstant.DATE_TIME_PATTERN
        ));
        return mapper;
    }
}
