package com.example.ethan.ordertest;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.BitmapFactory;

import java.util.List;

/**
 * Created by ethan on 12/20/14.
 */
public class dishAdapter extends ArrayAdapter<Dish> {
    private static final String    TAG                 = "OrderTest";
    private final Context context;
    private final List<Dish> dishes;
    //private static String imgsrc = Environment.getExternalStorageDirectory().getAbsolutePath() + "/OrderImages/";

    public dishAdapter(Context context, List<Dish> dishes) {
        super(context, R.layout.dishlayout, dishes);
        this.context = context;
        this.dishes = dishes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.dishlayout, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.label);
        TextView textViewPrice = (TextView) rowView.findViewById(R.id.pricetag);
        int imageRes = MainActivity.imagemap.get(dishes.get(position).getName());
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textViewName.setText(dishes.get(position).getName());
        textViewPrice.setText(Float.toString(dishes.get(position).getPrice()));
        imageView.setImageResource(imageRes);
        //String src =  imgsrc + "1.png";
        //Log.d(TAG, src);
        //Bitmap bm = decodeSampledBitmapFromUri(src, 220, 220);
        //imageView.setImageBitmap(bm);
        // Change the icon for Windows and iPhone
        /*
        String s = values[position];
        if (s.startsWith("Windows7") || s.startsWith("iPhone")
                || s.startsWith("Solaris")) {
            imageView.setImageResource(R.drawable.no);
        } else {
            imageView.setImageResource(R.drawable.ok);
        }
        */
        return rowView;
    }

    /*
    private Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
        Bitmap bm = null;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }

    private int calculateInSampleSize(

            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }

        return inSampleSize;
    }
    */
}
