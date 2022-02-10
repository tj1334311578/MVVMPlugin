
package other.mvvm.fragment

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API


val mvvmFragmentTemplate
    get() = template {
//        revision = 1
        name = "MVVM Fragment"
        description = "适用于MVVMPlugin框架的Fragment"
        minApi = MIN_API
//        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        lateinit var layoutName: StringParameter

        val fragmentClass = stringParameter {
            name = "Fragment Name"
            default = "First"
            help = "只输入名字，不要包含Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        layoutName = stringParameter {
            name = "Layout Name"
            default = "fragment_first"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${fragmentToLayout(fragmentClass.value.toLowerCase())}" }
        }

        val packageName = defaultPackageNameParameter

        val exitApi= booleanParameter {
            name="存在api"
            help="默认不勾选,如果repository需要网络api,则添加对应的类,否则不添加"
            default=false
        }

        val apiClass= stringParameter {
            name = "Api Name"
            default = ""
            visible={exitApi.value}
            help = "只输入相关Api类名"
            constraints = listOf(Constraint.CLASS)
        }

        widgets(
            TextFieldWidget(fragmentClass),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            CheckBoxWidget(exitApi),
            TextFieldWidget(apiClass)
        )
//        thumb { File("logo.png") }
        recipe = { data: TemplateData ->

            mvvmFragmentRecipe(
                data as ModuleTemplateData,
                fragmentClass.value,
                layoutName.value,
                packageName.value,
                if(exitApi.value){
                    apiClass.value
                }else{
                    null
                })
        }
    }



val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.mycompany.myapp"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }