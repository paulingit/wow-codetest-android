package au.com.wow.codetestapp.ui.fragment;

import android.widget.ListView;
import android.widget.ProgressBar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import au.com.wow.codetestapp.BuildConfig;
import au.com.wow.codetestapp.R;
import au.com.wow.codetestapp.ui.activity.FuelStationListActivity;
import au.com.wow.codetestapp.ui.fragments.FuelStationListFragment;

import static junit.framework.Assert.assertNotNull;
import static org.robolectric.util.FragmentTestUtil.startFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
public class FuelStationListFragmentTest {

    @Test
    public void testIfFragmentIsNull(){
        FuelStationListFragment fuelStationListFragment = new FuelStationListFragment();
        startFragment(fuelStationListFragment, FuelStationListActivity.class);
        assertNotNull(fuelStationListFragment);
    }

    @Test
    public void testIfFragmentIsLoaded(){
        FuelStationListFragment fuelStationListFragment = new FuelStationListFragment();

        startFragment(fuelStationListFragment, FuelStationListActivity.class);
        assertNotNull(fuelStationListFragment);

        assertNotNull(fuelStationListFragment.getView());

        ListView listView = (ListView) fuelStationListFragment.getView().findViewById(android.R.id.list);
        ProgressBar progressBar = (ProgressBar) fuelStationListFragment.getView().findViewById(R.id.progressBar);

        assertNotNull(listView);
        assertNotNull(progressBar);

    }

}

