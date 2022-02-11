
package other.mvvm.activity.src.app_package

fun mvvmActivityKt(
    applicationPackage: String?,
    activityClass: String,
    packageName: String,
    extraModel: Boolean,
)="""
package $packageName
import android.os.Bundle
import com.at.common.ui.act.BaseAct
${if (extraModel) "import androidx.activity.viewModels" else ""}
import com.at.common.viewbind.binding
import dagger.hilt.android.AndroidEntryPoint
import ${applicationPackage}.databinding.Activity${activityClass}Binding
@AndroidEntryPoint
class ${activityClass}Activity : BaseAct() {

    private val binding by binding<Activity${activityClass}Binding>()
    ${if (extraModel)"private val viewModel by viewModels<${activityClass}ViewModel>()" else ""}
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //此处代码直接用模板生成
        binding.initView()
        ${if(extraModel)"viewModel.observe()\n\t\t//viewModel.demo()" else ""}
    }

    private fun Activity${activityClass}Binding.initView() {

    }
    ${if (extraModel)
    "\tprivate fun ${activityClass}ViewModel.observe() {\n" +
    "\t\t// homeResult.observe(activity) {\n" +
    "\t\t//     binding.tv.text = it.getOrNull()\n" +
    "\t\t// }\n" +
    "\t}" else ""
    }
    
}    
"""