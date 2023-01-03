package com.example.apptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.MyViewHolder> {
//    private CarsAdapter adapter;
    private Context context;
    private List<ParkCarModel> parkCarModelList;
    private CarAdapterInterface listener;

    public CarsAdapter(Context context, CarAdapterInterface listener) {
        this.context = context;
        this.listener = listener;
        parkCarModelList = new ArrayList<>();
    }



    public void add(ParkCarModel parkCarModel){
        parkCarModelList.add(parkCarModel);
        notifyDataSetChanged();
    }
    public void remove(ParkCarModel parkCarModel){
        parkCarModelList.remove(parkCarModel);
        notifyDataSetChanged();
    }

    public void remove(int pos){
        parkCarModelList.remove(pos);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.park_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            ParkCarModel model = parkCarModelList.get(position);
//            holder.driverNumber.setText(model.getDriverNumber());
//            holder.driverName.setText(model.getDriverName());
//            holder.numberPlate.setText(model.getNumberPlate());
//            holder.fee.setText(model.getFee());
            holder.status.setText(model.getStatus());
            String[] dateAndTime=longIntoString(model.getTime());
            holder.time.setText(dateAndTime[0]+"\n"+dateAndTime[1]);
//            holder.vehicleType.setText(model.getVehicleType());
            holder.slot.setText(model.getSlot());

    }

    @Override
    public int getItemCount() {
        return parkCarModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView vehicleType, driverName, driverNumber, numberPlate, fee, time, status, slot;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            vehicleType=itemView.findViewById(R.id.vehicleType);
//            driverName=itemView.findViewById(R.id.driverName);
//            driverNumber=itemView.findViewById(R.id.driverNumber);
//            numberPlate=itemView.findViewById(R.id.numberPlate);
//            fee=itemView.findViewById(R.id.Amount);
            time=itemView.findViewById(R.id.Date);
            status=itemView.findViewById(R.id.statuspark);
            slot=itemView.findViewById(R.id.slot);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.OnLongClick(getAdapterPosition(), parkCarModelList.get(getAdapterPosition()).getSlot());
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClick(getAdapterPosition(), parkCarModelList.get(getAdapterPosition()).getSlot());
//                    remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }

    private String[] longIntoString(long milliseconds){
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd,yyyy");
        return new String[]{dateFormat.format(milliseconds),timeFormat.format(milliseconds)};
    }
}
