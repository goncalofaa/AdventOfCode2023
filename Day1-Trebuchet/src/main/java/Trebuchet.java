import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Trebuchet {

    private static final Map<String, String> wordToNumber = Map.of(
            "zero", "z0o",
            "one", "o1e",
            "two", "t2o",
            "three", "t3e",
            "four", "f4r",
            "five", "f5e",
            "six", "s6x",
            "seven", "s7n",
            "eight", "e8t",
            "nine", "n9e"
    );

    public static void main(String[] args) {
        try (InputStream calibrationValuesFile = Trebuchet.class.getClassLoader().getResourceAsStream("calibration_values.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(calibrationValuesFile))) {

            int calibrationValue = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                line = replaceWords(line, wordToNumber);
                int digitSum = calculateDigitSum(line);
                calibrationValue += digitSum;
                System.out.println(calibrationValue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String replaceWords(String line, Map<String, String> replacementMap) {
        for (Map.Entry<String, String> entry : replacementMap.entrySet()) {
            line = line.replaceAll(entry.getKey(), entry.getValue());
        }
        return line;
    }

    private static int calculateDigitSum(String line) {
        String digitsOnly = line.replaceAll("[^0-9]", "");
        if (digitsOnly.length() == 1) {
            return Integer.parseInt(digitsOnly.concat(digitsOnly));
        } else {
            return Integer.parseInt(String.valueOf(digitsOnly.charAt(0))
                    .concat(String.valueOf(digitsOnly.charAt(digitsOnly.length() - 1))));
        }
    }
}


