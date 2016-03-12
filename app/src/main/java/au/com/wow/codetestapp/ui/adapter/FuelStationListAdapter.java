package au.com.wow.codetestapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import au.com.wow.codetestapp.R;
import au.com.wow.codetestapp.response.FuelStationItem;
import au.com.wow.codetestapp.util.CustomVolleyRequestQueue;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @FileName FuelStationListAdapter.java
 * @Purpose Adapter for binding data with the list which displays the fuel stations.
 */
public class FuelStationListAdapter extends BaseAdapter {

    private Context context;
    private List<FuelStationItem> fuelItemList;
    private ImageLoader imageLoader;
    private LayoutInflater layoutInflater;


    public FuelStationListAdapter(Context context, List<FuelStationItem> fuelItemList){
        this.context = context;
        this.fuelItemList = fuelItemList;
        this.layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return fuelItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return fuelItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_fuel_station,viewGroup,false);
            if(imageLoader == null){
                imageLoader = CustomVolleyRequestQueue.getInstance(context).getImageLoader();
            }
            viewHolder = new ViewHolder(view,imageLoader);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.setData(fuelItemList.get(i));
        return view;
    }

    static class ViewHolder {

        @Bind(R.id.text_Address)
        TextView tvAddress;

        @Bind(R.id.text_Distance)
        TextView tvDistance;

        @Bind(R.id.imageView)
        NetworkImageView imageView;

        ImageLoader imageLoader;

        public ViewHolder(View view, ImageLoader imageLoader){
//            this.tvAddress = (TextView) view.findViewById(R.id.text_Address);
//            this.tvDistance = (TextView) view.findViewById(R.id.text_Distance);
//            this.imageView = (NetworkImageView) view.findViewById(R.id.imageView);

            ButterKnife.bind(this, view);
            this.imageLoader = imageLoader;
        }

        public void setData(FuelStationItem fuelStationItem){
            tvAddress.setText(fuelStationItem.getAddress());
            tvDistance.setText(fuelStationItem.getDistance());
            imageView.setDefaultImageResId(android.R.drawable.ic_menu_gallery);
            imageView.setImageUrl(fuelStationItem.getImg(), imageLoader);
        }

    }
}
