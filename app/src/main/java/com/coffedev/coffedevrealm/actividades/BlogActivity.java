package com.coffedev.coffedevrealm.actividades;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.coffedev.coffedevrealm.R;
import com.coffedev.coffedevrealm.configuraciones.RealmAdmin;
import com.coffedev.coffedevrealm.dominio.entidades.Blog;
import com.squareup.picasso.Picasso;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Github;

public class BlogActivity extends AppCompatActivity {

    String blogid;
    ImageView headerImage;
    MarkdownView bodynNew;
    Blog blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        RealmAdmin.open(this);
        this.headerImage = findViewById(R.id.header);
        this.bodynNew = findViewById(R.id.markdown_view);
        bodynNew.addStyleSheet(new Github());

        blogid = getIntent().getStringExtra("idBlog");
        blog = RealmAdmin.BlogDao().getBlog(blogid);
        bodynNew.loadMarkdown(blog.getContent());
        Picasso.get().load(blog.getImageBlog().getUrl()).into(headerImage);


        //setTitle("oeoe");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            RealmAdmin.close();
        } catch (Exception e) {
        }
    }
}
