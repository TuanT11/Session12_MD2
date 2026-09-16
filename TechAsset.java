import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Scanner;


class CheckNuber{
    public static boolean checkNumber(String number){
        try{
            double res =  Double.parseDouble(number);
            return true;
        } catch(RuntimeException e){
            return false;
        }
    }
}
abstract class Asset{
    protected String assetCode;
    protected String name;
    protected Double purchasePrice;
    public Asset(String assetCode, String name, Double purchasePrice){
        this.assetCode = assetCode;
        this.name = name;
        this.purchasePrice = purchasePrice;
    }
    abstract public void showValue();
    abstract public void getMarketValue();
    public Double getPurchasePrice(){
        return this.purchasePrice;
    }
    public String getAssetCode(){
        return this.assetCode;
    }
    abstract public void updatePrice();
}

class Computer extends Asset{
    private float ram;
    private float cpu;
    private final double originPrice;
    private final LocalDate initDate = LocalDate.now();
    public Computer(String assetCode, String name, double purchasePrice, float ram, float cpu){
        super(assetCode, name, purchasePrice);
        this.originPrice = purchasePrice;
        this.ram = ram;
        this.cpu = cpu;
    }
    @Override
    public void showValue(){
        LocalDate current = LocalDate.now();
        long date = ChronoUnit.DAYS.between(initDate, current);
        this.purchasePrice = (double)this.purchasePrice - Math.ceil((double)date/365) * 20/100 * this.purchasePrice;
        this.purchasePrice = Math.max(this.purchasePrice, 0d);
        System.out.println(this.purchasePrice);
    }
    @Override
    public void getMarketValue(){
        System.out.println("=============================");
        System.out.println("Tên: " + this.name);
        System.out.println("RAM: " + this.ram + "GB");
        System.out.println("CPU: " + this.cpu);
        showValue();
        System.out.println("Mã : " + this.assetCode);

    }
    public void updatePrice(){
        this.purchasePrice = this.originPrice;
    }
}

class NetworkDevice extends Asset{
    private int numberOfPorts;
    private final LocalDate initDate = LocalDate.now();
    private final double originPrice;
    public NetworkDevice(String assetCode, String name, double purchasePrice, int numberOfPorts){
        this.originPrice = purchasePrice;
        super(assetCode, name, purchasePrice);
        this.numberOfPorts = numberOfPorts;
    }
    @Override
    public void showValue(){
        LocalDate current = LocalDate.now();
        long date = ChronoUnit.DAYS.between(initDate, current);
        this.purchasePrice = (double)this.purchasePrice - Math.ceil((double)date/365) * 10/100 * this.purchasePrice;
        this.purchasePrice = Math.max(this.purchasePrice, 0d);
        System.out.println(this.purchasePrice);
    }
    @Override
    public void getMarketValue(){
        System.out.println("======================");
        System.out.println("Tên: " + this.name);
        System.out.println("Số cổng: " + numberOfPorts);
        showValue();
        System.out.println("Mã: " + this.assetCode);
    }
    public void updatePrice(){
        this.purchasePrice = this.originPrice;
    }
}

public class TechAsset{
    private static Scanner sc = new Scanner(System.in);
    private static Asset[] listProduct = new Asset[5001];
    private static int i = 0;
    public static void main(String[] arg){
        while(true){
            boolean ok = true;
            System.out.println("===Hệ thống quản lý sản phẩm======");
            System.out.println("1. Nhập sản phẩm. ");
            System.out.println("2. Xuất báo cáo. ");
            System.out.println("3. Tìm kiếm. ");
            System.out.println("4. Sửa giá mua. ");
            System.out.println("5. Thoát. ");
            System.out.print("Nhập lựa chọn của bạn: ");
            String option = sc.nextLine();
            switch(option){
                case "1":
                    addProduct();
                    break;
                case "2":
                    report();
                    break;
                case "3":
                    while(true){
                        boolean cont = true;
                        System.out.println("====TÌM KIẾM=====");
                        System.out.println("1. Tìm theo mã tài sản.");
                        System.out.println("2. Tìm theo giá mua (lớn hơn mức nhập).");
                        System.out.println("3. Thoát.");
                        System.out.print("Nhập lựa chọn của bạn: ");
                        String opt3 = sc.nextLine();
                        switch(opt3){
                            case "1":
                                String assetCode, th1;
                                while(true){
                                    System.out.print("Nhập mã tài sản cần tìm: ");
                                    assetCode = sc.nextLine();
                                    if(assetCode.isEmpty()) System.out.println("Nhập sai định dạng.");
                                    else break;
                                }
                                search(assetCode);
                                while(true){
                                    System.out.print("Nhập continue để tiếp tục hoặc exit để rời đi. ");
                                    th1 = sc.nextLine();
                                    if(th1.equals("continue") || th1.equals("exit")) break;
                                }
                                if(th1.equals("exit")) break;
                                break;
                            case "2":
                                String purchasePrice, th2;
                                while(true){
                                    System.out.print("Nhập mức giá cần tìm: ");
                                    purchasePrice = sc.nextLine();
                                    if(!CheckNumber.checkNumber(purchasePrice)) System.out.println("Nhập sai định dạng.");
                                    else break;
                                }
                                search(Double.parseDouble(purchasePrice));
                                while(true){
                                    System.out.print("Nhập continue để tiếp tục hoặc exit để rời đi. ");
                                    th2 = sc.nextLine();
                                    if(th2.equals("continue") || th2.equals("exit")) break;
                                }
                                if(th2.equals("exit")) break;
                                break;
                            case "3":
                                cont = false;
                                break;
                            default:
                                System.out.println("Sai định dạng. ");
                        }
                        if(cont) break;
                    }
                    break;
                case "4":
                    updatePrice();
                    break;
                case "5":
                    ok = false;
                    break;
                default:
                    System.out.println("Định dạng không hợp lệ. ");
            }
            if(!ok) break;
        }
    }
    public static void addProduct(){
        while(true){
            boolean ok = true;
            System.out.println("===Nhập sản phẩm=====");
            System.out.println("1. Nhập thêm máy tính. ");
            System.out.println("2. Nhập thêm thiết bị mạng. ");
            System.out.println("3. Thoát. ");
            System.out.print("Nhập lựa chọn bạn: ");
            String option = sc.nextLine();
            switch(option){
                case "1":
                    while(true){
                        String assetCode, name, purchasePrice, ram, cpu;

                        while(true){
                            System.out.print("Nhập mã tài sản: ");
                            assetCode = sc.nextLine();
                            if(assetCode.isEmpty()) System.out.println("Nhập sai định dạng.");
                            else break;
                        }

                        while(true){
                            System.out.print("Nhập tên: ");
                            name = sc.nextLine();
                            if(name.isEmpty()) System.out.println("Nhập sai định dạng.");
                            else break;
                        }

                        while(true){
                            System.out.print("Nhập giá mua: ");
                            purchasePrice = sc.nextLine();
                            if(!CheckNumber.checkNumber(purchasePrice)) System.out.println("Nhập sai định dạng.");
                            else break;
                        }

                        while(true){
                            System.out.print("Nhập dung lượng RAM: ");
                            ram = sc.nextLine();
                            if(!CheckNumber.checkNumber(ram)) System.out.println("Nhập sai định dạng.");
                            else break;
                        }

                        while(true){
                            System.out.print("Nhập thông tin CPU: ");
                            cpu = sc.nextLine();
                            if(!CheckNumber.checkNumber(cpu)) System.out.println("Nhập sai định dạng.");
                            else break;
                        }
                        Asset prod = new Computer(assetCode, name,Double.parseDouble(purchasePrice), Float.parseFloat(ram), Float.parseFloat(cpu));
                        listProduct[i++] = prod;
                        String cont;
                        while(true){
                            System.out.print("Nhập continue để tiếp tục hoặc exit để rời đi. ");
                            cont = sc.nextLine();
                            if(cont.equals("continue") || cont.equals("exit")) break;
                        }
                        if(cont.equals("exit")) break;
                    }
                    break;
                case "2":
                    while(true){
                        String assetCode, name, purchasePrice, numberOfPorts;
                        while(true){
                            System.out.print("Nhập mã tài sản: ");
                            assetCode = sc.nextLine();
                            if(assetCode.isEmpty()) System.out.println("Nhập sai định dạng.");
                            else break;
                        }
                        while(true){
                            System.out.print("Nhập tên: ");
                            name = sc.nextLine();
                            if(name.isEmpty()) System.out.println("Nhập sai định dạng.");
                            else break;
                        }
                        while(true){
                            System.out.print("Nhập giá mua: ");
                            purchasePrice = sc.nextLine();
                            if(!CheckNumber.checkNumber(purchasePrice)) System.out.println("Nhập sai định dạng.");
                            else break;
                        }
                        while(true){
                            System.out.print("Nhập số cổng: ");
                            numberOfPorts = sc.nextLine();
                            if(!CheckNumber.checkNumber(numberOfPorts)) System.out.println("Nhập sai định dạng.");
                            else break;
                        }
                        Asset prod = new NetworkDevice(assetCode, name, Double.parseDouble(purchasePrice), Integer.parseInt(numberOfPorts));
                        listProduct[i++] = prod;
                        String cont;
                        while(true){
                            System.out.print("Nhập continue để tiếp tục hoặc exit để rời đi. ");
                            cont = sc.nextLine();
                            if(cont.equals("continue") || cont.equals("exit")) break;
                        }
                        if(cont.equals("exit")) break;
                    }
                    break;
                case "3":
                    ok = false;
                    break;
                default:
                    System.out.println("Sai đinh dạng. ");
            }
            if(ok) break;
        }
    }
    public static void report(){
        for(int j=0; j<i; j++){
            listProduct[j].getMarketValue();
        }
    }
    public static void search(String assetCode){
        boolean ok = true;
        for(int j = 0; j< i; j++){
            if(listProduct[j].getAssetCode().equals(assetCode)){
                ok = false;
                listProduct[j].getMarketValue();
                break;
            }
        }
        if(ok) System.out.println("Không tồn tại sản phẩm.");
    }
    public static void search(double purchasePrice){
        boolean ok = true;
        for(int j = 0; j< i; j++){
            if(listProduct[j].getPurchasePrice() > purchasePrice){
                ok = false;
                listProduct[j].getMarketValue();
            }
        }
        if(ok) System.out.println("Không tồn tại sản phẩm.");
    }
    public static void updatePrice(){
        while(true){
            String assetCode, th2;
            boolean ok = true;
            while(true){
                System.out.print("Nhập mã tài sản cần tìm: ");
                assetCode = sc.nextLine();
                if(assetCode.isEmpty()) System.out.println("Nhập sai định dạng.");
                else break;
            }
            for(int j = 0; j< i; j++){
                if(listProduct[j].getAssetCode().equals(assetCode)){
                    ok =   false;
                    listProduct[j].updatePrice();
                    break;
                }
            }
            if(ok){
                System.out.println("Không tìm mã sản phẩm.");
            }
            while(true){
                System.out.print("Nhập continue để tiếp tục hoặc exit để rời đi. ");
                th2 = sc.nextLine();
                if(th2.equals("continue") || th2.equals("exit")) break;
            }
            if(th2.equals("exit")) break;
        }
    }
}
