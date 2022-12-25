import java.io.Serializable;

public class GameObject_Shop implements Serializable {
    private String name;
    private int buyPrice;
   // private int SalePrice;
    private int num;

    //private int salenum;//卖出去的数量
    private String  note="暂无";

    public GameObject_Shop(){}
    public GameObject_Shop(String name, int buyPrice, int num, String note){
        this.setName(name);
        this.setBuyPrice(buyPrice);
        this.setNum(num);
        this.setNote(note);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public void reduceNum(int reducenum) throws ReduceException {
        int newnum=num-reducenum;
        if (newnum>=0)
            return;
        else
        throw new ReduceException(-1);
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return  "商品名='" + name + '\'' +
                ", 进货价格=" + buyPrice +
                ", 数量=" + num +
                ", 备注='" + note ;
    }

//    public int getSalePrice() {
//        return SalePrice;
//    }
//
//    public void setSalePrice(int salePrice) {
//        SalePrice = salePrice;
//    }
}
