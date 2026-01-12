import com.entity.Student;
import com.manager.StudentManager;
import java.sql.Connection;
import com.util.DatabaseConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 第一步：检查数据库连接状态，运行就会显示
        Connection conn = DatabaseConnection.getConnection();
        if(conn != null){
            System.out.println("✅ 数据库连接成功！可以正常使用系统了！");
        }else{
            System.out.println("❌ 数据库连接失败，请检查密码！");
            return;
        }

        StudentManager sm = new StudentManager();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n=====学生信息管理系统=====");
            System.out.println("1.添加学生信息");
            System.out.println("2.根据学号查询学生");
            System.out.println("3.显示所有学生信息");
            System.out.println("4.计算各科平均成绩");
            System.out.println("5.退出系统");
            System.out.print("请选择操作(1-5)：");

            // 修复：防止输入非数字报错
            int num = -1;
            if(sc.hasNextInt()){
                num = sc.nextInt();
            }else{
                sc.nextLine(); // 清空错误输入
                System.out.println("输入错误，请输入数字1-5！");
                continue;
            }

            switch(num){
                case 1:
                    Student s = new Student();
                    System.out.print("请输入姓名：");
                    sc.nextLine();  // 只写这1行，完美吃掉回车，解决输入BUG！
                    s.setName(sc.nextLine().trim());
                    System.out.print("请输入性别：");
                    s.setGender(sc.nextLine().trim());
                    System.out.print("请输入班级：");
                    s.setClazz(sc.nextLine().trim());
                    System.out.print("请输入高数成绩：");
                    s.setMath_score(sc.nextDouble());
                    System.out.print("请输入Java成绩：");
                    s.setJava_score(sc.nextDouble());
                    sm.addStudent(s);
                    break;
                case 2:
                    System.out.print("请输入学号：");
                    int id = sc.nextInt();
                    Student stu = sm.queryStudentById(id);
                    if(stu!=null){
                        System.out.println("\n✅ 查询结果：");
                        System.out.println("学号："+stu.getId()+",姓名："+stu.getName()+",性别："+stu.getGender()+",班级："+stu.getClazz()+",高数："+stu.getMath_score()+",Java："+stu.getJava_score());
                    }
                    break;
                case 3:
                    sm.showAllStudents();
                    break;
                case 4:
                    sm.calculateAverageScore();
                    break;
                case 5:
                    System.out.println("谢谢使用，系统退出！");
                    sc.close();
                    return;
                default:
                    System.out.println("输入错误，请重新输入1-5！");
            }
        }
    }
}