package com.buyusbyus.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.buyusbyus.Model.Rules;
import com.buyusbyus.R;

import java.util.ArrayList;
import java.util.List;

public class RulesAdapter extends RecyclerView.Adapter<RulesAdapter.ProductViewHolder> implements Filterable {


    private Context mCtx;
    private List<Rules> rulesList;
    private List<Rules> rulesListFull;

    public RulesAdapter( List<Rules> rulesList) {

        this.rulesList = rulesList;
        rulesListFull = new ArrayList<>(rulesList);

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Rules rules = rulesList.get(position);



        holder.textViewTitle.setText(rules.getItemA());
        holder.textViewShortDesc.setText(rules.getItemB());

    }

    @Override
    public int getItemCount() {
        return rulesList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewPromotion, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewPromotion = itemView.findViewById(R.id.textViewPromotion);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }

    public Filter getFilter(){
        return rulesFilter;
    }

    private Filter rulesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Rules> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(rulesListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Rules item : rulesListFull) {
                    if (item.getItemB().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            rulesList.clear();
            rulesList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

}
