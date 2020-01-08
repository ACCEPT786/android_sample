package com.moon.widget.recycler

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * @author ry
 * @date 2019-12-21
 * 封装RecyclerView和StateView
 */
class StateRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
//    companion object {
//        const val LINEAR_LAYOUT_MANAGER = 0
//        const val GRID_LAYOUT_MANAGER = 1
//        const val STAGGERED_LAYOUT_MANAGER = 2
//    }
//
//    @IntDef(LINEAR_LAYOUT_MANAGER, GRID_LAYOUT_MANAGER, STAGGERED_LAYOUT_MANAGER)
//    @Retention(AnnotationRetention.SOURCE)
//    internal annotation class LayoutManager
//
////    val recyclerView: RecyclerView
////    val stateView: XmStateView
//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var layoutOrientation: Int = 0
//    private var spanCount: Int = 0
//
//    private var emptyText: String? = null
//    private var loadingText: String? = null
//    private var retryText: String? = null
//
////
////    init {
////        View.inflate(context, R.layout.xm_state_recycler, this)
////        recyclerView = findViewById(R.id.recyclerView)
////        stateView = findViewById(R.id.stateView)
////        val typedArray =
////            context.obtainStyledAttributes(attrs, R.styleable.StateRecyclerView, defStyleAttr, 0)
////        initRecyclerViewFromAttr(typedArray)
////        initStateViewFromAttr(typedArray)
////        typedArray.recycle()
////
////        //设置初始化语句
////        stateView.setOnInflateListener { viewType, view ->
////            view.findViewById<TextView>(R.id.tvStateTextView)?.let {
////                it.text = when (viewType) {
////                    XmStateView.EMPTY -> emptyText
////                    XmStateView.RETRY -> loadingText
////                    XmStateView.LOADING -> retryText
////                    else -> null
////                }
////            }
////
////        }
////    }
////
////
////    private fun initRecyclerViewFromAttr(typedArray: TypedArray) {
////        layoutOrientation = typedArray.getInt(R.styleable.StateRecyclerView_android_orientation,
////            RecyclerView.VERTICAL)
////        spanCount = typedArray.getInteger(R.styleable.StateRecyclerView_srvSpanCount, 0)
////
////        val layoutManagerInt =
////            typedArray.getInt(R.styleable.StateRecyclerView_srvLayoutManager, LINEAR_LAYOUT_MANAGER)
////        setLayoutManagerFromAttr(layoutManagerInt)
////    }
////
////    private fun initStateViewFromAttr(typedArray: TypedArray) {
////        emptyText = typedArray.getString(R.styleable.StateRecyclerView_srvEmptyText)
////        loadingText = typedArray.getString(R.styleable.StateRecyclerView_srvLoadingText)
////        retryText = typedArray.getString(R.styleable.StateRecyclerView_srvRetryText)
////
////        val emptyLayout = typedArray.getResourceId(R.styleable.StateRecyclerView_srvEmptyLayout, 0)
////        val loadingLayout =
////            typedArray.getResourceId(R.styleable.StateRecyclerView_srvLoadingLayout, 0)
////        val retryLayout = typedArray.getResourceId(R.styleable.StateRecyclerView_srvRetryLayout, 0)
////        if (emptyLayout != 0) {
////            stateView.setEmptyResource(emptyLayout)
////        }
////        if (loadingLayout != 0) {
////            stateView.setLoadingResource(loadingLayout)
////        }
////        if (retryLayout != 0) {
////            stateView.setRetryResource(retryLayout)
////        }
////    }
//
//    private fun setLayoutManagerFromAttr(managerValue: Int) {
//        layoutManager = when (managerValue) {
//            LINEAR_LAYOUT_MANAGER -> LinearLayoutManager(context, layoutOrientation, false)
//            GRID_LAYOUT_MANAGER -> GridLayoutManager(context, spanCount, layoutOrientation, false)
//            STAGGERED_LAYOUT_MANAGER -> StaggeredGridLayoutManager(spanCount, layoutOrientation)
//            else -> LinearLayoutManager(context, layoutOrientation, false)
//        }
//        recyclerView.layoutManager = layoutManager
//    }
//
//    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
//        recyclerView.layoutManager = layoutManager
//    }
//
//

}