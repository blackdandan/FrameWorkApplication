package com.example.foxconn.frameworkapplication.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * 添加者: foxconn
 * 时间 :16-6-21.
 * 用于:所有Activity的基类
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {
    /**是否沉浸状态栏**/
    private boolean isSetStatusBar = true;
    /**是否允许全屏**/
    private boolean isAllowFullScreen = true;
    /**是否禁止转屏**/
    private boolean isAllowScreenRoate = false;
    /**返回键退出**/
    private boolean isBackExit = false;
    /**当前Activity渲染的视图View**/
    private View mContextView;
    /**日志输出标志**/
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * View点击事件
     * @param view 被点击的View
     */
    public abstract void widgetClick(View view);

    /**
     * 重写{@link Activity}的onCreate生命周期方法
     * @param savedInstanceState 保存的一些数据
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        initParams(bundle);
        View mView = bindView();
        if (null == mView){
            mContextView = LayoutInflater.from(this).inflate(bindLayout(),null);
        }else{
            mContextView = mView;
        }
        if (isAllowFullScreen){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (isSetStatusBar){
            steepStatusBar();
        }
        setContentView(mContextView);
        if (!isAllowScreenRoate){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        initView(mContextView);
        doBusiness(this);


    }
    /**
     * 沉浸状态栏
     */
    private void steepStatusBar(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.
                    FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.
                    FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * [初始化参数]
     * @param params
     */
    public abstract void initParams(Bundle params);

    /**
     * 绑定视图
     */
    public abstract View bindView();

    /**
     * 绑定布局
     */
    public abstract int bindLayout();

    /**
     * 初始化控件
     */
    public abstract void initView(View view);
    public abstract void doBusiness(Context mContext);

    /**
     *
     * @param view 从view获得
     * @param id 控件id
     * @return
     */
    public View getView(View view,int id){
        return view.findViewById(id);
    }

    /**
     *
     * @param clz
     */
    public void startActivity(Class<?> clz){
        startActivity(clz,null);
    }

    /**
     *
     * @param clz 要启动的Activity
     * @param bundle 传输的数据
     */
    public void startActivity(Class<?> clz,Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(this,clz);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     *
     * @param clz
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> clz,Bundle bundle,int requestCode){
        Intent intent = new Intent();
        intent.setClass(this,clz);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,requestCode);
    }


    /**
     * 实现{@link View.OnClickListener}接口
     * @param v 被点击的View
     */
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置是否全屏
     * @param isAllowFullScreen
     */
    public void setAllowFullScreen(boolean isAllowFullScreen){
        this.isAllowFullScreen = isAllowFullScreen;
    }

    /**
     * 设置是否沉浸状态栏
     * @param setStatusBar
     */
    public void setSetStatusBar(boolean setStatusBar) {
        isSetStatusBar = setStatusBar;
    }

    /**
     * 设置是否允许反转屏幕
     * @param allowScreenRoate
     */
    public void setAllowScreenRoate(boolean allowScreenRoate) {
        isAllowScreenRoate = allowScreenRoate;
    }

    /**
     * 设置是否连点两次退出
     * @param backExit
     */
    public void setBackExit(boolean backExit) {
        isBackExit = backExit;
    }
    private long exitTime;//退出的时间

    /**
     * 两秒内连续点击返回键退出程序
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&& event.getAction() == KeyEvent.ACTION_DOWN){
            if (isBackExit){
                if ((System.currentTimeMillis() - exitTime)>2000){
                    //Todo 之后换成用ToastUtil来弹出Toast 和 从String 获得文字
                    Toast.makeText(getApplicationContext(),"在按一次退出程序",Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                }else{
                    System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
