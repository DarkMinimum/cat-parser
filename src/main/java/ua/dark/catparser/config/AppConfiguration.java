package ua.dark.catparser.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ua.dark.catparser.strategy.ParseStrategy;
import ua.dark.catparser.strategy.impl.ExecutionServiceStrategy;
import ua.dark.catparser.strategy.impl.ForkJoinPoolStrategy;
import ua.dark.catparser.strategy.impl.LinearExecutionStrategy;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

@EnableAspectJAutoProxy
@Configuration
@Aspect
public class AppConfiguration {

    @Value("#{new Integer('${parallel-execution.executor-service.pools}')}")
    private Integer poolSize;
    @Value("#{new Boolean('${parallel-execution.executor-service.virtual}')}")
    private Boolean useVirtual;

    @Bean
    public ExecutorService executorService() {
        return useVirtual ? Executors.newVirtualThreadPerTaskExecutor() : Executors.newFixedThreadPool(poolSize);
    }

    @Bean
    public ForkJoinPool forkJoinPool() {
        return new ForkJoinPool(poolSize);
    }

    @Bean
    public List<ParseStrategy> parseStrategyList(ForkJoinPoolStrategy forkJoinPoolStrategy,
                                                 ExecutionServiceStrategy executionServiceStrategy,
                                                 LinearExecutionStrategy linearExecutionStrategy) {
        return List.of(forkJoinPoolStrategy, executionServiceStrategy, linearExecutionStrategy);
    }

    @Pointcut(
            "execution(public void ua.dark.catparser.service.RawReader.processFile(..))"
    )
    public void monitor() {
    }

    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new PerformanceMonitorInterceptor(false);
    }

    @Bean
    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("ua.dark.catparser.config.AppConfiguration.monitor()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }
}
