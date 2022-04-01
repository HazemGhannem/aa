/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.AccessToken;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.social.FacebookConnect;
import com.codename1.social.GoogleConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
/**
 *
 * @author Hazem
 */
public class SignIn extends Form{

    private Form loginForm;

    private Resources theme;

    private Login login;

    public void init(Object context) {
//        try {
//            theme = Resources.openLayered("/theme");
//            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    
    public void destroy() {
    }

    public void stop() {
    }

    private void showFacebookUser(String token){
        ConnectionRequest req = new ConnectionRequest();
        req.setPost(false);
        req.setUrl("https://graph.facebook.com/v2.3/me");
        req.addArgumentNoEncoding("access_token", token);
        InfiniteProgress ip = new InfiniteProgress();
        Dialog d = ip.showInifiniteBlocking();
        NetworkManager.getInstance().addToQueueAndWait(req);
        byte[] data = req.getResponseData();
        JSONParser parser = new JSONParser();
        Map map = null;
        try {
            map = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String name = (String) map.get("name");
        d.dispose();
        Form userForm = new UserForm(name, (EncodedImage) theme.getImage("user.png"), "https://graph.facebook.com/v2.3/me/picture?access_token=" + token);
        userForm.show();
    }

    private void showGoogleUser(String token){
        ConnectionRequest req = new ConnectionRequest();
        req.addRequestHeader("Authorization", "Bearer " + token);
        req.setUrl("https://www.googleapis.com/plus/v1/people/me");
        req.setPost(false);
        InfiniteProgress ip = new InfiniteProgress();
        Dialog d = ip.showInifiniteBlocking();
        NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
        byte[] data = req.getResponseData();
        JSONParser parser = new JSONParser();
        Map map = null;
        try {
            map = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String name = (String) map.get("displayName");
        Map im = (Map) map.get("image");
        String url = (String) im.get("url");
        Form userForm = new UserForm(name, (EncodedImage) theme.getImage("user.png"), url);
        userForm.show();
    }

    public class LoginListener extends LoginCallback {

        public static final int FACEBOOK = 0;

        public static final int GOOGLE = 1;

        private int loginType;

        public LoginListener(int loginType) {
            this.loginType = loginType;
        }

        public void loginSuccessful() {

            try {
                AccessToken token = login.getAccessToken();
                if (loginType == FACEBOOK) {
                    showFacebookUser(token.getToken());
                } else if (loginType == GOOGLE) {
                    showGoogleUser(token.getToken());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void loginFailed(String errorMessage) {
            Dialog.show("Login Failed", errorMessage, "Ok", null);
        }
    }
}
