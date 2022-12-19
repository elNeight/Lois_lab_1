package lab1.reader;

import lab1.dto.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReader implements Reader {
    @Override
    public List<Element> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String setName = scanner.next();
        scanner.next();

        String set = scanner.nextLine();
        set = set.substring(2, set.length() - 1);
        String[] strings = set.split(", ");

        return Arrays.stream(strings)
                .map(s -> s.substring(1, s.length() - 1))
                .map(s -> {
                    String[] split = s.split(":");
                    return new Element(split[0], Double.parseDouble(split[1]), setName);
                })
                .collect(Collectors.toList());
    }
}
