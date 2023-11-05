package com.example.addtilesfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTileFragment extends Fragment {

    private FirebaseServices fbs;
    private EditText etName, etSize, etPrice, etMadeIn, etCompany, etDesignedIn;
    private Button btnAdd;
    private CheckBox cbPolished;
    private boolean polished;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddTileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTileFragment newInstance(String param1, String param2) {
        AddTileFragment fragment = new AddTileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_tile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        connectComponents();
    }

    private void connectComponents() {
        fbs = FirebaseServices.getInstance();
        etName = getView().findViewById(R.id.etNameAddTileFragment);
        etSize = getView().findViewById(R.id.etSizeAddTileFragment);
        etPrice = getView().findViewById(R.id.etPriceAddTileFragment);
        etMadeIn = getView().findViewById(R.id.etMadeInAddTileFragment);
        etCompany = getView().findViewById(R.id.etCompanyAddTileFragment);
        etDesignedIn = getView().findViewById(R.id.etDesignedInAddTileFragment);
        btnAdd = getView().findViewById(R.id.btnAddAddTileFragment);
        cbPolished = getView().findViewById(R.id.cbPolishedAddFragment);
        polished = true;
        cbPolished.setChecked(polished);
        cbPolished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbPolished.isChecked() == true)
                    polished = true;
                else
                    polished = false;


            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String size = etSize.getText().toString();
                String price = etPrice.getText().toString();
                String madeIn = etMadeIn.getText().toString();
                String company = etCompany.getText().toString();
                String designedIn = etDesignedIn.getText().toString();

                if (name.trim().isEmpty() || size.trim().isEmpty() ||
                        price.trim().isEmpty() || madeIn.trim().isEmpty() || company.trim().isEmpty() || designedIn.trim().isEmpty())
                {
                    Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(size) || !TextUtils.isDigitsOnly(price))
                {

                    Toast.makeText(getActivity(), "something went wrong!", Toast.LENGTH_LONG).show();
                    return;
                }
                double sized =  Double.parseDouble(size);
                double priced = Double.parseDouble(price);
                Tile tile1= new Tile(name , sized , priced , madeIn , company , designedIn , polished);

                 fbs.getFire().collection("tiles").add(tile1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                     @Override
                     public void onSuccess(DocumentReference documentReference) {
                         Toast.makeText(getActivity(), "Successfully added!", Toast.LENGTH_SHORT).show();
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Log.e("Failure AddData : ", e.getMessage());
                     }
                 });

            }
        });


    }
}