package DB;

import java.sql.SQLException;

public class BADURL_TEST {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		DB_BADURL bad = new DB_BADURL();
		bad.del_BADURL();
		bad.Create_BADURL();
		for (String got: bad.Get_BadUrls()) {
			System.out.println(got);
		}
		bad.END_CONNECTION();
	}

}
