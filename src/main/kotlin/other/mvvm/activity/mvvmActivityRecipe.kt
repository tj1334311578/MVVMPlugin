package other.mvvm.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import other.mvvm.activity.res.layout.mvvmActivityXml
import other.mvvm.activity.src.app_package.mvvmActivityKt
import other.mvvm.activity.src.app_package.mvvmRepository
import other.mvvm.activity.src.app_package.mvvmViewModel
import com.android.tools.idea.wizard.template.RecipeExecutor

fun RecipeExecutor.mvvmActivityRecipe(
    moduleData: ModuleTemplateData,
    activityClass: String,
    layoutName: String,
    packageName: String,
    extraModel:Boolean,
    apiClass: String?
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
//    generateManifest(
//            moduleData = moduleData,
//            activityClass = "${activityClass}Activity",
//            activityTitle = activityClass,
//            packageName = packageName,
//            isLauncher = false,
//            hasNoActionBar = false,
//            generateActivityTitle = true,
//            requireTheme = false,
//            useMaterial2 = false
//    )

    val mvvmActivity = mvvmActivityKt(projectData.applicationPackage, activityClass, packageName,extraModel)
    // 保存Activity
    save(mvvmActivity, srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}"))
    // 保存xml
    save(mvvmActivityXml(packageName, activityClass), resOut.resolve("layout/${layoutName}.xml"))
    if (extraModel){
    // 保存viewModel
    save(mvvmViewModel(packageName, activityClass), srcOut.resolve("${activityClass}ViewModel.${ktOrJavaExt}"))
    // 保存repository
    save(mvvmRepository(packageName, activityClass,apiClass), srcOut.resolve("${activityClass}Repository.${ktOrJavaExt}"))
    }
}

