import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface IPromotion{
    void applyDiscount(double percentage);
}

class CheckNumber {
    public static boolean checkNumber(String number){
        try{
            double res = Double.parseDouble(number);
            return true;
        } catch(RuntimeException e){
            return false;
        }
    }
}

abstract class Drink implements IPromotion{
    protected int id;
    protected String name;
    protected double price;
    protected final double priceOrigin;
    protected List<Double> listDis = new ArrayList<>();
    public Drink(int id, String name, double price){
        this.priceOrigin = price;
        this.id = id;
        this.name= name;
        this.price = price;
    }
    public int getID(){
        return this.id;
    }
    public abstract void prepare();
    public abstract void applyDiscount(double percentage);
    public void displayProduct(){
        System.out.println("=========Danh sách sản phẩm ========");
        System.out.println("Mã sản phẩm: " + this.id);
        System.out.println("Tên sản phẩm: " + this.name);
        System.out.printf("Giá sản phẩm: %.2f\n", this.price);
    }
    public void discountUsed(double percentage){
        this.listDis.add(percentage);
    }
    public void reportProduct(){
        System.out.println("Mã sản phẩm: " + this.id);
        System.out.println("Tên sản phẩm: " + this.name);
        System.out.printf("Giá gốc sản phẩm: %.2f\n", this.priceOrigin);
        System.out.printf("Giá đã giảm cuối cùng: %.2f\n", this.price);
        for(var x : this.listDis){
            System.out.println("Mã phần trăm đã giảm: " + x + "%");
        }
    }
}

class Coffee extends Drink{
    public Coffee(int id, String name, double price){
        super(id, name, price);
    }
    @Override
    public void prepare(){
        System.out.println("Pha bang ca phe");
    }
    @Override
    public void applyDiscount(double percentage){
        this.price = this.price * (1 - percentage/100);
    }
}

class FruitTea extends Drink{
    public FruitTea(int id, String name, double price){
        super(id, name, price);
    }
    @Override
    public void prepare(){
        System.out.println("Pha bang tra hoa qua ");
    }
    @Override
    public void applyDiscount(double percentage){
        this.price = this.price * (1 - percentage/100);
    }
}

public class CoffeeShopPro{
    private static Scanner sc = new Scanner(System.in);
    private static Drink[] listDrink = new Drink[5001];
    private static int i = 0;
    public static void main(String[] args){
        while(true){
            System.out.println("=========Đặt Đồ Uống ===========");
            System.out.println("1. Thêm món vào Menu.");
            System.out.println("2. Hiển thị Menu");
            System.out.println("3. Áp dụng mã giảm giá");
            System.out.println("4. Xóa món");
            System.out.println("5. Thống kê");
            System.out.println("6. Thoát. ");
            System.out.print("Nhập lựa chọn của bạn: ");
            String option = sc.nextLine();
            switch(option){
                case "1":
                    addMenu();
                    break;
                case "2":
                    displayPro();
                    break;
                case "3":
                    applyDiscount();
                    break;
                case "4":
                    deleteProduct();
                    break;
                case "5":
                    report();
                    break;
                case "6":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Định dạng không hợp lệ . ");
            }
        }
    }
    public static void addMenu(){
        while(true){
            boolean ok = true;
            String input;
            System.out.println("1. Thêm Coffee");
            System.out.println("2. Thêm FruitTea");
            System.out.println("3. Thoát.");
            System.out.print("Nhập lựa chọn: ");
            input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    addCoffee();
                    break;
                case "2":
                    addFruitTea();
                    break;
                case "3":
                    ok = false;
                default:
                    System.out.println("Sai định dạng! Vui lòng nhập lại (chỉ nhập 1 hoặc 2 hoặc 3).");
            }
            if(!ok) break;
        }
    }
    public static void addCoffee(){
        while(true){
            String id;
            while (true) {
                System.out.print("Nhập ID: ");
                id = sc.nextLine().trim();
                if (CheckNumber.checkNumber(id)) {
                    break;
                } else {
                    System.out.println("Sai định dạng! ID phải là số.");
                }
            }
            String name;
            while (true) {
                System.out.print("Nhập tên món: ");
                name = sc.nextLine().trim();
                if (!name.isEmpty()) {
                    break;
                } else {
                    System.out.println("Sai định dạng! Tên không được để trống.");
                }
            }
            String price;
            while (true) {
                System.out.print("Nhập giá: ");
                price = sc.nextLine().trim();
                if (CheckNumber.checkNumber(price)) {
                    break;
                } else {
                    System.out.println("Sai định dạng! Giá phải là số.");
                }
            }
            Drink prod = new Coffee(Integer.parseInt(id), name, Double.parseDouble(price));
            listDrink[i++] = prod;
            System.out.println("Đã chọn thêm Coffee");
            String exit;
            while(true){
                System.out.print("Continue để tiếp tục hoặc exit để rời đi: ");
                exit = sc.nextLine();
                if(exit.equals("continue") || exit.equals("exit")) break;
                System.out.println("Sai định dạng. ");
            }
            if(exit.equals("exit")) break;
        }
    }
    public static void addFruitTea(){
        while(true){
            String id;
            while (true) {
                System.out.print("Nhập ID: ");
                id = sc.nextLine().trim();
                if (CheckNumber.checkNumber(id)) {
                    break;
                } else {
                    System.out.println("Sai định dạng! ID phải là số.");
                }
            }

            String name;
            while (true) {
                System.out.print("Nhập tên món: ");
                name = sc.nextLine().trim();
                if (!name.isEmpty()) {
                    break;
                } else {
                    System.out.println("Sai định dạng! Tên không được để trống.");
                }
            }

            String price;
            while (true) {
                System.out.print("Nhập giá: ");
                price = sc.nextLine().trim();
                if (CheckNumber.checkNumber(price)) {
                    break;
                } else {
                    System.out.println("Sai định dạng! Giá phải là số.");
                }
            }
            Drink prod = new FruitTea(Integer.parseInt(id), name, Double.parseDouble(price));
            listDrink[i++] = prod;
            System.out.println("Đã chọn thêm FruitTea");
            String exit;
            while(true){
                System.out.print("Continue để tiếp tục hoặc exit để rời đi: ");
                exit = sc.nextLine();
                if(exit.equals("continue") || exit.equals("exit")) break;
                System.out.println("Sai định dạng. ");
            }
            if(exit.equals("exit")) break;
        }
    }
    public static void displayPro(){
        for(int j = 0; j< i; j++){
            listDrink[j].displayProduct();
        }
    }
    public static void applyDiscount(){
        while(true){
            System.out.print("Nhập mã giảm gía: ");
            String ma = sc.nextLine();
            if(CheckNumber.checkNumber(ma)) break;
            System.out.println("Định dạng không chính xác. ");
            for(int j=0; j<i; j++){
                listDrink[j].applyDiscount(Double.parseDouble(ma));
                listDrink[j].discountUsed(Double.parseDouble(ma));
            }
            System.out.println("Đã nhập mã thành công!");
            String exit;
            while(true){
                System.out.print("Continue để tiếp tục hoặc exit để rời đi: ");
                exit = sc.nextLine();
                if(exit.equals("continue") || exit.equals("exit")) break;
                System.out.println("Sai định dạng. ");
            }
            if(exit.equals("exit")) break;
        }
    }
    public static void deleteProduct(){
        while(true){
            System.out.print("Nhập mã sản phẩm: ");
            String ma = sc.nextLine();
            if(CheckNumber.checkNumber(ma)){
                boolean ok = true;
                for(int j = 0; j<i; j++){
                    if(listDrink[j].getID() == Integer.parseInt(ma)){
                        int h = j;
                        for(int k = h; k < i - 1; k++){
                            listDrink[k] = listDrink[k + 1];
                        }
                        System.out.println("Xóa sản phẩm thành công. ");
                        i--;
                        ok = false;
                        break;
                    }
                }
                if(ok) System.out.println("Mã sản phẩm không tồn tại. ");
            }
            else System.out.println("Sai định dạng. ");
            String exit;
            while(true){
                System.out.print("Continue để tiếp tục hoặc exit để rời đi: ");
                exit = sc.nextLine();
                if(exit.equals("continue") || exit.equals("exit")) break;
                System.out.println("Sai định dạng. ");
            }
            if(exit.equals("exit")) break;
        }
    }
    public static void report(){
        for(int j=0; j<i; j++){
            listDrink[j].reportProduct();
        }
    }
}
