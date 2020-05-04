package Entity;

public class Goods {
    private String GoodId;
    private String GoodName;
    private int Nume;
    private float Price;
    private String Category;
    public Goods() {

    }
    public Goods(String goodId,String goodName) {
        GoodId=goodId;
        GoodName=goodName;
    }
    public Goods(String goodId,String goodName,int number,float price,String category){
        GoodId=goodId;
        GoodName=goodName;
        Nume=number;
        Price=price;
        Category=category;
    }

    public String getGoodId() {
        return GoodId;
    }
    public String getGoodName() {
        return GoodName;
    }
    public  String getCategory(){
        return Category;
    }
    public float getPrice(){
        return Price;
    }
    public  int getNumber(){
        return Nume;
    }

    public void setGoodId(String goodId) {
        GoodId = goodId;
    }

    public void setGoodName(String goodName) {
        GoodName = goodName;
    }

    public void setNumber(int number) {
        Nume = number;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public void setCategory(String category) {
        Category = category;
    }
    public String toString() {
        return "Goods{" +
                "GoodId='" + GoodId + '\'' +
                ", GoodName='" + GoodName + '\'' +
                ", Number=" + Nume +
                ", Price=" + Price +
                ", Category='" + Category + '\'' +
                '}';
    }
}
