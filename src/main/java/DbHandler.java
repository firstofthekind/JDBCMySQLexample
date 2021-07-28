import org.sqlite.JDBC;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class DbHandler {

    final static Scanner scanner = new Scanner(System.in);

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:D:\\IT\\Test tasks\\disgroup\\disgroupbd.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private final Connection connection;

    private DbHandler() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public List<Product> getProducts(int a, int b) {
        String stmt = "SELECT products.ID, productname, Cost, Description, name, content, date " +
                "FROM Products left join Articles on productID = products.ID";
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "ID");
        map.put(2, "productname");
        map.put(3, "cost");
        map.put(4, "description");
        if (a == 1) {
            System.out.println("Введите ID продукта");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("ID должно быть числовым значением");
                getProducts(a, b);
            }
            stmt = stmt + " WHERE Products.ID = " + id;
        }
        if (a == 3) {
            stmt = stmt + " ORDER BY " + map.get(b);
        }
        if (a == 4) {
            System.out.println("Доступные команды фильтрации\n" +
                    "= - равно\n" +
                    "!= - не равно\n" +
                    "> - больше\n" +
                    ">= - больше или равно\n" +
                    "< - меньше\n" +
                    "<= - меньше или равно\n");
            if (b < 3 | b == 5) {
                System.out.println("Введите команду и значение в формате \">= 1\".");
            } else {
                System.out.println("Введите команду и значение в формате \">= 'текст'\".");
            }
            String comparsion = scanner.nextLine();
            stmt = stmt + " WHERE " + map.get(b) + " " + comparsion;
        }
        if (a == 5) {
            System.out.println("Введите ID продукта");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("ID должно быть числовым значением");
                getProducts(a, b);
            }
            deleteProduct(id);
        }
        if (a == 6) {
            System.out.println("Введите ID продукта");
            int id = 0;
            try {
                id = scanner.nextInt();
                updateProduct(id);
            } catch (Exception e) {
                System.out.println("ID должно быть числовым значением");
                updateProduct(id);
            }
        }
        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Product> products = new ArrayList<Product>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery(stmt);
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("ID"),
                        resultSet.getString("productname"),
                        resultSet.getDouble("Cost"),
                        resultSet.getString("Description"),
                        resultSet.getString("name"),
                        resultSet.getString("content"),
                        resultSet.getString("date")));
            }
            // Возвращаем наш список
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }


    public List<Article> getArticles(int a, int b) {
        String stmt = "SELECT Articles.ID, productID, Articles.Name, content, date, productname, " +
                "Products.description, Products.cost FROM Articles LEFT JOIN Products ON Products.ID = productID ";
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "ID");
        map.put(2, "productID");
        map.put(3, "Name");
        map.put(4, "content");
        map.put(5, "date");
        if (a == 1) {
            System.out.println("Введите ID статьи");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("ID должно быть числовым значением");
                getArticles(a, b);
            }
            stmt = stmt + " WHERE Articles.ID = " + id;
        }
        if (a == 3) {
            stmt = stmt + " ORDER BY " + map.get(b);
        }
        if (a == 4) {
            System.out.println("Доступные команды фильтрации\n" +
                    "= - равно\n" +
                    "!= - не равно\n" +
                    "> - больше\n" +
                    ">= - больше или равно\n" +
                    "< - меньше\n" +
                    "<= - меньше или равно\n");
            if (b < 3 | b == 5) {
                System.out.println("Введите команду и значение в формате \">= 1\".");
            } else {
                System.out.println("Введите команду и значение в формате \">= 'текст'\".");
            }
            String comparsion = scanner.nextLine();
            stmt = stmt + " WHERE " + map.get(b) + " " + comparsion;
        }
        if (a == 5) {
            System.out.println("Введите ID статьи");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("ID должно быть числовым значением");
                getArticles(a, b);
            }
            deleteArticle(id);
        }
        if (a == 6) {
            System.out.println("Введите ID статьи");
            int id = 0;
            try {
                id = scanner.nextInt();
                updateArticle(id);
            } catch (Exception e) {
                System.out.println("ID должно быть числовым значением");
                updateArticle(id);
            }
        }
        if (a == 7) {
            System.out.println("Введите ID продукта");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("ID должно быть числовым значением");
                getArticles(a, b);
            }
            stmt = stmt + " WHERE productID = " + id;
        }



        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши статьи, полученные из БД
            List<Article> articles = new ArrayList<Article>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery(stmt);
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("ID"),
                        resultSet.getInt("productID"),
                        resultSet.getString("Name"),
                        resultSet.getString("content"),
                        resultSet.getString("date"),
                        resultSet.getString("productname"),
                        resultSet.getString("description"),
                        resultSet.getDouble("cost")));
            }
            // Возвращаем наш список
            return articles;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление продукта в БД
    public void addProduct(Product product) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Products('productName', 'Description', 'Cost') " +
                        "VALUES(?, ?, ?)")) {
            statement.setObject(1, product.productname);
            statement.setObject(2, product.description);
            statement.setObject(3, product.cost);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление статьи в БД
    public void addArticle(Article article) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Articles('productID', 'Name', 'content', 'date') " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, article.productid);
            statement.setObject(2, article.name);
            statement.setObject(3, article.content);
            statement.setObject(4, article.date);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление продукта по id
    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Products WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление статьи по id
    public void deleteArticle(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Articles WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateArticle(int id) {
        String newID = String.valueOf(id);
        System.out.println("Введите новый ID продукта");
        int newproductID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите новое имя статьи");
        String newArticleName = scanner.nextLine();
        System.out.println("Введите новый текст статьи");
        String newContent = scanner.nextLine();

        String newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE Articles SET productID = ? , Name = ? , Content = ?, date = ? WHERE id = ?")) {

            // set the corresponding param
            statement.setObject(1, newproductID);
            statement.setObject(2, newArticleName);
            statement.setObject(3, newContent);
            statement.setObject(4, newDate);
            statement.setObject(5, id);
            // Выполняем запрос
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка. Введите данные заново.");
            updateArticle(id);
        }
    }

    public void updateProduct(int id) {
        System.out.println("Введите новое имя продукта");
        String newProductName = scanner.nextLine();
        System.out.println("Введите новое описание продукта");
        String newDescription = scanner.nextLine();
        System.out.println("Введите новое описание продукта");
        Double newCost = scanner.nextDouble();


        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE Products SET productname = ? , description = ? , Cost = ?WHERE id = ?")) {

            // set the corresponding param
            statement.setObject(1, newProductName);
            statement.setObject(2, newDescription);
            statement.setObject(3, newCost);
            statement.setObject(4, id);
            // Выполняем запрос
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка. Введите данные заново.");
            updateProduct(id);
        }
    }
}