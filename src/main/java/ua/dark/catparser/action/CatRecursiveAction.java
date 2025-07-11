package ua.dark.catparser.action;

import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.dark.catparser.entity.Cat;
import ua.dark.catparser.mapper.SimpleRowCatMapper;
import ua.dark.catparser.repo.CatRepository;
import ua.dark.catparser.strategy.impl.ForkJoinPoolStrategy;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CatRecursiveAction extends RecursiveTask<List<Cat>> {

    private static final Logger LOG = LoggerFactory.getLogger(CatRecursiveAction.class);

    private final ForkJoinPoolStrategy caller;
    private final int workload;
    private final int leftIndex;
    private final int rightIndex;
    private final CatRepository catRepository;
    private final SimpleRowCatMapper catMapper;
    private final Sheet sheet;

    public CatRecursiveAction(ForkJoinPoolStrategy caller, int workload, Sheet sheet, int leftIndex, int rightIndex, CatRepository catRepository, SimpleRowCatMapper catMapper) {
        this.caller = caller;
        this.workload = workload;
        this.sheet = sheet;
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
        this.catRepository = catRepository;
        this.catMapper = catMapper;
    }

    @Override
    protected List<Cat> compute() {
        if (rightIndex - leftIndex > workload) {
            return ForkJoinTask.invokeAll(createSubtasks()).stream()
                    .map(ForkJoinTask::join)
                    .flatMap(Collection::stream)
                    .toList();
        } else {
            return caller.callBatchProcess(sheet, leftIndex, rightIndex);
        }
    }

    private List<CatRecursiveAction> createSubtasks() {
        final int newIndex = leftIndex + (rightIndex - leftIndex) / 2;
        return List.of(
                new CatRecursiveAction(caller, workload, sheet, leftIndex, newIndex, catRepository, catMapper),
                new CatRecursiveAction(caller, workload, sheet, newIndex, rightIndex, catRepository, catMapper));
    }

}