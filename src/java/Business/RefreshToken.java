/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Entity.TokenEntity;
import java.util.Base64;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
//
@Stateless
public class RefreshToken implements IRefreshToken {

    private Client client;

    public RefreshToken() {

        client = ClientBuilder.newClient();
    }

    @Override
    public TokenEntity GetRefreshToken(String refreshToken) {
        byte[] encodedBytes = Base64.getEncoder().encode(
                "123456niloofar:8626c7579b2dcd18cd78".getBytes());
        String au = new String(encodedBytes);

        Form f = new Form();
        f.param("grant_type", "refresh_token");
        f.param("refresh_token", refreshToken);

        WebTarget webtarget = client
                .target("https://sandbox.finnotech.ir/dev/v1/oauth2/token");
        MultivaluedMap head = new MultivaluedHashMap();
        head.add("Authorization", "Basic " + au);
        head.add("Content-Type", "application/json");

        Builder builder = webtarget.request();
        builder.headers(head);
        
        TokenEntity returnTokenEntity = (TokenEntity) builder.post(Entity.
                entity(f, MediaType.APPLICATION_FORM_URLENCODED_TYPE), TokenEntity.class);
        return returnTokenEntity;
    }
}
