using System;
using System.Linq;

namespace LinqDemo
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
        }

        /// <summary>
        /// 1. Создайте массив с набором слов, и найдите наиболее часто встречающееся
        /// </summary>
        public static void Task1(String[] words)
        {
            var sorted = words
                .GroupBy(w => w)
                .OrderByDescending(g => g.Count());
            foreach (var g in sorted)
                Console.WriteLine($"{g.Key} ({g.Count()})");
        }

        /// <summary>
        /// 2. Создайте массив объектов типа Сотрудник (с полями Имя, Возраст, Зарплата) и вычислите среднюю зарплату сотрудника
        /// </summary>
        public static void Task2(Employee[] employees)
        {
            if (employees.Length == 0)
            {
                Console.WriteLine("Нет данных о сотрудниках");
                return;
            }

            var averageSalary = employees.Average(e => e.Salary);
            Console.WriteLine($"Средняя зарплата = {averageSalary}");
        }

        /// <summary>
        /// 3. Ищет самых старших сотрудников и отпечатает в консоль сообщение вида “N самых старших сотрудников зовут: имя1, имя2, имяN;”
        /// </summary>
        public static void Task3(Employee[] employees)
        {
            if (employees.Length == 0)
            {
                Console.WriteLine("Нет данных о сотрудниках");
                return;
            }

            var maxAge = employees.Max(e => e.Age);
            var oldest = employees.Where(e => e.Age == maxAge);
            var names = string.Join(", ", oldest.Select(e => e.Name));

            Console.WriteLine($"{oldest.Count()} самых старших сотрудников зовут: {names}");
        }

        /// <summary>
        /// 4. Взять строку, состоящую из 100 слов разделенных пробелом, получить список слов длиннее 5 символов, и склеить их в одну строку с пробелом в качестве разделителя
        /// </summary>
        public static void Task4(String line)
        {
            var result = string.Join(" ", line.Split(" ").Where(w => w.Length > 5));
            Console.WriteLine(result);
        }

        /// <summary>
        /// 5. Посчитать сумму четных чисел в пределах от 100 до 200 (включительно)
        /// </summary>
        public static void Task5()
        {
            var result = Enumerable
                .Range(100, 100)
                .Where(i => i % 2 == 0)
                .Sum();
            Console.WriteLine(result);
        }

        /// <summary>
        /// 6. Посчитать суммарную длину строк в одномерном массиве
        /// </summary>
        public static void Task6(String[] lines)
        {
            var result = lines.Sum(s => s.Length);
            Console.WriteLine(result);
        }

        /// <summary>
        /// 7. Из массива слов получить первые три слова в алфавитном порядке
        /// </summary>
        public static void Task7(String[] words)
        {
            var firstWords = words
                .OrderBy(s => s)
                .Take(3);
            Console.WriteLine("Первые три слова в алфавитном порядке: " + string.Join(" ", firstWords));
        }
    }
}
