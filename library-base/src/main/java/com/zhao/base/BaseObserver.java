package com.zhao.base;

import android.text.TextUtils;
import com.zhao.base.http.bean.BaseBean;
import com.zhao.base.utils.NetUtils;
import io.reactivex.observers.DisposableObserver;

/**
 * @author rg65544
 * @date 2018/6/14
 */
public abstract class BaseObserver<T> extends DisposableObserver<BaseBean<T>> {

    @Override
    public void onError(Throwable e){
        if(!NetUtils.isNetworkAvailable()){
            onError(-9998,"网络失败");
        }
        else{
            String msg = e.getMessage();
            if(TextUtils.isEmpty(msg)){
                msg = e.getCause().getMessage();
            }
            onError(-9999, msg);
        }
    }

    @Override
    public void onComplete(){
    }

    @Override
    public void onNext(BaseBean<T> value){
        if(value.isSuccess()){
            doOnNext(value);
        }
        else{
            if(value.getCode() == 40000006){ //如果token过去，调起登录页面进行登录
//                Httper.getInstance().setSessionId("");
//                ARouter.getInstance().build(RouterUrl.Mine.Login).navigation();
            }
            onError(value.getCode(), value.getMessage());
        }
    }

    protected abstract void doOnNext(BaseBean<T> t);

    protected void onError(int code, String msg){

    }
}
