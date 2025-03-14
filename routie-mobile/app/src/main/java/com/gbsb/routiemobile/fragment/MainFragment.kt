package com.gbsb.routiemobile.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gbsb.routiemobile.LoginFragment
import com.gbsb.routiemobile.R
import java.util.Calendar

class MainFragment : Fragment() {

    private var isNoticeBubbleVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // ✅ UI 요소 찾기
        val buttonProfile = view.findViewById<ImageButton>(R.id.btn_profile)
        val textView: TextView = view.findViewById(R.id.txt_nowdate)
        val btnSelectDate: ImageButton = view.findViewById(R.id.btn_selectdate)
        val buttonTest: Button = view.findViewById(R.id.buttontest)
        val btnBell: ImageButton = view.findViewById(R.id.btn_bell)
        val bubble2: ImageView = view.findViewById(R.id.img_noticefield)

        // ✅ 알림 버튼 클릭 시 토글
        btnBell.setOnClickListener {
            if (!isNoticeBubbleVisible) {
                bubble2.visibility = ImageView.VISIBLE
                isNoticeBubbleVisible = true
            } else {
                bubble2.visibility = ImageView.GONE
                isNoticeBubbleVisible = false
            }
        }

        // ✅ 프로필 버튼 클릭 시 SettingActivity 이동
        buttonProfile.setOnClickListener {
            val intent = Intent(requireContext(), SettingFragment::class.java)
            startActivity(intent)
        }

        // ✅ 테스트 버튼 클릭 시 LoginActivity 이동
        buttonTest.setOnClickListener {
            val intent = Intent(requireContext(), LoginFragment::class.java)
            startActivity(intent)
        }

        // ✅ 현재 날짜 가져오기
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // 1~12월 표시

        // ✅ 초기 값으로 현재 연/월 설정
        textView.text = "$year 년 $month 월"

        // ✅ 날짜 선택 버튼 클릭 시 DatePickerDialog 표시
        btnSelectDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, _ ->
                    textView.text = "$selectedYear 년 ${selectedMonth + 1} 월"
                },
                year,
                month - 1, // DatePicker는 0부터 시작하므로 -1 필요
                1
            )

            // 📌 일(day) 숨기기 (오류 방지)
            val dayPicker = datePickerDialog.datePicker.findViewById<View>(
                resources.getIdentifier("day", "id", "android")
            )
            dayPicker?.visibility = View.GONE // null 체크 추가

            datePickerDialog.show()
        }

        return view
    }
}