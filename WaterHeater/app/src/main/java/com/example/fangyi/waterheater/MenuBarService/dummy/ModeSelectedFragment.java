package com.example.fangyi.waterheater.MenuBarService.dummy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fangyi.waterheater.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModeSelectedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModeSelectedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModeSelectedFragment extends Fragment {


    private ListView lvMode;

    // 0.智能模式, 1.节能模式, 2.速热模式, 3.加热模式,4.保温模式,5.安全模式
    /** 模式图片资源数组. */
    private int[] ivResources = { R.mipmap.pattern_intelligence_icon,
            R.mipmap.pattern_energy_icon, R.mipmap.power_fullpower,
            R.mipmap.pattern_heating_icon,
            R.mipmap.pattern_temperature_icon, R.mipmap.pattern_safe_icon };

    /** 模式文字资源数组. */
    private int[] tvResources = { R.string.pattern_intelligence,
            R.string.pattern_energy, R.string.power_fullpower,
            R.string.pattern_heating, R.string.pattern_temperature,
            R.string.pattern_safe };



















    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ModeSelectedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModeSelectedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModeSelectedFragment newInstance(String param1, String param2) {
        ModeSelectedFragment fragment = new ModeSelectedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mode_selected, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
