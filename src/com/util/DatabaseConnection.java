package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";  // ★★★ 这里改成你自己的MySQL密码 ★★★

    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(Exception e){
            System.out.println("❌ 数据库连接失败：" + e.getMessage());
        }
        return conn;
    }

    // 新增：关闭所有资源的重载方法，防止内存泄漏
    public static void close(Connection conn, java.sql.PreparedStatement pstmt, java.sql.ResultSet rs) {
        try { if(rs!=null) rs.close(); } catch (SQLException e) {}
        try { if(pstmt!=null) pstmt.close(); } catch (SQLException e) {}
        try { if(conn!=null) conn.close(); } catch (SQLException e) {}
    }
}