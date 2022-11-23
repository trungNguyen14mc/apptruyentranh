package edu.fpt.apptruyentranh.retrofit;

import edu.fpt.apptruyentranh.model.UserModel;
import edu.fpt.apptruyentranh.model.commentModel;
import edu.fpt.apptruyentranh.model.truyentranhModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiAppDocTruyen {
    @GET("getAllTruyen.php")
    Observable<truyentranhModel> getalltruyen();
    @POST("getuser.php")
    @FormUrlEncoded
    Observable<UserModel> getUser(
            @Field("username") String username,
            @Field("pass") String pass
    );
    @POST("dangKyuser.php")
    @FormUrlEncoded
    Observable<UserModel> dangky(
            @Field("username") String username,
            @Field("pass") String password,
            @Field("email") String email,
            @Field("fullname") String fullname
    );
    @POST("getComment.php")
    @FormUrlEncoded
    Observable<commentModel> getComment(
            @Field("idtruyen") String username


    );
    @POST("postcomment.php")
    @FormUrlEncoded
    Observable<UserModel> post_comment(
            @Field("idtruyen") int idtruyen,
            @Field("noidung") String noidung,
            @Field("iduser") int iduser
    );
}
