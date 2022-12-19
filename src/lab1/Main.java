package lab1;

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
import lab1.implication.GoguenImplication;
import lab1.reader.FileReader;
import lab1.reader.Reader;
import lab1.tnorma.TNormaImpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final String[] FILES = {"files//file1.txt", "files//file2.txt", "files//file3.txt", "files//file4.txt"};
    private static List<List<Element>> facts = new ArrayList<>();

    private static void loadFiles() throws FileNotFoundException {
        Reader reader = new FileReader();
        for (String path : FILES) {
            facts.add(reader.readFile(path));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        loadFiles();

        int number = 1;
        for (int i = 0; i < facts.size(); i++) {
            for (int j = 0; j < facts.size(); j++, number++) {
                System.out.println(number + ":     " +
                        facts.get(i).get(0).getSetName() +
                        " -> " +
                        facts.get(j).get(0).getSetName());
            }
        }


        Scanner scanner = new Scanner(System.in);
        int option;
        while(true){
            System.out.print("enter rule number to apply : ");
            option = scanner.nextInt();
            if (option < 1 || option >= number) {
                System.out.println("invalid user input: should be between 1 and " + (number - 1));
                continue;
            }
            break;
        }

        List<Element> set1 = null;
        List<Element> set2 = null;
        number = 1;
        String implicationName = null;
        for (int i = 0; i < facts.size(); i++) {
            for (int j = 0; j < facts.size(); j++, number++) {
                if (number == option) {
                    set1 = facts.get(i);
                    set2 = facts.get(j);
                    implicationName = set1.get(0).getSetName() + " -> " + set2.get(0).getSetName();
                }
            }
        }

        System.out.println("You have chosen rule " + option + " : " + implicationName);

        List<Double> fuzzy = new ArrayList<>();

        for (int i = 0; i < set1.size(); i++) {
            while(true){
                System.out.print("enter fuzzy for x" + (i + 1) + ":");
                String str = scanner.next();
                double v = Double.parseDouble(str);
                if (v < 0 || v > 1) {
                    System.out.println("invalid user input: should be in [0, 1]");
                    continue;
                }
                fuzzy.add(v);
                break;
            }
        }


        System.out.println("result of applying the rule " + implicationName + " to");

        StringBuilder setString = new StringBuilder("{");
        for (int i = 0; i < set1.size(); i++) {
            setString
                    .append("(x")
                    .append(i + 1)
                    .append(":")
                    .append(fuzzy.get(i))
                    .append("), ");
        }
        setString
                .delete(setString.length() - 2, setString.length())
                .append("}");

        System.out.println(setString);
        setString = new StringBuilder();
        System.out.println("is");


        //-----------------------------------------


        Matrix matrix = new Matrix(new TNormaImpl(), new GoguenImplication());
        List<Double> firstSet = set1.stream().map(Element::getValue).collect(Collectors.toList());
        List<Double> secondSet = set2.stream().map(Element::getValue).collect(Collectors.toList());

        matrix.buildMatrix(firstSet, secondSet);
        List<Double> conclusion = matrix.getConclusion(fuzzy);


        setString.append("{");
        for (int i = 0; i < conclusion.size(); i++) {
            setString
                    .append("(x")
                    .append(i + 1)
                    .append(":")
                    .append(conclusion.get(i))
                    .append("), ");
        }
        setString
                .delete(setString.length() - 2, setString.length())
                .append("}");

        System.out.println(setString);

    }

}