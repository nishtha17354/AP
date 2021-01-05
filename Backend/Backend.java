import java.util.*;
public class Backend {


    public static int minimumDist(int numberOfWarehouse, int dist[], Boolean[] visited) {
        int minimum = Integer.MAX_VALUE;
        int min_idx = -1;

        for (int x = 0; x < numberOfWarehouse; x++) {
            if (visited[x] == false && dist[x] <= minimum) {
                minimum = dist[x];
                min_idx = x;
            }
        }
        return min_idx;
    }


    public static ArrayList<Warehouse> dijkstra(ArrayList<ArrayList<Integer>> matrix, int source, Warehouse sourceW) {

        int numberOfWarehouse = matrix.size();
        int dist[] = new int[numberOfWarehouse];
        Boolean visited[] = new Boolean[numberOfWarehouse];

        for (int i = 0; i < numberOfWarehouse; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[source] = 0;

        for (int c = 0; c < numberOfWarehouse; c++) {
            int u = minimumDist(numberOfWarehouse, dist, visited);
            visited[u] = true;
            for (int v = 0; v < numberOfWarehouse; v++) {
                if (!visited[v] && matrix.get(u).get(v) != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + matrix.get(u).get(v) < dist[v])
                    dist[v] = dist[u] + matrix.get(u).get(v);
            }
        }

        System.out.println("distances: " + dist.toString());

        HashMap<Integer,Warehouse> warehousesToBeAlerted = new HashMap<>();
        for (int i = 0; i<dist.length; i++){
            int d = dist[i];
            System.out.println(d);
            Warehouse w = sourceW.getWarehouseList().get(i);
            warehousesToBeAlerted.put(d,w);
        }

        warehousesToBeAlerted.remove(0);
        TreeMap<Integer, Warehouse> map = new TreeMap<Integer, Warehouse>(warehousesToBeAlerted);
        //System.out.println("tree map");
        //printDist(map);

        ArrayList<Warehouse> warehouses = new ArrayList<>();
        Collection distSet = map.values();
        Iterator iterator = distSet.iterator();
        while (iterator.hasNext()) {
            Warehouse w = (Warehouse) iterator.next();
            warehouses.add(w);
        }

        for (int i = 0; i < warehouses.size(); i++){
            System.out.println("warehouses optimized " + warehouses.get(i).getName());
        }

        return warehouses;

    }

    public static void printDist(TreeMap<Integer, Warehouse> dist) {
        Set distSet = dist.entrySet();
        Iterator iterator = distSet.iterator();
        while (iterator.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator.next();
            System.out.println(me2.getKey());
        }
    }

    public static void main(String[] args) {

        // TODO Auto-generated method stub

        Warehouse testW1 = new Warehouse("NFC", 10);
        Warehouse testW2 = new Warehouse("Rohini", 20);
        Warehouse testW3 = new Warehouse("Rohini", 20);

        System.out.println("Now printing dist matrix");
        System.out.println(testW1.getDistanceMatrix());

        Backend t = new Backend();
        ArrayList<Warehouse> dist = t.dijkstra(testW1.getDistanceMatrix(), 1, testW2);

    }
}