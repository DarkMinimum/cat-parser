package ua.dark.catparser.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.dark.catparser.entity.Cat;
import ua.dark.catparser.mapper.SimpleRowCatMapper;
import ua.dark.catparser.repo.CatRepository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class RawReader {
    private static final Logger LOG = LoggerFactory.getLogger(RawReader.class);
    private final CatRepository catRepository;
    private final ExecutorService executorService;
    private final SimpleRowCatMapper catMapper;

    @Value("#{new Integer('${spring.application.use-parallel.pools}')}")
    private Integer poolSize;
    @Value("#{new Boolean('${spring.application.use-parallel}')}")
    private Boolean useParallelExecution;

    @Autowired
    public RawReader(CatRepository catRepository, ExecutorService executorService, SimpleRowCatMapper catMapper) {
        this.catRepository = catRepository;
        this.executorService = executorService;
        this.catMapper = catMapper;
    }

    public void processFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            int size = sheet.getPhysicalNumberOfRows();
            int batchSize = size / poolSize;
            for (int i = 0; i < poolSize; i++) {
                int startRow = i * batchSize;
                if (useParallelExecution) {
                    executorService.submit(() -> processBatch(sheet, startRow, batchSize));
                } else {
                    processBatch(sheet, startRow, batchSize);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private void processBatch(final Sheet sheet, int startRow, int batchSize) {
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
