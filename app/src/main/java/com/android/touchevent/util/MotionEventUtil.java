package com.android.touchevent.util;

import android.view.MotionEvent;

/**
 * Description: <MotionEventUtil><br>
 * Author:      gxl<br>
 * Date:        2019/5/10<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MotionEventUtil {
    public static String getMotionEventName(MotionEvent event) {
        // ▼ 注意这里使用的是 getAction()，先埋一个小尾巴。
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 手指按下
                return "ACTION_DOWN";
            case MotionEvent.ACTION_MOVE:
                // 手指移动
                return "ACTION_MOVE";
            case MotionEvent.ACTION_UP:
                // 手指抬起
                return "ACTION_UP";
            case MotionEvent.ACTION_CANCEL:
                // 事件被拦截
                return "ACTION_CANCEL";
            case MotionEvent.ACTION_OUTSIDE:
                // 超出区域
                return "ACTION_OUTSIDE";
        }
        return "ACTION_NULL";
    }
}
