1.

public class MainActivity extends SlidingFragmentActivity {
	private SlidingMenu slidingMenu;

	public void onCreate(Builder savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowsFeature(Window.FEATURE_NO_TITLE);

		//内容页布局
		setContentView(R.layout.content);
		//侧拉栏目对应的帧布局的布局文件
		setBehindContentView(R.layou.menu_frame);//FrameLayout,  用Fragment填充
		slidingMenu = getSlidingMenu();
		
		//侧拉栏目所在方向- 左边
		slidingMenu.setMode(SlidingMenu.LEFT);//LEFT LEFT_RIGHT RIGHT SLIDING_CONTENT

		//侧拉条目设置相应的宽度
		//设置内容页content的宽度
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//180dp 拉出来以后内容页的宽度
		//设置侧拉条目的宽度
		slidingMenu.setBehindWidth(i);//像素
		//设置分割线
		slidingMenu.setShadowDrawable(R.drawable.shadow);//渐变颜色
		//给分割线设置宽度
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);//5dp
		// //给分割线设置宽度 - 传递像素
		// slidingMenu.setShadowWidth();
		
		//侧拉栏目的触摸范围
		/**
		 * SlidingMenu.TOUCHMODE_FULLSCREEN 全屏拖拽
		 * SlidingMenu.TOUCHMODE_MARGIN		侧边拖拽
		 * SlidingMenu.TOUCHMODE_NONE		不能拖拽
		 */
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏


		/*******************************************************************/
		//其他共用

		//设置右侧侧边栏
		slidingMenu.setSecondaryMenu(R.layout.right_menu_frame);
		//右侧侧拉栏目设置的分割线
		slidingMenu.setSecondaryShadowDrawable(R.drawable.shadow);
		//侧拉栏目所在方向- 右边
		slidingMenu.setMode(SlidingMenu.RIGHT);//LEFT LEFT_RIGHT RIGHT SLIDING_CO
		
		/*******************************************************************/


		//通过Fragmen去替换左侧侧拉条目对应的布局内容
		MenuFragment menuFragment = new MenuFragment();
		//替换帧布局内容
		getSupporFragmentManager();
		//开启事务（数据库开启事务，效率高一些）
		.beginTransaction()
		//替换帧布局对应的id,替换的fragemnt,tag,
		.replace(R.id.menu, menuFragment, "MENU");
		.commit;
		
	}


	public void switchFragment(BaseFragment baseFragment) {
        //暴露替换帧布局内容的操作，
        if (baseFragment != null) {
             getSupportFragmentManager()
             .beginTransaction()
             .replace(R.id.content_frame, baseFragment, "HOME")
             .commit();

             //缩回左侧侧拉条目
             slidingMenu.toggle();
        }
    }

}


/**
 * 通过Fragmen去替换左侧侧拉条目对应的布局内容
 */
import android.support.v4.app.Fragment;

public class MenuFragment extends Fragment {

	private View view;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 返回指定Fragment对象的展示形式，类比Activity,ContentView()，对应的View要自己加载进来
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载当前Fragment对应的布局
        // TextView textView = new TextView(getActivity());
        // textView.setText("这是左侧侧拉条目");
       	
       	view = View.inflater(getActivity(), R.layout.list_view, null);

        return view;
    }

    /**
     * 数据填充UI的操作
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);


    	ListView listView = (ListView) view.findViewById(R.id.list_view);
    	listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, R.id.text1, getData()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseFragment baseFragment = null;
                //在此处出发替换内容页内容操作
                switch (position) {
                    case 0:
                        //创建第0个条目对应的Fragment对象，然后去替换内容页面帧布局内部内容
                        baseFragment = new Fragment0();
                        break;
                    case 1:
                    	baseFragment = new Fragment1();
                        break;
                    case 2:
                    	baseFragment = new Fragment2();
                        break;
                    case 3:
                    	baseFragment = new Fragment3();
                        break;
                    case 4:
                    	baseFragment = new Fragment4();
                        break;
                    case 5:
                    	baseFragment = new Fragment5();
                        break;
                }
	            //BaseFragment替换MainActivity对应内容页的帧布局内部内容
	            ////要在MainActivity去替换内容页操作方法，MenuFragment直接调用
	            // getActivity().getSupportFragmentManager()
	            // .beginTransaction()
	            // .replace(R.id.content_frame, baseFragment, "HOME")
	            // .commit();
	            switchFragment(baseFragment);
            }
        });
    }

	private void switchFragment(BaseFragment baseFragment) {
	    //调用MainActivity上的方法做替换，MainActivity对象
	    if (getActivity() instanceof MainActivity) {
	        //getActivity 是 MainActivity
	        ((MainActivity) getActivity()).switchFragment(baseFragment);
	    }
	}

    public List<String> getData() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("fragment1");
        arrayList.add("fragment2");
        arrayList.add("fragment3");
        arrayList.add("fragment4");
        arrayList.add("fragment5");

        return arrayList;
    }

}


/**
 * 创建第0个条目对应的Fragment对象，然后去替换内容页面帧布局内部内容
 */

public class Fragment0 extends BaseFragment {
}
public class Fragment1 extends BaseFragment {
}
public class Fragment2 extends BaseFragment {
}
public class Fragment3 extends BaseFragment {
}
public class Fragment4 extends BaseFragment {
}
public class Fragment5 extends BaseFragment {
}

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        //获取当前对象对应的类似名称
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}




/**

 */




