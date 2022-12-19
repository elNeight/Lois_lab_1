package lab1;

import lab1.implication.Implication;
import lab1.tnorma.TNorma;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private TNorma tNorma;
    private Implication implication;

    private double[][] matrix;

    public Matrix(TNorma tNorma, Implication implication) {
        this.tNorma = tNorma;
        this.implication = implication;
    }

    public void buildMatrix(List<Double> set1, List<Double> set2) {
        matrix = new double[set1.size()][];

        for (int i = 0; i < matrix.length; i++)
            matrix[i] = new double[set2.size()];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = implication.evaluate(set1.get(i), set2.get(j));
    }
    public List<Double> getConclusion(List<Double> set) {


        List<Double> resultSet = new ArrayList<>();

        int length = matrix[0].length;

        for (int i = 0; i < length; i++) {

            List<Double> workingSet = new ArrayList<>(set);

            for (int j = 0; j < matrix.length; j++) {
                double matrixValue = matrix[j][i];
                double setValue = workingSet.get(j);
                double result = tNorma.evaluate(matrixValue, setValue);
                workingSet.set(j, result);
            }

            double max = workingSet.stream().mapToDouble(Double::doubleValue).max().orElseThrow();
            resultSet.add(max);

        }

        return resultSet;
    }

}
