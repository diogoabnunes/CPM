package org.feup.apm.sensors;

import java.util.List;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SensorSelectorFragment extends ListFragment {
    private SensorDisplayFragment sensorDisplay;

    /**
     * connect with a display fragment to call later when user clicks a sensor
     * name, also setup the ListAdapter to show all the Sensors
     */
    public void setSensorDisplay(SensorDisplayFragment sensorDisplay) {
        this.sensorDisplay = sensorDisplay;

        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Activity.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        setListAdapter(new SensorListAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, sensors));
    }

    /**
     * hide the list of sensors and show the sensor display fragment
     * add these changes to the backstack
     */
    private void showSensorFragment(Sensor sensor) {
        sensorDisplay.displaySensor(sensor);
        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        ft.hide(this);
        ft.show(sensorDisplay);
        ft.addToBackStack("Showing sensor: " + sensor.getName());
        ft.commit();
    }

    /**
     * list view adapter to show sensor names and respond to clicks.
     */
    private class SensorListAdapter extends ArrayAdapter<Sensor> {
        public SensorListAdapter(Context context, int textViewResourceId, List<Sensor> sensors) {
            super(context, textViewResourceId, sensors);
        }

        /**
         * create a text view containing the sensor name
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Sensor selectedSensor = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
            }

            ((TextView) convertView).setText(selectedSensor.getName());

            convertView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    showSensorFragment(selectedSensor);
                }
            });
            return convertView;
        }
    }
}
