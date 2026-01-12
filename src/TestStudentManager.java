import com.entity.Student;
import com.manager.StudentManager;

public class TestStudentManager {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        // 测试添加学生
        Student s = new Student();
        s.setName("张三");
        s.setGender("男");
        s.setClazz("一班");  // ★已修复：改为setClazz 解决报错★
        s.setMath_score(85.5);
        s.setJava_score(90.0);
        manager.addStudent(s);

        // 测试显示所有学生
        System.out.println("所有学生信息：");
        manager.showAllStudents();

        // 测试查询学生
        System.out.println("\n查询ID=1的学生：");
        Student queryS = manager.queryStudentById(1);
        if (queryS != null) {
            System.out.println("姓名：" + queryS.getName());
        }

        // 测试计算平均分
        System.out.println("\n各科平均分：");
        manager.calculateAverageScore();
    }
}