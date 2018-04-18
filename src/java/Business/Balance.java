package  Business;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import Entity.BalanceEntity;
import javax.ejb.Stateless;
@Stateless
public class Balance implements IBalance {
	private  Client client;

	public Balance() {

		client = ClientBuilder.newClient();
	}

    /**
     *
     * @param nid
     * @param deposit
     * @param returnTokenEntity
     * @return
     */
    @Override
	public BalanceEntity BalanceService(String nid, String deposit,
			String returnTokenEntity)  {

		 String BASE_URI = "https://sandbox.finnotech.ir/oak/v1/"+nid+"/deposits/"+deposit+"/balance";
		 WebTarget webTarget = client.target(BASE_URI);

		MultivaluedMap head = new MultivaluedHashMap();
		head.add("Authorization", "Bearer " + returnTokenEntity);
		
		Builder builder = webTarget.request();
		builder.headers(head);

		Response response = (Response) builder.get();
		 
		BalanceEntity returnBalanceEntity = (BalanceEntity)response.readEntity(BalanceEntity.class);
		return returnBalanceEntity;

	}

}
