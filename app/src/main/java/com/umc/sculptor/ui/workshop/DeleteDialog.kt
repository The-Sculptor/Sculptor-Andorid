package com.umc.sculptor.ui.workshop

import com.umc.sculptor.R
import com.umc.sculptor.base.BaseBottomDialogFragment
import com.umc.sculptor.databinding.DialogDeleteBinding
import com.umc.sculptor.databinding.DialogDeleteConfirmBinding

class DeleteDialog : BaseBottomDialogFragment<DialogDeleteBinding>(R.layout.dialog_delete) {

    override fun initStartView(){
        super.initStartView()
    }
    override fun initDataBinding() {
        super.initDataBinding()

    }
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.tvDelete.setOnClickListener {
            dismiss()
            DeleteConfirmDialog().show(requireActivity().supportFragmentManager,"dialog")
        }


    }
}