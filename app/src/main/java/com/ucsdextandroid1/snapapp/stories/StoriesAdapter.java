package com.ucsdextandroid1.snapapp.stories;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.snapapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjaylward on 2019-04-20
 */
public class StoriesAdapter extends RecyclerView.Adapter {

    //TODO The first thing your should do is finish the StoriesListItem class at the bottom

    private List<StoriesListItem> items = new ArrayList<>();
    private StoryCardViewHolder.StoryCardClickListener clickListener;

    public void setItems(Context context, List<Story> stories) {
        items.clear();

        //TODO add title item to our list of StoriesListItems
        //hint, you can use using context.getString(R.string.stories)) to get a String
        if (context != null){
            items.add(new StoriesListItem(context.getString(R.string.stories)));
        }

        //TODO add all of the story items to the list of StoriesListItems
        for (Story story : stories){
            items.add(new StoriesListItem(story));
        }

        //TODO let the adapter know that  the data has changed
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO return the correct view holder for each viewType. We want to return the
        // StoriesSectionTitleViewHolder for our title and the StoryCardViewHolder for our items.
        Log.d("StoriesAdapter", String.valueOf(viewType));
        switch(viewType){
            case StoriesListItem.TYPE_TITLE:
                return StoriesSectionTitleViewHolder.inflate(parent);
            case StoriesListItem.TYPE_STORY:
                StoryCardViewHolder viewholder = StoryCardViewHolder.inflate(parent);
                viewholder.setCallback(clickListener);
                return viewholder;
                //return StoryCardViewHolder.inflate(parent);
            default:
                throw new IllegalArgumentException("Unrecognized view type");
        }

        //return StoryCardViewHolder.inflate(parent).setCallback(currentCallBack);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TODO bind the title or the story to the correct view holder
        if(holder instanceof StoriesSectionTitleViewHolder)
            ((StoriesSectionTitleViewHolder) holder).bind(getTitle(position));
        else if(holder instanceof StoryCardViewHolder)
            ((StoryCardViewHolder) holder).bind(getStory(position));
    }

    private String getTitle(int index){
        return items.get(index).getTitle();
    }

    private Story getStory(int index){
        return items.get(index).getStory();
    }

    @Override
    public int getItemCount() {
        // TODO return the correct item count
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        //TODO return the correct view type
        return items.get(position).getType();
    }

    //TODO add a method that returns the correct span for each item type. It should take in the
    // int position and return an int representing either 1 or 2 depending on if the item is a title
    // or a story card item.
    public int getSpanSize(int position) {
        switch(getItemViewType(position)){
            case StoriesListItem.TYPE_TITLE:
                return 2;
            case StoriesListItem.TYPE_STORY:
                return 1;
        }
        return 0;
    }

    //TODO add a custom interface called Callback that extends the click listener defined on the StoriesCardViewHolder
    public void callBack(StoryCardViewHolder.StoryCardClickListener clickListener){
        this.clickListener = clickListener;
    }

    //TODO finish creating a class that holds both the story and the title
    private class StoriesListItem {

        public static final int TYPE_TITLE = 1;
        public static final int TYPE_STORY = 2;

        // you will need to add 2 constructors, one that takes in a String title, and another that
        // takes in a Story story. We need this data class to represent all the possibilities for
        // our list.
        @Nullable private final String title;
        @Nullable private final Story story;
        final private int type;

        public StoriesListItem(@Nullable String context) {
            this.title = context;
            this.story = null;
            this.type = TYPE_TITLE;
        }

        public StoriesListItem(@NonNull Story story) {
            this.story = story;
            this.title = null;
            this.type = TYPE_STORY;
        }

        @Nullable
        public String getTitle(){
            return title;
        }

        public Story getStory(){
            return story;
        }

        public int getType(){
            return type;
        }
    }

}
