//package com.example.CMP354.bazaar.Classes;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.CMP354.bazaar.R;
//
//import java.util.List;
//
//public class ShopsItemAdapter extends RecyclerView.Adapter<ShopItemHolder> {
//
//    private List<ShopItem> itemList;
//    private Context context;
//
//    public ShopsItemAdapter(List<ShopItem> itemList) {
//
//        this.itemList = itemList;
//    }
//
//    @Override
//    public ShopItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View itemView = layoutInflater.inflate(R.layout.event_item, parent, false);
//        ShopItemHolder ret = new ShopItemHolder(itemView);
//
//        return ret;
//
//    }
//
//
//    @Override
//    public void onBindViewHolder(final ShopItemHolder holder, int position) {
//        ShopItem item = itemList.get(position);
////
////        holder.getEventTitle().setText(item.getName());
////        holder.setEventID(item.getID());
////        holder.setEventDate(item.getDate().toString());
////        holder.setEventDesc(item.getDescription());
////        holder.setEventLocation(item.getLocation());
////        holder.setEventTime(item.getStart_time()+" - " +item.getEnd_time());
////        holder.setUserID(item.getUserID());
////        holder.setRole(item.getRole());
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        int ret = 0;
//        if(this.itemList!=null)
//        {
//            ret = this.itemList.size();
//        }
//        return ret;
//    }
//
//
//    public void deleteItem(int index) {
//        itemList.remove(index);
//        notifyItemRemoved(index);
//        notifyItemRangeChanged(index, itemList.size());
//
//    }
//
//}