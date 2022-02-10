
package other.mvvm.activity.src.app_package


fun mvvmRepository(
    packageName:String,
    activityClass:String,
    apiClass:String?
)="""
package $packageName
import javax.inject.Inject
// 网络请求,数据缓存等,都在此类处理,通常为挂起函数
class ${activityClass}Repository @Inject constructor(${if (apiClass.isNullOrBlank()){""}else{"private val repository:$apiClass"}}){

}
"""