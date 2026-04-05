package com.jobApplication.Jobservice.InterserviceCommunication;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CompanyServiceClientConfig
{
     @Bean
     @Primary
     public RestClient.Builder restClientBuilder() {
         return RestClient.builder();
     }

    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public CompanyServiceClient getCompanySericeClinet(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder resBuilder)
    {
        RestClient loRestClient = resBuilder
                .baseUrl("http://company-service")
                .build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(loRestClient);
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(CompanyServiceClient.class);
    }

    @Bean
    public ReviewServiceClient getReviewSericeClinet(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder resBuilder)
    {

        RestClient loRestClient = resBuilder
                .baseUrl("http://review-service")
                .build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(loRestClient);
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(ReviewServiceClient.class);
    }
}
