
import java.sql.Connection;

public class Category implements ActiveDomainObject{

	private int id;
	private String name;

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(String name) {
		this(-1, name);
	}

	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Connection conn) {
		initialize(conn);
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			if (id != -1){
				stmt.executeUpdate("UPDATE category SET name="+name+", WHERE id="+id);
			} else {
				stmt.executeUpdate("INSERT INTO category VALUES(NULL,"+name+")");
				id = last_insert_id();
			}
		} catch (Exception e){
			System.out.println("db error during saving of category");
			return;
		}
	}

}
