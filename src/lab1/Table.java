package lab1;

import lab1.implication.Implication;
import lab1.tnorma.TNorma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {

    private TNorma tNorma;
    private Implication implication;

    private double[][] table;

    public Table(TNorma tNorma, Implication implication) {
        this.tNorma = tNorma;
        this.implication = implication;
    }

    public void buildTable(List<Double> set1, List<Double> set2) {
        table = new double[set1.size()][];

        for (int i = 0; i < table.length; i++)
            table[i] = new double[set2.size()];

        for (int i = 0; i < table.length; i++)
            for (int j = 0; j < table[i].length; j++)
                table[i][j] = implication.evaluate(set1.get(i), set2.get(j));



    }


    public List<Double> getConclusion(List<Double> set) {


        List<Double> resultSet = new ArrayList<>();

        int length = table[0].length;

        for (int i = 0; i < length; i++) {

            List<Double> workingSet = new ArrayList<>(set);

            for (int j = 0; j < table.length; j++) {
                double tableValue = table[j][i];
                double setValue = workingSet.get(j);
                double result = tNorma.evaluate(tableValue, setValue);
                workingSet.set(j, result);
            }

            double max = workingSet.stream().mapToDouble(Double::doubleValue).max().orElseThrow();
            resultSet.add(max);

        }

        return resultSet;
    }

    public void printTable() {
        System.out.println(Arrays.deepToString(table));
    }

}
