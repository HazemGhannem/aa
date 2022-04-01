/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;


import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Pack;
import com.mycompany.myapp.services.ServicePack;
/**
 *
 * @author Hazem
 */
public class ListPack extends Form {
    
    public ListPack(Pack p,Resources res){
    
    Container List = new Container (BoxLayout.y());
        List.setScrollableY(true);
       
        for (Pack pack : ServicePack.getInstance().getAllPack()) {
            MultiButton mb = new MultiButton(pack.toString());
            //System.out.println(user.getId());
        Button update = new Button("update");
        update.setUIID("update");
            update.addActionListener(e -> new UpdatePackForm(pack,res).show());
        Button delete = new Button("delete");
        delete.setUIID("delete");
            delete.addActionListener(e -> new DeletePack(pack,res).show());
//            for(int i = 0; i < user; i++)
//            {
//                System.out.println();
//                }
            add(mb);
            add(delete);
            add(update);
            }
    }
    
}
