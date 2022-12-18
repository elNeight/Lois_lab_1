package lab1.dto;

public class Element {

    private String name;
    private double value;
    private String setName;


    public Element(String name, double value, String setName) {
        this.name = name;
        this.value = value;
        this.setName = setName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    @Override
    public String toString() {
        return "[name : " + name + ", value : " + value + "]";
    }
}
