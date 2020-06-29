package controllers;

import org.pmw.tinylog.Logger;

import java.util.List;

public abstract class BasicControllerWithInitData extends BasicController {

    public void initData(Object o){

        Logger.info("meghivtak az initdata fgv-t.");
    }
    public void initData(List l){}
}
