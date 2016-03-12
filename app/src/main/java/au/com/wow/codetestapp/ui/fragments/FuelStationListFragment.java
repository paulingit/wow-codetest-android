package au.com.wow.codetestapp.ui.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import au.com.wow.codetestapp.R;
import au.com.wow.codetestapp.dataprovider.AbstractDataProvider;
import au.com.wow.codetestapp.dataprovider.DataProvider;
import au.com.wow.codetestapp.dataprovider.IDataFetchListener;
import au.com.wow.codetestapp.response.FuelStationItem;
import au.com.wow.codetestapp.ui.adapter.FuelStationListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @FileName FuelStationListFragment.java
 * @Purpose Display whole station list. Each item contains icon, address and distance
 * @RevisionHistory Created
 */
public class FuelStationListFragment extends ListFragment implements IDataFetchListener {

    private Activity mContext;

    @Bind(R.id.progressBar)
    protected ProgressBar progressBar;

    @Bind(R.id.empty)
    protected TextView emptyView;

    private OnFuelItemSelectedListener onFuelItemSelectedListener;
    private List<FuelStationItem> fuelStationItemList = new ArrayList<>();
    private FuelStationListAdapter fuelStationListAdapter;

    private AbstractDataProvider dataProvider;

    /*
     * onAttach(Context) is not called on pre API 23 versions of Android and onAttach(Activity) is deprecated
     * Use onAttachToContext instead
     */
    @TargetApi(Build.VERSION_CODES.M)
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
        if (context instanceof OnFuelItemSelectedListener) {
            this.onFuelItemSelectedListener = (OnFuelItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnFuelItemSelectedListener");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        dataProvider = DataProvider.getDefaultDataProvider(mContext);
    }

    @Override
    public void onStart() {
        super.onStart();
        mContext.getActionBar().setTitle(R.string.app_name);
        mContext.getActionBar().setDisplayHomeAsUpEnabled(false);
        mContext.getActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fuel_station_layout, container, false);

        ButterKnife.bind(this, rootView);

        if (fuelStationListAdapter == null) {
            fuelStationListAdapter = new FuelStationListAdapter(mContext, fuelStationItemList);
            setListAdapter(fuelStationListAdapter);
            fetchFuelStationDetails();
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        onFuelItemSelectedListener.onFuelItemSelected(fuelStationItemList.get(position));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(Menu.NONE, Menu.FIRST, 0, "Refresh").setIcon(R.drawable.ic_refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == Menu.FIRST) {
            refresh();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFuelItemSelectedListener = null;
    }

    private void refresh() {
        fetchFuelStationDetails();
    }

    private void fetchFuelStationDetails() {
        progressBar.setVisibility(View.VISIBLE);
        dataProvider.fetchFuelStationList(this);
    }

    @Override
    public void onSuccess(List<FuelStationItem> fuelStationItems) {
        progressBar.setVisibility(View.GONE);
        fuelStationItemList.clear();
        fuelStationItemList.addAll(fuelStationItems);
        fuelStationListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errorMessage) {
        progressBar.setVisibility(View.GONE);
        emptyView.setText(R.string.error_message);
    }

    public interface OnFuelItemSelectedListener {
        void onFuelItemSelected(FuelStationItem fuelStationItem);
    }

}