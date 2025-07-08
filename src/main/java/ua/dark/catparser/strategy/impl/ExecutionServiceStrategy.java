package ua.dark.catparser.strategy.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.dark.catparser.mapper.SimpleRowCatMapper;
import ua.dark.catparser.repo.CatRepository;
import ua.dark.catparser.strategy.ParseStrategy;

import java.util.concurrent.ExecutorService;

@Component
public class ExecutionServiceStrategy extends AbstractParseStrategy implements ParseStrategy {

    private final ExecutorService executorService;

    @Value("#{new Boolean('${parallel-execution.executor-service.enabled}')}")
    private Boolean useParallelExecution;
    @Value("#{new Integer('${parallel-execution.executor-service.pools}')}")
    private Integer poolSize;

    @Autowired
    public ExecutionServiceStrategy(CatRepository catRepository, SimpleRowCatMapper catMapper,
                                    ExecutorService executorService) {
        super(catRepository, catMapper);
        this.executorService = executorService;
    }

    @Override
    public void process(int size, Sheet sheet) {
        int batchSize = size / poolSize;
        for (int i = 0; i < poolSize; i++) {
            int startRow = i * batchSize;
            if (useParallelExecution) {
                executorService.submit(() -> processBatch(sheet, startRow, batchSize));
            } else {
                processBatch(sheet, startRow, batchSize);
            }
        }
    }

    @Override
    public boolean isAllowed() {
        return useParallelExecution;
    }
}
