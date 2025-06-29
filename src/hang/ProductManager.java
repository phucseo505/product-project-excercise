package hang;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductManager {
    private ArrayList<Product> sp;

    public ProductManager() {
        sp = new ArrayList<>();
    }
    //commit cua thanh
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap ten san pham: ");
        String name = sc.nextLine();
        System.out.print("Nhap gia sp: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Nhap so luong: ");
        int quantity = Integer.parseInt(sc.nextLine());
        sp.add(new Product(id, name, price, quantity));
    }

    public void hienThiDS() {
        if (sp.isEmpty())
            System.out.println("Danh sach san pham trong");
        for (Product s : sp)
            System.out.println(s.toString());
    }

    public void update() {
        Scanner sc = new Scanner(System.in);
        boolean found = false;
        System.out.print("Nhap ID san pham can cap nhat: ");
        int id = Integer.parseInt(sc.nextLine());
        for (Product s : sp) {
            if (s.getId() == id) {
                found = true;
                System.out.print("Nhap id moi: ");
                id = Integer.parseInt(sc.nextLine());
                s.setId(id);

                System.out.print("Nhap ten sp moi: ");
                String name = sc.nextLine();
                if (!name.equals("")) s.setName(name);

                System.out.print("Nhap gia sp moi: ");
                String priceText = sc.nextLine();
                if (!priceText.equals("")) {
                    double price = Double.parseDouble(priceText);
                    s.setPrice(price);
                }

                System.out.print("Nhap so luong: ");
                int quantity = Integer.parseInt(sc.nextLine());
                s.setQuantity(quantity);
            }
            if (!found) System.out.println("Khong tim thay sam pham co id: " + id);
        }
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        boolean found = false;
        System.out.print("Nhap ID san pham can xoa: ");
        int id = Integer.parseInt(sc.nextLine());
        for (Product s : sp) {
            if (s.getId() == id) {
                found = true;
                System.out.println("Xac nhan xoa sp (Y/N); ");
                String c = sc.nextLine();
                if (c.equals("Y")) {
                    sp.remove(s);
                    System.out.println("xoa thanh cong");
                }
                break;
            }
        }
        if (!found) System.out.println("Khong tim thay sam co id: " + id);
    }

    public void sortUp() {
        SortUp sortUp = new SortUp();
        sp.sort(sortUp);
        hienThiDS();
    }

    public void sortDown() {
        SortDown sortDown = new SortDown();
        sp.sort(sortDown);
        hienThiDS();
    }

    public void writeFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "D:\\LAP_TRINH\\java\\hang\\products.csv"));
            for (Product s : sp) {
                bw.write(s.getId() + "," + s.getName() + "," + s.getPrice()
                        + "," + s.getQuantity() + "\n");
            }
            bw.close();
            System.out.println("Ghi file thanh cong");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        System.out.println("Doc file se xoa het du lieu cu. Ban co muon doc file(Y/N");
        Scanner sc = new Scanner(System.in);
        String confirm = sc.nextLine();
        if (confirm.equals("Y")) {
            BufferedReader br = null;
            try {
                String line;
                br = new BufferedReader(new FileReader(
                        "D:\\LAP_TRINH\\java\\hang\\products.csv"));
                while((line = br.readLine()) != null) {
                    printProduct(parseCscLine(line));
                }
            }
            catch (IOException e) {
                System.err.println("Khong co du lieu");
            }
            finally {
                try {
                    if(br != null) {
                        br.close();
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String> parseCscLine(String csvLine) {
        List<String> result = new ArrayList<>();
        if (csvLine != null) {
            String[] dataSplit = csvLine.split(",");
            boolean flag = false;
            for (Product s : sp) {
                if(s.getId() == Integer.parseInt(dataSplit[0])) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                sp.add(new Product(Integer.parseInt(dataSplit[0]), dataSplit[1], Double.parseDouble(dataSplit[2]), Integer.parseInt(dataSplit[3])));
            }
            result.addAll(Arrays.asList(dataSplit));
        }
        return result;
    }

    public void printProduct(List<String> products) {
        System.out.println("Product{" + "id: " + products.get(0) + " name: " + products.get(1)
        + " price: " + products.get(2) + " quantity: " + products.get(3) + "}");
    }

}
