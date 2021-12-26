package subsystem.interbanknew;

import common.exception.UnrecognizedException;
import utils.API;

public class InterbankBoudaryNew {
	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}
}
