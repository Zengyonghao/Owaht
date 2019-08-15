package com.example.mylibrary;

import android.support.test.uiautomator.Tracer;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

/**
 * Author：liaogulong
 * Time: 2018/7/6/006   16:25
 * Description：复写 滑动检索list
 */
public class MuiScrollable extends UiScrollable {
    /**
     * Constructor.
     *
     * @param container a {@link UiSelector} selector to identify the scrollable
     *                  layout element.
     * @since API Level 16
     */
    public MuiScrollable(UiSelector container) {
        super(container);
    }



    /**
     * 索引参数，直到找到，或者最大 扫动次数就停止
     *
     * @param selector
     * @return
     * @throws UiObjectNotFoundException
     */
    public boolean scrollIntoView(UiSelector selector) throws UiObjectNotFoundException {
        Tracer.trace(selector);
        // if we happen to be on top of the text we want then return here
        UiSelector childSelector = getSelector().childSelector(selector);
        if (exists(childSelector)) {
            return (true);
        } else {
            for (int x = 0; x < getMaxSearchSwipes(); x++) {
                boolean scrolled = scrollForward();
                if (exists(childSelector)) {
                    return true;
                }
                if (!scrolled) {
                    return false;
                }

            }
        }
        return false;
    }

    @Override
    public boolean scrollIntoView(UiObject obj) throws UiObjectNotFoundException {
        return scrollIntoView(obj.getSelector());
    }

    @Override
    public boolean scrollToEnd(int maxSwipes) throws UiObjectNotFoundException {
        return super.scrollToEnd(maxSwipes);
    }

    @Override
    public boolean scrollToBeginning(int maxSwipes) throws UiObjectNotFoundException {
        return super.scrollToBeginning(maxSwipes);
    }
}
