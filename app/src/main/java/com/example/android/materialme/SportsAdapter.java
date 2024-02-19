package com.example.android.materialme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;

class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder>  {

    private ArrayList<Sport> mSportsData;
private Context mContext;

        /**
         * Constructor that passes in the sports data and the context.
         *
         * @param sportsData ArrayList containing the sports data.
         * @param context Context of the application.
         */
        SportsAdapter(Context context, ArrayList<Sport> sportsData) {
        this.mSportsData = sportsData;
        this.mContext = context;

        }


/**
 * Required method for creating the viewholder objects.
 *
 * @param parent The ViewGroup into which the new View will be added
 *               after it is bound to an adapter position.
 * @param viewType The view type of the new View.
 * @return The newly created ViewHolder.
 */
@Override
public SportsAdapter.ViewHolder onCreateViewHolder(
        ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
        inflate(R.layout.list_item, parent, false));
        }

/**
 * Required method that binds the data to the viewholder.
 *
 * @param holder The viewholder into which the data should be put.
 * @param position The adapter position.
 */
@Override
public void onBindViewHolder(SportsAdapter.ViewHolder holder,
        int position) {
        // Get current sport.
        Sport currentSport = mSportsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentSport);
        Glide.with(mContext).load(currentSport.getImageResource()).into(holder.mSportsImage);
        }

/**
 * Required method for determining the size of the data set.
 *
 * @return Size of the data set.
 */
@Override
public int getItemCount() {
        return mSportsData.size();
        }


/**
 * ViewHolder class that represents each row of data in the RecyclerView.
 */
class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // Member Variables for the TextViews
    private ImageView mSportsImage;
    private TextView mTitleText;
    private TextView mInfoText;

    /**
     * Constructor for the ViewHolder, used in onCreateViewHolder().
     *
     * @param itemView The rootview of the list_item.xml layout file.
     */
    ViewHolder(View itemView) {
        super(itemView);

        // Initialize the views.
        mTitleText = itemView.findViewById(R.id.title);
        mInfoText = itemView.findViewById(R.id.subTitle);
        mSportsImage = (ImageView) itemView.findViewById(R.id.sportsImage);
        itemView.setOnClickListener(this);
    }

    void bindTo(Sport currentSport){
        // Populate the textviews with data.
        mTitleText.setText(currentSport.getTitle());
        mInfoText.setText(currentSport.getInfo());

    }

    @Override
    public void onClick(View view) {
        Sport currentSport = mSportsData.get(getAdapterPosition());
        Intent detailIntent = new Intent(mContext, DetailActivity.class);
        detailIntent.putExtra("title", currentSport.getTitle());
        detailIntent.putExtra("image_resource", currentSport.getImageResource());
    }
}
    public void onItemMove(int fromPosition, int toPosition) {
        // Reordenar los elementos en la lista.
        Collections.swap(mSportsData, fromPosition, toPosition);
        // Notificar al adaptador sobre el cambio.
        notifyItemMoved(fromPosition, toPosition);
    }

    // Método para manejar el deslizamiento para eliminar un elemento.
    public void onItemDismiss(int position) {
        // Eliminar el elemento de la lista.
        mSportsData.remove(position);
        // Notificar al adaptador sobre el cambio.
        notifyItemRemoved(position);
    }
}