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

public class CustomAdapterRecipe extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Recipe> versionList;

    public CustomAdapterRecipe(Context context, int resource, ArrayList<Recipe> objects) {
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
        TextView tvTitle = rowView.findViewById(R.id.titleRecipe);
        TextView tvSummary = rowView.findViewById(R.id.summaryRecipe);
        TextView tvIngre = rowView.findViewById(R.id.ingredientRecipe);
        TextView tvDetails = rowView.findViewById(R.id.detailRecipe);
        TextView tvRecipe = rowView.findViewById(R.id.recipeUpTime);
        TextView tvUN = rowView.findViewById(R.id.tvPostedBy);
        // Obtain the Android Version information based on the position
        Recipe currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvSummary.setText(currentVersion.getSummary());
        tvIngre.setText(currentVersion.getIngredients());
        tvDetails.setText(currentVersion.getDetails());
        tvRecipe.setText(currentVersion.getDate());
        tvUN.setText(currentVersion.getUsername());
        return rowView;


    }


}
