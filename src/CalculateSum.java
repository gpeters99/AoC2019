import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculateSum {


    public static int calculateFuel(int fuelInt) {
        return (fuelInt/3) - 2;
    }


    public static int calculateTotalFuel(int fuel) {
        int sumOfSingleModule = 0;
        while (fuel > 0) {
            fuel = calculateFuel(fuel);
            if (fuel > 0) {
                sumOfSingleModule += fuel;
            }
        }

        return sumOfSingleModule;
    }


    public static void main(String[] args) {
        int sum = 0;
        int fuel = 0;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:/Users/gregory.peters/AoC2019/src/numbers.txt"));
            String line = reader.readLine();
            while (line != null) {
                int module = Integer.parseInt(line);
                fuel = calculateTotalFuel(module);
                sum += fuel;

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sum);

//        System.out.println(calculateFuel("100756"));
    }
}
