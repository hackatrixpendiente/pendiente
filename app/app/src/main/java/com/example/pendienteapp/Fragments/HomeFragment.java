package com.example.pendienteapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.pendienteapp.MainappActivity;
import com.example.pendienteapp.R;

public class HomeFragment extends Fragment {
    CardView btnBembos, btnPardos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) getContext()).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(getResources()
                    .getColor(R.color.colorPrimary));

        }

        btnBembos = view.findViewById(R.id.btnBembos);
        btnPardos = view.findViewById(R.id.btnPardos);

        btnBembos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarFramento(new SeleccionFragment(),"1");
            }
        });

        btnPardos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarFramento(new SeleccionFragment(),"3");
            }
        });


        return view;

    }

    private void cargarFramento(Fragment fragment, String id){
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        fragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager ();
        manager.beginTransaction().replace(R.id.containerFrag,fragment).addToBackStack(null).commit();
    }
}
