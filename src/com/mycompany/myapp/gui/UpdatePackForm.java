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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Pack;
import com.mycompany.myapp.services.ServicePack;

/**
 *
 * @author Hazem
 */
public class UpdatePackForm extends Form {

    public UpdatePackForm(Pack p, Resources res) {
        setTitle("update Personne");
        setLayout(BoxLayout.y());
//        Event t = new Event();
 //       ComboBox cb = new ComboBox();
    TextField Id = new TextField(String.valueOf(p.getId()), "PackID");
    TextField prix = new TextField(p.getPrix(), "prix", 20, TextField.ANY);
    TextField desc = new TextField(p.getDescription(), "Description", 20, TextField.EMAILADDR);
    TextField type = new TextField(p.getType(), "type", 20, TextField.PHONENUMBER);
    
    prix.getAllStyles().setMargin(LEFT, 0);
    desc.getAllStyles().setMargin(LEFT, 0);
    type.getAllStyles().setMargin(LEFT, 0);

    
        Button btnSubmit = new Button("Update");
        Button btnret = new Button("return");
        
            btnret.addActionListener(e -> new ProfileForm(res).show());
        
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((desc.getText().length() == 0)) {
                    Dialog.show("Alert", "Are you sure !!", new Command("OK"));
               } else {
                     Pack p = new Pack(Integer.parseInt(Id.getText()),type.getText(), desc.getText(), prix.getText());
                   // System.out.println(tfID.getText());
//                    Event t = new Event(tfID.getText());
                    System.out.println(p.getId());
                    System.out.println(p.getPrix());
                    System.out.println(p.getType());
                    System.out.println(p.getDescription());
                    System.out.println("updated account");
                    
                    if (ServicePack.getInstance().UpdatePack(p)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }
                        
                }
               
            
            }
        });
        addAll( Id,type,desc,prix ,btnSubmit,btnret);
//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
}
}
