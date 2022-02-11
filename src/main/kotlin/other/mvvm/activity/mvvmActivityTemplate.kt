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


        val extraModel = booleanParameter {
            name="存在model"
            default=true
            help="默认勾选,如何存在,则生成相对应的model以及repository,否则不生成"
        }

        val apiClass: StringParameter = stringParameter {
            name = "Api Name"
            default = ""
            visible={extraModel.value}
            help = "只输入相关model所需要的repository中的Api类名"
            constraints = listOf(Constraint.CLASS,Constraint.UNIQUE,if (extraModel.value) Constraint.NONEMPTY else Constraint.VALUES)
        }
        widgets(
            TextFieldWidget(activityClass),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            CheckBoxWidget(extraModel),
            TextFieldWidget(apiClass)
        )

//        thumb { File("logo.png") }
        recipe = { data: TemplateData ->

            mvvmActivityRecipe(
                data as ModuleTemplateData,
                activityClass.value,
                layoutName.value,
                packageName.value,
                extraModel.value,
                apiClass.value
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