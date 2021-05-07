package com.intsab.intsabshop.ui.conformation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.intsab.intsabshop.R;
import com.intsab.intsabshop.Utils.AppUtils;

public class ConformationFragment extends Fragment {

    private ConformationViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ConformationViewModel.class);

        return inflater.inflate(R.layout.conformation_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button confirmButton = view.findViewById(R.id.confirm_button);
        EditText etCVC = view.findViewById(R.id.et_cvc);
        EditText etExp = view.findViewById(R.id.et_exp);
        EditText etCardNum = view.findViewById(R.id.et_card);

        TextView tvAmount = view.findViewById(R.id.totlAmout);
        TextView tvCount = view.findViewById(R.id.total_count);
        String amount = getArguments().getString(AppUtils.KEY_AMOUNT);
        String count = getArguments().getString(AppUtils.KEY_ITEMS);
        tvAmount.setText("AED " + amount);
        tvCount.setText(count);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etCardNum.getText().toString().isEmpty() || etCVC.getText().toString().isEmpty() || etExp.getText().toString().isEmpty()) {
                    Toast.makeText(requireContext(), getString(R.string.enter_card_details), Toast.LENGTH_SHORT).show();

                } else {
                    Navigation.findNavController(view).navigate(R.id.action_nav_conformation_to_nav_success);
                }
            }
        });


    }


}