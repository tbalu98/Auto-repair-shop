package controllers;

import daos.*;
import entities.*;
import filters.PartFilter;
import filters.AssemblyTypeFilter;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import representations.UsedPartsRepresentation;
import representations.AssemblyTypesRepresentation;
import representations.AssembliesRepresentation;
import org.pmw.tinylog.Logger;
import utils.TableManagerImpl;
import utils.TableManager;


import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditRepairs extends ViewRepairs {



    private RepairDao repairDao = new RepairDao(EntityManagerCreator.getEntityManager());

    private HourlyRateAssemblyDao hourlyRateAssemblyDao = new HourlyRateAssemblyDao(EntityManagerCreator.getEntityManager());
    private AssemblyDao assemblyDao = new AssemblyDao(EntityManagerCreator.getEntityManager());
    private AssemblyTypeDao assemblyTypeDao = new AssemblyTypeDao(EntityManagerCreator.getEntityManager());

    private PartDao partDao = new PartDao(EntityManagerCreator.getEntityManager());
    private UsedPartsDao usedPartsDao = new UsedPartsDao(EntityManagerCreator.getEntityManager());


    private List<UsedPart> kitorlendoFelhasznaltAlkatreszek = new ArrayList<>();
    private List<Assembly> kitorlendoJavitasok = new ArrayList<>();
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


    @FXML private TableView<PartView> alkatreszekTV;

    @FXML private TableView<AssemblyTypesRepresentation> javitasTipusokTV;

    //private TableManager<FelhasznaltAlkatreszekNezet> felahasznaltAlkatreszekTM;
    //private TableManager<JavitasokNezet> javitasokTM ;
    private TableManager<AssemblyTypesRepresentation> javitasTipusTM;
    private TableManager<PartView> alkatreszNezetTM;

    private HourlyPricedAssemblyType kivalasztottJavitasTipus;
    private Part kivalasztottPart;

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

        if(this.kivalasztottJavitasTipus instanceof FixPricedAssemblyType){
            FixPricedAssembly fixaruJavitas =
                    this.ujFixaruJavitasMentese((FixPricedAssemblyType)this.kivalasztottJavitasTipus);
            this.javitasokTM.addEntity(AssembliesRepresentation.of(fixaruJavitas));
            this.repair.getJavitasok().add(fixaruJavitas);

            // kivett update
            //this.szerelesDao.update(this.szereles);

        }
        else if(this.kivalasztottJavitasTipus instanceof HourlyPricedAssemblyType){
            HourlyPricedAssembly hourlyPricedAssembly = this.ujOradijasJavitasMentese(this.kivalasztottJavitasTipus);
            this.repair.getJavitasok().add(hourlyPricedAssembly);
            this.javitasokTM.addEntity(AssembliesRepresentation.of(hourlyPricedAssembly));
        }


    }

    private HourlyPricedAssembly ujOradijasJavitasMentese(HourlyPricedAssemblyType hourlyPricedAssemblyType){

        HourlyPricedAssembly hourlyPricedAssembly =
                new HourlyPricedAssembly(this.repair, hourlyPricedAssemblyType,Integer.parseInt(this.munkaorakSzamaTF.getText()));

        //kivett persist
        //this.javitasDao.persist(oradijasJavitas);
        return hourlyPricedAssembly;
    }


    private FixPricedAssembly ujFixaruJavitasMentese(FixPricedAssemblyType kivalasztottJavitasTipus) {

        FixPricedAssembly fixPricedAssembly =
                new FixPricedAssembly(this.repair,kivalasztottJavitasTipus);

        //kivett persist
        //this.javitasDao.persist(fixAruJavitas);
        return fixPricedAssembly;

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

        Part part =this.kivalasztottPart; //= ujAlkatreszMentese();
        Assembly assembly = this.javitasokTM.getSelectedEntity().getAssembly();
        UsedPart usedPart = this.felhasznaltAlkatresztHozzaad(part, assembly);
        this.felahasznaltAlkatreszekTM.addEntity(new UsedPartsRepresentation(usedPart));
        assembly.getFelhasznaltAlkatreszek().add(usedPart);


    }

    public Part ujAlkatreszMentese(){

        Part part = new Part(this.nevTF.getText(), Integer.parseInt(this.arTF.getText()),
                Integer.parseInt(this.felhasznaltAlkatreszgaranciaIdotartamaTF.getText()));

        //kivett persist
        //this.alkatreszDao.persist(alkatresz);
        return part;
    }

    public UsedPart felhasznaltAlkatresztHozzaad(Part part, Assembly assembly){

        UsedPart usedPart = new UsedPart(part, assembly);

        return usedPart;
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

        Assembly assembly = this.javitasokTM.getSelectedEntity().getAssembly();
        this.repair.getJavitasok().remove(assembly);
        Logger.info(repair.getJavitasok());
        //kivett remove
        //this.javitasDao.remove(javitas);

        // kivett update
        //this.szerelesDao.update(szereles);
        if(assembly.getId()!=null){

            this.kitorlendoJavitasok.add(assembly);

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

        UsedPart usedPart = this.felahasznaltAlkatreszekTM.getSelectedEntity().getUsedPart();

        Assembly assembly = usedPart.getAssembly();

        //this.szereles.getJavitasok().remove(javitas);
        assembly.getFelhasznaltAlkatreszek().remove(usedPart);
        //this.szereles.getJavitasok().add(javitas);

        //kivett remove
        //this.felhasznaltAlkatreszDao.remove(felhasznaltAlkatresz);

        if(usedPart.getId()!=null){
            this.kitorlendoFelhasznaltAlkatreszek.add(usedPart);
            //this.felhasznaltAlkatreszDao.remove(this.felhasznaltAlkatreszDao.getById(felhasznaltAlkatresz.getId()));
        }

        this.felahasznaltAlkatreszekTM.removeSelectedEntity();
        this.felahasznaltAlkatreszekTM.rerfreshTable();

        //kivett update
        //javitasDao.update(javitas);
    }

    // Javítás keresése
    public void javitasTipustKeresPushed(){
        AssemblyTypeFilter assemblyTypeFilter = this.javitasTipusFilterLetrehozasa();


        List javitasTipusok;

        if(assemblyTypeFilter.getFixar()==null) {
                        javitasTipusok = this.assemblyTypeDao.findOradijasJavitasTipus(assemblyTypeFilter);
        }else{
            javitasTipusok = this.assemblyTypeDao.findFixaruJavitasTipusok(assemblyTypeFilter);

        }

        //javitasTipusok.addAll(javitasTipusok);
        Logger.info(AssemblyTypesRepresentation.of(javitasTipusok));
        this.javitasTipusTM.setEntitasok(AssemblyTypesRepresentation.of(javitasTipusok));

    }

    public AssemblyTypeFilter javitasTipusFilterLetrehozasa(){

        AssemblyTypeFilter assemblyTypeFilter = new AssemblyTypeFilter();
        assemblyTypeFilter.setId(null);
        assemblyTypeFilter.setLeiras(this.leirasTA.getText());
        String fixar = this.fixArTF.getText();
        String garanciaIdotartama = this.javitasGaranciaIdotartamaTF.getText();
        Logger.info(fixar + "  -----  " + garanciaIdotartama);
        if(!fixar.equals("")){
            assemblyTypeFilter.setFixar(Integer.parseInt(fixar));
        }

        if(!garanciaIdotartama.equals("")){
            assemblyTypeFilter.setGaranciIdotartama(Integer.parseInt(garanciaIdotartama));
        }

        return assemblyTypeFilter;
    }

    public void javitasTipustValaszt(){

        HourlyPricedAssemblyType hourlyPricedAssemblyType =  this.assemblyTypeDao.getById(this.javitasTipusTM.getSelectedEntity().getId());
        this.leirasTA.setText(hourlyPricedAssemblyType.getLeiras());
        if(hourlyPricedAssemblyType instanceof FixPricedAssemblyType){
            this.fixArTF.setText(((FixPricedAssemblyType) hourlyPricedAssemblyType).getAr().toString());
        }
        this.javitasGaranciaIdotartamaTF.setText(hourlyPricedAssemblyType.getGaranciaIdotartama()!=null ?
                hourlyPricedAssemblyType.getGaranciaIdotartama().toString(): "");

        this.kivalasztottJavitasTipus = hourlyPricedAssemblyType;
    }

    public void modositasokMentesePushed(){

        Logger.info(repair.getJavitasok());
        this.repairDao.update(repair);

        this.assemblyDao.removeAll(this.kitorlendoJavitasok.stream().map(j->j.getId()).collect(Collectors.toList()));

        this.usedPartsDao.removeAll(this.kitorlendoFelhasznaltAlkatreszek.stream().map(j->j.getId()).collect(Collectors.toList()));

        Logger.info("javitas excetion");
        this.assemblyDao.remove(new HourlyPricedAssembly());


    }

    public void szerelestLezarPushed(){

  /*      this.szereles.setSzerelesVege(new Timestamp(System.currentTimeMillis()));
        this.szerelesDao.update(szereles);
*/

        //this.szereles = this.szerelesDao.getById(this.szereles.getId());
        //Hibernate.initialize(szereles);

        Logger.info(repair);

       // Hibernate.initialize(szereles.getJavitasok());
//  Logger.info(szereles.getJavitasok().get(1).getFelhasznaltAlkatreszek().get(2).getCikkszam());
  //Logger.info(szereles.aratSzamol());
    repair.aratSzamol();
 //   szereles.setSzerelesVege(new Timestamp(System.currentTimeMillis()));
 //   szerelesDao.update(szereles);
    Logger.info(repair.getAr());
    this.repair.setSzerelesVege(new Timestamp(System.currentTimeMillis()));
    this.repair.setAr(this.repair.aratSzamol());
    this.repairDao.update(this.repair);
    }



    //------------------------------------------------------------------------------------------------------------

    public void alkatresztKeresPush(){

        PartFilter partFilter = this.alkatreszFiltertLetrehoz();

        alkatreszNezetTM.setEntitasok( PartView.of(this.partDao.find(partFilter)));

    }


    private PartFilter alkatreszFiltertLetrehoz(){

        String nev = this.nevTF.getText();
        String garanciaIdotartama = this.felhasznaltAlkatreszgaranciaIdotartamaTF.getText();
        String ar = this.arTF.getText();

        return new PartFilter(nev,ar,garanciaIdotartama,this.cikkszamTF.getText());

    }

    public void ujAlkatreszPushed(){

        Part part = this.alkatresztLetrehoz();
        Assembly assembly = this.javitasokTM.getSelectedEntity().getAssembly();
        UsedPart usedPart = this.felhasznaltAlkatresztHozzaad(part, assembly);
        this.felahasznaltAlkatreszekTM.addEntity(new UsedPartsRepresentation(usedPart));
        assembly.getFelhasznaltAlkatreszek().add(usedPart);

    }

    private Part alkatresztLetrehoz() {
        return new Part(this.nevTF.getText(),Integer.parseInt(this.arTF.getText()),
                Integer.parseInt(this.felhasznaltAlkatreszgaranciaIdotartamaTF.getText()),Integer.parseInt(this.cikkszamTF.getText()));
    }

    public void alkatresztKivalasztPushed(){


        Part part = this.alkatreszNezetTM.getSelectedEntity().getPart();
        this.kivalasztottPart = part;
        this.nevTF.setText(part.getNev());
        this.felhasznaltAlkatreszgaranciaIdotartamaTF.setText(part.getGaranciaIdotartama().toString());
        this.arTF.setText(part.getAr().toString());


    }
}
