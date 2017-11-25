package net.starchl.springbootjpa;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket personApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("net.starchl.springbootjpa")).paths(regex("/person.*"))
				.build().apiInfo(metaDataPerson());
	}

/*	@Bean
	public Docket adressenApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("net.starchl.springbootjpa")).paths(regex("/adresse.*"))
				.build().apiInfo(metaDataAdresse());
	}*/

	private ApiInfo metaDataPerson() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API Personen", "Spring Boot REST API für Personen", "1.0",
				"Terms of service", new Contact("Peter Starchl", "https://nix.com/", "peter@firma.net"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", getVendors());
		return apiInfo;
	}

	private ApiInfo metaDataAdresse() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API Adressen", "Spring Boot REST API für Adressen", "1.0",
				"Terms of service", new Contact("Peter Starchl", "https://nix.com/", "peter@firma.net"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", getVendors());
		return apiInfo;
	}

	@SuppressWarnings("rawtypes")
	private List<VendorExtension> getVendors() {
		MeineVendorExtension mve = new MeineVendorExtension();
		List<VendorExtension> ret = new ArrayList<>();
		ret.add(mve);
		return ret;
	}
}

@SuppressWarnings("rawtypes")
class MeineVendorExtension implements VendorExtension {

	@Override
	public String getName() {
		return "Name";
	}

	@Override
	public Object getValue() {
		return "ICD";
	}

}
