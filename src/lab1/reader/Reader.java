package lab1.reader;

import lab1.dto.Element;

import java.io.FileNotFoundException;
import java.util.List;

@FunctionalInterface
public interface Reader {
    public List<Element> readFile(String path) throws FileNotFoundException;
}
