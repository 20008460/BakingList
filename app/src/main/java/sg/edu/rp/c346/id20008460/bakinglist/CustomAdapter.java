package sg.edu.rp.c346.id20008460.bakinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import sg.edu.rp.c346.id20008460.bakinglist.Users;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Users> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Users> objects) {
        // resource = the resource u created (aka row.xml)

        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvUN = rowView.findViewById(R.id.tvLVUN);
        TextView tvPW = rowView.findViewById(R.id.tvLVPW);
        TextView tvRole = rowView.findViewById(R.id.tvRole);
        // Obtain the Android Version information based on the position
        Users currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvUN.setText(currentVersion.getUsername());
        tvPW.setText(currentVersion.getPassword());
        tvRole.setText(currentVersion.getRole());
        return rowView;


    }


}
