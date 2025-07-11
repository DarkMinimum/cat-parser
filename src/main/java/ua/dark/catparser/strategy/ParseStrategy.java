package ua.dark.catparser.strategy;

import org.apache.poi.ss.usermodel.Sheet;
import ua.dark.catparser.entity.Cat;

import java.util.List;

/// The interface represents contract for check whether specific strategy is allowed and processes it.
/// - `boolean isAllowed();` checks if strategy is allowed (basically in terms of configuration)
/// - `void process(int size, Sheet sheet);` specific way to process data
public interface ParseStrategy {

    List<Cat> process(int size, Sheet sheet);

    boolean isAllowed();
}
