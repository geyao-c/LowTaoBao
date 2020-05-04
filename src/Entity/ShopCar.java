package Entity;

import java.util.Objects;

public class ShopCar {
    private String UserId;
    private String GoodId;
    private int Nume;
    private float Price;

    public ShopCar() {

    }
    public ShopCar(String userId,String goodId,int nume,float price){
        UserId=userId;
        GoodId=goodId;
        Nume=nume;
        Price=price;
    }

    public String getUserId() {
        return UserId;
    }

    public String getGoodId() {
        return GoodId;
    }

    public int getNume() {
        return Nume;
    }

    public float getPrice() {
        return Price;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setGoodId(String goodId) {
        GoodId = goodId;
    }

    public void setNume(int nume) {
        Nume = nume;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public boolean Equal(ShopCar Sc){
        if(this.GoodId.equals(Sc.GoodId)&&this.UserId.equals(Sc.UserId)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "ShopCar{" +
                "UserId='" + UserId + '\'' +
                ", GoodId='" + GoodId + '\'' +
                ", Nume=" + Nume +
                ", Price=" + Price +
                '}';
    }
}
