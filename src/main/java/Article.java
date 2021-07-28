import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Article {
    // Поля класса
    public int id;

    public int productid;

    public String name;

    public String content;

    public String date;

    public String productname;

    public String productdescription;

    public double productcost;

    // Конструктор
    public Article(int id, int productid, String name, String content) {
        this.id = id;
        this.productid = productid;
        this.name = name;
        this.content = content;
        this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public Article(int productid, String name, String content) {
        this.productid = productid;
        this.name = name;
        this.content = content;
        this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public Article(int id, int productid, String name, String content, String date) {
        this.id = id;
        this.productid = productid;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public Article(int id, int productid, String name, String content, String date, String productname, String productdescription, double productcost) {
        this.id = id;
        this.productid = productid;
        this.name = name;
        this.content = content;
        this.date = date;
        this.productname = productname;
        this.productdescription = productdescription;
        this.productcost = productcost;
    }

    // Выводим информацию по продукту
    @Override
    public String toString() {
        return String.format("ID статьи: %s | ID продукта: %s | Название статьи: %s | Статья: %s | Дата создания статьи: %s \n" +
                        "Название продукта: %s | Описание продукта: %s | Цена внедрения: %s \n",
                this.id, this.productid, this.name, this.content, this.date, this.productname, this.productdescription, this.productcost);
    }
}