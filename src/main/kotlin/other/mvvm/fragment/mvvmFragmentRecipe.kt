package other.mvvm.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import other.mvvm.fragment.src.app_package.mvvmRepository
import other.mvvm.fragment.src.app_package.mvvmViewModel
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvvm.fragment.res.layout.mvvmFragmentXml
import other.mvvm.fragment.src.app_package.mvvmFragmentKt

fun RecipeExecutor.mvvmFragmentRecipe(
    moduleData: ModuleTemplateData,
    fragmentClass: String,
    layoutName: String,
    packageName: String
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

    val mvvmActivity = mvvmFragmentKt(projectData.applicationPackage, fragmentClass, packageName)
    // 保存Activity
    save(mvvmActivity, srcOut.resolve("${fragmentClass}Fragment.${ktOrJavaExt}"))
    // 保存xml
    save(mvvmFragmentXml(packageName, fragmentClass), resOut.resolve("layout/${layoutName}.xml"))
    // 保存viewmodel
    save(mvvmViewModel(packageName, fragmentClass), srcOut.resolve("${fragmentClass}ViewModel.${ktOrJavaExt}"))
    // 保存repository
    save(mvvmRepository(packageName, fragmentClass), srcOut.resolve("${fragmentClass}Repository.${ktOrJavaExt}"))
}
