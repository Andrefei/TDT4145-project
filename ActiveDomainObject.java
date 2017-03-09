import java.sql.Connection;

public interface ActiveDomainObject {

	public abstract void initialize (Connection conn);
    public abstract void refresh (Connection conn);
    public abstract void save (Connection conn);
	
}
