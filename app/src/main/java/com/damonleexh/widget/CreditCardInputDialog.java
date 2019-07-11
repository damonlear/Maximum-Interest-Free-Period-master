package com.damonleexh.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.damonleexh.R;

public class CreditCardInputDialog extends DialogFragment {
    private ICredieCardListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_input_creditcard, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("保存",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText edtName = view.findViewById(R.id.edt_name);
                                EditText edtStatement = view.findViewById(R.id.edt_statement);
                                EditText edtPayment = view.findViewById(R.id.edt_payment);
                                if (listener != null) {
                                    listener.input(
                                            edtName.getText().toString(),
                                            edtStatement.getText().toString(),
                                            edtPayment.getText().toString()
                                    );
                                }

                            }
                        }).setNegativeButton("取消", null);
        return builder.create();
    }

    public CreditCardInputDialog setOnInputListener(ICredieCardListener listener) {
        this.listener = listener;
        return this;
    }
}