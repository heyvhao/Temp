package cn.edu.gdpt.libraryhome.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MeasureListview extends ListView {
    public MeasureListview(Context context) {
        super(context);
    }

    public MeasureListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(9999999,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
