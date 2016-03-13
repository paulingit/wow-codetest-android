package au.com.wow.codetestapp.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import au.com.wow.codetestapp.R;
import au.com.wow.codetestapp.response.FuelStationItem;
import au.com.wow.codetestapp.ui.fragments.FuelStationDetailFragment;
import au.com.wow.codetestapp.ui.fragments.FuelStationListFragment;

/**
 * @FileName FuelStationListActivity.java
 * @Purpose The activity class which displays the fuel station list and it's details.
 * @RevisionHistory Created
 */
public class FuelStationListActivity extends Activity implements FuelStationListFragment.OnFuelItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station_list);
        if (savedInstanceState == null) {
            loadFragment(new FuelStationListFragment(), null);
        }
    }

    @Override
    public void onFuelItemSelected(FuelStationItem fuelStationItem) {
        FuelStationDetailFragment fuelStationDetailFragment = new FuelStationDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FuelStationDetailFragment.FUEL_STATION_DETAIL, fuelStationItem);

        loadFragment(fuelStationDetailFragment, bundle);
    }

    /**
     * Method for loading a fragment into the UI.
     *
     * @param fragment The fragment instance to be loaded
     * @param bundle   The bundle to be passed along with the fragment.
     */
    private void loadFragment(Fragment fragment, Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            if (getFragmentManager().getBackStackEntryCount() > 2) {
                getActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                getActionBar().setDisplayHomeAsUpEnabled(false);
            }
            getFragmentManager().popBackStack();

        } else {
            finish();
        }
    }
}