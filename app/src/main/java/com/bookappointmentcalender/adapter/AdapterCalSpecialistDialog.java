package com.bookappointmentcalender.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bookappointmentcalender.R;
import com.bookappointmentcalender.databinding.ItemSpecialistNameBinding;
import com.appointmentbooking.model.GetAllSpecialistModel;

import java.util.List;

public class AdapterCalSpecialistDialog extends RecyclerView.Adapter<AdapterCalSpecialistDialog.MyViewHolder> {

    private List<GetAllSpecialistModel.ResultBean> featuredBooksModels;
    private Context context;
    private ItemSpecialistNameBinding binding;
    private OnClickSpecialist callback;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemSpecialistNameBinding binding;
        public MyViewHolder(ItemSpecialistNameBinding view) {
            super(view.getRoot());
            this.binding=view;
        }
    }
    public AdapterCalSpecialistDialog(List<GetAllSpecialistModel.ResultBean> moviesList, Context context , OnClickSpecialist callback) {
        this.featuredBooksModels = moviesList;
        this.context=context;
        this.callback = callback;
    }
    @Override
    public  MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_specialist_name, parent, false);
        return new MyViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (featuredBooksModels.get(position).isChecked()){
            binding.tvServiceName.setTextColor(context.getResources().getColor(R.color.orange));
        }else {
           binding.tvServiceName.setTextColor(context.getResources().getColor(R.color.textColor));
        }
        binding.tvServiceName.setText(featuredBooksModels.get(position).getName());

        binding.mainRoot.setOnClickListener(v->{
              for (int i = 0; i < featuredBooksModels.size(); i++) {
                       featuredBooksModels.get(i).setChecked(false);
                }
              featuredBooksModels.get(position).setChecked(true);
            callback.onClickSpecialist(featuredBooksModels.get(position).getName(),featuredBooksModels.get(position).get_id());
        });
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        if (featuredBooksModels.size() > 0) {
            return featuredBooksModels.size();
        } else {
            return 0;
        }
    }

    public interface OnClickSpecialist {
        void onClickSpecialist(String specialistName,String specialistId);
    }
}