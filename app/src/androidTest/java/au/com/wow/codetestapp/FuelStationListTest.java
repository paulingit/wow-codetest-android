package au.com.wow.codetestapp;

import android.test.InstrumentationTestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class FuelStationListTest extends InstrumentationTestCase {
    JSONObject mJsonObject;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        InputStream raw = getInstrumentation().getContext().getAssets().open("fuelstationlist.json");
        String json = new Scanner(raw).useDelimiter("\\A").next();
        mJsonObject = new JSONObject(json);
    }

    public void testJsonObjectIsNotNull() {
        assertTrue(mJsonObject != null);
    }

    public void testNumberOfStations() throws JSONException {
        assertEquals(mJsonObject.getJSONArray("item").length(), 50);
    }

    public void testZipValue() throws JSONException{
        assertEquals(mJsonObject.getString("zip"), "10024");
    }

    public void testFuelStationItems() throws JSONException {
        JSONArray jsonArray = mJsonObject.getJSONArray("item");
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        assert(jsonObject.getString("regular").equals("4.07"));
        assert(jsonObject.getString("plus").equals("false"));
        assert(jsonObject.getString("premium").equals("4.47"));
        assert(jsonObject.getString("diesel").equals("false"));
        assert(jsonObject.getString("brand").equals("Mobil"));
        assert(jsonObject.getString("img").equals("http:\\/\\/www.mshd.net\\/images\\/gas\\/mobil.gif"));
        assert(jsonObject.getString("address").equals("303 W 96th St, New York, NY 10025"));
        assert(jsonObject.getString("pupdate").equals("1408457760"));
        assert(jsonObject.getString("distance").equals("0.8847563"));
    }
}
