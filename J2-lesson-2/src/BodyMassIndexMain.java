import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BodyMassIndexMain {
    public static void main(String[] args) {
        String inputData = "118 2.05\n" + "106 1.77\n" + "87 1.83\n" + "45 1.12\n" + "70 1.87\n" + "54 1.57\n" + "105 1.76\n" + "50 1.96\n" + "114 1.76\n" + "72 2.45\n" + "53 2.10\n" + "66 2.25\n" + "54 1.50\n" + "95 1.62\n" + "86 1.72\n" + "62 1.57\n" + "65 2.24\n" + "72 1.43\n" + "93 2.01\n" + "109 3.01\n" + "106 2.97\n" + "77 1.69\n" + "114 2.09\n" + "98 1.72\n" + "85 2.46\n" + "113 1.94\n" + "53 1.77\n" + "106 2.30";

        ArrayList<Person> listPerson = getListPerson(inputData);
        for (Person person : listPerson) {
            System.out.println("Рост: " + person.getGrowth() + " вес: " + person.getWeight());
            System.out.println("BMI: " + getBMI(person));
            System.out.println("BMI status: " + checkBMI(getBMI(person)));

        }

    }

    public static float getBMI(Person person) {
        return person.getWeight() / (person.getGrowth() * 2);
    }

    public static String checkBMI(float bmi) {
        if (bmi < 18.5) return "under";
        if (bmi >= 18.5 && bmi < 25.0) return "normal";
        if (bmi >= 25.0 && bmi < 30.0) return "over";
        if (bmi >= 30.0) return "obese";
        return null;
    }

    public static ArrayList<Person> getListPerson(String input) {
        return getListPersonFromListStr(getListPersonStr(input));
    }

    public static Person parseStrToPerson(String input) throws NumberFormatException {
        //На вход получаем параметры одного человека в формате строки:'"118 2.05\n"'

        String weightStr = input.split("\\s")[0];
        String growthStr = input.split("\\s")[1];

        try {
            float growth = Float.parseFloat(growthStr);
            int weight = Integer.parseInt(weightStr);

            return new Person(weight, growth);
        } catch (NumberFormatException n) {
            System.out.println("Неверный формат данных");
        }

        return null;

    }

    public static ArrayList<String> getListPersonStr(String input) {
        ArrayList<String> list = new ArrayList<>();

        for (String person : input.split("\\n")) {
            //System.out.println("Person: "+person);
            list.add(person);
        }

        return list;
    }

    public static ArrayList<Person> getListPersonFromListStr(ArrayList<String> listStr) {
        ArrayList<Person> listPerson = new ArrayList<>();

        for (String personStr : listStr) {
            try {
                listPerson.add(parseStrToPerson(personStr));
            } catch (NumberFormatException n) {
                System.out.println("Неверный формат данных");
            }
        }

        return listPerson;

    }
}

class Person {
    private float growth;
    private int weight;

    public Person(int weight, float growth) {
        this.growth = growth;
        this.weight = weight;
    }

    public float getGrowth() {
        return growth;
    }

    public void setGrowth(int growth) {
        this.growth = growth;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}