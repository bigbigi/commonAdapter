package rx.recycleview;


import com.big.adapter.Adapter;

public class TestLayoutInfo implements Adapter.Layout {
    public TestLayoutInfo(int layoutId) {
        this.layoutId = layoutId;
    }

    public int layoutId;
    @Override
    public int getLayout() {
        return layoutId;
    }
}
