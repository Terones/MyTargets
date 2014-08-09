package de.dreier.mytargets;

import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Shows all passes of one round
 */
public abstract class NowListAdapter extends CursorAdapter {
    protected final LayoutInflater mInflater;
    protected final TargetOpenHelper db;
    protected final Context mContext;
    protected int mExtraCards = 1;
    protected String mNewText;

    public NowListAdapter(Context context, Cursor cursor) {
        super(context,cursor,0);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        db = new TargetOpenHelper(context);
        mContext = context;
    }

    @Override
    public int getCount() {
        return super.getCount()+mExtraCards;
    }

    @Override
    public int getItemViewType(int position) {
        if(position<mExtraCards)
            return position;
        else
            return mExtraCards;
    }

    @Override
    public int getViewTypeCount() {
        return mExtraCards+(super.getCount()>0?1:0);
    }

    @Override
    public long getItemId(int pos) {
        return pos<mExtraCards?-1-pos:super.getItemId(pos-mExtraCards);
    }

    public boolean isSelectable(int position) {
        return position>=mExtraCards;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View view;
        if(pos==0) {
            if (convertView == null) {
                view = mInflater.inflate(R.layout.new_card, parent, false);
            } else {
                view = convertView;
            }
            TextView text = (TextView) view.findViewById(R.id.newText);
            text.setText(mNewText);
        } else if(pos<mExtraCards) {
            view = buildExtraCard(pos,convertView,parent);
        } else {
            view = super.getView(pos-mExtraCards,convertView,parent);
        }
        return view;
    }

    protected View buildExtraCard(int pos, View convertView, ViewGroup parent) {throw new IllegalArgumentException("buildExtraCard must be implemented!");}
}
