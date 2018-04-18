/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entity.StatementEntity;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Stateless
public class Statement implements IStatement {

    private Client client;

    public Statement() {

        client = ClientBuilder.newClient();
    }

    /**
     *
     * @author niloufar
     */
    @Override
    public StatementEntity StatementService(String nid, String deposit, String returnTokenEntity) {
        String BASE_URI = "https://sandbox.finnotech.ir/oak/v1/" + nid + "/deposits/" + deposit + "/statement?toDate" + 970123;
        WebTarget webTarget = client.target(BASE_URI);
        MultivaluedMap head = new MultivaluedHashMap();
        head.add("Authorization", "Bearer " + returnTokenEntity);
        Builder builder = webTarget.request();
        builder.headers(head);
        Response response = (Response) builder.get();
        StatementEntity returnStatementEntity = (StatementEntity) response.readEntity(StatementEntity.class);
        return returnStatementEntity;
    }

}
