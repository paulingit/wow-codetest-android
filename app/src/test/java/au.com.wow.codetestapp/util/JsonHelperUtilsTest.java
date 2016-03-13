package au.com.wow.codetestapp.util;

import junit.framework.TestCase;

import java.util.List;

import au.com.wow.codetestapp.response.FuelStationItem;
import au.com.wow.codetestapp.response.FuelStationResponse;

/**
 * Test Class used to test Json Helper.
 */
public class JsonHelperUtilsTest extends TestCase{


    private String getJsonString(){
        String jsonString =  "{\n" +
                "                \"zip\": \"10024\",\n" +
                "                \"item\": [\n" +
                "        {\n" +
                "            \"regular\": 4.07,\n" +
                "                \"plus\": false,\n" +
                "                \"premium\": 4.47,\n" +
                "                \"diesel\": false,\n" +
                "                \"brand\": \"Mobil\",\n" +
                "                \"img\": \"http://www.mshd.net/images/gas/mobil.gif\",\n" +
                "                \"address\": \"303 W 96th St, New York, NY 10025\",\n" +
                "                \"pupdate\": 1408457760,\n" +
                "                \"distance\": \"0.8847563\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"regular\": 4.39,\n" +
                "                \"plus\": 4.49,\n" +
                "                \"premium\": 4.69,\n" +
                "                \"diesel\": false,\n" +
                "                \"brand\": \"Shell\",\n" +
                "                \"img\": \"http://androidexample.com/media/webservice/LazyListView_images/image0.png\",\n" +
                "                \"address\": \"718 11th Ave, New York, NY 10019\",\n" +
                "                \"pupdate\": 1408544160,\n" +
                "                \"distance\": \"1.443717\"\n" +
                "        }\n" +
                "        ],\n" +
                "        \"error\": false\n" +
                "        }";
        return jsonString;
    }

    public void testDeserializeValidInput(){


        FuelStationResponse fuelStationResponse = JsonHelperUtils.deSerialize(FuelStationResponse.class, getJsonString());

        assert(fuelStationResponse.getZip().equals("10024"));

        List<FuelStationItem> fuelStationItemList = fuelStationResponse.getItem();

        assert(fuelStationItemList.size()==2);

        assert(fuelStationItemList.get(0).getBrand().equals("Mobil"));
        assert(fuelStationItemList.get(0).getRegular().equals("4.07"));
        assert(fuelStationItemList.get(0).getPlus().equals("false"));
        assert(fuelStationItemList.get(0).getPremium().equals("4.47"));
        assert(fuelStationItemList.get(0).getDiesel().equals("false"));
        assert(fuelStationItemList.get(0).getImg()).equals("http://www.mshd.net/images/gas/mobil.gif");
        assert(fuelStationItemList.get(0).getAddress().equals("303 W 96th St, New York, NY 10025"));
        assert(fuelStationItemList.get(0).getPupdate().equals("1408457760"));
        assert(fuelStationItemList.get(0).getDistance().equals("0.8847563"));


        assert(fuelStationItemList.get(1).getBrand().equals("Shell"));
        assert(fuelStationItemList.get(1).getRegular().equals("4.39"));
        assert(fuelStationItemList.get(1).getPlus().equals("4.49"));
        assert(fuelStationItemList.get(1).getPremium().equals("4.69"));
        assert(fuelStationItemList.get(1).getDiesel().equals("false"));
        assert(fuelStationItemList.get(1).getImg()).equals("http://androidexample.com/media/webservice/LazyListView_images/image0.png");
        assert(fuelStationItemList.get(1).getAddress().equals("718 11th Ave, New York, NY 10019"));
        assert(fuelStationItemList.get(1).getPupdate().equals("1408544160"));
        assert(fuelStationItemList.get(1).getDistance().equals("1.443717"));

    }

    public void testDeserializeEmptyInput(){

        FuelStationResponse fuelStationResponse = JsonHelperUtils.deSerialize(FuelStationResponse.class, "");

        assert( fuelStationResponse==null );
    }

    public void testDeserializeInValidInput(){

        FuelStationItem fuelStationItem = JsonHelperUtils.deSerialize(FuelStationItem.class, getJsonString());

        assert( fuelStationItem!=null);
        assert( fuelStationItem.getBrand()==null);
        assert( fuelStationItem.getRegular()==null);
        assert( fuelStationItem.getPlus()==null);
        assert( fuelStationItem.getPremium()==null);
        assert( fuelStationItem.getImg()==null);
        assert( fuelStationItem.getAddress()==null);
        assert( fuelStationItem.getPupdate()==null);
        assert( fuelStationItem.getDistance()==null);


    }

}
