package com.moon.popup;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lxj.xpopup.interfaces.XPopupImageLoader;

import java.io.File;

/**
 * **************************************************
 * ******************_ooOoo_
 * *****************o8888888o
 * *****************88\" . \"88
 * *****************(| -_-  |)
 * *****************O\\  =  /O
 * **************____/`---'\\____
 * ************.'  \\\\|     |//  `.
 * ~~~~~~~~~~~/  \\\\|||  :  |||//  \\
 * ~~~~~~~~~~~/  _||||| -:- |||||-  \\
 * ~~~~~~~~~~~|   | \\\\\\  -  /// |   |
 * ~~~~~~~~~~~| \\_|  ''\\---/''  |   |
 * ~~~~~~~~~~~\\  .-\\__  `-`  ___/-. /
 * ~~~~~~~~~___`. .'  /--.--\\  `. . __
 * ~~~~~~.\"\" '<  `.___\\_<|>_/___.'  >'\"\".
 * ~~~~~| | :  `- \\`.;`\\ _ /`;.`/ - ` : | |
 * ~~~~~\\  \\ `-.   \\_ __\\ /__ _/   .-` /  /
 * ======`-.____`-.___\\_____/___.-`____.-'======
 * ******************`=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
 * ***************佛祖保佑*********永无BUG
 *
 * @ gmd:
 * @ 项目名称: Android Studio.
 * @ 日        期:2019/7/5 10:46
 * @ ClassName:      ImageLoader
 * @ 作        者:尼玛.wang
 * **************************************************
 */

public class ImageLoader implements XPopupImageLoader {
    @Override
    public void loadImage(int position, @NonNull Object url, @NonNull ImageView imageView) {
        //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
        Glide.with(imageView).load(url).apply(new RequestOptions().error(R.drawable.img_fail_to_load).override(Target.SIZE_ORIGINAL)).into(imageView);
    }

    @Override
    public File getImageFile(@NonNull Context context, @NonNull Object uri) {
        try {
            return Glide.with(context).downloadOnly().load(uri).submit().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

