package au.com.wow.codetestapp.dataprovider;

import android.content.Context;

/**
 * @FileName AbstractDataProvider.java
 * @Purpose Abstract class on which other concrete classes will be derived inorder to retrieve fuel station list from
 * required source (say sdcard file, assets or webservice)
 * @RevisionHistory Created
 */
public abstract class AbstractDataProvider {

    protected Context context;

    /**
     * Constructor for class with listener for callbacks.
     * The listener is instance of IDataFetchListener to give callbacks on data fetch (success and error)
     */
    public AbstractDataProvider(Context context) {
        this.context = context;
    }

    /**
     * Abstract method to be implemented in the derived class to add data fetch logic.
     * The listener IDataFetchListener has has to be handled here to give callbacks on data fetch
     * success or on error
     */
    public abstract void fetchFuelStationList(IDataFetchListener listener);
}
