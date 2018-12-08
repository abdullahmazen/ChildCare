package com.example.abodimazen.ChildCare;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abodimazen.fahad.R;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context){
        this.context = context;
    }

    public int[] Slide_image = {

            R.drawable.ic_launcher_child_img,
            R.drawable.ic_launcher_phone,
            R.drawable.email

    };

    public String[] Slide_Tile = {

            "Our Application Takes Care of your Child Vaccination," + "\n" +  "and helps you from the hassle of" + "\n" + "going to the hospital to Vaccinate your child," + "\n " + " instead we come to your door step." + "\n" + "With our application you can monitor" + "\n" + "the status of your Child's vaccination.",
            "Sign in with Phone Number",
            "Sign in with Email"

    };
    @Override
    public int getCount() {
        return Slide_Tile.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = view.findViewById(R.id.Slide_image);
        TextView SlidetextView = view.findViewById(R.id.Slide_Tile);

        slideImageView.setImageResource(Slide_image[position]);
        SlidetextView.setText(Slide_Tile[position]);

        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
