package batista.mateus.com.br.firebasecloudmessaging;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.support.constraint.Constraints.TAG;

public class FirebaseMessagingService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);
        sendTokenToCreateArnAWS(token);
    }

    private void sendTokenToCreateArnAWS(String token){

        //here you can send the token to AWS server or send to your own server.

    }
}
