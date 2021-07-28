import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Для работы со статьями напишите \"Статья\". \n" +
                "Для работы с продуктами напишите \"Продукт\".");
        String statement = start();
        int command = comms(statement);
        int sfBy = sfBy(statement, command);
        try {
            // Создаем экземпляр по работе с БД
            DbHandler dbHandler = DbHandler.getInstance();
            assert statement != null;
            if (statement.toLowerCase(Locale.ROOT).equals("статья")) {
                List<Article> articles = dbHandler.getArticles(command, sfBy);
                if (command != 5 & command != 6) {
                    for (Article article : articles) {
                        System.out.println(article.toString());
                    }
                }
            }
            if (statement.toLowerCase(Locale.ROOT).equals("продукт")) {
                List<Product> products = dbHandler.getProducts(command, sfBy);
                if (command != 5 & command != 6) {
                    for (Product product : products) {
                        System.out.println(product.toString());
                    }
                }
            }
            // Добавляем запись
            // dbHandler.addProduct(new Product("hello", 122512455, "aasdad"));
            // dbHandler.addArticle(new Article(4, "hell12o", "myartifasafcle"));
            // Получаем все записи и выводим их на консоль
            /* List<Product> products = dbHandler.getAllProducts();
            for (Product product : products) {
                System.out.println(product.toString());
            }
            List<Article> articles = dbHandler.getArticles();
            for (Article article : articles) {
                System.out.println(article.toString());
            }
            // Удаление записи с id = 8
            //dbHandler.deleteProduct(8); */


        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    public static String start() {
        String func = scanner.nextLine();
        if (!func.toLowerCase(Locale.ROOT).equals("статья") & !func.toLowerCase(Locale.ROOT).equals("продукт")) {
            System.out.println("Поддерживаются только команды \"Статья\" и \"Продукт\"");
            func = scanner.nextLine();
        } else {
            return func;
        }
        return null;
    }

    public static int comms(String a) {
        int stateNum = 0;
        try {
            if (a.toLowerCase(Locale.ROOT).equals("статья")) {
                System.out.println("Доступные команды для статей:\n" +
                        "1 - Вывод статьи по ID\n" +
                        "2 - Вывод всего списка статей\n" +
                        "3 - Вывод отсортированного списка статей\n" +
                        "4 - Вывод отфильтрованного списка статей\n" +
                        "5 - Удалить статью по ID\n" +
                        "6 - Модифицировать статью\n" +
                        "7 - Вывод статьи по productID\n" +
                        "0 - Назад");
                stateNum = scanner.nextInt();
                if (stateNum >= 0 & stateNum <= 7) {
                    if (stateNum == 0) {
                        main(null);
                    }
                }
            }
            if (a.toLowerCase(Locale.ROOT).equals("продукт")) {
                System.out.println("Доступные команды для продуктов:\n" +
                        "1 - Вывод одного продукта по ID\n" +
                        "2 - Вывод всего списка продуктов\n" +
                        "3 - Вывод отсортированного списка продуктов\n" +
                        "4 - Вывод отфильтрованного списка продуктов\n" +
                        "5 - Удалить продукт по ID\n" +
                        "6 - Модифицировать продукт\n" +
                        "0 - Назад");
                stateNum = scanner.nextInt();
                if (stateNum >= 0 & stateNum <= 6) {
                    if (stateNum == 0) {
                        main(null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка. Начните заново.");
            comms(a);
        }
        return stateNum;
    }

    public static int sfBy(String a, int b) {
        int sortNum = 0;
        try {
            if (a.toLowerCase(Locale.ROOT).equals("cтатья")) {
                if (b == 3) {
                    System.out.println("Сортировать статьи по: \n" +
                            "1 - ID\n" +
                            "2 - productID\n" +
                            "3 - Имени статьи\n" +
                            "4 - Содержанию\n" +
                            "5 - Дате создания");
                    sortNum = scanner.nextInt();
                }
                if (b == 4) {
                    System.out.println("Фильтровать статьи по: \n" +
                            "1 - ID\n" +
                            "2 - productID\n" +
                            "3 - Имени статьи\n" +
                            "4 - Содержанию\n" +
                            "5 - Дате создания");
                    sortNum = scanner.nextInt();
                }
            }
            if (a.toLowerCase(Locale.ROOT).equals("продукт")) {
                if (b == 3) {
                    System.out.println("Сортировать продукты по: \n" +
                            "1 - ID\n" +
                            "2 - Имени продукта\n" +
                            "3 - Цене\n" +
                            "4 - Описанию");
                    sortNum = scanner.nextInt();
                }
                if (b == 4) {
                    System.out.println("Фильтровать продукты по: \n" +
                            "1 - ID\n" +
                            "3 - Имени продукта\n" +
                            "4 - Цене\n" +
                            "5 - Описанию");
                    sortNum = scanner.nextInt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка. Начните заново.");
            sfBy(a, b);
        }
        return sortNum;
    }
}