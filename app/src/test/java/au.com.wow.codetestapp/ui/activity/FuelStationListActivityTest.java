package au.com.wow.codetestapp.ui.activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import au.com.wow.codetestapp.BuildConfig;
import au.com.wow.codetestapp.response.FuelStationItem;
import au.com.wow.codetestapp.ui.fragments.FuelStationDetailFragment;
import au.com.wow.codetestapp.ui.fragments.FuelStationListFragment;

import static junit.framework.Assert.assertEquals;
import static org.assertj.android.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21, manifest = "src/main/AndroidManifest.xml")
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "com.android.volley.*"})
public class FuelStationListActivityTest {

    private FuelStationListActivity fuelStationListActivity;

    @Before
    public void setUp(){
        fuelStationListActivity = Robolectric.setupActivity(FuelStationListActivity.class);
    }

    @Test
    public void testActivityNotNull(){
        assertThat(fuelStationListActivity).isNotNull();

    }

    @Test
    public void testFragmentLoading() {
        FuelStationListFragment fragment = (FuelStationListFragment) fuelStationListActivity.getFragmentManager().findFragmentByTag(FuelStationListFragment.class.getName());
        assert(fragment!=null);

        while(fragment.getListAdapter() == null) {}

        if(BuildConfig.USE_MOCK_DATA) {
            assertEquals(fragment.getListAdapter().getCount(), 50);
            shadowOf(fragment.getListView()).performItemClick(0);
            FuelStationDetailFragment detailFragment = (FuelStationDetailFragment) fuelStationListActivity.getFragmentManager().findFragmentByTag(FuelStationDetailFragment.class.getName());
            assert(detailFragment!=null);
            FuelStationItem stationItem = detailFragment.getArguments().getParcelable(FuelStationDetailFragment.FUEL_STATION_DETAIL);
            assertEquals(stationItem.getAddress(), "303 W 96th St, New York, NY 10025");
            assertEquals(stationItem.getBrand(), "Mobil");
            assertEquals(stationItem.getDiesel(), "false");
            assertEquals(stationItem.getPlus(), "false");
            assertEquals(stationItem.getRegular(), "4.07");
            assertEquals(stationItem.getPremium(), "4.47");
            assertEquals(stationItem.getPupdate(), "1408457760");
            assertEquals(stationItem.getDistance(), "0.8847563");
            assertEquals(stationItem.getImg(), "http://www.mshd.net/images/gas/mobil.gif");
        } else {
            assertEquals(fragment.getListAdapter().getCount(), 0);
        }
    }



}
