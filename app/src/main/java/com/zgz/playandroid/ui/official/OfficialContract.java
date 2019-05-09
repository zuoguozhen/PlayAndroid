package com.zgz.playandroid.ui.official;

import com.zgz.playandroid.base.BasePresenter;
import com.zgz.playandroid.base.BaseView;
import com.zgz.playandroid.model.bean.OfficialAccount;

import java.util.List;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class OfficialContract {
    interface View extends BaseView {

        void setOfficialAccount(List<OfficialAccount> data);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
