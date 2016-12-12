package fr.enssat.andreas_romain.geoquest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by andreas on 12/12/16.
 */

public class QuestsAdapter extends ArrayAdapter<Quest> {
    public QuestsAdapter(Context context, List<Quest> QuestsList) {
        super(context, 0, QuestsList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Quest quest = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list, parent, false);
        }
        // Lookup view for data population
        TextView nom = (TextView) convertView.findViewById(R.id.name);
        TextView naissance = (TextView) convertView.findViewById(R.id.description);
        // Populate the data into the template view using the data object
        nom.setText(quest.name);
        naissance.setText(quest.description);
        // Return the completed view to render on screen
        return convertView;
    }
}
