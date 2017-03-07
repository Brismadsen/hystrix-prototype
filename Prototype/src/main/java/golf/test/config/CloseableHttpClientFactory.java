package golf.test.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.glassfish.hk2.api.Factory;

import golf.test.client.PoolingConnectionManager;

public class CloseableHttpClientFactory implements Factory<CloseableHttpClient> {

	//Timeout for establishing the connection
	public static final int CONNECT_TIMEOUT = 1500;
	
	//Timeout when connection has been established.
	public static final int SOCKET_TIMEOUT = 1500;
	
	private static PoolingConnectionManager manager = null;
	
	@Override
	public void dispose(CloseableHttpClient arg0) {
	}

	private void create() {
		manager = PoolingConnectionManager.getInstance();
	}
	
	@Override
	public CloseableHttpClient provide() {
		if(manager == null) {
			create();
		}
		return manager.getClient(CONNECT_TIMEOUT, SOCKET_TIMEOUT);
	}
}
