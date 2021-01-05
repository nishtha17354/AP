import java.util.ArrayList;

public class SuperUser {

    private String loginID;
    private String password;
    private Database database = new Database();

    public SuperUser(String loginID, String password) {
        this.loginID = loginID;
        this.password = password;
    }

    public String getLoginID() {
        return loginID;
    }

    public String getPassword() {
        return password;
    }

    public Database getDatabase() {
        return database;
    }

    public Warehouse getWarehouseData(String warehouseName){

        ArrayList<Warehouse> warehouses = this.database.getWarehouses();
        Warehouse w = null;

        for (int i = 0; i < warehouses.size(); i++){
            if (warehouses.get(i).getName().equals(warehouseName)){
                w = warehouses.get(i);
                return w;
            }
        }
        return w;
    }

    public Store getStoreData(String storeName){

        ArrayList<Store> stores = this.database.getStores();
        Store s = null;

        for (int i = 0; i < stores.size(); i++){
            if (stores.get(i).getName().equals(storeName)){
                s = stores.get(i);
                return s;
            }
        }
        return s;
    }

    public void createWarehouse(String n, int carryingCost){
        Warehouse w = new Warehouse(n,carryingCost);
        this.database.addToWarehouses(w);
    }

    public void createStore(String n, int carryingCost, Warehouse linkedWarehouses){
        Warehouse w = new Warehouse(n,carryingCost);
        this.database.addToWarehouses(w);
    }

    public void createWarehouseAdmin(String loginID, String password, Warehouse warehouse){
        WarehouseAdmin w = new WarehouseAdmin(loginID, password, warehouse);
        this.database.addToWarehouseAdmin(w);
    }

    public void createStoreAdmin(String loginID, String password, Store store){
        StoreAdmin s = new StoreAdmin(loginID, password, store);
        this.database.addToStoreAdmin(s);
    }
}
