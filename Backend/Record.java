public class Record {

    String itemName;
    int itemCode;
    int quantReq ;
    Store receiver;

    public Record(String itemName, int itemCode, int quantReq, Store receiver) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.quantReq = quantReq;
        this.receiver = receiver;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCode() {
        return itemCode;
    }

    public int getQuantReq() {
        return quantReq;
    }

    public void setQuantReq(int quantReq) {
        this.quantReq = quantReq;
    }

    public Superstore getReceiver() {
        return receiver;
    }
}
