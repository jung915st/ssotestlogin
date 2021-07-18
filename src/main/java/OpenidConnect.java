//openid connect

import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.Nonce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;

//@WebServlet(name = "OpenidConnect", urlPatterns = {"/eduopenid/login"})
public class OpenidConnect extends HttpServlet {
    private static final long serialVersionUID = 2447533304172100227L;
    private final Logger logger = LoggerFactory.getLogger(OpenidConnect.class);
    String clientid = "c58032d18369506ee83b3112e3363b95";//www.sso.edu.tw
    String callbackURI = "https://www.sso.edu.tw/cncreturnpage";
    String openidServerURL = "https://oidc.tanet.edu.tw";
    String authorization_endpoint;

/*    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();

        _OpenIDServerURL = context.getInitParameter("OpenIDConnect_URL");    //從web.xml中取得OpenID Connect Server的網址
        _ClientID = context.getInitParameter("OpenIDConnect_ClientID");      //從web.xml中取得申請服務所取得的ClientID
        _CallbackURI = context.getInitParameter("OpenIDConnect_Callback");   //從web.xml中取得申請服務時所取得的callback URL(redirect_url)
    }*/

    //Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //Process the HTTP Get request
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //建立OAuth的ClientID物件,並指定申請服務取得的clientid
        ClientID clientID = new ClientID(clientid);

        try {
            //指定申請服務時所填寫的callback回傳網址
            URI callback = new URI(callbackURI);
            //"https://www.sso.edu.tw/cncreturnpage" ], "allowingscopes" : [ "educloudroles", "openid", "profile", "exchangedata", "syncdata", "email", "relation", "worker" ]

            //產生state值並存入session, 以利之後與回應的值做比對檢查是否一致
            State state = new State();
            session.setAttribute("state", state.toString());

            //產生nonce
            Nonce nonce = new Nonce();

            //產生request物件
            AuthenticationRequest authzReq = new AuthenticationRequest(
                    new URI(openidServerURL + "/oidc/v1/azp"),     //default URL -> https://oidc.tanet.edu.tw/oidc/v1/azp
                    new ResponseType("code"),
                    Scope.parse("openid email profile openid2 eduinfo"),//openid+email+profile+openid2+eduinfo
                    clientID,
                    callback,
                    state,
                    nonce);

            // 此為 Authrozation Code flow 的第一步
            logger.info("1.User authorization request");
            logger.info(authzReq.getEndpointURI().toString() + "?" + authzReq.toQueryString());

            //送出請求
            response.sendRedirect(authzReq.getEndpointURI().toString() + "?" + authzReq.toQueryString());

            this.authorization_endpoint = authzReq.getEndpointURI().toString() + "?" + authzReq.toQueryString();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



}
