package com.squad5.fifo.config;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        Condition<?, ?> skipWhenLongToNotLong = context ->
                context.getMapping().getLastDestinationProperty().getType().equals(Long.class) ==
                        context.getMapping().getSourceType().equals(Long.class);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true)
                .setPropertyCondition(skipWhenLongToNotLong);
        return modelMapper;
    }

}
