package ua.dark.catparser.strategy.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.dark.catparser.action.CatRecursiveAction;
import ua.dark.catparser.entity.Cat;
import ua.dark.catparser.mapper.SimpleRowCatMapper;
import ua.dark.catparser.repo.CatRepository;
import ua.dark.catparser.strategy.ParseStrategy;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Component
public class ForkJoinPoolStrategy extends AbstractParseStrategy implements ParseStrategy {

    private final ForkJoinPool forkJoinPool;

    @Value("#{new Boolean('${parallel-execution.fork-join.enabled}')}")
    private Boolean useForkJoinPool;
    @Value("#{new Integer('${parallel-execution.fork-join.workload}')}")
    private Integer workloadAmount;

    @Autowired
    public ForkJoinPoolStrategy(CatRepository catRepository, SimpleRowCatMapper catMapper,
                                ForkJoinPool forkJoinPool) {
        super(catRepository, catMapper);
        this.forkJoinPool = forkJoinPool;
    }

    @Override
    public List<Cat> process(int size, Sheet sheet) {
        return forkJoinPool.invoke(
                new CatRecursiveAction(this, workloadAmount, sheet, 1, size, catRepository, catMapper)
        );
    }

    @Override
    public boolean isAllowed() {
        return useForkJoinPool;
    }

    public List<Cat> callBatchProcess(Sheet sheet, int startIndex, int size) {
        return processBatch(sheet, startIndex, size);
    }

}
