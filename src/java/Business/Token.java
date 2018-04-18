package Business;

import Entity.TokenEntity;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Base64;
import javax.ejb.Stateless;

@Stateless
public class Token implements IToken {

    private Client client;

    public Token() {

        client = ClientBuilder.newClient();
    }

    @Override
    public TokenEntity GetClient_Credential(String nid, String deposit) {

        byte[] encodedBytes = Base64.getEncoder().encode(
                "123456niloofar:8626c7579b2dcd18cd78".getBytes());
        String au = new String(encodedBytes);

        Form f = new Form();
        f.param("grant_type", "client_credentials");
        f.param("nid", nid);
        f.param("deposit", deposit);

        WebTarget webtarget = client
                .target("https://sandbox.finnotech.ir/dev/v1/oauth2/token");

        MultivaluedMap head = new MultivaluedHashMap();
        head.add("Authorization", "Basic " + au);
        head.add("Content-Type", "application/json");

        Builder builder = webtarget.request();
        builder.headers(head);

//        Response r = (Response)builder.post(Entity.
//                entity(f, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Response.class);
//        String a = r.readEntity(String.class);
//        System.out.println("Client_Credential is " + a);
        TokenEntity returnTokenEntity = (TokenEntity) builder.post(Entity.
                entity(f, MediaType.APPLICATION_FORM_URLENCODED_TYPE), TokenEntity.class);
        return returnTokenEntity;

    }

    //Useing Authorization_Code for geting Token
    @Override
    public TokenEntity GetAuthorization_Code(String code) {

        byte[] encodedBytes = Base64.getEncoder().encode(
                "123456niloofar:8626c7579b2dcd18cd78".getBytes());
        String au = new String(encodedBytes);

        Form f = new Form();
        f.param("grant_type", "authorization_code");
        f.param("code", code);
        f.param("redirect_uri", "http://localhost:8080/AdanikProject/oauth2callback");

        WebTarget webtarget = client.target("https://sandbox.finnotech.ir/dev/v1/oauth2/token");

        MultivaluedMap head = new MultivaluedHashMap();
        head.add("Authorization", "Basic " + au);
        head.add("Content-Type", "application/json");

        Builder builder = webtarget.request();
        builder.headers(head);

        //Response r = (Response)builder.post(Entity.
        //entity(f, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Response.class);
        //String a = r.readEntity(String.class);
        TokenEntity returnTokenEntity;
        try {
            returnTokenEntity = (TokenEntity) builder.post(Entity
                    .entity(f, MediaType.APPLICATION_FORM_URLENCODED_TYPE), TokenEntity.class);
        } catch (Exception ex) {
            return null;
        }

        return returnTokenEntity;
    }
}
