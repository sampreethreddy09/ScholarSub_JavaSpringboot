// package com.demo;

// import com.fasterxml.jackson.core.JsonGenerator;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.SerializationFeature;
// import com.fasterxml.jackson.databind.json.JsonMapper;
// import com.fasterxml.jackson.databind.util.StdDateFormat;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.converter.HttpMessageConverter;
// import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import java.util.List;

// @Configuration
// @EnableWebMvc
// public class WebConfig implements WebMvcConfigurer {

//     @Bean
//     public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//         MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//         ObjectMapper objectMapper = converter.getObjectMapper();

//         // Configure ObjectMapper to write dates as ISO-8601 strings
//         objectMapper.setDateFormat(new StdDateFormat());
        
//         // Prevent quoting of non-numeric numbers and field names
//         objectMapper.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, false);
//         objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        
//         // Optionally, enable "pretty print" feature for JSON responses
//         objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
//         return converter;
//     }

//     @Override
//     public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//         converters.add(mappingJackson2HttpMessageConverter());
//     }
// }
