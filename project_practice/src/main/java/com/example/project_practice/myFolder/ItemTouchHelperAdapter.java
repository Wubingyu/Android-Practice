package com.example.project_practice.myFolder;

/**
 * 一个可以将这些回调方法传递出去的接口。
 *
 * 从解耦的角度考虑，我们需要一个接口来实现Adapter和ItemTouchHelper之间涉及数据的操作，
 * 因为ItemTouchHelper在完成触摸的各种动画后，就要对Adapter的数据进行操作，
 * 比如侧滑删除操作，最后需要调用Adapter的notifyItemRemove()方法来移除该数据。
 * 因此我们可以把数据操作的部分抽象成一个接口方法，让ItemTouchHelper.Callback调用该方法即可。具体如下：
 *
 */
public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    //这个是对应滑动方法，你想要滑动打开就写滑动打开的方法，想要滑动删除就写删除的方法。
    void onItemDismiss(int position);
}
