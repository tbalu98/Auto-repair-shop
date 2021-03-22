package controllers;

import daos.*;
import entities.*;
import filters.PartFilter;
import filters.AssemblyTypeFilter;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import representations.PartRepresentation;
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

/**
 * This is the controller of the scene where the user can edit the repair of the car.
 * This class is responsible for fetching and displaying the data of the chosen repair
 * so it extends the @link ViewRepairs class.
 * This class is also responsible for presenting the UI for the user to edit the repair like
 * adding new assemblies and parts, deleting, updating and finishing the edition of the repair.
 */


public class EditRepairs extends ViewRepairs {


    /**
     * These are the daos that are used for fetching, deleting and updating data from the database.
     */
    private RepairDao repairDao = new RepairDao(EntityManagerCreator.getEntityManager());
    private HourlyRateAssemblyDao hourlyRateAssemblyDao = new HourlyRateAssemblyDao(EntityManagerCreator.getEntityManager());
    private AssemblyDao assemblyDao = new AssemblyDao(EntityManagerCreator.getEntityManager());
    private AssemblyTypeDao assemblyTypeDao = new AssemblyTypeDao(EntityManagerCreator.getEntityManager());
    private PartDao partDao = new PartDao(EntityManagerCreator.getEntityManager());
    private UsedPartsDao usedPartsDao = new UsedPartsDao(EntityManagerCreator.getEntityManager());


    /**
     * These lists contain the parts and assemblies that the user wants to delete
     */
    private List<UsedPart> usedPartsToBeDeleted = new ArrayList<>();
    private List<Assembly> assembliesToBeDeleted = new ArrayList<>();


    @FXML private TableView assembliesTV;
    @FXML private TextArea descriptionTA;
    @FXML private TextField numOfWorkingHoursTF;
    @FXML private TextField guaranteeOfAssemblyTF;
    @FXML private TextField fixedPriceTF;

//    @FXML private TableView felhasznaltAlkatreszekTV;
    @FXML private TextField nameTF;
    @FXML private TextField priceTF;
    @FXML private TextField guaranteeOfUsedPartTF;
    @FXML private TextField articleNumTF;


    @FXML private TableView<PartRepresentation> partsTV;

    @FXML private TableView<AssemblyTypesRepresentation> assemblyTypesRepTV;

    private TableManager<AssemblyTypesRepresentation> assemblyTypeRepTM;
    private TableManager<PartRepresentation> partRepTM;

    private HourlyPricedAssemblyType chosenAssemblyType;
    private Part chosenPart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.usedPartsRepTM = new TableManagerImpl<>(this.usedPartsTV);
        this.assembliesRepTM = new TableManagerImpl<>(this.assembliesTV);
        this.assemblyTypeRepTM = new TableManagerImpl<>(this.assemblyTypesRepTV);
        this.partRepTM = new TableManagerImpl<>(this.partsTV);

    }


    public void addAssemblyPushed(){

        if(this.chosenAssemblyType !=null){
         this.addAssembly();
        }else{
            this.noChosenAssemblyTypeWarning();
        }

    }

    private void addAssembly(){

        if(this.chosenAssemblyType instanceof FixPricedAssemblyType){
            FixPricedAssembly fixaruJavitas =
                    this.saveNewFixPricedRepair((FixPricedAssemblyType)this.chosenAssemblyType);
            this.assembliesRepTM.addEntity(AssembliesRepresentation.of(fixaruJavitas));
            this.repair.getAssemblies().add(fixaruJavitas);



        }
        else if(this.chosenAssemblyType instanceof HourlyPricedAssemblyType){
            HourlyPricedAssembly hourlyPricedAssembly = this.saveNewHourlyRatedAssembly(this.chosenAssemblyType);
            this.repair.getAssemblies().add(hourlyPricedAssembly);
            this.assembliesRepTM.addEntity(AssembliesRepresentation.of(hourlyPricedAssembly));
        }


    }

    private HourlyPricedAssembly saveNewHourlyRatedAssembly(HourlyPricedAssemblyType hourlyPricedAssemblyType){

        HourlyPricedAssembly hourlyPricedAssembly =
                new HourlyPricedAssembly(this.repair, hourlyPricedAssemblyType,Integer.parseInt(this.numOfWorkingHoursTF.getText()));

        return hourlyPricedAssembly;
    }


    private FixPricedAssembly saveNewFixPricedRepair(FixPricedAssemblyType chosenAssemblyType) {

        FixPricedAssembly fixPricedAssembly =
                new FixPricedAssembly(this.repair,chosenAssemblyType);

        return fixPricedAssembly;

    }

    private void noChosenAssemblyTypeWarning() {

    }



    public void addPartPushed() {

        Part part =this.chosenPart; //= ujAlkatreszMentese();
        Assembly assembly = this.assembliesRepTM.getSelectedEntity().getAssembly();
        UsedPart usedPart = this.createUsedPart(part, assembly);
        this.usedPartsRepTM.addEntity(new UsedPartsRepresentation(usedPart));
        assembly.getUsedParts().add(usedPart);


    }

   // public Part ujAlkatreszMentese(){

       // Part part = new Part(this.nameTF.getText(), Integer.parseInt(this.priceTF.getText()),
      //          Integer.parseInt(this.guaranteeOfUsedPartTF.getText()));

        //kivett persist
        //this.alkatreszDao.persist(alkatresz);
   //     return part;
    //}

    public UsedPart createUsedPart(Part part, Assembly assembly){

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
    public void deleteAssemblyPushed(){

        //Javitas javitas =  this.javitasDao.getById(this.javitasokTM.getSelectedEntity().getId());

        Assembly assembly = this.assembliesRepTM.getSelectedEntity().getAssembly();
        this.repair.getAssemblies().remove(assembly);
        Logger.info(repair.getAssemblies());
        //kivett remove
        //this.javitasDao.remove(javitas);

        // kivett update
        //this.szerelesDao.update(szereles);
        if(assembly.getId()!=null){

            this.assembliesToBeDeleted.add(assembly);

        }

        this.assembliesRepTM.removeSelectedEntity();
        this.assembliesRepTM.rerfreshTable();
        this.usedPartsRepTM.removeAll();
    }

    // Alkatrész törlése
    public void deletePartPushed(){
        Logger.info("alkatresz torlese");

        Logger.info(this.usedPartsRepTM.getSelectedEntity().getId());
        //Logger.info("az alkatresz:" +this.alkatreszDao.getById(this.felahasznaltAlkatreszekTM.getSelectedEntity().getId()));

        //FelhasznaltAlkatresz felhasznaltAlkatresz = this.felhasznaltAlkatreszDao.getById(this.felahasznaltAlkatreszekTM.getSelectedEntity().getId());

        UsedPart usedPart = this.usedPartsRepTM.getSelectedEntity().getUsedPart();

        Assembly assembly = usedPart.getAssembly();

        //this.szereles.getJavitasok().remove(javitas);
        assembly.getUsedParts().remove(usedPart);
        //this.szereles.getJavitasok().add(javitas);

        //kivett remove
        //this.felhasznaltAlkatreszDao.remove(felhasznaltAlkatresz);

        if(usedPart.getId()!=null){
            this.usedPartsToBeDeleted.add(usedPart);
            //this.felhasznaltAlkatreszDao.remove(this.felhasznaltAlkatreszDao.getById(felhasznaltAlkatresz.getId()));
        }

        this.usedPartsRepTM.removeSelectedEntity();
        this.usedPartsRepTM.rerfreshTable();

        //kivett update
        //javitasDao.update(javitas);
    }

    // Javítás keresése
    public void findAssemblyTypePushed(){
        AssemblyTypeFilter assemblyTypeFilter = this.createAssemblyTypeFilter();


        List javitasTipusok;

        if(assemblyTypeFilter.getFixar()==null) {
                        javitasTipusok = this.assemblyTypeDao.findOradijasJavitasTipus(assemblyTypeFilter);
        }else{
            javitasTipusok = this.assemblyTypeDao.findFixaruJavitasTipusok(assemblyTypeFilter);

        }

        //javitasTipusok.addAll(javitasTipusok);
        Logger.info(AssemblyTypesRepresentation.of(javitasTipusok));
        this.assemblyTypeRepTM.setEntitasok(AssemblyTypesRepresentation.of(javitasTipusok));

    }

    public AssemblyTypeFilter createAssemblyTypeFilter(){

        AssemblyTypeFilter assemblyTypeFilter = new AssemblyTypeFilter();
        assemblyTypeFilter.setId(null);
        assemblyTypeFilter.setLeiras(this.descriptionTA.getText());
        String fixar = this.fixedPriceTF.getText();
        String garanciaIdotartama = this.guaranteeOfAssemblyTF.getText();
        Logger.info(fixar + "  -----  " + garanciaIdotartama);
        if(!fixar.equals("")){
            assemblyTypeFilter.setFixar(Integer.parseInt(fixar));
        }

        if(!garanciaIdotartama.equals("")){
            assemblyTypeFilter.setGaranciIdotartama(Integer.parseInt(garanciaIdotartama));
        }

        return assemblyTypeFilter;
    }

    public void choseAssemblyType(){

        HourlyPricedAssemblyType hourlyPricedAssemblyType =  this.assemblyTypeDao.getById(this.assemblyTypeRepTM.getSelectedEntity().getId());
        this.descriptionTA.setText(hourlyPricedAssemblyType.getDecription());
        if(hourlyPricedAssemblyType instanceof FixPricedAssemblyType){
            this.fixedPriceTF.setText(((FixPricedAssemblyType) hourlyPricedAssemblyType).getPrice().toString());
        }
        this.guaranteeOfAssemblyTF.setText(hourlyPricedAssemblyType.getGuarantee()!=null ?
                hourlyPricedAssemblyType.getGuarantee().toString(): "");

        this.chosenAssemblyType = hourlyPricedAssemblyType;
    }

    public void savePushed(){

        Logger.info(repair.getAssemblies());
        this.repairDao.update(repair);

        this.assemblyDao.removeAll(this.assembliesToBeDeleted.stream().map(j->j.getId()).collect(Collectors.toList()));

        this.usedPartsDao.removeAll(this.usedPartsToBeDeleted.stream().map(j->j.getId()).collect(Collectors.toList()));

        Logger.info("javitas excetion");
        this.assemblyDao.remove(new HourlyPricedAssembly());


    }

    public void finishRepairPushed(){

  /*      this.szereles.setSzerelesVege(new Timestamp(System.currentTimeMillis()));
        this.szerelesDao.update(szereles);
*/

        //this.szereles = this.szerelesDao.getById(this.szereles.getId());
        //Hibernate.initialize(szereles);

        Logger.info(repair);

       // Hibernate.initialize(szereles.getJavitasok());
//  Logger.info(szereles.getJavitasok().get(1).getFelhasznaltAlkatreszek().get(2).getCikkszam());
  //Logger.info(szereles.aratSzamol());
    repair.computePrice();
 //   szereles.setSzerelesVege(new Timestamp(System.currentTimeMillis()));
 //   szerelesDao.update(szereles);
    Logger.info(repair.getPrice());
    this.repair.setEndOfRepair(new Timestamp(System.currentTimeMillis()));
    this.repair.setPrice(this.repair.computePrice());
    this.repairDao.update(this.repair);
    }



    //------------------------------------------------------------------------------------------------------------

    public void findPartPushed(){

        PartFilter partFilter = this.createPartFilter();

        partRepTM.setEntitasok( PartRepresentation.of(this.partDao.find(partFilter)));

    }


    private PartFilter createPartFilter(){

        String name = this.nameTF.getText();
        String guarantee = this.guaranteeOfUsedPartTF.getText();
        String price = this.priceTF.getText();

        return new PartFilter(name,price,guarantee,this.articleNumTF.getText());

    }

    public void newPartPushed(){

        Part part = this.createPart();
        Assembly assembly = this.assembliesRepTM.getSelectedEntity().getAssembly();
        UsedPart usedPart = this.createUsedPart(part, assembly);
        this.usedPartsRepTM.addEntity(new UsedPartsRepresentation(usedPart));
        assembly.getUsedParts().add(usedPart);

    }

    private Part createPart() {
        return new Part(this.nameTF.getText(),Integer.parseInt(this.priceTF.getText()),
                Integer.parseInt(this.guaranteeOfUsedPartTF.getText()),Integer.parseInt(this.articleNumTF.getText()));
    }

    public void chosePartPushed(){


        Part part = this.partRepTM.getSelectedEntity().getPart();
        this.chosenPart = part;
        this.nameTF.setText(part.getName());
        this.guaranteeOfUsedPartTF.setText(part.getGuarantee().toString());
        this.priceTF.setText(part.getPrice().toString());


    }
}
