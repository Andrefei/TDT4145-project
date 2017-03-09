String url = "jdbc:mysql://localhost:3306/teacherspet?useSSL=false";
String username = "root";
String password = "root";

Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection con = DriverManager.getConnection(url, username, password);
Statement stmt = con.createStatement();