package com.jobApplication.ReviewService.ClientServieConfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CompanyClientConfig {

    @Bean
    @Primary
    public RestClient.Builder restlientbuilder()
    {
        return RestClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestClient.Builder companyrestlientbuilder()
    {
        return RestClient.builder();
    }

    @Bean
    public CompanyClient getCompanySericeClinet(@Qualifier("companyrestlientbuilder") RestClient.Builder resBuilder)
    {
        RestClient loRestClient = resBuilder
                .baseUrl("http://company-service")
                .build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(loRestClient);
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(CompanyClient.class);
    }
}
