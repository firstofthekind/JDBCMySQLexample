public class Product {
    // Поля класса
    public int id;

    public String productname;

    public String description;

    public double cost;

    public int articleid;

    public String articlename;

    public String articlecontent;

    public String articledate;

    // Конструктор
    public Product(int id, String name, double cost, String description) {
        this.id = id;
        this.productname = name;
        this.description = description;
        this.cost = cost;
    }

    public Product(int id, String name, double cost, String description,
                   String articlename, String articlecontent, String articledate) {
        this.id = id;
        this.productname = name;
        this.description = description;
        this.cost = cost;
        this.articlename = articlename;
        this.articlecontent = articlecontent;
        this.articledate = articledate;
    }

    public Product(String name, double cost, String description) {
        this.productname = name;
        this.description = description;
        this.cost = cost;
    }

    // Выводим информацию по продукту
    @Override
    public String toString() {
        return String.format("ID: %s | Товар: %s | Цена: %s | Описание: %s \n" +
                        " Название статьи: %s | Текст статьи: %s | Дата создания статьи: %s\n",
                this.id, this.productname, this.cost, this.description,
                 this.articlename, this.articlecontent, this.articledate);
    }
}