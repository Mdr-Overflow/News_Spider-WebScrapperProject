package DB;

import java.sql.SQLException;

public class TEST_DB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	try {
		Create_Tables ct = new Create_Tables();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
