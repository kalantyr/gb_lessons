package ru.kalantyr.java.lesson2;

public class MainApp {

    public static void main(String[] args) {
        String[][] values = {
                { "1.1", "2.2", "3.3", "4.4" },
                { "5.5", "6.6", "7.7", "8.8" },
                { "-1", "-2", "-3", "-4" },
                { "-5", "-6", "-7", "-8" }
        };
        try {
            var result = parseAndSum(values);
            System.out.printf("Сумма = %s%n", result);
        }
        catch (MyArrayDataException | MyArraySizeException e) {
            System.out.printf(String.format("Ошибка: (%s)", e.getMessage()));
        }
    }

    /** Парсит данные в таблице и суммирует их
     * @param values массив размером строго 4х4
     * @return Возвращает сумму всех ячеек
     */
    public static float parseAndSum(String[][] values) throws MyArraySizeException, MyArrayDataException {
        if (values == null)
            throw new NullPointerException("values");
        if (values.length != 4)
            throw new MyArraySizeException("Размер массива должен быть 4х4");
        for (var row: values)
            if (row.length != 4)
                throw new MyArraySizeException("Размер массива должен быть 4х4");

        var sum = 0f;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                var s = values[i][j];
                try {
                    sum += Float.parseFloat(s);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Не удалось преобразовать \"%s\" в число (ячейка (%s; %s))", s, i, j), e);
                }
            }
        }
        return sum;
    }

}
