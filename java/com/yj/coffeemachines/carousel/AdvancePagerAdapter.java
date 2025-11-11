package com.yj.coffeemachines.carousel;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.yj.coffeemachines.carousel.AdvancePagerAdapter;
import com.yj.coffeemachines.helper.Tools;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class AdvancePagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private Context context;
    private boolean pause;
    private Thread thread;
    private ViewPager viewPager;
    private List<Advance> datas = new ArrayList();
    private List<View> list = new ArrayList();
    private int current = 0;
    private int time = 10000;
    private int lastPosition = -1;
    Runnable runnable = new AnonymousClass2();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.yj.coffeemachines.carousel.AdvancePagerAdapter$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        public /* synthetic */ void lambda$run$0$AdvancePagerAdapter$2() {
            AdvancePagerAdapter.this.viewPager.setCurrentItem(AdvancePagerAdapter.this.viewPager.getCurrentItem() + 1, true);
        }

        @Override // java.lang.Runnable
        public void run() {
            while (AdvancePagerAdapter.this.thread != null && !AdvancePagerAdapter.this.thread.isInterrupted()) {
                try {
                    Thread.sleep(1000L);
                    if (!AdvancePagerAdapter.this.pause && !(AdvancePagerAdapter.this.list.get(AdvancePagerAdapter.this.viewPager.getCurrentItem()) instanceof AdvanceVideoView)) {
                        AdvancePagerAdapter.this.current += 1000;
                    }
                    if (AdvancePagerAdapter.this.current >= AdvancePagerAdapter.this.time) {
                        AdvancePagerAdapter.this.viewPager.post(new Runnable() { // from class: com.yj.coffeemachines.carousel.-$$Lambda$AdvancePagerAdapter$2$iDQAu7QwZnpjltgf3KNuQ4r7_hI
                            @Override // java.lang.Runnable
                            public final void run() {
                                AdvancePagerAdapter.AnonymousClass2.this.lambda$run$0$AdvancePagerAdapter$2();
                            }
                        });
                        AdvancePagerAdapter.this.current = 0;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public AdvancePagerAdapter(Context context, ViewPager viewPager) {
        this.context = context;
        this.viewPager = viewPager;
    }

    private void addView(Advance advance) {
        if (advance.type.equals("1")) {
            AdvanceVideoView advanceVideoView = new AdvanceVideoView(this.context);
            advanceVideoView.setImage(advance.path);
            this.list.add(advanceVideoView);
        } else {
            AdvanceImageView advanceImageView = new AdvanceImageView(this.context);
            advanceImageView.setImage(advance.path);
            this.list.add(advanceImageView);
        }
    }

    private void startTimer() {
        if (this.thread == null) {
            this.thread = new Thread(this.runnable);
            this.thread.start();
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.list.get(i));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = this.list.get(i);
        viewGroup.addView(view);
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (i != 0 || this.list.size() <= 1) {
            return;
        }
        int i2 = this.lastPosition;
        if (i2 != -1 && i2 != this.viewPager.getCurrentItem() && (this.list.get(this.lastPosition) instanceof AdvanceVideoView)) {
            ((AdvanceVideoView) this.list.get(this.lastPosition)).setPause();
        }
        if (this.viewPager.getCurrentItem() < 1) {
            this.viewPager.setCurrentItem(this.datas.size(), false);
        } else if (this.viewPager.getCurrentItem() > this.datas.size()) {
            this.viewPager.setCurrentItem(1, false);
        }
        this.current = 0;
        if (this.list.get(this.viewPager.getCurrentItem()) instanceof AdvanceVideoView) {
            ((AdvanceVideoView) this.list.get(this.viewPager.getCurrentItem())).setVideo(new MediaPlayer.OnCompletionListener() { // from class: com.yj.coffeemachines.carousel.AdvancePagerAdapter.3
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    AdvancePagerAdapter.this.viewPager.setCurrentItem(AdvancePagerAdapter.this.viewPager.getCurrentItem() + 1, true);
                }
            });
        }
        this.lastPosition = this.viewPager.getCurrentItem();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
    }

    public void setData(List<Advance> list) {
        if (list.size() == 0) {
            return;
        }
        this.datas.clear();
        this.datas.addAll(list);
        this.list.clear();
        if (list != null) {
            addView(list.get(list.size() - 1));
            if (list.size() > 1) {
                Iterator<Advance> it2 = list.iterator();
                while (it2.hasNext()) {
                    addView(it2.next());
                }
                addView(list.get(0));
            }
        }
        this.viewPager.addOnPageChangeListener(this);
        notifyDataSetChanged();
        if (list.size() > 1) {
            this.viewPager.setCurrentItem(1);
            startTimer();
        }
        if (list.get(0).type.equals("1")) {
            final AdvanceVideoView advanceVideoView = (AdvanceVideoView) this.list.get(this.viewPager.getCurrentItem());
            advanceVideoView.setVideo(new MediaPlayer.OnCompletionListener() { // from class: com.yj.coffeemachines.carousel.AdvancePagerAdapter.1
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Tools.upLocalLogM2("播放完毕,开启下一轮播放");
                    if (AdvancePagerAdapter.this.datas.size() == 1) {
                        advanceVideoView.setRestart();
                    } else {
                        AdvancePagerAdapter.this.viewPager.setCurrentItem(AdvancePagerAdapter.this.viewPager.getCurrentItem() + 1, true);
                    }
                }
            });
        }
    }

    public void setDestroy() {
        this.pause = true;
        Thread thread = this.thread;
        if (thread != null) {
            thread.interrupt();
            this.thread = null;
        }
    }

    public void setPause() {
        this.pause = true;
        if (this.list.size() <= 0 || !(this.list.get(this.viewPager.getCurrentItem()) instanceof AdvanceVideoView)) {
            return;
        }
        ((AdvanceVideoView) this.list.get(this.viewPager.getCurrentItem())).setPause();
        Tools.upLocalLogM2("调用暂停1");
    }

    public void setResume() {
        this.pause = false;
        if (this.list.size() <= 0 || !(this.list.get(this.viewPager.getCurrentItem()) instanceof AdvanceVideoView)) {
            return;
        }
        ((AdvanceVideoView) this.list.get(this.viewPager.getCurrentItem())).setRestart();
        Tools.upLocalLogM2("调用start2");
    }
}
