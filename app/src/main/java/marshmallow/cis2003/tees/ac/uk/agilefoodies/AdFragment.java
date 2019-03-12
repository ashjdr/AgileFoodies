package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by t7039352 on 29/01/19.
 *
 */

public class AdFragment extends Fragment
{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.ad_fragment, container, false);

        return v;
    }


}
