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
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.services.ServiceCategory;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form {

    public ListTasksForm(Form previous) {
        setTitle("Liste des categories");
        Container List = new Container (BoxLayout.y());
        for (Categorie p : ServiceCategory.getInstance().getAllTasks()) {
           
//            System.out.println(user.getId());
        Button update = new Button("modifier");
        update.setUIID("update");
           update.addActionListener(e -> new ModfCa(p,previous).show());
        Button delete = new Button("supprimer");
        delete.setUIID("delete");
           delete.addActionListener(e -> new DeleteCa(p,previous).show());
//            for(int i = 0; i < p; i++)
//            {
//                System.out.println();
//                }
          
            add(delete);
            add(update);
            MultiButton mb = new MultiButton(p.toString()); 
            add(mb);
        

        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
        

    }
}
