    //第一次点击的时间
    private long firstClickTime;

        //设置点击事件 - 实现 双击事件
        llDragView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstClickTime > 0) {
                    long secondClickTime = SystemClock.uptimeMillis();
                    long dTime = secondClickTime - firstClickTime;
                    if (dTime < 500) {
                        firstClickTime = 0;
                        return;
                    }
                }
                firstClickTime = SystemClock.uptimeMillis();//已开机时间
            }
        });

        //设置点击事件 - 实现 多击事件

        final long[] mHits = new long[3];//在这里改多击
        llDragView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * src 拷贝的原数组
                 * srcPos 拷贝原数组从那个地方开始
                 * dst 拷贝到那个数组
                 * dstPos 从那个地方开始拷贝
                 * length 拷贝数组元素的个数
                 */
                System.arraycopy(mHits, 1, mHits, 0, mHits.length-1);
                mHits[mHits.length-1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
                    Toast.makeText(DragViewActivity.this, "三击事件", Toast.LENGTH_SHORT).show();
                }
            }
        });