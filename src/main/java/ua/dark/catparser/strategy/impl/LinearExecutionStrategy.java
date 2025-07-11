package ua.dark.catparser.strategy.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.dark.catparser.entity.Cat;
import ua.dark.catparser.mapper.SimpleRowCatMapper;
import ua.dark.catparser.repo.CatRepository;
import ua.dark.catparser.strategy.ParseStrategy;

import java.util.List;

@Component
public class LinearExecutionStrategy extends AbstractParseStrategy implements ParseStrategy {

    @Autowired
    public LinearExecutionStrategy(CatRepository catRepository, SimpleRowCatMapper catMapper) {
        super(catRepository, catMapper);
    }

    @Override
    public List<Cat> process(int size, Sheet sheet) {
        return processBatch(sheet, 1, size - 1);
    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
