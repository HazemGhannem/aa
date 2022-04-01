/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.GoogleConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.mycompany.myapp.gui.WalkthruForm;
import com.mycompany.myapp.gui.SginForm;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceTask;

/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {
    
    
    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome, ", "WelcomeWhite"),
                new Label(":)", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
        
        Image profilePic = theme.getImage("yes.png");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());
        
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR) ;
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD) ;
        email.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        
        
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        
        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        
        loginButton.addActionListener(e -> {
            
                       ServiceTask.getInstance().signin(email,password,theme);
        });
        
        
        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("LoginButton");
        createNewAccount.addActionListener(e -> {
            Toolbar.setGlobalToolbar(false);
            new SginForm(theme).show();
            Toolbar.setGlobalToolbar(true);
        });
         Button d = new Button("fb");
        d.setUIID("LoginButton");
        Login fb = FacebookConnect.getInstance();
        String clientId = "1171134366245722";
                String redirectURI = "http://127.0.0.1:8000/";
                String clientSecret = "d2eefcd6e0f4c03b3cd60ffef344f851";
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                fb.setCallback(new LoginCallback() {
                    public void loginSuccessful() {
                System.out.println("google worked");
                // we can now start fetching stuff from Google+!
               new ProfileForm(theme).show();
            }

            public void loginFailed(String errorMessage) {
                System.out.println("login failed");
            }
        });
                d.addActionListener(e -> {
                if(!fb.isUserLoggedIn()){
                    fb.doLogin();
                }else{
                   String token = fb.getAccessToken().getToken();
                }

            });
        
     
       
       //
      
       
        
        
        
        Button logingglButton = new Button("LOGIN GOOGLE");
        logingglButton.setUIID("LoginButton");
        
          Login gc = GoogleConnect.getInstance();
        gc.setClientId("424141229670-ku7e3q8hcsnfdj9c5vo3qupf16lgav84.apps.googleusercontent.com");
        gc.setRedirectURI("http://127.0.0.1:8000/");
        gc.setClientSecret("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");
         gc.setCallback(new LoginCallback() {
            public void loginSuccessful() {
                System.out.println("google worked");
                // we can now start fetching stuff from Google+!
               new ProfileForm(theme).show();
            }

            public void loginFailed(String errorMessage) {
                System.out.println("login failed");
            }
        });
         

// trigger the login if not already logged in
        logingglButton.addActionListener(e -> {
        if (!gc.isUserLoggedIn()) {
            gc.doLogin();
           
            System.out.println("di");
           // new ProfileForm(theme).show();
        } else {
            // get the token and now you can query the Google API 
            String token = gc.getAccessToken().getToken();
            Storage.getInstance().writeObject("token", token);
            new ProfileForm(theme).show();
            System.out.println("hama");
            //Dialog.show("failed", "login err", new Command("OK"));
            
            // NOTE: On Android, this token will be null unless you provide valid
            // client ID and secrets.
        }
       // new ProfileForm(theme).show();
       
        });
          
          
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,
                BorderLayout.center(email).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount,
                logingglButton,
                d
              
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
