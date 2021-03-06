package com.leaf.clips.model.dataaccess.dao;
/**
 * @author Marco Zanella
 * @version 0.01
 * @since 0.01
 *
 *
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 *Classe di utility per la conversione da JSON a EdgeTypeTable
 */
public class RemoteEdgeTypeDao {

    /**
     * Costruttore di default per la classe RemoteEdgeTypeDao
     */
    public RemoteEdgeTypeDao(){}

    /**
     * Metodo utilizzato per la conversione di un oggetto JsonObject in un oggetto EdgeTypeTable, che viene ritornato
     * @param object Oggetto JSON che rappresenta un oggetto di tipo EdgeTypeTable
     * @return  EdgeTypeTable
     */
    public EdgeTypeTable fromJSONToTable(JsonObject object) {
        Gson gson = new Gson();
        EdgeTypeTable edgeTypeTable;
        edgeTypeTable = gson.fromJson(object, EdgeTypeTable.class);
        return edgeTypeTable;
    }

}
