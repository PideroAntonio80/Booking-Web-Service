
package interfaces;

import java.sql.ResultSet;

/**
 *
 * @author Shady
 */
public interface IMotorSQL {
	
	public void connect();
	public int execute(String sql);
	public ResultSet executeQuery(String sql);
	public void disconnect();

}
