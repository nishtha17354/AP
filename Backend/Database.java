import java.util.ArrayList;

public class Database {

    private ArrayList<Warehouse> warehouses;
    private ArrayList<Store> stores;
    private ArrayList<WarehouseAdmin> warehouseAdmins;
    private ArrayList<StoreAdmin> storeAdmins;

    Database(){
        warehouses = new ArrayList<>();
        stores = new ArrayList<>();
        warehouseAdmins = new ArrayList<>();
        storeAdmins = new ArrayList<>();
    }

    public void addToWarehouses(Warehouse w){
        warehouses.add(w);
    }

    public void addToStores(Store s){
        stores.add(s);
    }

    public void addToWarehouseAdmin (WarehouseAdmin WA){
        warehouseAdmins.add(WA);
    }

    public void addToStoreAdmin (StoreAdmin SA){
        storeAdmins.add(SA);
    }

    public ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public ArrayList<WarehouseAdmin> getWarehouseAdmins() {
        return warehouseAdmins;
    }

    public ArrayList<StoreAdmin> getStoreAdmins() {
        return storeAdmins;
    }
}


