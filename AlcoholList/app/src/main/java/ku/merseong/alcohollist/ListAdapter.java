package ku.merseong.alcohollist;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter
{
    private LayoutInflater inflater = null;
    private ArrayList<Alcohol> m_oData = null;
    private int nListCnt = 0;

    public ListAdapter(ArrayList<Alcohol> _oData)
    {
        m_oData = _oData;
        nListCnt = m_oData.size();
    }

    @Override
    public int getCount() {return nListCnt;}

    @Override
    public Object getItem(int i) { return null; }

    @Override
    public long getItemId(int i) { return 0; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            final Context context = viewGroup.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            view = inflater.inflate(R.layout.item, viewGroup, false);
        }

        // id 기반으로 텍스트뷰를 찾아다가 배정
        TextView oAlcName = (TextView) view.findViewById(R.id.alc_name_text);
        TextView oAlcDate = (TextView) view.findViewById(R.id.date_text);
        TextView oAlcCategory = (TextView) view.findViewById(R.id.category_text);
        TextView oAlcComment = (TextView) view.findViewById(R.id.comment_text);

        // 거기에 텍스트를 입력
        oAlcName.setText(m_oData.get(i).name);
        oAlcDate.setText(m_oData.get(i).GetDate());
        oAlcComment.setText(m_oData.get(i).GetShortComment());
        oAlcCategory.setText(m_oData.get(i).category.getName());

        return view;
    }
}
