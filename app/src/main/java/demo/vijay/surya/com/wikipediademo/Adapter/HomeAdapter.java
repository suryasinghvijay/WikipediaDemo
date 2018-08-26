package demo.vijay.surya.com.wikipediademo.Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import java.util.List;

import demo.vijay.surya.com.wikipediademo.R;
import demo.vijay.surya.com.wikipediademo.databinding.SearchItemAdapterBinding;
import demo.vijay.surya.com.wikipediademo.interfaces.HomeAdapterCommunicator;
import demo.vijay.surya.com.wikipediademo.models.PagesItem;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<PagesItem> resposneData;
    private HomeAdapterCommunicator mHomeAdapterCommunicator;

    public HomeAdapter(@NonNull List<PagesItem> resposneData, HomeAdapterCommunicator mHomeAdapterCommunicator) {
        this.resposneData = resposneData;
        this.mHomeAdapterCommunicator = mHomeAdapterCommunicator;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mBinding.tvPersonName.setText(resposneData.get(position).getTitle());
        if (null != resposneData.get(position).getTerms() && null != resposneData.get(position).getTerms().getDescription()) {
            holder.mBinding.tvPersonDescription.setText(resposneData.get(position).getTerms().getDescription().get(0));
        }
        if (null != resposneData.get(position).getThumbnail() && null != resposneData.get(position).getThumbnail().getSource()) {
            Glide.with(holder.mBinding.ivPersonPic.getContext()).load
                    (resposneData.get(position).getThumbnail().getSource()).into(holder.mBinding.ivPersonPic);
        } else {
            Glide.with(holder.mBinding.ivPersonPic.getContext()).load(R.drawable.ic_launcher_background).into(holder.mBinding.ivPersonPic);
        }

        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHomeAdapterCommunicator.onItemCLicked(holder.mBinding.tvPersonName.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return resposneData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        SearchItemAdapterBinding mBinding;

        ViewHolder(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }
    }

    public void addDataToList(@NonNull List<PagesItem> resposneData, boolean isDataRefreshed) {
        if (isDataRefreshed) {
            this.resposneData.clear();
            this.resposneData.addAll(resposneData);
            notifyDataSetChanged();
        } else {
            this.resposneData.addAll(resposneData);
            notifyItemRangeInserted(this.resposneData.size() - 1, resposneData.size());
        }
    }
}
