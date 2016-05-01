package com.leaf.clips.model.navigator.graph.edge;

/**
 * @author Oscar Elia Conti
 * @version 0.01
 * @since 0.00
 */

import com.leaf.clips.model.navigator.graph.area.RegionOfInterest;
import com.leaf.clips.model.navigator.graph.navigationinformation.NavigationInformation;
import com.leaf.clips.model.navigator.graph.navigationinformation.PhotoInformation;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 *Classe astratta che rappresenta l'implementazione di base utilizzata per gli archi del grafo
 */
public abstract class AbsEnrichedEdge extends DefaultWeightedEdge implements EnrichedEdge {

    /**
     * Angolo rispetto al Nord polare tra il punto di inizio dell'arco e il punto finale dell'arco
     */
    private final int coordinate;

    /**
     * Lunghezza dell'arco
     */
    private final double distance;

    /**
     * Punto di arrivo dell'arco
     */
    private final RegionOfInterest endROI;

    /**
     * Identificativo numerico di un arco
     */
    private final int id;

    /**
     * Informazioni associate ad un arco per il superamento dello stesso
     */
    private final NavigationInformation navInfo;

    /**
     * Punto di partenza dell'arco
     */
    private final RegionOfInterest startROI;

    /**
     * Preferenze dell'utente rispetto agli archi che prevedono un ascensore
     */
    protected int userElevatorPreference;

    /**
     * Preferenze dell'utente rispetto agli archi che prevedono delle scale
     */
    protected int userStairPreference;

    /**
     * Costruttore della classe AbsEnrichedEdge
     * @param startROI Punto di partenza dell'arco
     * @param endROI Punto di arrivo dell'arco
     * @param distance Lunghezza dell'arco
     * @param coordinate Angolo rispetto al Nord polare tra il punto di partenza dell'arco e il punto di arrivo dell'arco
     * @param id Identificativo numerico dell'arco
     * @param navInfo Informazioni associate ad un arco per il superamento dello stesso
     */
    public AbsEnrichedEdge(RegionOfInterest startROI, RegionOfInterest endROI, double distance, int coordinate, int id, NavigationInformation navInfo){
        this.startROI = startROI;
        this.endROI = endROI;
        this.distance = distance;
        this.coordinate = coordinate;
        this.id = id;
        this.navInfo = navInfo;
        this.userElevatorPreference = 0;
        this.userStairPreference = 0;
    }

    /**
     * Metodo astratto che ritorna le informazioni di base associate all'arco
     * @return  String
     */
    @Override
    public abstract String getBasicInformation();

    /**
     * Metodo che ritorna le coordinate di un arco
     * @return  int
     */
    @Override
    public int getCoordinate(){
        return 0;
    }

    /**
     * Metodo astratto che ritorna le informazioni di base associate all'arco
     * @return  String
     */
    @Override
    public abstract String getDetailedInformation();

    /**
     * Metodo che ritorna la lunghezza di un arco
     * @return  double
     */
    @Override
    public double getDistance(){
        return 0;
    }

    /**
     * Metodo che ritorna la RegionOfInterest di arrivo dell'arco
     * @return  RegionOfInterest
     */
    @Override
    public RegionOfInterest getEndPoint(){
        return null;
    }

    /**
     * Metodo che ritorna l'identificativo numerico associato all'oggetto AbsEnrichedEdge
     * @return  int
     */
    @Override
    public int getId(){
        return 0;
    }

    /**
     * Metodo che ritorna le informazioni di navigazione associate ad un arco
     * @return  NavigationInformation
     */
    protected NavigationInformation getNavigationInformation(){
        return null;
    }

    /**
     * Metodo che ritorna la un oggetto PhotoInformation contente un riferimento alle fotografie associate all'arco
     * @return  PhotoInformation
     */
    @Override
    public PhotoInformation getPhotoInformation(){
        return null;
    }

    /**
     * Metodo che ritorna la RegionOfInterest di partenza dell'arco
     * @return  RegionOfInterest
     */
    @Override
    public RegionOfInterest getStarterPoint(){
        return null;
    }

    /**
     * Metodo astratto che ritorna il peso dell'arco
     * @return  double
     */

    @Override
    public abstract double getWeight();


    // TODO: 5/1/16 Pullare le userSettings 
    /**
     * Metodo che permette di impostare le preferenze di un utente per il calcolo del peso dell'arco
     * @param setting Preferenze da impostare
     * @return  void
     */
    //public static void setUserPreference(Setting setting);

}