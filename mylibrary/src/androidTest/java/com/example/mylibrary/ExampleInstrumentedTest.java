package com.example.mylibrary;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest implements UiWatcher {
    protected UiDevice mUIDevice = null;
    protected Context mContext = null;//Context是android中上下文
    int delayTime = 100;  //默认等待获取View的时间
    float sleepTime = 1.2f;  //默认休眠时间

    @Before
    public void setUp() throws Exception{
        mUIDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());  //获得device对象
        mContext = InstrumentationRegistry.getTargetContext();
        Log.d("zyh","setup");
    }

    @After
    public void end(){
        Log.d("zyh","after");

    }

    @Test
    public void start() throws Exception{
        String[] values = new String[]{"末日方糖","sss","www","www","wwww","www"};
        // Context of the app under test.
        Log.d("zyh","test");
        clickUiByID("cn.owhat:id/remain_price");
        clickUiByTextContains("880");
//        MuiScrollable scrollable = new MuiScrollable(new UiSelector().resourceId("cn.owhat:id/sc_layout").className("android.widget.ScrollView"));
//        scrollable.scrollToEnd(1);
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().resourceId("cn.owhat:id/sc_layout").className("android.widget.ScrollView"));
        uiScrollable.flingForward();
        BySelector res = By.res("cn.owhat:id/ed_value");
        List<UiObject2> eds = mUIDevice.findObjects(res);
        for (int i = 0; i < eds.size() ; i++) {
            eds.get(i).setText(values[i]);
        }
        

    }

    @Override
    public boolean checkForCondition() {
        return false;
    }

    public boolean clickUiBySelector(BySelector bySelector) throws Exception {
        try {
            if (hasObject(bySelector)) {
                mUIDevice.findObject(bySelector).click();
                sleep(sleepTime);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public String getTextBySelector(BySelector bySelector) throws Exception {
        try {
            if (hasObject(bySelector)) {
                return mUIDevice.findObject(bySelector).getText();
            }
        } catch (Exception e) {
            throw e;
        }
        return "";
    }

    public String getDescBySelector(BySelector bySelector) throws Exception {
        try {
            if (hasObject(bySelector)) {
                return mUIDevice.findObject(bySelector).getContentDescription();
            }
        } catch (Exception e) {
            throw e;
        }
        return "";
    }

    public boolean clickUiBySelectorWaitNewWindow(BySelector bySelector) throws Exception {
        try {
            if (hasObject(bySelector)) {
                mUIDevice.findObject(bySelector).clickAndWait(Until.newWindow(), delayTime);
                sleep(sleepTime);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }


    public boolean clickUiBySelector(UiSelector uiSelector) throws Exception {
        try {
            if (mUIDevice.findObject(uiSelector).waitForExists(delayTime)) {
                mUIDevice.findObject(uiSelector).click();
                sleep(sleepTime);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public boolean hasObject(BySelector bySelector) {
        return mUIDevice.wait(Until.hasObject(bySelector), delayTime);
    }


    public boolean hasObject(BySelector bySelector, long delay) {
        return mUIDevice.wait(Until.hasObject(bySelector), delay);
    }


    public boolean clickUiByID(String id) {
        try {
            if (hasObject(By.res(id))) {
                mUIDevice.findObject(By.res(id)).click();
//                sleep(sleepTime);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public boolean clickUiByText(String text) {
        try {
            if (hasObject(By.text(text))) {
                mUIDevice.findObject(By.text(text)).click();
//                sleep(sleepTime);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public boolean clickUiByTextContains(String text) {
        try {
            if (hasObject(By.textContains(text))) {
                mUIDevice.findObject(By.textContains(text)).click();
                sleep(sleepTime);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public void sleep(float ss) {
        try {
            Thread.sleep((long) (ss * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
