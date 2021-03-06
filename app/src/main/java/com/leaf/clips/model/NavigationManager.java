package com.leaf.clips.model;
/**
 * @author Federico Tavella
 * @version 0.04
 * @since 0.00
 *
 *
 */

import com.leaf.clips.model.navigator.NavigationExceptions;
import com.leaf.clips.model.navigator.NoNavigationInformationException;
import com.leaf.clips.model.navigator.ProcessedInformation;
import com.leaf.clips.model.navigator.graph.MapGraph;
import com.leaf.clips.model.navigator.graph.area.PointOfInterest;

import java.util.List;

/**
 *Interfaccia che si occupa di esporre tutti i metodi utili alla navigazione
 */

public interface NavigationManager {

    /**
     * Metodo che permette di registrare un listener
     * @param listener Listener che deve essere aggiunto alla lista di NavigationListener
     */
    void addListener(NavigationListener listener);

    /**
     * Metodo che permette di recuperare tutte le istruzioni di navigazione per un percorso
     * calcolato. Viene lanciata una eccezione di tipo NoNavigationInformationException nel caso
     * in cui venga richiamato questo metodo senza aver prima avviato la navigazione
     * @return  List<ProcessedInformation>
     */
    List<ProcessedInformation> getAllNavigationInstruction() throws NavigationExceptions;

    /**
     * Metodo che permette di recuperare tutte le istruzioni di navigazione per un percorso calcolato
     * in base al beacon più potente ricavato dalla PriorityQueue<MyBeacon> passata come argomento.
     * Viene lanciata una eccezione di tipo NoNavigationInformationException nel caso in cui venga
     * richiamato questo metodo senza aver prima avviato la navigazione.
     * @return  ProcessedInformation
     */
    ProcessedInformation getNextInstruction() throws NoNavigationInformationException;

    /**
     * Metodo che permette di rimuovere un listener
     * @param listener Listener che deve essere rimosso dalla lista di NavigationListener
     */
    void removeListener(NavigationListener listener);

    /**
     * Metodo che permette di attivare il rilevamento dei dati dalla bussola
     */
    void startCompass();

    /**
     * Metodo che permette di avviare la navigazione verso uno specifico POI
     * @param endPOI POI da raggiungere tramite navigazione
     * @return  ProcessedInformation
     */
    ProcessedInformation startNavigation(PointOfInterest endPOI) throws NavigationExceptions, NoBeaconSeenException;

    /**
     * Metodo che permette di fermare il rilevamento dei dati ottenuti dalla bussola
     */
    void stopCompass();

    /**
     * Metodo che permette di fermare la navigazione
     */
    void stopNavigation();

    /**
     * Metodo che permette di accedere al grafo che rappresenta l'edificio
     * @return MapGraph
     */
    MapGraph getGraph();

}

