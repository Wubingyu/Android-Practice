package com.example.diarylist;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.diarylist.CustomView.SlidingMenu;

import java.util.ArrayList;

public class RecyclerView_activity extends AppCompatActivity {

    private static final String TAG = "RecyclerView_activity";

    public static final int add_Item = 1;
    public static final String ACTIVITY_RESULT_TITLE = "title";
    public static final String ACTIVITY_RESULT_CONTEXT = "context";
    /**
     ArrayList一定要new出地址空间的，不然是不能做出正确的add行为的
     */
    ArrayList<RecyclerView_item> recyclerView_items = new ArrayList<RecyclerView_item>();
    FloatingActionButton FAB;
    RecyclerView recyclerView;

    //关于SlidingMenu的touch事件
//    long downTime = 0;


    RecyclerView_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        FAB = (FloatingActionButton) findViewById(R.id.myFAB);

        initRecyclerList();


        //出现了一个面只有一个子项的情况，这是为什么呢?
        //因为子项的布局中，把子项的布局高度设为match_parent
        recyclerView = (RecyclerView) findViewById(R.id.recyclerList);

        //SwipeRecyclerView的设置尝试
        //侧滑菜单
//        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
//        recyclerView.setOnItemMenuClickListener(mMenuItemClickListener);

        //配置adapter和layoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerView_Adapter(recyclerView_items);
        recyclerView.setAdapter(adapter);

/**
 *         尝试在activity中处理子项控件的点击事件，以期能同时响应侧滑事件
 *         成了个功！！！！！！！！！！！！！！！！！！！！！同时响应了
 */
//可以放在adapter设置完之后进行是因为，一直到click事件发生之后才会去判断mOnItemClickLitener有没有初始化
        //下面这个是匿名类new interface 自动建立一个匿名类
        adapter.setOnItemClickLitener(new RecyclerView_Adapter.OnItemClickLitener()
        {
            @Override
            public void onImageClick(RecyclerView_item item) {

            Intent intent = new Intent(RecyclerView_activity.this, ItemImg_activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtra("img_id", item.getImg_id());
            startActivity(intent);

            }

            @Override
            public void onContextClick(RecyclerView_item item) {
                Intent intent = new Intent(RecyclerView_activity.this, ItemContext_activity.class);
                intent.putExtra("context", item.getContext());
                intent.putExtra("title", item.getTitle());
                intent.putExtra("img_id", item.getImg_id());
                startActivity(intent);
            }

            @Override
            public void onMenuRemoveClick(int position) {
                adapter.removeItem(position);
            }

            @Override
            public void onMenuTopClick(int position, Boolean is_Top) {
                if (is_Top) {
                    //如果是取消置顶：
                    adapter.removeTopItem(position);
                } else {
                    adapter.addTopItem(position);
                }
            }
        });


        FAB.setOnClickListener(v -> {
            Log.d(TAG, "FAB click ");
            Intent intent = new Intent(RecyclerView_activity.this, New_ItemContext_activity.class);

//            RecyclerView_item item = new RecyclerView_item("sakura", "ayane sakura", R.drawable.a2);
//            adapter.addItem(adapter.getItemCount(), item);
            //不知道为什么没有成功，即使传入的是直接定义的假数据，先注释掉，然后尝试直接click后添加list，看看是不是add方法有问题。

            //经过上一行代码的测试，add方法没有问题。
            //直接启动new-itemcontext活动，在活动的onCreate中调用finish()方法后就正确添加了。那么？为什么我使用退格键没有用呢？理论上来说退格键不是就调用了onDestroy方法吗，难道finish不是调用onDestroy方法的吗？
            //不是的，参考：https://blog.csdn.net/yelangjueqi/article/details/9466347
            startActivityForResult(intent, add_Item);
        });
    }

    /**
     * 获取new_context的返回，并更新list
     * 更新list使用adapter的addItem的方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "requestCode is: " + requestCode + " AND resultCode is: " + resultCode + " AND RESULT_OK is: " + RESULT_OK);
        switch (requestCode) {
            //一定要加final case后面使用常量
            case add_Item:
                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "进行add item操作了");
                    String title = data.getStringExtra(ACTIVITY_RESULT_TITLE);
                    String context = data.getStringExtra(ACTIVITY_RESULT_CONTEXT);

                    RecyclerView_item recyclerView_item = new RecyclerView_item(title, context, R.drawable.a2);
                    //更新的item都要在置顶item的下面
                    adapter.addItem(0 + adapter.getTop_number(), recyclerView_item);
                    recyclerView.smoothScrollToPosition(0);

                }
                break;
        }
    }

    private void initRecyclerList() {
        for (int i=0; i<1; i++){
            RecyclerView_item recyclerView_item = new RecyclerView_item("title1", "1", R.drawable.a1);
            RecyclerView_item recyclerView_item1 = new RecyclerView_item("title2", "2", R.drawable.a2);
            RecyclerView_item recyclerView_item2 = new RecyclerView_item("title3", "3", R.drawable.a3);
            RecyclerView_item recyclerView_item3 = new RecyclerView_item("title4", "4", R.drawable.a4);
            RecyclerView_item recyclerView_item4 = new RecyclerView_item("title5", "5", R.drawable.a5);
            RecyclerView_item recyclerView_item5 = new RecyclerView_item("title6", "6", R.drawable.a6);
            RecyclerView_item recyclerView_item6 = new RecyclerView_item("title7", "7", R.drawable.a7);
            RecyclerView_item recyclerView_item7 = new RecyclerView_item("title8", "8", R.drawable.a8);
            RecyclerView_item recyclerView_item8 = new RecyclerView_item("title9", "9", R.drawable.a9);
            RecyclerView_item recyclerView_item9 = new RecyclerView_item("title10", "10", R.drawable.a10);
            RecyclerView_item recyclerView_item10 = new RecyclerView_item("title11", "11", R.drawable.a11);
            RecyclerView_item recyclerView_item11 = new RecyclerView_item("title12", "12", R.drawable.a12);
            RecyclerView_item recyclerView_item12 = new RecyclerView_item("title13", "13", R.drawable.a13);
            RecyclerView_item recyclerView_item13 = new RecyclerView_item("title14", "14", R.drawable.a14);
            RecyclerView_item recyclerView_item14 = new RecyclerView_item("title15", "15", R.drawable.a15);
            RecyclerView_item recyclerView_item15 = new RecyclerView_item("title16", "16", R.drawable.a16);
            RecyclerView_item recyclerView_item16 = new RecyclerView_item("title17", "17", R.drawable.a17);
            RecyclerView_item recyclerView_item17 = new RecyclerView_item("title18", "18", R.drawable.a18);
            RecyclerView_item recyclerView_item18 = new RecyclerView_item("title19", "19", R.drawable.a19);
            RecyclerView_item recyclerView_item19 = new RecyclerView_item("title20", "10", R.drawable.a20);
            recyclerView_items.add(recyclerView_item);
            recyclerView_items.add(recyclerView_item1);
            recyclerView_items.add(recyclerView_item2);
            recyclerView_items.add(recyclerView_item3);
            recyclerView_items.add(recyclerView_item4);
            recyclerView_items.add(recyclerView_item5);
            recyclerView_items.add(recyclerView_item6);
            recyclerView_items.add(recyclerView_item7);
            recyclerView_items.add(recyclerView_item8);
            recyclerView_items.add(recyclerView_item9);
            recyclerView_items.add(recyclerView_item10);
            recyclerView_items.add(recyclerView_item11);
            recyclerView_items.add(recyclerView_item12);
            recyclerView_items.add(recyclerView_item13);
            recyclerView_items.add(recyclerView_item14);
            recyclerView_items.add(recyclerView_item15);
            recyclerView_items.add(recyclerView_item16);
            recyclerView_items.add(recyclerView_item17);
            recyclerView_items.add(recyclerView_item18);
            recyclerView_items.add(recyclerView_item19);
        }
    }


//    /**
//     * 菜单创建器，在Item要创建菜单的时候调用。
//     */
//    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
//        @Override
//        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
//            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
//
//            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
//            // 2. 指定具体的高，比如80;
//            // 3. WRAP_CONTENT，自身高度，不推荐;
//            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//
//            // 添加左侧的，如果不添加，则左侧不会出现菜单。
////            {
////                SwipeMenuItem addItem = new SwipeMenuItem(RecyclerView_activity.this).setBackground(R.drawable.selector_green)
////                        .setImage(R.drawable.ic_action_add)
////                        .setWidth(width)
////                        .setHeight(height);
////                swipeLeftMenu.addMenuItem(addItem); // 添加菜单到左侧。
////
////                SwipeMenuItem closeItem = new SwipeMenuItem(RecyclerView_activity.this).setBackground(R.drawable.selector_red)
////                        .setImage(R.drawable.ic_action_close)
////                        .setWidth(width)
////                        .setHeight(height);
////                swipeLeftMenu.addMenuItem(closeItem); // 添加菜单到左侧。
////            }
//
//            // 添加右侧的，如果不添加，则右侧不会出现菜单。
//            {
//                SwipeMenuItem deleteItem = new SwipeMenuItem(RecyclerView_activity.this).setBackground(R.drawable.selector_red)
//                        .setImage(R.drawable.ic_action_delete)
//                        .setText("删除")
//                        .setTextColor(Color.WHITE)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
//
//                SwipeMenuItem toTopItem = new SwipeMenuItem(RecyclerView_activity.this).setBackground(R.drawable.selector_green)
//                        .setText("置顶")
//                        .setImage(R.drawable.ic_action_add)
//                        .setTextColor(Color.WHITE)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(toTopItem); // 添加菜单到右侧。
//            }
//        }
//    };
//
//
//    /**
//     * RecyclerView的Item的Menu点击监听。
//     */
//    private OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {
//        @Override
//        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
//            Log.d(TAG, "click SwipeMenu");
//            menuBridge.closeMenu();
//
//            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
//            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
//
//            if (direction == SwipeRecyclerView.RIGHT_DIRECTION) {
//                Log.d(TAG, "onItemClick: Right");
//                Toast.makeText(RecyclerView_activity.this, "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
//                        .show();
//
//                if (menuPosition == 0) {
//                    adapter.removeItem(position);
//                } else if (menuPosition == 1) {
//                    adapter.addTopItem(position);
//                }
//
//            } else if (direction == SwipeRecyclerView.LEFT_DIRECTION) {
//                Log.d(TAG, "onItemClick: Left");
//                Toast.makeText(RecyclerView_activity.this, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//    };
}
