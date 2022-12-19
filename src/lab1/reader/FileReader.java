package lab1.reader;

//////////////////////////////////////////////////////////////////////////////////////
// Лабораторная работа 4 по дисциплине ЛОИС
// Выполнена студентами группы 921703 БГУИР
// Семякин А.В., Новицкий С.В., Кравцов М.С., Губаревич В.А.
// Вариант 22 - Реализовать прямой нечеткий логический вывод, используея импликацию Гогена
// 17.12.2022
// Использованные материалы:
// "Прикладные нечеткие системы" Т.Тэрано, К.Асаи, М.Сугэно
// Методическое пособие по курсу "Логические основы интеллектуальных систем"
// Благодарность за помощь в реализации алгоритма выражается Валюкевичу В.И.

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
