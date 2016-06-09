package tm.davidwang.dwprivatetraineranimation;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout explore_re,arrow_re,mianview,down_re;
    final int duration = 500;
    private ImageView card0,card1,card2,card3,bgup,explore,arrow_img,add_img,reflash_img,blueTooth_img;
    private FrameLayout  card4;
    private FrameLayout  card5;
    private LinearLayout cardLayout;
    private LinearLayout cardLayout2;
    private TextView text01,text02;
    private BlueTooth blueTooth;
    public int state=1;


    //心的
    public static BluetoothSocket bluetoothSocket = null;
    public static Boolean connect_result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blueTooth = new BlueTooth(this);
        findID();
        inData();
    }

    private void findID(){
        mianview = (RelativeLayout)findViewById(R.id.mianview);
        down_re = (RelativeLayout)findViewById(R.id.down_re);
        explore_re = (RelativeLayout)findViewById(R.id.explore_re);
        arrow_re = (RelativeLayout)findViewById(R.id.arrow_re);
        mianview.setOnClickListener(this);
        bgup = (ImageView)findViewById(R.id.bgup);
        card0 = (ImageView)findViewById(R.id.card0);
        card1 = (ImageView)findViewById(R.id.card1);
        card2 = (ImageView)findViewById(R.id.card2);
        card3 = (ImageView)findViewById(R.id.card3);
        card4 = (FrameLayout)findViewById(R.id.card4);
        card5 = (FrameLayout)findViewById(R.id.card5);
        explore = (ImageView)findViewById(R.id.explore);
        arrow_img = (ImageView)findViewById(R.id.arrow_img);
        add_img = (ImageView)findViewById(R.id.add_img);
        cardLayout = (LinearLayout)findViewById(R.id.cardLayout);
        cardLayout2 = (LinearLayout)findViewById(R.id.cardLayout2);
        text01 = (TextView)findViewById(R.id.text01);
        text02 = (TextView)findViewById(R.id.text02);
        reflash_img = (ImageView)findViewById(R.id.reflash_img);
        blueTooth_img = (ImageView)findViewById(R.id.blueTooth_img);
    }

    private void inData(){
        ObjectAnimator.ofFloat(arrow_re, "translationY", 0, dip2px(85)).start();
       // ObjectAnimator.ofFloat(bgup, "translationY", 0, dip2px(33) - 1280).start();
        ObjectAnimator.ofFloat(bgup, "translationY", 0, - 1280).start();
        ObjectAnimator.ofFloat(explore_re, "scaleX", 1, 0).start();
        ObjectAnimator.ofFloat(card0, "translationX", 0, dip2px(430)).start();
        ObjectAnimator.ofFloat(card1, "translationX", 0, dip2px(450)).start();
        ObjectAnimator.ofFloat(card2, "translationX", 0, dip2px(470)).start();
        ObjectAnimator.ofFloat(card3, "translationX", 0, dip2px(490)).start();
        ObjectAnimator.ofFloat(card0, "scaleX", 1, 0.7f).start();
        ObjectAnimator.ofFloat(card1, "scaleX", 1, 0.7f).start();
        ObjectAnimator.ofFloat(card2, "scaleX", 1, 0.7f).start();
        ObjectAnimator.ofFloat(card3, "scaleX", 1, 0.7f).start();
        ObjectAnimator.ofFloat(card0, "scaleY", 1, 0.7f).start();
        ObjectAnimator.ofFloat(card1, "scaleY", 1, 0.7f).start();
        ObjectAnimator.ofFloat(card2, "scaleY", 1, 0.7f).start();
        ObjectAnimator.ofFloat(card3, "scaleY", 1, 0.7f).start();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                showView();
                Bottom();
                Top();
            }
        }, 300);  //这个handler相当于一个定时器，0.3秒就救运行一次runnable里面的run方法a

    }

    private void showView(){
        down_re.setVisibility(View.VISIBLE);
        bgup.setVisibility(View.VISIBLE);
        explore_re.setVisibility(View.VISIBLE);
        arrow_re.setVisibility(View.VISIBLE);
        card0.setVisibility(View.VISIBLE);
        card1.setVisibility(View.VISIBLE);
        card2.setVisibility(View.VISIBLE);
        card3.setVisibility(View.VISIBLE);
    }

    private void Top(){
        ObjectAnimator.ofFloat(card0, "translationX", dip2px(430), 0).setDuration(duration*2 - 400).start();
        ObjectAnimator.ofFloat(card0, "scaleX", 0.7f,1).setDuration(duration * 2 - 400).start();
        ObjectAnimator.ofFloat(card0, "scaleY", 0.7f,1).setDuration(duration * 2 - 400).start();
        ObjectAnimator.ofFloat(card1, "translationX", dip2px(450), 0).setDuration(duration * 2 - 300).start();
        ObjectAnimator.ofFloat(card1, "scaleX", 0.7f, 1).setDuration(duration*2 - 300).start();
        ObjectAnimator.ofFloat(card1, "scaleY", 0.7f,1).setDuration(duration * 2 - 300).start();
        ObjectAnimator.ofFloat(card2, "translationX", dip2px(470), 0).setDuration(duration * 2 - 200).start();
        ObjectAnimator.ofFloat(card2, "scaleX", 0.7f, 1).setDuration(duration * 2 - 200).start();
        ObjectAnimator.ofFloat(card2, "scaleY", 0.7f, 1).setDuration(duration * 2 - 200).start();
        ObjectAnimator.ofFloat(card3, "translationX", dip2px(490), 0).setDuration(duration * 2 - 100).start();
        ObjectAnimator.ofFloat(card3, "scaleX", 0.7f,1).setDuration(duration*2 - 100).start();
        ObjectAnimator.ofFloat(card3, "scaleY", 0.7f,1).setDuration(duration*2 - 100).start();
        ObjectAnimator.ofFloat(bgup, "translationY", dip2px(33) - 1280, 0).setDuration(duration * 2 - 100).start();
    }

    private void Bottom() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(arrow_re, "translationY", dip2px(85), 0)
        );
        set.setDuration(duration * 2 - 200).start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                BottomOpen();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void BottomOpen(){
        explore_re.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(arrow_re, "scaleX", 1, 0.6f),
                ObjectAnimator.ofFloat(arrow_re, "scaleY", 1, 0.6f),
                ObjectAnimator.ofFloat(explore_re, "scaleX", 0, 1),
                ObjectAnimator.ofFloat(arrow_re, "translationX", 0, dip2px(83))
        );
        set.setDuration(duration).start();
    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.mianview:
//                ObjectAnimator.ofFloat(arrow_re, "translationX",dip2px(83),0).start();
//                ObjectAnimator.ofFloat(arrow_re, "scaleX",0.6f,1).start();
//                ObjectAnimator.ofFloat(arrow_re, "scaleY",0.6f,1).start();
//                inData();
                //如果需要完整动画，请屏蔽：
                  newAnimator();
                mianview.setEnabled(false);

                break;
        }
    }

    private void newAnimator(){
//        ObjectAnimator.ofFloat(explore_re, "scaleY", 1.0f, 1.25f).setDuration(duration).start();
        ObjectAnimator.ofFloat(explore_re, "scaleX", 1.0f, 0.1f).setDuration(duration).start();
        ObjectAnimator.ofFloat(explore_re, "translationX", 0.0f, dip2px(117)).setDuration(duration + 100).start();
        ObjectAnimator.ofFloat(explore_re, "alphe", 1.0f, 0.0f).setDuration(duration).start();
        ObjectAnimator.ofFloat(explore_re, "scaleY", 1.0f,1.25f).setDuration(duration).start();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                add_img.setVisibility(View.VISIBLE);
                AddImgAnimator();
                MoveTopAnimator();
                MoveTopAnimator2();
            }
        }, duration - 300);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(arrow_img, "scaleX", 0.6f, 0.0f),
                ObjectAnimator.ofFloat(arrow_img, "scaleY", 0.6f, 0.0f),
                ObjectAnimator.ofFloat(arrow_re, "scaleX", 0.6f, 1.0f),
                ObjectAnimator.ofFloat(arrow_re, "scaleY", 0.6f, 1.0f),
                ObjectAnimator.ofFloat(arrow_re, "translationX",dip2px(83),dip2px(130)),

                ObjectAnimator.ofFloat(explore, "scaleX", 0.6f, 0.0f),
                ObjectAnimator.ofFloat(explore, "scaleY", 0.6f, 0.0f)
        );
        set.setDuration(duration*2).start();
//        set.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                add_img.setVisibility(View.VISIBLE);
//                AddImgAnimator();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
    }

    private void AddImgAnimator(){
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(add_img, "rotation", 0, 180),
                ObjectAnimator.ofFloat(add_img, "scaleX", 0.8f, 1.2f),
                ObjectAnimator.ofFloat(add_img, "scaleY", 0.8f, 1.2f)
        );
        set.setDuration(duration + 300).start();

        //设置小按钮弹出动画
        add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //先设置我们的两个view变出来
                reflash_img.setVisibility(View.VISIBLE);
                blueTooth_img.setVisibility(View.VISIBLE);
                if(state==0){
                    state=1;
                }else {
                    state=0;
                }
                //旋转变大再变小
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(add_img, "rotation", 180, 360),
                        ObjectAnimator.ofFloat(add_img, "scaleX", 1.2f, 1.6f, 1.2f),
                        ObjectAnimator.ofFloat(add_img, "scaleY", 1.2f, 1.6f, 1.2f)
                );
                set.setDuration(duration + 300).start();
                if(state==0){
                    ObjectAnimator.ofFloat(reflash_img, "translationX", dip2px(130),dip2px(50),dip2px(40)).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(reflash_img, "translationY", 0,-dip2px(60),-dip2px(48)).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(reflash_img, "scaleX", 0,1.2f,1f).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(reflash_img, "scaleY", 0,1.2f,1f).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(reflash_img, "rotation", 0,360).setDuration(duration + 300).start();
                }else{
                    ObjectAnimator.ofFloat(reflash_img, "translationX", dip2px(40),dip2px(50),dip2px(130)).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(reflash_img, "translationY", -dip2px(48),-dip2px(60),0).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(reflash_img, "scaleX", 1f,1.2f,0).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(reflash_img, "scaleY", 1f,1.2f,0).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(reflash_img, "rotation", 360,0).setDuration(duration + 300).start();
                }

                if(state==0){
                    ObjectAnimator.ofFloat(blueTooth_img, "translationX", dip2px(130),dip2px(90),dip2px(86)).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(blueTooth_img, "translationY", 0,-dip2px(91),-dip2px(82)).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(blueTooth_img, "scaleX", 0,1.2f,1f).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(blueTooth_img, "scaleY", 0,1.2f,1f).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(blueTooth_img, "rotation", 0,360).setDuration(duration + 300).start();
                }else{
                    ObjectAnimator.ofFloat(blueTooth_img, "translationX", dip2px(86),dip2px(90),dip2px(130)).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(blueTooth_img, "translationY", -dip2px(82),-dip2px(91),0).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(blueTooth_img, "scaleX", 1f,1.2f,0).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(blueTooth_img, "scaleY", 1f,1.2f,0).setDuration(duration + 300).start();
                    ObjectAnimator.ofFloat(blueTooth_img, "rotation", 360,0).setDuration(duration + 300).start();
                }


            }
        });

        reflash_img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"正在刷新", Toast.LENGTH_SHORT).show();
                reflash();
            }
        });

        blueTooth_img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
             //   Toast.makeText(MainActivity.this,"正在连接蓝牙，请稍等", Toast.LENGTH_SHORT).show();
                blueTooth.connect();
                reflash();
            }
        });
    }





    private void MoveTopAnimator(){
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                //ObjectAnimator.ofFloat(bgup, "translationY", -1280 + dip2px(120)),
                ObjectAnimator.ofFloat(bgup, "translationY", -1280-dip2px(120) ),
                ObjectAnimator.ofFloat(cardLayout, "translationY", -1280 - 500)
        );
        set.setDuration(duration + 300).start();
    }

    private void MoveTopAnimator2() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                //ObjectAnimator.ofFloat(bgup, "translationY", -1280 + dip2px(120)),
               // ObjectAnimator.ofFloat(card4, "translationY",0,-1280),
               // ObjectAnimator.ofFloat(card5, "translationY",0,-1280),
                ObjectAnimator.ofFloat(card4, "translationY",dip2px(800),0),
                ObjectAnimator.ofFloat(card5, "translationY",dip2px(800),0),
                ObjectAnimator.ofFloat(card4, "scaleY", 1.5f,1.0f),
                ObjectAnimator.ofFloat(card5, "scaleY", 1.5f,1.0f)
        );
        new Handler().postDelayed(new Runnable() {
            public void run() {
                card4.setVisibility(View.VISIBLE);
                card5.setVisibility(View.VISIBLE);
            }
        }, duration - 300);
        set.setDuration(duration+500 ).start();
    }

    private void reflash(){
        text01.setText(blueTooth.inData());
    }
}
