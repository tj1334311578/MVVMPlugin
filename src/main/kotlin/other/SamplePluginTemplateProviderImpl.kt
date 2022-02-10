package other

import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.android.tools.idea.wizard.template.stringParameter
import other.mvvm.activity.mvvmActivityTemplate
import other.mvvm.fragment.mvvmFragmentTemplate

/**
 * @description:
 * @author:  stone
 * @email:
 * @date :   2022/2/8 下午5:48
 */
class SamplePluginTemplateProviderImpl : WizardTemplateProvider() {
    val clazz = stringParameter {
        name = "Activity Name"
        default = "Main"
        help = "只输入名字，不要包含Activity"
        constraints = listOf(Constraint.NONEMPTY)
    }

    override fun getTemplates(): List<Template> = listOf(
        // activity的模板
        mvvmActivityTemplate,
        // fragment的模板
        mvvmFragmentTemplate
    )
}