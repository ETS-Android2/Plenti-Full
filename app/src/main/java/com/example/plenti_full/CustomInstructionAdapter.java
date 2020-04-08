package com.example.plenti_full;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plenti_full.Javabeans.Instruction;

import java.util.ArrayList;

public class CustomInstructionAdapter extends RecyclerView.Adapter<CustomInstructionAdapter.CustomViewHolder> {
    private ArrayList<Instruction> instructions;
    private Context context;

    public CustomInstructionAdapter(ArrayList<Instruction> instructions, Context context){
        this.instructions = instructions;
        this.context = context;
    }

    @NonNull
    @Override
    /**
     * Create view and return CustomViewHolder
     */
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instruction_item, parent, false);

        return new CustomViewHolder(view);
    }

    /**
     * Bind values to the POJO getters
     */
    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {

        final Instruction instructionItem = instructions.get(position);
        String instructionID = Integer.toString(instructionItem.getId());
        holder.id.setText(instructionID);
        holder.step.setText(instructionItem.getStep());

    }

    @Override
    public int getItemCount() {
        return instructions.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView step;
        protected TextView id;


        /**
         * Set values to textViews and imageView
         */
        public CustomViewHolder(View view){
            super(view);
            this.step = view.findViewById(R.id.instructionStep);
            this.id = view.findViewById(R.id.instructionId);
        }


    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    public int getItemViewType(int position) {
        return position;
    }
}
