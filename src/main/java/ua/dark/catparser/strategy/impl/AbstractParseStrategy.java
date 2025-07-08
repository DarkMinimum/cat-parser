package ua.dark.catparser.strategy.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import ua.dark.catparser.entity.Cat;
import ua.dark.catparser.mapper.SimpleRowCatMapper;
import ua.dark.catparser.repo.CatRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParseStrategy {

    protected final CatRepository catRepository;
    protected final SimpleRowCatMapper catMapper;

    protected AbstractParseStrategy(CatRepository catRepository, SimpleRowCatMapper catMapper) {
        this.catRepository = catRepository;
        this.catMapper = catMapper;
    }

    protected void processBatch(final Sheet sheet, int startRow, int batchSize) {
        List<Cat> catList = new ArrayList<>();
        int offset = startRow;
        while (offset < sheet.getLastRowNum() && offset < startRow + batchSize) {
            Row row = sheet.getRow(offset);
            catList.add(catMapper.rowToCat(row));
            offset++;
        }
        catRepository.saveAll(catList);
    }
}
