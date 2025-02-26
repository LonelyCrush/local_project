package com.lzf.local.d2501.d250212;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author leizefeng
 */
@Configuration
public class WebFluxDemoConfig {

  @Bean
  public RouterFunction<ServerResponse> helloRouter() {
    // 暂时无效，不确定是否由于不兼容导致
    return RouterFunctions.route(
        RequestPredicates.GET("/d250212/hello1"),
        request -> ServerResponse.ok().bodyValue("Hello, Spring WebFlux!")
    );
  }
}
