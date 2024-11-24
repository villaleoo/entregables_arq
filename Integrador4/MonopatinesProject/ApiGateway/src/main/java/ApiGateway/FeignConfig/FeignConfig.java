package ApiGateway.FeignConfig;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                    .filter(requestAttributes -> requestAttributes instanceof ServletRequestAttributes)
                    .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes).getRequest())
                    .map(request -> request.getHeader(HttpHeaders.AUTHORIZATION))
                    .ifPresent(token -> requestTemplate.header(HttpHeaders.AUTHORIZATION, token));
        };
    }
}