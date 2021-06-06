package ro.fasttrackit.curs12.homework.api.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Curs12ApiConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("studentRemoval.fanout");
    }

    @Bean
    Queue fanoutQue() {
        return new AnonymousQueue();
    }

    //60bcfd3560ed4b445f98bff0

    @Bean
    Binding fanoutBinding(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
