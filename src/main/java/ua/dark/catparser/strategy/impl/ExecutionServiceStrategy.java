package ua.dark.catparser.strategy.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.dark.catparser.entity.Cat;
import ua.dark.catparser.mapper.SimpleRowCatMapper;
import ua.dark.catparser.repo.CatRepository;
import ua.dark.catparser.strategy.ParseStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public List<Cat> process(int size, Sheet sheet) {
        List<Cat> cats = Collections.synchronizedList(new ArrayList<Cat>());
        int batchSize = size / poolSize;
        for (int i = 0; i < poolSize; i++) {
            int startRow = i * batchSize;
            executorService.submit(() -> cats.addAll(processBatch(sheet, startRow, batchSize)));
        }
        return cats;
    }

    @Override
    public boolean isAllowed() {
        return useParallelExecution;
    }
}
