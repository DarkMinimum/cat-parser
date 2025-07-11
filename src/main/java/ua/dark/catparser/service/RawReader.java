package ua.dark.catparser.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.dark.catparser.enums.StrategyType;
import ua.dark.catparser.strategy.ParseStrategy;

import java.io.InputStream;
import java.util.List;

@Service
public class RawReader {

    private static final Logger LOG = LoggerFactory.getLogger(RawReader.class);

    private final List<ParseStrategy> parseStrategyList;

    @Autowired
    public RawReader(List<ParseStrategy> parseStrategyList) {
        IOUtils.setByteArrayMaxOverride(260000000);
        this.parseStrategyList = parseStrategyList;
    }

    public void processFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (ParseStrategy strategy : parseStrategyList) {
                if (strategy.isAllowed()) {
                    LOG.info("Strategy {} is picked for parse", strategy.getClass());
                    strategy.process(sheet.getPhysicalNumberOfRows(), sheet);
                    return;
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }


    public void processFile(MultipartFile file, StrategyType strategyType) {
        try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            var strategy = getStrategyByEnum(strategyType);
            LOG.info("Strategy {} is picked for parse", strategy.getClass());
            strategy.process(sheet.getPhysicalNumberOfRows(), sheet);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private ParseStrategy getStrategyByEnum(StrategyType strategyType) {
        return switch (strategyType) {
            case FORK -> parseStrategyList.get(0);
            case EXEC -> parseStrategyList.get(1);
            case LINEAR -> parseStrategyList.get(2);
            case null, default -> throw new RuntimeException("No parse strategy available");
        };
    }
}
