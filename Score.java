import java.util.Scanner;

interface IAppraisable{
    String getRating();
}

class Branch implements IAppraisable{
    private String branchName;
    private double revenueBillion;
    public Branch(String branchName, double revenueBillion){
        this.branchName = branchName;
        this.revenueBillion = revenueBillion;
    }
    public String getName(){
        return this.branchName;
    }
    @Override
    public String getRating(){
        if(this.revenueBillion > 10000000000d) return "Level branch: A";
        return "Level branch: B";
    }
}

class Employee implements IAppraisable{
    private String empName;
    private double kpiScore;
    public Employee(String empName, double kpiScore){
        this.empName = empName;
        this.kpiScore = kpiScore;
    }
    public String getName(){
        return this.empName;
    }
    @Override
    public String getRating(){
        if(this.kpiScore >= 80) return "Xuất sắc!";
        return "Đạt";
    }
}
public class Score{
    private static IAppraisable[] list = new IAppraisable[10];
    private static int i = 0;
    public static void main(String[] args){
        IAppraisable nhanVien = new Employee("Nguyen Van A", 85d);
        list[i++] = nhanVien;
        IAppraisable branch = new Branch("Chi Nhánh Hà Nôi", 12500000000d);
        list[i++] = branch;
        System.out.println("-----------Chất lượng Education-----------");
        for(int j = 0; j<i; j++){
            if(list[j] instanceof Employee){
                Employee e = (Employee) list[j];
                System.out.printf("[ Nhân viên ]: %s | Xếp hạng: %s\n", e.getName(), e.getRating());
            }
            else{
                Branch b = (Branch) list[j];
                System.out.printf("[ Chi nhánh ]: %s | Xếp hạng: %s\n", b.getName(), b.getRating());
            }
        }
    }
}
