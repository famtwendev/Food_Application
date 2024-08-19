package com.example.models;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    //
    //BillModels
    //

    //Lấy tất cả các hóa đơn
    @GET("api/bills")
    Call<ArrayList<BillModels>> getAllBills();

    // Lấy hóa đơn theo ID
    @GET("api/bills/{idBill}")
    Call<BillModels> getBill(@Path("idBill") int idBill);

    // Thêm hóa đơn mới
    @POST("api/bills")
    Call<BillModels> addBill(@Body BillModels billModels);

    // Cập nhật hóa đơn
    @PUT("api/bills/{idBill}")
    Call<Void> updateBill(@Path("idBill") int idBill, @Body BillModels billModels);

    // Xóa hóa đơn
    @DELETE("api/bills/{idBill}")
    Call<Void> deleteBill(@Path("idBill") int idBill);


    //
    //BillDetailModels
    //

    //Lấy tất cả các chi tiết hóa đơn
    @GET("/api/bill-details")
    Call<ArrayList<BillDetailModels>> getAllBillDetails();

    // Lấy chi tiết hóa đơn theo ID
    @GET("api/bill-details/{idBillDetails}")
    Call<BillDetailModels> getBillDetails(@Path("idDetails") int idDetails);

    // Thêm chi tiết hóa đơn mới
    @POST("api/bill-details")
    Call<BillDetailModels> addDetails(@Body BillDetailModels billDetailModels);

    // Cập nhật chi tiết hóa đơn
    @PUT("api/bill-details/{idBillDetails}")
    Call<Void> updateDetails(@Path("idDetails") int idDetails, @Body BillDetailModels billDetailModels);

    // Xóa chi tiết hóa đơn
    @DELETE("api/bill-details/{idBillDetails}")
    Call<Void> deleteDetails(@Path("idDetails") int idDetails);

    //
    //CategoryModels
    //

    //Lấy tất cả các chi tiết hóa đơn
    @GET("/api/categories")
    Call<ArrayList<CategoryModels>> getAllCategories();

    // Lấy chi tiết hóa đơn theo ID
    @GET("api/categories/{idCategories}")
    Call<CategoryModels> getCategories(@Path("idCategories") int idCategories);

    // Thêm chi tiết hóa đơn mới
    @POST("api/categories")
    Call<CategoryModels> addCategories(@Body CategoryModels categoryModels);

    // Cập nhật chi tiết hóa đơn
    @PUT("api/categories/{idCategories}")
    Call<Void> updateCategories(@Path("idCategories") int idCategories, @Body CategoryModels categoryModels);

    // Xóa chi tiết hóa đơn
    @DELETE("api/categories/{idCategories}")
    Call<Void> deleteCategories(@Path("idCategories") int idCategories);


    //
    //CustomerModels
    //

    //Lấy tất cả khách hàng
    @GET("/api/customers")
    Call<ArrayList<CustomerModels>> getAllCustomer();

    // Lấy khách hàng theo ID
    @GET("api/customers/{idCustomers}")
    Call<CustomerModels> getCustomer(@Path("idCustomers") String idCustomers);

    // Thêm khách hàng mới
    @POST("api/customers")
    Call<CustomerModels> addCustomer(@Body CustomerModels customerModels);

    // Cập nhật khách hàng
    @PUT("api/customers/{idCustomers}")
    Call<Void> updateCustomer(@Path("idCustomers") String idCustomers, @Body CustomerModels customerModels);

    // Xóa khách hàng
    @DELETE("api/customers/{idCustomers}")
    Call<Void> deleteCustomer(@Path("idCustomers") String idCustomers);

    //
    //FoodModels
    //

    //Lấy tất cả thực phẩm
    @GET("/api/foods")
    Call<ArrayList<FoodModels>> getAllFood();

    // Lấy thực phẩm theo ID
    @GET("api/foods/{idFoods}")
    Call<FoodModels> getFood(@Path("idFoods") int idFoods);

    // Thêm khách thực phẩm
    @POST("api/foods")
    Call<FoodModels> addFood(@Body FoodModels foodModels);

    // Cập nhật thực phẩm
    @PUT("api/foods/{idFoods}")
    Call<Void> updateFood(@Path("idFoods") int idFoods, @Body FoodModels foodModels);

    // Xóa thực phẩm
    @DELETE("api/foods/{idFoods}")
    Call<Void> deleteFood(@Path("idFoods") int idFoods);

    //
    //SupplierModels
    //

    //Lấy tất cả nhà cung cấp
    @GET("/api/suppliers")
    Call<ArrayList<SupplierModels>> getAllSuppliers();

    // Lấy nhà cung cấp theo ID
    @GET("api/suppliers/{idSuppliers}")
    Call<SupplierModels> getSuppliers(@Path("idSuppliers") int idSuppliers);

    // Thêm nhà cung cấp mới
    @POST("api/suppliers")
    Call<SupplierModels> addSuppliers(@Body SupplierModels supplierModels);

    // Cập nhật nhà cung cấp
    @PUT("api/suppliers/{idSuppliers}")
    Call<Void> updateSuppliers(@Path("idSuppliers") int idSuppliers, @Body SupplierModels supplierModels);

    // Xóa nhà cung cấp
    @DELETE("api/suppliers/{idSuppliers}")
    Call<Void> deleteSuppliers(@Path("idSuppliers") int idSuppliers);

    //
    //VourcherModels
    //

    //Lấy tất cả nhà cung cấp
    @GET("/api/vouchers")
    Call<ArrayList<VourcherModels>> getAllVouchers();

    // Lấy nhà cung cấp theo ID
    @GET("api/vouchers/{idVouchers}")
    Call<VourcherModels> getVouchers(@Path("idVouchers") int idVouchers);

    // Thêm nhà cung cấp mới
    @POST("api/vouchers")
    Call<VourcherModels> addVouchers(@Body VourcherModels vourcherModels);

    // Cập nhật nhà cung cấp
    @PUT("api/vouchers/{idVouchers}")
    Call<Void> updateVouchers(@Path("idVouchers") int idSuppliers, @Body VourcherModels vourcherModels);

    // Xóa nhà cung cấp
    @DELETE("api/vouchers/{idVouchers}")
    Call<Void> deleteVouchers(@Path("idVouchers") int idVouchers);

}
