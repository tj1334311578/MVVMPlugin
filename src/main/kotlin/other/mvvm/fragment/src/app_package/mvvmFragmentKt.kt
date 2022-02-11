
package other.mvvm.fragment.src.app_package

fun mvvmFragmentKt(
    applicationPackage: String?,
    fragmentClass: String,
    packageName: String,
    extraModel: Boolean
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
    ${
    if (extraModel) {
        "private val viewModel by viewModels<${fragmentClass}ViewModel>()\n\t//private val viewModel by activityViewModels<${fragmentClass}ViewModel>()"
    }else{
        ""
    }}
    
    override fun layoutRes() = R.layout.fragment_${fragmentClass.toLowerCase()}
    
    override fun initUiAndListener() {
        binding.initView()
        ${
            if (extraModel){
                "viewModel.observe()"
            }else{
                ""
            }
        }
    }
    
    private fun Fragment${fragmentClass}Binding.initView() {

    }
    ${if (extraModel){
            "\tprivate fun ${fragmentClass}ViewModel.observe() {\n" +
                    "\n" +
                    "\t}"
        }else "" 
    }
}
"""