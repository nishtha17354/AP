import java.util.ArrayList;

class Pair {
    private int quantity;
    private String itemName;
    private int price;
    private int amount;

    public Pair(String itemName, int quantity, int price) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.price = price;
        this.amount = price * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.amount = quantity*price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

public class Cart {

    private Store s;
    private ArrayList<Pair> itemsAdded = new ArrayList<Pair>();
    private int totalAmount = 0;

    public Cart(Store s){
        s = s;
    }

    public ArrayList<Pair> getItemsAdded() {
        return itemsAdded;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public Store getS() {
        return s;
    }

    public void setS(Store s) {
        this.s = s;
    }

    public void modifyCart(String itemName, int quantity){
        boolean flag = false;
        for (int i = 0; i < itemsAdded.size(); i++){
            Pair p = itemsAdded.get(i);
            if (p.getItemName().equals("itemName")){
                if (quantity == 0){
                    this.itemsAdded.remove(p);
                }
                else {
                    p.setQuantity(quantity);
                }
                flag = true;
                break;
            }
        }

        if (flag == false){
            Item i = s.searchItem(itemName);
            Pair newPair = new Pair(itemName,quantity,i.getFixedCost());
        }
    }
}
