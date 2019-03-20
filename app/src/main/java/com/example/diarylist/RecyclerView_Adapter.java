package com.example.diarylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.diarylist.CustomView.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder> {

    private static final String TAG = "RecyclerView_Adapter";

    //该list中，有多少item被标记为置顶项目 ，以后添加的item都会放在置顶项目的下方
    private int top_number = 0;

    //关于侧滑菜单中，侧滑一个新菜单，原先的菜单就要被关闭
    private SlidingMenu mOpenMenu;
    //避免同时划开多个item
//    private SlidingMenu mScrollingMenu;

    private List<RecyclerView_item> items;
    private Context context;

    /**
     * 把点击响应的事件放在activity中去响应，看看能不能同时响应侧滑事件和子项控件点击事件
     * //成了个功！！！！！！！！！！！！！！！！！！！！！同时响应了
     */
    public interface OnItemClickLitener {
        void onImageClick(RecyclerView_item item);

        void onContextClick(RecyclerView_item item);

        void onMenuRemoveClick(int position);

        void onMenuTopClick(int position, Boolean is_Top);
    }

    private OnItemClickLitener mOnItemClickLitener;

    //在activity中初始化mOnItemClickLitener
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    //初始化ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView menuRemove, menuTop;
//        SlidingMenu slidingMenu;
//        LinearLayout context;
//        String context;
//        String title;
//        int img_id;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.recycler_item_img);
            textView = (TextView) view.findViewById(R.id.recycler_item_text);
            menuRemove = (TextView) view.findViewById(R.id.menuRemove);
            menuTop = (TextView) view.findViewById(R.id.menuTop);
        }
    }

    //初始化源数据
    public RecyclerView_Adapter(ArrayList<RecyclerView_item> items) {
        this.items = items;
    }

    //在三个复写的方法中设置好ViewHolder

    /**
     * 初始化ViewHolder要绑定的XML子项布局文件
     * 定义子项布局文件的响应行为
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //并不理解为什么的View是什么情况。但是大概意思就是在这里初始化了对应的子项布局
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);

//        //在非activity中如何跳转到activity
//        //点击文字跳转到内容界面
//        viewHolder.textView.setOnClickListener(v -> {
//            //没有context初始化的时候，编译期不报错，但是运行期报错
//            context = v.getContext();
////            Activity activity = (Activity) v.getContext();
//            Intent intent = new Intent(context, ItemContext_activity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            //ViewHolder传递数据的方式，记住~
//            intent.putExtra("context", viewHolder.context);
//            intent.putExtra("title", viewHolder.title);
//            intent.putExtra("img_id", viewHolder.img_id);
//            context.startActivity(intent);
//        });
//
//        //点击图片查看大图
//        //似乎并不推荐这么处理点击事件？
//        viewHolder.imageView.setOnClickListener(v -> {
//            context = v.getContext();
//
//            Intent intent = new Intent(context, ItemImg_activity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            intent.putExtra("img_id", viewHolder.img_id);
//            context.startActivity(intent);
//        });

        //完成ViewHolder的定义
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RecyclerView_item item = items.get(i);
        //把正确的数据和每个子项布局绑定起来
        //绑定的方法是：正确设定ViewHolder中的部分就可以了，其他的部分库帮我们做好了
        viewHolder.imageView.setImageResource(item.getImg_id());
        viewHolder.textView.setText(item.getTitle());

        //如果是置顶控件的话
        if (item.getIs_top()) {
            //
            Log.d(TAG, "the item of :" + item.getTitle() + " isTop and to set BackgroundResource");
            //不能用setBackgroundColor
            viewHolder.textView.setBackgroundResource(R.color.gray_text);
            viewHolder.menuTop.setText("取消置顶");
        }else {
            viewHolder.textView.setBackgroundResource(R.color.white);
        }

//        //这部分是在干什么呢？似乎是把每个viewHolder的数据绑定好了，然后在onCreateViewHolder中绑定的响应事件，viewHolder就能够正确的把数据放进intent的Extra中了
        //很明显，当我们在activity中，使用item本身来进行响应的时候，这个viewHolder存储的数据就不需要了
//        viewHolder.context = item.getContext();
//        viewHolder.title = item.getTitle();
//        viewHolder.img_id = item.getImg_id();

        //尝试在activity中处理图片的响应事件

        if (mOnItemClickLitener != null) {
            viewHolder.imageView.setOnClickListener(v -> mOnItemClickLitener.onImageClick(item));
            viewHolder.textView.setOnClickListener(v -> mOnItemClickLitener.onContextClick(item));
            viewHolder.menuRemove.setOnClickListener(v -> mOnItemClickLitener.onMenuRemoveClick(i));
            viewHolder.menuTop.setOnClickListener(v -> mOnItemClickLitener.onMenuTopClick(i, item.getIs_top()));
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: items_size=" + items.size());
        return items.size();
    }


    /**
     * 效果：实现RecyclerView的添加
     * 过程：
     * 1、图片- 图片先不管了，先用固定的资源图片代替
     * 2、点击加号，进入文章编辑界面。
     * 3、点击文章编辑界面的完成按钮，RecyclerList更新
     *
     * @param position
     */
    public void addItem(int position, RecyclerView_item item) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * 删除
     *
     * @param position
     */
    public void removeItem(int position) {
        items.remove(position);
        notifyItemRangeChanged(0, getItemCount());
        notifyDataSetChanged();
    }

    /**
     * 置顶，并且更新textView的颜色
     * 这个地方啊，效率是不是太低了，感觉通知了很多次更新事件
     *
     * @param position
     */
    public void addTopItem(int position) {
        RecyclerView_item item = items.get(position);
        //把原先的item添加到开头
        items.add(0, item);
        //把之前位置的item删除掉，因为在开头加了一个item，所以position要+1
//        removeItem(position + 1);
        //设为置顶的item数量加1
        items.remove(position+1);
        top_number++;
        //改变item的置顶标识
        item.setIs_top(true);
        Log.d(TAG, "the item title is: " + item.getTitle() + ", and the top_number is: " + top_number);
        //通知更新颜色，能做到吗。。。
        //做到了！！更新了！！！！ 吃了一惊
        notifyDataSetChanged();
        for (RecyclerView_item item1 : items) {
            if (item1.getIs_top()) {
                Log.d(TAG, "the item of :" + item1.getTitle() + " isTop");
            }
        }
    }

    public void removeTopItem(int position) {
        RecyclerView_item item = items.get(position);

        //把原来在list中置顶的项目删掉
        items.remove(position);
        top_number--;
        //把这个Item设置到list的置顶后的位置
        items.add(0 + top_number, item);
        //更改置顶标识
        item.setIs_top(false);
        notifyDataSetChanged();
    }


    public int getTop_number() {
        return top_number;
    }

    /**
     * 记录打开的是哪个侧滑菜单view，方便关闭侧滑菜单
     *
     * @param slidingMenu
     */
    public void holdOpenMenu(SlidingMenu slidingMenu) {
        mOpenMenu = slidingMenu;
    }

    /**
     * 关闭之前打开的侧滑菜单
     */
    public void closeOpenMenu() {
        if (mOpenMenu != null && mOpenMenu.isOpen()) {
            mOpenMenu.closeMenu();
        }
    }


//    public SlidingMenu getScrollingMenu() {
//        return mScrollingMenu;
//    }
//
//    public void setScrollingMenu(SlidingMenu scrollingMenu) {
//        mScrollingMenu = scrollingMenu;
//    }
}
