package au.com.wow.codetestapp.dataprovider;

import android.content.Context;

import au.com.wow.codetestapp.BuildConfig;

/**
 * @FileName DataProvider.java
 * @Purpose Factory class to create a dataprovider.
 * @RevisionHistory Created
 */
public class DataProvider {

    /**
     * Return the default data provider based on build configuration
     * @param context
     * @return  data provider
     */
    public static AbstractDataProvider getDefaultDataProvider(Context context) {
        if (BuildConfig.USE_MOCK_DATA) {
            return new MockDataProvider(context);
        } else {
            return new NetworkDataProvider(context);
        }
    }
}
