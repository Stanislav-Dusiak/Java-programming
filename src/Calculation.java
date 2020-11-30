import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Calculation extends RomanArabic {
    public static String calc(int x, String operator, int y) {
        switch (operator) {
            case "+":
                int result = (x + y);
                return (Integer.toString(result));
            case "-":
                int result1 = (x - y);
                return (Integer.toString(result1));
            case "*":
                int result2 = (x * y);
                return (Integer.toString(result2));
            case "/":
                int result3 = (x / y);
                return (Integer.toString(result3));
            default:
                break;
        }
        return ("Вы ввели неверный знак арифметической операции!");
    }

    public static void main(String[] args) {
        String n;
        String m;
        String operation;

        Scanner scanner = new Scanner(System.in);
        n = scanner.next();
        operation = scanner.next();
        m = scanner.next();
        try {
            int value1 = Integer.parseInt(n);
            int value2 = Integer.parseInt(m);
            if (value1 > 0 && value1 <= 10 && value2 > 0 && value2 <= 10) {
                System.out.println(calc(value1, operation, value2));
            } else {
                System.out.println("Переменные должны быть не меньше 1 и не больше 10!");
            }
        } catch (NumberFormatException e) {
            int value1 = romanToArabic(n);
            int value2 = romanToArabic(m);
            if (value1 > 0 && value1 <= 10 && value2 > 0 && value2 <= 10) {
                String result = calc(value1, operation, value2);
                int res = Integer.parseInt(result);
                System.out.println(arabicToRoman(res));
                if (res < 0) {
                    res *= -1;
                    String t = arabicToRoman(res);
                    System.out.println("-" + t);
                }
            } else {
                System.out.println("Переменные должны быть не меньше 1 и не больше 10!");
            }
        }


    }
}


class RomanArabic {
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50),
        XC(90), C(100);

        private final int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException("Неправильный ввод - вводить разрешается только ЦЕЛЫЕ! " +
                    "римские ИЛИ арабские числа");
        }

        return result;
    }

    public static String arabicToRoman(int number) {

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        if (number == 0) {
            System.out.println("N");
        }

        while (number > 0 && i < romanNumerals.size()) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}
