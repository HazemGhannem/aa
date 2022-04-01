/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Plat;
import com.mycompany.myapp.services.ServicePlat;

/**
 *
 * @author pc
 */
public class affichage extends Form {
    
        public affichage(Plat p,Form previous) {
        setTitle("Liste des Plats");
        setLayout(BoxLayout.y());
       
        
        MultiButton mb = new MultiButton("Nom =" +p.getNom());
        MultiButton ms = new MultiButton("Descrption =" +p.getDesc());
        MultiButton mn = new MultiButton("Image =" +p.getImg());
      
        
//            System.out.println(user.getId());
        Button update = new Button("modifier");
        update.setUIID("update");
            update.addActionListener(e -> new ModifierPlats(p,previous).show());
        Button delete = new Button("supprimer");
        delete.setUIID("delete");
           delete.addActionListener(e -> new DeletePlats(p,previous).show());
//            for(int i = 0; i < p; i++)
//            {
//                System.out.println();
//                }
            add(mb);
            add(ms);
            add(mn);
           
            add(delete);
            add(update);
            

       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
         
    