package au.com.wow.codetestapp.dataprovider;

import java.util.List;

import au.com.wow.codetestapp.response.FuelStationItem;

/**
 *
 */

/**
 * @FileName MockDataProvider.java
 * @Purpose  Interface for sharing data as callbacks in listener.
 *           The listener give callback for success and error scenarios.
 * @RevisionHistory Created
 */

public interface IDataFetchListener {

    /**
     * Callback on data retrieve success. The method shares the wordList from the data source.
     *
     * @param fuelStationItems List of fuel station data from data source
     */
    void onSuccess(List<FuelStationItem> fuelStationItems);

    /**
     * Callback on data retrieve failure. The method shares the error message if any error occurs
     *
     * @param errorMessage error message if any
     */
    void onError(String errorMessage);

}
