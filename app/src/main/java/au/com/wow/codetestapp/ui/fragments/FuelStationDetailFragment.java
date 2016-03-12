package au.com.wow.codetestapp.ui.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.com.wow.codetestapp.R;
import au.com.wow.codetestapp.response.FuelStationItem;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @FileName FuelStationDetailFragment.java
 * @Purpose  display prices - regular, plus, premium, diesel
 * @RevisionHistory Created
 */
public class FuelStationDetailFragment extends Fragment {

    public static final String FUEL_STATION_DETAIL = "fuel_station_detail";

    private Activity mContext;

    @Bind(R.id.tv_regular_price)
    protected TextView tvRegularPrice;

    @Bind(R.id.tv_plus_price)
    protected TextView tvPlusPrice;

    @Bind(R.id.tv_premium_price)
    protected TextView tvPremiumPrice;

    @Bind(R.id.tv_diesel_price)
    protected TextView tvDieselPrice;

    public FuelStationDetailFragment() {
    }

    /*
     * onAttach(Context) is not called on pre API 23 versions of Android and onAttach(Activity) is deprecated
     * Use onAttachToContext instead
     */
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        mContext = (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        ButterKnife.bind(this,rootView);

//        tvRegularPrice = (TextView) rootView.findViewById(R.id.tv_regular_price);
//        tvPlusPrice = (TextView) rootView.findViewById(R.id.tv_plus_price);
//        tvPremiumPrice = (TextView) rootView.findViewById(R.id.tv_premium_price);
//        tvDieselPrice = (TextView) rootView.findViewById(R.id.tv_diesel_price);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle  = getArguments();
        if(bundle!=null && bundle.containsKey(FUEL_STATION_DETAIL)){
            FuelStationItem fuelStationItem = (FuelStationItem) bundle.get(FUEL_STATION_DETAIL);

            String actionBarTitle = fuelStationItem.getBrand();
            if(!TextUtils.isEmpty(actionBarTitle)){
                mContext.getActionBar().setTitle(actionBarTitle);
            }
            mContext.getActionBar().setHomeButtonEnabled(true);
            mContext.getActionBar().setDisplayHomeAsUpEnabled(true);
            setData(fuelStationItem);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mContext.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Setting data on the iew
     * @param fuelStationItem
     */
    private void setData(FuelStationItem fuelStationItem) {
        tvRegularPrice.setText(fuelStationItem.getRegular().equals("false")?"N/A":fuelStationItem.getRegular());
        tvPlusPrice.setText(fuelStationItem.getPlus().equals("false")?"N/A":fuelStationItem.getPlus());
        tvPremiumPrice.setText(fuelStationItem.getPremium().equals("false")?"N/A":fuelStationItem.getPremium());
        tvDieselPrice.setText(fuelStationItem.getDiesel().equals("false")?"N/A":fuelStationItem.getDiesel());
    }
}