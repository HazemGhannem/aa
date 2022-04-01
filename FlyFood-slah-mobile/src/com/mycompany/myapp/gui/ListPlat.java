/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Plat;
import com.mycompany.myapp.services.ServicePlat;


/**
 *
 * @author bouss
 */
public class ListPlat extends Form {
    
        public ListPlat(Form previous) {
        setTitle("Liste des Plats");
        setLayout(BoxLayout.y());
       
        for (Plat p : ServicePlat.getInstance().getAllPlats()) {
        
        MultiButton afficher = new MultiButton(p.toString());
      //afficher.addActionListener(e->new affichage(p, previous));
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
            add (afficher);
            add(delete);
            add(update);
            
            }
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
         
    
}
