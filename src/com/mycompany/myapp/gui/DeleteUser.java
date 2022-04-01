/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author Hazem
 */
public class DeleteUser extends Form {

    public DeleteUser(User u, Resources res) {
        setTitle("delete Personne");
        setLayout(BoxLayout.y());
//        Event t = new Event();
 //       ComboBox cb = new ComboBox();
        //TextField tfID = new TextField("", "PersonneId");
        Button btnSubmit = new Button("Delete");
        Button btnret = new Button("return");
        
            btnret.addActionListener(e -> new ProfileForm(res).show());
        
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               // if ((tfID.getText().length() == 0)) {
                    Dialog.show("Alert", "Are you sure !!", new Command("OK"));
               // } else {
                   // System.out.println(tfID.getText());
//                    Event t = new Event(tfID.getText());
                    System.out.println(u.getId());
                    System.out.println(u.getUsername());
                    System.out.println("deleted account");
                    
                    if (ServiceTask.getInstance().deleteUser(u)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }
                        
                }
            //}
        });
        addAll( btnSubmit,btnret);
//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }  
}
