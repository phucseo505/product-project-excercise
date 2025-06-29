package hang;

import java.util.Scanner;

public class Menu {
    ProductManager qlsp = new ProductManager();
    public void menu(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("\n-----Chon chuc nang-----");
            System.out.println("1. them");
            System.out.println("2. xoa");
            System.out.println("3. xua");
            System.out.println("4. hien thi danh sach");
            System.out.println("5. sap xep");
            System.out.println("6. ghi file");
            System.out.println("7. doc file");
            System.out.println("8. thoat");
            System.out.println();
            System.out.print("Lua chon cua ban: ");
            byte choice = Byte.parseByte(sc.nextLine());

            switch (choice) {
                case 1:
                    qlsp.add();
                    break;
                case 2:
                    qlsp.delete();
                    break;
                case 3:
                    qlsp.update();
                    break;
                case 4:
                    qlsp.hienThiDS();
                    break;
                case 5:
                    menuSort();
                    break;
                case 6:
                    qlsp.writeFile();
                    break;
                case 7:
                    qlsp.readFile();
                    break;
                case 8:
                    sc.close();
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
    private void menuSort(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n-----Chon chieu-----");
            System.out.println("1. tang");
            System.out.println("2. giam");
            System.out.println("3. quay lai");
            switch (Byte.parseByte(sc.nextLine())) {
                case 1:
                    qlsp.sortUp();
                    break;
                case 2:
                    qlsp.sortDown();
                    break;
                case 3:
                    sc.close();
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
