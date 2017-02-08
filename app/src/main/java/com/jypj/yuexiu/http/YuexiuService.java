package com.jypj.yuexiu.http;

import com.jypj.yuexiu.model.AwardM;
import com.jypj.yuexiu.model.ChangeRoleM;
import com.jypj.yuexiu.model.FiledealM;
import com.jypj.yuexiu.model.FlashM;
import com.jypj.yuexiu.model.LoginM;
import com.jypj.yuexiu.model.NoticeM;
import com.jypj.yuexiu.model.QuMyProM;
import com.jypj.yuexiu.model.QueryAward;
import com.jypj.yuexiu.model.QueryMsgM;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/1.
 */

public interface YuexiuService {

//    @GET("login")
//    Observable<LoginM> getlogin(@Query("loginname") String loginname, @Query("password") String password);

    @GET("login")
    Observable<HttpResult<LoginM.DataEntity>> getlogin(@Query("loginname") String loginname, @Query("password") String password);

    @GET("changeRole")
    Observable<HttpResult<List<ChangeRoleM.DataEntity>>> changeRole(@Query("roleType") String roleType);

    @GET("query/notice")
    Observable<HttpResult<List<NoticeM.DataEntity>>> notice();

    @GET("query/flash")
    Observable<HttpResult<List<FlashM.DataEntity>>> flash();

    @GET("query/quMyPro")
    Observable<HttpResult<List<QuMyProM.DataEntity>>> quMyPro();

    @GET("query/exam")
    Observable<HttpResult<List<NoticeM.DataEntity>>> exam();

    @GET("queryMsg")
    Observable<HttpResult<List<QueryMsgM.DataEntity>>> queryMsg();

    @POST("queryOA/filedeal")
    Observable<HttpResult<List<FiledealM.DataEntity>>> filedeal();

    @POST("queryOA/dealing")
    Observable<HttpResult<List<FiledealM.DataEntity>>> dealing();

    @POST("queryOA/fileNotice")
    Observable<HttpResult<List<FiledealM.DataEntity>>> fileNotice();

    @GET("query/award")
    Observable<HttpResult<List<QueryAward.DataBean>>> award();

    @GET("query/award")
    Observable<HttpResult<List<AwardM.DataEntity>>> award(String id);

}
