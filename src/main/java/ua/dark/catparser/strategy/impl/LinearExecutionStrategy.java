package ua.dark.catparser.strategy.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.dark.catparser.mapper.SimpleRowCatMapper;
import ua.dark.catparser.repo.CatRepository;
import ua.dark.catparser.strategy.ParseStrategy;

@Component
public class LinearExecutionStrategy extends AbstractParseStrategy implements ParseStrategy {

    @Autowired
    public LinearExecutionStrategy(CatRepository catRepository, SimpleRowCatMapper catMapper) {
        super(catRepository, catMapper);
    }

    @Override
    public void process(int size, Sheet sheet) {
        processBatch(sheet, 1, size - 1);
    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
