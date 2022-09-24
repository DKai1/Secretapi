package com.kevin.secret.config;

import com.kevin.secret.util.SecretApiAdvice;
import com.kevin.secret.util.SecretApiProperties;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dengkai
 */
@Configuration
public class SecretApiConfiguration {
    public static final String traceExecution = "@annotation(com.kevin.secret.annotation.SecretApi)";

    public SecretApiConfiguration() {
    }

    @Bean
    public SecretApiProperties getSecretApiProperties() {
        return new SecretApiProperties();
    }

    @Bean
    public DefaultPointcutAdvisor secretApiPointcutAdvisor(SecretApiProperties secretApiProperties) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(com.kevin.secret.annotation.SecretApi)");
        advisor.setPointcut(pointcut);
        advisor.setAdvice(new SecretApiAdvice(secretApiProperties));
        return advisor;
    }
}