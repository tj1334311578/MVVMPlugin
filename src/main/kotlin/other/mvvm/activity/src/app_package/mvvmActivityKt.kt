
package other.mvvm.activity.src.app_package

fun mvvmActivityKt(
    applicationPackage:String?,
    activityClass:String,
    packageName:String
)="""
package $packageName
import android.os.Bundle
import com.at.common.ui.act.BaseAct
import androidx.activity.viewModels
import com.at.common.viewbind.binding
import dagger.hilt.android.AndroidEntryPoint
import ${applicationPackage}.databinding.Activity${activityClass}Binding
@AndroidEntryPoint
class ${activityClass}Activity : BaseAct() {

  private val binding by binding<Activity${activityClass}Binding>()
  private val viewModel by viewModels<${activityClass}ViewModel>()
  
 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //此处代码直接用模板生成
        binding.initView()

        viewModel.observe()

        //viewModel.demo()

    }

    private fun Activity${activityClass}Binding.initView() {

    }

    private fun ${activityClass}ViewModel.observe() {
        // homeResult.observe(activity) {
        //     binding.tv.text = it.getOrNull()
        // }
    }
}    
"""