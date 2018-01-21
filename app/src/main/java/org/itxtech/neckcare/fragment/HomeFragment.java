package org.itxtech.neckcare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.itxtech.neckcare.R;

/**
 * @author PeratX
 */
public class HomeFragment extends ToolbarFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void checkStatus() {
        menu.findItem(R.id.nav_home).setChecked(true);
        toolbar.setTitle(R.string.nav_home);
    }
}
