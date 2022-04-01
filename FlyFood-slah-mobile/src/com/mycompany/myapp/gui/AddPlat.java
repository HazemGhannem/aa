/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Plat;
import com.mycompany.myapp.services.ServicePlat;

/**
 *
 * @author bouss
 */
public class AddPlat extends Form{
    public AddPlat(Form previous) {
        setTitle("Ajouter un plat");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","nom du plat");
        TextField tfDesc = new TextField("","description");
        TextField tfImg = new TextField("","image");
       
        
        
        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0) && (tfDesc.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Plat  p = new Plat (tfNom.getText().toString(),tfDesc.getText().toString(),tfImg.getText().toString() );
                        if( ServicePlat.getInstance().addProduct(p))
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
        
        addAll(tfNom,tfDesc,tfImg,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
