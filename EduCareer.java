import java.util.Scanner;

class CheckNumber{
    public static boolean checkNumber(String baseSalary){
        try{
            float res = Float.parseFloat(baseSalary);
            return true;
        }catch(RuntimeException e){
            return false;
        }
    }
}
interface ICapability{
    void checkPerformance();//public abstract
    default void xinChao(){
        System.out.println("Xin chao ban la toi day");
    }
}

abstract class Staff{
    protected int id;
    protected String name;
    protected float baseSalary;
    public Staff(int id, String name, float baseSalary){
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }
    public abstract float calculateSalary();
    public void display(){
        System.out.println("Mã: " + this.id);
        System.out.println("Tên: " + this.name);
        System.out.println("Lương cơ bản: " + this.baseSalary);
        System.out.println("Lương thực: " + calculateSalary());
    }
    public int getID() {return this.id;}
    public void setName(String name){this.name = !name.isEmpty()?name:this.name;}
    public void setSalary(String salary){this.baseSalary = !salary.isEmpty()?Float.parseFloat(salary):this.baseSalary;}
}

class AdminStaff extends Staff implements ICapability{
    private float bonus;
    public AdminStaff(int id, String name, float baseSalary, float bonus){
        super(id, name, baseSalary);
        this.bonus = bonus;
    }
    @Override
    public float calculateSalary(){
        return this.baseSalary + this.bonus;
    }
    @Override
    public void checkPerformance(){
        System.out.println("Very good!");
    }
    public void setBonus(String bonus){
        this.bonus = !bonus.isEmpty()?Float.parseFloat(bonus):this.bonus;
    }
}

class Lecturer extends Staff implements ICapability{
    private float teachHourse;
    public Lecturer(int id, String name, float baseSalary, float teachHourse){
        super(id, name, baseSalary);
        this.teachHourse = teachHourse;
    }
    @Override
    public float calculateSalary() {
        return this.baseSalary + (this.teachHourse * 200000);
    }
    @Override
    public void checkPerformance(){
        System.out.println("Very good!");
    }
    public void setHourse(String teachHourse){
        this.teachHourse = !teachHourse.isEmpty()?Float.parseFloat(teachHourse):this.teachHourse;
    }
}

public class EduCareer{
    private static Staff[] listStaff = new Staff[5000];
    private static int i = 0;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        while(true){
            System.out.println("Hệ thống quản lí nhân viên. ");
            System.out.println("1. Thêm mới.");
            System.out.println("2. Hiển thị danh sách.");
            System.out.println("3. Cập nhật. ");
            System.out.println("4. Xóa. ");
            System.out.println("5. Thoát. ");
            System.out.print("Nhập lựa chọn của bạn: ");
            String option = sc.nextLine();
            if(option.isEmpty()) System.out.println("Lỗi không được bỏ trống. ");
            else if(!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")
                    && !option.equals("5")) System.out.println("Sai định dạng!");
            switch(option){
                case "1":
                    addStaff();
                    break;
                case "2":
                    displayStaff();
                    break;
                case "3":
                    updateStaff();
                    break;
                case "4":
                    deleteStaff();
                    break;
                case "5":
                    System.exit(0);
                    break;
            }
        }
    }
    public static void addStaff(){
        while(true){
            boolean ok = true;
            System.out.println("====THEM MOI=====");
            System.out.println("1. Thêm Giảng Viên.");
            System.out.println("2. Thêm nhân viên.");
            System.out.println("3. Thoát.");
            System.out.print("Nhập lựa chọn của bạn:");
            String option = sc.nextLine();
            if(option.isEmpty()) System.out.println("Lỗi không được bỏ trống. ");
            else if(!option.equals("1") && !option.equals("2") && !option.equals("3")) System.out.println("Sai định dạng!");
            switch(option){
                case "1":
                    while(true){
                        String ma, name, salary, teachHourse;
                        while(true){
                            System.out.print("Nhập mã: ");
                            ma = sc.nextLine();
                            if(ma.isEmpty() || !CheckNumber.checkNumber(ma)) {
                                System.out.println("Nhập sai đinh dạng.");
                                continue;
                            }
                            break;
                        }
                        while(true){
                            System.out.print("Nhập tên giảng viên: ");
                            name = sc.nextLine();
                            if(name.isEmpty()){
                                System.out.println("Sai dinh dang!");
                                continue;
                            }
                            break;
                        }
                        while(true){
                            System.out.print("Nhập lương cơ bản: ");
                            salary = sc.nextLine();
                            if(!CheckNumber.checkNumber(salary)){
                                System.out.println("Đinh dạng không hợp lệ. ");
                                continue;
                            }
                            break;
                        }
                        while(true){
                            System.out.print("Nhập số giờ dạy: ");
                            teachHourse = sc.nextLine();
                            if(!CheckNumber.checkNumber(teachHourse)){
                                System.out.println("Nhập sai đinh dạng.");
                                continue;
                            }
                            break;
                        }
                        Staff gv = new Lecturer(Integer.parseInt(ma),name,Float.parseFloat(salary), Float.parseFloat(teachHourse));
                        listStaff[i++] = gv;
                        System.out.println("Thêm thành công.");
                        String text;
                        while(true){
                            System.out.print("Continue để thêm giảng viên hoặc exit để rời đi: ");
                            text = sc.nextLine();
                            if(text.equals("continue") || text.equals("exit")) break;
                            System.out.println("Sai định dạng. ");
                        }
                        if(text.equals("exit")) break;
                    }
                    break;
                case "2":
                    while(true){
                        String ma, name, salary, bonus;
                        while(true){
                            System.out.print("Nhập mã: ");
                            ma = sc.nextLine();
                            if(ma.isEmpty() || !CheckNumber.checkNumber(ma)){
                                System.out.println("Nhập sai đinh dạng.");
                                continue;
                            }
                            break;
                        }
                        while(true){
                            System.out.print("Nhập tên nhân viên: ");
                            name = sc.nextLine();
                            if(name.isEmpty()){
                                System.out.println("Sai dinh dang!");
                                continue;
                            }
                            break;
                        }
                        while(true){
                            System.out.print("Nhập lương cơ bản: ");
                            salary = sc.nextLine();
                            if(CheckNumber.checkNumber(salary)){
                                System.out.println("Đinh dạng không hợp lệ. ");
                                continue;
                            }
                            break;
                        }
                        while(true){
                            System.out.print("Nhập thưởng: ");
                            bonus = sc.nextLine();
                            if(!CheckNumber.checkNumber(bonus)){
                                System.out.println("Nhập sai đinh dạng.");
                                continue;
                            }
                            break;
                        }
                        Staff nv = new AdminStaff(Integer.parseInt(ma),name,Float.parseFloat(salary), Float.parseFloat(bonus));
                        listStaff[i++] = nv;
                        System.out.println("Thêm thành công.");
                        String text;
                        while(true){
                            System.out.print("Continue để thêm nhân viên hoặc exit để rời đi: ");
                            text = sc.nextLine();
                            if(text.equals("continue") || text.equals("exit")) break;
                            System.out.println("Sai định dạng. ");
                        }
                        if(text.equals("exit")) break;
                    }
                    break;
                default:
                    ok = false;
                    break;
            }
            if(!ok) break;
        }
    }
    public static void displayStaff(){
        for(int j = 0; j < i; j++){
            listStaff[j].display();
        }
    }
    public static void updateStaff(){
        while(true){
            String option;
            System.out.print("Nhập mã: ");
            String ma = sc.nextLine();
            if(ma.isEmpty() || !CheckNumber.checkNumber(ma)) System.out.println("Nhập sai đinh dạng.");
            else{
                boolean noReach = true;
                for(int j = 0; j< i ;j++){
                    if(listStaff[j].getID() == Integer.parseInt(ma)){
                        noReach = false;
                        String name, salary;
                        while(true){
                            System.out.print("Nhập tên hoặc enter để bỏ qua: ");
                            name = sc.nextLine();
                            break;
                        }
                        while(true){
                            System.out.print("Nhập lương hoặc enter để bỏ qua: ");
                            salary = sc.nextLine();
                            if(!salary.isEmpty()){
                                if(CheckNumber.checkNumber(salary)) break;
                                System.out.println("Định dạng không chính xác. ");
                                continue;
                            }
                            break;
                        }
                        listStaff[j].setName(name);
                        listStaff[j].setSalary(salary);
                        if(listStaff[j] instanceof AdminStaff){
                            AdminStaff tmp = (AdminStaff) listStaff[j];
                            String bonus;
                            while(true){
                                System.out.print("Nhập thưởng hoặc enter để bỏ qua: ");
                                bonus = sc.nextLine();
                                if(!bonus.isEmpty()){
                                    if(!CheckNumber.checkNumber(bonus)){
                                        System.out.println("Sai định dạng.");
                                        continue;
                                    }
                                }
                                break;
                            }
                            tmp.setBonus(bonus);
                            listStaff[j] = tmp;
                        }
                        else{
                            Lecturer tmp = (Lecturer) listStaff[j];
                            String teachHourse;
                            while(true){
                                System.out.print("Nhập số giờ dạy hoặc enter để bỏ qua: ");
                                teachHourse = sc.nextLine();
                                if(!teachHourse.isEmpty()){
                                    if(!CheckNumber.checkNumber(teachHourse)){
                                        System.out.println("Sai định dạng.");
                                        continue;
                                    }
                                }
                                break;
                            }
                            tmp.setHourse(teachHourse);
                            listStaff[j] = tmp;
                        }
                        break;
                    }
                }
                if(noReach){
                    System.out.println("Không tồn tại mã. ");
                }
            }
            while(true){
                System.out.print("Nhập contunue để tiếp tục hoặc exit để rời đi. ");
                option = sc.nextLine();
                if(option.equals("continue") || option.equals("exit")) break;
            }
            if(option.equals("exit")) break;
            break;
        }
    }
    public static void deleteStaff(){
        while(true){
            System.out.print("Nhập mã: ");
            String ma = sc.nextLine();
            if(ma.isEmpty() || !CheckNumber.checkNumber(ma)) System.out.println("Nhập sai đinh dạng.");
            else{
                boolean ok = true;
                for(int j = 0; j < i; j++){
                    if(listStaff[j].getID() == Integer.parseInt(ma)){
                        ok = false;
                        int k = j;
                        for(int h = k; h < i-1; h++){
                            listStaff[h] = listStaff[h+1];
                        }
                        i--;
                        break;
                    }
                }
                if(ok){
                    System.err.print("Không tồn tại mã. ");
                    String option;
                    while(true){
                        System.out.print("Nhập continue để tiếp tục hoặc exit để thoát ra:");
                        option = sc.nextLine();
                        if(option.equals("continue") || option.equals("exit")) break;
                        System.err.print("Sai định dạng. ");
                    }
                    if(option.equals("exit")) break;
                }
                else break;
            }
        }
    }
}