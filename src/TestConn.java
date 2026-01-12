import com.util.DatabaseConnection;
import java.sql.Connection;

public class TestConn {
    public static void main(String[] args) {
        // 测试数据库连接状态
        Connection conn = DatabaseConnection.getConnection();
        if(conn != null){
            System.out.println("✅ 数据库连接成功！");
        }else{
            System.out.println("❌ 数据库连接失败！");
        }
        // 修复核心报错：close方法传3个参数(conn, null, null) 匹配工具类定义
        DatabaseConnection.close(conn, null, null);
    }
}