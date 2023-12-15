package examportal.portal.Config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    Logger log = LoggerFactory.getLogger("Config  -> AsyncConfig.java");

    @Override
    public Executor getAsyncExecutor() {
        log.info("Config  -> AsyncConfig.java, getAsyncExecutor Method Start");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        log.info("Config  -> AsyncConfig.java, getAsyncExecutor Method End");
        return executor;
    }
}
