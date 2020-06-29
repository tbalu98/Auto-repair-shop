package controllers;

import daos.*;
import entities.*;
import filters.AlkatreszFilter;
import filters.JavitasTipusFilter;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nezetek.FelhasznaltAlkatreszekNezet;
import nezetek.JavitasTipusNezet;
import nezetek.JavitasokNezet;
import org.pmw.tinylog.Logger;
import utils.TableManagerImpl;
import utils.TableManager;


import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SzerelesSzerkesztese extends SzerelesMegtekintese {



    private SzerelesDao szerelesDao = new SzerelesDao(EntityManagerCreator.getEntityManager());

    private OradijasJavitasTipusDao oradijasJavitasTipusDao = new OradijasJavitasTipusDao(EntityManagerCreator.getEntityManager());
    private JavitasDao javitasDao = new JavitasDao(EntityManagerCreator.getEntityManager());
    private JavitasTipusDao javitasTipusDao = new JavitasTipusDao(EntityManagerCreator.getEntityManager());

    private AlkatreszDao alkatreszDao = new AlkatreszDao(EntityManagerCreator.getEntityManager());
    private FelhasznaltAlkatreszDao felhasznaltAlkatreszDao = new FelhasznaltAlkatreszDao(EntityManagerCreator.getEntityManager());


    private List<FelhasznaltAlkatresz> kitorlendoFelhasznaltAlkatreszek = new ArrayList<>();
    private List<Javitas> kitorlendoJavitasok = new ArrayList<>();
    @FXML private TableView javitasokTV;
    @FXML private TextArea leirasTA;
    @FXML private TextField munkaorakSzamaTF;
    @FXML private TextField javitasGaranciaIdotartamaTF;
    @FXML private TextField fixArTF;

//    @FXML private TableView felhasznaltAlkatreszekTV;
    @FXML private TextField nevTF;
    @FXML private TextField arTF;
    @FXML private TextField felhasznaltAlkatreszgaranciaIdotartamaTF;
    @FXML private TextField cikkszamTF;


    @FXML private TableView<AlkatreszNezet> alkatreszekTV;

    @FXML private TableView<JavitasTipusNezet> javitasTipusokTV;

    //private TableManager<FelhasznaltAlkatreszekNezet> felahasznaltAlkatreszekTM;
    //private TableManager<JavitasokNezet> javitasokTM ;
    private TableManager<JavitasTipusNezet> javitasTipusTM;
    private TableManager<AlkatreszNezet> alkatreszNezetTM;

    private OradijasJavitasTipus kivalasztottJavitasTipus;
    private Alkatresz kivalasztottAlkatresz;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.felahasznaltAlkatreszekTM = new TableManagerImpl<>(this.felhasznaltAlkatreszekTV);
        this.javitasokTM = new TableManagerImpl<>(this.javitasokTV);
        this.javitasTipusTM = new TableManagerImpl<>(this.javitasTipusokTV);
        this.alkatreszNezetTM = new TableManagerImpl<>(this.alkatreszekTV);

    }

/*
    @Override
    public void initData(Object o) {
        Szereles szereles = (Szereles)o;
        //Hibernate.initialize(szereles);

        this.szereles = szereles;

        //this.javitasokTM.setEntitasok(JavitasokNezet.of(this.javitasDao.findAll(this.szereles.getJavitasokIdk())));
        this.javitasokTM.setEntitasok(JavitasokNezet.of(this.szereles.getJavitasok()));

    }

*/
    /*private OradijasJavitasTipus ujOradijasJavitasTipusMentese(){
        OradijasJavitasTipus oradijasJavitasTipus =
                new OradijasJavitasTipus(this.leirasTA.getText(),Integer.parseInt(this.javitasGaranciaIdotartamaTF.getText()));
        oradijasJavitasTipusDao.persist(oradijasJavitasTipus);
        return oradijasJavitasTipus;

    }*/



// Javítás hozzáadása

    public void javitastHozzaadPushed(){

        if(this.kivalasztottJavitasTipus!=null){
         this.javitastHozzaad();
        }else{
            this.nincsKivalasztottjavitasTipusFigyelmeztetes();
        }

    }

    private void javitastHozzaad(){

        if(this.kivalasztottJavitasTipus instanceof FixAruJavitasTipus){
            FixAruJavitas fixaruJavitas =
                    this.ujFixaruJavitasMentese((FixAruJavitasTipus)this.kivalasztottJavitasTipus);
            this.javitasokTM.addEntity(JavitasokNezet.of(fixaruJavitas));
            this.szereles.getJavitasok().add(fixaruJavitas);

            // kivett update
            //this.szerelesDao.update(this.szereles);

        }
        else if(this.kivalasztottJavitasTipus instanceof OradijasJavitasTipus){
            OradijasJavitas oradijasJavitas= this.ujOradijasJavitasMentese(this.kivalasztottJavitasTipus);
            this.szereles.getJavitasok().add(oradijasJavitas);
            this.javitasokTM.addEntity(JavitasokNezet.of(oradijasJavitas));
        }


    }

    private OradijasJavitas ujOradijasJavitasMentese(OradijasJavitasTipus oradijasJavitasTipus){

        OradijasJavitas oradijasJavitas =
                new OradijasJavitas(this.szereles,oradijasJavitasTipus,Integer.parseInt(this.munkaorakSzamaTF.getText()));

        //kivett persist
        //this.javitasDao.persist(oradijasJavitas);
        return oradijasJavitas;
    }


    private FixAruJavitas ujFixaruJavitasMentese(FixAruJavitasTipus kivalasztottJavitasTipus) {

        FixAruJavitas fixAruJavitas =
                new FixAruJavitas(this.szereles,kivalasztottJavitasTipus);

        //kivett persist
        //this.javitasDao.persist(fixAruJavitas);
        return fixAruJavitas;

    }

    private void nincsKivalasztottjavitasTipusFigyelmeztetes() {
    }


    // Új alkatrész mentése

/*
    public void alkatresztHozzaadPushed(){

        Alkatresz alkatresz = ujAlkatreszMentese();
        Javitas javitas = this.javitasokTM.getSelectedEntity().getJavitas();
        FelhasznaltAlkatresz felhasznaltAlkatresz = this.felhasznaltAlkatresztHozzaad(alkatresz,javitas);
        this.felahasznaltAlkatreszekTM.addEntity(new FelhasznaltAlkatreszekNezet(felhasznaltAlkatresz));
        javitas.getFelhasznaltAlkatreszek().add(felhasznaltAlkatresz);


    }*/

    public void alkatresztHozzaadPushed() {

        Alkatresz alkatresz =this.kivalasztottAlkatresz; //= ujAlkatreszMentese();
        Javitas javitas = this.javitasokTM.getSelectedEntity().getJavitas();
        FelhasznaltAlkatresz felhasznaltAlkatresz = this.felhasznaltAlkatresztHozzaad(alkatresz,javitas);
        this.felahasznaltAlkatreszekTM.addEntity(new FelhasznaltAlkatreszekNezet(felhasznaltAlkatresz));
        javitas.getFelhasznaltAlkatreszek().add(felhasznaltAlkatresz);


    }

    public Alkatresz ujAlkatreszMentese(){

        Alkatresz alkatresz = new Alkatresz(this.nevTF.getText(), Integer.parseInt(this.arTF.getText()),
                Integer.parseInt(this.felhasznaltAlkatreszgaranciaIdotartamaTF.getText()));

        //kivett persist
        //this.alkatreszDao.persist(alkatresz);
        return alkatresz;
    }

    public FelhasznaltAlkatresz felhasznaltAlkatresztHozzaad(Alkatresz alkatresz, Javitas javitas){

        FelhasznaltAlkatresz felhasznaltAlkatresz = new FelhasznaltAlkatresz(alkatresz,javitas);

        return felhasznaltAlkatresz;
    }

// A kiválasztott javítás felhasznált alkatrészeinek megjelenítése.

  /*  public void felhasznaltAlkatreszeinekMegjelenitesePushed(){

        //Javitas javitas = this.javitasDao.getById(this.javitasokTM.getSelectedEntity().getId());
        Javitas javitas = this.javitasokTM.getSelectedEntity().getJavitas();
        Logger.info(javitas.getId());
        Logger.info(javitas.getFelhasznaltAlkatreszek().size());
        this.felahasznaltAlkatreszekTM.setEntitasok(FelhasznaltAlkatreszekNezet.of(javitas.getFelhasznaltAlkatreszek()));

    }
*/

    // A kiválaszott javítás törlése
    public void  javitasTorlesePushed(){

        //Javitas javitas =  this.javitasDao.getById(this.javitasokTM.getSelectedEntity().getId());

        Javitas javitas = this.javitasokTM.getSelectedEntity().getJavitas();
        this.szereles.getJavitasok().remove(javitas);
        Logger.info(szereles.getJavitasok());
        //kivett remove
        //this.javitasDao.remove(javitas);

        // kivett update
        //this.szerelesDao.update(szereles);
        if(javitas.getId()!=null){

            this.kitorlendoJavitasok.add(javitas);

        }

        this.javitasokTM.removeSelectedEntity();
        this.javitasokTM.rerfreshTable();
        this.felahasznaltAlkatreszekTM.removeAll();
    }

    // Alkatrész törlése
    public void alkatreszTorlesePushed(){
        Logger.info("alkatresz torlese");

        Logger.info(this.felahasznaltAlkatreszekTM.getSelectedEntity().getId());
        //Logger.info("az alkatresz:" +this.alkatreszDao.getById(this.felahasznaltAlkatreszekTM.getSelectedEntity().getId()));

        //FelhasznaltAlkatresz felhasznaltAlkatresz = this.felhasznaltAlkatreszDao.getById(this.felahasznaltAlkatreszekTM.getSelectedEntity().getId());

        FelhasznaltAlkatresz felhasznaltAlkatresz = this.felahasznaltAlkatreszekTM.getSelectedEntity().getFelhasznaltAlkatresz();

        Javitas javitas = felhasznaltAlkatresz.getJavitas();

        //this.szereles.getJavitasok().remove(javitas);
        javitas.getFelhasznaltAlkatreszek().remove(felhasznaltAlkatresz);
        //this.szereles.getJavitasok().add(javitas);

        //kivett remove
        //this.felhasznaltAlkatreszDao.remove(felhasznaltAlkatresz);

        if(felhasznaltAlkatresz.getId()!=null){
            this.kitorlendoFelhasznaltAlkatreszek.add(felhasznaltAlkatresz);
            //this.felhasznaltAlkatreszDao.remove(this.felhasznaltAlkatreszDao.getById(felhasznaltAlkatresz.getId()));
        }

        this.felahasznaltAlkatreszekTM.removeSelectedEntity();
        this.felahasznaltAlkatreszekTM.rerfreshTable();

        //kivett update
        //javitasDao.update(javitas);
    }

    // Javítás keresése
    public void javitasTipustKeresPushed(){
        JavitasTipusFilter javitasTipusFilter = this.javitasTipusFilterLetrehozasa();


        List javitasTipusok;

        if(javitasTipusFilter.getFixar()==null) {
                        javitasTipusok = this.javitasTipusDao.findOradijasJavitasTipus(javitasTipusFilter);
        }else{
            javitasTipusok = this.javitasTipusDao.findFixaruJavitasTipusok(javitasTipusFilter);

        }

        //javitasTipusok.addAll(javitasTipusok);
        Logger.info(JavitasTipusNezet.of(javitasTipusok));
        this.javitasTipusTM.setEntitasok(JavitasTipusNezet.of(javitasTipusok));

    }

    public JavitasTipusFilter javitasTipusFilterLetrehozasa(){

        JavitasTipusFilter javitasTipusFilter = new JavitasTipusFilter();
        javitasTipusFilter.setId(null);
        javitasTipusFilter.setLeiras(this.leirasTA.getText());
        String fixar = this.fixArTF.getText();
        String garanciaIdotartama = this.javitasGaranciaIdotartamaTF.getText();
        Logger.info(fixar + "  -----  " + garanciaIdotartama);
        if(!fixar.equals("")){
            javitasTipusFilter.setFixar(Integer.parseInt(fixar));
        }

        if(!garanciaIdotartama.equals("")){
            javitasTipusFilter.setGaranciIdotartama(Integer.parseInt(garanciaIdotartama));
        }

        return javitasTipusFilter;
    }

    public void javitasTipustValaszt(){

        OradijasJavitasTipus oradijasJavitasTipus =  this.javitasTipusDao.getById(this.javitasTipusTM.getSelectedEntity().getId());
        this.leirasTA.setText(oradijasJavitasTipus.getLeiras());
        if(oradijasJavitasTipus instanceof FixAruJavitasTipus){
            this.fixArTF.setText(((FixAruJavitasTipus) oradijasJavitasTipus).getAr().toString());
        }
        this.javitasGaranciaIdotartamaTF.setText(oradijasJavitasTipus.getGaranciaIdotartama()!=null ?
                oradijasJavitasTipus.getGaranciaIdotartama().toString(): "");

        this.kivalasztottJavitasTipus = oradijasJavitasTipus;
    }

    public void modositasokMentesePushed(){

        Logger.info(szereles.getJavitasok());
        this.szerelesDao.update(szereles);

        this.javitasDao.removeAll(this.kitorlendoJavitasok.stream().map(j->j.getId()).collect(Collectors.toList()));

        this.felhasznaltAlkatreszDao.removeAll(this.kitorlendoFelhasznaltAlkatreszek.stream().map(j->j.getId()).collect(Collectors.toList()));

        Logger.info("javitas excetion");
        this.javitasDao.remove(new OradijasJavitas());


    }

    public void szerelestLezarPushed(){

  /*      this.szereles.setSzerelesVege(new Timestamp(System.currentTimeMillis()));
        this.szerelesDao.update(szereles);
*/

        //this.szereles = this.szerelesDao.getById(this.szereles.getId());
        //Hibernate.initialize(szereles);

        Logger.info(szereles);

       // Hibernate.initialize(szereles.getJavitasok());
//  Logger.info(szereles.getJavitasok().get(1).getFelhasznaltAlkatreszek().get(2).getCikkszam());
  //Logger.info(szereles.aratSzamol());
    szereles.aratSzamol();
 //   szereles.setSzerelesVege(new Timestamp(System.currentTimeMillis()));
 //   szerelesDao.update(szereles);
    Logger.info(szereles.getAr());
    this.szereles.setSzerelesVege(new Timestamp(System.currentTimeMillis()));
    this.szereles.setAr(this.szereles.aratSzamol());
    this.szerelesDao.update(this.szereles);
    }



    //------------------------------------------------------------------------------------------------------------

    public void alkatresztKeresPush(){

        AlkatreszFilter alkatreszFilter = this.alkatreszFiltertLetrehoz();

        alkatreszNezetTM.setEntitasok( AlkatreszNezet.of(this.alkatreszDao.find(alkatreszFilter)));

    }


    private AlkatreszFilter alkatreszFiltertLetrehoz(){

        String nev = this.nevTF.getText();
        String garanciaIdotartama = this.felhasznaltAlkatreszgaranciaIdotartamaTF.getText();
        String ar = this.arTF.getText();

        return new AlkatreszFilter(nev,ar,garanciaIdotartama,this.cikkszamTF.getText());

    }

    public void ujAlkatreszPushed(){

        Alkatresz alkatresz = this.alkatresztLetrehoz();
        Javitas javitas = this.javitasokTM.getSelectedEntity().getJavitas();
        FelhasznaltAlkatresz felhasznaltAlkatresz = this.felhasznaltAlkatresztHozzaad(alkatresz,javitas);
        this.felahasznaltAlkatreszekTM.addEntity(new FelhasznaltAlkatreszekNezet(felhasznaltAlkatresz));
        javitas.getFelhasznaltAlkatreszek().add(felhasznaltAlkatresz);

    }

    private Alkatresz alkatresztLetrehoz() {
        return new Alkatresz(this.nevTF.getText(),Integer.parseInt(this.arTF.getText()),
                Integer.parseInt(this.felhasznaltAlkatreszgaranciaIdotartamaTF.getText()),Integer.parseInt(this.cikkszamTF.getText()));
    }

    public void alkatresztKivalasztPushed(){


        Alkatresz alkatresz = this.alkatreszNezetTM.getSelectedEntity().getAlkatresz();
        this.kivalasztottAlkatresz = alkatresz;
        this.nevTF.setText(alkatresz.getNev());
        this.felhasznaltAlkatreszgaranciaIdotartamaTF.setText(alkatresz.getGaranciaIdotartama().toString());
        this.arTF.setText(alkatresz.getAr().toString());


    }
}
