package au.com.wow.codetestapp.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import au.com.wow.codetestapp.BuildConfig;
import au.com.wow.codetestapp.R;
import au.com.wow.codetestapp.response.FuelStationItem;
import au.com.wow.codetestapp.ui.fragments.FuelStationDetailFragment;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.robolectric.util.FragmentTestUtil.startFragment;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
public class FuelStationDetailFragmentTest {

    @Test
    public void testIfFragmentIsNull(){
        FuelStationDetailFragment fuelStationDetailFragment = new FuelStationDetailFragment();
        startFragment(fuelStationDetailFragment);
        assertNotNull(fuelStationDetailFragment);
    }

    @Test
    public void testIfFragmentIsLoaded(){
        FuelStationDetailFragment fuelStationDetailFragment = new FuelStationDetailFragment();

        FuelStationItem fuelStationItem = new FuelStationItem();
        fuelStationItem.setAddress("Address");
        fuelStationItem.setRegular("Regular");
        fuelStationItem.setPlus("Plus");
        fuelStationItem.setPremium("Premium");
        fuelStationItem.setPupdate("Pupdate");
        fuelStationItem.setBrand("Brand");
        fuelStationItem.setDiesel("Diesel");
        fuelStationItem.setDistance("Distance");
        fuelStationItem.setImg("Img");
        Bundle bundle = new Bundle();
        bundle.putParcelable(FuelStationDetailFragment.FUEL_STATION_DETAIL, fuelStationItem);

        fuelStationDetailFragment.setArguments(bundle);
        startFragment(fuelStationDetailFragment);
        assertNotNull(fuelStationDetailFragment);

        assertNotNull(fuelStationDetailFragment.getView());

        TextView textRegular = (TextView) fuelStationDetailFragment.getView().findViewById(R.id.tv_regular_price);
        TextView textPlus = (TextView) fuelStationDetailFragment.getView().findViewById(R.id.tv_plus_price);
        TextView textPremium = (TextView) fuelStationDetailFragment.getView().findViewById(R.id.tv_premium_price);
        TextView textDiesel = (TextView) fuelStationDetailFragment.getView().findViewById(R.id.tv_diesel_price);

        assertNotNull(textRegular);
        assertNotNull(textPlus);
        assertNotNull(textPremium);
        assertNotNull(textDiesel);

        assertEquals(textRegular.getText().toString(),"Regular");
        assertEquals(textPlus.getText().toString(),"Plus");
        assertEquals(textPremium.getText().toString(),"Premium");
        assertEquals(textDiesel.getText().toString(),"Diesel");

    }
}
