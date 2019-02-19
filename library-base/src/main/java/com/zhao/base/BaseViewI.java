package com.zhao.base;

/**
 *
 */
public interface BaseViewI {

    /**
     * 数据加载失败
     * tag==1,正常错误，可以只显示toast
     * tag=0,非正常错误，需要考虑显示错误界面
     * */
    void showDataError(String errorMessage, int tag);

    /**
     * 显示加载进度
     * */
    void showProgress();

    /**
     * 显示加载进度
     * */
    void hideProgress();

    /**
     * 点击重新加载
     * */
    void onReload();

    /**
     * 显示加载中视图
     * */
    void showLoadingView();

    /**
     *  显示无网络视图
     * */
    void showNetErrorView();

    /**
     *   没有加载到内容
     * */
    void showEmptyView(String msg);

    /**
     * 显示内容View
     * */
    void showContent();

}
