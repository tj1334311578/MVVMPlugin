package other.mvvm.activity.src.app_package

fun mvvmViewModel(
    packageName:String,
    activityClass:String
)="""
package $packageName
import com.at.common.UnFlowLiveData
import com.at.common.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ${activityClass}ViewModel @Inject constructor(private val repository:${activityClass}Repository): BaseViewModel() {
    val homeResult = UnFlowLiveData<Result<String>>()
    
}    
"""