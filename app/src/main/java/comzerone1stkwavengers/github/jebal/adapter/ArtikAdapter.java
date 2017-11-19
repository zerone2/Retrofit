package comzerone1stkwavengers.github.jebal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import comzerone1stkwavengers.github.jebal.R;
import comzerone1stkwavengers.github.jebal.model.Data;

/**
 * Created by yicho on 2017-08-27.
 */
public class ArtikAdapter extends ArrayAdapter<Data> {

    private Context context;
    private List<Data> values;

    public ArtikAdapter(Context context, List<Data> values) {
        super(context,R.layout.list_item_pagination, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.list_item_pagination_text);

        Data item = values.get(position);
        String message = item.getFullName();
        String message2 =item.getId();
        String message3 = item.getEmail();
        textView.setText("name : " + message + " \n id : "+ message2 + "\n E-mail : " + message3);
        //textView.setText(""+ message2);

        return row;
    }
}
