/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.FloatingHint;
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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceTask;
import com.mycompany.myapp.gui.LoginForm;


/**
 *
 * @author Hazem
 */
public class SginForm extends Form {
    public SginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        
    
    TextField username = new TextField("", "Username", 20, TextField.ANY);
    TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
    TextField tel = new TextField("", "Phone", 20, TextField.PHONENUMBER);
    TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
    TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
    
    username.getAllStyles().setMargin(LEFT, 0);
    email.getAllStyles().setMargin(LEFT, 0);
    tel.getAllStyles().setMargin(LEFT, 0);
    password.getAllStyles().setMargin(LEFT, 0);
    confirmPassword.getAllStyles().setMargin(LEFT, 0);
    
    
    Button register = new Button("Register");
    register.setUIID("CreateNewAccountButton");
    register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((email.getText().length()==0)||(password.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        User t = new User(tel.getText().toString(),username.getText(),email.getText(), password.getText().toString());
                        if( ServiceTask.getInstance().addUsers(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
    Button login = new Button("Login");
    login.setUIID("CreateNewAccountButton");
    login.addActionListener( e -> {
            Toolbar.setGlobalToolbar(false);
            new LoginForm(theme).show();
            Toolbar.setGlobalToolbar(true);});
        
        
        Container by = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
               
                new FloatingHint(username),
                new FloatingHint(email),
                new FloatingHint(tel),
                new FloatingHint(password),
                new FloatingHint(confirmPassword),
     
                register,
                login
                
                );
        
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    
    
    }
}
