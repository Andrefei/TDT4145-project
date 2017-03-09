package trening;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Exercise implements ActiveDomainObject{
	
	private String name, description;
	private ArrayList<Category> categories;
	private Goal goal;

	public Exercise(String name, String description) {
		this.name = name;
		this.description = description;
		this.categories = new ArrayList<>();
		getCategories();
	}
	
	private void getCategories() {
		// TODO Auto-generated method stub
		
	}
	
	public void setGoal(Goal goal){
		this.goal = goal;
	}

	public Exercise(String name) {
		this.name = name;
	}
	
	public void initialize(Connection conn) {
		try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select description from Exercise where name=" + this.name);
            while (rs.next()) {
                this.description = rs.getString("description");
            }

        } catch (Exception e) {
            System.out.println("db error during select of exercise= "+e);
            return;
        }
		
	}

	@Override
	public void refresh(Connection conn) {
		initialize(conn);
		
	}

	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		
	}

}
