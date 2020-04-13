package com.photograph.lo7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.photograph.lo7.R;
import com.photograph.lo7.databinding.ItemSectionBinding;
import com.photograph.lo7.entity.Section;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    private List<Section> sections;
    private Context context;
    public SectionAdapter(Context context, List<Section> sections) {
        this.sections = sections;
        this.context = context ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSectionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_section, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setSection(sections.get(position));
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return sections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ItemSectionBinding binding;

        public ViewHolder(ItemSectionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
