package lab1.dto;

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
