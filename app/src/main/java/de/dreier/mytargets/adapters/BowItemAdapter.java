package de.dreier.mytargets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.dreier.mytargets.R;
import de.dreier.mytargets.managers.DatabaseManager;
import de.dreier.mytargets.models.Bow;

public class BowItemAdapter extends ArrayAdapter<Bow> {

    public BowItemAdapter(Context context) {
        super(context, 0, DatabaseManager.getInstance(context).getBows());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.image_item, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.name);

        Bow item = getItem(position);
        name.setText(item.name);
        img.setImageBitmap(item.image);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }
}