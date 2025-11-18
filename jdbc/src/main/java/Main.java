import lombok.extern.slf4j.Slf4j;
import model.Member;

import java.sql.*;

@Slf4j
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://3.35.235.238:3306/tutor", "tutor", "tutorp");

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM member WHERE id = ?");
        statement.setLong(1, 5L);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .email(resultSet.getString("email"))
                    .age(resultSet.getInt("age")).build();
            log.info("회원 {}", member);
        }
        connection.close();
    }
}
