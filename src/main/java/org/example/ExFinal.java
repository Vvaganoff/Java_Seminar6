package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
• Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
• Создать множество ноутбуков.
• Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и
выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
• Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
• Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.
 */

public class ExFinal {
    public static void main(String[] args) {
        String file = "D:\\Обучение Разработчик\\Знакомство с языками\\Java\\Семинары\\sem6\\src\\main\\java\\org\\example\\BD.txt";
        System.out.println("Вы хотите ввести новые ноутбуки? (да или нет)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        HashSet<Notebooks> notebooksHashSet = new HashSet<>();
        if (answer.equals("Да") || answer.equals("да")) {
            NewNotebook(scanner, file);
        } else {
            notebooksHashSet = ReadBD(file);
        }
        Map<Integer, String> filtersMap = InputFilters(scanner);
        List<Notebooks> notebooksList = FilterNotebooks(notebooksHashSet, filtersMap);
        for (Notebooks item : notebooksList
        ) {
            System.out.println(item.toPrint());
        }
    }


    /**
     * Функция выполняет фильтрацию базы ноутбуков по заданным параметрам
     * @param notebooksHashSet база ноутбуков, HashSet&lt;Notebooks&gt;
     * @param filtersMap набор параметров, введённых пользователем, Map&lt;Integer,String&gt;
     * @return список ноутбуков, подходящих по параметрам фильтрации, List&lt;Notebooks&gt;
     */
    private static List<Notebooks> FilterNotebooks(HashSet<Notebooks> notebooksHashSet, Map<Integer, String> filtersMap) {
        List<Notebooks> resultList;
        Set<Notebooks> resultHashSet = notebooksHashSet;
        for (Map.Entry<Integer, String> entry : filtersMap.entrySet()
        ) {
            if (entry.getKey() == 1) {
                resultHashSet = resultHashSet.stream().filter(notebooks -> notebooks.getRam().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 2) {
                resultHashSet = resultHashSet.stream().filter(notebooks -> notebooks.getRamSize().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 3) {
                resultHashSet = resultHashSet.stream().filter(notebooks -> notebooks.getProcessor().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 4) {
                resultHashSet = resultHashSet.stream().filter(notebooks -> notebooks.getNumberOfCore().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 5) {
                resultHashSet = resultHashSet.stream().filter(notebooks -> notebooks.getHdd().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 6) {
                resultHashSet = resultHashSet.stream().filter(notebooks -> notebooks.getHddSize().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 7) {
                resultHashSet = resultHashSet.stream().filter(notebooks -> notebooks.getMonitorSize().equals(entry.getValue())).collect(Collectors.toSet());
            } else if (entry.getKey() == 8) {
                resultHashSet = resultHashSet.stream().filter(notebooks -> notebooks.getLabel().equals(entry.getValue())).collect(Collectors.toSet());
            }

        }
        resultList = resultHashSet.stream().toList();
        return resultList;
    }


    /**
     * Метод позволяет пользователю ввести набор фильтров для фильтрации базы ноутбуков
     * @param scanner для использования интерфейса ввода с клавиатуры, Scanner
     * @return набор данных для фильтрации базы ноутбуков, Map&lt;Integer,String&gt;
     */
    private static Map<Integer, String> InputFilters(Scanner scanner) {
        Map<Integer, String> resultMap = new HashMap<>();
        System.out.println("Введите индекс фильтра:" + '\n' +
                "1 - Тип ОЗУ" + '\n' +
                "2 - Размер ОЗУ" + '\n' +
                "3 - Процессор" + '\n' +
                "4 - Количество ядер" + '\n' +
                "5 - Тип жесткого диска" + '\n' +
                "6 - Размер жесткого диска" + '\n' +
                "7 - Размер дисплея" + '\n' +
                "8 - Производитель");
        String strFilter = scanner.nextLine();
        while (strFilter != "") {
            System.out.println("Введите значение фильтра:");
            String filterVolume = scanner.nextLine();
            resultMap.put(Integer.parseInt(strFilter), filterVolume);
            System.out.println("Введите индекс фильтра (для окончания введите пустую строку):");
            strFilter = scanner.nextLine();
        }
        return resultMap;
    }

    /**
     * Метод позволяет считать до 10000 символов из файла с базой ноутбуков,
     * создаёт объект класса Notebooks
     * и сохраняет в набор объекты класса Notebooks
     * @param strFile строка с путем к файлу с базой ноутбуков, String
     * @return набор объектов класса Notebooks, HashSet&lt;Notebooks&gt;
     */
    private static HashSet<Notebooks> ReadBD(String strFile) {
        HashSet<Notebooks> notebooksList = new HashSet<>();
        try {
            FileReader fileReader = new FileReader(strFile);
            char[] charArray = new char[10000];
            try {
                fileReader.read(charArray);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            List<String> strArray = new ArrayList<>();
            String strItem = "";
            for (char item : charArray) {
                if (item != ' ' & item != '\n') {
                    if (item != ',' & item != '\r') {
                        strItem = strItem + item;
                    } else {
                        strArray.add(strItem);
                        strItem = "";
                    }
                }
            }
            String strNotebook = strArray.get(0);

            for (int i = 1; i < strArray.size(); i++) {
                if (i % 8 != 0) {
                    strNotebook = strNotebook + "," + strArray.get(i);
                } else {
                    notebooksList.add(InputNotebook(strNotebook));
                    strNotebook = strArray.get(i);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return notebooksList;
    }

    /**
     * Метод позволяет пользователю записать в файл с базой ноутбуков новую строку параметров
     * @param scanner используетс интерфей ввода строки с клавиатуры, Scanner
     * @param fileBD строка с путем к файлу с базой ноутбуков, String
     */
    public static void NewNotebook(Scanner scanner, String fileBD) {
        Boolean readline = true;
        String line = "";
        System.out.println("Введите данные ноутбука в строку черезапятую (Тип ОЗУ, размер ОЗУ, " +
                " процессор, количество ядер, тип жесткого диска, размер ЖД, размер дисплея, марка ноутбука):");
        while (readline) {
            line = scanner.nextLine();
            if (line.equals("")) {
                readline = false;
            } else {
                Notebooks notebook1 = InputNotebook(line);
                WriteBD(notebook1.toString(), fileBD);
            }
        }
    }

    /**
     * Метод создаёт объект класса Notebooks используя конструктор
     * @param note строка параметров ноутбука со строгой последовательностью, String
     * @return объект класса Notebooks, Notebooks
     */
    public static Notebooks InputNotebook(String note) {
        String[] strArray = note.split("[,.;:]");
        Notebooks notebook = new Notebooks(strArray[0], strArray[1], strArray[2], strArray[3], strArray[4],
                strArray[5], strArray[6], strArray[7]);

        return notebook;
    }

    /**
     * Метод осуществляет запись строки параметров в файл с базой ноутбуков
     * @param strNote строка параметров ноутбука, String
     * @param bdFile строка с путем к файлу с базой ноутбуков, String
     */
    public static void WriteBD(String strNote, String bdFile) {
        try (FileWriter fileWriter = new FileWriter(bdFile, true)) {
            fileWriter.append(strNote + System.getProperty("line.separator"));
            fileWriter.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
