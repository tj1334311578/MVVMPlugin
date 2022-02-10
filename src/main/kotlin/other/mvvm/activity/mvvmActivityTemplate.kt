package other.mvvm.activity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API


val mvvmActivityTemplate
    get() = template {
//        revision = 1
        name = "MVVM Activity"
        description = "适用于MVVMPlugin框架的Activity"
        minApi = MIN_API
//        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        lateinit var layoutName: StringParameter

        val activityClass = stringParameter {
            name = "Activity Name"
            default = "Main"
            help = "只输入名字，不要包含Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(activityClass.value.toLowerCase())}" }
        }

        val packageName = defaultPackageNameParameter


        val exitApi = booleanParameter {
            name="存在api"
            default=false
            help="默认不勾选,如果repository需要网络api,则添加对应的类,否则不添加"
        }

        val apiClass: StringParameter = stringParameter {
            name = "Api Name"
            default = ""
            visible={exitApi.value}
            help = "只输入相关Api类名"
            constraints = listOf(Constraint.CLASS)
        }
        widgets(
            TextFieldWidget(activityClass),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            CheckBoxWidget(exitApi),
            TextFieldWidget(apiClass)
        )

//        thumb { File("logo.png") }
        recipe = { data: TemplateData ->

            mvvmActivityRecipe(
                data as ModuleTemplateData,
                activityClass.value,
                layoutName.value,
                packageName.value,
                if (exitApi.value) {
                    apiClass.value
                } else {
                    null
                }
            )
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