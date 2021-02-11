package controllers;

import org.pmw.tinylog.Logger;

import java.util.List;

/**
 * This class represents a basic controller which receives initializing data from the controller that creates it.
 */

public abstract class BasicControllerWithInitData extends BasicController {

    public void initData(Object o){

        //Logger.info("meghivtak az initdata fgv-t.");
    }
    public void initData(List l){}
}
