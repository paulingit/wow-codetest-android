package au.com.wow.codetestapp.dataprovider;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import au.com.wow.codetestapp.response.FuelStationItem;
import au.com.wow.codetestapp.response.FuelStationResponse;
import au.com.wow.codetestapp.util.JsonHelperUtils;

/**
 * @FileName MockDataProvider.java
 * @Purpose Data provider class to get the list of fuel stations from the mock data present in a asset file
 * A listener is added for callbacks on data fetch complete or on error scenarios.
 * @RevisionHistory Created
 */

class MockDataProvider extends AbstractDataProvider {

    private static final String FUEL_STATION_ASSET_FILENAME = "fuelstationlist.json";
    private static final String TAG = "MockDataProvider";

    /**
     * Constructor for class with listener for callbacks.
     * The listener is instance of IDataFetchListener to give callbacks on data fetch (success and error)
     */
    public MockDataProvider(Context context) {
        super(context);
    }

    @Override
    public void fetchFuelStationList(IDataFetchListener listener) {
        ReadFromAssetsAsyncTask readFromAssetsAsyncTask = new ReadFromAssetsAsyncTask(context, FUEL_STATION_ASSET_FILENAME, listener);
        readFromAssetsAsyncTask.execute();
    }

    /**
     * Async Task which is responsible for reading from the asset file
     */
    private static class ReadFromAssetsAsyncTask extends AsyncTask<Void, Void, List<FuelStationItem>> {

        private Context context;
        private String fileName;
        private IDataFetchListener iDataFetchListener;

        public ReadFromAssetsAsyncTask(Context context, String fileName, IDataFetchListener iDataFetchListener) {
            this.context = context;
            this.fileName = fileName;
            this.iDataFetchListener = iDataFetchListener;
        }

        @Override
        protected List<FuelStationItem> doInBackground(Void... voids) {

            String response = readAssets(context, fileName);
            if (!TextUtils.isEmpty(response)) {
                FuelStationResponse fuelStationResponse = JsonHelperUtils.deSerialize(FuelStationResponse.class, response);
                List<FuelStationItem> fuelStationItemList = fuelStationResponse.getItem();
                return fuelStationItemList;
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<FuelStationItem> fuelStationItemList) {
            if (fuelStationItemList != null) {
                if (iDataFetchListener != null) {
                    iDataFetchListener.onSuccess(fuelStationItemList);
                }
            } else {
                if (iDataFetchListener != null) {
                    iDataFetchListener.onError("Data not available");
                }
            }
        }

        /**
         * Method which reads the text from the specific asset file.
         *
         * @param context  Context reference
         * @param filename The name of the asset file.
         * @return
         */
        private String readAssets(Context context, String filename) {

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
                String mLine;
                while ((mLine = reader.readLine()) != null) {
                    stringBuilder.append(mLine);
                }
            } catch (IOException e) {
                Log.d(TAG, "Exception while reading the file : " + e.getMessage());
                return null;
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.d(TAG, "Exception while closing the reader : " + e.getMessage());
                    }
                }
            }
            return stringBuilder.toString();
        }
    }

}
