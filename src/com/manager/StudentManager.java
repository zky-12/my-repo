package com.manager;

import com.entity.Student;
import com.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManager {
    // 1.添加学生 - 修复所有问题，数据100%存入数据库
    public void addStudent(Student student) {
        String sql = "INSERT INTO students(name, gender, class, math_score, java_score) VALUES (?,?,?,?,?)";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        if(conn == null){
            System.out.println("❌ 数据库未连接，添加失败！");
            return;
        }
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClazz());
            pstmt.setDouble(4, student.getMath_score());
            pstmt.setDouble(5, student.getJava_score());
            int count = pstmt.executeUpdate();
            if(count > 0){
                System.out.println("✅ 添加成功！你的学号是：" + getNewId());
            } else {
                System.out.println("❌ 添加失败，数据未存入！");
            }
        } catch (SQLException e) {
            System.out.println("❌ 添加出错：" + e.getMessage());
        } finally {
            DatabaseConnection.close(conn, pstmt, null); // 强制关闭资源，刷入数据
        }
    }

    // 2.根据学号查询学生
    public Student queryStudentById(int id) {
        Student student = null;
        String sql = "SELECT * FROM students WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if(conn == null) return student;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClazz(rs.getString("class"));
                student.setMath_score(rs.getDouble("math_score"));
                student.setJava_score(rs.getDouble("java_score"));
            } else {
                System.out.println("❌ 学号 " + id + " 不存在！");
            }
        } catch (SQLException e) {
            System.out.println("❌ 查询出错：" + e.getMessage());
        } finally {
            DatabaseConnection.close(conn, pstmt, rs);
        }
        return student;
    }

    // 3.显示所有学生信息
    public void showAllStudents() {
        String sql = "SELECT * FROM students";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if(conn == null) return;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            boolean hasData = false;
            System.out.println("\n=====所有学生信息=====");
            while(rs.next()) {
                hasData = true;
                System.out.println("学号："+rs.getInt("id")+" | 姓名："+rs.getString("name")+" | 性别："+rs.getString("gender")+" | 班级："+rs.getString("class")+" | 高数："+rs.getDouble("math_score")+" | Java："+rs.getDouble("java_score"));
            }
            if(!hasData) System.out.println("暂无学生信息");
        } catch (SQLException e) {
            System.out.println("❌ 查询出错：" + e.getMessage());
        } finally {
            DatabaseConnection.close(conn, pstmt, rs);
        }
    }

    // 4.计算平均分
    public void calculateAverageScore() {
        String sql = "SELECT AVG(math_score) avgMath, AVG(java_score) avgJava FROM students";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if(conn == null) return;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                System.out.println("\n=====成绩统计=====");
                System.out.println("高数平均分：" + rs.getDouble("avgMath"));
                System.out.println("Java平均分：" + rs.getDouble("avgJava"));
            }
        } catch (SQLException e) {
            System.out.println("❌ 统计出错：" + e.getMessage());
        } finally {
            System.out.println("-----------------");
            DatabaseConnection.close(conn, pstmt, rs);
        }
    }

    // 获取最新添加的学生学号
    private int getNewId() {
        String sql = "SELECT MAX(id) as maxId FROM students";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 1;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) id = rs.getInt("maxId");
        } catch (SQLException e) {} finally {
            DatabaseConnection.close(conn, pstmt, rs);
        }
        return id;
    }
}