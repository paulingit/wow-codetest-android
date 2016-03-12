package au.com.wow.codetestapp.dataprovider;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import au.com.wow.codetestapp.response.FuelStationItem;
import au.com.wow.codetestapp.response.FuelStationResponse;
import au.com.wow.codetestapp.util.CustomVolleyRequestQueue;
import au.com.wow.codetestapp.util.JsonHelperUtils;

/**
 * @FileName NetworkDataProvider.java
 * @Purpose Data provider class to get the list of fuel stations from a webservice.
 * @RevisionHistory Created
 */
class NetworkDataProvider extends AbstractDataProvider {

    // NewYork Petrol stations info api
    private static final String URL = "http://www.mshd.net/api/gasprices/10025";
    private static final String TAG = "NetworkDataProvider";

    /**
     * Constructor for class with listener for callbacks.
     * The listener is instance of IDataFetchListener to give callbacks on data fetch (success and error)
     */
    public NetworkDataProvider(Context context) {
        super(context);
    }

    @Override
    public void fetchFuelStationList(IDataFetchListener listener) {
        VolleyResponseListener responseListener = new VolleyResponseListener(listener);
        StringRequest stringRequest = new StringRequest(URL, responseListener, responseListener);
        CustomVolleyRequestQueue.getInstance(context).addToRequestQueue(stringRequest);
    }

    private static class VolleyResponseListener implements Response.ErrorListener, Response.Listener<String> {

        private final IDataFetchListener mDataFetchListener;

        public VolleyResponseListener(IDataFetchListener listener) {
            mDataFetchListener = listener;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            if (mDataFetchListener != null) {
                mDataFetchListener.onError("Data not available");
            }
        }

        @Override
        public void onResponse(String response) {
            if (!TextUtils.isEmpty(response)) {
                FuelStationResponse fuelStationResponse = JsonHelperUtils.deSerialize(FuelStationResponse.class, response);
                List<FuelStationItem> fuelStationItemList = fuelStationResponse.getItem();
                if (fuelStationItemList != null) {
                    if (mDataFetchListener != null) {
                        mDataFetchListener.onSuccess(fuelStationItemList);
                    }
                } else {
                    if (mDataFetchListener != null) {
                        mDataFetchListener.onError("Data not available");
                    }
                }
            } else {
                if (mDataFetchListener != null) {
                    mDataFetchListener.onError("Data not available");
                }
            }
        }
    }

}
