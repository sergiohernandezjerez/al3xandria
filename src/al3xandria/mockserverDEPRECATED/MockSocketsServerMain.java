
package al3xandria.mockserverDEPRECATED;

import java.io.IOException;

/**
 * Class containing program that uses MockServer Uses default
 * CommaSeparatedParser
 * 
 * @author professor
 */
public class MockSocketsServerMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		int port = 5556;

		MockSocketsServer mss = new MockSocketsServer();

		mss.run(port);

	}

}
