package com.coffedev.coffedevrealm.viewHolders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffedev.coffedevrealm.R;
import com.coffedev.coffedevrealm.dominio.entidades.Blog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

public class ViewHolderBlog extends RecyclerView.ViewHolder {
    private MaterialTextView tituloBlog;
    private MaterialTextView descripcionBlog;
    private MaterialCardView cardBlog;


    public ViewHolderBlog(@NonNull View itemView) {
        super(itemView);
        cardBlog = itemView.findViewById(R.id.cardBlog);
        tituloBlog = itemView.findViewById(R.id.tituloBlog);
        descripcionBlog = itemView.findViewById(R.id.descripcionBlog);
    }


    public void bind(Blog blog) {
        tituloBlog.setText(blog.getTitle());
        //tituloBlog.setTextColor(Color.BLACK);
        descripcionBlog.setText(blog.getDescription());
    }


    public MaterialCardView getCardBlog() {
        return cardBlog;
    }

    public MaterialTextView getTituloBlog() {
        return tituloBlog;
    }

    public MaterialTextView getDescripcionBlog() {
        return descripcionBlog;
    }

    public void setTituloBlog(MaterialTextView tituloBlog) {
        this.tituloBlog = tituloBlog;
    }

    public void setCardBlog(MaterialCardView cardBlog) {
        this.cardBlog = cardBlog;
    }

    public void setDescripcionBlog(MaterialTextView descripcionBlog) {
        this.descripcionBlog = descripcionBlog;
    }

}
