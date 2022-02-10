
package other.mvvm.fragment.src.app_package

fun mvvmFragmentKt(
    applicationPackage:String?,
    fragmentClass:String,
    packageName:String
)="""
package $packageName
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.at.common.ui.fragment.BaseFragment
import com.at.common.viewbind.binding
import ${applicationPackage}.R
import ${applicationPackage}.databinding.Fragment${fragmentClass}Binding

@AndroidEntryPoint
class ${fragmentClass}Fragment : BaseFragment() {

    private val binding by binding<Fragment${fragmentClass}Binding>()
    private val viewModel by viewModels<${fragmentClass}ViewModel>()
    //private val viewModel by activityViewModels<${fragmentClass}ViewModel>()
    
    override fun layoutRes() = R.layout.fragment_${fragmentClass.toLowerCase()}
    
    override fun initUiAndListener() {
        binding.initView()
        viewModel.observe()
    }
    
    private fun Fragment${fragmentClass}Binding.initView() {

    }
    private fun ${fragmentClass}ViewModel.observe() {

    }
}
"""