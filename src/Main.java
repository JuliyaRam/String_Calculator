import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        String expression = s.nextLine();
        char action;
        String[] operand;

        //Определяем знак действия:

        if (expression.contains(" + ")) {
            operand = expression.split(" \\+ ");
            action = '+';
        } else if (expression.contains(" - ")) {
            operand = expression.split(" - ");
            action = '-';
        } else if (expression.contains(" * ")) {
            operand = expression.split(" \\* ");
            action = '*';
        } else if (expression.contains(" / ")) {
            operand = expression.split(" / ");
            action = '/';
        } else {
            throw new Exception("Некорректный знак действия");
        }

        if (isInteger(operand[0])) {
            throw new RuntimeException("Первый операнд должен быть записан в кавычках");
        }

        // Проверяем, является ли текущая операция умножением (*) или делением (/).
        if (action == '*' || action == '/') {
            if (operand[1].contains("\"")) {
                throw new Exception("Строку можно делить или умножать только на число");
            }
        }

        // убираем кавычки, заменяя на пустой символ
        for (int i = 0; i < operand.length; i++) {
            operand[i] = operand[i].replace("\"", "");
        }

        // проверка длины операндов
        if (operand[0].length() > 10 || operand[1].length() > 10) {
            throw new Exception("Строки не должны превышать 10 символов");
        }

        // Выполняем действие:

        // Операция - СЛОЖЕНИЕ
        if (action == '+') {
            String sum = operand[0] + operand[1];
            System.out.println(addQuotation(sum));

        //  Операция - ВЫЧИТАНИЕ
        } else if (action == '-') {
            int index = operand[0].indexOf(operand[1]);
            if (index == -1) {
                System.out.println(addQuotation(operand[0]));
            } else {
                String result = operand[0].substring(0, index);
                result += operand[0].substring(index + operand[1].length());
                System.out.println(addQuotation(result));
            }

        // Операция - УМНОЖЕНИЕ
        } else if (action == '*') {
            int multiplier = Integer.parseInt(operand[1]);
            String result = "";
            if (multiplier >= 1 && multiplier <= 10) {
                for (int i = 0; i < multiplier; i++) {
                    result += operand[0];
                }
                System.out.println(addQuotation(result));
            }
            else {
                throw new Exception("Число должно быть в пределах от 1 до 10");
            }

            //  Операция - ДЕЛЕНИЕ
        } else {
            int divider = Integer.parseInt(operand[1]);
            if (divider >= 1 && divider <= 10) {
                int line = operand[0].length() / divider;
                String result = operand[0].substring(0, line);
                System.out.println(addQuotation(result));
            } else {
                throw new Exception("Число должно быть в пределах от 1 до 10");
            }
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String addQuotation(String text){
        if (text.length() > 40) {
            text = text.substring(0, 40) + "...";
        }
        return ("\""+text+"\"");
    }
}
