package com.taskflow.taskflow.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new Converter<String, UUID>() {
            @Override
            public UUID convert(MappingContext<String, UUID> context) {
                return context.getSource() != null ? UUID.fromString(context.getSource()) : null;
            }
        });
        return new ModelMapper();
    }
}
