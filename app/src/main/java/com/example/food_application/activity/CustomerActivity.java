package com.example.food_application.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_application.R;
import com.example.food_application.databinding.ActivityCustomerBinding;
import com.example.food_application.helper.ManagementUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class CustomerActivity extends AppCompatActivity {

    ActivityCustomerBinding binding;

    private static final int LOGIN_REQUEST_CODE = 1;
    private ManagementUser managementUser;

    private CalendarView calendarView;
    private TextView tvXuNotification;
    private boolean isXuReceived = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEventsForMenu();
        managementUser = new ManagementUser(this);
        addEvents();

    }

    private void addEvents() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa thông tin người dùng
                managementUser.destroy();
                // Cập nhật giao diện
                binding.btnLogin.setVisibility(View.VISIBLE);
                binding.txtusername.setVisibility(View.GONE);
                binding.imvUser.setImageResource(R.drawable.ic_customer);
                binding.btnLogout.setVisibility(View.GONE);
            }
        });

        binding.btnvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });

        //Them o day
        binding.btnshopeecoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.setContentView(R.layout.activity_shopeexu);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                CalendarView calendarView = dialog.findViewById(R.id.calendarView);
                TextView tvXuNotification = dialog.findViewById(R.id.tvXuNotification);
                TextView tvTotalXu = dialog.findViewById(R.id.tvTotalXu);

                // Retrieve and display current Xu
                int currentXu = 200;
                tvTotalXu.setText("Tổng Xu hiện tại: " + currentXu);

                Calendar currentCalendar = Calendar.getInstance();
                int currentYear = currentCalendar.get(Calendar.YEAR);
                int currentMonth = currentCalendar.get(Calendar.MONTH);
                int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);

                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);

                        Calendar currentDate = Calendar.getInstance();
                        currentDate.set(currentYear, currentMonth, currentDay);

                        if (selectedDate.before(currentDate)) {
                            Toast.makeText(CustomerActivity.this, "Ngày này đã qua, không thể nhận Xu!", Toast.LENGTH_SHORT).show();
                        } else if (selectedDate.after(currentDate)) {
                            Toast.makeText(CustomerActivity.this, "Chưa đến thời gian nhận Xu!", Toast.LENGTH_SHORT).show();
                        } else if (!isXuReceived) {
                            // Update Xu and display
                            int newXuValue = currentXu + 100;
                            tvTotalXu.setText("Tổng Xu hiện tại: " + newXuValue);
                            tvXuNotification.setText("Bạn đã nhận được 100 Shopee Xu vào ngày " + dayOfMonth + "/" + (month + 1) + "/" + year);
                            Toast.makeText(CustomerActivity.this, "Bạn nhận được 100 Shopee Xu!", Toast.LENGTH_SHORT).show();
                            isXuReceived = true; // Prevent claiming Xu multiple times
                        } else {
                            Toast.makeText(CustomerActivity.this, "Bạn đã nhận Xu cho ngày này rồi!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.findViewById(R.id.btnbacklogin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        binding.btnmyaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });
        binding.btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });

        binding.btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });

        //Them o day
        String policyContent = "1. Chính sách giao hàng: \n\n" +
                "Thời gian giao hàng từ 30 đến 60 phút tùy vào khu vực.\n\n" +
                "2. Chính sách hủy đơn hàng: \n\n" +
                "Bạn có thể hủy đơn hàng trong vòng 5 phút sau khi đặt hàng.\n\n" +
                "3. Chính sách hoàn tiền: \n\n" +
                "Hoàn tiền trong trường hợp đơn hàng không đúng hoặc có vấn đề về chất lượng.\n\n" +
                "4. Chính sách khuyến mãi: \n\n" +
                "Các mã giảm giá có thể áp dụng theo điều kiện nhất định.\n\n" +
                "5. Chính sách bảo vệ người dùng: \n\n" +
                "ShopeeFood cam kết bảo vệ quyền lợi của người dùng.\n\n" +
                "6. Chính sách bảo mật: \n\n" +
                "Thông tin cá nhân của khách hàng được bảo mật tuyệt đối và không được chia sẻ cho bên thứ ba.\n\n" +
                "7. Chính sách đổi trả: \n\n" +
                "Khách hàng có thể yêu cầu đổi trả sản phẩm trong vòng 24 giờ nếu sản phẩm có lỗi hoặc không đúng với đơn đặt hàng.\n\n" +
                "8. Chính sách thanh toán: \n\n" +
                "Hỗ trợ nhiều phương thức thanh toán như tiền mặt, thẻ tín dụng, và ví điện tử. Các khoản thanh toán đều được bảo mật an toàn.\n\n" +
                "9. Chính sách chăm sóc khách hàng: \n\n" +
                "Dịch vụ chăm sóc khách hàng hoạt động 24/7 để hỗ trợ và giải đáp các thắc mắc của khách hàng.\n\n" +
                "10. Chính sách phản hồi: \n\n" +
                "Chúng tôi luôn lắng nghe phản hồi từ khách hàng để cải thiện chất lượng dịch vụ.";


        binding.btnpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.setContentView(R.layout.activity_policy);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                TextView tvPolicyContent = dialog.findViewById(R.id.tv_policy_content);
                tvPolicyContent.setText(policyContent);

                dialog.findViewById(R.id.btnbacklogin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        binding.btnsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.setContentView(R.layout.activity_helper);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.findViewById(R.id.btnbacklogin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        //Them o day
        String aboutUsContent = "Food là một nền tảng giao đồ ăn hàng đầu tại Việt Nam, mang đến cho người dùng trải nghiệm ẩm thực tiện lợi và phong phú. Chúng tôi kết nối hàng ngàn nhà hàng, quán ăn với hàng triệu khách hàng trên cả nước, giúp họ dễ dàng tiếp cận các món ăn ngon chỉ bằng vài thao tác đơn giản trên điện thoại.\n\n" +
                "Sứ mệnh của chúng tôi là không ngừng cải thiện và đổi mới dịch vụ, mang lại sự hài lòng tối đa cho khách hàng cũng như hỗ trợ các đối tác nhà hàng phát triển bền vững. Chúng tôi cam kết cung cấp dịch vụ giao đồ ăn nhanh chóng, an toàn và chất lượng cao.\n\n" +
                "Tại Food, chúng tôi tin rằng ẩm thực không chỉ là ăn uống, mà còn là cách kết nối mọi người lại gần nhau hơn. Chúng tôi luôn nỗ lực để mỗi bữa ăn của bạn trở thành một trải nghiệm thú vị và đáng nhớ.\n\n" +
                "Chúng tôi chân thành cảm ơn sự tin tưởng và ủng hộ của quý khách hàng trong suốt thời gian qua và cam kết sẽ tiếp tục phát triển để mang lại những dịch vụ tốt nhất.";

        binding.btnaboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.setContentView(R.layout.activity_aboutme);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                TextView tvAboutmeContent = dialog.findViewById(R.id.tv_aboutme_content);
                tvAboutmeContent.setText(aboutUsContent);

                dialog.findViewById(R.id.btnbacklogin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }

    @Override
    public void onResume() {
        Log.e("CustomerFragment", "onResume");
        super.onResume();
        loadData();
    }


    private void loadData() {
        if (managementUser.gethasData()) {
            binding.btnLogin.setVisibility(View.GONE);
            binding.btnLogout.setVisibility(View.VISIBLE);
            binding.txtusername.setVisibility(View.VISIBLE);
            binding.txtusername.setText(managementUser.getFullname());

            int drawableResourceId = binding.getRoot().getContext().getResources().getIdentifier(managementUser.getPicture(), "drawable", binding.getRoot().getContext().getPackageName());

            binding.imvUser.setImageResource(drawableResourceId);
        } else {
            Log.e("CustomerFragment", "hasData : NULL");
        }
        binding.getRoot().requestLayout();
    }

    private void addEventsForMenu() {
        binding.bottomNavigation.setSelectedItemId(R.id.menu_customer);
        MenuItem customerItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_customer);
        customerItem.setIcon(R.drawable.ic_user_filled);


        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(CustomerActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    Intent intent = new Intent(CustomerActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(CustomerActivity.this, CartActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_notify) {
                    Intent intent = new Intent(CustomerActivity.this, NotificationActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_customer) {
                    return true;
                } else {
                    return false; // Return false if no item matches
                }
                return true; // Return true if item was handled
            }
        });
    }
}