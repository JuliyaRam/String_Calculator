import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws Exception {
        // создание объекта `Scanner` для считывания пользовательского ввода из консоли.
        Scanner s = new Scanner(System.in);
        // считывание введенной пользователем строки и сохранение ее в переменной `expression`.
        String expression = s.nextLine();
        char action; // определяем тип данных для математической операции
        String[] operand;  // определяем массив данных


        //Определяем знак действия:

        // если выражение содержит знак '+', разделяем строку по символу " + "
        // и устанавливаем переменную `action` в '+'
        if (expression.contains(" + ")) {
            operand = expression.split(" \\+ ");
            action = '+';
            // если выражение содержит знак '-', разделяем строку по символу " - "
            // и устанавливаем переменную `action` в '-'
        } else if (expression.contains(" - ")) {
            operand = expression.split(" - ");
            action = '-';
            // если выражение содержит знак '*', разделяем строку по символу " * "
            // и устанавливаем переменную `action` в '*'
        } else if (expression.contains(" * ")) {
            operand = expression.split(" \\* ");
            action = '*';
            // если выражение содержит знак '/', разделяем строку по символу " / "
            // и устанавливаем переменную `action` в '/'
        } else if (expression.contains(" / ")) {
            operand = expression.split(" / ");
            action = '/';
        } else {
            throw new Exception("Некорректный знак действия");
        }

        if (isInteger(operand[0])) {
            throw new RuntimeException("Первый операнд должен быть записан в кавычках");
        }

        if (operand[0].length() > 12 || operand[0].length() > 12) {
            throw new IllegalArgumentException("Операнды должны быть длиной не более 10 символов");
        }

        // Проверяем, является ли текущая операция умножением (*) или делением (/).
        // Если операция не является умножением или делением, то блок кода внутри условия не будет выполнен.
        if (action == '*' || action == '/') {
            // Если операция '*' или '/',
            // проверяем, содержит ли 2-ой операнд (хранится в `operand[1]`) символ двойных кавычек '"' с помощью метода `contains("\"")`.
            if (operand[1].contains("\"")) {
                // Если 2-ой операнд содержит символ кавычек, значит это строка, и выбрасываем исключение
                throw new Exception("Строку можно делить или умножать только на число");
            }
        }

        // Проходим по каждому элементу массива и убираем кавычки, заменяя на пустой символ
        for (int i = 0; i < operand.length; i++) {
            //заменяем кавычки на пустой символ
            operand[i] = operand[i].replace("\"", "");
        }

               // Добавляем условие - проверка длины операндов
        if (operand[0].length() > 10 || operand[1].length() > 10) {
            throw new Exception("Строки не должны превышать 10 символов");
        }

// Выполняем действие:

        // Операция - СЛОЖЕНИЕ
        if (action == '+') {
            String sum = operand[0] + operand[1]; // объединяем две строки
            System.out.println(addQuotation(sum));                    // результат выводим на экран с добавлением кавычек
            //System.out.println(addQuotation(result));
            //  Операция - ВЫЧИТАНИЕ
        } else if (action == '-') {
            //index - это позиция, на которой начинается второй операнд `operand[1]`
            int index = operand[0].indexOf(operand[1]);   // Удаляем подстроку operand[1] из строки operand[0].
            if (index == -1) {                             // Если подстрока не найдена, то выводится исходная строка.
                System.out.println(addQuotation(operand[0]));                // Результат выводим на экран с добавлением кавычек
            } else {
                //если подстрока найдена, создается новая строка result,
                // в которую копируются символы до индекса index, а затем символы после удаленной подстроки.
                // В эту строку копируем символы из операнда `operand[0]` до индекса `index`
                String result = operand[0].substring(0, index);
                // Затем к строке `result` добавляем символы из операнда `operand[0]`, начиная с позиции `index + длина второго операнда` и далее.
                // Таким образом, символы второго операнда удаляются из строки `operand[0]`.
                result += operand[0].substring(index + operand[1].length());
                // обновленный результат выводим на экран с добавлением кавычек
                System.out.println(addQuotation(result));
            }

            // Операция - УМНОЖЕНИЕ

            // Если операция является умножением (значение `action` равно `'*'`),
            // программа продолжает выполнение следующих действий.
        } else if (action == '*') {
            // Преобразуется 2-ой операнд (хранится в `operand[1]`) в целочисленное значение
            // с помощью метода `Integer.parseInt(operand[1])`.
            // Это число определяет, сколько раз нужно умножить первый операнд `operand[0]`.
            int multiplier = Integer.parseInt(operand[1]);
            // Создается пустая строка `result`, в которую будет накапливаться результат умножения.
            String result = "";
            // Если 2-ой операнд в пределах от 1 до 10, то выполняем цикл for
            if (multiplier >= 1 && multiplier <= 10) {
                // Запускается цикл `for`, где переменная `i` принимает значения от 0 до `multiplier - 1`.
                // На каждой итерации к строке `result` добавляется значение первого операнда `operand[0]`.
                // Таким образом, в итоге строка `result` будет содержать повторяющееся значение
                // первого операнда умноженное на значение `multiplier` раз.
                for (int i = 0; i < multiplier; i++) {
                    result += operand[0];
                }
                // результат выводим на экран с добавлением кавычек
                System.out.println(addQuotation(result));
            }
            // если операнд меньше 0 или больше 10, выбрасывается исключение
            else {
                throw new Exception("Число должно быть в пределах от 1 до 10");
            }

            //  Операция - ДЕЛЕНИЕ
        } else {
            // Находим результат деления длины первого операнда `operand[0]` на целочисленное значение второго операнда,
            // которое получается с помощью метода `Integer.parseInt(operand[1])`.
            // Таким образом, программа вычисляет целочисленное значение част-ного от деления длины первого операнда
            // на значение второго операнда.
            int divider = Integer.parseInt(operand[1]);
            if (divider >= 1 && divider <= 10) {
                int line = operand[0].length() / divider;
                //Затем, из первого операнда `data[0]` берется подстрока, начиная с позиции 0 и заканчивая длиной,
                // полученной в результате деления, и сохраняется в переменной `result`.
                // Другими словами, программа обрезает первый операнд до дли-ны,
                // которая равна делению его длины на значение второго операн-да.
                String result = operand[0].substring(0, line);
                // результат выводим на экран с добавлением кавычек
               // String res = addQuotation(result);
                //System.out.println(res);
                //String res = addQuotation(result);
                System.out.println(addQuotation(result));

                //addQuotation(result);
            } else {
                throw new Exception("Число должно быть в пределах от 1 до 10");
            }

        }}

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Метод - добавление кавычек к результату математической операции
    private static String addQuotation(String text){
        if (text.length() > 40) {
            text = text.substring(0, 40) + "...";
        }
        return ("\""+text+"\"");
    }
}
