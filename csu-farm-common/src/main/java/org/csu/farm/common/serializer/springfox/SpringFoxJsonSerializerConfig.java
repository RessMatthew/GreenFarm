

package org.csu.farm.common.serializer.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.json.JsonSerializer;

import java.util.List;


@Configuration
public class SpringFoxJsonSerializerConfig {

    @Bean
    @Primary
    public JsonSerializer yamiSpringfoxJsonSerializer(List<JacksonModuleRegistrar> moduleRegistrars) {
        return new SpringfoxJsonSerializer(moduleRegistrars);
    }
}
