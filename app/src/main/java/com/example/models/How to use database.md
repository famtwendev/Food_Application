# Tạo biến truy cập
+ Tạo 1 biến dưới giao diện interface ApiService
+ Hiện thực nó bằng ApiClient
+ Dùng biến vừa tạo để gọi hàm truy vấn database

# Ví dụ về hàm lấy all bil gán vào recyclerView
```shell
ApiService apiService = ApiClient.getClient().create(ApiService.class);
 apiService.getAllBills().enqueue(new Callback<List<BillModel>>() {
            @Override
            public void onResponse(Call<List<BillModel>> call, Response<List<BillModel>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    List<BillModel> bills = response.body();
                    billAdapter = new BillAdapter(bills);
                    recyclerView.setAdapter(billAdapter);
                    tvNoData.setVisibility(View.GONE);  // Ẩn thông báo không có dữ liệu
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    tvNoData.setVisibility(View.VISIBLE);  // Hiển thị thông báo không có dữ liệu
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<BillModel>> call, Throwable t) {
                Log.e("API Error", "Lỗi kết nối: " + t.getMessage());
                tvNoData.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });
```

#Thông tin danh sách tên hàm
    + `getAllBills()`: Hàm này được sử dụng để lấy tất cả các hóa đơn
    + `enqueue()`: Hàm này được sử dụng để thực hiện yêu cầu API
    + `onResponse()`: Hàm này được sử dụng để xử lý phản hồi từ API
    + `onFailure()`: Hàm này được sử dụng để xử lý lỗi khi thực hiện yêu cầu API

    - các hàm bên dưới theo cú pháp <Kiểu trả trị> <Tên hàm> (<Các đối số đầu vào>);

    + Lấy tất cả khách hàng
    <List<CustomerModels>> getAllCustomer();

    + Lấy khách hàng theo ID
    <CustomerModels> getCustomer(int idCustomers);

    + Thêm khách hàng mới
    <CustomerModels> addCustomer(CustomerModels customerModels);

    + Cập nhật khách hàng
    <Void> updateCustomer(int idCustomers,CustomerModels customerModels);

    + Xóa khách hàng
    <Void> deleteCustomer( int idCustomers);

    //
    //FoodModels
    //

    + Lấy tất cả thực phẩm
    <List<FoodModels>> getAllFood();

    + Lấy thực phẩm theo ID
    <FoodModels> getFood( int idFoods);

    + Thêm khách thực phẩm
    <FoodModels> addFood(FoodModels foodModels);

    + Cập nhật thực phẩm
    <Void> updateFood(int idFoods, FoodModels foodModels);

    + Xóa thực phẩm
    <Void> deleteFood(int idFoods);

    //
    //SupplierModels
    //

    + Lấy tất cả nhà cung cấp
    <List<SupplierModels>> getAllSuppliers();

    + Lấy nhà cung cấp theo ID
    <SupplierModels> getSuppliers(int idSuppliers);

    + Thêm nhà cung cấp mới
    <SupplierModels> addSuppliers(SupplierModels supplierModels);

    + Cập nhật nhà cung cấp
    <Void> updateSuppliers(int idSuppliers, SupplierModels supplierModels);

    + Xóa nhà cung cấp
    <Void> deleteSuppliers(int idSuppliers);

    //
    //VourcherModels
    //

    + Lấy tất cả nhà cung cấp
    <List<VourcherModels>> getAllVouchers();

    + Lấy nhà cung cấp theo ID
    <VourcherModels> getVouchers(int idVouchers);

    + Thêm nhà cung cấp mới
    <VourcherModels> addVouchers( VourcherModels vourcherModels);

    + Cập nhật nhà cung cấp
    <Void> updateVouchers(int idSuppliers, VourcherModels vourcherModels);

    + Xóa nhà cung cấp
    <Void> deleteVouchers(int idVouchers);