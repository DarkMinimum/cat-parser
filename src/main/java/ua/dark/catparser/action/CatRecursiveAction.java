package ua.dark.catparser.action;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.dark.catparser.entity.Cat;
import ua.dark.catparser.mapper.SimpleRowCatMapper;
import ua.dark.catparser.repo.CatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class CatRecursiveAction extends RecursiveAction {

    private static final Logger LOG = LoggerFactory.getLogger(CatRecursiveAction.class);

    private final int workload;
    private final int leftIndex;
    private final int rightIndex;
    private final CatRepository catRepository;
    private final SimpleRowCatMapper catMapper;
    private final Sheet sheet;

    public CatRecursiveAction(int workload, Sheet sheet, int leftIndex, int rightIndex, CatRepository catRepository, SimpleRowCatMapper catMapper) {
        this.workload = workload;
        this.sheet = sheet;
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
        this.catRepository = catRepository;
        this.catMapper = catMapper;
    }

    @Override
    protected void compute() {
        if (rightIndex - leftIndex > workload) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
            processBatch(leftIndex, rightIndex);
        }
    }

    private List<CatRecursiveAction> createSubtasks() {
        final int newIndex = leftIndex + (rightIndex - leftIndex) / 2;
        return List.of(
                new CatRecursiveAction(workload, sheet, leftIndex, newIndex, catRepository, catMapper),
                new CatRecursiveAction(workload, sheet, newIndex, rightIndex, catRepository, catMapper));
    }

    private void processBatch(int leftIndex, int rightIndex) {
        List<Cat> catList = new ArrayList<>();
        int offset = leftIndex;
        while (offset < sheet.getLastRowNum() && offset <= rightIndex) {
            Row row = sheet.getRow(offset);
            catList.add(catMapper.rowToCat(row));
            offset++;
        }
//        catRepository.saveAll(catList);
    }

}