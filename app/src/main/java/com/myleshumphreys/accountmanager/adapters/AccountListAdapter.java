package com.myleshumphreys.accountmanager.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myleshumphreys.accountmanager.R;
import com.myleshumphreys.accountmanager.models.Account;

import java.util.List;

public class AccountListAdapter extends BaseAdapter {

    List<Account> accountList;
    LayoutInflater inflater;

    public AccountListAdapter(Activity context, List<Account> accountList) {
        this.accountList = accountList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_account_list_item, null);
            mViewHolder = new MyViewHolder();
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        mViewHolder.association = detail(convertView, R.id.textViewAccountAssociation, accountList.get(position).getName());

        return convertView;
    }

    private TextView detail(View v, int resId, String text) {
        TextView tv = (TextView) v.findViewById(resId);
        tv.setText(text);
        return tv;
    }

    private class MyViewHolder {
        TextView association;
    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Account getItem(int position) {
        return accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}