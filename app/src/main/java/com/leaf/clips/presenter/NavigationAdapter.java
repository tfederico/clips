package com.leaf.clips.presenter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leaf.clips.R;
import com.leaf.clips.model.navigator.ProcessedInformation;

import java.util.List;

/**
 * @author Andrea Tombolato
 * @version 0.03
 * @since 0.00
 */
public class NavigationAdapter extends BaseAdapter {
    private Context context;
    private List<ProcessedInformation> navigationInformation;

    public NavigationAdapter(Context context, List<ProcessedInformation> navigationInformation) {
        this.context = context;
        this.navigationInformation = navigationInformation;
    }

    @Override
    public int getCount() {
        return navigationInformation.size();
    }

    @Override
    public Object getItem(int position) {
        return navigationInformation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.nav_info_row,null);
        }

        ProcessedInformation navigationInformation = (ProcessedInformation)getItem(position);

        ImageView directionImage = (ImageView)convertView.findViewById(R.id.imageView_direction);
        int direction = navigationInformation.getDirection();
        setDirectionArrow(direction, directionImage);

        TextView basicDescription = (TextView)convertView.findViewById(R.id.textView_short_description);
        basicDescription.setText(navigationInformation.getProcessedBasicInstruction());

        /**TODO: where to find the distance?
         * TextView distance = (TextView)convertView.findViewById(R.id.textView_distance);
         * distance.setText(navigationInformation.getDistance());
         */

        return  convertView;
    }

    /**
     * Metodo di utilità che associa la freccia corretta (Drawable) alla istruzione, in modo che ne
     * indichi visivamente la direzione da seguire.
     * @param direction indicatore della direzione.
     */
    private void setDirectionArrow(int direction, ImageView image){
        int directionArrowId = -1;

        switch (direction){
            case 0: image.setBackgroundResource(R.drawable.arrow_go_straight);
                    break;
            case 1: image.setBackgroundResource(R.drawable.arrow_turn_left);
                break;
            case 2: image.setBackgroundResource(R.drawable.arrow_turn_right);
                break;
            case 3: image.setBackgroundResource(R.drawable.arrow_stairs_up);
                break;
            case 4: image.setBackgroundResource(R.drawable.arrow_stairs_down);
                break;
            case 5: image.setBackgroundResource(R.drawable.arrow_elevator_up);
                break;
            case 6: image.setBackgroundResource(R.drawable.arrow_elevator_down);
                break;
            default: Log.d("DIRECTION_ARRAY_ERR","Non esiste una rappresentazione grafica per questa direzione");
                break;
        }

    }
}
