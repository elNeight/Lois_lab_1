package lab1;

import lab1.dto.Element;
import lab1.implication.GoguenImplication;
import lab1.reader.FileReader;
import lab1.reader.Reader;
import lab1.tnorma.TNormaImpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

        args = new String[]{
                "files//file1.txt",
                "files//file2.txt",
                "files//file3.txt",
                "files//file4.txt"
        };

        List<List<Element>> facts = new ArrayList<>();
        Reader reader = new FileReader();
        for (String path : args) facts.add(reader.readFile(path));

        int number = 1;

        for (int i = 0; i < facts.size(); i++) {
            for (int j = 0; j < facts.size(); j++, number++) {
                System.out.println(number + ":     " +
                        facts.get(i).get(0).getSetName() +
                        " -> " +
                        facts.get(j).get(0).getSetName());
            }
        }
        System.out.print("enter rule number to apply : ");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

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

        if (option < 1 || option > number) throw new IllegalArgumentException("Illegal argument");

        System.out.println("You have chosen rule " + option + " : " + implicationName);

        List<Double> fuzzy = new ArrayList<>();

        for (int i = 0; i < set1.size(); i++) {
            System.out.print("enter fuzzy for y" + (i + 1) + ":");
            double v = scanner.nextDouble();
            fuzzy.add(v);
        }


        System.out.println("result of applying the rule " + implicationName + " to");

        StringBuilder setString = new StringBuilder("{");
        for (int i = 0; i < set1.size(); i++) {
            setString
                    .append("(y")
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


        Table table = new Table(new TNormaImpl(), new GoguenImplication());
        List<Double> firstSet = set1.stream().map(Element::getValue).toList();
        List<Double> secondSet = set2.stream().map(Element::getValue).toList();

        table.buildTable(firstSet, secondSet);
        List<Double> conclusion = table.getConclusion(fuzzy);

        System.out.println(conclusion);

        setString.append("{");
        for (int i = 0; i < conclusion.size(); i++) {
            setString
                    .append("(y")
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